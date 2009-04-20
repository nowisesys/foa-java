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
 * EncodeFileStream.java
 *
 * Created: Mar 31, 2009, 1:54:16 AM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Demonstrate how to write encoded data to a file stream.
 */
package se.uu.bmc.it.foa.examples;

import se.uu.bmc.it.foa.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EncodeFileStream {

    private String file;

    /**
     * Setup example parameters.
     */
    EncodeFileStream(String file) {
        this.file = file;
    }

    /**
     * Run example code.
     */
    public void run() throws IOException {
        FileWriter stream = new FileWriter(file);
        Encoder encoder = new Encoder(new BufferedWriter(stream));

        try {
            encoder.write("person", Entity.SpecialChar.StartObject);
            encoder.write("name", "adam");
            encoder.write("age", 24);
            encoder.write(Entity.SpecialChar.EndObject);
            encoder.getStream().flush();

            System.out.println("Wrote data to " + file);
        } finally {
            stream.close();
        }
    }

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        String file = "data/person.txt";
        if (args.length != 0) {
            file = args[0];
        }

        EncodeFileStream encoder = new EncodeFileStream(file);
        try {
            encoder.run();
        } catch (FileNotFoundException e) {
            System.err.println("Failed open " + file + ", " + e);
        } catch (IOException e) {
            System.err.println("Failed write data to " + file);
        }
    }
}
