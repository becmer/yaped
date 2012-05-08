/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maisica.debug;

import java.util.EventObject;

/**
 *
 * @author kbec
 */
public class DebugEvent extends EventObject {
    
    private DebugMessage message;

    public DebugEvent(Object source, DebugMessage message) {
        super(source);
        this.message = message;
    }

    public DebugMessage getMessage() {
        return message;
    }
    
}
