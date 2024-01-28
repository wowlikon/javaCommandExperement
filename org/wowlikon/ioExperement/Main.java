package org.wowlikon.ioExperement;

import jline.ConsoleReader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        String line, err;
        boolObj run = new boolObj(true);
        boolObj dbg = new boolObj(false);
        ConsoleReader reader = new ConsoleReader();

        //Find commands
        List<Command> commands = getCommands();

        for (Command c: commands) {

            System.out.println("\n"+c);
            // Get the all field objects of User class
            Field[] fields = c.getClass().getFields();
            for (Field field : fields) {
                Object value;
                try {value = field.get(c);}
                catch (IllegalAccessException e) {continue;}
                System.out.println("Value of Field " + field.getName() + " is " + value);
            }

//            if (c.cmd.equals("help")) {
//                c.execute("help", new String[] {"help"}, dbg, run, commands);
//            }
        }

        //Main input loop
        while (((line = reader.readLine(">")) != null) & run.value) {
            if (line.isEmpty()){
                System.out.println("Empty command!");
                continue;
            }

            String[] arguments = removePrefix(line).split(" ");

            //Get command and args
            String cmd = arguments[0];
            arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

            //Print command and arguments if debug mode
            if (dbg.value) System.out.println(cmd+' '+Arrays.toString(arguments));

            //Finding commands
            finding: {
                for (Command c: commands) {
                    if (!c.cmd.equals(cmd)) continue;
                    err = c.validate(cmd, arguments);
                    if (!Objects.isNull(err)){
                        System.out.println(err);
                    } else {
                        c.execute(cmd, arguments, dbg, run, commands);
                    }
                    break finding;
                }
                System.out.println("Command \""+cmd+"\" not found!");
            }
        }
    }

    static String removePrefix(String src) {
        if ((src.charAt(0) == '/') & !(src.charAt(1) == '/')) {
            return src.substring(1);
        }
        return src;
    }

    public static List<Command> getCommands(){
        ClassLoader classLoader = Main.class.getClassLoader();
        List<Class<?>> classes = ReflectionUtils.getAllClasses(classLoader);
        List<Class<? extends Command>> commandClasses = new ArrayList<>();
        for (Class<?> clazz : classes) {
            if (Command.class.isAssignableFrom(clazz) && !clazz.equals(Command.class)) {
                commandClasses.add((Class<? extends Command>) clazz);
            }
        }

        List<Command> commandInstances = new ArrayList<>();
        for (Class<? extends Command> commandClass : commandClasses) {
            try {
                Constructor<? extends Command> constructor = commandClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                Command commandInstance = constructor.newInstance();
                commandInstances.add(commandInstance);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                System.out.println("Error creating instance of " + commandClass);
            }
        }

        return commandInstances;
    }
}