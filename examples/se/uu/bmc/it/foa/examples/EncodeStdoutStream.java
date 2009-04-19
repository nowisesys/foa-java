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
 * EncodeStdoutStream.java
 *
 * Created: Apr 1, 2009, 1:06:45 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Demonstrate how to make encoded data be written to stdout without
 * explicit calling Encoder::getBuffer() to get the encoded data.
 *
 * This example uses System.out as output stream, but in practice any buffered
 * or unbuffered Writer anchestor class could be used.
 */
package se.uu.bmc.it.foa.examples;

import se.uu.bmc.it.foa.*;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class EncodeStdoutStream {

    void run() throws IOException {
        OutputStreamWriter stream = new OutputStreamWriter(System.out);
        Encoder encoder = new Encoder(stream);

        try {
            //
            // Write a simple person object:
            //
            encoder.write("person", Entity.SpecialChar.StartObject);
            encoder.write("name", "adam");
            encoder.write("age", 24);
            encoder.write(Entity.SpecialChar.EndObject);
        } finally {
            stream.close();
        }

    }

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        EncodeStdoutStream encoder = new EncodeStdoutStream();
        try {
            encoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
