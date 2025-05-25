import argparse
import os
import openai
import time
import signal

from util.chatgpt_wrapper import request_chatgpt_engine, create_chatgpt_config
from util.prompt_format import FORMAT_INIT_PROMPT, FORMAT_REFINE_PROMPT
from util.util import file2str, parse_code_from_reply
from util.token_counter import count_config_token

from generation_prompt import create_generation_prompt_config
from refinement_prompt import create_specialized_patcher_prompt_config, gen_extra_guidance, extract_err_type

def token_limit_fitter(config, token_limit=4090):
    res = config
    while(count_config_token(res) > token_limit):
        res['messages'] = res['messages'][3:len(res['messages'])]
        res['messages'].insert(0, config['messages'][0])
    return res

class TimeoutException(Exception):
    pass

def timeout_handler(signum, frame):
    raise TimeoutException

signal.signal(signal.SIGALRM, timeout_handler)

def validate_openjml(code_with_spec, classname):
    tmp_filename = os.path.abspath(".") + "/tmp/{filename}.java".format(filename=classname)
    tmp_file = open(tmp_filename, 'w')
    tmp_file.write(code_with_spec)
    tmp_file.close()
    cmd = os.path.abspath(".") + "/openjml/openjml --esc --esc-max-warnings 1 --arithmetic-failure=quiet --nonnull-by-default --quiet -nowarn --prover=cvc4 " + tmp_filename
    TIMEOUT = 400 # seconds
    signal.alarm(TIMEOUT)
    try:        
        res_lines = os.popen(cmd).readlines()
        signal.alarm(0)
    except TimeoutException:
        return ""
        # sys.exit(-1)
    res = ""
    for line in res_lines:
        res = res + line
    return res

def config2str(config):
    res = ""
    for message in config["messages"]:
        res += "{role}: {content}\n".format(role=message['role'], content=message['content'])
    return res

def print_config(config):
    print(config2str(config))

def print_msg(message):
    print("{r}:{c}".format(r=message['role'], c=message['content']))

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--input", type=str, default="")
    parser.add_argument("--key_file", type=str, default="api_key.txt")
    parser.add_argument("--max_iterations", type=int, default=20)
    parser.add_argument("--verbose", action='store_true')
    args = parser.parse_args()

    if not os.access(args.input, os.R_OK):
        print("Cannot open input file {filename}".format(filename=args.input))
        exit(-1)
    classname = args.input.split('/')[-1].split('.')[0]

    input_code = file2str(args.input)

    current_time_str = time.strftime("%Y_%m_%d_%H_%M_%S", time.localtime(time.time()))
    f_log = open(os.path.abspath(".") + "/logs/log-{name}-{time_str}.txt".format(name=classname, time_str=current_time_str), "w")

    # Candidate Generation Phase
    config = {}
    current_code = input_code
    err_info = ""
    for i in range(1, args.max_iterations+1):
        print("=============== Iteration {num} ===============".format(num=i))
        if i == 1:
            config = create_generation_prompt_config(input_code, classname)
            print_config(config)
            ret = request_chatgpt_engine(config)
            print("assistant:", ret['choices'][0]['message']['content'])
            current_code = parse_code_from_reply(ret['choices'][0]['message']['content'])
            current_code = current_code.strip()
            config['messages'].append(
                {
                    'role': 'assistant',
                    'content': "```\n{code}\n```".format(code=current_code)
                }
            )
        else:
            refine_msg = {
                'role': 'user',
                'content': FORMAT_REFINE_PROMPT.format(err_info=err_info)
            }
            refine_msg['content'] += gen_extra_guidance(err_info)
            config['messages'].append(refine_msg)
            print_msg(refine_msg)
            token_limit_fitter(config, 3000)
            ret = request_chatgpt_engine(config)
            print("assistant:", ret['choices'][0]['message']['content'])
            current_code = parse_code_from_reply(ret['choices'][0]['message']['content'])
            current_code = current_code.strip()
            config['messages'].append(
                {
                    'role': 'assistant',
                    'content': "```\n{code}\n```".format(code=current_code)
                }
            )
        f_log.write("==============================\n")
        f_log.write(current_code + "\n")
        err_info = validate_openjml(current_code, classname)
        f_log.write("==============================\n")
        f_log.write(err_info + "\n")
        if err_info == "":
            break
        # time.sleep(20)

    f_log.close()

    os.makedirs(os.path.abspath(".") + "/output", exist_ok=True)
    output_filename = classname + ".java"
    if os.path.exists(os.path.abspath(".") + "/output/" + output_filename):
        index = 1
        while os.path.exists(os.path.abspath(".") + "/output/" + output_filename):
            index = index + 1
            output_filename = classname + ".java ({num})".format(num=str(index))
    with open(os.path.join("./output/", output_filename)) as f:
        f.write(current_code)

if __name__ == "__main__":
    main()
