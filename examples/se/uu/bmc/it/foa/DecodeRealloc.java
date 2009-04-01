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
 * DecodeRealloc.java
 *
 * Created: Apr 1, 2009, 10:03:19 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Test that realloc works when decoding by setting a silly
 * memory strategy forcing realloc the data buffer for each
 * decoded character.
 *
 */
package se.uu.bmc.it.foa;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DecodeRealloc {

    private String file;

    /**
     * Setup example parameters.
     */
    DecodeRealloc(String file) {
        this.file = file;
    }

    /**
     * Run example code.
     */
    void run() throws FileNotFoundException, IOException, DecoderException {
        MemoryStrategy strategy = new MemoryStrategy(1, 1);
        FileReader stream = new FileReader(file);
        Decoder decoder = new Decoder(stream, strategy);
        Entity entity;
        while ((entity = decoder.read()) != null) {
            if (entity.hasName()) {
                System.out.println("[" +
                        entity.getLine() + "]: " +
                        entity.getName() + " = " +
                        entity.getData());
            } else {
                System.out.println("[" +
                        entity.getLine() + "]: " +
                        entity.getData());
            }

        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String file = "person.txt";
        if (args.length != 0) {
            file = args[0];
        }

        DecodeRealloc decoder = new DecodeRealloc(file);
        try {
            decoder.run();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
