package net.mynym.lesampledata.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import net.mynym.lesampledata.processing.Graphable;

public class ContextRepo implements Graphable {
	public Map<Integer, Context> contexts = new HashMap<>();

	public Context addNewContext() {
		Context c = new Context(true);
		put(c);
		return c;
	}

	public String listContexts() {
		StringBuilder s = new StringBuilder();
		for (Context c : contexts.values()) {
			s.append(c.toLine());
		}
		return s.toString();
	}

	public String printHeader() {
		StringBuilder line = new StringBuilder();
		line.append("id" + "\t");
		line.append("name" + "\t");
		line.append("type" + "\t");
		line.append("location" + "\t");
		line.append("team" + "\t");
		line.append("initiationDate" + "\t");
		line.append("finalisationDate" + "\t");
		line.append("finalStatus" + "\r\n");
		return line.toString();
	}

	public void put(Context c) {
		contexts.put(c.id, c);
	}

	public Context get(Integer key) {
		return contexts.get(key);
	}

	public void loadFromFile() throws FileNotFoundException, IOException {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader("Contexts.txt"))) {
			while ((line = br.readLine()) != null) {
				String[] splitArray = line.split("\\t", 20);
				Context c = new Context();
				c.id = Integer.parseInt(splitArray[0]);
				c.name = splitArray.length > 1 ? splitArray[1] : null;
				c.type = splitArray.length > 2 ? splitArray[2] : null;
				c.team = splitArray.length > 3 ? splitArray[3] : null;
				c.initiationDate = splitArray.length > 4 ? splitArray[4] : null;
				c.finalisationDate = splitArray.length > 5 ? splitArray[5] : null;
				c.finalStatus = splitArray.length > 6 ? splitArray[6] : null;
				put(c);
			}
		}
	}

	@Override
	public void graph(GraphDatabaseService db) {
		for (Context c: contexts.values()) {
			c.graph(db);
		}
	}

	@Override
	public void setGraphNode(Node graphNode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getGraphNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
