# Day 1 challenge 1 solution
The way that I thought about this was first to try to find a way to intake the data. This was only difficult because I figured that the scale of the    
input data was way too much for an array or ArrayList. However, upon thinking about it more I realized that there should be enough space in an ArrayList    
so I through all of the data into 2 of them and it worked. I also did it this way as it allowed me to only have to go through each line of the file    
once and using the split function of a string (there were exactly 3 spaces in between the lists) allowed a relatively painless experience. Once I had     
all the data in a list the collections interface allowed me so sort and from there it was only a matter of subtracting the left from the right list.    
