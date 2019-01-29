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
/**
 * EncodeObjectArray.java
 *
 * Created: Apr 1, 2009, 1:00:24 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Show how to encode an array of objects (persons)
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Encoder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on encoding array of objects.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class EncodeObjectArray {

    /**
     * This class represent a simplified view of an person.
     */
    private class Person {

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        String name;
        int age;
    }

    private final Person[] persons;   // The array of persons.

    /**
     * Setup example parameters.
     */
    EncodeObjectArray() {
        persons = new Person[3];
        persons[0] = new Person("adam", 35);
        persons[1] = new Person("bertil", 41);
        persons[2] = new Person("ceasar", 71);
    }

    /**
     * Run example code.
     */
    void run() throws IOException {
        Encoder encoder = new Encoder(System.out);

        try {
            encoder.write(Entity.SpecialChar.StartArray);
            for (Person person : persons) {
                encoder.write(Entity.SpecialChar.StartObject);
                encoder.write("Name", person.name);
                encoder.write("Age", person.age);
                encoder.write(Entity.SpecialChar.EndObject);
            }
            encoder.write(Entity.SpecialChar.EndArray);
        } finally {
            encoder.getStream().close();
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            EncodeObjectArray encoder = new EncodeObjectArray();
            encoder.run();
        } catch (IOException ex) {
            Logger.getLogger(EncodeObjectArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
