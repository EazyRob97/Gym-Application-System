/**
 * A Gym class which was specifically developed to demonstrate the use of an
 * ArrayList of Member.
 * 
 * @author Robert Solomon
 * @version 1.0
 */

public class Member {
	private int memberId;
	private String memberName;
	private String memberAddress;
	private double height;
	private double startingWeight;
	private String gender;

	// Constructor for the Member Class containing the private parameters that is to
	// be returned
	public Member(int memberId, String memberName, String memberAddress, double height, double startingWeight,
			String gender) {
		if (memberId > 100000 && memberId <= 999999)
			this.memberId = memberId;
		else
			this.memberId = 100000;

		if (memberName.length() <= 30)
			this.memberName = memberName;
		else
			this.memberName = memberName.substring(0, 30);
		this.memberAddress = memberAddress;
		if (height <= 3 && height >= 1)
			this.height = height;
		if (startingWeight <= 250 && startingWeight >= 35)
			this.startingWeight = startingWeight;
		if (gender.equals("m") || gender.equals("M"))
			this.gender = "M";
		else if (gender.equals("f") || gender.equals("F"))
			this.gender = "F";
		else
			this.gender = "Unspecified";
	}

	/**
	 * public getter and setter methods to be accessed for the member class
	 * 
	 * @return
	 */
	public int getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public double getHeight() {
		return height;
	}

	public double getStartingWeight() {
		return startingWeight;
	}

	public String getMemberGender() {
		return gender;
	}

	public void setMemberId(int memberId) {
		if (memberId > 100000 && memberId <= 999999)
			this.memberId = memberId;
	}

	public void setMemberName(String memberName) {
		if (memberName.length() <= 30)
			this.memberName = memberName;
		else
			this.memberName = memberName.substring(0, 30);
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public void setHeight(double height) {
		if (height <= 3 && height >= 1)
			this.height = height;
	}

	public void setStartingWeight(double startingWeight) {
		if (startingWeight <= 250 && startingWeight >= 35)
			this.startingWeight = startingWeight;
	}

	public void setGender(String gender) {
		if (gender.equals("m") || gender.equals("M"))
			this.gender = "M";
		if (gender.equals("f") || gender.equals("F"))
			this.gender = "F";
	}

	/**
	 * Formal Method for formatting decimal output
	 * 
	 * @param num
	 * @return
	 */
	private double toTwoDecimalPlaces(double num) {
		return (int) (num * 100) / 100.0; // returns this value truncated to two decimal places.
	}

	public double calculateBMI() {
		return toTwoDecimalPlaces(startingWeight / (height * height));
	}

	public double convertHeightMetresToInches() {
		return toTwoDecimalPlaces(height * 39.37);
	}

	public double convertWeightKGtoPounds() {
		return toTwoDecimalPlaces(startingWeight * 2.2);
	}

	public String determineBMICategory() {
		double bmi = calculateBMI();
		if (bmi < 15)
			return "\"VERY SEVERELY UNDERWEIGHT\"";
		else if (bmi >= 15 && bmi < 16)
			return "\"SEVERELY UNDERWEIGHT\"";
		else if (bmi >= 16 && bmi < 18.5)
			return "\"UNDERWEIGHT\"";
		else if (bmi >= 18.5 && bmi < 25)
			return "\"NORMAL\"";
		else if (bmi >= 25 && bmi < 30)
			return "\"OVERWEIGHT\"";
		else if (bmi >= 30 && bmi < 35)
			return "\"MODERATELY OBESE\"";
		else if (bmi >= 35 && bmi < 40)
			return "\"SEVERELY OBESE\"";
		else
			return "\"VERY SEVERELY OBESE\"";
	}

	/**
	 * (Devine Method) public boolean method to check for the ideal body weight of
	 * each member
	 * 
	 * @return
	 */
	public boolean isIdealBodyWeight() {
		double idealWeight;
		if (convertHeightMetresToInches() <= 60) {
			if (gender == "M")
				idealWeight = 50;
			else
				idealWeight = 45.5;
		} else {
			if (gender == "M")
				idealWeight = 50 + ((convertHeightMetresToInches() - 60) * 2.3);
			else
				idealWeight = 45.5 + ((convertHeightMetresToInches() - 60) * 2.3);
		}
		/**
		 * if statement to return True when checking for the condition if the
		 * calculation calculated above is within +-2 tolerance of the members starting
		 * weight otherwise return false if its not
		 */
		if (idealWeight >= (startingWeight - 2) && idealWeight <= (startingWeight + 2))
			return true;
		else
			return false;
	}

	public String toString() {
		return "Member Id: " + memberId + ", Member Name: " + memberName + ", Member Address: " + memberAddress
				+ ".\n\t Height: " + height + " " + ", Starting Weight: " + startingWeight + ", Gender: " + gender
				+ "BMI of " + calculateBMI() + " " + determineBMICategory();
	}
}