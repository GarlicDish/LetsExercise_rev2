package dto;

import java.sql.Date;

public class AskComment {

	private int CommentNumber;
	private int BoardNumber;
	private String BoardCode;
	private Date CommentDate;
	private String CommentText;
	private int UserNumber;
	
	@Override
	public String toString() {
		return "AskComment [CommentNumber=" + CommentNumber + ", BoardNumber=" + BoardNumber + ", BoardCode="
				+ BoardCode + ", CommentDate=" + CommentDate + ", CommentText=" + CommentText + ", UserNumber="
				+ UserNumber + "]";
	}

	public int getCommentNumber() {
		return CommentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		CommentNumber = commentNumber;
	}

	public int getBoardNumber() {
		return BoardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		BoardNumber = boardNumber;
	}

	public String getBoardCode() {
		return BoardCode;
	}

	public void setBoardCode(String boardCode) {
		BoardCode = boardCode;
	}

	public Date getCommentDate() {
		return CommentDate;
	}

	public void setCommentDate(Date commentDate) {
		CommentDate = commentDate;
	}

	public String getCommentText() {
		return CommentText;
	}

	public void setCommentText(String commentText) {
		CommentText = commentText;
	}

	public int getUserNumber() {
		return UserNumber;
	}

	public void setUserNumber(int userNumber) {
		UserNumber = userNumber;
	}
	

	
	
	
}
