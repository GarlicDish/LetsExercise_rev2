package dto.club;

public class ClubEmblemDto {
	public int ClubEmblemNumber;
	public int ClubNumber;
	public String OriginalName;
	public String ChangedName;
	public String FileType;
	public int FileSize;
	public String FileDirectory;
	public String FileRevisionDate;

	@Override
	public String toString() {
		return "ClubEmblem [ClubEmblemNumber=" + ClubEmblemNumber + ", ClubNumber=" + ClubNumber + ", OriginalName="
				+ OriginalName + ", ChangedName=" + ChangedName + ", FileType=" + FileType + ", FileSize=" + FileSize
				+ ", FileDirectory=" + FileDirectory + ", FileRevisionDate=" + FileRevisionDate + "]";
	}

	public int getClubEmblemNumber() {
		return ClubEmblemNumber;
	}

	public void setClubEmblemNumber(int clubEmblemNumber) {
		ClubEmblemNumber = clubEmblemNumber;
	}

	public int getClubNumber() {
		return ClubNumber;
	}

	public void setClubNumber(int clubNumber) {
		ClubNumber = clubNumber;
	}

	public String getOriginalName() {
		return OriginalName;
	}

	public void setOriginalName(String originalName) {
		OriginalName = originalName;
	}

	public String getChangedName() {
		return ChangedName;
	}

	public void setChangedName(String changedName) {
		ChangedName = changedName;
	}

	public String getFileType() {
		return FileType;
	}

	public void setFileType(String fileType) {
		FileType = fileType;
	}

	public int getFileSize() {
		return FileSize;
	}

	public void setFileSize(int fileSize) {
		FileSize = fileSize;
	}

	public String getFileDirectory() {
		return FileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		FileDirectory = fileDirectory;
	}

	public String getFileRevisionDate() {
		return FileRevisionDate;
	}

	public void setFileRevisionDate(String fileRevisionDate) {
		FileRevisionDate = fileRevisionDate;
	}

}
