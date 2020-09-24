package nl.thedutchmc.PexTeamLinker.discord;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.discord.listeners.PrivateMessageReceived;

public class JdaHandler {

	private static JDA jda;
	
	public JdaHandler(String token) {
		
		try {
			jda = JDABuilder.createDefault(token).build();
			jda.awaitReady();
			
		} catch(LoginException e) {
			PexTeamLinker.logWarn("Unable to login to Discord. Please check the token and your internet connection! Errors may occur from now on!");
			PexTeamLinker.logWarn("Shutting down plugin.");
			
			Bukkit.getPluginManager().disablePlugin(PexTeamLinker.INSTANCE);
			return;
			
		} catch(InterruptedException e) {
			PexTeamLinker.logWarn("JDA login process got interrupted. Errors may occur from now on!");
			PexTeamLinker.logWarn("Shutting down plugin.");
			
			Bukkit.getPluginManager().disablePlugin(PexTeamLinker.INSTANCE);
			return;
		}
		
		jda.addEventListener(new PrivateMessageReceived());
		
		PexTeamLinker.logInfo("JDA setup complete.");
	}
	
	public JDA getJda() {
		return jda;
	}
	
	public static void shutdownJda() throws Exception {
		jda.shutdownNow();
	}
}
