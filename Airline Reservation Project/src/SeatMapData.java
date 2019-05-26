import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class SeatMapData {

	//creates 2D array of all row numbers and seats
	private char[][] allSeats = {{'1','A','B','C','D','E','F','G'}, {'2','A','B','C','D','E','F','G'},
			{'3','A','B','C','D','E','F','G'}, {'4','A','B','C','D','E','F','G'}, {'5','A','B','C','D','E','F','G'},
			{'6','A','B','C','D','E','F','G'}, {'7','A','B','C','D','E','F','G'}, {'8','A','B','C','D','E','F','G'}, 
			{'9','A','B','C','D','E','F','G'}, {'0','A','B','C','D','E','F','G'}};

	public SeatMapData(){}
	
	public void printSeatMapToFile(String flightNumber) {
		//creates a new seat map file
		File seatMap =new File(System.getProperty("user.dir") + "/Flight Seat Maps/" + flightNumber + ".txt");
		
		try {
			Formatter output = new Formatter(seatMap);
			
			//prints seat map to file
			for(int x = 0; x < allSeats.length; x++) {				
				for(int y = 0; y < allSeats[x].length; y++) {
					
					if(allSeats[x][y] == '0') {	//formatting for row 10
						output.format("%-2d%s", 10, "  ");
						continue;
					}
					
					if(y == 2 || y == 5)
						output.format("%s", "  ");	//format spacing to create aisle space
					
					if(y==0)	//format spacing for row numbers
						output.format("%c%s", allSeats[x][y], "   ");
					else
						output.format("%c%s", allSeats[x][y], " ");
						
				}
				output.format("%n");	//output moves to next line of file
			}
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	//pulls the seat map from file and updates the allFlights array
	public void loadSeatMapFromFile(String flightNumber) {
		try {
			//finds the correct seat map file
			Scanner seatMap = new Scanner(new File(System.getProperty("user.dir") + "/Flight Seat Maps/" + flightNumber + ".txt"));
			Scanner dataScan;	//Scanner object to scan each line of the file
			String data;	//Scanner object to scan each data item in the line given
			String value;
			
			//runs the command to gather seat map data until there is no more data
			for (int x = 0; x < allSeats.length; x++) {

				data = seatMap.nextLine();	//reads in a line of text
				dataScan= new Scanner(data);	//reads each item in the line of text
				
				//updates values in allSeats array based on file
				for(int y = 0; y < allSeats[x].length; y++) {
					value = dataScan.next();	//scans in next item in the file
					
					if(value.equals("10"))	//change 10 to 0 to put in 2D array
						allSeats[x][y] = '0';
					else
						allSeats[x][y] = value.charAt(0);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//return a copy of allSeats array
	public char[][] getSeatMap(){
		return allSeats;
	}
	
	//update the seat map file
	public void updateSeatMapFile(String flightNumber, String seatNumber) {
		int rowNum;
		
		if(seatNumber.charAt(1)=='0')
			rowNum = 10; 
		else
			rowNum = Integer.parseInt(String.valueOf(seatNumber.charAt(0)));
		
		char seat = seatNumber.charAt(seatNumber.length()-1);
		
		for(int y = 0; y < allSeats[rowNum].length; y++) {
			if(allSeats[rowNum-1][y] == seat)
				allSeats[rowNum-1][y] = 'X';
		}
		
		printSeatMapToFile(flightNumber);
	}
}
