/*
 * Copyright (C) 2009-2018 by Anders Lövgren and the Computing Department at BMC,
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
 * DecodeComplexObject.java
 *
 * Created: Apr 20, 2009, 5:27:03 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 */
package se.uu.bmc.it.foa.examples;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.uu.bmc.it.foa.DecoderException;

/**
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
 */
public class DecodeComplexObject {

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        try {
            String file = "data/complex.txt";

            if (args.length != 0) {
                file = args[0];
            }

            DecodeFileStream decoder = new DecodeFileStream(file);
            decoder.run();
        } catch (IOException | DecoderException ex) {
            Logger.getLogger(DecodeComplexObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
