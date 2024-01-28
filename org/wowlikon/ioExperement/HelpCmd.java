package org.wowlikon.ioExperement;

import java.util.List;

public class HelpCmd implements Command{
    String cmd = "help";
    String cmdHelp = "/help [command] - write help information for commands";
    String helpText = "Writing all information about command or list of commands with description.\n/help [command]]\nRequired arguments:\n\t[command] - any command";

    public String validate(String cmd, String[] args){
        if (args.length < 2) return null;
        return "Supporting only one argument [command]";
    }

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        if (args.length == 0) {
            for (Command c : cmds) System.out.println(c.cmdHelp);
        } else {
            for (Command c : cmds) {
                if (c.cmd.equals(args[1])) {
                    System.out.println(c.cmdHelp); //full info
                }
            }
        }
    }
}
