/**
 * 
 */
package mvc.view.event;

/**
 * @author Sergey
 *
 */
public class ViewEvent {

	private String nameSortByPowerButton;

	public ViewEvent(String nameSortByPowerButton) {
		this.nameSortByPowerButton = nameSortByPowerButton;
	}

	/**
	 * @return the nameSortByPowerButton
	 */
	public String getNameSortByPowerButton() {
		return nameSortByPowerButton;
	}

	/**
	 * @param nameSortByPowerButton the nameSortByPowerButton to set
	 */
	public void setNameSortByPowerButton(String nameSortByPowerButton) {
		this.nameSortByPowerButton = nameSortByPowerButton;
	}
	
}
