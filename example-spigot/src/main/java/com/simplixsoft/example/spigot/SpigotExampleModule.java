package com.simplixsoft.example.spigot;

import com.google.inject.Binder;
import dev.simplix.core.common.aop.AbstractSimplixModule;

public class SpigotExampleModule extends AbstractSimplixModule {

  private final SpigotExamplePlugin plugin;

  public SpigotExampleModule(SpigotExamplePlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void configure(Binder binder) {
    super.configure(binder); // Don't forget to call super method
    binder.bind(SpigotExamplePlugin.class).toInstance(plugin); // Our main class can be injected
  }
}
