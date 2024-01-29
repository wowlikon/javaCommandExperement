package org.wowlikon.ioExperement;

import java.util.List;

public class ExitCmd implements Command{
    public String getCmd(){return "exit";}
    public String getCmdHelp(){return "/exit - close this application";}
    public String getHelpText(){return "Stop this application.\n/exit";}

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        System.out.println("Exit!");
        run.value = false;
    }
}