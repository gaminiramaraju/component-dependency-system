package com.cds.core;

import com.cds.model.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@org.springframework.stereotype.Component
public class DependencyManagerSystem {
    private Map<Component, LinkedHashSet<Component>> incomingDependencies;
    private Map<String, Component> installedComponents;

    public DependencyManagerSystem(){
        incomingDependencies = new HashMap<>();
        installedComponents = new HashMap<>();
    }

    public Map<String, Component> getInstalledComponents() {
        return installedComponents;
    }

    public void setInstalledComponents(Map<String, Component> installedComponents) {
        this.installedComponents = installedComponents;
    }

    public Map<Component, LinkedHashSet<Component>> getIncomingDependencies() {
        return incomingDependencies;
    }

    public void setIncomingDependencies(Map<Component, LinkedHashSet<Component>> incomingDependencies) {
        this.incomingDependencies = incomingDependencies;
    }
    public boolean isInstalled(Component component) {
        return component.isInstalled();
    }

    public boolean componentInstalled(Component component) {
        return installedComponents.containsValue(component);
    }

    public boolean hasIncomingDependencies(Component component) {
        return !CollectionUtils.isEmpty(incomingDependencies.get(component));
    }
    public void addIncomingDependency(Component component, Component dependant) {
        if (!incomingDependencies.containsKey(dependant)) {
            incomingDependencies.put(dependant, new LinkedHashSet<>());
        }
        getIncomingDependencies().get(dependant).add(component);
    }
    public Set<Component> getDependencies(Component component) {
        for(Component comp : incomingDependencies.keySet()){
            Set<Component> components  = incomingDependencies.get(comp);
            for(Component dep : components){
                if(dep.equals(component)) {
                    return dep.getDependencies();
                }
            }

        }
        return null;
    }
    public Component getInstalledComponent(Component component) {
        return installedComponents.get(component.getName());
    }
}
