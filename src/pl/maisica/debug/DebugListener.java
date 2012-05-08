/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maisica.debug;

import java.util.EventListener;

/**
 *
 * @author kbec
 */
public interface DebugListener extends EventListener {
    
    public void debugMessageAvailable(DebugEvent evt);
    
}
