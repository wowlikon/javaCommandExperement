package org.wowlikon.ioExperement;

import java.util.List;

public interface Command {
    String getCmd();
    String getCmdHelp();
    String getHelpText();
    default String validate(String command, String[] arguments){return null;}
    void execute(String command, String[] arguments, boolObj debug, boolObj running, List<Command> cmds);
}