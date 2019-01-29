/*
 * Copyright (C) 2009-2018 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * EntityMap.java
 *
 * Created: Apr 1, 2009, 7:15:06 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

import java.util.HashMap;

/**
 * Utility class for mapping special chars (entity types) to their encoded and decoded string
 * representation. This class acts as an lookup table for special chars &lt;-&gt; encoded representaion.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class EntityMap {

    /**
     * Construct the lookup table for use by both an Encoder and Decoder.
     */
    public EntityMap() {
        encodeMap = new HashMap<>();
        decodeMap = new HashMap<>();
        String str = "([])=";
        for (int i = 0; i < str.length(); ++i) {
            Character key = str.charAt(i);
            String val = String.format("%%%X", new Integer(key));
            encodeMap.put(key, val);
            decodeMap.put(val, key);
        }
    }

    /**
     * Lookup the encoded representation given the special char.
     *
     * @param key The special char.
     * @return The special char string representation.
     */
    public String getEncoded(char key) {
        return encodeMap.get(key);
    }

    /**
     * Lookup the special char given its encoded representation.
     *
     * @param key The encoded string.
     * @return The special char value.
     */
    public char getDecoded(String key) {
        return decodeMap.get(key);
    }

    /**
     * @return The encode map.
     */
    public HashMap<Character, String> getEncodeMap() {
        return encodeMap;
    }

    /**
     * @return The decode map.
     */
    public HashMap<String, Character> getDecodeMap() {
        return decodeMap;
    }
    protected HashMap<Character, String> encodeMap;
    protected HashMap<String, Character> decodeMap;
}
