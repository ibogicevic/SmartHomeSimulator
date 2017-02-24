package gui;

import java.util.List;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class RulesArea extends GridPane {

	class Rule {
		ComboBox<String> comboBox1;
		ComboBox<String> comboBox2;
		Spinner<Integer> spinner1;
		ComboBox<String> comboBox3;
		ComboBox<String> comboBox4;
		Spinner<Integer> spinner2;		
		ComboBox<String> comboBox5;
		Spinner<Integer> spinner3;		
	}

	private List<Rule> rules = new ArrayList<Rule>();

	private void drawLine(int row) {
		// create rule data
		Rule rule = new Rule();
		// add label
		this.add(new Label("When "), 1, row);
		// add slider choice
		ObservableList<String> options1 = FXCollections.observableArrayList(" ","A","B","C","D");
		rule.comboBox1 = new ComboBox<>(options1);
		rule.comboBox1.setValue(" ");
		this.add(rule.comboBox1, 2, row);
		// add comparator choice
		ObservableList<String> options2 = FXCollections.observableArrayList("<",">");
		rule.comboBox2 = new ComboBox<>(options2);
		rule.comboBox2.setValue(" ");
		this.add(rule.comboBox2, 3, row);
		// add value choice
		rule.spinner1 = new Spinner<>();
		SpinnerValueFactory<Integer> valueFactory1 = //
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50);
		rule.spinner1.setValueFactory(valueFactory1);
		rule.spinner1.setMaxWidth(80);
		this.add(rule.spinner1, 4, row);
		// add word "and"
		this.add(new Label(" and "), 5, row); 		
		// add slider 2 choice
		ObservableList<String> options3 = FXCollections.observableArrayList(" ","A","B","C","D");
		rule.comboBox3 = new ComboBox<>(options3);
		rule.comboBox3.setValue(" ");
		this.add(rule.comboBox3, 6, row);
		// add comparator choice
		ObservableList<String> options4 = FXCollections.observableArrayList("<",">");
		rule.comboBox4 = new ComboBox<>(options4);
		rule.comboBox4.setValue(" ");
		this.add(rule.comboBox4, 7, row);
		// add value choice
		rule.spinner2 = new Spinner<>();
		SpinnerValueFactory<Integer> valueFactory2 =
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50);
		rule.spinner2.setValueFactory(valueFactory2);
		rule.spinner2.setMaxWidth(80);
		this.add(rule.spinner2, 8, row);
		// add word "then"
		this.add(new Label(" then "), 9, row); 		
		// add slider 3 choice
		ObservableList<String> options5 = FXCollections.observableArrayList(" ","A","B","C","D");
		rule.comboBox5 = new ComboBox<>(options5);
		rule.comboBox5.setValue(" ");
		this.add(rule.comboBox5, 10, row);
		// add text "="
		this.add(new Label(" := "), 11, row); 		
		// add value choice
		rule.spinner3 = new Spinner<>();
		SpinnerValueFactory<Integer> valueFactory3 =
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50);
		rule.spinner3.setValueFactory(valueFactory3);
		rule.spinner3.setMaxWidth(80);
		this.add(rule.spinner3, 12, row);
		// remember rule
		rules.add(rule);
	}

	public RulesArea() {
		for (int line = 1; line <= 5; line++) {
			// draw grid
			drawLine(line);			
		}
	}

	private void applyRule(Rule rule, DoubleProperty A, DoubleProperty B, DoubleProperty C, DoubleProperty D) {
		// cancel if a value is missing
		if (rule.comboBox1.getValue().equals(" ")
				|| rule.comboBox2.getValue().equals(" ")
				|| rule.comboBox3.getValue().equals(" ")
				|| rule.comboBox4.getValue().equals(" ")
				|| rule.comboBox5.getValue().equals(" ")) {
			return;
		}
		// rule is active, so check first param
		double firstSliderVal;
		switch(rule.comboBox1.getValue()) {
		case "A": firstSliderVal = Main.homeArea.topSlider.getValue(); break;
		case "B": firstSliderVal = Main.homeArea.rightSlider.getValue(); break;
		case "C": firstSliderVal = Main.homeArea.bottomSlider.getValue(); break;
		case "D": firstSliderVal = Main.homeArea.leftSlider.getValue(); break;
		default: return;
		}
		switch (rule.comboBox2.getValue()) {
		case "<":
			if (firstSliderVal >= rule.spinner1.getValue()) {
				return;
			}
			break;
		case ">":
			if (firstSliderVal <= rule.spinner1.getValue()) {
				return;
			}
			break;
		default: return;
		}
		// check second param
		double secondSliderVal;
		switch(rule.comboBox3.getValue()) {
		case "A": secondSliderVal = Main.homeArea.topSlider.getValue(); break;
		case "B": secondSliderVal = Main.homeArea.rightSlider.getValue(); break;
		case "C": secondSliderVal = Main.homeArea.bottomSlider.getValue(); break;
		case "D": secondSliderVal = Main.homeArea.leftSlider.getValue(); break;
		default: return;
		}
		switch (rule.comboBox4.getValue()) {
		case "<":
			if (secondSliderVal >= rule.spinner2.getValue()) {
				return;
			}
			break;
		case ">":
			if (secondSliderVal <= rule.spinner2.getValue()) {
				return;
			}
			break;
		default: return;
		}
		
		// set slider value
		int val1 = rule.spinner3.getValue();
		switch(rule.comboBox5.getValue()) {
		case "A": Main.homeArea.topSlider.setValue(val1); break;
		case "B": Main.homeArea.rightSlider.setValue(val1); break;
		case "C": Main.homeArea.bottomSlider.setValue(val1); break;
		case "D": Main.homeArea.leftSlider.setValue(val1); break;
		default: return;
		}
	}

	public void applyRules(DoubleProperty A, DoubleProperty B, DoubleProperty C, DoubleProperty D) {
		for (Rule rule : rules) {
			applyRule(rule, A, B, C, D);
		}
	}

}
