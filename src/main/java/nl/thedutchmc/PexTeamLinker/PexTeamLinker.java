package nl.thedutchmc.PexTeamLinker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.authentication.Authentication;
import nl.thedutchmc.PexTeamLinker.commands.AuthCommandCompleter;
import nl.thedutchmc.PexTeamLinker.commands.AuthCommandExecutor;
import nl.thedutchmc.PexTeamLinker.commands.LinkerCommandCompleter;
import nl.thedutchmc.PexTeamLinker.commands.LinkerCommandExecutor;
import nl.thedutchmc.PexTeamLinker.discord.JdaHandler;
import nl.thedutchmc.PexTeamLinker.minecraftEvents.PlayerJoinEventListener;
import nl.thedutchmc.PexTeamLinker.minecraftEvents.PlayerLoginEventListener;
import nl.thedutchmc.PexTeamLinker.prefabs.Prefabs;

public class PexTeamLinker extends JavaPlugin {

	public static PexTeamLinker INSTANCE;
	public static ConfigurationHandler CONFIG;
	public static Prefabs PREFABS;
	public static JdaHandler JDA;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		CONFIG = new ConfigurationHandler();
		CONFIG.loadConfig();
		
		PREFABS = new Prefabs();
		PREFABS.loadPrefabsFromStorage();
		
		JDA = new JdaHandler(ConfigurationHandler.discordToken);
		
		//Create a new Authentication object to load the PlayerProfiles from disk into memory.
		new Authentication();
		
		//JdaHandler can disable the plugin, so we check if it is still enabled before proceeding.
		if(!this.isEnabled()) {
			return;
		}
		
		//Commands and tab completers
		
		if(ConfigurationHandler.isMainServer) {
			this.getCommand("linker").setExecutor(new LinkerCommandExecutor());
			this.getCommand("linker").setTabCompleter(new LinkerCommandCompleter());
			
			this.getCommand("auth").setExecutor(new AuthCommandExecutor());
			this.getCommand("auth").setTabCompleter(new AuthCommandCompleter());
		}
		
		
		
		//Minecraft event listeners
		Bukkit.getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLoginEventListener(), this);
		
		logInfo("Welcome to PexTeamLinker version " + this.getDescription().getVersion() + " by TheDutchMC!");
	}
	
	@Override
	public void onDisable() {
		final int DISCONNECT_DELAY = 3;
		int delayLeft = DISCONNECT_DELAY;
		
		logInfo("Giving work queue " + DISCONNECT_DELAY + " seconds to empty...");

		while(delayLeft > 0) {
			try {
				Thread.sleep(1000);
				delayLeft--;
			} catch (InterruptedException e) {
				logWarn("There was an error gracefully shutting down the plugin!");
			}
		}
		
		logInfo("Disconnecting from Discord...");

		try {
			JdaHandler.shutdownJda();
		} catch(Exception e) {} //We're going to get exceptions from JDA, but we're just not going to care ¯\_(ツ)_/¯

		logInfo("Thank you for using PexTeamLinker by TheDutchMC");	
	}
	
	public static String getPluginPrefix() {
		return ChatColor.GRAY + "[" + ChatColor.AQUA + "PTL" + ChatColor.GRAY + "] " + ChatColor.RESET;
	}
	
	public static void logInfo(String log) {
		Bukkit.getLogger().info("[" + PexTeamLinker.INSTANCE.getDescription().getName() + "] " + log);	
	}
	
	public static void logWarn(String log) {
		Bukkit.getLogger().warning("[" + PexTeamLinker.INSTANCE.getDescription().getName() + "] " + log);	
	}
}
