package dto.club;

public class ExerciseDto {
	private int exercisenumber;
	private String exercisename;

	@Override
	public String toString() {
		return "Exercise [exercisenumber=" + exercisenumber + ", exercisename=" + exercisename + "]";
	}

	public int getExercisenumber() {
		return exercisenumber;
	}

	public void setExercisenumber(int exercisenumber) {
		this.exercisenumber = exercisenumber;
	}

	public String getExercisename() {
		return exercisename;
	}

	public void setExercisename(String exercisename) {
		this.exercisename = exercisename;
	}

}
