package dto.member;

public class MyExerciseDto {
	private String userID;
	private int InterestNumber;
	private int ExerciseNumber;
	private String ExerciseExperience;

	@Override
	public String toString() {
		return "MyExerciseDto [userID=" + userID + ", InterestNumber=" + InterestNumber + ", ExerciseNumber="
				+ ExerciseNumber + ", ExerciseExperience=" + ExerciseExperience + "]";
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getInterestNumber() {
		return InterestNumber;
	}

	public void setInterestNumber(int interestNumber) {
		InterestNumber = interestNumber;
	}

	public int getExerciseNumber() {
		return ExerciseNumber;
	}

	public void setExerciseNumber(int exerciseNumber) {
		ExerciseNumber = exerciseNumber;
	}

	public String getExerciseExperience() {
		return ExerciseExperience;
	}

	public void setExerciseExperience(String exerciseExperience) {
		ExerciseExperience = exerciseExperience;
	}

}
