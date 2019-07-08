package me.jedimastersoda.transfer.utils;

import java.lang.reflect.InvocationTargetException;

import com.google.common.base.Preconditions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReflectionUtils {

  private static String nms_version;

  public static void addChannel(Player player, String channel) throws ClassNotFoundException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    Preconditions.checkArgument(player.isOnline(), "Player must be online to add a channel.");

    if(player.getListeningPluginChannels().contains(channel)) return;

    Class<?> c_craftPlayer = Class.forName(fetchCraftbukkitClass("entity.CraftPlayer"));
    
    Object craftPlayer = c_craftPlayer.cast(player);

    c_craftPlayer.getDeclaredMethod("addChannel", String.class).invoke(craftPlayer, channel);
  }

  public static String fetchNMSVersion() {
    if(nms_version == null) {
      String _package = Bukkit.getServer().getClass().getPackage().getName();
      _package = _package.substring(_package.lastIndexOf(".") + 1);

      nms_version = _package;
    }

    return nms_version;
  }

  public static String fetchCraftbukkitClass(String _class) {
    return "org.bukkit.craftbukkit." + fetchNMSVersion() + "." + _class;
  }
}