package mazeInput;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Kayne
 * Class to solve and output solution to a maze given an input file 'input.txt' within the package directory.
 * 
 * Input file must be in format:
 * - width height
 * - start X start Y
 * - end X end Y
 * - maze with format 0 for potenital path and 1 for a wall
 */
public class MazeSolver {

	static int startX; //X start position
	static int startY; //Y start position
	static int endX; //X end position
	static int endY; //Y end position
	static String[][] maze; //Maze Array[][]
	static String north; //Holds character in north position
	static String south; //Holds character in south position
	static String east; //Holds character in east position
	static String west; //Holds character in west position
	static String current; //Holds character in current position
	static LinkedList<Integer> moves = new LinkedList<Integer>(); //Holds list of all moves in maze 

	/**
	 * @param coordX - Used for X coordinate of maze - refers to array lookup
	 * @param coordY - Used for Y coordinate of maze - refers to item in array
	 * @return - Returns true/ false whether maze has been solved
	 */
	static boolean solveMaze(int coordX, int coordY) {
		current = maze[coordX][coordY]; //Current position in maze
		north = maze[coordX - 1][coordY];//Holds what is in north position
		south = maze[coordX + 1][coordY];//Holds what is in south position
		east = maze[coordX][coordY + 1];//Holds what is in east position
		west = maze[coordX][coordY - 1];//Holds what is in west position

		if (north.equals("E") || south.equals("E") || east.equals("E") || west.equals("E")) {
			//We have reached end of maze
			if (current != "S"){
				//End isn't next to start, mark final position
				maze[coordX][coordY] = "X";
			}
			return true;//Maze solved
		}

		if (north.equals("0")) {
			//Not wall or retraced step, go north
			if (current != "S"){
				//Not start, mark step
				maze[coordX][coordY] = "X";
			}
			//Add coordinates to list of moves
			moves.add(coordX);
			moves.add(coordY);
			solveMaze(coordX - 1, coordY);//Repeat from new position
		} else if (south.equals("0")) {
			//Not wall or retraced step, go south
			if (current != "S"){
				//Not start, mark step
				maze[coordX][coordY] = "X";
			}
			//Add coordinates to list of moves
			moves.add(coordX);
			moves.add(coordY);
			solveMaze(coordX + 1, coordY);
		} else if (east.equals("0")) {
			//Not wall or retraced step, go east
			if (current != "S"){
				//Not start, mark step
				maze[coordX][coordY] = "X";
			}
			//Add coordinates to list of moves
			moves.add(coordX);
			moves.add(coordY);
			solveMaze(coordX, coordY + 1);
		} else if (west.equals("0")) {
			//Not wall or retraced step, go west
			if (current != "S"){
				//Not start, mark step
				maze[coordX][coordY] = "X";
			}
			//Add coordinates to list of moves
			moves.add(coordX);
			moves.add(coordY);
			solveMaze(coordX, coordY - 1);
		} else {
			// Deadend, retrace steps
			if (current != "S"){
				//Not start, mark retraced step
				maze[coordX][coordY] = "R";
			}
			if (moves.isEmpty() == false) {
				//List of moves populated, go back a space and remove from list of moves
				coordY = moves.getLast();
				moves.removeLast();
				coordX = moves.getLast();
				moves.removeLast();
				solveMaze(coordX, coordY);//repeat from retraced step
			}
		}
		return false;
	}

	/**
	 * @param height - height of maze to print all arrays
	 */
	static void printMaze(int height) {
		for (int i = 0; i < height; i++) {
			//Print entrire array removing uneeded characters and formatting to look like maze
			System.out.println((((((Arrays.toString(maze[i]).replace("[", "")).replace("]", "")).replace(",", ""))
					.replace("R", " ")).replace("1", "#")).replace("0", " "));
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//Open text file from package directory
		Path filePath = Paths.get("input.txt");
		Scanner scanner = new Scanner(filePath);
		//Retrieve width and height of maze
		int width = scanner.nextInt();
		int height = scanner.nextInt();
		//Initialise maze array[][]
		maze = new String[height][width];
		//Sent start and end positions
		startX = scanner.nextInt();
		startY = scanner.nextInt();
		endX = scanner.nextInt();
		endY = scanner.nextInt();
		
		int count = 0;//Used to count array number 
		String row;//Holds current row in array
		while (scanner.hasNextLine()) {
			row = scanner.nextLine().replace(" ", "");//Remove blanks from maze
			if (row.isEmpty() == false) {
				for (int i = 0; i < row.length(); i++) {
					maze[count][i] = row.substring(i, i + 1);//Add values for row into each array element
				}
				count++;
			}
		}
		maze[startY][startX] = "S";//Set "S" start position
		maze[endY][endX] = "E";//Set "E" end position
		solveMaze(startX, startY);//Solve maze
		printMaze(height);//Output maze
		scanner.close();
	}
}
