package dto;

import java.sql.Date;

public class commentDto {
	
	private int commentnumber;
	private int boardnumber;
	private int boardcode;
	private int usernumber;
	private Date commentDate;
	private String commentText;
	
	
	
	
	
	public final int getUsernumber() {
		return usernumber;
	}
	public final void setUsernumber(int usernumber) {
		this.usernumber = usernumber;
	}
	public int getCommentnumber() {
		return commentnumber;
	}
	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}
	public int getBoardnumber() {
		return boardnumber;
	}
	public void setBoardnumber(int boardnumber) {
		this.boardnumber = boardnumber;
	}
	public int getBoardcode() {
		return boardcode;
	}
	public void setBoardcode(int boardcode) {
		this.boardcode = boardcode;
	}

	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	
	

}
