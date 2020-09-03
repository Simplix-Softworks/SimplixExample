package com.simplixsoft.example.spigot.kick;

import com.simplixsoft.example.common.kick.PlayerKickService;
import com.simplixsoft.example.spigot.SpigotExampleModule;
import dev.simplix.core.common.aop.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.UUID;

@Component(value = SpigotExampleModule.class, // We will bind this component to our spigot module
    parent = PlayerKickService.class) // This class instance will be registered as PlayerKickService
public final class SpigotPlayerKickService implements PlayerKickService {

  public void kickPlayer(UUID uniqueId, String reason) {
    Player player = Bukkit.getPlayer(uniqueId);
    if(player == null) {
      return;
    }
    player.kickPlayer(reason);
  }

}
