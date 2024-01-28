package org.wowlikon.ioExperement;

import java.util.List;

public class PrintCmd implements Command{
    public final String cmd = "print";
    public final String cmdHelp = "/print [...] - write arguments to console";
    public final String helpText = "Outputting text to console";

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        System.out.println(String.join(" ", args));
    }
}