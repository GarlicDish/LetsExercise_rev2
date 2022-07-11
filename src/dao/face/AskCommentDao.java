package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.AskComment;
import util.Paging;

public interface AskCommentDao {

	/**
	 * Comment 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Coment> - Comment 전체 조회 결과 목록
	 */
	public List<AskComment> selectAll(Connection conn);

	/**
	 * Comment 전체 조회 (페이징 처리 추가됨)
	 *  
	 * @param conn   - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Coment> - Comment 전체 조회 결과 목록
	 */
	public List<AskComment> selectAll(Connection conn, Paging paging);

	/**
	 * 총 댓글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Comment 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	

}
