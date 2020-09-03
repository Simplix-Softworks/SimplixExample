package com.simplixsoft.example.bungee;

import com.simplixsoft.example.common.ExampleApplication;
import dev.simplix.core.common.inject.SimplixInstaller;
import dev.simplix.core.minecraft.bungeecord.dynamiccommands.DynamicCommandsSimplixModule;
import dev.simplix.core.minecraft.bungeecord.dynamiclisteners.DynamicListenersSimplixModule;
import dev.simplix.core.minecraft.bungeecord.quickstart.SimplixQuickStart;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.md_5.bungee.api.plugin.Plugin;
import java.util.Locale;

@Accessors(fluent = true)
public final class BungeeExamplePlugin extends Plugin {

  @Getter
  private Locale locale = Locale.GERMAN;

  @Override
  public void onEnable() {
    if(!SimplixQuickStart.ensureSimplixCore(this)) {
      return;
    }
    SimplixInstaller.instance().register(ExampleApplication.class,
        new DynamicListenersSimplixModule(this), // Add listener component auto registration module
        new DynamicCommandsSimplixModule(this), // Add command component auto registration module
        new BungeeExampleModule(this)); // Add our bungee module
  }

}
