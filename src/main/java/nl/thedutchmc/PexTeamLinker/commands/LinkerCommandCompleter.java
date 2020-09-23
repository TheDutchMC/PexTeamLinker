package nl.thedutchmc.PexTeamLinker.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.prefabs.Prefab;

public class LinkerCommandCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		// /linker
		if(args.length == 1) {
			List<String> possibleCommands = new ArrayList<>();
			possibleCommands.add("help");
			possibleCommands.add("prefab");
			
			return possibleCommands;
		}
		
		if(args.length == 2) {
			
			// /linker prefab
			if(args[0].equalsIgnoreCase("prefab")) {				
				
				List<String> possibleCommands = new ArrayList<>();
				possibleCommands.add("help");
				possibleCommands.add("create");
				possibleCommands.add("list");
				possibleCommands.add("remove");
				
				return possibleCommands;
			}
		}
		
		if(args.length == 3) {
			
			// /linker prefab create
			if(args[1].equalsIgnoreCase("create")) {
				List<String> possibleCommands = new ArrayList<>();
				
				possibleCommands.add("<prefab_name>");
				
				return possibleCommands;
			}
			
			// /linker prefab remove
			else if(args[0].equalsIgnoreCase("remove")) {
				List<String> possibleCommands = new ArrayList<>();
				
				for(Prefab prefab : PexTeamLinker.PREFABS.getPrefabs()) {
					possibleCommands.add(prefab.getName());
				}
				
				return possibleCommands;
			}
			
			else {
				List<String> possibleCommands = new ArrayList<>();
				possibleCommands.add("");
				return possibleCommands;
				
			}
		}
		
		else if(args.length == 4) {
			
			// /linker prefab create <prefab name>
			if(args[1].equalsIgnoreCase("create")) {
				List<String> possibleCommands = new ArrayList<>();
				
				possibleCommands.add("<Pex_group_name>");
				
				return possibleCommands;
			}
		}
		
		else if(args.length == 5) {
			
			// /linker prefab create <prefab name> <pex group name>
			if(args[1].equalsIgnoreCase("create")) {
				List<String> possibleCommands = new ArrayList<>();
				
				possibleCommands.add("<Minecraft_team_name>");
				
				return possibleCommands;
			}
		}
		
		else if(args.length == 6) {
			
			// /linker prefab create <prefab name> <pex group name> <minecraft team name>
			if(args[1].equalsIgnoreCase("create")) {
				List<String> possibleCommands = new ArrayList<>();
				
				possibleCommands.add("<Discord_role_id>");
				
				return possibleCommands;
			}
		}
		
		else if(args.length > 6) {
			
			// /linker prefab create <prefab name> <pex group name> <minecraft team name> <discord id>
			if(args[1].equalsIgnoreCase("create")) {
				List<String> possibleCommands = new ArrayList<>();
				
				possibleCommands.add("");
				
				return possibleCommands;
			}
		}
		
		return null;
	}
	
}
