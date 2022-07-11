package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.ClubDao;
import dao.face.ClubSNSDao;
import dao.impl.ClubDaoImpl;
import dao.impl.ClubSNSDaoImpl;
import dto.club.ClubDto;
import dto.club.ClubSNSAttachedPhoto;
import dto.club.ClubSNSDto;
import service.face.ClubSNSService;

public class ClubSNSServiceImpl implements ClubSNSService {

	ClubSNSDao clubSNSDao = new ClubSNSDaoImpl();
	ClubDao clubDao = new ClubDaoImpl();

	@Override
	public ClubDto getClubnumber(HttpServletRequest req) {

		ClubDto club = new ClubDto();

		System.out.println(req.getParameter("clubnumber"));

		String param = req.getParameter("clubnumber");
		System.out.println("param 값 : " + param);

		if (param != null && !"".equals(param)) {
			club.setClubNumber(Integer.parseInt(param));
			System.out.println("clubno 대입한 club dto =" + club);
			return club;
		} else {
			return null;
		}
	}

	@Override
	public List<ClubSNSDto> selectSNSBySNSno(ClubDto club) {

		Connection conn = JDBCTemplate.getConnection();

		return clubSNSDao.selectByClubNumber(conn, club);
	}

	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BoardService - wirte(), 글 및 파일 업로드");
		// -------------------------

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1. multipart/form-data 형식이 아닐 경우 파일 업로드 중단 처리

		if (!isMultipart) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");

			// fileupload()메소드 중단시키기
			// -> response 응답을 해도 자바코드가 중단되지 않는다.
			// -> HTTP 응답과 별개로 return; 을 꼭 적어야 하는 경우가 많음

