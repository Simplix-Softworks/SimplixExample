package com.simplixsoft.example.common;

import dev.simplix.core.common.aop.ScanComponents;
import dev.simplix.core.common.aop.SimplixApplication;

@SimplixApplication(name = "SimplixExample",
                    version = "1.0",
                    authors = "Exceptionflug",
                    dependencies = "SimplixCore",
                    workingDirectory = "plugins/SimplixExample")
@ScanComponents("com.simplixsoft.example") // Scan common base package
public class ExampleApplication {
}
