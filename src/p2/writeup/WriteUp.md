# Project 2 (uMessage) Write-Up #
--------

## Project Enjoyment ##
- What was your favorite part of the project? Why?
  <pre>TODO</pre>

- What was your least favorite part of the project? Why?
  <pre>TODO</pre>

- How could the project be improved? Why?
  <pre>TODO</pre>

- Did you enjoy the project?  Why or why not?
  <pre>TODO</pre>
    
-----

## Experiments ##
Throughout p1 and p2, you have written (or used) several distinct implementations of the Dictionary interface:
 - HashTrieMap 
 - MoveToFrontList
 - BinarySearchTree
 - AVLTree
 - ChainingHashTable
 
 In this Write-Up, you will compare various aspects of these data structures.  This will take a significant amount of
 time, and you should not leave it to the last minute.  For each experiment, we expect you to:
 - Explain how you constructed the inputs to make your conclusions
 - Explain why your data supports your conclusions
 - Explain your methodology (e.g., if we wanted to re-run your experiment, we would be able to)
 - Include the inputs themselves in the experiments folder
 - Include your data either directly in the write-up or in the experiments folder
 - If you think it helps your explanation, you can include graphs of the outputs (we recommend that you do this for some of them)
 - We recommend that you keep your "N" (as in "N-gram") constant throughout these experiments. (N = 2 and N = 3 are reasonable.) 
 - You should probably run multiple trials for each data point to help remove outliers.


### BST vs. AVLTree ###
Construct input files for BST and AVLTree to demonstrate that an AVL Tree is asymptotically better
than a Binary Search Tree. To do this, we expect you to show trends.  You might consider fitting a curve to
your results. Explain your intuition on why your results are what they are.
<pre>TODO</pre>

### ChainingHashTable ###
Your ChainingHashTable should take as an argument to its constructor the type of "chains" it uses.  Determine
which type of chain is (on average) best: an MTFList, a BST, or an AVL Tree.  Explain your intuition on why
the answer you got makes sense (or doesn't!). 
<pre>TODO</pre>
 
### Hash Functions ###
Write a new hash function (it doesn't have to be any good, but remember to include the code in your repository).
Compare the runtime of your ChainingHashTable when the hash function is varied.  How big of a difference can the
hash function make?  (You should keep all other inputs (e.g., the chain type) constant.)  Explain your intuition on
why your results are what they are.
<pre>TODO</pre>

### General Purpose Dictionary ###
Compare BST, AVLTree, ChainingHashTable, and HashTrieMap on alice.txt.  Is
there a clear winner?  Why or why not?  Is the winner surprising to you?
<pre>TODO</pre>

