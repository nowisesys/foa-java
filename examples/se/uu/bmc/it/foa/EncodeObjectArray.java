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

/*
 * EncodeObjectArray.java
 *
 * Created: Apr 1, 2009, 1:00:24 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Show how to encode an array of objects (persons)
 */

package se.uu.bmc.it.foa;

import java.io.OutputStreamWriter;
import java.io.IOException;

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

    private Person[] persons;   // The array of persons.

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
        OutputStreamWriter stream = new OutputStreamWriter(System.out);
        Encoder encoder = new Encoder(stream);

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
            stream.close();
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        EncodeObjectArray encoder = new EncodeObjectArray();
        try {
            encoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }

}
