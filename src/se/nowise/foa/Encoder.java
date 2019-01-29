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
 * Encoder.java
 *
 * Created: Mar 30, 2009, 1:50:13 PM
 * Author:  Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 */
package se.nowise.foa;

import java.io.Writer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * This class implements the encoder part of the FOA specification.
 *
 * @author Anders Lövgren (Nowise Systems/Uppsala University (BMC-IT))
 */
public class Encoder {

    /**
     * Creates an encoder object without associated stream.
     */
    public Encoder() {
        writer = null;
    }

    /**
     * Create an encoder object with an associated stream that the encoded string (entity) gets
     * written to.
     *
     * @param stream The output character stream.
     */
    public Encoder(Writer stream) {
        this.writer = stream;
    }

    /**
     * Create an encoder object with an associated stream that the encoded string (entity) gets
     * written to.
     *
     * @param stream The output stream.
     */
    public Encoder(OutputStream stream) {
        if (stream != null) {
            this.writer = new OutputStreamWriter(stream);
        }
    }

    /**
     * Sets the associated write stream.
     *
     * @param stream The output character stream.
     * @see se.uu.bmc.it.foa.Encoder#Encoder(java.io.Writer)
     */
    public void setStream(Writer stream) {
        this.writer = stream;
    }

    /**
     * Sets the associated write stream.
     *
     * @param stream The output character stream.
     * @see se.uu.bmc.it.foa.Encoder#Encoder(java.io.Writer)
     */
    public void setStream(OutputStream stream) {
        if (stream != null) {
            this.writer = new OutputStreamWriter(stream);
        }
    }

    /**
     * Get the stream associated with this decoder object.
     *
     * @return The write stream.
     */
    public Writer getStream() {
        return writer;
    }

    /**
     * Return the string representation of the last encoded entity.
     *
     * @return The encoded entity.
     */
    public String getBuffer() {
        return buffer;
    }

    /**
     * Write an anonymous special char entity to the buffer.
     *
     * @param spec The special char.
     * @see se.uu.bmc.it.foa.Entity.SpecialChar
     * @throws java.io.IOException
     */
    public void write(Entity.SpecialChar spec) throws IOException {
        buffer = spec.getValue() + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Write an named special char entity to the buffer.
     *
     * @param name The name of the special char.
     * @param spec The special char.
     * @see se.uu.bmc.it.foa.Entity.SpecialChar
     * @throws java.io.IOException
     */
    public void write(String name, Entity.SpecialChar spec) throws IOException {
        if (spec == Entity.SpecialChar.StartObject
                || spec == Entity.SpecialChar.StartArray) {
            buffer = name + " = " + spec.getValue() + "\n";
        } else {
            buffer = spec.getValue() + "\n";
        }
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Write anonymous data entity to the buffer.
     *
     * @param data The data.
     * @throws java.io.IOException
     */
    public void write(String data) throws IOException {
        buffer = getEscaped(data) + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Specialization for writing anonymous integer values.
     *
     * @param data The integer value.
     * @throws java.io.IOException
     */
    public void write(long data) throws IOException {
        buffer = data + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Specialization for writing anonymous float point value.
     *
     * @param data The float point number (double)
     * @throws java.io.IOException
     */
    public void write(double data) throws IOException {
        buffer = data + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Specialization for writing named integer value.
     *
     * @param name The data name.
     * @param data The integer value.
     * @throws java.io.IOException
     */
    public void write(String name, long data) throws IOException {
        buffer = name + " = " + data + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Specialization for writing named float point value.
     *
     * @param name The data name.
     * @param data The float point number (double)
     * @throws java.io.IOException
     */
    public void write(String name, double data) throws IOException {
        buffer = name + " = " + data + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Write named data entity to the buffer.
     *
     * @param name The data name.
     * @param data The data.
     * @throws java.io.IOException
     */
    public void write(String name, String data) throws IOException {
        buffer = name + " = " + getEscaped(data) + "\n";
        if (writer != null) {
            writer.write(buffer);
        }
    }

    /**
     * Get string with all special chars replaced by their HTTP encoded equivalents.
     *
     * @param str The string to escape.
     * @return The escaped string.
     */
    private String getEscaped(String str) {
        if (escape) {
            String replace = "([])=";
            if (str.indexOf(replace.charAt(0)) != -1
                    || str.indexOf(replace.charAt(1)) != -1
                    || str.indexOf(replace.charAt(2)) != -1
                    || str.indexOf(replace.charAt(3)) != -1) {
                StringBuilder res = new StringBuilder(str.length());
                for (int i = 0; i < str.length(); ++i) {
                    int index = replace.indexOf(str.charAt(i));
                    if (index != -1) {
                        String code = String.format("%%%X", new Integer(replace.charAt(index)));
                        res.append(code);
                    } else {
                        res.append(str.charAt(i));
                    }
                }
                return res.toString();
            }
        }
        return str;
    }

    /**
     * Sets the option to true or false.
     *
     * @param option The option to set.
     * @param val The option value.
     * @throws se.nowise.foa.EncoderException
     */
    public void setOption(Option option, boolean val) throws EncoderException {
        if (option == Option.EnableEscape) {
            escape = val;
        } else {
            throw new EncoderException("Invalid option " + option.name());
        }
    }

    /**
     * Gets the option value.
     *
     * @param option The option to get true or false of.
     * @return The option value.
     * @throws se.nowise.foa.EncoderException
     */
    public boolean getOption(Option option) throws EncoderException {
        if (option == Option.EnableEscape) {
            return escape;
        } else {
            throw new EncoderException("Invalid option " + option.name());
        }
    }

    /**
     * The various options for setOption and getOption.
     */
    public enum Option {

        EnableEscape
    }
    private Writer writer = null;
    private String buffer;
    private boolean escape = true;
}
