package nl.thedutchmc.PexTeamLinker.commands.linkerSubCommand.prefab;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.prefabs.Prefab;

public class List {

	public static boolean list(CommandSender sender, String[] args) {
		
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "All prefabs registerd with PTL:");
		sender.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "Name : Pex Group : Minecraft Team Name : Discord Role ID");
		
		for(Prefab prefab : PexTeamLinker.PREFABS.getPrefabs()) {
			sender.sendMessage(PexTeamLinker.getPluginPrefix() + prefab.getName() + " : " + prefab.getPexGroup() + " : " + prefab.getMinecraftTeam() + " : " + prefab.getRoleId());
		}
		
		return true;
	}
	
}
