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
 * Decoder.java
 *
 * Created: Mar 30, 2009, 1:50:40 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

import java.io.Reader;
import java.io.IOException;

/**
 * This class implements the decoder part of the FOA specification.
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class Decoder {

    /**
     * The various options for setOption and getOption.
     */
    public enum Option {

        EnableEscape
    }

    /**
     * Creates an decoder object using an default memory allocation strategy.
     */
    public Decoder() {
        this.map = new EntityMap();
        this.strategy = new MemoryStrategy();
        this.stream = null;
        reset(null, false);
    }

    /**
     *
     * Creates an decoder object for decoding an external buffer.
     *
     * @param buffer The external buffer.
     * @deprecated This method is provided for backward compatibility. Use constructor
     * Decoder(String) or Decoder(char []) instead.
     */
    public Decoder(byte[] buffer) {
        this(new String(buffer));
    }

    /**
     * Creates an decoder object for decoding an external buffer.
     *
     * @param buffer The external buffer.
     */
    public Decoder(char[] buffer) {
        this.map = new EntityMap();
        this.stream = null;
        this.strategy = null;
        reset(buffer, true);
    }

    /**
     * Creates an decoder object for decoding an string. This is an convenient constructor for
     * Decoder(char[]).
     *
     * @param str The string to decode.
     */
    public Decoder(String str) {
        this(str.toCharArray());
    }

    /**
     * Creates an decoder for decoding entities from the input stream using an default memory
     * allocation strategy.
     *
     * @param stream The input stream.
     */
    public Decoder(Reader stream) {
        this.map = new EntityMap();
        this.strategy = new MemoryStrategy();
        this.stream = stream;
        reset(new char[strategy.getInitSize()], false);
    }

    /**
     * Creates an decoder for decoding entities from the input stream.
     *
     * @param stream The input stream to decode.
     * @param strategy An memory allocation strategy to use.
     */
    public Decoder(Reader stream, MemoryStrategy strategy) {
        this.map = new EntityMap();
        this.strategy = strategy;
        this.stream = stream;
        reset(new char[strategy.getInitSize()], false);
    }

    /**
     * Set an extern buffer to read entities from.
     *
     * @param buffer The input buffer.
     */
    public void setBuffer(char[] buffer) {
        reset(buffer, true);
    }

    /**
     * Set an extern buffer to read entities from.
     *
     * @param buffer The input buffer.
     * @deprecated This method is provided for backward compatibility. Use setBuffer(String) or
     * setBuffer(char []) instead.
     */
    public void setBuffer(byte[] buffer) {
        if (buffer != null) {
            reset((new String(buffer)).toCharArray(), true);
        } else {
            reset(null, true);
        }
    }

    /**
     * Set an string to read entities from. This is a convenient method for setBuffer(byte[]).
     *
     * @param str The string to decode.
     */
    public void setBuffer(String str) {
        reset(str.toCharArray(), true);
    }

    /**
     * @return The current used buffer.
     */
    public char[] getBuffer() {
        return buffer;
    }

    /**
     * Set the stream to read entity data from. This function will create a default memory
     * allocation strategy if none exists yet.
     *
     * @param stream The input stream.
     */
    public void setStream(Reader stream) {
        this.stream = stream;
        if (this.strategy == null) {
            this.strategy = new MemoryStrategy();
        }
        reset(buffer, false);
    }

    /**
     * Set the stream to read entity data from using the strategy as memory allocation strategy.
     *
     * @param stream The input stream.
     * @param strategy The memory allocation strategy.
     */
    public void setStream(Reader stream, MemoryStrategy strategy) {
        this.stream = stream;
        this.strategy = strategy;
        reset(buffer, false);
    }

    /**
     * Get the current used input stream.
     *
     * @return The input stream.
     */
    public Reader getStream() {
        return stream;
    }

    /**
     * <p>
     * Set the memory allocation strategy to be used when reading data from the input stream. Note
     * that setting a new strategy might trigger the input buffer to be allocated or resized.</p>
     *
     * <p>
     * If strategy is null, then this function will simply tell the garbage collector to dispose the
     * current strategy.</p>
     *
     * @param strategy The memory allocation strategy.
     * @throws DecoderException if max size is smaller than current used size.
     */
    public void setStrategy(MemoryStrategy strategy) throws DecoderException {
        if (strategy != null) {
            if (strategy.getMaxSize() < ppos
                    && strategy.getMaxSize() != MemoryStrategy.UNLIMITED) {
                throw new DecoderException("Setting the memory allocation strategy would truncate data in the input buffer.");
            }
            if (buffer == null) {
                buffer = new char[strategy.getInitSize()];
            }
            if (strategy.getMaxSize() < this.buffer.length
                    && strategy.getMaxSize() != MemoryStrategy.UNLIMITED) {
                resize(strategy.getMaxSize());
            }
        }
        this.strategy = strategy;
    }

    /**
     * Get the currently used memory allocation strategy.
     *
     * @return The current memory allocation strategy.
     */
    public MemoryStrategy getStrategy() {
        return strategy;
    }

    /**
     * Sets the option to true or false.
     *
     * @param option The option to set.
     * @param val The option value.
     * @throws se.nowise.foa.DecoderException
     */
    public void setOption(Option option, boolean val) throws DecoderException {
        if (option == Option.EnableEscape) {
            escape = val;
        } else {
            throw new DecoderException("Invalid option " + option.name());
        }
    }

    /**
     * Gets the option value.
     *
     * @param option The option to get true or false of.
     * @return The option value.
     * @throws se.nowise.foa.DecoderException
     */
    public boolean getOption(Option option) throws DecoderException {
        if (option == Option.EnableEscape) {
            return escape;
        } else {
            throw new DecoderException("Invalid option " + option.name());
        }
    }

    /**
     * @return Current used buffer size.
     */
    public int getBufferSize() {
        return buffer != null ? buffer.length : 0;
    }

    /**
     * @return Current put position for input buffer.
     */
    public int getPosition() {
        return ppos;
    }

    /**
     * <p>
     * Read next entity from the current selected input stream or buffer. If the data source is an
     * stream, then the internal buffer is grown as large as required.</p>
     *
     * <p>
     * An DecoderException might be thrown if the maximum allowed buffer size is exceeded. If
     * maximum buffer size is unlimited, then an OutOfMemoryError might be thrown.</p>
     *
     * <p>
     * This function may block until at least one byte has been read if the data source is an
     * stream.</p>
     *
     * @return The entity or null.
     * @throws IOException
     * @throws DecoderException if maximum buffer size is reached.
     */
    public Entity read() throws IOException, DecoderException {
        if (extern) {
            if (buffer == null) {
                return null;
            }
            int start = ppos;
            for (int i = ppos; i < buffer.length; ++i) {
                if (buffer[i] == '\n') {
                    ppos = i;
                    break;
                }
            }
            if (ppos != start) {
                ++line;
                return decode(start, ppos);
            }
        } else {
            if (buffer == null) {
                buffer = new char[strategy.getInitSize()];
            }
            if (stream == null) {
                return null;
            }
            for (ppos = 0; ppos < buffer.length;) {
                int c = stream.read();
                if (c == -1) {
                    return null;     // End of stream.
                }
                if (c == '\n') {
                    ++line;
                    return decode(0, ppos);
                }
                buffer[ppos++] = (char) c;

                if (ppos == buffer.length) {
                    // Attempt to grow the input buffer:
                    int size = buffer.length + strategy.getStepSize();
                    if (strategy.getMaxSize() >= size
                            && strategy.getMaxSize() != MemoryStrategy.UNLIMITED) {
                        throw new DecoderException("Maximum buffer size exceeded.");
                    }
                    resize(size);
                }
            }
        }
        return null;
    }

    /**
     * Decode the entity in buffer between start and end.
     *
     * @param start The start position.
     * @param end The end position.
     * @return The decoded entity.
     */
    private Entity decode(int start, int end) {
        String str = new String(buffer, start, end - start);

        ppos += 1;   // Move past '\n'

        String name;
        String data;
        Entity.Type type = Entity.Type.DataName;

        int index = str.indexOf('=');
        if (index != -1) {
            name = str.substring(0, index).trim();
            data = str.substring(index + 1).trim();
        } else {
            name = null;
            data = str.trim();
        }

        if (data.length() == 1) {
            char c = data.charAt(0);
            if (c == Entity.SpecialChar.StartObject.getValue()) {
                type = Entity.Type.StartObject;
            } else if (c == Entity.SpecialChar.EndObject.getValue()) {
                type = Entity.Type.EndObject;
            } else if (c == Entity.SpecialChar.StartArray.getValue()) {
                type = Entity.Type.StartArray;
            } else if (c == Entity.SpecialChar.EndArray.getValue()) {
                type = Entity.Type.EndArray;
            }
        } else {
            data = getEscaped(data);
        }

        return new Entity(name, data, type, line);
    }

    /**
     * Escape all HTTP code escaped special chars. This function is an noop unless the escape option
     * is enabled.
     *
     * @param str The escaped string.
     * @return An unescaped string.
     */
    private String getEscaped(String str) {
        if (escape) {
            if (str.indexOf('%') == -1) {
                return str;     // No escaped data in the string.
            }
            StringBuilder builder = new StringBuilder(str);
            String replace = "([])=";
            for (int i = 0; i < replace.length(); ++i) {
                char key = replace.charAt(i);
                String enc = map.getEncoded(key);
                int pos = builder.indexOf(enc);
                while (pos != -1) {
                    builder.replace(pos, pos + 3, String.valueOf(key));
                    pos = builder.indexOf(enc, pos);
                }
            }
            return builder.toString();
        }
        return str;
    }

    /**
     * A helper function for managing the input buffer and internal counters.
     *
     * @param buffer The input buffer.
     * @param extern True if buffer is extern.
     */
    private void reset(char[] buffer, boolean extern) {
        this.buffer = buffer;
        this.extern = extern;
        this.line = this.ppos = 0;
    }

    /**
     * Resize the input buffer and copy up to size bytes from the old buffer to the new one. The
     * buffer data might be truncated if the new size is smaller than the old one.
     *
     * @param size The new buffer size.
     * @throws DecoderException if size is smaller than current used size.
     * @see getBufferSize
     * @see getPosition
     */
    private void resize(int size) throws DecoderException {
        if (size < ppos) {
            throw new DecoderException("Resize input buffer would truncate data.");
        }
        char[] buff = new char[size];
        for (int i = 0; i < ppos; ++i) {
            buff[i] = this.buffer[i];
        }
        this.buffer = buff;
    }
    private Reader stream;
    private MemoryStrategy strategy;
    private final EntityMap map;
    private char[] buffer;  // The data buffer.
    private int ppos;       // Put data position (when reading stream)
    private int line;       // The current line.
    private boolean extern; // True if buffer is external.
    private boolean escape = true; // Escape data or not.
}
