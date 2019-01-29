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
 * StreamEncoder.java
 *
 * Created: Apr 1, 2009, 2:38:24 AM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * This is a convenience class that wraps up the stream functionality of the more generic Decoder
 * class.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class StreamEncoder {

    /**
     * Construct an StreamEncoder object for encoding to the stream.
     *
     * @param stream The destination stream.
     */
    public StreamEncoder(OutputStream stream) {
        encoder = new Encoder(new OutputStreamWriter(stream));
    }

    /**
     * Sets an new output stream for encoded entities.
     *
     * @param stream The output stream.
     * @see se.uu.bmc.it.foa.StreamEncoder#StreamEncoder(OutputStream)
     */
    public void setStream(OutputStream stream) {
        encoder.setStream(new OutputStreamWriter(stream));
    }

    /**
     * Get the stream associated with this decoder object.
     *
     * @return The write stream.
     */
    public Writer getStream() {
        return encoder.getStream();
    }

    /**
     * Sets the option to true or false.
     *
     * @param option The option to set.
     * @param val The option value.
     * @throws se.nowise.foa.EncoderException
     */
    public void setOption(Encoder.Option option, boolean val) throws EncoderException {
        encoder.setOption(option, val);
    }

    /**
     * Gets the option value.
     *
     * @param option The option to get true or false of.
     * @return The option value.
     * @throws se.nowise.foa.EncoderException
     */
    public boolean getOption(Encoder.Option option) throws EncoderException {
        return encoder.getOption(option);
    }

    /**
     * Write an anonymous special char entity to the stream.
     *
     * @param spec The special char.
     * @see se.uu.bmc.it.foa.Entity.SpecialChar
     * @throws java.io.IOException
     */
    public void write(Entity.SpecialChar spec) throws IOException {
        encoder.write(spec);
    }

    /**
     * Write an named special char entity to the stream.
     *
     * @param name The name of the special char.
     * @param spec The special char.
     * @see se.uu.bmc.it.foa.Entity.SpecialChar
     * @throws java.io.IOException
     */
    public void write(String name, Entity.SpecialChar spec) throws IOException {
        encoder.write(name, spec);
    }

    /**
     * Write anonymous data entity to the stream.
     *
     * @param data The data.
     * @throws java.io.IOException
     */
    public void write(String data) throws IOException {
        encoder.write(data);
    }

    /**
     * Specialization for writing anonymous integer values.
     *
     * @param data The integer value.
     * @throws java.io.IOException
     */
    public void write(long data) throws IOException {
        encoder.write(data);
    }

    /**
     * Specialization for writing anonymous float point value.
     *
     * @param data The float point number (double)
     * @throws java.io.IOException
     */
    public void write(double data) throws IOException {
        encoder.write(data);
    }

    /**
     * Specialization for writing named integer value.
     *
     * @param name The data name.
     * @param data The integer value.
     * @throws java.io.IOException
     */
    public void write(String name, long data) throws IOException {
        encoder.write(name, data);
    }

    /**
     * Specialization for writing named float point value.
     *
     * @param name The data name.
     * @param data The float point number (double)
     * @throws java.io.IOException
     */
    public void write(String name, double data) throws IOException {
        encoder.write(name, data);
    }

    /**
     * Write named data entity to the stream.
     *
     * @param name The data name.
     * @param data The data.
     * @throws java.io.IOException
     */
    public void write(String name, String data) throws IOException {
        encoder.write(name, data);
    }

    private final Encoder encoder;
}
