package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.ClubSNSReplyDto;

public interface ClubSNSReplyDao {

	/**
	 * SNS 번호를 이용해 reply 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param req  - SNS 글 번호를 담고 있는 전달 파라미터
	 * @return - 조회된 SNS 댓글 리스트
	 */
	List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req);

	/**
	 * DB에서 리플 삭제
	 * 
	 * @param conn
	 * @param csr
	 */
	int delete(Connection conn, ClubSNSReplyDto csr);

	/**
	 * 전달 받은 객체를 DB에 입력
	 * 
	 * @param conn - dB 연결 객체
	 * @param csr  - 전달 객체
	 */
	int write(Connection conn, ClubSNSReplyDto csr);

	/**
	 * DB에 저장할 ClubSNSReply 번호값을 dto에 입력
	 * 
	 * @param conn - DB 연결객체
	 * @param csr  - 비어있는 csr dto 객체
	 * @return - csrnumber-seq 값을 가진 dto 객체 반환
	 */
	ClubSNSReplyDto getClubSNSReplyNumber(Connection conn, ClubSNSReplyDto csr);

	/**
	 * csr 정보 전체 불러오기
	 * 
	 * @param conn - DB 연결객체
	 * @param csr  - csr 번호를 가지고 있는 dto 객체
	 * @return - 해당 csr 번호의 모든 정보를 갖고 있는 dto 객체
	 */
	ClubSNSReplyDto getCSRdata(Connection conn, ClubSNSReplyDto csr);

	/**
	 * csr 정보 업데이트
	 * 
	 * @param conn - DB 연결객체
	 * @param req  - 수정한 값을 파라미터로 갖고 있음.
	 * @return
	 */
	int update(Connection conn, HttpServletRequest req);

}
