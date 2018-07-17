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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.uu.bmc.it.foa.Decoder.Option;

/**
 * Unit test for class BufferDecoder.
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
 */
public class BufferDecoderTest {

    private String string = "Hello world!\n";
    private char[] buffer;
    private BufferDecoder decoder;

    public BufferDecoderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        buffer = string.toCharArray();
        decoder = new BufferDecoder(buffer);
    }

    @After
    public void tearDown() {
        buffer = null;
        decoder = null;
    }

    /**
     * Test of setBuffer method, of class BufferDecoder.
     */
    @Test
    public void testSetBuffer_byteArr() {
        System.out.println("setBuffer(byte[])");
        decoder.setBuffer(buffer);
        assertArrayEquals(buffer, decoder.getBuffer());
        assertSame(buffer, decoder.getBuffer());
    }

    /**
     * Test of setBuffer method, of class BufferDecoder.
     */
    @Test
    public void testSetBuffer_String() {
        System.out.println("setBuffer(String)");
        decoder.setBuffer(string);
        assertArrayEquals(buffer, decoder.getBuffer());
    }

    /**
     * Test of getBuffer method, of class BufferDecoder.
     */
    @Test
    public void testGetBuffer() {
        System.out.println("getBuffer()");
        assertSame(buffer, decoder.getBuffer());
    }

    /**
     * Test of setOption method, of class BufferDecoder.
     */
    @Test
    public void testSetOption() throws Exception {
        System.out.println("setOption(Option, boolean)");
        Option option = Option.EnableEscape;
        decoder.setOption(option, false);
        assertEquals(false, decoder.getOption(option));
        decoder.setOption(option, true);
        assertEquals(true, decoder.getOption(option));
    }

    /**
     * Test of getOption method, of class BufferDecoder.
     */
    @Test
    public void testGetOption() throws Exception {
        System.out.println("getOption(Option)");
        Option option = Option.EnableEscape;
        assertEquals(true, decoder.getOption(option));
    }

    /**
     * Test of read method, of class BufferDecoder.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read()");
        Entity entity;

        buffer = null;
        decoder.setBuffer(buffer);
        entity = decoder.read();
        assertEquals(null, entity);

        decoder.setBuffer("name = data\n");
        entity = decoder.read();
        assertEquals("name", entity.getName());
        assertEquals("data", entity.getData());
        assertEquals(Entity.Type.DataName, entity.getType());

        decoder.setBuffer("name = data\nname = data\n");
        entity = decoder.read();
        entity = decoder.read();
        assertEquals(2, entity.getLine());
        assertEquals(true, entity.hasName());
    }

}
