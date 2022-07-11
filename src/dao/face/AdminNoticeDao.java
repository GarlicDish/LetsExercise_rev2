package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.board.FindFile;
import dto.board.Notice;
import util.Paging;

public interface AdminNoticeDao {

	/**
	 * Board테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 목록
	 */
	public List<Notice> selectAll(Connection conn);

	/**
	 * Board테이블 전체 조회 -> 페이징 처리 추가
	 * 
	 * @param conn   - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 목록
	 */
	public List<Notice> selectAll(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Board테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn    - DB연결 객체
	 * @param boardno - 조회할 게시글의 boardno를 가진 DTO객체
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int updateHit(Connection conn, Notice noticeno);

	/**
	 * 지정된 boardno의 게시글 조회하기
	 * 
	 * @param boardno - 조회할 게시글의 boardno를 가진 DTO객체
	 * @return Board - 조회된 게시글의 상세정보 DTO객체
	 */
	public Notice selectBoardByBoardno(Connection conn, Notice noticeno);

	/**
	 * 
	 * @param conn
	 * @param viewNotice
	 * @return
	 */
	public String selectNickByUserid(Connection conn, Notice viewNotice);

	public int insert(Connection conn, Notice notice);

	public String selectNickByUserno(Connection connection, Notice viewNotice);

	public int selectBoardno(Connection conn);

	public int insertFile(Connection conn, FindFile findFile);

	/**
	 * 게시글 입력
	 * 
	 * @param conn  - DB연결 객체
	 * @param board - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */

}
