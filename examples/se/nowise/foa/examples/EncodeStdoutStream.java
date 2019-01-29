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
 * EncodeStdoutStream.java
 *
 * Created: Apr 1, 2009, 1:06:45 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Demonstrate how to make encoded data be written to stdout without
 * explicit calling Encoder::getBuffer() to get the encoded data.
 *
 * This example uses System.out as output stream, but in practice any buffered
 * or unbuffered Writer anchestor class could be used.
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Encoder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on encoding data to stdout stream.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class EncodeStdoutStream {

    void run() throws IOException {
        Encoder encoder = new Encoder(System.out);

        try {
            //
            // Write a simple person object:
            //
            encoder.write("person", Entity.SpecialChar.StartObject);
            encoder.write("name", "adam");
            encoder.write("age", 24);
            encoder.write(Entity.SpecialChar.EndObject);
        } finally {
            encoder.getStream().close();
        }

    }

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        try {
            EncodeStdoutStream encoder = new EncodeStdoutStream();
            encoder.run();
        } catch (IOException ex) {
            Logger.getLogger(EncodeStdoutStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
