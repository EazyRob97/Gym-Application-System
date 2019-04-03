import java.util.Scanner;

/**
 * A Gym class which was specifically developed to demonstrate the use of an
 * ArrayList of Member.
 * 
 * @author Robert Solomon
 * @version 1.0
 */

public class MenuController {
	private Scanner input;
	private Gym gym;

	public static void main(String args[]) {
		new MenuController();
	}

	/**
	 * First Menu generated to the user before the Gym Menu which is the main menu
	 * of the system
	 */
	public MenuController() {
		input = new Scanner(System.in);
		// read in manager name and phone n pass to gym constructor
		gym = new Gym("", "");
		System.out.print("Please enter the Gym.\n");
		System.out.print("Name:");
		String name = input.nextLine();
		System.out.print("Manager Name:");
		String managerName = input.nextLine();
		System.out.print("Phone Number:");
		String phoneNumber = input.nextLine();
		gym = new Gym(name, managerName, phoneNumber);
		runMenu();
	}

	/**
	 * mainMenu() - This method displays the menu for the application, reads the
	 * menu option that the user entered and returns it.
	 * 
	 * @return the users menu choice
	 */
	private int mainMenu() {
		System.out.println("Gym Menu");
		System.out.println("---------");
		System.out.println("  1) Add a member");
		System.out.println("  2) List all members");
		System.out.println("  3) Remove a member (by index)");
		System.out.println("  4) Number of members in the gym");
		System.out.println("---------");
		System.out.println("  5) List gym details");
		System.out.println("  6) List members with ideal starting weight");
		System.out.println("  7) List members with a specific BMI category");
		System.out.println("  8) List all members stats imperically and metrically");
		System.out.println("-----------");
		System.out.println("  9) Save to XML");
		System.out.println("  10) Load from XML");
		System.out.println("-----------");
		System.out.println("  0) Exit");
		System.out.print("==>> ");
		int option = input.nextInt();
		return option;
	}

	/**
	 * This is the method that controls the loop of the Menu.
	 */
	private void runMenu() {
		int option = mainMenu();
		while (option != 0) {

			switch (option) {
			case 1:
				addMember();
				break;
			case 2:
				System.out.println(gym.listMembers());
				break;
			case 3:
				int index = 0;
				// list all members...
				// read index from user...make sure to use try n catch
				// pass index to remove
				gym.remove(index);
				break;
			case 4:
				System.out.println(gym.numberOfMembers());
				break;
			case 5:
				System.out.println(gym.toString());
				break;
			case 6:
				System.out.println(gym.listMembersWithIdealWeight());
				break;
			case 7:
				String category = "";
				/// read in category from user...
				System.out.println(gym.listBySpecificBMICategory(category));
				break;
			case 8:
				System.out.println(gym.listMemberDetailsImperialAndMetric());
				break;
			case 9:
				try {
					gym.save();
				} catch (Exception e) {
					System.out.println("Error writing to file: " + e);
				}
				break;
			case 10:
				try {
					gym.load();
				} catch (Exception e) {
					System.out.print("Error reading from file: " + e);
				}
				break;
			default:
				System.out.println("Invalid option entered: " + option);
				break;
			}

			// pause the program so that the user can read what we just printed to the
			// terminal window
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			input.nextLine(); // this second read is required - bug in Scanner class; a String read is ignored
								// straight after reading an int.

			// display the main menu again
			option = mainMenu();
		}

		// the user chose option 0, so exit the program
		System.out.println("Exiting... bye");
		System.exit(0);
	}

	public void addMember() {
		System.out.print("Id (between 100001 and 999999): ");
		int memberID = input.nextInt();
		System.out.print("Name (max 30 chars): ");
		String memberName = input.nextLine();
		memberName = input.nextLine();
		System.out.print("Addess: ");
		String memberAddress = input.nextLine();
		memberAddress = input.nextLine();
		System.out.print("Height (between 1 and 3 metres): ");
		double height = input.nextDouble();
		System.out.print("Starting weight (between 35kg and 250kg): ");
		double startingWeight = input.nextDouble();
		System.out.print("Gender (M/F): ");
		String gender = input.nextLine();
		gender = input.next();
		if (gender == "m" || gender == "M" || gender == "f" || gender == "F") {
		} else {
			gender = "Unspecified";
		}
		gym.add(new Member(memberID, memberName, memberAddress, height, startingWeight, gender));
	}

}