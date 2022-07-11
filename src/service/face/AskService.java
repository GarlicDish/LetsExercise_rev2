package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Ask;
import dto.AskFile;
import util.Paging;

public interface AskService {

	/**
	 * 문의글 목록 조회
	 * 
	 * @param paging - 페이징 객체
	 * @return List<Ask> - 문의글 조회 결과
	 */
	public List<Ask> getList(HttpServletRequest req, Paging paging);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Board - 전달파라미터 boardno값을 포함한 DTO객체
	 */
	public Ask getBoardno(HttpServletRequest req);

	/**
	 * 전달된 boardno를 이용하여 문의글을 조회한다
	 * 
	 * 조회된 문의글의 조회수를 1 증가시킨다
	 * 
	 * @param boardno - 조회할 boardno를 가지고 있는 DTO객체
	 * @return Board - 조회된 문의글 정보
	 */
	public Ask view(Ask boardno);

	/**
	 * 문의글 작성 입력한 문의글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(문의글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	/**
	 * 전달된 Board 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewBoard - 조회할 문의글 정보
	 * @return String - 문의글 작성자의 닉네임
	 */
	public String getNick(Ask viewAsk);

	/**
	 * 문의글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * 문의글 삭제
	 * 
	 * @param board - 삭제할 문의글 번호를 가진 객체
	 */
	public void delete(Ask ask);

	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param viewBoard - 첨부파일과 연결된 문의글의 번호
	 * @return BoardFile - 첨부파일 정보 DTO객체
	 */
	public AskFile viewFile(Ask viewAsk);

}
