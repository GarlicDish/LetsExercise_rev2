package dto.board;

public class commentDto {

	private int commentnumber;
	private int boardnumber;
	private int boardcode;
	private String nickname;
	private String commentDate;
	private String commentText;

	public final String getNickname() {
		return nickname;
	}

	public final void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

}
