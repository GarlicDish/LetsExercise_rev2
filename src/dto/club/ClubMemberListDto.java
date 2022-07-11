package dto.club;

import java.util.Date;

public class ClubMemberListDto {
	private int clubNumber;
	private int clubMember;
	private Date memberdate;
	private Boolean approved;

	@Override
	public String toString() {
		return "ClubMemberList [clubNumber=" + clubNumber + ", clubMember=" + clubMember + ", memberdate=" + memberdate
				+ ", approved=" + approved + "]";
	}

	public int getClubNumber() {
		return clubNumber;
	}

	public void setClubNumber(int clubNumber) {
		this.clubNumber = clubNumber;
	}

	public int getClubMember() {
		return clubMember;
	}

	public void setClubMember(int clubMember) {
		this.clubMember = clubMember;
	}

	public Date getMemberdate() {
		return memberdate;
	}

	public void setMemberdate(Date memberdate) {
		this.memberdate = memberdate;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

}
