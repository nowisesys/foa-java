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
 * EncodeEscape.java
 *
 * Created: Mar 31, 2009, 12:20:02 AM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Test escaping of special chars in output.
 */

package se.uu.bmc.it.foa.examples;

import se.uu.bmc.it.foa.*;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class EncodeEscape {
    private String data;

    /**
     * Setup example parameters.
     */
    EncodeEscape(String data) {
        this.data = data;
    }

    /**
     * Run example code.
     */
    void run() throws IOException, EncoderException {
        OutputStreamWriter stream = new OutputStreamWriter(System.out);
        Encoder encoder = new Encoder(stream);

        try {
            // Print data encoded:
            encoder.setOption(Encoder.Option.EnableEscape, true);
            encoder.write(data);

            // Don't print data encoded:
            encoder.setOption(Encoder.Option.EnableEscape, false);
            encoder.write(data);
        } finally {
            stream.close();    // Ensure stream is always flushed & closed.
        }
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        EncodeEscape encoder = new EncodeEscape("a(b[c]d)e=");
        try {
            encoder.run();
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}
