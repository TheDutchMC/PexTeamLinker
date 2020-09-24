package nl.thedutchmc.PexTeamLinker.commands;

import java.util.Map;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.authentication.Authentication;

public class Auth {

	public boolean auth(CommandSender sender) {
		for(Map.Entry<UUID, String> entry : Authentication.getVerifiedPlayers().entrySet()) {
			
			if(entry.getKey().equals(((Player) sender).getUniqueId())) {
				sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "You are already verified!");
				return true;
			}
		}
		
		String code = Authentication.generateCodeForPlayer(((Player) sender));
		
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Please send a message to " + ChatColor.RED + ConfigurationHandler.botNameAndTag + ChatColor.GOLD + " with the code: " + ChatColor.RED + code);
		
		return true;
	}
}
