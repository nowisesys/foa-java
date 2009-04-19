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
 * SetMemoryStrategy.java
 *
 * Created: Apr 1, 2009, 2:00:40 PM
 * Author:  Anders Lövgren (QNET/BMC CompDept)
 *
 * Description:
 * Demonstration on various aspects of the MemoryStrategy class.
 */
package se.uu.bmc.it.foa.examples;

import se.uu.bmc.it.foa.*;

public class SetMemoryStrategy {

    /**
     * Show some information about this memory strategy.
     */
    void show(String label, MemoryStrategy strategy) {
        if (strategy != null) {
            System.out.println(label +
                    ": init=" + strategy.getInitSize() +
                    ", step=" + strategy.getStepSize() +
                    ", max=" + strategy.getMaxSize());
        } else {
            System.out.println(label + ": no memory strategy is set");
        }
    }

    /**
     * Run example code.
     */
    void run() throws DecoderException {
        Decoder decoder = new Decoder();

        MemoryStrategy strategy1 = new MemoryStrategy();
        MemoryStrategy strategy2 = new MemoryStrategy(512, 1024, 8096);

        show("uninitilized", decoder.getStrategy());

        decoder.setStrategy(strategy1);
        show("default", decoder.getStrategy());

        decoder.setStrategy(strategy2);
        show("custom", decoder.getStrategy());

        strategy2.setUnlimited();
        decoder.setStrategy(strategy2);
        show("unlimited", decoder.getStrategy());

        decoder.setStrategy(null);
        show("cleared", decoder.getStrategy());
    }

    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SetMemoryStrategy strategy = new SetMemoryStrategy();
        try {
            strategy.run();
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}