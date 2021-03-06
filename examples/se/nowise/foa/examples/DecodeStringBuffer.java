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
 * DecodeStringBuffer.java
 *
 * Created: Apr 1, 2009, 12:39:36 AM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Demonstrate decoding an string buffer.
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Decoder;
import se.nowise.foa.DecoderException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on decoding string buffer.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class DecodeStringBuffer {

    private final String buffer;

    /**
     * Setup example parameters.
     */
    DecodeStringBuffer(String buffer) {
        this.buffer = buffer;
    }

    /**
     * Run example code.
     *
     * @throws java.io.IOException
     * @throws DecoderException
     */
    public void run() throws IOException, DecoderException {
        Decoder decoder = new Decoder(buffer);
        Entity entity;

        System.out.println("Buffer:\n---------\n" + buffer + "\n");
        while ((entity = decoder.read()) != null) {
            if (entity.hasName()) {
                System.out.println(entity.getName() + " = " + entity.getData() + "\t(" + entity.getType().toString() + ")");
            } else {
                System.out.println(entity.getData() + "\t(" + entity.getType().toString() + ")");
            }
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            String buffer = "persons = [\n(\nname=adam\nage = 37\n)\n(\nname=bertil\nage=45\n)\n]\n";
            DecodeStringBuffer decoder = new DecodeStringBuffer(buffer);
            decoder.run();
        } catch (IOException | DecoderException ex) {
            Logger.getLogger(DecodeStringBuffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
