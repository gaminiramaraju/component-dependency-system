package com.cds.core;

import com.cds.model.CommandType;
import com.cds.model.Component;
import com.cds.service.IDependencyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Component
public class DependCommand implements Command {


    static {
        CommandFactory.register(String.valueOf(CommandType.DEPEND), DependCommand.class);
    }

    @Autowired
    private IDependencyManagerService dependencyManagerService;

    @Override
    public void execute(String[] parameters) {
        String[] depParams = Arrays.copyOfRange(parameters, 2, parameters.length);
        Set<Component> dependencies = new HashSet<>();

        for(String dependency : depParams){
            dependencies.add(new Component(dependency));
        }
        dependencyManagerService.depend(new Component(parameters[1]),
                dependencies);
    }
}
