package questionparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DataParser {
	public static void main(String[] args) {
		BufferedReader br;
		BufferedWriter bw;
		final int QUESTION_COUNT = 110;
		String[] questions = new String[QUESTION_COUNT];
		Scanner scanIn = new Scanner(System.in);
		
		
		// This basepath is used to obtain the location of the input resource file
		String filePath = new File("").getAbsolutePath();
		// Define input and output files
		String inputFile = "/classes/Questions.txt";
		String outputFile = "collapsedquestions.txt";
		
		// Optional output filename can be passed as CLI argument
		if (args.length == 1) {
			outputFile = args[0];
		}
		
		// Begin application
		System.out.println("Beginning question parser....");
		
		try {
			System.out.println("=====================================================");
			System.out.println("LOADING INPUT FILE:  "+ inputFile);
			br = new BufferedReader(new FileReader(filePath+inputFile));
			System.out.println("PREPARING OUTPUT FILE: "+ outputFile);
			bw = new BufferedWriter(new FileWriter(outputFile));
			System.out.println("=====================================================");
			
			
			//Parse all lines from the input document that start with a number (these are probably the ones that are questions)
			//Store them in an array (so that they can be optionally randomized)
			String line = br.readLine();
			int index = 0;
			while ((line = br.readLine()) != null) {
				if (line.matches("^\\d*")) {
					line = br.readLine();	// for some reason, it's finding a match on the line BEFORE the line we need, so just take in the next line
					questions[index++] = line;
				}
			}
			
			System.out.println(index +" questions found.");
			
			// Convert array to list to allow shuffling
			List<String> questionList = Arrays.asList(questions);
			
			
			// OPTIONAL : Randomize the order of the questions
			System.out.println("Would you like to randomize the order? (Y/N) ");
			String response = scanIn.nextLine();
			if (response.equalsIgnoreCase("Y"))
				Collections.shuffle(questionList);
			
			
			// Write all the questions to a new file
			System.out.println("Outputting text to "+outputFile);
			
			bw.append("=======================================\n");
			bw.append("\tQuestion Parser Output\n");
			bw.append("=======================================\n");
			for (String question : questionList) {
				if (question != null)
					bw.append(question + "\n\n");
			}
			br.close();
			bw.close();
			scanIn.close();
			System.out.println("Operation complete. Closing files");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}