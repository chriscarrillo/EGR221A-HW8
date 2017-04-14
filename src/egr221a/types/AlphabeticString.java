package egr221a.types;

import egr221a.interfaces.trie.BString;
import egr221a.interfaces.worklists.FixedSizeFIFOWorkList;

public class AlphabeticString extends BString<Character> {
    public AlphabeticString(String s) {
        super(BString.wrap(s.toCharArray()));
    }

    public AlphabeticString(FixedSizeFIFOWorkList<Character> q) {
        super(q);
    }

    public AlphabeticString(Character[] s) {
        super(s);
    }

    public static Class<Character> getLetterType() {
        return Character.class;
    }
}
