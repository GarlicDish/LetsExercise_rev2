package dto.club;

public class ClubSNSAttachedPhoto {
	public int ClubSNSAttachedPhotoNumber;
	public int ClubSNSNumber;
	public String OriginName;
	public String ChangedName;
	public int FileSize;
	public String UploadDate;

	@Override
	public String toString() {
		return "ClubSNSAttachedPhoto [ClubSNSAttachedPhotoNumber=" + ClubSNSAttachedPhotoNumber + ", ClubSNSNumber="
				+ ClubSNSNumber + ", OriginName=" + OriginName + ", ChangedName=" + ChangedName + ", FileSize="
				+ FileSize + ", UploadDate=" + UploadDate + "]";
	}

	public int getClubSNSAttachedPhotoNumber() {
		return ClubSNSAttachedPhotoNumber;
	}

	public void setClubSNSAttachedPhotoNumber(int clubSNSAttachedPhotoNumber) {
		ClubSNSAttachedPhotoNumber = clubSNSAttachedPhotoNumber;
	}

	public int getClubSNSNumber() {
		return ClubSNSNumber;
	}

	public void setClubSNSNumber(int clubSNSNumber) {
		ClubSNSNumber = clubSNSNumber;
	}

	public String getOriginName() {
		return OriginName;
	}

	public void setOriginName(String originName) {
		OriginName = originName;
	}

	public String getChangedName() {
		return ChangedName;
	}

	public void setChangedName(String changedName) {
		ChangedName = changedName;
	}

	public int getFileSize() {
		return FileSize;
	}

	public void setFileSize(int fileSize) {
		FileSize = fileSize;
	}

	public String getUploadDate() {
		return UploadDate;
	}

	public void setUploadDate(String uploadDate) {
		UploadDate = uploadDate;
	}

}
