package com.sgtcaze.LilyHub;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("hub")) {
			    String server = getConfig().getString("Server");
				player.performCommand("server " + server);
			}
		return true;

	}
}
