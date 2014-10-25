package mvc;
import javax.swing.SwingUtilities;

import mvc.controller.Controller;
import mvc.model.Model;
import mvc.view.View;

/**
 * 
 */

/**
 * @author Sergey
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				runApplication();		
			}
			
		});
	}

	private static void runApplication() {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(view, model);
		view.setViewListener(controller);
				
	}


}
