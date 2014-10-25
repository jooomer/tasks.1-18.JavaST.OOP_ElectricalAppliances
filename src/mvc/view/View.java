/**
 * 
 */
package mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import mvc.model.rooms.Appartment;
import mvc.view.event.ViewEvent;
import mvc.view.listener.*;
//import utilities.TableSorter;

/**
 * @author Sergey
 *
 */
public class View extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ViewListener viewListener;
	
	private static String titleMainFrame = "Electrical Appliances";
	private RoomView[] roomViews = new RoomView[4];
	
	private String nameSortByPowerButton = "Sort by Power";

	public View() {
		super(titleMainFrame);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(700, 600));
		pack();
		setLocationRelativeTo(null);

		setVisible(true);
	}

	public void initDataForView(Appartment appartment) {
		add(createPanels(appartment));
	}
	
	/**
	 * @return
	 */
	private JPanel createPanels(Appartment appartment) {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel centerPanel = new JPanel(new GridLayout(2, 0));
		Border centerBorder = BorderFactory.createTitledBorder("Appartment");
		centerPanel.setBorder(centerBorder);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel sortPanel = new JPanel();
		Border sortBorder = BorderFactory.createTitledBorder("Sort by power");
		sortPanel.setBorder(sortBorder);
		sortPanel.add(createButton(nameSortByPowerButton));
		mainPanel.add(sortPanel, BorderLayout.SOUTH);

		for (int i = 0; i < roomViews.length; i++) {
			roomViews[i] = new RoomView(appartment.getRooms().get(i));
			centerPanel.add(roomViews[i].createRoomView());
		}
		
		return mainPanel;
	}
	
	private JButton createButton(String nameButton) {

		JButton button = new JButton(nameButton);
		button.addActionListener(this);
		button.setActionCommand(nameButton);

		return button;
	}

	
	public void addTableListener(TableModelListener tableModelListener) {
		for (RoomView roomView : roomViews) {
				roomView.getTable().getModel().addTableModelListener(tableModelListener);
		}		
	}	

	public void updateSummaryPowerView(RoomView roomView) {
		roomView.updateSummaryPower();
	}

	/**
	 * @return the roomViews
	 */
	public RoomView[] getRoomViews() {
		return roomViews;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		viewListener.ViewActionPerformed(e, new ViewEvent(nameSortByPowerButton));
	}

	/**
	 * @param viewListener the viewListener to set
	 */
	public void setViewListener(ViewListener viewListener) {
		this.viewListener = viewListener;
	}

}
