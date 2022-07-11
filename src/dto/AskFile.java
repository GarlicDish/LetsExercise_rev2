package dto;

import java.util.Date;

public class AskFile {
	private int FileNumber;
	private int BoardNumber;
	private String Originname;
	private String Storedname;
	private int FileSize;
	private Date WriteDate;
	
	@Override
	public String toString() {
		return "AskFile [FileNumber=" + FileNumber + ", BoardNumber=" + BoardNumber + ", Originname=" + Originname
				+ ", Storedname=" + Storedname + ", FileSize=" + FileSize + ", WriteDate=" + WriteDate + "]";
	}

	public int getFileNumber() {
		return FileNumber;
	}

	public void setFileNumber(int fileNumber) {
		FileNumber = fileNumber;
	}

	public int getBoardNumber() {
		return BoardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		BoardNumber = boardNumber;
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

	public int getFileSize() {
		return FileSize;
	}

	public void setFileSize(int fileSize) {
		FileSize = fileSize;
	}

	public Date getWriteDate() {
		return WriteDate;
	}

	public void setWriteDate(Date writeDate) {
		WriteDate = writeDate;
	}
	
	
	
	
	
}
