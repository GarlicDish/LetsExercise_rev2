package dto;

import java.util.Date;

public class Userprofile {
	private int ProfileNumber;
	private int UserNumber;
	private String Originname;
	private String Storedname;
	
	@Override
	public String toString() {
		return "Userprofile [ProfileNumber=" + ProfileNumber + ", UserNumber=" + UserNumber + ", Originname="
				+ Originname + ", Storedname=" + Storedname + "]";
	}

	public int getProfileNumber() {
		return ProfileNumber;
	}

	public void setProfileNumber(int profileNumber) {
		ProfileNumber = profileNumber;
	}

	public int getUserNumber() {
		return UserNumber;
	}

	public void setUserNumber(int userNumber) {
		UserNumber = userNumber;
	}

	public String getOriginname() {
		return Originname;
	}

	public void setOriginname(String originname) {
		Originname = originname;
	}

	public String getStoredname() {
		return Storedname;
	}

	public void setStoredname(String storedname) {
		Storedname = storedname;
	}

	

	
	
}
