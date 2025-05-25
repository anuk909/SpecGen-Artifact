import openai
import os
import signal
import time
from openai import AzureOpenAI
import httpx

def create_chatgpt_config(messages):
    config = {
        "model": "gpt-3.5-turbo",
        "temperature": 0.4,
        "messages": []
    }
    config["messages"] = messages
    return config

def handler(signum, frame):
    raise Exception("end of time")

def request_chatgpt_engine(config):
    client = openai.OpenAI()
    ret = None
    while ret is None:
        try:
            signal.signal(signal.SIGALRM, handler)
            signal.alarm(100)
            ret = client.chat.completions.create(**config)
            signal.alarm(0)  # cancel alarm
        except openai.InvalidRequestError as e:
            print(e)
            signal.alarm(0)  # cancel alarm
        except openai.RateLimitError as e:
            print("Rate limit exceeded. Waiting...")
            print(e)
            signal.alarm(0)  # cancel alarm
            time.sleep(5)  # wait for a minute
        except openai.APIConnectionError as e:
            print("API connection error. Waiting...")
            signal.alarm(0)  # cancel alarm
            time.sleep(5)  # wait for a minute
        except Exception as e:
            print(e)
            print("Unknown error. Waiting...")
            signal.alarm(0)  # cancel alarm
            time.sleep(1)  # wait for a minute
    return ret
    """
    with open("./api_key.txt", 'r') as f:
        api_key = f.read().strip()
    client = AzureOpenAI(
        azure_endpoint = "https://mygavin.openai.azure.com/", 
        api_key = api_key,  
        api_version="2024-02-01"
    )
    completion = client.chat.completions.create(
        model="Gavin_deployment",
        messages=config["messages"]
    )
    return {
        'choices': [ {'message': {'role':completion.choices[0].message.role, 'content':completion.choices[0].message.content}} ]
    }
    """
