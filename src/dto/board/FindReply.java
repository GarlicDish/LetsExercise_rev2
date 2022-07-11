package dto.board;

import java.sql.Date;

public class FindReply {

	private int findreplyno;
	private int member;
	private String content;
	private Date writedate;
	private Date updatedate;
	private int findboardno;

	@Override
	public String toString() {
		return "FindReply [findreplyno=" + findreplyno + ", member=" + member + ", content=" + content + ", writedate="
				+ writedate + ", updatedate=" + updatedate + ", findboardno=" + findboardno + "]";
	}

	public int getFindreplyno() {
		return findreplyno;
	}

	public void setFindreplyno(int findreplyno) {
		this.findreplyno = findreplyno;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public int getFindboardno() {
		return findboardno;
	}

	public void setFindboardno(int findboardno) {
		this.findboardno = findboardno;
	}

}
