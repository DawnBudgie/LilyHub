package com.sgtcaze.LilyHub;

import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.RedirectRequest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	Connect connect;

	public void onEnable() {
		this.connect = ((Connect) getServer().getServicesManager()
				.getRegistration(Connect.class).getProvider());
		saveDefaultConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("hub")
				|| cmd.getName().equalsIgnoreCase("lobby")) {
			try {
				this.connect.request(new RedirectRequest(getConfig().getString(
						"Server"), ((Player) sender).getName()));
			} catch (RequestException e) {
				e.printStackTrace();
			}
			return true;
		}
		return true;

	}
}
