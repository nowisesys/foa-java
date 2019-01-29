/*
 * Copyright (C) 2009-2018 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * EncodeStringStream.java
 *
 * Created: Apr 1, 2009, 1:42:27 PM
 * Author:  Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 *
 * Description:
 * Demonstrate how to use a StringWriter as destination stream for encoded
 * data.
 *
 * Using a StringWriter should be fairly efficient alternative to wrapping
 * the stream in a BufferedWriter. In addition, we got full control of when
 * data gets flushed to the underlying stream (can be important in some
 * situations, i.e. when doing output buffring on web applications).
 */
package se.uu.bmc.it.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Encoder;
import java.io.StringWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on encoding to string stream.
 * @author Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 */
public class EncodeStringStream {

    /**
     * Run example code.
     */
    void run() throws IOException {
        StringWriter writer = new StringWriter();
        Encoder encoder = new Encoder(writer);

        //
        // Write a simple object to the string stream:
        //
        encoder.write("person", Entity.SpecialChar.StartObject);
        encoder.write("name", "adam");
        encoder.write("age", 24);
        encoder.write(Entity.SpecialChar.EndObject);

        //
        // Write string stream buffer to stdout:
        //
        StringWriter result = (StringWriter) encoder.getStream();
        System.out.print(result.toString());
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            EncodeStringStream encoder = new EncodeStringStream();
            encoder.run();
        } catch (IOException ex) {
            Logger.getLogger(EncodeStringStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
