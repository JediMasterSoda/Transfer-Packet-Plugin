package me.jedimastersoda.transfer;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.jedimastersoda.transfer.utils.ReflectionUtils;

public class TransferPacket extends JavaPlugin {

  @Getter
  private static TransferPacket instance;

  private final String channel = "transfer:main";

  public void onEnable() {
    instance = this;

    this.getServer().getMessenger().registerOutgoingPluginChannel(this, channel);
  }

  /**
   * Sends a transfer packet to the specified player.
   * @param player
   * @param ip_address
   */
  public void sendTransferPacket(Player player, String ip_address) {
    Preconditions.checkArgument(player.isOnline(), "Player must be online to send a transfer packet.");

    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF(ip_address);

    try {
      ReflectionUtils.addChannel(player, channel);

      player.sendPluginMessage(this, channel, out.toByteArray());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends a transfer packet to the specified players.
   * @param ip_address
   * @param players
   */
  public void sendTransferPacket(String ip_address, Player... players) {
    for(Player player : players) {
      this.sendTransferPacket(player, ip_address);
    }
  }
}