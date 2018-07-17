/*
 * Copyright (C) 2009-2018 by Anders Lövgren and the Computing Departmentity at BMC,
 * Uppsala University.
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * DecodeEscape.java
 *
 * Created: Apr 1, 2009, 3:07:19 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Tests decoding escaped input data from an memory buffer.
 */
package se.uu.bmc.it.foa.examples;

import se.uu.bmc.it.foa.*;
import java.io.IOException;

public class DecodeEscape {

    /**
     * Run example code.
     */
    void run() throws IOException, DecoderException {
        Decoder decoder = new Decoder();
        Entity entity;

        System.out.println("\n** escape " + decoder.getOption(Decoder.Option.EnableEscape) + ":");
        decoder.setBuffer("name=a%28b%5Bc%5Dd%29e%3D\n");
        if ((entity = decoder.read()) != null) {
            System.out.println("\nbuffer: '" + new String(decoder.getBuffer()).trim() + "'");
            System.out.println("  name: '" + entity.getName() + "'");
            System.out.println("  data: '" + entity.getData() + "'");
        }

        decoder.setBuffer("a%28b%5Bc%5Dd%29e%3D=name\n");
        if ((entity = decoder.read()) != null) {
            System.out.println("\nbuffer: '" + new String(decoder.getBuffer()).trim() + "'");
            System.out.println("  name: '" + entity.getName() + "'");
            System.out.println("  data: '" + entity.getData() + "'");
        }

        decoder.setBuffer("a%28b%5Bc%5Dd%29e%3D=a%28b%5Bc%5Dd%29e%3D\n");
        if ((entity = decoder.read()) != null) {
            System.out.println("\nbuffer: '" + new String(decoder.getBuffer()).trim() + "'");
            System.out.println("  name: '" + entity.getName() + "'");
            System.out.println("  data: '" + entity.getData() + "'");
        }

        decoder.setBuffer("name=name=a%28b%5Bc%5Dd%29e%3D\n");
        if ((entity = decoder.read()) != null) {
            System.out.println("\nbuffer: '" + new String(decoder.getBuffer()).trim() + "'");
            System.out.println("  name: '" + entity.getName() + "'");
            System.out.println("  data: '" + entity.getData() + "'");
        }

        decoder.setOption(Decoder.Option.EnableEscape, false);
        System.out.println("\n** escape " + decoder.getOption(Decoder.Option.EnableEscape) + ":");
        decoder.setBuffer("name=a%28b%5Bc%5Dd%29e%3D\n");
        if ((entity = decoder.read()) != null) {
            System.out.println("\nbuffer: '" + new String(decoder.getBuffer()).trim() + "'");
            System.out.println("  name: '" + entity.getName() + "'");
            System.out.println("  data: '" + entity.getData() + "'");
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        DecodeEscape decoder = new DecodeEscape();
        try {
            decoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
