package net.craftminecraft.bungee.minitp;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TPCommand extends Command {
	String name;
	String dest;
	public TPCommand(String name, String destination) {
		super(name);
		this.name = name;
		this.dest = destination;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			if (sender.hasPermission("minitp.teleport") || sender.hasPermission("minitp.teleport." + name)) {
				ProxiedPlayer player = (ProxiedPlayer) sender;
				ServerInfo info = ProxyServer.getInstance().getServerInfo(dest);
				if (info != null)
					player.connect(info);
				else
					sender.sendMessage(ChatColor.RED + "Server " + dest + " does not exist!");
			} else {
				sender.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			}
		} else {
			sender.sendMessage("This command can only be sent in-game");
		}	
	}
}
