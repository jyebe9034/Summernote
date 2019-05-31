package kh.semi.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.semi.dao.BoardDAO;
import kh.semi.dto.BoardDTO;
import kh.semi.dto.TitleImgDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");// 페이지내에 인코딩 정보가 없는 경우에 필요한 코드(ex. ajax)
		
		PrintWriter pw = response.getWriter();
		
		String reqUri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = reqUri.substring(ctxPath.length());
		
		BoardDAO dao = new BoardDAO();
		
		if(cmd.equals("/writer.board")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			Long currentTime = System.currentTimeMillis();
			String newDate = sdf.format(currentTime);
			
			String rootPath = this.getServletContext().getRealPath("/");
			String email = (String)request.getSession().getAttribute("loginEmail");
			String filePath = rootPath + email + "/" + newDate;
//			System.out.println(rootPath);
			
			File uploadPath = new File(filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}
			
//			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
//			diskFactory.setRepository(new File(rootPath + "/WEB-INF/semi"));
//			ServletFileUpload sfu = new ServletFileUpload(diskFactory);
//			sfu.setSizeMax(10 * 1024 * 1024);
			
//			sfu.setHeaderEncoding("utf-8");
			
			try {
//				List<FileItem> items = sfu.parseRequest(request);
				BoardDTO dto = new BoardDTO();
				TitleImgDTO tdto = new TitleImgDTO();
//				for(FileItem fi : items) {
//					if(fi.isFormField()) {
//						
//						System.out.println(fi.getFieldName() + " : " + fi.getString("utf-8"));
//						if(fi.getFieldName().equals("title")) {
//							dto.setTitle(fi.getString());
//							System.out.println(fi.getString("utf8"));
//						}else if(fi.getFieldName().equals("writer")) {
//							dto.setWriter(fi.getString());
//							System.out.println(fi.getString("utf8"));
//						}else if(fi.getFieldName().equals("amount")) {
//							dto.setAmount(Integer.parseInt(fi.getString()));
//							System.out.println(fi.getString("utf8"));
//						}else if(fi.getFieldName().equals("dueDate")) {
//							dto.setDueDate(fi.getString());
//							System.out.println(fi.getString("utf8"));
//						}else if(fi.getFieldName().equals("select")) {
//							dto.setBank(fi.getString());
//							System.out.println(fi.getString("utf8"));
//						}else if(fi.getFieldName().equals("account")) {
//							dto.setAccount(fi.getString());
//							System.out.println(fi.getString("utf8"));
//						}else if(fi.getFieldName().equals("contents")) {
//							dto.setContents(fi.getString());
//							System.out.println(fi.getString("utf8"));
//						}
//					}else {
//						tdto.setOriFileName(fi.getName());
//						tdto.setFileSize(fi.getSize());
//						tdto.setFilePath(filePath);
//						
//						String tempFileName = null;
//						while(true) {
//							try {
//								long tempTime = System.currentTimeMillis();
//								tempFileName = tempTime+"_"+fi.getName();
//								fi.write(new File(filePath+"/"+tempFileName));
//								tdto.setFileName("title_"+tempFileName);
//								System.out.println("title_"+tempFileName);
//								break;
//							}catch(Exception e) {
//								System.out.println("파일 이름 재설정.");
//							}
//						}
//					}
//				}
				int result = dao.insertBoard(dto);
//				int fileResult = dao.insertTitleImg(tdto);
				System.out.println(result);
//				System.out.println(fileResult);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else if(cmd.equals("/uploadImage.board")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			Long currentTime = System.currentTimeMillis();
			String newDate = sdf.format(currentTime);
			
			String rootPath = this.getServletContext().getRealPath("/");
			String email = (String)request.getSession().getAttribute("loginEmail");
			String filePath = rootPath + email + "/" + newDate;
			System.out.println(rootPath);
			
			File uploadPath = new File(filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}
			
//			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
//			diskFactory.setRepository(new File(rootPath + "/WEB-INF/semi"));
//			ServletFileUpload sfu = new ServletFileUpload(diskFactory);
//			sfu.setSizeMax(10 * 1024 * 1024);
//			
//			try {
//				List<FileItem> items = sfu.parseRequest(request);
//				for(FileItem fi : items) {
//					if(fi.getSize() == 0) {continue;}
//					TitleImgDTO dto = new TitleImgDTO();
//					dto.setOriFileName(fi.getName());
//					System.out.println(fi.getName());
//					dto.setFileSize(fi.getSize());
//					System.out.println(fi.getSize());
//					dto.setFilePath(filePath);
//					System.out.println(filePath);
//					
//					String tempFileName = null;
//					while(true) {
//						try {
//							long tempTime = System.currentTimeMillis();
//							tempFileName = tempTime+"_"+fi.getName();
//							fi.write(new File(filePath+"/"+tempFileName));
//							dto.setFileName(tempFileName);
//							System.out.println(tempFileName);
//							break;
//						}catch(Exception e) {
//							System.out.println("파일 이름 재설정.");
//						}
//					}
//					pw.append(email+"/"+newDate+"/"+dto.getFileName());
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
