package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.board.QnaReply;

public interface QnaReplyService {

	/**
	 * SNS 글 번호 가져오기
	 * 
	 * @param req - 페이지로부터 받은 전달 파라미터
	 * @return - SNS글 번호가 담긴 ClubSNSReply 객체
	 */
	QnaReply getQnano(HttpServletRequest req);

	/**
	 * 리플 삭제
	 * 
	 * @param csr
	 */
	void delete(QnaReply csr);

	/**
	 * 전달받은 파라미터를 DTO로 변환하고 즉시, DB 등록
	 * 
	 * @param csr
	 */
	void write(QnaReply csr);

	/**
	 * 
	 * @param csr
	 * @return
	 */
	QnaReply getQnaReplyNumber(QnaReply csr);

	/**
	 * req로 전달받은 댓글 수정한 값 입력하기
	 * 
	 * @param req
	 */
	void updateReply(HttpServletRequest req);

	/**
	 * 기존댓글내용 불러오기
	 * 
	 * @param csr
	 * @return
	 */
	QnaReply getQrdata(QnaReply csr);

}
