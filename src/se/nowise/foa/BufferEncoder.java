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
 * BufferEncoder.java
 *
 * Created: Apr 1, 2009, 2:53:26 AM
 * Author:  Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 */
package se.nowise.foa;

import java.io.IOException;

/**
 * This is a convenience class that wraps up the string buffer functionality of the more generic
 * Decoder class. It also redefines how the write() member function behaves by always returning the
 * string buffer containing the last encoded FOA entity.
 *
 * @author Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 */
public class BufferEncoder {

    /**
     * Creates an object for writing entities to an string buffer.
     */
    public BufferEncoder() {
        encoder = new Encoder();
    }

    /**
     * Return the string representation of the last encoded entity.
     *
     * @return The encoded entity.
     */
    public String getBuffer() {
        return encoder.getBuffer();
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
     * @return String
     * @see se.uu.bmc.it.foa.Entity.SpecialChar
     * @throws java.io.IOException
     */
    public String write(Entity.SpecialChar spec) throws IOException {
        encoder.write(spec);
        return encoder.getBuffer();
    }

    /**
     * Write an named special char entity to the stream.
     *
     * @param name The name of the special char.
     * @param spec The special char.
     * @return String
     * @see se.uu.bmc.it.foa.Entity.SpecialChar
     * @throws java.io.IOException
     */
    public String write(String name, Entity.SpecialChar spec) throws IOException {
        encoder.write(name, spec);
        return encoder.getBuffer();
    }

    /**
     * Write anonymous data entity to the stream.
     *
     * @param data The data.
     * @return String
     * @throws java.io.IOException
     */
    public String write(String data) throws IOException {
        encoder.write(data);
        return encoder.getBuffer();
    }

    /**
     * Specialization for writing anonymous integer values.
     *
     * @param data The integer value.
     * @return String
     * @throws java.io.IOException
     */
    public String write(long data) throws IOException {
        encoder.write(data);
        return encoder.getBuffer();
    }

    /**
     * Specialization for writing anonymous float point value.
     *
     * @param data The float point number (double).
     * @return String
     * @throws java.io.IOException
     */
    public String write(double data) throws IOException {
        encoder.write(data);
        return encoder.getBuffer();
    }

    /**
     * Specialization for writing named integer value.
     *
     * @param name The data name.
     * @param data The integer value.
     * @return String
     * @throws java.io.IOException
     */
    public String write(String name, long data) throws IOException {
        encoder.write(name, data);
        return encoder.getBuffer();
    }

    /**
     * Specialization for writing named float point value.
     *
     * @param name The data name.
     * @param data The float point number (double).
     * @return String
     * @throws java.io.IOException
     */
    public String write(String name, double data) throws IOException {
        encoder.write(name, data);
        return encoder.getBuffer();
    }

    /**
     * Write named data entity to the stream.
     *
     * @param name The data name.
     * @param data The data.
     * @return String
     * @throws java.io.IOException
     */
    public String write(String name, String data) throws IOException {
        encoder.write(name, data);
        return encoder.getBuffer();
    }

    private final Encoder encoder;

}
