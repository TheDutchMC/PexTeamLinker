package nl.thedutchmc.PexTeamLinker.authentication;

import java.io.Serializable;
import java.util.UUID;

public class PlayerProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UUID uuid;
	private String discordNameAsTagged;
	
	public PlayerProfile(UUID uuid, String discordNameAsTagged) {
		this.uuid = uuid;
		this.discordNameAsTagged = discordNameAsTagged;
	}
	
	public UUID getUuid() {
		return this.uuid;
	}
	
	public String getDiscordNameAsTagged() {
		return this.discordNameAsTagged;
	}
}
