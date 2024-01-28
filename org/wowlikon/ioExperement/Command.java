package org.wowlikon.ioExperement;

import java.util.List;

public interface Command {
    String cmd = "";
    String cmdHelp = "";
    String helpText = "";
    void execute(String command, String[] arguments, boolObj debug, boolObj running, List<Command> cmds);
    default String validate(String command, String[] arguments){
        return null;
    }
}

