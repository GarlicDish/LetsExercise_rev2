package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.ClubSNSReplyDto;

public interface ClubSNSReplyService {

	/**
	 * SNS 글 번호 가져오기
	 * 
	 * @param req - 페이지로부터 받은 전달 파라미터
	 * @return - SNS글 번호가 담긴 ClubSNSReply 객체
	 */
	ClubSNSReplyDto getSNSno(HttpServletRequest req);

	/**
	 * 전달받은 clubSNS 번호로 관련 댓글 불러오기
	 * 
	 * @param req - clubSNS 번호를 담은 전달 파라미터
	 * @return - clubSNS 번호로 조회한 clubSNSreply, userinfo, userprofile 객체들의 배열리스트
	 */
	List<Map<String, Object>> selectReply(HttpServletRequest req);

	/**
	 * 리플 삭제
	 * 
	 * @param csr
	 */
	void delete(ClubSNSReplyDto csr);

	/**
	 * 전달받은 파라미터를 DTO로 변환하고 즉시, DB 등록
	 * 
	 * @param csr
	 */
	void write(ClubSNSReplyDto csr);

	/**
	 * 
	 * @param csr
	 * @return
	 */
	ClubSNSReplyDto getClubSNSReplyNumber(ClubSNSReplyDto csr);

	/**
	 * csr 번호가 담긴 객체로, 해당 csr의 모든 정보를 담은 객체 반환
	 * 
	 * @param csr
	 */
	ClubSNSReplyDto getCSRdata(ClubSNSReplyDto csr);

	/**
	 * req로 전달받은 댓글 수정한 값 입력하기
	 * 
	 * @param req
	 */
	void updateReply(HttpServletRequest req);

}
