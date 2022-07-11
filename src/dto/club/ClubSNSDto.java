package dto.club;

import java.util.Date;

public class ClubSNSDto {
	public int ClubSNSNumber;
	public int ClubNumber;
	public int Writer;
	public String Content;
	public Date PostingDate;
	public Date RevisionDate;

	@Override
	public String toString() {
		return "ClubSNS [ClubSNSNumber=" + ClubSNSNumber + ", ClubNumber=" + ClubNumber + ", Writer=" + Writer
				+ ", Content=" + Content + ", PostingDate=" + PostingDate + ", RevisionDate=" + RevisionDate + "]";
	}

	public int getClubSNSNumber() {
		return ClubSNSNumber;
	}

	public void setClubSNSNumber(int clubSNSNumber) {
		ClubSNSNumber = clubSNSNumber;
	}

	public int getClubNumber() {
		return ClubNumber;
	}

	public void setClubNumber(int clubNumber) {
		ClubNumber = clubNumber;
	}

	public int getWriter() {
		return Writer;
	}

	public void setWriter(int writer) {
		Writer = writer;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Date getPostingDate() {
		return PostingDate;
	}

	public void setPostingDate(Date postingDate) {
		PostingDate = postingDate;
	}

	public Date getRevisionDate() {
		return RevisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		RevisionDate = revisionDate;
	}

}