			return;
		}

		// 1-2. multipart/form-data 형식이 맞을 경우 파일 업로드 정상 처리
		System.out.println("[OK] multipart 형식이 맞음");

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 3. 업로드된 파일 아이템의 용량(크기)이 설정값보다 작으면 메모리에서 처리하도록 설정
		int maxMem = 1 * 1024 * 1024; // == 1MB
		factory.setSizeThreshold(maxMem);

		ServletContext context = req.getServletContext();

		// 서버에 배포된 서블릿의 실제 경로를 이용해서, tmp 폴더 알아내기
		String path = context.getRealPath("tmp");
		System.out.println("FileService fileupload() - tmp : " + path);

		// 임시파일을 저장할 폴더 객체
		File tmpRepository = new File(path);

		// 폴더 생성하기 - 존재하면 생성하지 않음
		tmpRepository.mkdir();

		// 임시파일을 저장할 폴더를 factory 객체에 설정하기
		factory.setRepository(tmpRepository);

		// 5. 파일 업로드를 수행하는 객체 생성

		// factory 객체에 설정한 사항들을 반영해놓는다.
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 최대 업로드 허용 사이즈
		int maxFile = 10 * 1024 * 1024; // 10mb까지 허용

		// 파일 업로드 용량 제한 사이즈 설정
		upload.setFileSizeMax(maxFile);

		// ===== 파일 업로드 준비 완료 =====

		// 6. 파일 업로드 처리
		// -> 업로드된 데이터 추출하기(파싱)

		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 전달 파라미터 추출데이터 확인
		for (FileItem item : items) {
			System.out.println("전달 파라미터로 부터 온 추출 데이터 확인 = " + item);
		}

		// 폼필더 전달 데이터를 저장할 DTO 객체
		ClubSNSDto clubSNS = new ClubSNSDto();

		// 파일 정보를 저장할 DTO 객체
		ClubSNSAttachedPhoto attachedPhoto = new ClubSNSAttachedPhoto();

		// 파일 아이템 반복자
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.getSize() <= 0) {
				// 빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}

			// --- 2) 폼 필드일 경우
			if (item.isFormField()) {

				// 키 추출
				String key = item.getFieldName();

				// 값 추출 (try- catch 적용)
				String value = null; // try-catch 구문이 끝나고 나서도 변수를 사용할 수 있도록 밖에서 선언.
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				// key에 맞게 value를 DTO에 삽입
				if ("content".equals(key)) {
					clubSNS.setContent(value);
				} else if ("writer".equals(key)) {
					clubSNS.setWriter(Integer.parseInt(value));
				} else if ("clubnumber".equals(key)) {
					clubSNS.setClubNumber(Integer.parseInt(value));
				}
				System.out.println("전달 파라미터를 입력한 clubSNS 객체 : " + clubSNS);
			} // if( item.isFormField() ) END

			// --- 3) 파일일 경우
			if (!item.isFormField()) {

				String uid = UUID.randomUUID().toString().split("-")[0]; // 8자리 UUID
				File uploadFolder = new File(context.getRealPath("upload"));

				uploadFolder.mkdir();

				String origin = item.getName();
				String stored = uid;

				File up = new File(uploadFolder, stored);

				try {
					item.write(up);
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 업로드된 파일의 정보를 DTO 객체에 저장하기
				attachedPhoto.setOriginName(origin);
				attachedPhoto.setChangedName(stored);
				attachedPhoto.setFileSize((int) item.getSize());

			} // if ( !item.isFormField() ) END

		} // while( iter.hasNext() ) END

		// DB 연결 객체
		Connection conn = JDBCTemplate.getConnection();

		int clubNoToInsert = clubSNSDao.selectClubNo(conn);
		System.out.println("대입할 ClubSNS 게시글의 번호 : " + clubNoToInsert);
		clubSNS.setClubSNSNumber(clubNoToInsert);

		clubSNS.setWriter((int) req.getSession().getAttribute("userno"));

		System.out.println("대입할 club SNS의 정보 : " + clubSNS);
		if (clubSNSDao.insert(conn, clubSNS) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		if (attachedPhoto.getFileSize() != 0) {
			attachedPhoto.setClubSNSNumber(clubNoToInsert);
			System.out.println("대입할 club SNS attached Photo의 정보 : " + attachedPhoto);
			if (clubSNSDao.insertPhoto(conn, attachedPhoto) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}

	@Override
	public List<Map<String, Object>> listWithMap(ClubDto club) {

		System.out.println("ClubSNSServiceImpl - listWithMap - club객체 : " + club);

		Connection conn = JDBCTemplate.getConnection();

		List<Map<String, Object>> lMap = clubSNSDao.getUserinfoAndProfileBySNSnumber(conn, club);

		return lMap;
	}

	@Override
	public void delete(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		clubSNSDao.delete(conn, req);
	}

	@Override
	public ClubSNSDto getSNSno(HttpServletRequest req) {

		System.out.println("/////clubSNSService getSNSno() 시작");

		ClubSNSDto clubSNS = new ClubSNSDto();

		String param = req.getParameter("clubsnsnumber");
		if (param != null && !"".equals(param)) {
			clubSNS.setClubSNSNumber(Integer.parseInt(param));
			return clubSNS;
		} else {
			return null;
		}
	}

	@Override
	public ClubSNSDto view(ClubSNSDto snSno) {
		System.out.println("/////clubSNSService view() 시작");

		Connection conn = JDBCTemplate.getConnection();

		snSno = clubSNSDao.selectSNSbySNSno(conn, snSno);

		return snSno;
	}

	@Override
	public void update(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("///ClubSNSService update() - SNS 수정");

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}

		System.out.println("[OK] multipart 형식이 맞음");

		DiskFileItemFactory factory = new DiskFileItemFactory();

		int maxMem = 1 * 1024 * 1024; // == 1MB
		factory.setSizeThreshold(maxMem);

		ServletContext context = req.getServletContext();

		String path = context.getRealPath("tmp");
		System.out.println("FileService fileupload() - tmp : " + path);

		File tmpRepository = new File(path);

		tmpRepository.mkdir();

		factory.setRepository(tmpRepository);

		ServletFileUpload upload = new ServletFileUpload(factory);

		int maxFile = 10 * 1024 * 1024; // 10mb까지 허용

		upload.setFileSizeMax(maxFile);

		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		for (FileItem item : items) {
			System.out.println("전달 파라미터로 부터 온 추출 데이터 확인 = " + item);
		}

		ClubSNSDto clubSNS = new ClubSNSDto();

		ClubSNSAttachedPhoto attachedPhoto = new ClubSNSAttachedPhoto();

		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.getSize() <= 0) {
				continue;
			}

			if (item.isFormField()) {
				String key = item.getFieldName();

				String value = null; // try-catch 구문이 끝나고 나서도 변수를 사용할 수 있도록 밖에서 선언.
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				if ("content".equals(key)) {
					clubSNS.setContent(value);
				} else if ("clubsnsnumber".equals(key)) {
					clubSNS.setClubSNSNumber(Integer.parseInt(value));
				}
			} // if( item.isFormField() ) END

			if (!item.isFormField()) {

				String uid = UUID.randomUUID().toString().split("-")[0]; // 8자리 UUID

				File uploadFolder = new File(context.getRealPath("upload"));

				uploadFolder.mkdir(); // make directory

				String origin = item.getName();
				String stored = uid;

				File up = new File(uploadFolder, stored);

				try {
					item.write(up);
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}

				attachedPhoto.setOriginName(origin);
				attachedPhoto.setChangedName(stored);
				attachedPhoto.setFileSize((int) item.getSize());

			} // if ( !item.isFormField() ) END

		} // while( iter.hasNext() ) END

		Connection conn = JDBCTemplate.getConnection();

		if (clubSNSDao.update(conn, clubSNS) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		if (attachedPhoto.getFileSize() != 0) {
			attachedPhoto.setClubSNSNumber(clubSNS.getClubSNSNumber());
			if (attachedPhoto.getFileSize() != 0 && clubSNSDao.insertPhoto(conn, attachedPhoto) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

		System.out.println("/////DB UPDATE 끗");

	}

	@Override
	public boolean checkMember(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		if (clubSNSDao.checkMember(conn, req) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ClubSNSDto getSNS(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		ClubSNSDto cs = new ClubSNSDto();

		cs.setClubSNSNumber(Integer.parseInt(req.getParameter("clubsnsnumber")));

		return clubSNSDao.selectSNSbySNSno(conn, cs);
	}

	@Override
	public ClubDto getClubnumber(ClubSNSDto clubSNS) {

		return clubSNSDao.getClubNo(JDBCTemplate.getConnection(), clubSNS);
	}

	@Override
	public Map<String, Object> getUserUserProfileClubSNSbyClubSNSno(ClubSNSDto clubSNS) {

		System.out.println("/////getUserUserProfileClubSNSbyClubSNSno() - clubSNS 객체 : " + clubSNS);

		return clubSNSDao.getUserUserProfileClubSNSbyClubSNSno(JDBCTemplate.getConnection(), clubSNS);
	}

}
