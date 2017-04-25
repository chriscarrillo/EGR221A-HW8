# Project 2 (uMessage) Write-Up #
--------

## Project Enjoyment ##
- What was your favorite part of the project? Why?
  <pre>Our favorite part of the project was the fact that we were able to create and implement our own data structures.
  This was our favorite part because we were able to see how they were actually constructed.</pre>

- What was your least favorite part of the project? Why?
  <pre>We found this project to be very difficult. It took a lot of time to come up with results that would allow us to
  compile the code. The hardest part of the project was probably constructing things with very little information, as
  the spec has become more vague. However, this isn't necessarily a bad thing. We are new to the vague specs, so this is
  some good practice for more difficult projects.</pre>

- How could the project be improved? Why?
  <pre>The due dates for the checkpoints felt like they were very close to each other. Perhaps because it was close to
  finals. It would have been neat to see the uMessage server working as well! We would have been able to see how the 
  worked together. I realize this was out of your power, but we're not even mad!</pre>

- Did you enjoy the project?  Why or why not?
  <pre>Although the project was the most difficult yet, we enjoyed it overall because it was a great learning experience.
  One of the reasons as to why we found it difficult may have been because of how vague the spec was. I understand it's
  necessary for the specs to become more vague, however, it does take time to adjust.</pre>
    
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
<pre>WriteUpTest.java is a class that contains createTree, which constructs either a BST or an AVL Tree based on the
boolean given. The Scanner contains a file that it scans through. We tested five different files (adventures.txt,
talk.txt, alice.txt, dictionary.txt, and dictionary2.txt). Based on our results, we discovered that AVL Tree has a
"sweet spot". It performs better than BST in the middle of the graph, which is provided in the PDF. However, our results
show that the BST had a better performance in the beginning and the end of the graph. Along with the graph, the data is
provided. Technically speaking, the middle of the graph shows that when the number of words increased, the runtime
decreased for an AVL Tree. AVL Tree seems to be asymptotically better than BST around 25,000 words, but it seems to be
the only case. The reasoning may be because BST may have never experienced its worst case. In addition, AVL Tree does
have to do rotations. This could be an effect to why it didn't perform as well as BST.</pre>

### ChainingHashTable ###
Your ChainingHashTable should take as an argument to its constructor the type of "chains" it uses.  Determine
which type of chain is (on average) best: an MTFList, a BST, or an AVL Tree.  Explain your intuition on why
the answer you got makes sense (or doesn't!). 
<pre>After conducting 5 tests (using alice.txt) for BST, AVL Tree, and MTFList, the results showed the average runtime
for BST was 370.4ms, 386.6ms for AVL Tree, and 387.8ms for MTFList. BST was the average winner by more than 15ms. This
makes sense because BST did not reach its worst case, which is O(n).</pre>
 
### Hash Functions ###
Write a new hash function (it doesn't have to be any good, but remember to include the code in your repository).
Compare the runtime of your ChainingHashTable when the hash function is varied.  How big of a difference can the
hash function make?  (You should keep all other inputs (e.g., the chain type) constant.)  Explain your intuition on
why your results are what they are.
<pre>After using a different hashCode method, the average runtime (after 5 tests) was 382.4ms. With the rehash method, 
the average runtime (after 5 tests) was 376.6ms. That is a difference of 5.8ms. Although this may seem insignificant,
the hashCode makes a huge difference on a large scale. With bigger files, the difference may be much greater. It's
good practice to have an efficient hash function in general, but most importantly on a larger scale of data.</pre>

### General Purpose Dictionary ###
Compare BST, AVLTree, ChainingHashTable, and HashTrieMap on alice.txt.  Is
there a clear winner?  Why or why not?  Is the winner surprising to you?
<pre>After using alice.txt for all four of them, the results showed that AVL Tree was the clear winner. BST took 289ms,
AVL Tree took 264ms, ChainingHashTable took 338ms, and HashTrieMap took 300ms. It made sense to see that AVL Tree won 
this test because of the number of words of alice.txt (26,524 words). Our graphs showed this previously for the first
question. The time for the ChainingHashTable was surprising. We did not expect it to be as slow as it was compared to
the others.</pre>

