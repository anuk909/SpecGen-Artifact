import os


def file2str(filename):
    res = ""
    with open(filename, "r") as f:
        for line in f.readlines():
            res = res + line
    return res


def save_output_to_file(content, classname, prefix="", timestamp=None):
    """
    Save the output content to a file in the output directory.
    Uses timestamp and prefix for unique filename generation.

    Args:
        content: The content to save to file
        classname: The base name for the output file (without .java extension)
        prefix: Optional prefix to identify the generator (e.g., 'specgen', 'myhoudini', 'conversational')
        timestamp: Optional timestamp string; if None, current time will be used

    Returns:
        str: The path to the output file
    """
    import time

    os.makedirs(os.path.abspath(".") + "/output", exist_ok=True)

    # Generate timestamp if not provided
    if timestamp is None:
        timestamp = time.strftime("%Y%m%d_%H%M%S", time.localtime(time.time()))

    # Create filename with optional prefix and timestamp
    if prefix:
        output_filename = f"{prefix}_{classname}_{timestamp}.java"
    else:
        output_filename = f"{classname}_{timestamp}.java"

    output_path = os.path.join("./output/", output_filename)
    with open(output_path, "w") as f:
        f.write(content)
    return output_path


def parse_code_from_reply(content):
    # lines = content.split('\n')
    # res = ""
    # flag = False
    # for line in lines:
    #     if line.strip() == '```' and flag:
    #         break
    #     if flag:
    #         res = res + line + "\n"
    #     if line.strip() == '```' and not flag:
    #         flag = True
    content = "a" + content
    extracted_str = content.split("```")[1]
    extracted_str = (
        extracted_str
        if not extracted_str.startswith("java")
        else extracted_str[len("java") :]
    )
    return extracted_str
