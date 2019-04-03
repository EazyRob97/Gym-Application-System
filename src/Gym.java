import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * A Gym class which was specifically developed to demonstrate the use of an
 * ArrayList of Member.
 * 
 * @author Robert Solomon
 * @version 1.0
 */
public class Gym {

	private String gymName;
	private String managerName;
	private String phoneNumber;
	private ArrayList<Member> members;

	/**
	 * Constructor for objects of class Gym, where the phone number instance is to
	 * be set to "unknown"
	 * 
	 * @param
	 */
	public Gym(String gymName, String managerName) {
		members = new ArrayList<Member>(); // ArrayList of members been initiated
		if (gymName.length() <= 30) // statement for the Gym name to be set to a limit of 30 characters
			this.gymName = gymName;
		else
			this.gymName = gymName.substring(0, 30);
		this.managerName = managerName; // no validation performed here, so it will remain as it is.
		this.phoneNumber = "unknown";
	}

	/**
	 * Constructor for objects of class Gym defined in order for the Gym constructor
	 * to compile
	 * 
	 * @param
	 */
	public Gym(String gymName, String managerName, String phoneNumber) {
		members = new ArrayList<Member>();
		if (gymName.length() <= 30) // statement for the Gym name to be set to a limit of 30 characters
			this.gymName = gymName;
		else
			this.gymName = gymName.substring(0, 30);
		this.managerName = managerName;
		if (isNumeric(phoneNumber))
			this.phoneNumber = phoneNumber;
		else
			this.phoneNumber = "unknown";
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void add(Member member) {
		members.add(member);
	}

	/***
	 * public static boolean function to check for fails
	 */
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * public getters and setters that is accesses the constructor parameters
	 * 
	 * @return the instant fields
	 */
	public String getGymName() {
		return gymName;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setGymName(String gymName) {
		if (gymName.length() <= 30)
			this.gymName = gymName;
		else
			this.gymName = gymName.substring(0, 30);
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (isNumeric(phoneNumber)) // statement to check whether a String is numeric
			this.phoneNumber = phoneNumber;
		else
			this.phoneNumber = phoneNumber.replaceAll("\\D+", ""); // replace everything except digits with an empty
																	// String.
	}

	public int numberOfMembers() {
		return members.size();
	}

	public String listMembers() {
		String listOfMembers = "";
		int index = 0;
		for (Member member : members) {
			listOfMembers = listOfMembers + index + ": " + member.toString() + "\n";
			index++;
		}
		if (listOfMembers.equals("")) {
			return "There are no members in the gym";
		} else {
			return listOfMembers;
		}
	}

	/**
	 * Public Method to list all the members of a specific BMI Category
	 * 
	 * @param category
	 *            String is returned.
	 * @return
	 */
	public String listBySpecificBMICategory(String category) {
		if (members.size() == 0)
			return "There are no members in the gym";
		String listOfMembers = "";
		int index = 0;
		// for loop to test for each member with a precise BMI category
		for (Member member : members) {
			if (member.determineBMICategory().contains(category)) {
				listOfMembers = listOfMembers + index + ": " + member.toString() + "\n";
				index++;
			}
		}
		if (listOfMembers == "")
			return "No members in this BMI category";
		else
			return listOfMembers;
	}

	/**
	 * Public Method to list all the members weight and height both imperically and
	 * metrically
	 * 
	 * @return
	 */
	public String listMemberDetailsImperialAndMetric() {
		String str = "";

		if (members.size() == 0)
			return "There are no members in the gym";
		// for loot to check for the
		for (Member member : members) {
			str += member.getMemberName() + ":\t" + member.getStartingWeight() + " kg ("
					+ member.convertWeightKGtoPounds() + " lbs) \t" + member.getHeight() + " metres ("
					+ member.convertHeightMetresToInches() + " inches)." + "\n";
		}
		return str;
	}

	/**
	 * public method to list all the members that have an ideal body weight
	 * 
	 * @return
	 */
	public String listMembersWithIdealWeight() {
		String str = "";

		if (members.size() == 0) // checking for if there are no members in the gym return that there are none
			return "There are no members in the gym";

		for (Member member : members) {
			if (member.isIdealBodyWeight())
				str += listMembers(); // empty string (local variable is added to call the "listMembers" method in
										// this class
		}

		if (str.equals("")) {
			return "There are no members in the gym with an ideal weight.";
		} else
			return str;
	}

	public void remove(int index) {
		if (!(index > (numberOfMembers() - 1) || index < 0))
			members.remove(index);
	}

	public String toString() {
		return "Gym Name: " + gymName + ", Manager: " + managerName + ", Phone Number: " + phoneNumber
				+ ". \n\nList of members in the gym:\n" + listMembers();
	}

	/**
	 * Suppresses the errors that displays on the consoles and contains a load
	 * method to load, hence "void load" the saved once the system is running in the
	 * Menu Controller (which is controlling the members class and the gym class)
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
		members = (ArrayList<Member>) is.readObject();
		is.close();
	}

	/**
	 * Contains a save method to save, hence "void save" the data that was inputed
	 * into the system while the it is running in the Menu Controller (which is
	 * controlling the members class and the gym class)
	 * 
	 * @throws Exception
	 */
	public void save() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
		out.writeObject(members);
		out.close();
	}
}
