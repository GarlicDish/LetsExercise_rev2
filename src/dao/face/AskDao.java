package dao.face;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Ask;
import dto.AskFile;
import util.Paging;

public interface AskDao {

	/**
	 * 문의내역 전체 조회
	 * 
	 * @param conn   - DB연결 객체
	 * @param paging - 페이징 객체
	 * @return List<Ask> - 전체 내역 조회 결과
	 */
	public List<Ask> selectAll(HttpServletRequest req, Connection conn, Paging paging);

	/**
	 * 총 문의글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - 문의내역 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 문의글의 조회수 증가됨
	 * 
	 * @param conn    - DB연결 객체
	 * @param boardno - 문의글의 boardno를 가진 DTO객체
	 * @return int - 수행 결과
	 */
	public int updateHit(Connection conn, Ask boardno);

	/**
	 * 지정된 boardno의 문의글 조회하기
	 * 
	 * @param conn    - DB연결 객체
	 * @param boardno - 문의글의 boardno를 가진 DTO객체
	 * @return Board - 문의글 상세정보 DTO객체
	 */
	// public Ask selectBoardByUserid(Connection conn, Ask userid);
	public Ask selectBoardByBoardno(Connection conn, Ask boardno);

	/**
	 * 문의글 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param ask  - 삽입될 문의글 정보
	 * @return int - 수행 결과
	 */
	public int insert(HttpServletRequest req, Connection conn, Ask ask);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn    - DB연결 객체
	 * @param viewAsk - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserid(Connection conn, Ask viewAsk);
	// public int selectNickByBoardno(Connection conn,Ask viewAsk);

	/**
	 * 시퀀스를 이용하여 다음 문의글 번호를 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return int - 다음 게시글 번호
	 */
	public int selectBoardno(Connection conn);

	/**
	 * 첨부파일 삽입
	 * 
	 * @param conn    - DB연결 객체
	 * @param askFile - 첨부파일 정보
	 * @return int - 수행 결과
	 */
	public int insertFile(Connection conn, AskFile askFile);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param conn    - DB연결 객체
	 * @param viewAsk - 조회할 게시글 번호
	 * @return AskFile - 첨부파일 정보
	 */
	public AskFile selectFile(Connection conn, Ask viewAsk);

	/**
	 * 문의글 수정
	 * 
	 * @param ask - 수정할 내용이 들어있는 객체
	 */
	public int update(Connection conn, Ask ask);

	/**
	 * 문의글 삭제
	 * 
	 * @param ask - 삭제할 게시글 번호가 들어있는 객체
	 */
	public int delete(Connection conn, Ask ask);

	/**
	 * 문의글에 첨부된 파일 기록 삭제
	 * 
	 * @param ask - 삭제할 게시글 번호가 들어있는 객체
	 */
	public int deleteFile(Connection conn, Ask ask);

}
