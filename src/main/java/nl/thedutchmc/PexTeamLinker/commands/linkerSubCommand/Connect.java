package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand;

import java.util.Map;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.Verification;

public class Connect {
	
	public static boolean connect(CommandSender sender, String[] args) {
		
		for(Map.Entry<String, UUID> entry : Verification.getVerifiedPlayers().entrySet()) {
			PexTeamLinker.logWarn(entry.getKey() + ": " + entry.getValue());
			
			if(entry.getValue().equals(((Player) sender).getUniqueId())) {
				sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "You are already verified!");
				return true;
			}
		}
		
		String code = Verification.generateCodeForPlayer(((Player) sender));
		
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Please send a message to " + ChatColor.RED + ConfigurationHandler.botNameAndTag + ChatColor.GOLD + " with the code: " + ChatColor.RED + code);
		
		return true;
	}
}
