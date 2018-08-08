package me.jacob.wobits;
import java.util.Scanner;

/**
 * Name: Jacob Seiler
 * Teacher: Mrs. Costin
 * Course: ICS4U
 * 
 * The Class WorldOfWobits runs the wobits universe.
 */
public class WorldOfWobits {

    private static int[][] wobits; // Wobits grid
    private static int maleWobits; // Amount of male wobits
    private static int femaleWobits; // Amount of female wobits

    /**
     * The main method.
     *
     * @param args
     *                 the arguments
     */
    public static void main(String[] args) {
	wobits = new int[10][10];
	wobits[0][2] = -2; // 2-year old female
	wobits[1][1] = 2; // 2-year old male

	maleWobits++; // Add to total amount of male wobits
	femaleWobits++; // Add to total amount of female wobits

	int year = 0; // The year

	Scanner input = new Scanner(System.in);
	String response = "";

	// Keep running until the user types "quit"
	while (!response.equals("quit")) {
	    if (year <= 0) {
		System.out.println("At the start:");
	    } else {
		birthWobits(wobits); // Born
		ageWobits(wobits); // Age / Death
		System.out.println("End of year " + year + ":");
	    }

	    year++; // Add to the year

	    printGrid(wobits); // Print the grid
	    response = input.nextLine(); // Wait for response
	}

	input.close();

    }

    /**
     * Give birth to new wobits if the conditions are correct.
     *
     * @param wobits
     *                   the wobits grid
     */
    private static void birthWobits(int[][] wobits) {
	// Loop through wobits grid
	for (int row = 0; row < wobits.length; row++) {
	    for (int col = 0; col < wobits[row].length; col++) {
		int value = wobits[row][col]; // The current spot being checked

		boolean momFound = false; // If the female wobit is found
		boolean dadFound = false; // If the male wobit is found

		// If there is not a wobit in the grid space being checked
		if (value == 0) {
		    // Loop through all adjacent wobits
		    for (int pY = -1; pY < 2; pY++)
			for (int pX = -1; pX < 2; pX++) {
			    if (momFound && dadFound) // If the mom and dad have been found
				continue;
			    if (pY == 0 && pX == 0) // Do not check the current space
				continue;

			    int pRow = row + pY;
			    int pCol = col + pX;

			    if (pRow >= wobits.length || pRow < 0) // If the row being checked is out of the index
								   // bounds
				continue;
			    if (pCol >= wobits[row].length || pCol < 0) // If the column being checked is out of the
									// array bounds
				continue;

			    int v = wobits[pRow][pCol]; // the value of the current grid spot being checked
			    if (!dadFound && v >= 2 && v <= 6) // If the value is male and 2-6yo
				dadFound = true; // Viable dad
			    else if (!momFound && Math.abs(v) >= 2 && Math.abs(v) <= 4) // If the value is female and
											// 2-4yo
				momFound = true; // Viable mom
			}
		}

		// If mom and dad is found
		if (momFound && dadFound)
		    // If there are more or equal male wobits
		    if (femaleWobits <= maleWobits) {
			wobits[row][col] = -10; // Set space to become a female wobit
			femaleWobits++; // Add to total amount of female wobits
		    } else {
			wobits[row][col] = 10; // Set space to become a male wobit
			maleWobits++; // Add to total amount of male wobits
		    }
	    }
	}
    }

    /**
     * Give birth to new wobits if the conditions are correct.
     *
     * @param wobits
     *                   the wobits grid
     */
    private static void ageWobits(int[][] wobits) {
	// Loop through wobits grid
	for (int row = 0; row < wobits.length; row++) {
	    for (int col = 0; col < wobits[row].length; col++) {
		int value = wobits[row][col]; // The current spot being checked

		if (value > 0)
		    value++; // Age male
		else if (value < 0)
		    value--; // Age female

		if (Math.abs(value) == 11) // If wobit was just born (10 + 1 = 11)
		    if (value > 0)
			value = 1; // Set value to 1 (male)
		    else
			value = -1; // Set value to 1 (female)

		if (Math.abs(value) >= 8) // If wobit is old
		    value = 0; // Kill wobit

		wobits[row][col] = value; // Set new grid value
	    }
	}
    }

    /**
     * Print the grid of wobits.
     *
     * @param grid
     *                 the wobits grid
     */
    private static void printGrid(int[][] grid) {
	// Loop through wobit grid
	for (int row = 0; row < grid.length; row++) {
	    for (int col = 0; col < grid[row].length; col++) {
		int value = grid[row][col]; // The current spot being checked

		if (value > 0) {
		    System.out.print("{" + value + "}"); // Print male with {}
		} else if (value < 0) {
		    System.out.print("(" + value * -1 + ")"); // Print female with ()
		} else {
		    System.out.print("[ ]"); // Print none with []
		}
	    }

	    System.out.println();
	}

	System.out.println();
    }

}
