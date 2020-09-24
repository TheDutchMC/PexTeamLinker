package nl.thedutchmc.PexTeamLinker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler {

	public static String discordToken, botNameAndTag, discordGuildId, customAuthConfigurationPath;
	public static boolean isMainServer, useCustomAuthConfigPath;
	
	public static List<String> availablePrefabNames;
	
	private File file;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void loadConfig() {
		file = new File(PexTeamLinker.INSTANCE.getDataFolder(), "config.yml");
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			PexTeamLinker.INSTANCE.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		
		try {
			config.load(file);
			readConfig();
		} catch (InvalidConfigurationException e) {
			PexTeamLinker.logWarn("Invalid config.yml!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readConfig() {
		discordToken = this.getConfig().getString("discordToken");
		botNameAndTag = this.getConfig().getString("botNameAndTag");
		discordGuildId = this.getConfig().getString("discordGuildId");
		useCustomAuthConfigPath = this.getConfig().getBoolean("useCustomAuthConfigPath");
		customAuthConfigurationPath = (useCustomAuthConfigPath) ? this.getConfig().getString("customAuthConfigurationPath") : PexTeamLinker.INSTANCE.getDataFolder()  + File.separator + "PlayerProfiles";
		isMainServer = this.getConfig().getBoolean("isMainServer");
		
		availablePrefabNames = this.getConfig().getStringList("availablePrefabs");
		
		//Verified Players
		/*List<String> verifiedPlayers = this.getConfig().getStringList("verifiedPlayers");
		for(String str : verifiedPlayers) {
			String[] parts = str.split("<-#->");
						
			UUID playerUuid = UUID.fromString(parts[1]);
			
			Authentication.addVerifiedPlayerInitialize(parts[0], playerUuid);
		}*/
	}
	
	/*public void setVerifiedPlayer() {
		
		List<String> verifiedPlayers = new ArrayList<>();
		for(Map.Entry<UUID, String> entry : Authentication.getVerifiedPlayers().entrySet()) {
			String discord = entry.getValue();
			UUID uuid = entry.getKey();
			
			verifiedPlayers.add(discord + "<-#->" + uuid);
		}
		
		this.getConfig().set("verifiedPlayers", verifiedPlayers);
		
		try {
			this.getConfig().save(file);
		} catch (IOException e) {
			PexTeamLinker.logWarn("There was an error saving the configuration! Errors may occur!");
		}
	}*/
	
	public void setAvailablePrefabs() {
		
		this.getConfig().set("availablePrefabs", availablePrefabNames);
		
		try {
			this.getConfig().save(file);
		} catch (IOException e) {
			PexTeamLinker.logWarn("There was an error saving the configuration! Errors may occur!");
		}
	}
}
