package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.prefab;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.prefabs.Prefab;

public class Remove {
	
	public static boolean remove(CommandSender sender, String[] args) {
		
		if(!(args.length >= 3)) {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Not enough arguments supplied! See " + ChatColor.RED + "/linker prefab help " + ChatColor.GOLD + "for help");
		}
		
		boolean prefabExists = false;
		Prefab toRemove = null;
		
		for(Prefab prefab : PexTeamLinker.PREFABS.getPrefabs()) {
			if(prefab.getName().contentEquals(args[2])) {
				prefabExists = true;
				
				toRemove = prefab;
				
			}
		}
		
		if(prefabExists) {
			PexTeamLinker.PREFABS.removePrefab(toRemove);
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "The Prefab " + ChatColor.RED + args[2] + ChatColor.GOLD + " has been removed!");
			return true;
		} else {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "The Prefab " + ChatColor.RED + args[2] + ChatColor.GOLD + " does not exist! Use " + ChatColor.RED + "/linker prefab list" + ChatColor.GOLD + " for a list of available Prefabs.");
			return true;
		}
	}
}
