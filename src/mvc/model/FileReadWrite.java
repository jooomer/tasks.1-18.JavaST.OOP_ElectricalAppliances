/**
 * 
 */
package mvc.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import mvc.model.rooms.*;
import mvc.model.appliances.*;

/**
 * @author Sergey
 *
 *         DAO class purchase table or view CRUD - create, retrieve, update,
 *         delete
 *
 */
public class FileReadWrite {

	public final static String fileWithPath = "Appliances.csv";
	public final static String delimiter = ",";
	public final static String classNameLighting = "class mvc.model.appliances.Lighting";
	public final static String classNameMicrowaveOven = "class mvc.model.appliances.MicrowaveOven";
	public final static String classNameRefrigirator = "class mvc.model.appliances.Refrigirator";
	public final static String classNameWasher = "class mvc.model.appliances.Washer";
	public final static String classNameDryer = "class mvc.model.appliances.Dryer";

	public void writeDataToFile(Appartment appartment) {

		List<StringBuffer> fileLines = new ArrayList<StringBuffer>();

		for (Room room : appartment.getRooms()) {

			for (Appliance appliance : room.getRoomAppliances()) {
				StringBuffer fileLine = new StringBuffer();

				fileLine.append(room.getName() + delimiter);
				fileLine.append(String.valueOf(appliance.getClass())
						+ delimiter);
				fileLine.append(appliance.getName() + delimiter);
				fileLine.append(String.valueOf(appliance.getPower())
						+ delimiter);
				fileLine.append(String.valueOf(appliance.isOn()));

				fileLines.add(fileLine);
			}

		}

		File file = new File(fileWithPath);
//		System.out.println(file.getAbsolutePath());
//		System.out.println(file.getCanonicalPath());
		PrintWriter printWriter = null;

		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			printWriter = new PrintWriter(new FileWriter(file));

			for (StringBuffer line : fileLines) {
				printWriter.println(line);
			}

		} catch (IOException e) {
			System.out.println("Error! The program can not write to a file.");
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				try {
					printWriter.close();
				} catch (Exception e) {
				}
			}
		}

		System.out.println("Done");

	}

	public Appartment readDataFromFile(Appartment appartment) {
		
		List<String> lines = new ArrayList<String>();

		File file = new File(fileWithPath);
//		System.out.println(file.getAbsolutePath());
//		System.out.println(file.getCanonicalPath());

		if (!file.exists()) {
			System.out.println("Error! File not found.");
			System.exit(0);
		}

		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(file);
			bufferedReader = new BufferedReader(
					new InputStreamReader(fileInputStream));

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return createAppartment(appartment, lines);
	}


	
	/**
	 * @param lines
	 * @return
	 */
	private Appartment createAppartment(Appartment appartment, List<String> lines) {
		List<Room> rooms = createRooms(lines);
		appartment = setRoomsToAppartment(appartment, rooms);		
		return appartment;
	}


	/**
	 * @param rooms
	 * @return
	 */
	private Appartment setRoomsToAppartment(Appartment appartment, List<Room> rooms) {
		for (Room room : rooms) {
			appartment.addRoom(room);
			for (Appliance appliance : room.getRoomAppliances()) {
				appartment.addAppliance(appliance);
			}
		}
		return appartment;
	}

	/**
	 * @param lines
	 * @return
	 */
	private List<Room> createRooms(List<String> lines) {
		String line = lines.get(0);
		String[] parameters = line.split(delimiter);

		List<Room> rooms = new ArrayList<Room>();
				
		String roomName = parameters[0];

		Room room = new Room();

		room = setParametersToRoom(room, parameters);
		
		rooms.add(room);

		for (int i = 1; i < lines.size(); i++) {
			line = lines.get(i);
			parameters = line.split(delimiter);
			
			roomName = parameters[0];
			
			if (!isRoomNameExists(rooms, roomName)) {
				room = new Room();
				room = setParametersToRoom(room, parameters);
				rooms.add(room);
			} else {
				room = setParametersToRoom(room, parameters);				
			}
							
		
		}

		
		return rooms;
	}



	/**
	 * @param room
	 * @param parameters
	 * @return
	 */
	private Room setParametersToRoom(Room room, String[] parameters) {
		room.setName(parameters[0]);

		String applianceClass = parameters[1];

		switch (applianceClass) {
		case classNameLighting :
			Lighting lighting = new Lighting();
			room = setParametersToAppliance(room, lighting, parameters);
			break;
		case classNameMicrowaveOven :
			MicrowaveOven microwaveOven = new MicrowaveOven();
			room = setParametersToAppliance(room, microwaveOven, parameters);
			break;
		case classNameRefrigirator :
			Refrigirator refrigirator = new Refrigirator();
			room = setParametersToAppliance(room, refrigirator, parameters);
			break;
		case classNameWasher :
			Washer washer = new Washer();
			room = setParametersToAppliance(room, washer, parameters);
			break;
		case classNameDryer :
			Dryer dryer = new Dryer();
			room = setParametersToAppliance(room, dryer, parameters);
			break;
		default :
			System.out.println("Error! Wrong appliance class in a file.");
			System.exit(0);
		}

		return room;
	}

	/**
	 * @param rooms
	 * @param roomName
	 * @return
	 */
	private boolean isRoomNameExists(List<Room> rooms, String roomName) {
		for (Room room : rooms) {
			if (room.getName().equals(roomName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param lighting
	 */
	private Room setParametersToAppliance(Room room, Appliance appliance, String[] parameters) {
		String nameAppliance = parameters[2];
		int power = Integer.parseInt(parameters[3]);
		boolean isOn = Boolean.parseBoolean(parameters[4]);
		
		appliance.setName(nameAppliance);
		appliance.setPower(power);
		appliance.setOn(isOn);
		appliance.setRoom(room);
		return room;
	}

	public void updateDataInFile(Appliance appliance) {

	}

	public void deleteDataFromFile(Appliance appliance) {

	}
}
