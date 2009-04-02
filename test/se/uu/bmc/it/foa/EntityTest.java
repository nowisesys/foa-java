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
import se.uu.bmc.it.foa.Entity.Type;

/**
 * Unit test (junit) of class Entity.
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
 */
public class EntityTest {

    public EntityTest() {
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
     * Test of hasName method, of class Entity.
     */
    @Test
    public void testHasName() {
        System.out.println("hasName()");
        Entity instance = new Entity();
        instance.setName("Name");
        assertEquals(true, instance.hasName());
        instance.setName(null);
        assertEquals(false, instance.hasName());
    }

    /**
     * Test of getName method, of class Entity.
     */
    @Test
    public void testGetName() {
        System.out.println("getName()");
        Entity instance = new Entity();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Entity.
     */
    @Test
    public void testSetName() {
        System.out.println("setName(String)");
        String name = "Name";
        Entity instance = new Entity();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getData method, of class Entity.
     */
    @Test
    public void testGetData() {
        System.out.println("getData()");
        Entity instance = new Entity();
        String expResult = null;
        String result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of setData method, of class Entity.
     */
    @Test
    public void testSetData() {
        System.out.println("setData(String)");
        String data = "Data";
        Entity instance = new Entity();
        instance.setData(data);
        assertEquals(data, instance.getData());
    }

    /**
     * Test of getType method, of class Entity.
     */
    @Test
    public void testGetType() {
        System.out.println("getType()");
        Entity instance = new Entity();
        Type expResult = Type.DataName;
        Type result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Entity.
     */
    @Test
    public void testSetType() {
        System.out.println("setType(Type)");
        Type type = Type.StartObject;
        Entity instance = new Entity();
        instance.setType(type);
        assertEquals(Type.StartObject, instance.getType());
    }

    /**
     * Test of getLine method, of class Entity.
     */
    @Test
    public void testGetLine() {
        System.out.println("getLine()");
        Entity instance = new Entity();
        int expResult = 0;
        int result = instance.getLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLine method, of class Entity.
     */
    @Test
    public void testSetLine() {
        System.out.println("setLine(int)");
        int line = 10;
        Entity instance = new Entity();
        instance.setLine(line);
        assertEquals(line, instance.getLine());
    }

}
