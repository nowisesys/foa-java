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

import se.nowise.foa.MemoryStrategy;
import se.nowise.foa.Entity;
import se.nowise.foa.Decoder;
import java.io.Reader;
import java.io.InputStreamReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nowise.foa.Decoder.Option;

/**
 * Unit test for class Decoder.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class DecoderTest {

    public DecoderTest() {
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
     * Test of setBuffer method, of class Decoder.
     */
    @Test
    public void testSetBuffer() {
        testSetCharBuffer();
        testSetByteBuffer();
        testSetStringBuffer();
    }

    private void testSetCharBuffer() {
        System.out.println("setBuffer(char[])");
        Decoder instance = new Decoder();
        // Test with no buffer:
        char[] buffer = null;
        instance.setBuffer(buffer);
        assertArrayEquals(null, instance.getBuffer());
        // Test with buffer:
        buffer = new char[3];
        buffer[0] = 'F';
        buffer[1] = 'O';
        buffer[2] = 'A';
        instance.setBuffer(buffer);
        // Should be the same object:
        assertArrayEquals(buffer, instance.getBuffer());
        boolean match = true;
        char[] result = instance.getBuffer();
        for (int i = 0; i < buffer.length; ++i) {
            if (result[i] != buffer[i]) {
                match = false;
                break;
            }
        }
        // Should have identical content:
        assertEquals(true, match);
    }

    private void testSetByteBuffer() {
        System.out.println("setBuffer(byte[])");
        Decoder instance = new Decoder();
        // Test with no buffer:
        byte[] buffer = null;
        instance.setBuffer(buffer);
        assertArrayEquals(null, instance.getBuffer());
        // Test with buffer:
        buffer = new byte[3];
        buffer[0] = 70;
        buffer[1] = 79;
        buffer[2] = 65;
        instance.setBuffer(buffer);
        boolean match = true;
        char[] result = instance.getBuffer();
        for (int i = 0; i < buffer.length; ++i) {
            if (result[i] != buffer[i]) {
                match = false;
                break;
            }
        }
        // Should have identical content:
        assertEquals(true, match);
    }

    private void testSetStringBuffer() {
        System.out.println("setBuffer(String)");
        Decoder instance = new Decoder();
        String str = "Test string";
        char[] buffer = str.toCharArray();
        instance.setBuffer(buffer);
        assertArrayEquals(buffer, instance.getBuffer());
        String ref = new String(instance.getBuffer());
        boolean match = str.compareTo(ref) == 0;
        assertEquals(true, match);
    }

    /**
     * Test of setStream method, of class Decoder.
     */
    @Test
    public void testSetStream() {
        System.out.println("setStream(Reader)");
        Decoder instance = new Decoder();
        Reader stream = null;
        instance.setStream(stream);
        assertEquals(null, instance.getStream());
        stream = new InputStreamReader(System.in);
        instance.setStream(stream);
        assertEquals(true, stream.equals(instance.getStream()));
    }

    /**
     * Test of getStream method, of class Decoder.
     */
    @Test
    public void testGetStream() {
        System.out.println("getStream()");
        Decoder instance = new Decoder();
        Reader expResult = null;
        Reader result = instance.getStream();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStrategy method, of class Decoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetStrategy() throws Exception {
        System.out.println("setStrategy(MemoryStrategy)");
        MemoryStrategy strategy = null;
        Decoder instance = new Decoder();
        instance.setStrategy(strategy);
        assertEquals(null, instance.getStrategy());
        strategy = new MemoryStrategy();
        instance.setStrategy(strategy);
        assertEquals(strategy, instance.getStrategy());
    }

    /**
     * Test of getStrategy method, of class Decoder.
     */
    @Test
    public void testGetStrategy() {
        System.out.println("getStrategy()");
        String str = "";
        Decoder instance = new Decoder(str);
        MemoryStrategy expResult = null;
        MemoryStrategy result = instance.getStrategy();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOption method, of class Decoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetOption() throws Exception {
        System.out.println("setOption(Option, boolean)");
        Decoder instance = new Decoder();
        Option option = Decoder.Option.EnableEscape;

        System.out.println("-- Test disable escape");
        instance.setOption(option, false);
        assertEquals(false, instance.getOption(option));

        System.out.println("-- Test enable escape");
        instance.setOption(option, true);
        assertEquals(true, instance.getOption(option));

        // Test decoding:
        Entity entity;
        String name = "name";
        String data = "a%28b%5Bc%5Dd%29e%3D";
        String ddec = "a(b[c]d)e=";

        System.out.println("-- Test decode (with escape enabled)");
        instance.setOption(option, true);
        instance.setBuffer(name + "=" + data + "\n");
        entity = instance.read();
        assertEquals(name, entity.getName());
        assertEquals(ddec, entity.getData());

        instance.setBuffer(name + "=" + name + "\n");
        entity = instance.read();
        assertEquals(name, entity.getName());
        assertEquals(name, entity.getData());

        instance.setBuffer(data + "=" + name + "\n");
        entity = instance.read();
        assertEquals(data, entity.getName());
        assertEquals(name, entity.getData());

        instance.setBuffer(data + "=" + data + "\n");
        entity = instance.read();
        assertEquals(data, entity.getName());
        assertEquals(ddec, entity.getData());

        System.out.println("-- Test decode (with escape disabled)");
        instance.setOption(option, false);
        instance.setBuffer(name + "=" + data + "\n");
        entity = instance.read();
        assertEquals(name, entity.getName());
        assertEquals(data, entity.getData());

        instance.setBuffer(name + "=" + name + "\n");
        entity = instance.read();
        assertEquals(name, entity.getName());
        assertEquals(name, entity.getData());

        instance.setBuffer(data + "=" + name + "\n");
        entity = instance.read();
        assertEquals(data, entity.getName());
        assertEquals(name, entity.getData());

        instance.setBuffer(data + "=" + data + "\n");
        entity = instance.read();
        assertEquals(data, entity.getName());
        assertEquals(data, entity.getData());
    }

    /**
     * Test of getOption method, of class Decoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOption() throws Exception {
        System.out.println("getOption(Option)");
        Decoder instance = new Decoder();
        Option option = Decoder.Option.EnableEscape;
        // Test default escape mode:
        boolean result = instance.getOption(option);
        assertEquals(true, result);
        // Disable escape:
        instance.setOption(option, false);
        result = instance.getOption(option);
        assertEquals(false, result);
        // Enable escape:
        instance.setOption(option, true);
        result = instance.getOption(option);
        assertEquals(true, result);
    }

    /**
     * Test of getBufferSize method, of class Decoder.
     */
    @Test
    public void testGetBufferSize() {
        System.out.println("getBufferSize()");
        Decoder instance = new Decoder();
        int expResult = 0;
        int result = instance.getBufferSize();
        assertEquals(expResult, result);
        instance.setBuffer(new byte[3]);
        expResult = 3;
        result = instance.getBufferSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Decoder.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition()");
        Decoder instance = new Decoder();
        int expResult = 0;
        int result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class Decoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read()");
        Decoder instance = new Decoder();
        Entity expResult = null;
        Entity result = instance.read();
        assertEquals(expResult, result);
    }
}
