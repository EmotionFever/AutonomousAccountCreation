package netflixcreator.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("NetflixCreatorApp.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
	        primaryStage.setTitle("Netflix Creator");
			primaryStage.setScene(scene);
			primaryStage.setAlwaysOnTop(true);
	        primaryStage.setX(0);
	        primaryStage.setY(0);
	        //primaryStage.setOnShowing((WindowEvent e) -> (new MainController()).initiateApp());
			primaryStage.show();
			(new MainController()).initiateApp();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
