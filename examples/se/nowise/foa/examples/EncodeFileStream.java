/*
 * Copyright (C) 2009-2019 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: https://nowise.se/oss/libfoa/spec
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * EncodeFileStream.java
 *
 * Created: Mar 31, 2009, 1:54:16 AM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Demonstrate how to write encoded data to a file stream.
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Encoder;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on encoding file stream.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class EncodeFileStream {

    private final String file;

    /**
     * Setup example parameters.
     */
    EncodeFileStream(String file) {
        this.file = file;
    }

    /**
     * Run example code.
     *
     * @throws java.io.IOException
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

        try {
            EncodeFileStream encoder = new EncodeFileStream(file);
            encoder.run();
        } catch (FileNotFoundException e) {
            String message = "Failed open " + file + ", " + e;
            Logger.getLogger(EncodeEscape.class.getName()).log(Level.SEVERE, null, message);
        } catch (IOException e) {
            String message = "Failed write data to " + file;
            Logger.getLogger(EncodeEscape.class.getName()).log(Level.SEVERE, null, message);
        }
    }
}
