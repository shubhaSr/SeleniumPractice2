package AxeTrading.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testng.Assert;


public class TestListView extends Application {
	

	public boolean isISINCodeValid(String code) {
		if (code.length() != 12) {
			System.out.println(code + " is not a valid ISIN code");
			return false;
		}
		return true;
	}
	
	@Override
	public void start(Stage displayScreen) throws Exception {
		
		/* create list object */
		ListView<String> listViewReference = new ListView<String>();
		
		String isinCodesFileName = ".\\ISINcodes.txt";
		try {
			Scanner scanner = new Scanner(new File(isinCodesFileName));
			
			while (scanner.hasNextLine()) {
				String isinCode = scanner.nextLine();
				if (isISINCodeValid(isinCode)) { 
					listViewReference.getItems().add(isinCode);
				}
			}
		} catch (FileNotFoundException exception) {
			System.out.println("File " + isinCodesFileName + " not found. Exception : " + exception.getMessage());
			throw exception;
			
		}
		
		Assert.assertEquals(listViewReference.getItems().size(), 7);
		
		/* creating vertical box to add item objects */
		VBox vBox = new VBox(listViewReference);
		/* creating scene */
		Scene scene = new Scene(vBox, 220, 270);
		/* adding scene to stage */
		displayScreen.setScene(scene);
		/* display scene for showing output */
		displayScreen.show();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException { 
		/*launch method calls internally start() method*/
		Application.launch(args);
	}
}
