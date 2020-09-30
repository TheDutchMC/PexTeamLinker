package nl.thedutchmc.PexTeamLinker.discord;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;
import nl.thedutchmc.PexTeamLinker.discord.listeners.PrivateMessageReceived;

public class JdaHandler {

	private static JDA jda;
	
	public JdaHandler(String token) {
		
		List<GatewayIntent> intents = new ArrayList<>();
		intents.add(GatewayIntent.GUILD_MESSAGES);
		intents.add(GatewayIntent.DIRECT_MESSAGES);
		
		try {
			jda = JDABuilder.createDefault(token)
					.enableIntents(intents)
					.build();
			
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
