/*
 * Copyright (C) 2009-2019 Anders Lövgren (Nowise Systems/Uppsala University)
 *
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 *
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * EncoderException.java
 *
 * Created: Mar 30, 2009, 10:35:02 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

/**
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class EncoderException extends Exception {

    /**
     * Creates a new instance of <code>EncoderException</code> without detail message.
     */
    public EncoderException() {
    }

    /**
     * Constructs an instance of <code>EncoderException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public EncoderException(String msg) {
        super(msg);
    }
}
