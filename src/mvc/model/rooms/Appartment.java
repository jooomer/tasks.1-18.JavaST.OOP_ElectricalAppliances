/**
 * 
 */
package mvc.model.rooms;

import java.util.LinkedList;
import java.util.List;
import mvc.model.appliances.Appliance;

/**
 * @author Sergey
 *
 */
public class Appartment {
	
	private List<Room> rooms = new LinkedList<Room>();
	private List<Appliance> appliances = new LinkedList<Appliance>();
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void addAppliance(Appliance appliance) {
		appliances.add(appliance);
	}
	
	public void printRooms() {
		for (Room room : rooms) {
			System.out.println(room.getName());
			for (Appliance appliance : room.getRoomAppliances()) {
				System.out.print("- " + appliance.getName());
				System.out.println(""
						+ " : Power = " + appliance.getPower() + " W");
			}
		}
	}
	
	public void printAppliances() {
		String applianceName;
		int appliancePower;
		for (Appliance appliance : appliances) {
			applianceName = appliance.getName();
			appliancePower = appliance.getPower();
			System.out.println(applianceName + " : Power = " + appliancePower);
		}
	}

	/**
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	/**
	 * @return the appliances
	 */
	public List<Appliance> getAppliances() {
		return appliances;
	}

	/**
	 * @param appliances the appliances to set
	 */
	public void setAppliances(List<Appliance> appliances) {
		this.appliances = appliances;
	}
}
