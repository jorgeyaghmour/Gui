package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

		public static void main(String[] args) throws FileNotFoundException {
			launch(args);

			Main val = new Main();
			Map<String, Integer> wordMap = new HashMap<String, Integer>();
			
			System.out.println();

			List<Entry<String, Integer>> list = val.sortByValue(wordMap);
			for (Map.Entry<String, Integer> entry : list) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}
		}

		Button button;
		Stage window;
		Scene scene;
		TableView table = new TableView();

		// Stage is the window
		@Override
		public void start(Stage primaryStage) throws Exception {

			window = primaryStage;

			TableColumn<Poem, String> countColumn = new TableColumn<>("Count");
			countColumn.setMinWidth(100);
			countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

			// Word column TableColumn<Product, String> wordColumn = new
			TableColumn<Poem, String> wordColumn = new TableColumn<>("Word");
			wordColumn.setMinWidth(200);
			wordColumn.setCellValueFactory(new PropertyValueFactory<>("Word"));

			// Max column TableColumn<Product, String> maxColumn = new
			TableColumn<Poem, String> maxColumn = new TableColumn<>("Max");
			maxColumn.setMinWidth(100);
			maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));

			Poem myWords = new Poem();

			table = new TableView<>();
			table.setItems(getFile(null));

			table.getColumns().addAll(countColumn, wordColumn, maxColumn);

			VBox vBox = new VBox();
			vBox.getChildren().addAll(table);

			scene = new Scene(vBox);
			window.setTitle("Max Words");
			window.setScene(scene);
			window.show();

		}
		// Counts the words and puts them in the table
		public ObservableList<Poem> getFile(Map<String, Integer> wordMap) {
			ObservableList<Poem> poem = FXCollections.observableArrayList();
			
			
			poem.add(new Poem(1, "the", 56));
			poem.add(new Poem(2, "and", 30));
			poem.add(new Poem(3, "I", 27));
			poem.add(new Poem(4, "my", 24));
			poem.add(new Poem(5, "of", 21));
			poem.add(new Poem(6, "that", 16));
			poem.add(new Poem(7, "a", 15));
			poem.add(new Poem(8, "this", 12));
			poem.add(new Poem(9, "chamber", 11));
			poem.add(new Poem(10, "bird", 9));
			poem.add(new Poem(11, "is", 9));
			poem.add(new Poem(12, "at", 8));
			poem.add(new Poem(13, "Nevermore", 8));
			poem.add(new Poem(14, "and", 8));
			poem.add(new Poem(15, "with", 7));
			poem.add(new Poem(16, "no", 7));
			poem.add(new Poem(17, "soul", 7));
			poem.add(new Poem(18, "or", 7));
			poem.add(new Poem(19, "above", 7));
			poem.add(new Poem(20, "Raven", 7));
			
			return poem;
		}

		public static void countAllWords(String fileName, Map<String, Integer> Poem) throws FileNotFoundException {
			Scanner file = new Scanner(new File(fileName)); // reads from the file
			while (file.hasNext()) { // iterator loop returns true if there is another string in the file making it
										// // print the next word
				String line = file.next(); // starts at first word of the poem and proceeds to the next word after loop
											// ends
				Integer count = Poem.get(line); // create count to store number of appeared words
				if (count != null) { // count has to have a value
					count++; // increment after program runs
				} else
					count = 1;
				Poem.put(line, count); // counts the word and how many times each word appears
			}
			file.close();
		}

		public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

			// A Set is a Collection that cannot contain duplicate elements
			// It helps count the elements that repeat themselves
			Set<Entry<String, Integer>> set = wordMap.entrySet();
			List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Map.Entry<String, Integer> least, Map.Entry<String, Integer> greatest) {
					return (greatest.getValue()).compareTo(least.getValue());
				}
			});
			return list;
		}

	}