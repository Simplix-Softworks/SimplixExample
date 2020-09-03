package com.simplixsoft.example.bungee.kick;

import com.simplixsoft.example.bungee.BungeeExampleModule;
import com.simplixsoft.example.common.kick.PlayerKickService;
import dev.simplix.core.common.aop.Component;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.util.UUID;

@Component(value = BungeeExampleModule.class, // We will bind this component to our bungee module
    parent = PlayerKickService.class) // This class instance will be registered as PlayerKickService
public final class BungeePlayerKickService implements PlayerKickService {

  public void kickPlayer(UUID uniqueId, String reason) {
    ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(uniqueId);
    if(proxiedPlayer == null) {
      return;
    }
    proxiedPlayer.disconnect(reason);
  }

}
