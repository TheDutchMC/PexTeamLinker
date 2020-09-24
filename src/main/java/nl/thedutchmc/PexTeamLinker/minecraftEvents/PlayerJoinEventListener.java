package nl.thedutchmc.PexTeamLinker.minecraftEvents;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.authentication.Authentication;
import nl.thedutchmc.PexTeamLinker.prefabs.Prefab;

public class PlayerJoinEventListener implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		UUID uuid = event.getPlayer().getUniqueId();
		
		if(!Authentication.getVerifiedPlayers().containsKey(uuid)) {
			PexTeamLinker.logInfo("Player " + event.getPlayer().getName() + " is not verified.");
			
			event.getPlayer().sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "You have not linked your discord yet. Please use " + ChatColor.RED + "/auth" + ChatColor.GOLD + " to link your Discord.");
			
			return;
		}
		
		String discordNameAsId = Authentication.getVerifiedPlayers().get(uuid);
		
		Guild guild = PexTeamLinker.JDA.getJda().getGuildById(ConfigurationHandler.discordGuildId);
				
		AtomicReference<Member> atomicMember = new AtomicReference<>();
		
		//Code is async
		guild.retrieveMemberById(discordNameAsId).queue(member -> {
			atomicMember.set(member);
			
			//Sync with the main Bukkit Thread
			new BukkitRunnable() {
				@Override
				public void run() {
					handleRole(member, event.getPlayer().getName());
				}
			}.runTask(PexTeamLinker.INSTANCE);
		});
	}	
	
	private void handleRole(Member m, String playerName) {
				
		Prefab prefab = null;
		roleIterator: for(Role r : m.getRoles()) {
			for(Prefab p : PexTeamLinker.PREFABS.getPrefabs()) {
				if(p.getRoleId().equals(r.getId())) {
					prefab = p;
					break roleIterator;
				}
			}		
		}
		
		if(prefab == null) {
			PexTeamLinker.logInfo("No Prefab found for the user " + playerName + "!");
			return;
		}
		
		//Pex Group.
		if(Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + playerName + " group set " + prefab.getPexGroup());
		} else {
			PexTeamLinker.logWarn("PermissionsEx not found. Not setting group for player " + playerName);
		}
		
		//Minecraft team
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team join " + prefab.getMinecraftTeam() + " " + playerName);
	}
}
