package nl.thedutchmc.PexTeamLinker.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.Connect;
import nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.Help;
import nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.prefab.Prefab;

public class LinkerCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "No arguments given. See " + ChatColor.RED + "/linker help" + ChatColor.GOLD + " for help");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("help")) {
			return Help.help(sender, args);
		}
		
		else if(args[0].equalsIgnoreCase("connect")) {
			return Connect.connect(sender, args);
		}
		
		else if(args[0].equalsIgnoreCase("prefab")) { 
			return Prefab.prefab(sender, args);
		}
		
		else {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Invalid command! See " + ChatColor.RED + "/linker help" + ChatColor.GOLD + " for help");
			return true;
		}
	}
}
