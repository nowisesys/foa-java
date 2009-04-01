/*
 * Copyright (C) 2009 by Anders Lövgren and the Computing Department at BMC,
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

/**
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
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
        System.out.println("getInitSize");
        MemoryStrategy instance = new MemoryStrategy();
        int expResult = 0;
        int result = instance.getInitSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInitSize method, of class MemoryStrategy.
     */
    @Test
    public void testSetInitSize() {
        System.out.println("setInitSize");
        int initSize = 0;
        MemoryStrategy instance = new MemoryStrategy();
        instance.setInitSize(initSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStepSize method, of class MemoryStrategy.
     */
    @Test
    public void testGetStepSize() {
        System.out.println("getStepSize");
        MemoryStrategy instance = new MemoryStrategy();
        int expResult = 0;
        int result = instance.getStepSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStepSize method, of class MemoryStrategy.
     */
    @Test
    public void testSetStepSize() {
        System.out.println("setStepSize");
        int stepSize = 0;
        MemoryStrategy instance = new MemoryStrategy();
        instance.setStepSize(stepSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxSize method, of class MemoryStrategy.
     */
    @Test
    public void testGetMaxSize() {
        System.out.println("getMaxSize");
        MemoryStrategy instance = new MemoryStrategy();
        int expResult = 0;
        int result = instance.getMaxSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxSize method, of class MemoryStrategy.
     */
    @Test
    public void testSetMaxSize() {
        System.out.println("setMaxSize");
        int maxSize = 0;
        MemoryStrategy instance = new MemoryStrategy();
        instance.setMaxSize(maxSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnlimited method, of class MemoryStrategy.
     */
    @Test
    public void testSetUnlimited() {
        System.out.println("setUnlimited");
        MemoryStrategy instance = new MemoryStrategy();
        instance.setUnlimited();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}