package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.prefab;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class Prefab {

	public static boolean prefab(CommandSender sender, String[] args) {
		
		if(!(args.length >= 2)) {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Not enough arguments supplied! See " + ChatColor.RED + "/linker prefab help " + ChatColor.GOLD + "for help");
			return true;
		}
		
		if(args[1].equalsIgnoreCase("help")) {
			return Help.help(sender, args);
		}
		
		else if(args[1].equalsIgnoreCase("create")) {
			return Create.create(sender, args);
		}
		
		else if(args[1].equalsIgnoreCase("list")) {
			return List.list(sender, args);
		}
		
		else if(args[1].equalsIgnoreCase("remove")) {
			return Remove.remove(sender, args);
		}
		
		else {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Invalid subcommand!");
		}
		
		
		return true;
	}
	
}
