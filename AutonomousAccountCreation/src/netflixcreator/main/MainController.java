package netflixcreator.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import netflixcreator.app.facade.handers.CreateNetflixAccountHandler;
import netflixcreator.utils.Pair;

public class MainController {
	
	@FXML private Button tblMainParts;
	
	public void initiateApp()  {
		CreateNetflixAccountHandler cnah = CreateNetflixAccountHandler.getInstance();
		
		System.out.println("CREATING EMAIL");
		cnah.createEmail("pedromar");
	}
	
	public void nextSteps(ActionEvent a) {
		// Platform.runLater is used to avoid freeze of the window
	    Platform.runLater(() -> {
	    	CreateNetflixAccountHandler cnah = CreateNetflixAccountHandler.getInstance();
	    	
	    	System.out.println("CREATING CARD...");
	    	cnah.createCard("Netflix","ABCDEF"); // TODO: Type MBWay Password
	    	
	    	System.out.println("CREATING NETFLIX ACCOUNT...");
	    	Pair<String, String> usernamePassword = cnah.createNetflix("123456");
	    	
	    	System.out.println("NETFLIX ACCOUNT CREATED SUCCESSFULLY");
	    	System.out.println("Username: " + usernamePassword.first);
	    	System.out.println("Password: " + usernamePassword.second);
	    });
	}
}
