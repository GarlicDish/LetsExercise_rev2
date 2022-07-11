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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.QnaDao;
import dao.impl.QnaDaoImpl;
import dto.board.BoardFile;
import dto.board.Qna;
import service.face.QnaService;
import util.Paging;

public class QnaServiceImpl implements QnaService {

	private QnaDao qnaDao = new QnaDaoImpl();

	@Override
	public List<Qna> getList() {

		// 게시글 전체 조회 결과 반환
		return qnaDao.selectAll(JDBCTemplate.getConnection());

	}

	@Override
	public List<Qna> getList(Paging paging) {

		// 페이징 적용해서 조회 결과 반환
		return qnaDao.selectAll(JDBCTemplate.getConnection(), paging);

	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] BoardService getPaging() - curPage값이 null이거나 비어있음");
		}

		// 총 게시글 수 조회하기
		int totalCount = qnaDao.selectCntAll(JDBCTemplate.getConnection());

		// Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public Qna getQnano(HttpServletRequest req) {

		// 전달파라미터 boardno를 저장할 DTO객체 생성
		Qna qnano = new Qna();

		String param = req.getParameter("boardno");
		if (param != null && !"".equals(param)) {
			qnano.setBoardno(Integer.parseInt(param));
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
		}

		return qnano;
	}

	@Override
	public Qna view(Qna qnano) {

		Connection conn = JDBCTemplate.getConnection();

		// 조회수 증가
		if (qnaDao.updateHit(conn, qnano) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// 게시글 조회
		Qna qna = qnaDao.selectBoardByBoardno(conn, qnano);

		return qna;
	}

	@Override
	public void write(HttpServletRequest req) {

		// 파일업로드형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		// multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if (!isMultipart) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);

		// 서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();

		// 임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);

		// 파일업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);

		// 파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 게시글 정보 DTO객체
		Qna qna = new Qna();

		// 첨부파일 정보 DTO객체
		BoardFile boardFile = new BoardFile();

		// 파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();

			// --- 1) 빈 파일에 대한 처리 ---
			if (item.getSize() <= 0) {

				// 빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}

			// --- 2) 폼 필드에 대한 처리 ---
			if (item.isFormField()) {

				// 키 추출하기
				String key = item.getFieldName();

				// 값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				// key에 맞게 value를 DTO에 삽입
				if ("title".equals(key)) {
					qna.setTitle(value);
				} else if ("content".equals(key)) {
					qna.setContent(value);
				}

			} // if( item.isFormField() ) end

			// --- 3) 파일에 대한 처리 ---
			if (!item.isFormField()) {

				// UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; // 8자리 UUID

				// 파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();

				// 파일명 처리
				String origin = item.getName();
				String stored = uid;

				// 업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);

				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거

				} catch (Exception e) {
					e.printStackTrace();
				}

				// 업로드된 파일의 정보를 DTO객체에 저장하기
				boardFile.setOriginname(origin);
				boardFile.setStoredname(stored);
				boardFile.setFilesize((int) item.getSize());

			} // if( !item.isFormField() ) end

		} // while( iter.hasNext() ) end

		// DB연결 객체
		Connection conn = JDBCTemplate.getConnection();

		// 게시글 번호 생성
		int qnano = qnaDao.selectBoardno(conn);

		// 게시글 정보 삽입
		qna.setBoardno(qnano);
		if (qna.getTitle() == null || "".equals(qna.getTitle())) {
			qna.setTitle("(제목없음)");
		}
		qna.setUserno((int) req.getSession().getAttribute("userno"));// TEST
		qna.setUserid((String) req.getSession().getAttribute("userID"));

		System.out.println("qna 객체 정보 : " + qna);

		if (qnaDao.insert(conn, qna) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// 첨부파일 정보 삽입 **************************************************8
		if (boardFile.getFilesize() != 0) {
			boardFile.setBoardno(qnano);

			if (qnaDao.insertFile(conn, boardFile) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		System.out.println(qna);
		System.out.println(boardFile);

	}

	@Override
	public String getNick(Qna viewQna) {
		return qnaDao.selectNickByUserid(JDBCTemplate.getConnection(), viewQna);
	}

	@Override
	public BoardFile viewFile(Qna viewQna) {
		return qnaDao.selectFile(JDBCTemplate.getConnection(), viewQna);

	}

	@Override
	public void update(HttpServletRequest req) {

		// 파일업로드형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		// multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if (!isMultipart) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);

		// 서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();

		// 임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);

		// 파일업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);

		// 파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 게시글 정보 DTO객체
		Qna qna = new Qna();

		// 첨부파일 정보 DTO객체
		BoardFile boardFile = new BoardFile();

		// 파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();

			// --- 1) 빈 파일에 대한 처리 ---
			if (item.getSize() <= 0) {

				// 빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}

			// --- 2) 폼 필드에 대한 처리 ---
			if (item.isFormField()) {

				// 키 추출하기
				String key = item.getFieldName();

				// 값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				// key에 맞게 value를 DTO에 삽입
				if ("title".equals(key)) {
					qna.setTitle(value);

				} else if ("content".equals(key)) {
					qna.setContent(value);

				} else if ("boardno".equals(key)) {
					qna.setBoardno(Integer.parseInt(value));
				}

			} // if( item.isFormField() ) end

			// --- 3) 파일에 대한 처리 ---
			if (!item.isFormField()) {

				// UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; // 8자리 UUID

				// 파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();

				// 파일명 처리
				String origin = item.getName();
				String stored = uid;

				// 업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);

				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거

				} catch (Exception e) {
					e.printStackTrace();
				}

				// 업로드된 파일의 정보를 DTO객체에 저장하기
				boardFile.setOriginname(origin);
				boardFile.setStoredname(stored);
				boardFile.setFilesize((int) item.getSize());

			} // if( !item.isFormField() ) end

		} // while( iter.hasNext() ) end

		// DB연결 객체
		Connection conn = JDBCTemplate.getConnection();

		// 게시글 정보 삽입
		if (qna.getTitle() == null || "".equals(qna.getTitle())) {
			qna.setTitle("(제목없음)");
		}
		if (qnaDao.update(conn, qna) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// 첨부파일 정보 삽입
		if (boardFile.getFilesize() != 0) {
			boardFile.setBoardno(qna.getBoardno());

			if (qnaDao.insertFile(conn, boardFile) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

	}

	@Override
	public void delete(Qna qna) {
		Connection conn = JDBCTemplate.getConnection();

		// 첨부 파일 삭제
		if (qnaDao.deleteFile(conn, qna) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		// 글 삭제
		if (qnaDao.delete(conn, qna) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

	@Override
	public List<Map<String, Object>> selectReply(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		return qnaDao.getMapList(conn, req);
	}

}
