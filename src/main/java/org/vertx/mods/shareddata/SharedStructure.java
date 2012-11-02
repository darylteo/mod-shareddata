package org.vertx.mods.shareddata;

import java.util.Date;

public class SharedStructure {

  private final int timeToLive;
  private long lastAccess;

  public SharedStructure(int timeToLive) {
    this.timeToLive = timeToLive;

    this.accessed();
  }

  public void accessed(){
    this.lastAccess = new Date().getTime();
  }

  public boolean isExpired() {
    Date now = new Date();
    
    long diff = now.getTime() - lastAccess;
    double minutes = diff / 60000.0;
    
    return minutes > this.timeToLive;
  }
}
