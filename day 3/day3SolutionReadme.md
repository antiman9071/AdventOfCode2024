## Day 3 challenge 1 solution
In both challenges, the goal needed to be to multiply 2 things that were within an instruction. For this reason, I used regex parsing to search 
for the pattern and then do another parse to get the numbers. Once this was done I split the numbers based on the comma and multiplied them. Both challenges 
used the same file input code as the first 2 days.
## Day 3 challenge 2 solution
There were many ways I attempted to solve this problem including an ArrayList and later a HashMap but the way I ended up using was a tree map. The treemap
used a binary style sort with the key being the index of the conditionals we needed to find(do() and don't()) and the value is a true or false value based
on whether the conditional was do() or don't(). Once the treemap was created I looped through the map and stored the value to be used as the
beginning of a substring. I also stored the value as the true or false would be dictated by the start of the substring not the end. once done I passed the 
substring to a function that was effectively the challenge 1 solution and returned the answer.

Syntax for both challenges
java adventOfCode2024.day3.challenge# day\ 3/input.txt where # is the challenge number

