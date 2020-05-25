package com.cds.core;

import com.cds.model.CommandType;
import com.cds.model.Component;
import com.cds.service.IDependencyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
@org.springframework.stereotype.Component
public class RemoveCommand implements  Command {
    static {
        CommandFactory.register(String.valueOf(CommandType.REMOVE), RemoveCommand.class);
    }
    @Autowired
    private IDependencyManagerService dependencyManagerService;

    @Override
    public void execute(String[] parameters) {
        dependencyManagerService.remove(new Component(parameters[1]));
    }
}
