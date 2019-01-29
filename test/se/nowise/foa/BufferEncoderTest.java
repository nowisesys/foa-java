/*
 * Copyright (C) 2009-2019 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
package se.nowise.foa;

import se.nowise.foa.BufferEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nowise.foa.Encoder.Option;
import se.nowise.foa.Entity.SpecialChar;

/**
 * Unit test for class BufferEncoder.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class BufferEncoderTest {

    public BufferEncoderTest() {
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
     * Test of getBuffer method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBuffer() throws Exception {
        System.out.println("getBuffer()");
        BufferEncoder instance = new BufferEncoder();
        assertEquals(null, instance.getBuffer());
        instance.write("X");
        assertEquals(2, instance.getBuffer().length());    // 'X\n'
    }

    /**
     * Test of setOption method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetOption() throws Exception {
        System.out.println("setOption()");
        Option option = Option.EnableEscape;
        BufferEncoder instance = new BufferEncoder();
        instance.setOption(option, false);
        assertEquals(false, instance.getOption(option));
        instance.setOption(option, true);
        assertEquals(true, instance.getOption(option));
    }

    /**
     * Test of getOption method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOption() throws Exception {
        System.out.println("getOption()");
        Option option = Option.EnableEscape;
        BufferEncoder instance = new BufferEncoder();
        assertEquals(true, instance.getOption(option));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_EntitySpecialChar() throws Exception {
        System.out.println("write(SpecialChar)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("(\n", instance.write(SpecialChar.StartObject));
        assertEquals("[\n", instance.write(SpecialChar.StartArray));
        assertEquals("]\n", instance.write(SpecialChar.EndArray));
        assertEquals(")\n", instance.write(SpecialChar.EndObject));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_EntitySpecialChar() throws Exception {
        System.out.println("write(String, SpecialChar)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("name = (\n", instance.write("name", SpecialChar.StartObject));
        assertEquals("name = [\n", instance.write("name", SpecialChar.StartArray));
        assertEquals("]\n", instance.write("name", SpecialChar.EndArray));
        assertEquals(")\n", instance.write("name", SpecialChar.EndObject));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String() throws Exception {
        System.out.println("write(String)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("data\n", instance.write("data"));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_long() throws Exception {
        System.out.println("write(long)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("0\n", instance.write(0L));
        assertEquals("12345678\n", instance.write(12345678L));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_double() throws Exception {
        System.out.println("write(double)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("0.0\n", instance.write(0.0));
        assertEquals("1234.5678\n", instance.write(1234.5678));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_long() throws Exception {
        System.out.println("write(String, long)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("ratio = 0\n", instance.write("ratio", 0L));
        assertEquals("ratio = 12345678\n", instance.write("ratio", 12345678L));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_double() throws Exception {
        System.out.println("write(String, double)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("ratio = 0.0\n", instance.write("ratio", 0.0));
        assertEquals("ratio = 1234.5678\n", instance.write("ratio", 1234.5678));
    }

    /**
     * Test of write method, of class BufferEncoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_String() throws Exception {
        System.out.println("write(String, String)");
        BufferEncoder instance = new BufferEncoder();
        assertEquals("name = data\n", instance.write("name", "data"));
    }

}
