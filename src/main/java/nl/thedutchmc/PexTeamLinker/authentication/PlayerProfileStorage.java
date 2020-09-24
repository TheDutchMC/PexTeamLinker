package nl.thedutchmc.PexTeamLinker.authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;

import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class PlayerProfileStorage {

	public void load() {
		
		List<String> discoveredProfiles = discoverProfiles();
		
		List<PlayerProfile> playerProfiles = new ArrayList<>();
		
		if(discoveredProfiles == null) {
			return;
		}
		
		for(String profileName : discoveredProfiles) {
			
			PlayerProfile playerProfile = null;
			
			try {
				FileInputStream fis = new FileInputStream(profileName);
				ObjectInputStream in = new ObjectInputStream(fis);
				
				playerProfile = (PlayerProfile) in.readObject();
				
				in.close();
				fis.close();
				
				playerProfiles.add(playerProfile);
			} catch(IOException | ClassNotFoundException e) {
				PexTeamLinker.logWarn("Something went wrong whilst loading the Player Profile " + profileName + "! Errors may occur!");
				e.printStackTrace();
			}
		}
		
		Authentication.setPlayerProfiles(playerProfiles);	
		
	}
	
	public void write(PlayerProfile profile) {
		
		try {
			
			File outFile = new File(ConfigurationHandler.customAuthConfigurationPath + File.separator + String.valueOf(profile.getUuid()) + ".playerprofile");
			
			if(!outFile.exists()) {
				outFile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(ConfigurationHandler.customAuthConfigurationPath + File.separator + String.valueOf(profile.getUuid()) + ".playerprofile");

			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			out.writeObject(profile);
			
			out.close();
			fos.close();
			
		} catch (IOException e) {
			PexTeamLinker.logWarn("Something went wrong whilst saving the PlayerProfile " + profile.getUuid() + "! Errors may occur!");
			e.printStackTrace();
		}
	}

	@Nullable
	private List<String> discoverProfiles() {
		File profileFolder = new File(ConfigurationHandler.customAuthConfigurationPath);
			
		if(!profileFolder.exists()) {
			PexTeamLinker.logInfo("Player Profile folder does not exist. Creating...");

			try {
				Files.createDirectories(Paths.get(profileFolder.getAbsolutePath()));
			} catch(SecurityException e) {
				PexTeamLinker.logWarn("A SecurityException was thrown whilst attempting to create the PlayerProfile folder. Please check the permissions for this folder. Disabling plugin. Errors may occur from now on!");
				Bukkit.getPluginManager().disablePlugin(PexTeamLinker.INSTANCE);
				
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			PexTeamLinker.logInfo("Player Profile folder created at: " + profileFolder.getAbsolutePath());
		}
		
		try (Stream<Path> walk = Files.walk(Paths.get(profileFolder.getAbsolutePath()))) {

			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".playerprofile")).collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			PexTeamLinker.logWarn("An IOException occured whilst attempting to discover player profiles. Errors may occur from now on!");
			e.printStackTrace();
			return null;
		}		
	}
}