package kh.semi.dto;

public class TitleImgDTO {
	private int boardNo;
	private int fileSeq;
	private String fileName;
	private String oriFileName;
	private String filePath;
	private long fileSize;
	
	public TitleImgDTO() {}
	public TitleImgDTO(int boardNo, int fileSeq, String fileName, String oriFileName, String filePath,
			long fileSize) {
		this.boardNo = boardNo;
		this.fileSeq = fileSeq;
		this.fileName = fileName;
		this.oriFileName = oriFileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriFileName() {
		return oriFileName;
	}
	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
}
