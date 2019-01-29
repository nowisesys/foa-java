/*
 * Copyright (C) 2009-2018 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: http://it.bmc.uu.se/andlov/proj/libfoa/spec.php
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * MemoryStrategy.java
 *
 * Created: Mar 31, 2009, 5:05:49 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

/**
 * This class implements a memory allocation strategy to be used by the Decoder class. It gives the
 * user of the decoder class an option to define how aggressive memory gets allocated, as well as,
 * giving an opportunity to set the maximum number of bytes used for input buffer.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class MemoryStrategy {

    public static final int INIT_SIZE = 128;
    public static final int STEP_SIZE = 256;
    public static final int MAX_SIZE = 8 * 1024 * 1024;
    public static final int UNLIMITED = 0;

    private int initSize;
    private int stepSize;
    private int maxSize;

    /**
     * Creates an memory strategy using default values.
     */
    public MemoryStrategy() {
        this.initSize = INIT_SIZE;
        this.stepSize = STEP_SIZE;
        this.maxSize = MAX_SIZE;
    }

    /**
     * Creates an memory strategy with unlimited memory allocation limit.
     *
     * @param initSize The initial size.
     * @param stepSize The realloc size.
     */
    public MemoryStrategy(int initSize, int stepSize) {
        this.initSize = initSize;
        this.stepSize = stepSize;
        this.maxSize = UNLIMITED;
    }

    /**
     * Create an memory strategy.
     *
     * @param initSize The initial size.
     * @param stepSize The realloc size.
     * @param maxSize The maximum memory allocation limit.
     */
    public MemoryStrategy(int initSize, int stepSize, int maxSize) {
        this.initSize = initSize;
        this.stepSize = stepSize;
        this.maxSize = maxSize;
    }

    /**
     * @return The initial size.
     */
    public int getInitSize() {
        return initSize;
    }

    /**
     * @param initSize Sets the initial size.
     */
    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    /**
     * @return The step size for memory reallocation.
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * @param stepSize Sets the step size for memory reallocation.
     */
    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    /**
     * @return The maximum memory allocation limit.
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @param maxSize The maximum memory allocation limit.
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Sets the memory allocation limit to unlimited.
     */
    public void setUnlimited() {
        this.maxSize = UNLIMITED;
    }

}
