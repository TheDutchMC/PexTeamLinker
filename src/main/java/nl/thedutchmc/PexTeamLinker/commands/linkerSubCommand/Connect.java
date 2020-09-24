package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand;

import org.bukkit.command.CommandSender;

import nl.thedutchmc.PexTeamLinker.commands.Auth;

public class Connect {
	
	public static boolean connect(CommandSender sender, String[] args) {
		Auth a = new Auth();
		a.auth(sender);
		
		return true;
	}
}
