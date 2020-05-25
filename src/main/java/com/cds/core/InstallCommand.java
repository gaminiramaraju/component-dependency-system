package com.cds.core;

import com.cds.model.CommandType;
import com.cds.model.Component;
import com.cds.service.IDependencyManagerService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class InstallCommand implements Command {
    static {
        CommandFactory.register(String.valueOf(CommandType.INSTALL), InstallCommand.class);
    }
    @Autowired
    private IDependencyManagerService dependencyManagerService;

    @Override
    public void execute(String[] parameters) {
        dependencyManagerService.install(new Component(parameters[1]));
    }
}
