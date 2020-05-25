package com.cds.service;

import com.cds.core.DependencyManagerSystem;
import com.cds.model.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class DependencyManagerService implements IDependencyManagerService {

    private DependencyManagerSystem dependencyManagerSystem;

    public DependencyManagerService(DependencyManagerSystem dependencyManagerSystem){
        this.dependencyManagerSystem = dependencyManagerSystem;
    }

    @Override
    public void depend(Component component, Set<Component> dependencies) {
        ensureComponentNotNull(component);
        ensureComponentNotNull(dependencies);

        component.setDependencies(dependencies);
        for(Component dependant : dependencies){
            dependencyManagerSystem.addIncomingDependency(component, dependant);
        }
     }

    @Override
    public Component install(Component component) {
        ensureComponentNotNull(component);

        if(!dependencyManagerSystem.componentInstalled(component)) {
            System.out.println("    Installing "+component.getName());
            dependencyManagerSystem.getInstalledComponents().put(component.getName(), component);
            installDependencies(component);
        }else{
            System.out.println("    "+component.getName() + " is already installed.");
        }
        return component;
    }

    private void installDependencies(Component component) {
        Set<Component> dependencies = dependencyManagerSystem.getDependencies(component);
        if(!CollectionUtils.isEmpty(dependencies)) {
            for (Component dependency : dependencies) {
                if (!dependencyManagerSystem.componentInstalled(dependency)) {
                    System.out.println("    Installing " + dependency.getName());
                    dependencyManagerSystem.getInstalledComponents().put(dependency.getName(), dependency);
                }
            }
        }
    }

    @Override
    public Component remove(Component component) {
        ensureComponentNotNull(component);
        Component compTobeRemoved =  dependencyManagerSystem.getInstalledComponent(component);
        if(null == compTobeRemoved) {
            System.out.println("    "+component.getName()+" is not installed.");
            return component;
        }
        if(!dependencyManagerSystem.hasIncomingDependencies(compTobeRemoved)){
            removeDependencies(component);
            dependencyManagerSystem.getInstalledComponents().remove(compTobeRemoved.getName());
            System.out.println("    Removing " + compTobeRemoved.getName());
        }else{
           System.out.println("     "+component.getName()+" is still needed.");
        }
        return component;
    }

    private void cleanUpIncomingDependencies(Component component) {
        dependencyManagerSystem.getIncomingDependencies().keySet().forEach(component1 -> {
           LinkedHashSet<Component> components =  dependencyManagerSystem.getIncomingDependencies().get(component1);
           components.remove(component);
        });
    }

    private void removeDependencies(Component component) {
        Set<Component> dependencies = dependencyManagerSystem.getDependencies(component);
        cleanUpIncomingDependencies(component);
        if(!CollectionUtils.isEmpty(dependencies)) {
            for (Component dependency : dependencies) {
                if(!dependencyManagerSystem.hasIncomingDependencies(dependency)){
                    System.out.println("    Removing " + dependency.getName());
                    dependencyManagerSystem.getInstalledComponents().remove(dependency.getName());
                }
            }
        }
    }

    @Override
    public Set<Component> list() {
        dependencyManagerSystem.getInstalledComponents().keySet().stream().forEach(comp -> System.out.println(" "+comp));

        return new HashSet<>(dependencyManagerSystem.getInstalledComponents().values());
    }

    private void ensureComponentNotNull(final Object... args) {
        for (final Object arg : args) {
            if (arg == null || arg instanceof Collection && ((Collection<?>) arg).stream().anyMatch(Objects::isNull)) {
                throw new NullPointerException("Null nodeContent is not allowed");
            }
        }
    }

}
