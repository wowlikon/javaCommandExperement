package org.wowlikon.ioExperement;

import java.util.List;

public class ExitCmd implements Command{
    public final String cmd = "exit";
    public final String cmdHelp = "/exit - close this application";
    public final String helpText = "Stop this application.\n/exit";

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        System.out.println("Exit!");
        run.value = false;
    }
}