package org.wowlikon.ioExperement;

import java.util.List;

public class HelpCmd implements Command{
    public final String cmd = "help";
    public final String cmdHelp = "/help [command] - write help information for commands";
    public final String helpText = "Writing all information about command or list of commands with description.\n/help [command]\nRequired arguments:\n\t[command] - any command";

    public String validate(String cmd, String[] args){
        if (args.length < 2) return null;
        return "Supporting only one argument [command]";
    }

    public void execute(String cmd, String[] args, boolObj dbg, boolObj run, List<Command> cmds){
        if (args.length == 0) {
            for (Command c : cmds) System.out.println(c.cmdHelp);
        } else {
            System.out.println("Help:");
            for (Command c : cmds) {
                if (c.cmd.equals(args[1])) {
                    System.out.println(c.helpText);
                }
            }
        }
    }
}
