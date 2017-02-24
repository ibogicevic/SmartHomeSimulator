package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	/** Name of the application */
	private final static String APPNAME = "SmartHomeSimulator";

    // gui areas
	public static ToolbarArea toolbarArea = new ToolbarArea();
	public static HomeArea homeArea = new HomeArea();
	public static RulesArea rulesArea = new RulesArea();
	public static StatusArea statusArea = new StatusArea();

	// remember stage for subwindows
	private Stage primaryStage;
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	@Override
	public void start(Stage primaryStage) {
		// remember stage for subwindows
		this.primaryStage = primaryStage;
		
		// lrSplitPane
		SplitPane lrSplitPane = new SplitPane();
		lrSplitPane.getItems().addAll(rulesArea, homeArea);
		lrSplitPane.setDividerPositions(0.5f, 0.5f);
		
		// add all areas
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(toolbarArea);
		mainPane.setCenter(lrSplitPane);
		mainPane.setBottom(statusArea);

		// show main pane
		Scene scene = new Scene(mainPane);
		primaryStage.setMaximized(true);
		primaryStage.setTitle(APPNAME);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
