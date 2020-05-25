package com.cds.core;

import com.cds.model.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DependencyManagerSystemTest {

    private DependencyManagerSystem dependencyManagerSystemUnderTest;

    @BeforeEach
    void setUp() {
        dependencyManagerSystemUnderTest = new DependencyManagerSystem();
    }

    @Test
    void testIsInstalled() {
        // Setup
        final Component component = new Component("name");

        // Run the test
        final boolean result = dependencyManagerSystemUnderTest.isInstalled(component);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testComponentInstalled() {
        // Setup
        final Component component = new Component("name");

        // Run the test
        final boolean result = dependencyManagerSystemUnderTest.componentInstalled(component);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testHasIncomingDependencies() {
        // Setup
        final Component component = new Component("name");

        // Run the test
        final boolean result = dependencyManagerSystemUnderTest.hasIncomingDependencies(component);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testAddIncomingDependency() {
        // Setup
        final Component component = new Component("name");
        final Component dependant = new Component("name");

        // Run the test
        dependencyManagerSystemUnderTest.addIncomingDependency(component, dependant);

        // Verify the results
    }

    @Test
    void testGetDependencies() {
        // Setup
        final Component component = new Component("name");
        final Set<Component> expectedResult = new HashSet<>(Arrays.asList(new Component("name")));

        // Run the test
        final Set<Component> result = dependencyManagerSystemUnderTest.getDependencies(component);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstalledComponent() {
        // Setup
        final Component component = new Component("name");
        final Component expectedResult = new Component("name");

        // Run the test
        final Component result = dependencyManagerSystemUnderTest.getInstalledComponent(component);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
