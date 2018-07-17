/*
 * Copyright (C) 2009-2018 by Anders Lövgren and the Computing Department at BMC,
 * Uppsala University.
 *
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 *
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
package se.uu.bmc.it.foa;

import java.io.OutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.uu.bmc.it.foa.Encoder.Option;
import se.uu.bmc.it.foa.Entity.SpecialChar;

/**
 * Unit test for class Encoder.
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
 */
public class EncoderTest {

    public EncoderTest() {
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
     * Test of setStream method, of class Encoder.
     */
    @Test
    public void testSetStream() {
        System.out.println("setStream(OutputStream)");
        OutputStream stream = null;
        Encoder instance = new Encoder();
        instance.setStream(stream);
    }

    /**
     * Test of getBuffer method, of class Encoder.
     */
    @Test
    public void testGetBuffer() {
        System.out.println("getBuffer()");
        Encoder instance = new Encoder();
        String expResult = null;
        String result = instance.getBuffer();
        assertEquals(expResult, result);
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_EntitySpecialChar() throws Exception {
        System.out.println("write(SpecialChar)");
        Encoder instance = new Encoder();
        instance.write(SpecialChar.StartObject);
        assertEquals("(\n", instance.getBuffer());
        instance.write(SpecialChar.EndObject);
        assertEquals(")\n", instance.getBuffer());
        instance.write(SpecialChar.StartArray);
        assertEquals("[\n", instance.getBuffer());
        instance.write(SpecialChar.EndArray);
        assertEquals("]\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_EntitySpecialChar() throws Exception {
        System.out.println("write(String, SpecialChar)");
        Encoder instance = new Encoder();
        instance.write("name", SpecialChar.StartObject);
        assertEquals("name = (\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String() throws Exception {
        System.out.println("write(String)");
        Encoder instance = new Encoder();
        Option option = Encoder.Option.EnableEscape;

        String str1 = "some text";
        String str2 = "(([[]]))==()[]=(]([=";
        String data = "(a[b]c)d=e";
        String denc = "%28a%5Bb%5Dc%29d%3De";

        System.out.println("-- Testing with escape enabled");
        instance.setOption(option, true);
        instance.write(data);
        assertEquals(denc + "\n", instance.getBuffer());
        instance.write(denc);
        assertEquals(denc + "\n", instance.getBuffer());
        instance.write(str1);
        assertEquals(str1 + "\n", instance.getBuffer());

        System.out.println("-- Testing with escape disabled");
        instance.setOption(option, false);
        instance.write(data);
        assertEquals(data + "\n", instance.getBuffer());
        instance.write(denc);
        assertEquals(denc + "\n", instance.getBuffer());
        instance.write(str2);
        assertEquals(str2 + "\n", instance.getBuffer());
        instance.write(str2);
        assertEquals(str2 + "\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_long() throws Exception {
        System.out.println("write(long)");
        Encoder instance = new Encoder();
        instance.write(123);
        assertEquals("123\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_double() throws Exception {
        System.out.println("write(double)");
        Encoder instance = new Encoder();
        instance.write(123.456);
        assertEquals("123.456\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_String() throws Exception {
        System.out.println("write(String, String)");
        Encoder instance = new Encoder();
        Option option = Encoder.Option.EnableEscape;

        String name = "name";
        String data = "(a[b]c)d=e";
        String denc = "%28a%5Bb%5Dc%29d%3De";

        System.out.println("-- Testing with escape enabled");
        instance.setOption(option, true);
        instance.write(name, data);
        assertEquals(name + " = " + denc + "\n", instance.getBuffer());
        instance.write(name, denc);
        assertEquals(name + " = " + denc + "\n", instance.getBuffer());
        instance.write(name, name);
        assertEquals(name + " = " + name + "\n", instance.getBuffer());
        instance.write(data, data);
        assertEquals(data + " = " + denc + "\n", instance.getBuffer());
        instance.write(data, denc);
        assertEquals(data + " = " + denc + "\n", instance.getBuffer());
        instance.write(data, name);
        assertEquals(data + " = " + name + "\n", instance.getBuffer());
        instance.write(denc, data);
        assertEquals(denc + " = " + denc + "\n", instance.getBuffer());
        instance.write(denc, denc);
        assertEquals(denc + " = " + denc + "\n", instance.getBuffer());
        instance.write(denc, name);
        assertEquals(denc + " = " + name + "\n", instance.getBuffer());

        System.out.println("-- Testing with escape disabled");
        instance.setOption(option, false);
        instance.write(name, data);
        assertEquals(name + " = " + data + "\n", instance.getBuffer());
        instance.write(name, denc);
        assertEquals(name + " = " + denc + "\n", instance.getBuffer());
        instance.write(name, name);
        assertEquals(name + " = " + name + "\n", instance.getBuffer());
        instance.write(data, data);
        assertEquals(data + " = " + data + "\n", instance.getBuffer());
        instance.write(data, denc);
        assertEquals(data + " = " + denc + "\n", instance.getBuffer());
        instance.write(data, name);
        assertEquals(data + " = " + name + "\n", instance.getBuffer());
        instance.write(denc, data);
        assertEquals(denc + " = " + data + "\n", instance.getBuffer());
        instance.write(denc, denc);
        assertEquals(denc + " = " + denc + "\n", instance.getBuffer());
        instance.write(denc, name);
        assertEquals(denc + " = " + name + "\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_long() throws Exception {
        System.out.println("write(String, long)");
        Encoder instance = new Encoder();
        instance.write("name", 123);
        assertEquals("name = 123\n", instance.getBuffer());
    }

    /**
     * Test of write method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite_String_double() throws Exception {
        System.out.println("write(String, double)");
        Encoder instance = new Encoder();
        instance.write("name", 123.456);
        assertEquals("name = 123.456\n", instance.getBuffer());
    }

    /**
     * Test of setOption method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetOption() throws Exception {
        System.out.println("setOption(Option, boolean)");
        Encoder instance = new Encoder();
        Option option = Option.EnableEscape;

        System.out.println("-- Test disable escape");
        boolean val = false;
        instance.setOption(option, val);
        assertEquals(val, instance.getOption(option));

        System.out.println("-- Test enable escape");
        val = true;
        instance.setOption(option, val);
        assertEquals(val, instance.getOption(option));

        // Test encoding is in respective write() test.
    }

    /**
     * Test of getOption method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOption() throws Exception {
        System.out.println("getOption(Option)");
        Encoder instance = new Encoder();
        Option option = Option.EnableEscape;
        boolean expResult = true;
        boolean result = instance.getOption(option);
        assertEquals(expResult, result);
    }

}
