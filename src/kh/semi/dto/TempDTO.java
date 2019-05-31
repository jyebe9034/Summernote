package kh.semi.dto;

public class TempDTO {
	private int boardNo;
	private String fileName;
	private String filePath;
	
	public TempDTO() {}
	public TempDTO(int boardNo, String fileName, String filePath) {
		super();
		this.boardNo = boardNo;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
