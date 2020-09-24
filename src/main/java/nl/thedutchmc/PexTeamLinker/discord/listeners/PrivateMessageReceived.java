package nl.thedutchmc.PexTeamLinker.discord.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.authentication.Authentication;

public class PrivateMessageReceived extends ListenerAdapter {

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		
		if(!ConfigurationHandler.isMainServer) {
			return;
		}
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String message = event.getMessage().getContentDisplay();
		
		if(!Authentication.getPendingVerifications().containsKey(message)) {
			event.getChannel().sendMessage("Unknown code!").queue();
			return;
		}
		
		UUID verifiedPlayerUuid = Authentication.getPlayer(message);
		
		Authentication.removePendingVerification(message);
		
		String author = event.getAuthor().getId();
		
		PexTeamLinker.logInfo("Player with UUID " + verifiedPlayerUuid + " has linked their Discord to Minecraft.");
		
		Authentication.addVerifiedPlayer(author, verifiedPlayerUuid);
		
		Player verifiedPlayer = Bukkit.getPlayer(verifiedPlayerUuid);
		
		//If the player isnt online, verifiedPlayer will be null.
		if(verifiedPlayer != null) {
			event.getChannel().sendMessage("Authenticated with the Minecraft user ``" + verifiedPlayer.getName() + "``!").queue();
			verifiedPlayer.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "You have been authenticated with the Discord user " + ChatColor.RED + event.getAuthor().getAsTag() + ChatColor.GOLD + "!");
		} else {
			event.getChannel().sendMessage("Authenticated with Minecraft!").queue();
		}
		
	}
}
