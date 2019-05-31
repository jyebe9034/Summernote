package kh.semi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kh.semi.dto.BoardDTO;
import kh.semi.dto.TempDTO;

public class BoardDAO {
	static int recordCountPerPage = 10;
	static int naviCountPerPage = 10;
	public static int pageTotalCount;

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "semi";
		String pw = "semi";
		return DriverManager.getConnection(url,user,pw);
	}

	public int insertBoard(BoardDTO dto)throws Exception{
		String sql = "insert into Board values(b_no_seq.nextval,?,?,?,?,?,?,?,default,default,default,default)";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getWriter());
			pstat.setInt(3, dto.getAmount());
			pstat.setString(4, dto.getBank());
			pstat.setString(5, dto.getAccount());
			pstat.setTimestamp(6, dto.getDueDate());
			pstat.setString(7, dto.getContents());
			
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}

	}
	private PreparedStatement pstatForSelectOneArticle(Connection con, int boardNo)throws Exception{
		String sql = "select * from board where b_no=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, boardNo);
		return pstat;
	}

	public BoardDTO selectOneArticle(int boardNo)throws Exception{
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = pstatForSelectOneArticle(con, boardNo);
				ResultSet rs = pstat.executeQuery();
				){
			BoardDTO dto = new BoardDTO();
			if(rs.next()) {
				dto.setBoardNo(rs.getInt("b_no"));
				dto.setTitle(rs.getString("b_title"));
				dto.setWriter(rs.getString("b_writer"));
				dto.setAmount(rs.getInt("b_amount"));
				dto.setBank(rs.getString("b_bank"));
				dto.setAccount(rs.getString("b_account"));
				dto.setDueDate(rs.getTimestamp("b_due_date"));
				dto.setContents(rs.getString("b_contents"));
				dto.setViewCount(rs.getInt("b_viewcount"));
				dto.setWriteDate(rs.getTimestamp("b_writedate"));
				dto.setRecommend(rs.getInt("b_recommend"));
				dto.setSumAmount(rs.getInt("b_sum_amount"));
				return dto;
			}
			return null;
		}
	}
	
//	public int insertTitleImg(TitleImgDTO dto) throws Exception {
//		String sql = "insert into title_img values(t_fileSeq_seq.nextval, ?, ?, ?, ?)";
//		try(
//				Connection con = this.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				){
//			pstat.setString(1, dto.getFileName());
//			pstat.setString(2, dto.getOriFileName());
//			pstat.setString(3, dto.getFilePath());
//			pstat.setLong(4, dto.getFileSize());
//			int result = pstat.executeUpdate();
//			con.commit();
//			return result;
//		}
//	}

	public int insertTitleImg(TempDTO dto) throws Exception {
		String sql = "insert into title_img values(t_fileSeq_seq.nextval, ?, 'dunno', ?, 0)";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getFileName());
			pstat.setString(2, dto.getFilePath());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
//	public PreparedStatement pstatForGetTitleImg(Connection con, int boardNo) throws Exception {
//		String sql = "select * from title_img where t_b_no=?";
//		PreparedStatement pstat = con.prepareStatement(sql);
//		pstat.setInt(1, boardNo);
//		return pstat;
//	}
//	public TitleImgDTO getTitleImg(int boardNo) throws Exception {
//		try(
//				Connection con = this.getConnection();
//				PreparedStatement pstat = this.pstatForGetTitleImg(con, boardNo);
//				ResultSet rs = pstat.executeQuery();
//				){
//			if(rs.next()) {
//				TitleImgDTO dto = new TitleImgDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
//				return dto;
//			}
//		}
//		return null;
//	}
}

