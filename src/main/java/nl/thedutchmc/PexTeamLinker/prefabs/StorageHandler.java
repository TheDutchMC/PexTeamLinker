package nl.thedutchmc.PexTeamLinker.prefabs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class StorageHandler {

	public void writePrefabs() {
		
		for(Prefab prefab : PexTeamLinker.PREFABS.getPrefabs()) {
			
			try {				
				File outFile = new File(PexTeamLinker.INSTANCE.getDataFolder() + File.separator + "prefabs" + File.separator + prefab.getName() + ".prefab");
				
				if(!outFile.exists()) {
					outFile.getParentFile().mkdirs();
					outFile.createNewFile();
				}
				
				FileOutputStream fos = new FileOutputStream(PexTeamLinker.INSTANCE.getDataFolder() + File.separator + "prefabs" + File.separator + prefab.getName() + ".prefab");
				ObjectOutputStream out = new ObjectOutputStream(fos);
				
				out.writeObject(prefab);
				
				out.close();
				fos.close();
				
			} catch (IOException e) {
				PexTeamLinker.logWarn("Something went wrong whilst saving the prefab " + prefab.getName() + "! Errors may occur!");
				e.printStackTrace();
			}
		}
	}
	
	public void readPrefabs() {
		
		List<Prefab> prefabs = new ArrayList<>();
		
		for(String name : ConfigurationHandler.availablePrefabNames) {
			
			Prefab prefab = null;
			
			try {
				FileInputStream fis = new FileInputStream(PexTeamLinker.INSTANCE.getDataFolder() + File.separator + "prefabs" + File.separator + name + ".prefab");
				ObjectInputStream in = new ObjectInputStream(fis);
				
				prefab = (Prefab) in.readObject();
				
				in.close();
				fis.close();
				
				prefabs.add(prefab);
				
			} catch(IOException | ClassNotFoundException e) {
				PexTeamLinker.logWarn("Something went wrong whilst loading the prefab " + name + "! Errors may occur!");
				e.printStackTrace();
			}
		}
		
		PexTeamLinker.PREFABS.setPrefabs(prefabs);
	}
	
	public void removePrefab(String prefabName) {
		
		File toRemovePrefabFile = new File(PexTeamLinker.INSTANCE.getDataFolder() + File.separator + "prefabs" + File.separator + prefabName + ".prefab");
		
		boolean removeSuccessfully = toRemovePrefabFile.delete();
		
		if(!removeSuccessfully) {
			PexTeamLinker.logWarn("Unable to remove " + prefabName + "! Errors may occur!");
		}
		
	}
}
