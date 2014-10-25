/**
 * 
 */
package mvc.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mvc.model.appliances.*;
import mvc.model.rooms.Appartment;
import mvc.model.rooms.Room;

/**
 * @author Sergey
 *
 */
public class Model {
	
	private Appartment appartment;
	
	public Model() {
		createAppartment();
	}
	
	/**
	 * 
	 */
	private void createAppartment() {
		this.appartment = new Appartment();
		
		if (isFileExists()) {
			FileReadWrite fileReadWrite = new FileReadWrite();
			appartment = fileReadWrite.readDataFromFile(appartment);
		} else {
			createKitchen(appartment);		
			createLivingRoom(appartment);
			createBedRoom(appartment);
			createBathRoom(appartment);
			
			FileReadWrite fileReadWrite = new FileReadWrite();
			fileReadWrite.writeDataToFile(appartment);
		}
		
	}

	/**
	 * @return
	 */
	private boolean isFileExists() {
		File file = new File(FileReadWrite.fileWithPath);
		return file.exists();
	}


	/**
	 * @param appartment2
	 */
	private void createBathRoom(Appartment appartment) {
		Room bathRoom = new Room();
		bathRoom.setName("Bathroom");
		appartment.addRoom(bathRoom);		
		
		Lighting cellingLighting = new Lighting();
		cellingLighting.setName("Bathroom Celling Lighting");
		cellingLighting.setPower(100);
		cellingLighting.setOn(false);
		cellingLighting.setRoom(bathRoom);
		appartment.addAppliance(cellingLighting);
		
		Washer washer = new Washer();
		washer.setName("Washer");
		washer.setPower(350);
		washer.setOn(false);
		washer.setRoom(bathRoom);
		appartment.addAppliance(washer);
		
		Dryer dryer = new Dryer();
		dryer.setName("Dryer");
		dryer.setPower(250);
		dryer.setOn(false);
		dryer.setRoom(bathRoom);
		appartment.addAppliance(dryer);
		
	}

	/**
	 * @param appartment2
	 */
	private void createBedRoom(Appartment appartment) {
		Room bedRoom = new Room();
		bedRoom.setName("Bedroom");
		appartment.addRoom(bedRoom);		
		
		Lighting cellingLighting = new Lighting();
		cellingLighting.setName("Bedroom Celling Lighting");
		cellingLighting.setPower(200);
		cellingLighting.setOn(false);
		cellingLighting.setRoom(bedRoom);
		appartment.addAppliance(cellingLighting);
	
		Lighting floorLamp = new Lighting();
		floorLamp.setName("Living Room Floor Lamp");
		floorLamp.setPower(75);
		floorLamp.setOn(false);
		floorLamp.setRoom(bedRoom);
		appartment.addAppliance(floorLamp);
		
		Lighting sconceLamp1 = new Lighting();
		sconceLamp1.setName("Sconce Lamp 1");
		sconceLamp1.setPower(60);
		sconceLamp1.setOn(false);
		sconceLamp1.setRoom(bedRoom);
		appartment.addAppliance(sconceLamp1);
		
		Lighting sconceLamp2 = new Lighting();
		sconceLamp2.setName("Sconce Lamp 2");
		sconceLamp2.setPower(60);
		sconceLamp2.setOn(false);
		sconceLamp2.setRoom(bedRoom);
		appartment.addAppliance(sconceLamp2);
	
	}

	/**
	 * @param appartment
	 */
	private void createLivingRoom(Appartment appartment) {
		Room livingRoom = new Room();
		livingRoom.setName("Living Room");
		appartment.addRoom(livingRoom);		
		
		Lighting cellingLighting = new Lighting();
		cellingLighting.setName("Living Room Celling Lighting");
		cellingLighting.setPower(150);
		cellingLighting.setOn(false);
		cellingLighting.setRoom(livingRoom);
		appartment.addAppliance(cellingLighting);
	
		Lighting floorLamp = new Lighting();
		floorLamp.setName("Living Room Floor Lamp");
		floorLamp.setPower(70);
		floorLamp.setOn(false);
		floorLamp.setRoom(livingRoom);
		appartment.addAppliance(floorLamp);

	}

	private void createKitchen(Appartment appartment) {
		Room kitchen = new Room();
		kitchen.setName("Kitchen");
		appartment.addRoom(kitchen);
		
		Lighting cellingLighting = new Lighting();
		cellingLighting.setName("Kitchen Celling Lighting");				
		cellingLighting.setPower(100);
		cellingLighting.setRoom(kitchen);
		cellingLighting.setOn(false);
		appartment.addAppliance(cellingLighting);
		
		MicrowaveOven microwaveOven = new MicrowaveOven();
		microwaveOven.setName("Microwave Oven");
		microwaveOven.setPower(250);
		microwaveOven.setRoom(kitchen);
		microwaveOven.setOn(false);
		appartment.addAppliance(microwaveOven);
		
		Refrigirator refrigirator = new Refrigirator();
		refrigirator.setName("Refrigirator");
		refrigirator.setPower(300);
		refrigirator.setRoom(kitchen);
		refrigirator.setOn(false);
		appartment.addAppliance(refrigirator);

	}
	
	public void updateApplianceData(Room room) {
		appartment.getRooms().get(0);
	}

	/**
	 * @return the appartment
	 */
	public Appartment getAppartment() {
		return appartment;
	}

	/**
	 * @param appartment the appartment to set
	 */
	public void setAppartment(Appartment appartment) {
		this.appartment = appartment;
	}

	/**
	 * 
	 */
	public List<Appliance> getSortedAppliances() {

		int size = appartment.getAppliances().size();
		
		List<Appliance> appliances = new ArrayList<Appliance>();
		
		for (int i = 0; i < size; i++) {
			appliances.add(i, appartment.getAppliances().get(i));
		}
		
		int small;
		for (int i = 0; i < size; i++) {
			small = i;
			for (int j = i + 1; j < size; j++) {
				if (appliances.get(j).getPower() < appliances.get(small).getPower()) {
					swap(appliances, i, j);
				}
			}
		}
				
		return appliances;
		
	}

	/**
	 * @param appliances
	 * @param i
	 * @param j
	 */
	private void swap(List<Appliance> appliances, int i, int j) {
		Appliance applianceTemp = appliances.get(i);
		appliances.set(i, appliances.get(j));
		appliances.set(j, applianceTemp);
	}

	
	
}
