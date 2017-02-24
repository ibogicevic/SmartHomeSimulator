package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HomeArea extends BorderPane {

	protected Slider leftSlider = new Slider();
	protected Slider bottomSlider = new Slider();
	protected Slider topSlider = new Slider();
	protected Slider rightSlider = new Slider();

	private Canvas layer = new Canvas(500, 500);
	private GraphicsContext gc = layer.getGraphicsContext2D();

	private void addSliders() {

		// left slider
		leftSlider.setMin(0);
		leftSlider.setMax(100);
		leftSlider.setValue(0);
		leftSlider.setShowTickLabels(true);
		leftSlider.setShowTickMarks(true);
		leftSlider.setMajorTickUnit(10);
		leftSlider.setMinorTickCount(5);
		leftSlider.setBlockIncrement(10);
		leftSlider.setOrientation(Orientation.VERTICAL);
		leftSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {
				redrawScene();
			}
		});

		// bottom slider
		bottomSlider.setMin(0);
		bottomSlider.setMax(100);
		bottomSlider.setValue(20);
		bottomSlider.setShowTickLabels(true);
		bottomSlider.setShowTickMarks(true);
		bottomSlider.setMajorTickUnit(10);
		bottomSlider.setMinorTickCount(5);
		bottomSlider.setBlockIncrement(10);
		bottomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {
				redrawScene();
			}
		});

		// top slider
		topSlider.setMin(0);
		topSlider.setMax(100);
		topSlider.setValue(50);
		topSlider.setShowTickLabels(true);
		topSlider.setShowTickMarks(true);
		topSlider.setMajorTickUnit(10);
		topSlider.setMinorTickCount(5);
		topSlider.setBlockIncrement(10);
		topSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {
				redrawScene();
			}
		});

		// right slider
		rightSlider.setMin(0);
		rightSlider.setMax(100);
		rightSlider.setValue(100);
		rightSlider.setShowTickLabels(true);
		rightSlider.setShowTickMarks(true);
		rightSlider.setMajorTickUnit(10);
		rightSlider.setMinorTickCount(5);
		rightSlider.setBlockIncrement(10);
		rightSlider.setOrientation(Orientation.VERTICAL);
		rightSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {
				redrawScene();
			}
		});

		// add sliders to grid (including labels of slider)
		HBox leftSliderBlock = new HBox();
		leftSliderBlock.setAlignment(Pos.CENTER);
		leftSliderBlock.getChildren().add(new Label("D"));
		leftSliderBlock.getChildren().add(leftSlider);
		this.setLeft(leftSliderBlock);
		VBox bottomSliderBlock = new VBox();
		bottomSliderBlock.setAlignment(Pos.CENTER);
		bottomSliderBlock.getChildren().add(bottomSlider);
		bottomSliderBlock.getChildren().add(new Label("C"));
		this.setBottom(bottomSliderBlock);
		VBox topSliderBlock = new VBox();
		topSliderBlock.setAlignment(Pos.CENTER);
		topSliderBlock.getChildren().add(new Label("A"));
		topSliderBlock.getChildren().add(topSlider);
		this.setTop(topSliderBlock);
		HBox rightSliderBlock = new HBox();
		rightSliderBlock.setAlignment(Pos.CENTER);
		rightSliderBlock.getChildren().add(rightSlider);
		rightSliderBlock.getChildren().add(new Label("B"));
		this.setRight(rightSliderBlock);
	}

	private void initScene() {
		// add items to grid
		Pane pane = new Pane();
		pane.getChildren().add(layer);
		this.setCenter(pane);
	}

	private void drawSun() {
		// params
		double x = 480;
		double y = 500 - (50+2*400*rightSlider.getValue()/100-400);
		// pen settings
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.YELLOW);
		gc.setLineWidth(5);
		// circle
		gc.fillOval(x-15, y-15, 30, 30);
		// shine
		gc.strokeLine(x-25, y,    x+25, y);
		gc.strokeLine(x   , y-25, x,    y+25);
		gc.strokeLine(x-18, y-18, x+18, y+18);
		gc.strokeLine(x+18, y-18, x-18, y+18);
	}

	private void drawMoon() {
		// params
		double x = 480;
		double y = (50+2*400*rightSlider.getValue()/100);
		Color skyColor = getSkyColor(rightSlider.getValue());
		// outer circle
		gc.setFill(Color.YELLOW);
		gc.fillOval(x-15, y-15, 30, 30);
		// inner circle
		gc.setFill(skyColor);
		gc.fillOval(x-25, y-15+1, 28, 28);		
	}

	private void drawCloud() {
		double cloudPosition = topSlider.getValue();
		double x = 40+4*cloudPosition;
		// pen settings
		gc.setFill(Color.DARKGRAY);
		// cloud form
		gc.fillOval(x-20, 40, 30, 30);
		gc.fillOval(x+20, 40, 30, 30);
		gc.fillRect(x, 40, 40, 30);
		gc.fillOval(x-10, 25, 25, 25);
		gc.fillOval(x+5, 25, 35, 45);
		// rain
		if (cloudPosition > 30) {
			return;
		}
		gc.setLineWidth(3);
		gc.setStroke(Color.DARKBLUE);
		gc.setLineDashes(3, 15);
		for (int rainLine = 1; rainLine<=5; rainLine++) {
			gc.strokeLine(x-10+8*rainLine, 75+rainLine, x-10+8*rainLine, 400+rainLine*4);
		}
		gc.setLineDashes(1);
	}

	private Color getSkyColor(double sunPosition) {
		int brightness = (int)Math.round(sunPosition/100*255);
		if (brightness < 25) {
			brightness = 25;
		}
		Color skyColor = Color.rgb(brightness*142/255, brightness*202/255, brightness);
		return skyColor; 
	}

	private void drawHouse() {
		// pen settings
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);
		// house body
		gc.strokeRect(300, 300, 150, 150);
		// roof
		gc.strokeLine(300, 300, 375, 200);
		gc.strokeLine(450, 300, 375, 200);
		// garage body
		gc.strokePolyline(
				new double[]{200, 200, 300, 300, 200},
				new double[]{375, 350, 350, 450, 450},
				5);
		// garage door
		if (leftSlider.getValue() >= 10 && leftSlider.getValue() <= 40) {
			// open
			gc.strokeLine(200, 385, 260, 370);
		} else {
			// close
			gc.strokeLine(200, 385, 200, 440);
		}
	}

	private void drawCar() {
		double x = 2*bottomSlider.getValue();
		// pen settings
		gc.setLineWidth(5);
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.BLACK);
		// wheels
		gc.fillOval(20+x, 433, 15, 15);
		gc.fillOval(60+x, 433, 15, 15);
		// car body
		gc.strokePolygon(
				new double[]{10+x, 90+x, 90+x, 60+x, 25+x, 10+x},
				new double[]{440,  440,  425,  410,  410,  425},
				6);
	}

	private void redrawScene() {
		// clear old scene
		gc.setFill(getSkyColor(rightSlider.getValue()));
		gc.fillRect(0, 0, 500, 500);
		// moon
		drawMoon();
		// sun
		drawSun();
		// house
		drawHouse();
		// car
		drawCar();
		// cloud
		drawCloud();
		// lamps
		drawLamps();
		// apply rules
		if (Main.rulesArea != null) {
			Main.rulesArea.applyRules(topSlider.valueProperty(), rightSlider.valueProperty(),
					bottomSlider.valueProperty(), leftSlider.valueProperty());
		}
		// check status violation
		checkStatusViolation();
	}

	private void drawFlash() {
		// show flash
		gc.setLineWidth(10);
		gc.setStroke(Color.DARKRED);
		double x = 200;
		double y = 100;
		gc.strokePolyline(
				new double[]{x, x-50, x+80, x+2},
				new double[]{y, y+80, y+60, y+178},
				4);
		gc.strokePolyline(
				new double[]{x-10,   x,     x+40},
				new double[]{y+140, y+180, y+170},
				3);
	}

	private void drawLamp(double x, double y, boolean on) {
		// pen settings
		if (on) {
			gc.setFill(Color.YELLOW);
		} else {
			gc.setFill(Color.DARKGRAY);
		}
		// bulb
		gc.fillOval(x, y, 20, 20);
	}

	private void drawLamps() {
		// garage lamp
		drawLamp(280, 360, leftSlider.getValue()>=30 && leftSlider.getValue()<=70);
		// house lamp
		drawLamp(365, 320, leftSlider.getValue()>=60 && leftSlider.getValue()<=90);
	}

	private void checkStatusViolation() {
		String statusText = "";
		if ((bottomSlider.getValue() > 50 && bottomSlider.getValue() < 95)
				&& (leftSlider.getValue() < 10 || leftSlider.getValue() > 40)) {
			// car crashes into garage
			statusText += "Collision with garage door! ";	
		}
		if (topSlider.getValue() <= 30
				&& 30*(topSlider.getValue()-14)/16 < bottomSlider.getValue()
				&& 30*(bottomSlider.getValue()-33)/(94-33) < topSlider.getValue()) {
			// car gets wet
			statusText += "Rain is falling on car! ";	
		}
		if (rightSlider.getValue() <= 40 && bottomSlider.getValue() < 95) {
			// car needs to be in garage
			statusText += "Car needs to be in garage at night! ";
		}
		if (leftSlider.getValue() > 30 && leftSlider.getValue() < 90 && rightSlider.getValue() > 40) {
			// lights are on at daytime
			statusText += "Lights are on during daytime! ";
		}
		if (leftSlider.getValue() >= 10 && leftSlider.getValue() <= 40 && topSlider.getValue() < 30) {
			// lights are on at daytime
			statusText += "Garage open while raining! ";
		}
		if (statusText.length() > 0) {
			drawFlash();
		}
		if (Main.statusArea != null) {
			Main.statusArea.setText(statusText);
		}
	}

	public HomeArea() {
		// add left and bottom slider
		addSliders();
		// initialize house and garage scene
		initScene();
		// draw house and garage scene
		redrawScene();
	}

}
