## Day 2 challenge 1 solution 
Just like day 1 the code was written to either take a user input or the file input from the terminal. Also like day 1 once the data was inputted it was fairly simple to create a function to check the safety. The function
worked by checking if the list was the same as a sorted/reversed version of the same list. If either was true the function would check for duplicates, then if a difference of greater than 3 was in the array. It also
turns out that this solution worked for both this challenge and the next challenge but I did not know that at the time. 
## Day 2 challenge 2 solution
This took forever. If you check the commit history, this code was published approximately 10 days after the challenge was posted this is because try after try it just would not work whether it was some weird edge case or
an obvious thing that I just forgot about. For this reason, this code was written and rewritten at least 5 times and on 2 different computers. The way it ended up working was 2 functions 
one(which could have been the code from the first challenge) was to check the safety using the difference between the index and the index+1. The second function took an ArrayList and effectively brute-forced the problem
by trying the first function with every ArrayList with one element removed(and the list as a whole) it was used as the key for a hash map with the result as the value. Finally, it looped through the hash map to find
if there were any possibilities where the answer was true and returned false otherwise.

Syntax for both challenges      
java adventOfCode2024.day1.challenge# day\ 1/input.txt where # is the challenge number
