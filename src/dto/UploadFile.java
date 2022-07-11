package dto;

public class UploadFile {
	
	private int boardnumber;
	private int filenumber;
	private String originName;
	private String storedName;

	@Override
	public String toString() {
		return "UploadFile [fileno=" + filenumber + ", originName=" + originName + ", storedName=" + storedName + "]";
	}
	

	public int getBoardnumber() {
		return boardnumber;
	}


	public void setBoardnumber(int boardnumber) {
		this.boardnumber = boardnumber;
	}


	public int getFilenumber() {
		return filenumber;
	}


	public void setFilenumber(int filenumber) {
		this.filenumber = filenumber;
	}


	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getStoredName() {
		return storedName;
	}
	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}
	
}