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

	public double timer = 1;

	public void onEnable() {
		this.connect = ((Connect) getServer().getServicesManager()
				.getRegistration(Connect.class).getProvider());

		saveDefaultConfig();

		this.timer = getConfig().getDouble("Delay");
	}

	public void redirect(String string, Player player) {
		try {
			connect.request(new RedirectRequest(
					getConfig().getString("Server"), player.getName()));
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		final Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("hub")
				|| cmd.getName().equalsIgnoreCase("lobby")) {
			if (getConfig().getBoolean("TeleportDelay")) {
				if (player.hasPermission("lilyhub.bypass")) {
					redirect(getConfig().getString("Server"), player);
				} else {
					getServer().getScheduler().scheduleSyncDelayedTask(this,
							new Runnable() {
								public void run() {
									try {
										connect.request(new RedirectRequest(
												getConfig().getString("Server"),
												player.getName()));
									} catch (RequestException e) {
										e.printStackTrace();
									}
								}
							}, (long) (this.timer * 20));
				}
			} else {
				redirect(getConfig().getString("Server"), player);

			}
		}
		return false;
	}
}