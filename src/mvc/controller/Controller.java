/**
 * 
 */
package mvc.controller;

import java.awt.event.ActionEvent;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mvc.model.*;
import mvc.model.rooms.Room;
import mvc.view.SortView;
import mvc.view.RoomView;
import mvc.view.View;
import mvc.view.event.ViewEvent;
import mvc.view.listener.ViewListener;

/**
 * @author Sergey
 *
 */
public class Controller implements ViewListener {

	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;

		this.view.initDataForView(model.getAppartment());
		this.view.addTableListener(new TableListener());
		
		

	}

	class TableListener implements TableModelListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.TableModelListener#tableChanged(javax.swing.event
		 * .TableModelEvent)
		 */
		@Override
		public void tableChanged(TableModelEvent e) {
			if ((TableModel) e.getSource() == view.getRoomViews()[0].getTable()
					.getModel()) {
//				System.out.println("Table 1");
				updateData(e, view.getRoomViews()[0]);
			} else if ((TableModel) e.getSource() == view.getRoomViews()[1]
					.getTable().getModel()) {
//				System.out.println("Table 2");
				updateData(e, view.getRoomViews()[1]);
			} else if ((TableModel) e.getSource() == view.getRoomViews()[2]
					.getTable().getModel()) {
//				System.out.println("Table 3");
				updateData(e, view.getRoomViews()[2]);
			} else if ((TableModel) e.getSource() == view.getRoomViews()[3]
					.getTable().getModel()) {
//				System.out.println("Table 4");
				updateData(e, view.getRoomViews()[3]);
			}
		}

		/**
		 * @param roomView
		 */
		private void updateData(TableModelEvent e, RoomView roomView) {
			if (e.getType() == TableModelEvent.UPDATE) {
				int rowIndex = e.getFirstRow();
				int columnIndex = e.getColumn();
				int power = 0;
				boolean isOn;

				
				for (Room room : model.getAppartment().getRooms()) {
					if (room == roomView.getRoom()) {
						TableModel tableModel = (TableModel) e.getSource();
						if (columnIndex == 1) {
							power = (int) tableModel.getValueAt(rowIndex,
									columnIndex);
							room.getRoomAppliances()
							.get(rowIndex).setPower(power);
						} else if (columnIndex == 2) {
							isOn = (boolean) tableModel.getValueAt(rowIndex,
									columnIndex);
							room.getRoomAppliances()
							.get(rowIndex).setOn(isOn);
						}
						view.updateSummaryPowerView(roomView);						
					}
				}	
			}
			
			FileReadWrite fileReadWrite = new FileReadWrite();
			fileReadWrite.writeDataToFile(model.getAppartment());

		}

	}

	/* (non-Javadoc)
	 * @see mvc.view.ViewListener#ViewActionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void ViewActionPerformed(ActionEvent e, ViewEvent viewEvent) {
		if (e.getActionCommand() == viewEvent.getNameSortByPowerButton()) {
//			System.out.println(e.getActionCommand());
			createSotrView();
		}
		
	}

	/**
	 * @param model
	 */
	private void createSotrView() {
		SortView sortView = new SortView();
		sortView.viewSortedAppliances(model.getSortedAppliances());
		
	}

}
