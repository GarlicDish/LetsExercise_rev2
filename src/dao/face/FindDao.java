package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.board.Find;
import dto.board.FindFile;
import util.Paging;

public interface FindDao {

	/**
	 * Board테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 목록
	 */
	public List<Find> selectAll(Connection conn);

	/**
	 * Board테이블 전체 조회 -> 페이징 처리 추가
	 * 
	 * @param conn   - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - Board테이블 전체 조회 결과 목록
	 */
	public List<Find> selectAll(Connection conn, Paging paging);

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
	public int updateHit(Connection conn, Find Findno);

	/**
	 * 지정된 boardno의 게시글 조회하기
	 * 
	 * @param conn    - DB연결 객체
	 * @param boardno - 조회할 게시글의 boardno를 가진 DTO객체
	 * @return Board - 조회된 게시글의 상세정보 DTO객체
	 */
	public Find selectBoardByBoardno(Connection conn, Find Findno);

	/**
	 * 게시글 입력
	 * 
	 * @param conn  - DB연결 객체
	 * @param board - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insert(Connection conn, Find find);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn      - DB연결 객체
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserno(Connection conn, Find viewFind);

	/**
	 * 시퀀스를 이용하여 다음 게시글 번호를 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return int - 다음 게시글 번호
	 */
	public int selectBoardno(Connection conn);

	/**
	 * 첨부파일 삽입
	 * 
	 * @param conn     - DB연결 객체
	 * @param findFile - 첨부파일 정보
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFile(Connection conn, FindFile findFile);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param conn      - DB연결 객체
	 * @param viewBoard - 조회할 게시글 번호
	 * @return BoardFile - 첨부파일 정보
	 */
	public FindFile selectFile(Connection conn, Find viewBoard);

	/**
	 * 게시글 수정
	 * 
	 * @param board - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, Find find);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, Find find);

	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, Find find);

	/**
	 * SNS 번호를 이용해 reply 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param req  - SNS 글 번호를 담고 있는 전달 파라미터
	 * @return - 조회된 SNS 댓글 리스트
	 */
	List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req);

}
