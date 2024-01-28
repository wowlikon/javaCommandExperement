package org.wowlikon.ioExperement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static List<Class<?>> getAllClasses(ClassLoader classLoader) {
        String packageName = ReflectionUtils.class.getPackage().getName();
        List<Class<?>> classes = new ArrayList<>();
        try (InputStream input = classLoader.getResourceAsStream(packageName.replaceAll("[.]", "/") + "/")) {
            assert input != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.endsWith(".class")) {
                        String className = line.substring(0, line.lastIndexOf('.'));
                        try {
                            classes.add(Class.forName(packageName + "." + className));
                        } catch (ClassNotFoundException e) {
                            System.out.println("Error loading class: " + packageName + "." + className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading package: " + packageName);
        }
        return classes;
    }
}
