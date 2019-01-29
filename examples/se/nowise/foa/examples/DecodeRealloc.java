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
 * DecodeRealloc.java
 *
 * Created: Apr 1, 2009, 10:03:19 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Test that realloc works when decoding by setting a silly
 * memory strategy forcing realloc the data buffer for each
 * decoded character.
 *
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Decoder;
import se.nowise.foa.MemoryStrategy;
import se.nowise.foa.DecoderException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on decode using small memory buffer (realloc in each iteration).
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class DecodeRealloc {

    private final String file;

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
                System.out.println("["
                        + entity.getLine() + "]: "
                        + entity.getName() + " = "
                        + entity.getData());
            } else {
                System.out.println("["
                        + entity.getLine() + "]: "
                        + entity.getData());
            }

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

            DecodeRealloc decoder = new DecodeRealloc(file);
            decoder.run();
        } catch (IOException | DecoderException ex) {
            Logger.getLogger(DecodeRealloc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
