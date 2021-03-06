package net.mynym.lesampledata.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FemaleFirstNames {
	public List<String> theNames = new ArrayList<>(2100);

	public FemaleFirstNames() {
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\FemaleFirstNames.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				theNames.add(line);
			}
			System.out.println("Successfully loaded " + theNames.size() + " female first names");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
