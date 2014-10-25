/**
 * 
 */
package mvc.view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import mvc.model.rooms.Room;

/**
 * @author Sergey
 *
 */
public class RoomView {

	private JTable table;
	private JLabel summaryPowerTextLabel = new JLabel();
	private Room room;

	private String[] columnNames = { "Appliance", "Power", "On / Off" };
	private Object[][] rowsData;
	private int columnQuantity = 3;
	private String summaryPowerText = "Summary power is ";
	

	private int summaryPowerValue;


	public RoomView(Room room) {
		this.room = room;
		setRowsData(room);
	}

	/**
	 * 
	 */
	public void setSummaryPower(JLabel summaryPowerTextLabel,
			int summaryPowerValue) {
		summaryPowerTextLabel.setText(summaryPowerText
				+ Integer.toString(summaryPowerValue) + " W");
	}

	/**
	 * @param roomView
	 */
	public void updateSummaryPower() {
		
		summaryPowerValue = room.calculateSummaryPower();
		setSummaryPower(summaryPowerTextLabel, summaryPowerValue);
	}


	public void setRowsData(Room room) {
		int size = room.getRoomAppliances().size();
		rowsData = new Object[size][columnQuantity];
		for (int i = 0; i < size; i++) {
			rowsData[i][0] = room.getRoomAppliances().get(i).getName();
			rowsData[i][1] = room.getRoomAppliances().get(i).getPower();
			rowsData[i][2] = room.getRoomAppliances().get(i).isOn();
		}

		room.calculateSummaryPower();
	}

	/**
	 * @return
	 */
	public Container createRoomView() {
		JPanel roomContainer = new JPanel(new GridLayout(2, 0));

		String roomName = room.getName();
		Border centerBorder = BorderFactory.createTitledBorder(roomName);
		roomContainer.setBorder(centerBorder);

		DefaultTableModel defaultTableModel = new DefaultTableModel(rowsData, columnNames);
		table = new JTable(defaultTableModel)
        {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class<? extends Object> getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }

            public boolean isCellEditable(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel( column );
                return (modelColumn < 1) ? false : true;
            }
        };
           
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(200);

		JScrollPane scrollPane = new JScrollPane(table);
		roomContainer.add(scrollPane);

		summaryPowerValue = room.calculateSummaryPower();
		setSummaryPower(summaryPowerTextLabel, summaryPowerValue);
		roomContainer.add(summaryPowerTextLabel);

		return roomContainer;
	}

	public void addTableListener(AncestorListener ancestorListener) {
		table.addAncestorListener(ancestorListener);
		;
	}
	
	public void setValueAt(Object value, int row, int col) {
		rowsData[row][col] = value;
	}


	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

}
