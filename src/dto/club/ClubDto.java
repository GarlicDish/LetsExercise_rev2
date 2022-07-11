package dto.club;

import java.util.Date;

public class ClubDto {
	private int ClubNumber;
	private int ClubChiefNumber;
	private int ClubExerciseNumber;
	private String ClubName;
	private int ClubArea;
	private Boolean ClubActivated = true;
	private Date CreationDate;
	private String Introduction;

	@Override
	public String toString() {
		return "Club [ClubNumber=" + ClubNumber + ", ClubChiefNumber=" + ClubChiefNumber + ", ClubExerciseNumber="
				+ ClubExerciseNumber + ", ClubName=" + ClubName + ", ClubArea=" + ClubArea + ", ClubActivated="
				+ ClubActivated + ", CreationDate=" + CreationDate + ", Introduction=" + Introduction + "]";
	}

	public String getIntroduction() {
		return Introduction;
	}

	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}

	public Date getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}

	public int getClubNumber() {
		return ClubNumber;
	}

	public void setClubNumber(int clubNumber) {
		ClubNumber = clubNumber;
	}

	public int getClubChiefNumber() {
		return ClubChiefNumber;
	}

	public void setClubChiefNumber(int clubChiefNumber) {
		ClubChiefNumber = clubChiefNumber;
	}

	public int getClubExerciseNumber() {
		return ClubExerciseNumber;
	}

	public void setClubExerciseNumber(int clubExerciseNumber) {
		ClubExerciseNumber = clubExerciseNumber;
	}

	public String getClubName() {
		return ClubName;
	}

	public void setClubName(String clubName) {
		ClubName = clubName;
	}

	public int getClubArea() {
		return ClubArea;
	}

	public void setClubArea(int clubArea) {
		ClubArea = clubArea;
	}

	public Boolean getClubActivated() {
		return ClubActivated;
	}

	public void setClubActivated(Boolean clubActivated) {
		ClubActivated = clubActivated;
	}

}
