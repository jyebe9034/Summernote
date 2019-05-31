package kh.semi.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.semi.dao.BoardDAO;
import kh.semi.dto.BoardDTO;
import kh.semi.dto.TempDTO;

@WebServlet("*.temp")
public class TempBoard extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");// 페이지내에 인코딩 정보가 없는 경우에 필요한 코드(ex. ajax)
		PrintWriter pw = response.getWriter();

		String reqUri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = reqUri.substring(ctxPath.length());

		BoardDAO dao = new BoardDAO();

		if(cmd.equals("/writer.temp")) {
			request.getSession().setAttribute("flag", "true");
			
			TempDTO tdto = new TempDTO();
			int maxSize = 10 * 1024 * 1024;

			SimpleDateFormat simpledf = new SimpleDateFormat("yy-MM-dd");
			Long tempTime = System.currentTimeMillis();
			String newDate = simpledf.format(tempTime);

			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String email = (String)request.getSession().getAttribute("loginEmail");

			File tempFile = new File(rootPath+email);
			if(!tempFile.exists()) {
				tempFile.mkdir();
			}

			String savePath = rootPath + email + "/" + newDate;
			tdto.setFilePath(savePath);
			String uploadFile = "";
			String newFileName = "";

			File uploadPath = new File(savePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}

			long currentTime = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			try {
				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				
				uploadFile = multi.getFilesystemName("filename");
				newFileName = currentTime + "." + uploadFile.substring(uploadFile.lastIndexOf(".")+1);
				tdto.setFileName(newFileName);

				File oldFile = new File(savePath + "/" + uploadFile);
				File newFile = new File(savePath  + "/" + newFileName);
				
				if(!oldFile.renameTo(newFile)) {
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(newFile));
					DataInputStream dis = new DataInputStream(new FileInputStream(oldFile));

					byte[] fileContents = new byte[(int)oldFile.length()];
					dis.readFully(fileContents);

					dos.write(fileContents);
					dos.flush();
					dos.close();
					dis.close();
					oldFile.delete();
				}

				BoardDTO dto = new BoardDTO();
				dto.setTitle(multi.getParameter("title"));
				dto.setWriter(multi.getParameter("writer"));
				dto.setAmount(Integer.parseInt(multi.getParameter("amount")));
				String duedate = multi.getParameter("dueDate");
				Date newdate = sdf.parse(duedate);
				Timestamp timestamp = new Timestamp(newdate.getTime());
				dto.setDueDate(timestamp);
				dto.setBank(multi.getParameter("select"));
				dto.setAccount(multi.getParameter("account"));
				dto.setContents(multi.getParameter("contents"));
				try {
					int result = dao.insertBoard(dto);
					System.out.println("result : "+result);
				}catch(Exception e) {
					e.printStackTrace();
				}
				int result = dao.insertTitleImg(tdto);
				System.out.println("titleresult : " + result);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(cmd.equals("/uploadImage.temp")) {
			request.getSession().setAttribute("flag", "false");
			
			TempDTO tdto = new TempDTO();
			int maxSize = 10 * 1024 * 1024;

			SimpleDateFormat simpledf = new SimpleDateFormat("yy-MM-dd");
			Long tempTime = System.currentTimeMillis();
			String newDate = simpledf.format(tempTime);

			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String email = (String)request.getSession().getAttribute("loginEmail");

			File tempFile = new File(rootPath+email);
			if(!tempFile.exists()) {
				tempFile.mkdir();
			}

			String savePath = rootPath + email + "/" + newDate;
			tdto.setFilePath(savePath);
			String uploadFile = "";
			String newFileName = "";

			File uploadPath = new File(savePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdir();
			}

			long currentTime = System.currentTimeMillis();

			try {
				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());				
				uploadFile = multi.getFilesystemName("file");
				newFileName = currentTime + "." + uploadFile.substring(uploadFile.lastIndexOf(".")+1);
				tdto.setFileName(newFileName);

				File oldFile = new File(savePath + "/" + uploadFile);
				File newFile = new File(savePath  + "/" + newFileName);
				
				if(!oldFile.renameTo(newFile)) {
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(newFile));
					DataInputStream dis = new DataInputStream(new FileInputStream(oldFile));

					byte[] fileContents = new byte[(int)oldFile.length()];
					dis.readFully(fileContents);

					dos.write(fileContents);
					dos.flush();
					dos.close();
					dis.close();
					oldFile.delete();
				}

				pw.append(email + "/" + newDate + "/"+ newFileName);

			}catch(Exception e) {
				e.printStackTrace();
			}

		}else if(cmd.equals("/deleteImage.temp")) {
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				e.printStackTrace();
			}

			String test = (String)request.getSession().getAttribute("flag");
			System.out.println("test : " + test);
			if(test.equals("false")) {
				String rootPath = this.getServletContext().getRealPath("/");
				String fileUrl = request.getParameter("src");
				System.out.println("fileUrl : " + fileUrl);
				String filePath = null;
				if(fileUrl.startsWith("http")) {
					filePath = fileUrl.replaceAll("http://.+?/", "");
				}else {
					filePath = fileUrl;
				}
				boolean deleteFile = new File(rootPath+filePath).delete();
				pw.print(deleteFile);
				System.out.println("delefeFile : " + deleteFile);
			}
			request.getSession().setAttribute("flag", "false");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
