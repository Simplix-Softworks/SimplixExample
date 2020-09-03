package com.simplixsoft.example.spigot.commands;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import com.simplixsoft.example.common.kick.PlayerKickService;
import com.simplixsoft.example.spigot.SpigotExamplePlugin;
import dev.simplix.core.common.aop.Component;
import dev.simplix.core.common.aop.Private;
import dev.simplix.core.common.i18n.LocalizationManager;
import dev.simplix.minecraft.spigot.dynamiccommands.DynamicCommandsSimplixModule;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

@Component(DynamicCommandsSimplixModule.class) // We will bind this instance to our auto registration module
public class LocalKickCommand extends Command {

  private final PlayerKickService playerKickService;
  private final LocalizationManager localizationManager;
  private final SpigotExamplePlugin plugin;

  @Inject // With @Inject, we will automatically receive our registered instances
  public LocalKickCommand(
      PlayerKickService playerKickService,
      @Private LocalizationManager localizationManager,
      SpigotExamplePlugin plugin) {
    super("localkick");
    this.localizationManager = localizationManager;
    this.plugin = plugin;
    setPermission("example.command.localkick");
    this.playerKickService = playerKickService;
  }

  public boolean execute(
      @NotNull CommandSender sender,
      @NotNull String s,
      @NotNull String[] args) {
    if(args.length == 0) {
      sender.sendMessage(localizationManager.localized("kick-usage", plugin.locale()));
      return false;
    }
    Player targetPlayer = Bukkit.getPlayer(args[0]);
    if(targetPlayer == null) {
      sender.sendMessage(String.format(localizationManager.localized("player-not-found",
          plugin.locale()), args[0]));
      return false;
    }
    String reason = localizationManager.localized("default-kick-reason", plugin.locale());
    if(args.length > 1) {
      reason = Joiner.on(" ").join(Arrays.asList(args).subList(1, args.length));
    }
    playerKickService.kickPlayer(targetPlayer.getUniqueId(), reason);
    sender.sendMessage(String.format(localizationManager.localized("player-kicked",
        plugin.locale()), targetPlayer.getName(), reason));
    return false;
  }
}
