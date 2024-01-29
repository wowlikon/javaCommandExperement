package org.wowlikon.ioExperement;

import java.util.List;

public class PrintCmd implements Command{
    public String getCmd(){return "print";}
    public String getCmdHelp(){return "/print [...] - write arguments to console";}
    public String getHelpText(){return "Outputting text to console";}

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        System.out.println(String.join(" ", args));
    }
}