package org.mazzarese.turnoutControl;

import com.fazecast.jSerialComm.*;

import java.nio.charset.StandardCharsets;

/**
 *  A class to represent turnouts in the Java-Arduino Dispatching Environment. JADE turnouts currently operate over USB.
 *
 *  Written by Max Mazzarese.
 */
public class Turnout {
    private String state;
    private String identifier;
    private SerialPort port;
    private int type;
    public static final int THROW_UP_RIGHT = 1;
    public static final int THROW_DOWN_RIGHT = 2;
    public static final int THROW_UP_LEFT = 3;
    public static final int THROW_DOWN_LEFT = 4;

    /**
     * The turnout constructor.
     *
     * @param identifier an identifier(in the form of a number) for the turnout. No two turnouts on the same SerialPort should use the same identifier.
     * @param port the SerialPort which should be used to communicate with this turnout.
     */
    public Turnout(String identifier, int type, SerialPort port) {
        this.identifier = identifier;
        this.port = port;
        this.type = type;
    }

    /**
     * Flips the turnout in the specified direction.
     *
     * @param instruction the direction (1 or 0) to flip the turnout.
     */
    public void flipDirection(String instruction) {
        String toSend = ("c" + this.identifier + ":" + instruction);
        byte[] idBytes = toSend.getBytes(StandardCharsets.US_ASCII);
        port.openPort();
        port.writeBytes(idBytes, idBytes.length);
        port.closePort();
        state = instruction;
    }

    /**
     * A string to represent the turnout in the form "identifier:state"
     *
     * @return a string representing the turnout.
     */
    public String toString() {
        return identifier + ":" + state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public SerialPort getPort() {
        return port;
    }
    public void setPort(SerialPort port) {
        this.port = port;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String depiction() {
        if(type == THROW_UP_RIGHT) {
            if("0".equals(state)) {
                return ("_ _ _");
            }
            else if("1".equals(state)) {
                return ("_ / _");
            }
            else {
                return ("x");
            }
        }

        if(type == THROW_DOWN_RIGHT) {
            if("0".equals(state)) {
                return ("_ _");
            }
            else if("1".equals(state)) {
                return ("\\ _");
            }
            else {
                return ("x");
            }
        }

        if(type == THROW_UP_LEFT) {
            if("0".equals(state)) {
                return ("_ _ _");
            }
            else if("1".equals(state)) {
                return ("_ \\ _");
            }
            else {
                return ("x");
            }
        }

        if(type == THROW_DOWN_LEFT) {
            if("0".equals(state)) {
                return ("_ _");
            }
            else if("1".equals(state)) {
                return ("_ /");
            }
            else {
                return ("x");
            }
        }


        else {
            return "TYPE UNDEFINED";
        }
    }

}
