FORMAT_INIT_PROMPT = """
Please generate JML specifications for the Java program given below.
```
{src_code}
```
Note that:
+ You SHOULD generate `ensures` statements for every method, and `maintaining` statements for every loop.
+ Consider adding `/*@ spec_public @*/` specifications to all the members (especially private ones) within the class.
+ You SHOULD only insert the specifications into the source code given to you. You SHOULD NOT modify any content other than the comments inserted into the code.
+ You SHOULD output the code in its entirety, withou omitting any original content.
"""

FORMAT_REFINE_PROMPT = """
Your specification got the following error information:

{err_info}

Please generate again.
"""

FORMAT_GENERATION_PROMPT = """
Please generate JML specifications for the Java program given below.
```
{src_code}
```
Note that:
+ You SHOULD generate `ensures` statements for every method, and `maintaining` statements for every loop.
+ Consider adding `/*@ spec_public @*/` specifications to all the members (especially private ones) within the class.
+ You SHOULD only insert the specifications into the source code given to you. You SHOULD NOT modify any content other than the comments inserted into the code.
+ You SHOULD output the code in its entirety, withou omitting any original content.
"""

FORMAT_REFINEMENT_PROMPT = """
The following Java code is instrumented with JML specifications:
```
{code}
```
Verifier failed to verify the specifications given above, with error information as follows:
```
{info}
```
Please refine the specifications, so that it can pass the verification.
Note that:
+ You SHOULD NOT modify any content other than the specifications inserted into the code.
+ You SHOULD output the code in its entirety, withou omitting any original content.
"""
