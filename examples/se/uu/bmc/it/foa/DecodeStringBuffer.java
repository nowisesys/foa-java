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
 * DecodeStringBuffer.java
 *
 * Created: Apr 1, 2009, 12:39:36 AM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Demonstrate decoding an string buffer.
 */

package se.uu.bmc.it.foa;

import java.io.IOException;

public class DecodeStringBuffer {

    private String buffer;

    /**
     * Setup example parameters.
     */
    DecodeStringBuffer(String buffer) {
        this.buffer = buffer;
    }

    /**
     * Run example code.
     */
    public void run() throws IOException, DecoderException {
        Decoder decoder = new Decoder(buffer);
        Entity entity;

        System.out.println("Buffer:\n---------\n" + buffer + "\n");
        while ((entity = decoder.read()) != null) {
            if (entity.hasName()) {
                System.out.println(entity.getName() + " = " + entity.getData() + "\t(type=" + entity.getType().toString());
            } else {
                System.out.println(entity.getData() + "\t(type=" + entity.getType().toString());
            }
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String buffer = "persons = [\n(\nname=adam\nage = 37\n)\n(\nname=bertil\nage=45\n)\n]\n";
        DecodeStringBuffer decoder = new DecodeStringBuffer(buffer);

        try {
            decoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
