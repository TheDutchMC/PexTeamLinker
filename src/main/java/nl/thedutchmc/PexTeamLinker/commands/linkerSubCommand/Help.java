package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class Help {
	
	public static boolean help(CommandSender sender, String[] args) {
			
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "PexTeamLinker Help Menu");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + "- " + ChatColor.GOLD + "/linker help " + ChatColor.WHITE + "See this list");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + "- " + ChatColor.GOLD + "/linker prefab " + ChatColor.WHITE + "Prefab commands");

		return true;
	}
}
