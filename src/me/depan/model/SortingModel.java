package me.depan.model;

public class SortingModel {
	private String artistName;
	private String mainFolder;
	private String subFolder;
	private String oldPath;
	private String newPath;
	public String getOldPath() {
		return oldPath;
	}
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}
	public String getNewPath() {
		return newPath;
	}
	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getMainFolder() {
		return mainFolder;
	}
	public void setMainFolder(String mainFolder) {
		this.mainFolder = mainFolder;
	}
	public String getSubFolder() {
		return subFolder;
	}
	public void setSubFolder(String subFolder) {
		this.subFolder = subFolder;
	}
}
