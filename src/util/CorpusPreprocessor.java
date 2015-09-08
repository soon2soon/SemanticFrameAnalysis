package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import parse.ConllParser;

public class CorpusPreprocessor {

	OpenNLPSentenceDetector sentDetect;
	ConllParser parser;

	public CorpusPreprocessor() {

		// sentence tokenization
		sentDetect = new OpenNLPSentenceDetector();
		parser = new ConllParser();

	}

	public final static String dataPath_deceptive_ott = "data/deceptive_from_MTurk";
	public final static String dataPath_truthful_ott = "data/truthful_from_TripAdvisor";
	
	public final static String dataPath_deceptive_jiwei_hotel_positive_turker = "data/deception_dataset_jiwei/hotel/positive/deceptive_turker";
	public final static String dataPath_deceptive_jiwei_hotel_positive_expert = "data/deception_dataset_jiwei/hotel/positive/deceptive_expert";
	public final static String dataPath_truthful_jiwei_hotel_positive = "data/deception_dataset_jiwei/hotel/positive/truthful";
	
	public final static String dataPath_deceptive_jiwei_hotel_negative_turker = "data/deception_dataset_jiwei/hotel/negative/deceptive_turker";
	public final static String dataPath_deceptive_jiwei_hotel_negative_expert = "data/deception_dataset_jiwei/hotel/negative/deceptive_expert";
	public final static String dataPath_truthful_jiwei_hotel_negative = "data/deception_dataset_jiwei/hotel/negative/truthful";
	
	
	public final static String dataPath_deceptive_yelp = "data/deceptive_from_Yelp";
	public final static String dataPath_truthful_yelp = "data/truthful_from_Yelp";
	
	

	public static void main(String[] args) {

		CorpusPreprocessor cPreprocessor = new CorpusPreprocessor();

//		cPreprocessor.processFiles(dataPath_deceptive_ott);
//		cPreprocessor.processFiles(dataPath_truthful_ott);
		
//		cPreprocessor.processFiles(dataPath_deceptive_jiwei_hotel_positive_turker);
//		cPreprocessor.processFiles(dataPath_deceptive_jiwei_hotel_positive_expert);
//		cPreprocessor.processFiles(dataPath_truthful_jiwei_hotel_positive);
//		cPreprocessor.processFiles(dataPath_deceptive_jiwei_hotel_negative_turker);
//		cPreprocessor.processFiles(dataPath_deceptive_jiwei_hotel_negative_expert);
//		cPreprocessor.processFiles(dataPath_truthful_jiwei_hotel_negative);
		
		
		cPreprocessor.processFiles(dataPath_deceptive_yelp);
		cPreprocessor.processFiles(dataPath_truthful_yelp);
		
		
	}

	public void processFiles(String dataPath) {

		Set<File> files = (Set<File>) listFileTree(new File(dataPath));

		BufferedWriter bWriter;
		BufferedReader bReader;

		for (File file : files) {

			System.out.println("processing " + file.getName());
			String tmpConllPath = file.getPath().replace("data", "data\\conll");
			// System.out.println(tmpConllPath);

			try {
				bReader = new BufferedReader(new FileReader(new File(file.getPath())));
				String[] sentences = sentDetect.detector(bReader.readLine());

				File newConllFile = new File(tmpConllPath);

				// create directory if not exists
				newConllFile.getParentFile().mkdirs();
				bWriter = new BufferedWriter(new FileWriter(newConllFile));

				// split sentences for parsing
				for (String sentence : sentences) {

					// do parse for each sentence ad CONLL format
					bWriter.write(parser.doParseinConllFormat(sentence));
					bWriter.write('\n');
				}

				bWriter.close();
			} catch (Exception e) {

			}

		}
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
