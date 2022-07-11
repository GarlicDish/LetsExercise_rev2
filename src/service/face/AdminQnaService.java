package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.board.BoardFile;
import dto.board.Qna;
import util.Paging;

public interface AdminQnaService {

	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<Board> - 게시글 전체 조회 결과 목록
	 */
	public List<Qna> getList();

	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<Qna> getList(Paging paging);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 전달된 boardno를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param boardno - 조회할 boardno를 가지고 있는 DTO객체
	 * @return Board - 조회된 게시글 정보
	 */
	public Qna view(Qna Qnano);

	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Board - 전달파라미터 boardno값을 포함한 DTO객체
	 */
	public Qna getQnano(HttpServletRequest req);

	/**
	 * 게시글 작성 입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	/**
	 * 전달된 Board 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param viewBoard - 첨부파일과 연결된 게시글의 번호
	 * @return BoardFile - 첨부파일 정보 DTO객체
	 */
	public BoardFile viewFile(Qna viewQna);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(Qna qna);

	/**
	 * 전달된 Board 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Qna viewQna);

	/**
	 * 댓글 출력
	 * 
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> selectReply(HttpServletRequest req);
}
