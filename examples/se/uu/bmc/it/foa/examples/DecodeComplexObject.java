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
 * DecodeComplexObject.java
 *
 * Created: Apr 20, 2009, 5:27:03 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 */
package se.uu.bmc.it.foa.examples;

/**
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
 */
public class DecodeComplexObject {

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        String file = "data/complex.txt";
        if (args.length != 0) {
            file = args[0];
        }
        DecodeFileStream decoder = new DecodeFileStream(file);
        try {
            decoder.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
