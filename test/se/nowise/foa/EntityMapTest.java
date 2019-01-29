/*
 * Copyright (C) 2009-2019 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: https://nowise.se/oss/libfoa/spec
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
package se.nowise.foa;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class EntityMapTest {

    public EntityMapTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEncoded method, of class EntityMap.
     */
    @Test
    public void testGetEncoded() {
        System.out.println("getEncoded(char)");
        EntityMap instance = new EntityMap();
        String str = "([])=";
        for (int i = 0; i < str.length(); ++i) {
            char key = str.charAt(i);
            String val = String.format("%%%X", new Integer(key));
            assertEquals(val, instance.getEncoded(key));
        }
    }

    /**
     * Test of getDecoded method, of class EntityMap.
     */
    @Test
    public void testGetDecoded() {
        System.out.println("getDecoded(String)");
        EntityMap instance = new EntityMap();
        String str = "([])=";
        for (int i = 0; i < str.length(); ++i) {
            char val = str.charAt(i);
            String key = String.format("%%%X", new Integer(val));
            assertEquals(val, instance.getDecoded(key));
        }
    }

    /**
     * Test of getEncodeMap method, of class EntityMap.
     */
    @Test
    public void testGetEncodeMap() {
        System.out.println("getEncodeMap()");
        EntityMap instance = new EntityMap();
        HashMap<Character, String> map = instance.getEncodeMap();
        assertNotNull(map);
        String str = "([])=";
        for (int i = 0; i < str.length(); ++i) {
            char key = str.charAt(i);
            String val = String.format("%%%X", new Integer(key));
            assertEquals(val, map.get(key));
        }
    }

    /**
     * Test of getDecodeMap method, of class EntityMap.
     */
    @Test
    public void testGetDecodeMap() {
        System.out.println("getDecodeMap()");
        EntityMap instance = new EntityMap();
        HashMap<String, Character> map = instance.getDecodeMap();
        assertNotNull(map);
        String str = "([])=";
        for (int i = 0; i < str.length(); ++i) {
            char val = str.charAt(i);
            String key = String.format("%%%X", new Integer(val));
            assertEquals(val, (char) map.get(key));
        }
    }

}
