package com.simplixsoft.example.common.kick;

import java.util.UUID;

/**
 * A simple example interface for platform independent player kicking
 */
public interface PlayerKickService {

  void kickPlayer(UUID uniqueId, String reason);

}
