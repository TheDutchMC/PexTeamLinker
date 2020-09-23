package nl.thedutchmc.PexTeamLinker.discord.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.Verification;

public class PrivateMessageReceived extends ListenerAdapter {

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String message = event.getMessage().getContentDisplay();
		
		Player verifiedPlayer = Bukkit.getPlayer(Verification.getPlayer(message));
		if(verifiedPlayer == null) {
			event.getChannel().sendMessage("Unknown code!").queue();
			return;
		}
		
		Verification.removePendingVerification(message);
		
		String author = event.getAuthor().getAsTag();
		
		PexTeamLinker.logWarn(verifiedPlayer.getName());
		
		Verification.addVerifiedPlayer(author, verifiedPlayer.getUniqueId());
		
		event.getChannel().sendMessage("Authenticated with the Minecraft user ``" + verifiedPlayer.getName() + "``!").queue();
		verifiedPlayer.sendMessage(PexTeamLinker.getPluginPrefix() + ChatColor.GOLD + "You have been verified with the Discord user " + ChatColor.RED + author + ChatColor.GOLD + "!");
	}
}
