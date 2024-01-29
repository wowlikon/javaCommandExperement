package org.wowlikon.ioExperement;

import java.util.List;

public class DebugCmd implements Command{
    public String getCmd(){return "dbg";}
    public String getCmdHelp(){return "/dbg {on/off} - enable/disable debug mode";}
    public String getHelpText(){return "Enabling od disabling debug mode.\n/dbg {status}\nRequired arguments:\n\t{status} - on/off";}

    public String validate(String cmd, String[] args){
        if (args.length != 1) return "required argument: status {on/off}";
        if (args[0].equals("on") | args[0].equals("off")) return null;
        return "choice error: status {on/off}";
    }

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        if (args[0].equals("on")) {
            dbg.value = true; System.out.println("Debug enabled");
        } else if (args[0].equals("off")) {
            dbg.value = false; System.out.println("Debug disabled");
        }
    }
}