package org.wowlikon.ioExperement;

import jline.ConsoleReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    static String removePrefix(String src, char prefix) {
        if ((src.charAt(0) == prefix) & !(src.charAt(1) == prefix)) {
            return src.substring(1);
        }
        return src;
    }

    public static void main(String[] args) throws IOException {

        String line;
        boolean dbg = false;
        ConsoleReader reader = new ConsoleReader();
        running: while ((line = reader.readLine(">")) != null) {
            if (!line.isEmpty()){
                String[] arguments = removePrefix(line, '/').split(" ");

                String cmd = arguments[0];
                arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

                if (dbg) System.out.println(cmd+' '+Arrays.toString(arguments));

                switch (cmd.toLowerCase()) {
                    case "exit":
                        System.out.println("Exit!");
                        break running;
                    case "print":
                        System.out.println(String.join(" ", arguments));
                        break;
                    case "dbg":
                        if (arguments.length != 0) {
                            if (arguments[0].equals("on")) {
                                dbg = true;
                                System.out.println("Debug enabled");
                            } else if (arguments[0].equals("off")) {
                                dbg = false;
                                System.out.println("Debug disabled");
                            } else {
                                System.out.println("Error. Select on/off");
                            }
                        } else {
                            System.out.println("Error. Select on/off");
                        }
                        break;
                    case "help":
                        System.out.println("/print [...] - write arguments to console");
                        System.out.println("/dbg {on/off} - enable/disable debug mode");
                        System.out.println("/help [cmd] - write information about command");
                        System.out.println("/exit - close this application");
                        break;
                    default:
                        System.out.println("Command \""+cmd+"\" not found");
                        break;
                }

            }else{
                System.out.println("Empty command!");
            }
        }
    }
}