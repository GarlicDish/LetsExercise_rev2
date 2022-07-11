package service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.JDBCTemplate;
import dao.face.MemberInfoDao;
import dao.impl.MemberInfoDaoImpl;
import dto.member.MemberDto;
import dto.member.PhotoDto;
import service.face.MemberInfoUpdateService;

public class MemberInfoUpdateServiceImpl implements MemberInfoUpdateService{
	
	
	
	public int cosFileupload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		int upresult = 0;

		int usernumber = (int)session.getAttribute("userno");
		
		String saveDirectory = req.getServletContext().getRealPath("upload");
		File folder = new File(saveDirectory);
		folder.mkdirs();
		int maxPostSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();

		MultipartRequest mul = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, policy);
		Connection conn = JDBCTemplate.getConnection();

		// 전달파라미터 처리하기

		String userID = mul.getParameter("userID");
		String userPW = mul.getParameter("userPW");
		String nickname = mul.getParameter("nickname");
		String email = mul.getParameter("email");
		
		if (!ServletFileUpload.isMultipartContent(req)) {

			req.setAttribute("typeerrormsg", "form태그의 enctype속성이 잘못되었습니다");

			req.setAttribute("alertMsg", "파일 업로드 불가");
			req.setAttribute("redirectURL", "/file/list");

			req.getRequestDispatcher("/WEB-INF/views/file/uploaderror.jsp").forward(req, resp);

		}

//			----------------파일저장-------------------------	

		System.out.println("[TEST] - forward코드 실행 이후");
		String origin = mul.getOriginalFileName("upfile");

		String stored = mul.getFilesystemName("upfile");

		// 전달파라미터 정보 DB삽입하기

		MemberDto memberDto = new MemberDto();

		memberDto.setUserNumber(usernumber);
		memberDto.setUserID(userID);
		memberDto.setUserPW(userPW);
		memberDto.setEmail(email);
		memberDto.setNickname(nickname);

		MemberInfoDao memberInfoDao = new MemberInfoDaoImpl();
		upresult = memberInfoDao.infoupdate(conn, memberDto);
		
		if(upresult == 1) System.out.println( "사진 외 정보 업데이트 성공"); 

		// 파일 정보 DTO
		PhotoDto photoDto = new PhotoDto();

		photoDto.setUsernumber(usernumber);
		photoDto.setOriginname(origin);
		photoDto.setStoredname(stored);

		// 파일 정보 DB 삽입
	int res=0;

	System.out.println("isPhotoExist "+memberInfoDao.isPhotoExist(photoDto));
	if(!memberInfoDao.isPhotoExist(photoDto) || photoDto.getStoredname()==null) {
		res = memberInfoDao.fileInsert(conn, photoDto);
		if (res > 0) {
			System.out.println("---업데이트정보---");
			System.out.println("memberDto :"+memberDto);
			System.out.println("photoDto :"+photoDto);
			memberInfoDao.sessionUpdate(conn, req, memberDto, photoDto);
			System.out.println("session :"+session.toString());
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return res;
	} 
	else {
		res = memberInfoDao.fileUpdate(conn, photoDto);
			if (res > 0) {
				System.out.println("---업데이트정보---");
				System.out.println("memberDto :"+memberDto);
				System.out.println("photoDto :"+photoDto);
				memberInfoDao.sessionUpdate(conn, req, memberDto, photoDto);
				System.out.println("session :"+session.toString());
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
	}
	return upresult;
	}
	
}// cosFileupload() end

	