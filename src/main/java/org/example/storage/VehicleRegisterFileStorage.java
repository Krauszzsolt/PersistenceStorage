package org.example.storage;

import org.example.app.entity.Vehicle;
import org.example.app.interfaces.VehicleRegisterPersistenceStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VehicleRegisterFileStorage implements VehicleRegisterPersistenceStorage {
    @Override
    public void saveVehicle(Vehicle vehicle) {
        try {
            File file = new File( vehicle.registrationNumber + ".txt" );
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(vehicle.registrationNumber + ".txt");
            myWriter.write(vehicle.model+';'+vehicle.registrationNumber+';'+vehicle.make+';'+vehicle.numberOfSeats+';'+vehicle.vehicleType+';'+vehicle.motorEmissionType);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vehicle getVehicle(String RegistrationNumber) {
        String data = "";
        try {
            File myObj = new File(RegistrationNumber + ".txt");
            Scanner myReader = new Scanner(myObj);
            data = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Vehicle vehicle = new Vehicle(data.split(";")[0],data.split(";")[1],data.split(";")[2],
                Integer.parseInt(  data.split(";")[3]),data.split(";")[4],data.split(";")[5]);
        return vehicle;
    }
}
