/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maisica.debug;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import javax.swing.event.EventListenerList;

/**
 *
 * @author kbec
 */
public class DebugServer implements HttpHandler {
    
    private HttpServer server;

    public DebugServer(int listenPort) throws IOException {
        server = HttpServer.create(new InetSocketAddress(listenPort), 0);
        server.createContext("/", this);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Listening on port "+Integer.toString(listenPort));
    }

    public DebugServer() throws IOException {
        this(9001);
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String method = he.getRequestMethod();
        if (method.equalsIgnoreCase("POST")) {
            Reader reader = new InputStreamReader(he.getRequestBody());
            DebugMessage msg = new Gson().fromJson(reader, DebugMessage.class);
            if (msg != null) fireDebugEvent(new DebugEvent(this, msg));
        }
        he.close();
    }
    
    private EventListenerList listenerList = new EventListenerList();
    
    public void addDebugListener(DebugListener listener) {
        listenerList.add(DebugListener.class, listener);
    }
    
    public void removeDebugListener(DebugListener listener) {
        listenerList.remove(DebugListener.class, listener);
    }
    
    private void fireDebugEvent(DebugEvent evt) {
        for (DebugListener l : listenerList.getListeners(DebugListener.class)) {
            l.debugMessageAvailable(evt);
        }
    }
    
}
