package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.commentDto;
import util.Paging;

public interface WorkCommentService {

	/**
	 * 댓글 전체 조회
	 * 
	 * @return List<commentDto> - 댓글 전체 조회 결과 목록
	 */
	public List<commentDto> getList(HttpServletRequest req);

	/**
	 * 댓글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<commentDto> - 페이징이 반영된 댓글 조회 결과 목록
	 */
	public List<commentDto> getList(HttpServletRequest req, Paging paging);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

}
