/**
 * 
 */
package mvc.model.appliances;

import mvc.model.rooms.Room;

/**
 * @author Sergey
 *
 */
public abstract class Appliance {
	private String name;
	private int power;
	private Room room;
	private boolean isOn;
		
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
		room.addAppliance(this);
	}

	/**
	 * @return the isOn
	 */
	public boolean isOn() {
		return isOn;
	}

	/**
	 * @param isOn the isOn to set
	 */
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

}
