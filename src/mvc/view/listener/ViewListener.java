/**
 * 
 */
package mvc.view.listener;

import java.awt.event.ActionEvent;

import mvc.view.event.ViewEvent;

/**
 * @author Sergey
 *
 */
public interface ViewListener {

	/**
	 * @param e
	 * @param viewEvent
	 */
	void ViewActionPerformed(ActionEvent e, ViewEvent viewEvent);
	
}
