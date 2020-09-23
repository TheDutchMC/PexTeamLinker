package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.prefab;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class Create {

	public static boolean create(CommandSender sender, String[] args) {
		
		if(!(args.length >= 6)) {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Not enough arguments supplied! See " + ChatColor.RED + "/linker prefab help " + ChatColor.GOLD + "for help");
			return true;
		}		
		
		PexTeamLinker.PREFABS.createPrefab(args[2], args[3], args[4], args[5]);
		
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Created a Prefab called " + ChatColor.RED + args[2] + ChatColor.GOLD + "!");
		
		
		return true;
	}
}
