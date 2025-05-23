package net.cf.form.repository.sql.parser;

/**
 * 控制字符
 *
 * @author clouds
 */
public interface LayoutCharacters {

    /**
     * Tabulator column increment.
     */
    static final int TAB_INC = 8;

    /**
     * Tabulator character.
     */
    static final byte TAB = 0x8;

    /**
     * Line feed character.
     */
    static final byte LF = 0xA;

    /**
     * Form feed character.
     */
    static final byte FF = 0xC;

    /**
     * Carriage return character.
     */
    static final byte CR = 0xD;

    /**
     * End of input character. Used as a sentinel to denote the character one beyond the last defined character in a
     * source file.
     */
    static final byte EOI = 0x1A;
}
