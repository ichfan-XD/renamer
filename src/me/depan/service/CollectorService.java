package me.depan.service;

import java.io.File;


public class CollectorService {
	public File[] getFilesList(String sourcePath) {
		File[] rawListOfFiles;
		File folder = new File(sourcePath);
		rawListOfFiles = folder.listFiles();	
		return rawListOfFiles;
	}
}
