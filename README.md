# Transfer-Packet-Plugin

#### Warning: This plugin requires players to have the [Transfer Packet Mod](https://github.com/JediMasterSoda/Transfer-Packet-Mod/releases/latest) installed in order to properly function.

### Usage
1. Install the [Forge mod](https://github.com/JediMasterSoda/Transfer-Packet-Mod/releases/latest) (currently supports 1.14)
2. Install [the plugin](https://github.com/JediMasterSoda/Transfer-Packet-Plugin/releases/latest) on your Spigot server
3. Use `TransferPacket.getInstance()` to access the API & route players to other servers.

### API
###### Send a transfer packet to one player:  
`TransferPacket#sendTransferPacket(Player player, String ip_address)`

###### Send a transfer packet to one or more players:  
`TransferPacket#sendTransferPacket(String ip_address, Player... players)`
