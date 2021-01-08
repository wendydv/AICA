package aica.utiles;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Validaciones {

	//Valida las Letras (Solo escribir cáracteres)
	public static void validateLetter(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE) ||
						(Character.isWhitespace(c))))) {
					e.consume();
				}
			}
		});
	}
	
	public static void validateDigitAndComma(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) ||
						(c == '.') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
	}
	
//	Valida las Letras (Solo escribir dígitos)
	public static void validateDigit(JTextField src) {
		src.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
	}
	
	
	public static void validatelenght(final JTextField src) {
		src.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((src.getText().length() !=11 ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
	}
	
	
	public static void validatelenghtCode(final JTextField src) {
		src.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((src.getText().length() !=6 ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
	}
	
	
}