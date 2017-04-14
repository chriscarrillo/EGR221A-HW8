package datastructures.dictionaries;

import egr221a.interfaces.trie.BString;
import egr221a.interfaces.trie.TrieSet;

public class HashTrieSet<A extends Comparable<A>, E extends BString<A>> extends TrieSet<A, E> {
    /* Note: You should not be adding any methods to this class...you only need to implement
     *       the constructor!  */

    //Uncomment from line 13-17
    public HashTrieSet(Class<E> Type) {
        // Call the correct super constructor...that's it!
        super(new HashTrieMap<A, E, Boolean>(Type));
    }
}
