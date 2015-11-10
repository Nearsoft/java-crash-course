# Session 3:  Strings

## Goals
   
   - comparisons
   - concatenations
   - string methods
   - Workshop create search workshop feature
     
## Class script

1. [INSTRUCTOR] gives intro to Strings
    Compare:
        - equals
        - "=="
    Concatenate:
        -StringBuilder
        -"ola" + "ke" + "ase" = "ola ke ase"

2. [STUDENT] tries comparison example


3. [INSTRUCTOR] explains ".equals" vs "=="
The == checks for reference comparison
"equals" compares the value of the object

you should always use "equals" when comparing strings unless checking if a String object is null. Example:

fruit =! null

use:

"Apple".equals(fruit) instead of fruit.equals("Apple") to avoid NullPointerException


