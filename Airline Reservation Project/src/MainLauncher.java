/* Name: Nisha Patel
 * Project Due: April 19
 * Project Name: Airline Reservation System Assignment
 * Instructor: Topsakal
 * Course: COP3330C.03
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLauncher extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root =
			FXMLLoader.load(getClass().getResource("AirlineReservationMain.fxml"));

		Scene scene = new Scene(root);
		stage.setTitle("Airline Reservation System"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
		
	    stage.setOnCloseRequest(evt -> {
	        // prevent window from closing
	        evt.consume();
	    });
	}

	public static void main(String[] args) throws FileNotFoundException {
		createFiles();
		// create a AirlineReservation object and call its start method
		launch(args);
	}

	static void createFiles() throws FileNotFoundException {
		File flightFile = new File(System.getProperty("user.dir") + "/flights.txt");
		
		// if file doesn't exists, then create it
		if (!flightFile.exists()) {
			try {
				flightFile.createNewFile();
				Formatter output = new Formatter(flightFile);
				output.format("%-10s %-10s %-10s %-10s %-15s %-13s %s", "Flight#", "FDate", "DTime", "ATime", "DepartCity", "DestCity", "AvailableSeats");
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File reservationFile = new File(System.getProperty("user.dir") + "/reservations.txt");
		
		// if file doesn't exists, then create it
		if (!reservationFile.exists()) {
			try {
				reservationFile.createNewFile();
				Formatter output = new Formatter(reservationFile);
				output.format("%-5s %-8s %-13s %s%n", "ID", "Name", "SeatNumber", "Flights");
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File folder = new File(System.getProperty("user.dir") + "/Flight Seat Maps/");
		
		// if folder doesn't exists, then create it
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
}