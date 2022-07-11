package dto.board;

import java.sql.Date;

public class QnaReply {

	private int qnareplyno;
	private int member;
	private String content;
	private Date writedate;
	private Date updatedate;
	private int qnaboardno;

	@Override
	public String toString() {
		return "QnaReply [qnareplyno=" + qnareplyno + ", member=" + member + ", content=" + content + ", writedate="
				+ writedate + ", updatedate=" + updatedate + ", qnaboardno=" + qnaboardno + "]";
	}

	public int getQnareplyno() {
		return qnareplyno;
	}

	public void setQnareplyno(int qnareplyno) {
		this.qnareplyno = qnareplyno;
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

	public int getQnaboardno() {
		return qnaboardno;
	}

	public void setQnaboardno(int qnaboardno) {
		this.qnaboardno = qnaboardno;
	}

}
