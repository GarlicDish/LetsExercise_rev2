package dto.board;

import java.util.Date;

public class Find {

	
	private int boardno;
	private int boardcode;
	private String userid;
	private int userno;
	private String title;
	private String content;
	private int hit;
	private Date writedate;
	private String isdelete;
	
	
	@Override
	public String toString() {
		return "Find [boardno=" + boardno + ", boardcode=" + boardcode + ", userid=" + userid + ", userno=" + userno
				+ ", title=" + title + ", content=" + content + ", hit=" + hit + ", writedate=" + writedate
				+ ", isdelete=" + isdelete + "]";
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public int getBoardcode() {
		return boardcode;
	}
	public void setBoardcode(int boardcode) {
		this.boardcode = boardcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
}
