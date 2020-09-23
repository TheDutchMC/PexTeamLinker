package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.prefab;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class Help {

	public static boolean help(CommandSender sender, String[] args) {
		
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Prefab Help Menu");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + "- " + ChatColor.GOLD + "/linker prefab help " + ChatColor.WHITE + "See this list");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + "- " + ChatColor.GOLD + "/linker prefab create <name> <pex group name> <minecraft team name> <discord role id> " + ChatColor.WHITE + "Create a new Prefab");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + "- " + ChatColor.GOLD + "/linker prefab list " + ChatColor.WHITE + "Get a list of all registered Prefabs");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + "- " + ChatColor.GOLD + "/linker prefab remove <name> " + ChatColor.WHITE + "Remove a Prefab");

		return true;
	}
}
