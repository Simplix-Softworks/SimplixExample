package com.simplixsoft.example.spigot;

import com.simplixsoft.example.common.ExampleApplication;
import dev.simplix.core.common.inject.SimplixInstaller;
import dev.simplix.core.minecraft.spigot.dynamiclisteners.DynamicListenersSimplixModule;
import dev.simplix.core.minecraft.spigot.quickstart.SimplixQuickStart;
import dev.simplix.minecraft.spigot.dynamiccommands.DynamicCommandsSimplixModule;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Locale;

@Accessors(fluent = true)
public class SpigotExamplePlugin extends JavaPlugin {

  @Getter
  private Locale locale = Locale.GERMAN;

  @Override
  public void onEnable() {
    if(!SimplixQuickStart.ensureSimplixCore(this)) {
      return;
    }
    SimplixInstaller.instance().register(
        ExampleApplication.class,
        new DynamicListenersSimplixModule(this), // Add listener component auto registration
        new DynamicCommandsSimplixModule()); // Add command component auto registration
  }

}
