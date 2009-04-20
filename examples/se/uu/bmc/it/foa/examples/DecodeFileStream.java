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
 * DecodeFileStream.java
 *
 * Created: Apr 1, 2009, 4:31:55 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Demonstrate how to decode an file input stream.
 */
package se.uu.bmc.it.foa.examples;

import se.uu.bmc.it.foa.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DecodeFileStream {

    private String file;

    /**
     * Setup example parameters.
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
                    System.out.println("[" +
                            entity.getLine() + "]: " +
                            entity.getName() + " = " +
                            entity.getData() + " (" +
                            entity.getType() + ")");
                } else {
                    System.out.println("[" +
                            entity.getLine() + "]: " +
                            entity.getData() + " (" +
                            entity.getType() + ")");
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
        String file = "data/person.txt";
        if (args.length != 0) {
            file = args[0];
        }
        DecodeFileStream decoder = new DecodeFileStream(file);
        try {
            decoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
