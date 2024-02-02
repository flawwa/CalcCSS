package dad.calculadora.ui;

import java.io.IOException;

import dad.calculadora.css.Calculadora;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;

public class CalcController {
	
	// model
	
	private Calculadora calculadora = new Calculadora();
	
	// view
	
	@FXML 
	private GridPane view;
	
	@FXML
	private TextField screenText;

	public CalcController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalcView.fxml"));
        loader.setController(this);
        loader.load();
	}
	
	@FXML
	private void initialize() {
		
		screenText.textProperty().bind(calculadora.screenProperty());
		  view.getStylesheets().add("/css/clasica.css");

	        //menú contextual
	        MenuItem clasicoItem = new MenuItem("Clásica");
	        clasicoItem.setOnAction(e->{
	            view.getStylesheets().clear();
	            view.getStylesheets().add("/css/clasica.css");
	            System.out.println("clasica");
	        });

	        MenuItem modernoItem = new MenuItem("Moderna");
	        modernoItem.setOnAction(e->{
	            view.getStylesheets().clear();
	            view.getStylesheets().add("/css/moderna.css");
	            System.out.println("moderna");
	        });
	        ContextMenu menu = new ContextMenu(clasicoItem, modernoItem);
	        view.setOnContextMenuRequested(e->{
	            menu.show(view, e.getScreenX(),e.getScreenY());
	        });
	}
	
	@FXML
	private void onOperationButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		if (texto.equals("CE")) {
			calculadora.cleanEverything();
		} else if (texto.equals("C")) {
			calculadora.clean();
		} else if (texto.equals("+/-")) {
			calculadora.negate();
		} else {
			calculadora.operate(texto.charAt(0));
		}
	}
	
	@FXML
	private void onCommaButtonHandle(ActionEvent e) {
		calculadora.insertComma();
	}
	
	@FXML
	private void onNumberButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		calculadora.insert(texto.charAt(0));
	}
	
	public GridPane getView() {
		return view;
	}

}
