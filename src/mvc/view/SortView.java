/**
 * 
 */
package mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mvc.model.appliances.*;

/**
 * @author Sergey
 *
 */
public class SortView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String titleFrame = "Sorted appliances by power";
	
	public SortView() {
		super(titleFrame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(400, 300));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void viewSortedAppliances(List<Appliance> appliances) {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		mainPanel.setBorder(border);;
		JPanel panel = new JPanel(new GridLayout(0, 1));
		mainPanel.add(panel);		

//		System.out.println();
		for (Appliance app : appliances) {
			JLabel label = new JLabel();
			panel.add(label);
			label.setText("Power is " + app.getPower() + " W - " + app.getName());
		}
		
	}
	

}
