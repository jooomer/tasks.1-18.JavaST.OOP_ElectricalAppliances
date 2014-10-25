/**
 * 
 */
package mvc.model.rooms;

import java.util.ArrayList;
import java.util.List;

import mvc.model.appliances.Appliance;

/**
 * @author Sergey
 *
 */
public class Room {
	private String name;
	private List<Appliance> roomAppliances = new ArrayList<Appliance>();

	public void addAppliance(Appliance appliance) {
		roomAppliances.add(appliance);
	}
	
	public int calculateSummaryPower() {
		int summaryPower = 0;
		for (Appliance appliance : roomAppliances) {
			if (appliance.isOn()) {
				summaryPower += appliance.getPower();
			}
		}
		return summaryPower;
	}

	public void setName(String roomName) {
		this.name = roomName;
	}

	public String getName() {
		return name;
	}

	public List<Appliance> getRoomAppliances() {
		return roomAppliances;
	}

	public void printAppliances() {
		System.out.println(name);
		for (Appliance appliance : roomAppliances) {
			System.out.println(appliance.getName() + " : "
					+ appliance.getPower() + " W : " + appliance.isOn());
		}
	}

	/**
	 * @param roomAppliances the roomAppliances to set
	 */
	public void setRoomAppliances(List<Appliance> roomAppliances) {
		this.roomAppliances = roomAppliances;
	}
}
