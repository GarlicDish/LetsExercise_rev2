package dto;

import java.sql.Date;

public class BoardDto {

	private int boardnumber;
	private String boardtitle;
	private String boardcontent;
	private Date boarddate;
	private int views;
	private String nickname;
	private String userid;
	private int usernumber;
	private int boardcode;
	private String boardcategory;
	private String boardstate;

	public Date getBoarddate() {
		return boarddate;
	}

	public void setBoarddate(Date boarddate) {
		this.boarddate = boarddate;
	}

	public String getBoardcategory() {
		return boardcategory;
	}

	public void setBoardcategory(String boardcategory) {
		this.boardcategory = boardcategory;
	}

	public String getBoardstate() {
		return boardstate;
	}

	public final String getNickname() {
		return nickname;
	}

	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setBoardstate(String boardstate) {
		this.boardstate = boardstate;
	}

	public int getBoardcode() {
		return boardcode;
	}

	public void setBoardcode(int boardcode) {
		this.boardcode = boardcode;
	}

	public int getUsernumber() {
		return usernumber;
	}

	public void setUsernumber(int usernumber) {
		this.usernumber = usernumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBoardcontent() {
		return boardcontent;
	}

	public void setBoardcontent(String boardcontent) {
		this.boardcontent = boardcontent;
	}

	public int getBoardnumber() {
		return boardnumber;
	}

	public void setBoardnumber(int boardnumber) {
		this.boardnumber = boardnumber;
	}

	public String getBoardtitle() {
		return boardtitle;
	}

	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

}
