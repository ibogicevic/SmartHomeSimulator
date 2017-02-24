package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ToolbarArea extends ToolBar {
	
	private void showInfoDialog() {
		// info text
		final Text infoText = new Text("SmartHomeSimulator v0.1 \n (c) 2017 Dr. Ivan Bogicevic");
		// init dialog
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        // init close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialog.close());
        // init content
        VBox vbox = new VBox(20);
        vbox.getChildren().add(infoText);
        vbox.getChildren().add(closeButton);
        Scene dialogScene = new Scene(vbox, 400, 250);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	public ToolbarArea() {
		// initialize buttons
		Button startButton = new Button("Start");
		Button stopButton = new Button("Stop");
		Button infoButton = new Button("Info");
		Button exitButton = new Button("Exit");
		// actionlisteners
		//startButton.setOnAction(e -> );
		infoButton.setOnAction(e -> showInfoDialog());
		exitButton.setOnAction(e -> System.exit(0));
		// add all buttons
		this.getItems().add(startButton);
		this.getItems().add(stopButton);
		this.getItems().add(infoButton);
		this.getItems().add(exitButton);
	}
}