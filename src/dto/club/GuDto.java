package dto.club;

public class GuDto {

	private int gunumber;
	private int citynumber;
	private String guname;

	@Override
	public String toString() {
		return "Gu [gunumber=" + gunumber + ", citynumber=" + citynumber + ", guname=" + guname + "]";
	}

	public int getGunumber() {
		return gunumber;
	}

	public void setGunumber(int gunumber) {
		this.gunumber = gunumber;
	}

	public int getCitynumber() {
		return citynumber;
	}

	public void setCitynumber(int citynumber) {
		this.citynumber = citynumber;
	}

	public String getGuname() {
		return guname;
	}

	public void setGuname(String guname) {
		this.guname = guname;
	}

}
