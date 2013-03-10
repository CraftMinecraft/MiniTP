package net.craftminecraft.bungee.minitp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import net.craftminecraft.bungee.bungeeyaml.InvalidConfigurationException;
import net.craftminecraft.bungee.bungeeyaml.supereasyconfig.Config;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class MiniTP extends Plugin {
	MainConfig config;
	public void onEnable() {
		config = new MainConfig(this);
		for (Map.Entry<String, String> entry : config.entries.entrySet()) {
			ProxyServer.getInstance().getPluginManager().registerCommand(new TPCommand(entry.getKey(), entry.getValue()));
		}
	}
	
	public void onDisable() {
		config = null;
	}
	
	public class MainConfig extends Config {
		public MainConfig(Plugin plugin) {
			CONFIG_FILE = new File("plugins" + File.separator + plugin.getDescription().getName(), "config.yml");
			CONFIG_HEADER = "MiniTP Configuration Syntax : ";
			CONFIG_HEADER += "nameofcommand: targetserver";
			try {
				this.init();
			} catch (InvalidConfigurationException e) {
				ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Could not read config !", e);
			}
		}
		
		public Map<String, String> entries = new HashMap<String,String>(){{ put("lobby","lobby");}};
	}
}
