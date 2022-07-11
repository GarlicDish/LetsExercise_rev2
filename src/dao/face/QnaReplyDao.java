package dao.face;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import dto.board.QnaReply;

public interface QnaReplyDao {

	/**
	 * DB에서 리플 삭제
	 * 
	 * @param conn
	 * @param fr
	 */
	int delete(Connection conn, QnaReply qr);

	/**
	 * 전달 받은 객체를 DB에 입력
	 * 
	 * @param conn - dB 연결 객체
	 * @param fr   - 전달 객체
	 */
	int write(Connection conn, QnaReply qr);

	/**
	 * DB에 저장할 FindReply 번호값을 dto에 입력
	 * 
	 * @param conn - DB 연결객체
	 * @param csr  - 비어있는 csr dto 객체
	 * @return - csrnumber-seq 값을 가진 dto 객체 반환
	 */
	QnaReply getQnaReplyNumber(Connection conn, QnaReply csr);

	/**
	 * csr 정보 전체 불러오기
	 * 
	 * @param conn - DB 연결객체
	 * @param csr  - csr 번호를 가지고 있는 dto 객체
	 * @return - 해당 csr 번호의 모든 정보를 갖고 있는 dto 객체
	 */
	QnaReply getQRdata(Connection conn, QnaReply csr);

	/**
	 * csr 정보 업데이트
	 * 
	 * @param conn - DB 연결객체
	 * @param req  - 수정한 값을 파라미터로 갖고 있음.
	 * @return
	 */
	int update(Connection conn, HttpServletRequest req);

}
