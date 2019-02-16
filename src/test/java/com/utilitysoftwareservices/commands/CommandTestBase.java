package com.utilitysoftwareservices.commands;

import static org.mockito.Mockito.mock;

import com.utilitysoftwareservices.controllers.Controller;

import org.junit.Before;

/**
 * Base test class for testing Commands
 * 
 */
public class CommandTestBase {

    protected Controller controller;

    @Before
    public void setUp() {
        controller = mock(Controller.class);
    }
}