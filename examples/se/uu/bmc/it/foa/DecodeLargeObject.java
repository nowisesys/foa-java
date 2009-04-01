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
 * DecodeLargeObject.java
 *
 * Created: Apr 1, 2009, 4:55:37 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Test parsing a large object. Running this example might generate an
 * OutOfMemoryError exception.
 */
package se.uu.bmc.it.foa;

import java.io.IOException;

public class DecodeLargeObject {

    private int num;

    DecodeLargeObject(int num) {
        this.num = num;
    }

    /**
     * Run example code.
     */
    void run() throws IOException, DecoderException {
        String obj = "(\nname=adam\nage=35\n)\n";
        StringBuilder str = new StringBuilder();

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
        }
        System.out.println("done");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num = 2000000;
        if (args.length != 0) {
            num = Integer.parseInt(args[0]);
        }
        DecodeLargeObject decoder = new DecodeLargeObject(num);
        try {
            decoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
