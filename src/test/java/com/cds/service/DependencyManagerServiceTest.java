package com.cds.service;

import com.cds.core.DependencyManagerSystem;
import com.cds.model.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DependencyManagerServiceTest {

    @Mock
    private DependencyManagerSystem mockDependencyManagerSystem;

    private DependencyManagerService dependencyManagerServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        dependencyManagerServiceUnderTest = new DependencyManagerService(mockDependencyManagerSystem);
    }

    @Test
    void testDepend() {
        // Setup
        final Component component = new Component("name");
        final Set<Component> dependencies = new HashSet<>(Arrays.asList(new Component("name")));

        // Run the test
        dependencyManagerServiceUnderTest.depend(component, dependencies);

        // Verify the results
        verify(mockDependencyManagerSystem).addIncomingDependency(new Component("name"), new Component("name"));
    }

    @Test
    void testInstall() {
        // Setup
        final Component component = new Component("name");
        final Component expectedResult = new Component("name");
        when(mockDependencyManagerSystem.componentInstalled(new Component("name"))).thenReturn(false);
        when(mockDependencyManagerSystem.getInstalledComponents()).thenReturn(new HashMap<>());

        // Configure DependencyManagerSystem.getDependencies(...).
        final Set<Component> components = new HashSet<>(Arrays.asList(new Component("name")));
        when(mockDependencyManagerSystem.getDependencies(new Component("name"))).thenReturn(components);

        // Run the test
        final Component result = dependencyManagerServiceUnderTest.install(component);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testRemove() {
        // Setup
        final Component component = new Component("name");
        final Component expectedResult = new Component("name");
        when(mockDependencyManagerSystem.getInstalledComponent(new Component("name"))).thenReturn(new Component("name"));
        when(mockDependencyManagerSystem.hasIncomingDependencies(new Component("name"))).thenReturn(false);

        // Configure DependencyManagerSystem.getDependencies(...).
        final Set<Component> components = new HashSet<>(Arrays.asList(new Component("name")));
        when(mockDependencyManagerSystem.getDependencies(new Component("name"))).thenReturn(components);

        when(mockDependencyManagerSystem.getInstalledComponents()).thenReturn(new HashMap<>());

        // Run the test
        final Component result = dependencyManagerServiceUnderTest.remove(component);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testList() {
        // Setup
        final Set<Component> expectedResult = new HashSet<>(Arrays.asList(new Component("name")));
        when(mockDependencyManagerSystem.getInstalledComponents()).thenReturn(new HashMap<>());

        // Run the test
        final Set<Component> result = dependencyManagerServiceUnderTest.list();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
