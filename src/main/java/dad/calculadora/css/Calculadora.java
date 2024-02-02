package dad.calculadora.css;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Calculadora {
	
	public static final char EQUAL = '='; 
	public static final char ADD = '+'; 
	public static final char SUBSTRACT = '-'; 
	public static final char DIVIDE = '/'; 
	public static final char MULTIPLY = '*';
	
	private static final char COMMA = '.'; 
	
	private Double operand;
	private char operator;
	private Boolean newOperand;
	
	private StringProperty screen = new SimpleStringProperty(this, "screen");
	
	public Calculadora() {
		clean();
	}

	/**
	 * Inicializa por completo la calculadora, borrando la informaci�n que tiene memorizada y la pantalla.
	 */
	public void clean() {
		operand = 0.0;
		operator = '=';
		cleanEverything();
	}
	
	/**
	 * Borra lo que hay en la pantalla (el �ltimo operando introducido).
	 */
	public void cleanEverything() {
		newOperand = true;
		setScreen("0.0");
	}
	
	/**
	 * Indica a la calculadora que realice la operaci�n indicada. 
	 * @param operator Operaci�n a realizar; usar una constante: IGUAL, SUMAR, RESTAR, MULTIPLCIAR, DIVIDIR.
	 */
	public void operate(char operator) {
		newOperand = true;
		double operand2 = Double.parseDouble(getScreen());
		switch (this.operator) {
			case ADD: operand += operand2; break;
			case SUBSTRACT: operand -= operand2; break;
			case MULTIPLY: operand *= operand2; break;
			case DIVIDE: operand /= operand2; break;
			case EQUAL: operand = operand2; break;
		}
		this.operator = operator;
		setScreen("" + operand);
	}
	
	public void negate() {
		if (getScreen().startsWith("-")) {
			setScreen(getScreen().substring(1));
		} else {
			setScreen("-" + getScreen());
		}
	}
	
	/**
	 * Inserta una coma en el operando actual (pantalla).
	 */
	public void insertComma() {
		if (!getScreen().contains("" + COMMA)) {
			setScreen(getScreen() + COMMA);
		}
	}
	
	public void insert(char digit) {
		if (digit >= '0' && digit <= '9') {
			if (newOperand) {
				newOperand = false;
				setScreen("");
			}
			setScreen(getScreen() + digit);
		} else if (digit == COMMA) {
			insertComma();
		}
	}

	public final StringProperty screenProperty() {
		return this.screen;
	}
	

	public final String getScreen() {
		return this.screenProperty().get();
	}
	

	public final void setScreen(final String screen) {
		this.screenProperty().set(screen);
	}
	
}
