package view;

import java.awt.Window;
import java.lang.reflect.Method;

import javax.swing.SwingUtilities;

public class Ninn {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){ 
			public void run(){
				/* Launch application */
				new MainFrame();
			}
		});
	}

}
