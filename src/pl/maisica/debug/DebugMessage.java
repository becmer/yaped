/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.maisica.debug;

import java.util.List;

/**
 *
 * @author kbec
 */
public class DebugMessage {

    private String message;
    private DebugMessage previous;
    private String code;
    private String file;
    private int line;
    private List<StackItem> trace;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DebugMessage getPrevious() {
        return previous;
    }

    public void setPrevious(DebugMessage previous) {
        this.previous = previous;
    }

    public List<StackItem> getStackTrace() {
        return trace;
    }

    public void setStackTrace(List<StackItem> stackTrace) {
        this.trace = stackTrace;
    }

    public static class StackItem {

        private String file;
        private int line;
        private String function;
        private String className;
        private String type;
        private List<String> args;

        public List<String> getArgs() {
            return args;
        }

        public void setArgs(List<String> args) {
            this.args = args;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    
}
