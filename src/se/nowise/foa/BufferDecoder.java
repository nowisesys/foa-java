/*
 * Copyright (C) 2009-2019 Anders Lövgren (Nowise Systems/Uppsala University)
 * 
 * FOA Java Library (foa-java) - An Java (tm) library implementation of the FOA
 * specification: https://nowise.se/oss/libfoa/spec
 * 
 * This source is released under GPL3 (GNU General Public License) with the
 * GNU Classpath Exception. See the file COPYING and COPYING.CLASSPATH bundled
 * with the foa-java source or visit http://www.gnu.org
 */
/**
 * BufferDecoder.java
 *
 * Created: Apr 1, 2009, 3:10:00 AM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

import java.io.IOException;

/**
 * This is a convenience class that wraps up the byte array buffer functionality of the more generic
 * Decoder class.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class BufferDecoder {

    /**
     * Creates an decoder object for decoding the buffer.
     *
     * @param buffer The external buffer.
     * @deprecated This method is provided for backward compatibility. Use constructor
     * BufferDecoder(String) or BufferDecoder(char []) instead.
     */
    public BufferDecoder(byte[] buffer) {
        decoder = new Decoder(buffer);
    }

    /**
     * Creates an decoder object for decoding the buffer.
     *
     * @param buffer The external buffer.
     */
    public BufferDecoder(char[] buffer) {
        decoder = new Decoder(buffer);
    }

    /**
     * Creates an decoder object for decoding the string.
     *
     * @param str The string to decode.
     */
    public BufferDecoder(String str) {
        decoder = new Decoder(str);
    }

    /**
     * Set the char array buffer to read entities from.
     *
     * @param buffer The input buffer.
     */
    public void setBuffer(char[] buffer) {
        decoder.setBuffer(buffer);
    }

    /**
     * Set the byte array buffer to read entities from.
     *
     * @param buffer The input buffer.
     * @deprecated This method is provided for backward compatibility. Use setBuffer(String) or
     * setBuffer(char []) instead.
     */
    public void setBuffer(byte[] buffer) {
        decoder.setBuffer(buffer);
    }

    /**
     * Set the string to read entities from. This is a convenience method for setBuffer(byte[]).
     *
     * @param str The string to decode.
     */
    public void setBuffer(String str) {
        decoder.setBuffer(str);
    }

    /**
     * @return The current used buffer.
     */
    public char[] getBuffer() {
        return decoder.getBuffer();
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
     * @param option The option to get boolean value of.
     * @return The option value.
     * @throws se.nowise.foa.DecoderException
     */
    public boolean getOption(Decoder.Option option) throws DecoderException {
        return decoder.getOption(option);
    }

    /**
     * Read the next entity from the byte array.
     *
     * @return The next entity or null if no more entities exist in buffer.
     * @throws IOException
     * @throws se.nowise.foa.DecoderException
     * @see se.nowise.foa.Decoder#read() for details.
     */
    public Entity read() throws IOException, DecoderException {
        return decoder.read();
    }

    private final Decoder decoder;

}
