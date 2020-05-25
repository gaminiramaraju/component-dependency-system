package com.cds.service;

import com.cds.model.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface IDependencyManagerService {

        void depend(Component component, Set<Component> dependencies);

        Component install(Component component);

        Component remove(Component component);

        Set<Component> list();
}
