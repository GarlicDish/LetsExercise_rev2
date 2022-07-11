package service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.JDBCTemplate;
import dao.face.FileDao;
import dao.face.JoinDao;
import dao.impl.FileDaoImpl;
import dao.impl.JoinDaoImpl;
import dto.member.MemberDto;
import dto.member.MyExerciseDto;
import dto.member.PhotoDto;
import service.face.JoinService;

public class JoinServiceImpl implements JoinService {

	JoinDao joinDao = new JoinDaoImpl();
	private FileDao fileDao = new FileDaoImpl();

	@Override
	public int join(MemberDto member) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println("JoinServiceImpl join() : " + member.toString());
		int result = 0;

		if (joinDao.insert(conn, member)) {
			result = 1;
		} else
			result = 0;
		// DB삽입 성공시 commit, 실패시 rollback

		if (result > 0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);

		return result;
	}

	public int cosFileupload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int upresult = 0;

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
		String userPW2 = mul.getParameter("userPW2");
		String nickname = mul.getParameter("nickname");
		String email = mul.getParameter("email");

		int gender = Integer.parseInt(mul.getParameter("gender"));
		int areaNumber = Integer.parseInt(mul.getParameter("areaNumber"));

		int interestNumber = 1; // 값 미정, 미구현!~!
		int exerciseNumber = Integer.parseInt(mul.getParameter("exerciseNumber"));
		String exerciseExperience = mul.getParameter("ExerciseExperience");

		System.out.println("interestNumber" + interestNumber);
		System.out.println("exerciseNumber" + exerciseNumber);

//		----------------------------------------------
		if (!ServletFileUpload.isMultipartContent(req)) {

			req.setAttribute("typeerrormsg", "form태그의 enctype속성이 잘못되었습니다");

			req.setAttribute("alertMsg", "파일 업로드 불가");
			req.setAttribute("redirectURL", "/file/list");

			req.getRequestDispatcher("/WEB-INF/views/file/uploaderror.jsp").forward(req, resp);

		}

//			----------------파일저장-------------------------	

		System.out.println("[TEST] - forward코드 실행 이후");
		// 원본 파일 이름
		String origin = mul.getOriginalFileName("upfile");

		// 저장된 파일 이름
		String stored = mul.getFilesystemName("upfile");

		// 전달파라미터 정보 DB삽입하기

		MemberDto memberDto = new MemberDto();

		memberDto.setUserNumber(joinDao.getNextUserNo(conn));
		memberDto.setUserID(userID);
		memberDto.setUserPW(userPW);
		memberDto.setEmail(email);
		memberDto.setNickname(nickname);
		memberDto.setGender(gender);
		memberDto.setAreaNumber(areaNumber);

		MyExerciseDto myExerciseDto = new MyExerciseDto();
		myExerciseDto.setUserID(userID);
		myExerciseDto.setInterestNumber(interestNumber);
		myExerciseDto.setExerciseNumber(exerciseNumber);
		myExerciseDto.setExerciseExperience(exerciseExperience);

//			------------이하 기본값 --------------
		// 입력받은 날짜
		DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String dateStr = dateformat.format(date);
		// DB에 저장되는 날짜는 String 값이다.
		// 처음 날짜 생성시 date형식으로 생성하여 simpledateformat으로 가공한 후
		// DTO객체 안에 넣어서 다룰 때는 string형식으로 다룬다.

		memberDto.setJoinDate(dateStr);
		memberDto.setRePwDate(dateStr);

		// 회원관리용 변수 기본값 설정
		memberDto.setBlackListed(0);
		memberDto.setSocialMem(0);
		memberDto.setDeActivated(0);
		memberDto.setStoredName(stored); // 위쪽에 파일 저장할 때 쓴 변수임

		joinDao.insert(conn, memberDto);
		joinDao.Exerciseinsert(conn, memberDto, myExerciseDto);
		JDBCTemplate.commit(conn);
		System.out.println("cosFileupload() end ; 사진 외 회원정보 업로드 완료 ");

		upresult = 1; // 가입성공

		// 파일 정보 DTO
		PhotoDto photoDto = new PhotoDto();

		photoDto.setOriginname(origin);
		photoDto.setStoredname(stored);

		// 파일 정보 DB 삽입
		int res = fileDao.fileInsert(conn, photoDto);
		if (res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		return upresult;
	} // cosFileupload() end

	@Override
	public List<PhotoDto> list() {
		return fileDao.selectAll(JDBCTemplate.getConnection());
	}

} // class end
