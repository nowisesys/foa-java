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
package se.uu.bmc.it.foa;

import se.nowise.foa.MemoryStrategy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for class MemoryStrategy.
 *
 * @author Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 */
public class MemoryStrategyTest {

    public MemoryStrategyTest() {
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
     * Test of getInitSize method, of class MemoryStrategy.
     */
    @Test
    public void testGetInitSize() {
        System.out.println("getInitSize()");
        System.out.println("-- Testing default init size");
        MemoryStrategy instance = new MemoryStrategy();
        assertEquals(MemoryStrategy.INIT_SIZE, instance.getInitSize());
        System.out.println("-- Testing custom init size");
        instance = new MemoryStrategy(1024, 2048);
        assertEquals(1024, instance.getInitSize());
    }

    /**
     * Test of setInitSize method, of class MemoryStrategy.
     */
    @Test
    public void testSetInitSize() {
        System.out.println("setInitSize()");
        MemoryStrategy instance = new MemoryStrategy();
        instance.setInitSize(1024);
        assertEquals(1024, instance.getInitSize());
        instance.setInitSize(0);
        assertEquals(0, instance.getInitSize());
    }

    /**
     * Test of getStepSize method, of class MemoryStrategy.
     */
    @Test
    public void testGetStepSize() {
        System.out.println("getStepSize()");
        System.out.println("-- Testing default step size");
        MemoryStrategy instance = new MemoryStrategy();
        assertEquals(MemoryStrategy.STEP_SIZE, instance.getStepSize());
        System.out.println("-- Testing custom step size");
        instance = new MemoryStrategy(1024, 2048);
        assertEquals(2048, instance.getStepSize());
    }

    /**
     * Test of setStepSize method, of class MemoryStrategy.
     */
    @Test
    public void testSetStepSize() {
        System.out.println("setStepSize()");
        MemoryStrategy instance = new MemoryStrategy();
        instance.setStepSize(1024);
        assertEquals(1024, instance.getStepSize());
        instance.setStepSize(0);
        assertEquals(0, instance.getStepSize());
    }

    /**
     * Test of getMaxSize method, of class MemoryStrategy.
     */
    @Test
    public void testGetMaxSize() {
        System.out.println("getMaxSize()");
        System.out.println("-- Testing default max size");
        MemoryStrategy instance = new MemoryStrategy();
        assertEquals(MemoryStrategy.MAX_SIZE, instance.getMaxSize());
        System.out.println("-- Testing custom max size");
        instance = new MemoryStrategy(1024, 2048, 4096);
        assertEquals(4096, instance.getMaxSize());
    }

    /**
     * Test of setMaxSize method, of class MemoryStrategy.
     */
    @Test
    public void testSetMaxSize() {
        System.out.println("setMaxSize()");
        MemoryStrategy instance = new MemoryStrategy();
        instance.setMaxSize(1024);
        assertEquals(1024, instance.getMaxSize());
        instance.setMaxSize(0);
        assertEquals(0, instance.getMaxSize());
    }

    /**
     * Test of setUnlimited method, of class MemoryStrategy.
     */
    @Test
    public void testSetUnlimited() {
        System.out.println("setUnlimited");
        MemoryStrategy instance = new MemoryStrategy();
        instance.setUnlimited();
        assertEquals(MemoryStrategy.UNLIMITED, instance.getMaxSize());
    }
}
