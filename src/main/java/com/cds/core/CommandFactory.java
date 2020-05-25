package com.cds.core;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, Class<? extends Command>> instances = new HashMap<>();

    public static void register(String command, Class<? extends Command> instance) {
        if (command != null && instance != null) {
            instances.put(command, instance);
        }
    }

    public static Command getInstance(ApplicationContext context, String command) {
        if (instances.containsKey(command)) {
            return context.getBean(instances.get(command));
        }
        return null;
    }
}
