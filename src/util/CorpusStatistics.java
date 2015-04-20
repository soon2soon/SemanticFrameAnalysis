package util;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CorpusStatistics {

	public final static String dataPath_deceptive = "data/deceptive_from_MTurk";
	public final static String dataPath_truthful = "data/truthful_from_TripAdvisor";

	public CorpusStatistics() {

	}

	public static void main(String[] args) {

		CorpusStatistics cStatistics = new CorpusStatistics();

		cStatistics.corpusStatistics(dataPath_deceptive);

		
		
	}

	public void corpusStatistics(String path) {

		Set<File> files = (Set<File>) listFileTree(new File(path));

	}

	public static Collection<File> listFileTree(File dir) {
		Set<File> fileTree = new HashSet<File>();

		// iterate files recurssively
		for (File entry : dir.listFiles()) {
			if (entry.isFile()) {
				fileTree.add(entry);
			} else {
				fileTree.addAll(listFileTree(entry));
			}
		}
		return fileTree;
	}

}
