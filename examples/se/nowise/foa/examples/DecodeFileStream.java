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
 * DecodeFileStream.java
 *
 * Created: Apr 1, 2009, 4:31:55 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Demonstrate how to decode an file input stream.
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Decoder;
import se.nowise.foa.DecoderException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on decode file stream.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class DecodeFileStream {

    private final String file;

    /**
     * Setup example parameters.
     *
     * @param file The file to read.
     */
    DecodeFileStream(String file) {
        this.file = file;
    }

    /**
     * Run example.
     */
    void run() throws IOException, DecoderException {
        FileReader stream = new FileReader(file);
        Decoder decoder = new Decoder(new BufferedReader(stream));

        try {
            Entity entity;
            while ((entity = decoder.read()) != null) {
                if (entity.hasName()) {
                    System.out.println("["
                            + entity.getLine() + "]: "
                            + entity.getName() + " = "
                            + entity.getData() + " ("
                            + entity.getType() + ")");
                } else {
                    System.out.println("["
                            + entity.getLine() + "]: "
                            + entity.getData() + " ("
                            + entity.getType() + ")");
                }
            }

        } finally {
            stream.close();
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            String file = "data/person.txt";

            if (args.length != 0) {
                file = args[0];
            }

            DecodeFileStream decoder = new DecodeFileStream(file);
            decoder.run();
        } catch (IOException | DecoderException ex) {
            Logger.getLogger(DecodeFileStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
