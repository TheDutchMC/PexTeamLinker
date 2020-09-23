package nl.thedutchmc.PexTeamLinker.prefabs;

import java.util.ArrayList;
import java.util.List;

import nl.thedutchmc.PexTeamLinker.ConfigurationHandler;
import nl.thedutchmc.PexTeamLinker.PexTeamLinker;

public class Prefabs {

	private static List<Prefab> prefabs = new ArrayList<>();
	private static StorageHandler prefabStorageHandler = new StorageHandler();
	
	public void loadPrefabsFromStorage() {
		prefabStorageHandler.readPrefabs();
	}
	
	//Create a new prefab. We don't check if it exists.
	public void createPrefab(String prefabName, String pexGroupName, String minecraftTeamName, String roleId) {
		
		Prefab prefab = new Prefab(prefabName, pexGroupName, minecraftTeamName, roleId);
		prefabs.add(prefab);
		
		prefabStorageHandler.writePrefabs();
		
		ConfigurationHandler.availablePrefabNames.add(prefabName);
		PexTeamLinker.CONFIG.setAvailablePrefabs();
	}
	
	public List<Prefab> getPrefabs() {
		return prefabs;
	}
	
	public void removePrefab(Prefab prefab) {
		prefabs.remove(prefab);
		
		prefabStorageHandler.removePrefab(prefab.getName());
		
		ConfigurationHandler.availablePrefabNames.remove(prefab.getName());
		PexTeamLinker.CONFIG.setAvailablePrefabs();
	}
	
	public void setPrefabs(List<Prefab> prefabs) {
		Prefabs.prefabs = prefabs;
	}
}
