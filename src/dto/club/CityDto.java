package dto.club;

public class CityDto {

	private int citynumber;
	private String cityname;

	@Override
	public String toString() {
		return "City [citynumber=" + citynumber + ", cityname=" + cityname + "]";
	}

	public int getCitynumber() {
		return citynumber;
	}

	public void setCitynumber(int citynumber) {
		this.citynumber = citynumber;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

}
