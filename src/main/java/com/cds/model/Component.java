package com.cds.model;

import com.cds.core.Command;

import java.util.Objects;
import java.util.Set;

public class Component {
    private String name;
    private Set<Component> dependencies;
    private boolean isInstalled;

    public Component(String name){
        this.name = name;
    }
    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Component> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<Component> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return name.equals(component.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
