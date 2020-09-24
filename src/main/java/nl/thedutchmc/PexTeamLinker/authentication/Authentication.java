package nl.thedutchmc.PexTeamLinker.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

public class Authentication {
	
	private static HashMap<String, UUID> pendingVerifications = new HashMap<>();
	private static HashMap<UUID, String> verifiedPlayers = new HashMap<>();
	private static List<PlayerProfile> playerProfiles = new ArrayList<>(); 

	private static PlayerProfileStorage STORAGE = new PlayerProfileStorage();
	
	public Authentication() {
		STORAGE.load();
		
		for(PlayerProfile profile : playerProfiles) {
			verifiedPlayers.put(profile.getUuid(), profile.getDiscordNameAsTagged());
		}
	}
	
	public static HashMap<String, UUID> getPendingVerifications() {
		return pendingVerifications;
	}
	
	@Nullable
	public static UUID getPlayer(String code) {
		if(pendingVerifications.containsKey(code)) {
			return pendingVerifications.get(code);
		} else {
			return null;
		}
	}
	
	public static String generateCodeForPlayer(Player player) {
		final Random rnd = new Random();
		final int n = 100000 + rnd.nextInt(900000);
		
		pendingVerifications.put(String.valueOf(n), player.getUniqueId());
		
		return String.valueOf(n);
	}
	
	public static void removePendingVerification(String code) {
		pendingVerifications.remove(code);
	}
	
	public static void addVerifiedPlayer(String discord, UUID uuid) {
		verifiedPlayers.put(uuid, discord);
		//PexTeamLinker.CONFIG.setVerifiedPlayer();
		
		PlayerProfile profile = new PlayerProfile(uuid, discord);
		playerProfiles.add(profile);
		
		STORAGE.write(profile);
		
	}
	
	/*public static void addVerifiedPlayerInitialize(String discord, UUID uuid) {
		verifiedPlayers.put(uuid, discord);
	}*/
	
	public static HashMap<UUID, String> getVerifiedPlayers() {
		return verifiedPlayers;
	}
	
	public static void setPlayerProfiles(List<PlayerProfile> profiles) {
		playerProfiles = profiles;
	}
	
	public static List<PlayerProfile> getPlayerProfiles() {
		return playerProfiles;
	}
	
}
