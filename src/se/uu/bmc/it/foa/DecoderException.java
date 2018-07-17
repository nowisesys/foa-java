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
 * DecoderException.java
 *
 * Created: Mar 30, 2009, 1:51:15 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 */
package se.uu.bmc.it.foa;

/**
 * The exception thrown by various methods in class Decoder.
 *
 * @author Anders Lövgren (QNET/BMC CompDept)
 */
public class DecoderException extends Exception {

    /**
     * Creates a new instance of <code>DecoderException</code> without detail message.
     */
    public DecoderException() {
    }

    /**
     * Constructs an instance of <code>DecoderException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DecoderException(String msg) {
        super(msg);
    }
}
