package nl.thedutchmc.PexTeamLinker.minecraftEvents;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.authentication.Authentication;

public class PlayerLoginEventListener implements Listener {

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		
		UUID uuid = event.getPlayer().getUniqueId();
		
		if(!Authentication.getVerifiedPlayers().containsKey(uuid)) {
			PexTeamLinker.logInfo("Player " + event.getPlayer().getName() + " is not verified.");
			
			String code = Authentication.generateCodeForPlayer(event.getPlayer());
			
			event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.GOLD + "You must authenticate yourself with Discord. Please send a DM to " + ChatColor.RED + ConfigurationHandler.botNameAndTag + ChatColor.GOLD + " with the code: " + ChatColor.RED + code);
		}
	}
}
