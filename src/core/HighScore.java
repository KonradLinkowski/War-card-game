package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class HighScore {

	private String name;
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public static String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private int score;
	private final static String url = "src/highscore.chj";
	
	
	public HighScore(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public static void saveData (HighScore...highScores) {
		for (HighScore highScore : highScores) {
			String x = highScore.name + " " + highScore.score + "\n";
			try {
			    Files.write(Paths.get(url), x.getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	}
	
	public static void saveData (String url, HighScore...highScores) {
		for (HighScore highScore : highScores) {
			String x = highScore.name + " " + highScore.score + "\n";
			try {
			    Files.write(Paths.get(url), x.getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	}
	
	public static List <HighScore> loadData () {
		String line;
		String[] parts;
		List <HighScore> highScores = new LinkedList <HighScore> ();
	    InputStream fis = null;
		try {
			fis = new FileInputStream(url);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	    BufferedReader br = new BufferedReader(isr);
	    try {
			while ((line = br.readLine()) != null) {
			    parts = line.split(" ");
			    highScores.add(new HighScore(parts[0], Integer.parseInt(parts[1])));
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for (int i = 0; i < highScores.size(); i++) {
	    	System.out.println(highScores.get(i).name);
	    }
		return highScores;
	}
	
	public static List <HighScore> loadData (String url) {
		String line;
		String[] parts;
		List <HighScore> highScores = new LinkedList <HighScore> ();
	    InputStream fis = null;
		try {
			fis = new FileInputStream(url);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	    BufferedReader br = new BufferedReader(isr);
	    try {
			while ((line = br.readLine()) != null) {
			    parts = line.split(" ");
			    highScores.add(new HighScore(parts[0], Integer.parseInt(parts[1])));
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for (int i = 0; i < highScores.size(); i++) {
	    	System.out.println(highScores.get(i).name);
	    }
		return highScores;
	}
	
	public String toString () {
		return name + " " + score;
	}

}
