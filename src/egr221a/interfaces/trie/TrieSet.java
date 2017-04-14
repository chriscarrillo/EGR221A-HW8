package egr221a.interfaces.trie;

import egr221a.interfaces.misc.Set;

public abstract class TrieSet<A extends Comparable<A>, E extends BString<A>> extends Set<E> {
    protected TrieSet(TrieMap<A, E, Boolean> backingMap) {
        super(backingMap);
    }

    @SuppressWarnings("unchecked")
    public final boolean containsPrefix(E e) {
        return ((TrieMap<A, E, Boolean>) this.map).findPrefix(e);
    }
}
