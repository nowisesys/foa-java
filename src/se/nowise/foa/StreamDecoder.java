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
 * StreamDecoder.java
 *
 * Created: Apr 1, 2009, 3:10:30 AM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

import java.io.Reader;
import java.io.IOException;

/**
 * This is a convenience class that wraps up the stream functionality of the more generic Decoder
 * class.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class StreamDecoder {

    /**
     * Creates an decoder for decoding entities from the input stream using an default memory
     * allocation strategy.
     *
     * @param stream The input stream.
     */
    public StreamDecoder(Reader stream) {
        decoder = new Decoder(stream);
    }

    /**
     * Creates an decoder for decoding entities from the input stream.
     *
     * @param stream The input stream to decode.
     * @param strategy An memory allocation strategy to use.
     */
    public StreamDecoder(Reader stream, MemoryStrategy strategy) {
        decoder = new Decoder(stream, strategy);
    }

    /**
     * Set the stream to read entity data from. This function will create a default memory
     * allocation strategy if none exists yet.
     *
     * @param stream The input stream.
     */
    public void setStream(Reader stream) {
        decoder.setStream(stream);
    }

    /**
     * Set the stream to read entity data from using the strategy as memory allocation strategy.
     *
     * @param stream The input stream.
     * @param strategy The memory allocation strategy.
     */
    public void setStream(Reader stream, MemoryStrategy strategy) {
        decoder.setStream(stream, strategy);
    }

    /**
     * Get the current used input stream.
     *
     * @return The input stream.
     */
    public Reader getStream() {
        return decoder.getStream();
    }

    /**
     * Set the memory allocation strategy to be used when reading data from the input stream.
     *
     * @param strategy The memory allocation strategy.
     * @throws se.nowise.foa.DecoderException if max size is smaller than current used size.
     * @see se.uu.bmc.it.foa.Decoder#setStrategy(se.uu.bmc.it.foa.MemoryStrategy) for details.
     */
    public void setStrategy(MemoryStrategy strategy) throws DecoderException {
        decoder.setStrategy(strategy);
    }

    /**
     * Get the currently used memory allocation strategy.
     *
     * @return The current memory allocation strategy.
     */
    public MemoryStrategy getStrategy() {
        return decoder.getStrategy();
    }

    /**
     * Sets the option to true or false.
     *
     * @param option The option to set.
     * @param val The option value.
     * @throws se.nowise.foa.DecoderException
     */
    public void setOption(Decoder.Option option, boolean val) throws DecoderException {
        decoder.setOption(option, val);
    }

    /**
     * Gets the option value.
     *
     * @param option The option to get true or false of.
     * @return The option value.
     * @throws se.nowise.foa.DecoderException
     */
    public boolean getOption(Decoder.Option option) throws DecoderException {
        return decoder.getOption(option);
    }

    /**
     * Read the next entity from the input stream.
     *
     * @return The next entity or null on end of stream.
     * @throws IOException
     * @throws se.nowise.foa.DecoderException
     * @see se.uu.bmc.it.foa.Decoder#read() for details.
     */
    public Entity read() throws IOException, DecoderException {
        return decoder.read();
    }

    private final Decoder decoder;

}
