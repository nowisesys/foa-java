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
 * Entity.java
 *
 * Created: Mar 30, 2009, 5:24:50 PM
 * Author:  Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
package se.nowise.foa;

/**
 *
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class Entity {

    /**
     * @return True if name is set.
     */
    public boolean hasName() {
        return name != null;
    }

    /**
     * @return The entity name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The entity data.
     */
    public String getData() {
        return data;
    }

    /**
     * @param data The data to set.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return The entity type.
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return The line number.
     */
    public int getLine() {
        return line;
    }

    /**
     * @param line The line number to set.
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Get the character mapped by the type argument.
     *
     * @param type The entity type.
     * @return char
     */
    public static char getTypeChar(Type type) {
        if (null == type) {
            return 0;
        }

        switch (type) {
            case StartObject:
                return SpecialChar.StartObject.getValue();
            case StartArray:
                return SpecialChar.StartArray.getValue();
            case EndObject:
                return SpecialChar.EndObject.getValue();
            case EndArray:
                return SpecialChar.EndArray.getValue();
            default:
                return 0;
        }
    }

    /**
     * Create an Entity object of data name type with data and name null.
     */
    public Entity() {
        this(null, null, Type.DataName, 0);
    }

    /**
     * The standard constructor for named data.
     *
     * @param name The entity name.
     * @param data The entity data.
     * @param line The line number.
     */
    public Entity(String name, String data, int line) {
        this(name, data, Type.DataName, line);
    }

    /**
     * The standard constructor for unnamed data.
     *
     * @param data The entity data.
     * @param line The line number.
     */
    public Entity(String data, int line) {
        this(null, data, Type.DataName, line);
    }

    /**
     * The standard constructor for special chars entity.
     *
     * @param type The entity type.
     * @param line The line number.
     */
    public Entity(Type type, int line) {
        this(null, null, type, line);
        this.data = "" + getTypeChar(type);
    }

    /**
     * The generic constructor of entity objects.
     *
     * @param name The entity name.
     * @param data The entity data.
     * @param type The entity type.
     * @param line The line number.
     */
    Entity(String name, String data, Type type, int line) {
        this.name = name;
        this.data = data;
        this.type = type;
        this.line = line;
    }

    /**
     * These are the different type of entities.
     */
    public enum Type {
        StartObject,
        EndObject,
        StartArray,
        EndArray,
        DataName,
    }

    /**
     * The mapping between entity types and their special char representation.
     */
    public enum SpecialChar {
        StartObject('('),
        EndObject(')'),
        StartArray('['),
        EndArray(']');

        public char getValue() {
            return value;
        }

        SpecialChar(char value) {
            this.value = value;
        }

        private final char value;
    }

    private String name;
    private String data;
    private Type type;
    private int line;
}
