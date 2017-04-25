package datastructures.dictionaries;

import egr221a.datastructures.trees.BinarySearchTree;

/**
 * AVLTree.java extends BinarySearchTree. It constructs an AVLTree.
 * It also overrides some of its parent methods (find and insert).
 *
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and callst o superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 *
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 *    instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 *    children array or left and right fields in AVLNode.  This will 
 *    instead mask the super-class fields (i.e., the resulting node 
 *    would actually have multiple copies of the node fields, with 
 *    code accessing one pair or the other depending on the type of 
 *    the references used to access the instance).  Such masking will 
 *    lead to highly perplexing and erroneous behavior. Instead, 
 *    continue using the existing BSTNode children array.
 * 4. If this class has redundant methods, your score will be heavily
 *    penalized.
 * 5. Cast children array to AVLNode whenever necessary in your
 *    AVLTree. This will result a lot of casts, so we recommend you make
 *    private methods that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 */

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V>  {
    /**
     * Default constructor for AVLTree calls parent's constructor
     */
    public AVLTree() {
        super();
    }

    /**
     * Finds with the given key and value
     * @param key given key to find
     * @param value given value to find
     * @return BSTNode
     */
    @Override
    protected BSTNode find(K key, V value){
        if(key == null){
            throw new IllegalArgumentException();
        }

        return super.find(key, value);
    }

    /**
     * Inserts data into the AVLTree with given key and given value
     * @param key the key of the data that will be inserted
     * @param value the value of the data that will be inserted
     * @return the value inserted
     */
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        V oldValue = find(key);
        this.root = insert((AVLNode) this.root, key, value);

        return oldValue;
    }

    // Private helper method for inserting
    private AVLNode insert(AVLNode current, K key, V value) {
        if(current != null){
            int direction = Integer.signum(key.compareTo(current.key));
            if(direction == 0){

                current.value = value;
                return current;

            } else {
                int child = Integer.signum(direction + 1);
                if(current.getAVLChildren(child) != null){
                    current.children[child] = insert(current.getAVLChildren(child),key,value);
                } else {
                    current.children[child] = new AVLNode(key,value,0);
                    size++;
                }

                heightUpdate(current);

                if(heightDifference(current) >= 2){
                    if(heightDifference(current.getAVLChildren(0)) < 0){
                        current.children[0] = rotate(current.getAVLChildren(0),1);
                    }
                    return rotate(current,0);
                } else if (heightDifference(current) <= -2) {
                    if(heightDifference(current.getAVLChildren(1)) > 0){
                        current.children[1] = rotate(current.getAVLChildren(1), 0);
                    }
                    return rotate(current,1);
                }
                return current;
            }
        } else {
            root = new AVLNode(key, value, 0);
            current = (AVLNode) root;

            size++;
            return current;
        }
    }

    /**
     * Gets the height of the given root
     * @param root the root to be getting the height from
     * @return an int that represents the height
     */
    protected int height(BSTNode root) {
        return root == null ? -1 : ((AVLNode)root).getHeight();
    }

    /**
     * will accept an AVLNode and perform a height update on the AVLTree.
     * @param current
     */
    private void heightUpdate(AVLNode current) {
        int leftHeight = (current.children[0] == null) ? -1 : current.getAVLChildren(0).getHeight();

        int rightHeight = (current.children[1] == null) ? -1 : current.getAVLChildren(1).getHeight();

        current.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    /**
     * Will rotate dependent on the parent and childIndex
     * @param parent
     * @param childIndex
     * @return
     */
    private AVLNode rotate(AVLNode parent, int childIndex) {
        int oppositeIndex = 1 - childIndex;
        AVLNode temp = parent.getAVLChildren(childIndex);

        parent.children[childIndex] = parent.getAVLChildren(childIndex).getAVLChildren(oppositeIndex);
        temp.children[oppositeIndex] = parent;

        heightUpdate(parent);
        heightUpdate(temp);
        return temp;
    }

    // Private helper method to get the difference of the height
    private int heightDifference(AVLNode current) {
        if (current == null) {
            return -1;
        }
        int leftHeight = (current.children[0] == null) ? -1 : current.getAVLChildren(0).getHeight();

        int rightHeight = (current.children[1] == null) ? -1 : current.getAVLChildren(1).getHeight();

        return leftHeight - rightHeight;
    }

    public void checkBalance( ) {
        checkBalance(root);
    }

    //ONLY FOR JUNIT Testing
    private int checkBalance(BSTNode t) {
        if( t == null ) return -1;

        if( t != null ) {
            int hl = checkBalance(t.children[0]);
            int hr = checkBalance(t.children[1]);
            if( Math.abs( height( t.children[0] ) - height( t.children[1] ) ) > 1 ||
                    height( t.children[0] ) != hl || height( t.children[1] ) != hr ||
                    height( t.children[0]) != heightRecursion( t.children[0]) ||
                    height( t.children[1]) != heightRecursion( t.children[1]))
                throw new IllegalStateException("Need to fix your code");
        }
        return height( t );
    }

    //ONLY FOR JUNIT Testing
    public int height(){
        return height(root); //you should have this height helper defined in AVLTree.java (the height helper shouldn't use recursion) which returns the height given the root
    }

    //ONLY FOR JUNIT Testing
    private int heightRecursion(BSTNode node){
        if(node == null) return -1;
        return Math.max( heightRecursion(node.children[0]), heightRecursion(node.children[1])) + 1;
    }


        /**
         * A subclass of BSTNode with an added height.
         */
    public class AVLNode extends BSTNode {
        private int height;

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public AVLNode(K key, V value, int height) {
            super(key, value);
            this.height = height;
        }


        public AVLNode getAVLChildren(int child) {
            return (AVLNode) children[child];
        }

    }
}
