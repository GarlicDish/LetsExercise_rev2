package dto;

import java.util.Date;

public class Ask {

	private int BoardNumber;
	private String Title;
	private String UserID;
	private String Content;
	private int Hit;
	private Date Write_date;
	
	
	@Override
	public String toString() {
		return "Ask [BoardNumber=" + BoardNumber + ", Title=" + Title + ", UserID=" + UserID + ", Content=" + Content + ", Hit="
				+ Hit + ", Write_date=" + Write_date + "]";
		
		
	}


	public int getBoardNumber() {
		return BoardNumber;
	}


	public void setBoardNumber(int boardnumber) {
		BoardNumber = boardnumber;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getUserID() {
		return UserID;
	}


	public void setUserID(String userID) {
		UserID = userID;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	public int getHit() {
		return Hit;
	}


	public void setHit(int hit) {
		Hit = hit;
	}


	public Date getWrite_date() {
		return Write_date;
	}


	public void setWrite_date(Date write_date) {
		Write_date = write_date;
	}
	
	
}
