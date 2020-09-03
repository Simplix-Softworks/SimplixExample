package com.simplixsoft.example.bungee;

import com.google.inject.Binder;
import dev.simplix.core.common.aop.AbstractSimplixModule;

public final class BungeeExampleModule extends AbstractSimplixModule {

  private final BungeeExamplePlugin plugin;

  public BungeeExampleModule(BungeeExamplePlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void configure(Binder binder) {
    super.configure(binder); // Don't forget to call super method
    binder.bind(BungeeExamplePlugin.class).toInstance(plugin); // Our main class can be injected
  }

}
