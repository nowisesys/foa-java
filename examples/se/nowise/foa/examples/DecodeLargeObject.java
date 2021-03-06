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
 * DecodeLargeObject.java
 *
 * Created: Apr 1, 2009, 4:55:37 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 *
 * Description:
 * Test parsing a large object. Running this example might generate an
 * OutOfMemoryError exception.
 */
package se.nowise.foa.examples;

import se.nowise.foa.Entity;
import se.nowise.foa.Decoder;
import se.nowise.foa.DecoderException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example on decode large number of objects.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class DecodeLargeObject {

    private final int num;

    DecodeLargeObject(int num) {
        this.num = num;
    }

    /**
     * Run example code.
     */
    void run() throws IOException, DecoderException {
        String obj = "(\nname=adam\nage=35\n)\n";
        StringBuilder str = new StringBuilder();
        int count = 0;

        System.out.print("Initilizing string of " + num + " objects... ");
        System.out.flush();
        str.append("[\n");
        for (int i = 0; i < num; ++i) {
            str.append(obj);
        }
        str.append("]\n");
        System.out.println("done");

        System.out.print("Creating the object decoder... ");
        System.out.flush();
        Decoder decoder = new Decoder(str.toString());
        System.out.println("done");

        System.out.print("Decoding objects... ");
        System.out.flush();
        Entity entity;
        while ((entity = decoder.read()) != null) {
            ++count;
        }
        System.out.println("done");
        System.out.println("Decoded " + count + " entities total.");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int num = 3000000;

            if (args.length != 0) {
                num = Integer.parseInt(args[0]);
            }

            DecodeLargeObject decoder = new DecodeLargeObject(num);
            decoder.run();
        } catch (IOException | DecoderException ex) {
            Logger.getLogger(DecodeLargeObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
