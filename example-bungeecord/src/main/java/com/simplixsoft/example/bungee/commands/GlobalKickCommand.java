package com.simplixsoft.example.bungee.commands;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import com.simplixsoft.example.bungee.BungeeExamplePlugin;
import com.simplixsoft.example.common.kick.PlayerKickService;
import dev.simplix.core.common.aop.Component;
import dev.simplix.core.common.aop.Private;
import dev.simplix.core.common.i18n.LocalizationManager;
import dev.simplix.core.minecraft.bungeecord.dynamiccommands.DynamicCommandsSimplixModule;
import java.util.Arrays;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@Component(DynamicCommandsSimplixModule.class) // We will bind this instance to our auto registration module
public class GlobalKickCommand extends Command {

  private final PlayerKickService playerKickService;
  private final LocalizationManager localizationManager;
  private final BungeeExamplePlugin plugin;

  @Inject // With @Inject, we will automatically receive our registered instances
  public GlobalKickCommand(
      PlayerKickService playerKickService,
      @Private LocalizationManager localizationManager,
      BungeeExamplePlugin plugin) {
    super("globalkick", "example.command.globalkick");
    this.playerKickService = playerKickService;
    this.localizationManager = localizationManager;
    this.plugin = plugin;
  }

  public void execute(CommandSender sender, String[] args) {
    if(args.length == 0) {
      sender.sendMessage(localizationManager.localized("kick-usage", plugin.locale()));
      return;
    }
    ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(args[0]);
    if(targetPlayer == null) {
      sender.sendMessage(String.format(localizationManager.localized("player-not-found",
          plugin.locale()), args[0]));
      return;
    }
    String reason = localizationManager.localized("default-kick-reason", plugin.locale());
    if(args.length > 1) {
      reason = Joiner.on(" ").join(Arrays.asList(args).subList(1, args.length));
    }
    playerKickService.kickPlayer(targetPlayer.getUniqueId(), reason);
    sender.sendMessage(String.format(localizationManager.localized("player-kicked",
        plugin.locale()), targetPlayer.getName(), reason));
  }

}
