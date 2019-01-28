
public class UserInformation {

	private String highSchool;
	private String university;
	private String employer;
	private String currentCity;
	private String hometown;
	
	UserInformation() {
		this.highSchool = "";
		this.university = "";
		this.employer = "";
		this.currentCity = "";
		this.hometown = "";
	}
	
	void updateInformation(String highSchool, String university, String employer, String currentCity, String hometown) {
		this.highSchool = highSchool;
		this.university = university;
		this.employer = employer;
		this.currentCity = currentCity;
		this.hometown = hometown;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public String getUniversity() {
		return university;
	}

	public String getEmployer() {
		return employer;
	}


	public String getCurrentCity() {
		return currentCity;
	}

	public String getHometown() {
		return hometown;
	}
	
}
