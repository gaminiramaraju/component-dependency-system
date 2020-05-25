package com.cds.core;

import com.cds.model.CommandType;
import com.cds.model.Component;
import com.cds.service.IDependencyManagerService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class ListCommand implements Command {

    static {
        CommandFactory.register(String.valueOf(CommandType.LIST), ListCommand.class);
    }

    @Autowired
    private IDependencyManagerService dependencyManagerService;

    @Override
    public void execute(String[] parameters) {
        dependencyManagerService.list();
    }
}
