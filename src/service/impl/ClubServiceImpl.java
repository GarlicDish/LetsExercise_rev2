package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ClubDao;
import dao.impl.ClubDaoImpl;
import dto.club.CityDto;
import dto.club.ClubDto;
import dto.club.ClubMemberListDto;
import dto.club.ExerciseDto;
import dto.club.GuDto;
import dto.club.User;
import service.face.ClubService;
import util.Paging;

public class ClubServiceImpl implements ClubService {

	// DAO 객체
	private ClubDao clubDao = new ClubDaoImpl();

	@Override
	public ClubDto getClubData(HttpServletRequest req) {
//		System.out.println(req.getSession().getAttribute("userno"));
		ClubDto club = new ClubDto();
		try {
			club.setClubChiefNumber((int) req.getSession().getAttribute("userno"));
			club.setClubArea(Integer.parseInt(req.getParameter("clubgu")));
			club.setClubName(req.getParameter("clubname"));
			club.setClubExerciseNumber(Integer.parseInt(req.getParameter("excercisenumber")));
			club.setClubActivated(true);
			club.setIntroduction(req.getParameter("introduction"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return club;
	}

	@Override
	public ClubDto createClub(ClubDto club) {

		Connection conn = JDBCTemplate.getConnection();
		System.out.println("ClubCreateDao createClub() " + club);

		int next = 0;

		next = clubDao.selectNextClubNo(conn);
		System.out.println("clubCreateDao.selectNextClubNo(conn) - " + next);

		club.setClubNumber(next);
		System.out.println("ClubCreateDao createClub() " + club);

		int result = clubDao.insertNextClub(conn, club);

		// insert 수행
		if (result > 0) {
			JDBCTemplate.commit(conn);
			return club;
		} else {
			JDBCTemplate.rollback(conn);
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> getList(Paging paging) {

		Connection conn = JDBCTemplate.getConnection();

		List<Map<String, Object>> list = clubDao.selectActivate(conn, paging);

//		System.out.println("넘어왔나요 = " + list);

		return list;
	}

	@Override
	public User getLoginUser(HttpServletRequest req) {

		User user = new User();

		user.setUserid(req.getParameter("id"));
		user.setUserpassword(req.getParameter("pw"));

		return user;

	}

	@Override
	public boolean login(User loginUser) {

		if (clubDao.selectUserByUserIdPw(JDBCTemplate.getConnection(), loginUser) > 0)
			return true;

		return false;
	}

	@Override
	public User getUserinfo(int userno) {

		Connection conn = JDBCTemplate.getConnection();

		return clubDao.selectUserInfobyUserno(conn, userno);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		String param = req.getParameter("curPage");

		int curPage = 0;

		if (param != null && param != "") {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] ClubSErvice getPaging() - curPage 값이 null 이거나, 비어있음");
		}

		// 총 게시글 수 조회하기
		int totalCount = clubDao.selectCntAll(JDBCTemplate.getConnection());

		// Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public List<CityDto> getCityInfo() {

		return clubDao.selectAllCity(JDBCTemplate.getConnection());
	}

	@Override
	public List<GuDto> getGuInfo() {

		return clubDao.selectAllGu(JDBCTemplate.getConnection());
	}

	@Override
	public List<ExerciseDto> getExerciseInfo() {

		return clubDao.selectAllExercise(JDBCTemplate.getConnection());
	}

	@Override
	public CityDto getParamCity(HttpServletRequest req) {

		CityDto city = new CityDto();

		city.setCitynumber(Integer.parseInt(req.getParameter("citynumber")));

		return city;
	}

	@Override
	public List<GuDto> getGuByCityNumber(CityDto city) {
		return clubDao.selectGuByCityNumber(JDBCTemplate.getConnection(), city);
	}

	@Override
	public void addMember(ClubDto club, HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		if (clubDao.insertClubMemberList(conn, club, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public ClubDto getClubInfoByClubnumber(HttpServletRequest req) {

		ClubDto club = new ClubDto();

		club = clubDao.selectClub(JDBCTemplate.getConnection(), req);

		return club;
	}

	@Override
	public List<Map<String, Object>> getClubMemberData(ClubDto club) {

		List<Map<String, Object>> lMap = clubDao.selectMember(JDBCTemplate.getConnection(), club);

		return lMap;
	}

	@Override
	public void deleteMember(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		if (clubDao.deleteMember(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void updateClub(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		if (clubDao.updateClub(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void clubJoin(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		if (clubDao.clubJoin(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void approveMember(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		ClubMemberListDto cml = new ClubMemberListDto();

		cml.setClubNumber(Integer.parseInt(req.getParameter("clubnumber")));
		cml.setClubMember(Integer.parseInt(req.getParameter("userno")));

		if (clubDao.memberApprove(conn, cml) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
}
