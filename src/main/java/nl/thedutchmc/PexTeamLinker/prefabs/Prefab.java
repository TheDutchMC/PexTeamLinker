package nl.thedutchmc.PexTeamLinker.prefabs;

import java.io.Serializable;

public class Prefab implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name, pexGroup, minecraftTeam, roleId;
	
	public Prefab(String name, String pexGroup, String minecraftTeam, String roleId) {
		this.name = name;
		this.pexGroup = pexGroup;
		this.minecraftTeam = minecraftTeam;
		this.roleId = roleId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPexGroup() {
		return this.pexGroup;
	}
	
	public String getMinecraftTeam() {
		return this.minecraftTeam;
	}
	
	public String getRoleId() {
		return this.roleId;
	}
}
