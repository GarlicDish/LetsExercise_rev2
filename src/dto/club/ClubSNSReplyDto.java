package dto.club;

import java.util.Date;

public class ClubSNSReplyDto {
	public int ClubSNSReplyNumber;
	public int ClubSNSNumber;
	public int Writer;
	public String ReplyContent;
	public Date PostingDate;

	@Override
	public String toString() {
		return "ClubSNSReply [ClubSNSReplyNumber=" + ClubSNSReplyNumber + ", ClubSNSNumber=" + ClubSNSNumber
				+ ", Writer=" + Writer + ", ReplyContent=" + ReplyContent + ", PostingDate=" + PostingDate + "]";
	}

	public int getClubSNSReplyNumber() {
		return ClubSNSReplyNumber;
	}

	public void setClubSNSReplyNumber(int clubSNSReplyNumber) {
		ClubSNSReplyNumber = clubSNSReplyNumber;
	}

	public int getClubSNSNumber() {
		return ClubSNSNumber;
	}

	public void setClubSNSNumber(int clubSNSNumber) {
		ClubSNSNumber = clubSNSNumber;
	}

	public int getWriter() {
		return Writer;
	}

	public void setWriter(int writer) {
		Writer = writer;
	}

	public String getReplyContent() {
		return ReplyContent;
	}

	public void setReplyContent(String replyContent) {
		ReplyContent = replyContent;
	}

	public Date getPostingDate() {
		return PostingDate;
	}

	public void setPostingDate(Date postingDate) {
		PostingDate = postingDate;
	}

}
