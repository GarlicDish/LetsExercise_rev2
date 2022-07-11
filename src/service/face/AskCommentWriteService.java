package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.AskComment;

public interface AskCommentWriteService {

	/**
	 * SNS 글 번호 가져오기
	 * 
	 * @param req - 페이지로부터 받은 전달 파라미터
	 * @return - SNS글 번호가 담긴 AskComment 객체
	 */
	AskComment getSNSno(HttpServletRequest req);

	/**
	 * 전달받은 board 번호로 관련 댓글 불러오기
	 * 
	 * @param req - board 번호를 담은 전달 파라미터
	 * @return - board 번호로 조회한 askcomment, userinfo, userprofile 객체들의 배열리스트
	 */
	List<Map<String, Object>> selectComment(HttpServletRequest req);

	/**
	 * 리플 삭제
	 * 
	 * @param ac
	 */
	void delete(AskComment ac);

	/**
	 * 전달받은 파라미터를 DTO로 변환하고 즉시, DB 등록
	 * 
	 * @param ac
	 */
	void write(AskComment ac);

	/**
	 * 
	 * @param ac
	 * @return
	 */
	AskComment getCommentNumber(AskComment ac);

	/**
	 * ac 번호가 담긴 객체로, 해당 wc의 모든 정보를 담은 객체 반환
	 * 
	 * @param ac
	 */
	AskComment getACdata(AskComment ac);

	/**
	 * req로 전달받은 댓글 수정한 값 입력하기
	 * 
	 * @param req
	 */
	void updateComment(HttpServletRequest req);

}
