package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ClubDao;
import dto.club.CityDto;
import dto.club.ClubDto;
import dto.club.ClubMemberListDto;
import dto.club.ExerciseDto;
import dto.club.GuDto;
import dto.club.User;
import dto.club.UserProfile;
import util.Paging;

public class ClubDaoImpl implements ClubDao {

	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // 결과 집합 객체

	@Override
	public int selectNextClubNo(Connection conn) {

		String sql = "";
		sql += " SELECT clubnumber_seq.nextval AS next FROM dual"; // 약자 next

		int next = 0; // 결과 저장 변수

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				next = rs.getInt("next");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return next;
	}

	@Override
	public int insertNextClub(Connection conn, ClubDto club) {

		String sql = "";
		sql += "INSERT INTO club (clubnumber, clubchiefnumber, exercisenumber, clubname, clubarea, ACTIVATE, introduction)";
		sql += " VALUES(?,?,?,?,?,1,?)";

		int result = 0; // INSERT 수행 결과 저장 변수

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, club.getClubNumber());
			ps.setInt(2, club.getClubChiefNumber());
			ps.setInt(3, club.getClubExerciseNumber());
			ps.setString(4, club.getClubName());
			ps.setInt(5, club.getClubArea());
			ps.setString(6, club.getIntroduction());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectActivate(Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, I.* FROM(";
		sql += "  SELECT cl.clubnumber, cl.clubname, cl.clubchiefnumber,cl.introduction, cl.clubarea, cl.creationdate, ex.exercisename, ui.nickname, city.cityname, gu.guname, (";
		sql += "	select count(*) FROM clubmemberlist cml";
		sql += "	where cl.clubnumber = cml.clubnumber and cml.approved = 1";
		sql += "	) cnt";
		sql += "  FROM club cl, exercise ex, userinfo ui, city, gu";
		sql += "  where cl.exercisenumber = ex.exercisenumber (+) and cl.clubchiefnumber = ui.usernumber (+) and cl.clubarea = gu.gunumber (+) and  gu.citynumber = city.citynumber (+) and cl.activate = 1";
		sql += "  order by cl.clubnumber desc";
		sql += "  ) I";
		sql += " )";
		sql += " WHERE rnum BETWEEN ? and ?";

		List<Map<String, Object>> list = new ArrayList<>();

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			// 조회결과 처리
			while (rs.next()) {

				Map<String, Object> map = new HashMap<String, Object>();

				ClubDto club = new ClubDto();

				club.setClubNumber(rs.getInt("clubnumber"));
				club.setClubChiefNumber(rs.getInt("clubchiefnumber"));
				club.setClubName(rs.getString("clubname"));
				club.setClubArea(rs.getInt("clubarea"));
				club.setIntroduction(rs.getString("introduction"));
				club.setCreationDate(rs.getDate("creationdate"));
//				System.out.println("map에 들어갈 클럽 : " + club);

				User user = new User();
				user.setUsername(rs.getString("nickname"));

				ExerciseDto exercise = new ExerciseDto();
				exercise.setExercisename(rs.getString("exercisename"));

				int memberCNT = rs.getInt("cnt");
				String area = null;
				area = rs.getString("cityname") + " " + rs.getString("guname");
				map.put("club", club);
				map.put("user", user);
				map.put("exercise", exercise);
				map.put("memberCNT", memberCNT);
				map.put("area", area);

				list.add(map);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		System.out.println(list);
		return list;
	}

	@Override
	public int selectUserByUserIdPw(Connection connection, User loginUser) {

		String sql = "";
		sql += "SELECT count(*) FROM userinfo";
		sql += " WHERE userid = ? and userpw = ?";

		int res = 0;

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			ps.setString(2, loginUser.getUserpassword());

			rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public User selectUserInfobyUserno(Connection conn, int userno) {

		String sql = "";
		sql += "SELECT usernumber, nickname FROM userinfo";
		sql += " WHERE userno = ?";

		User user = new User();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, userno);

			rs = ps.executeQuery();
			while (rs.next()) {
				user.setUserno(rs.getInt("usernumber"));
				user.setUsername(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return user;
	}

	@Override
	public UserProfile getUserProfilebyUserno(Connection conn, int userNo) {

		String sql = "";
		sql += "SELECT key, usernumber, originname, STOREDNAME  FROM profilephoto";
		sql += " WHERE usernumber = ?";

		UserProfile up = new UserProfile();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, userNo);

			rs = ps.executeQuery();
			while (rs.next()) {
				up.setKey(rs.getInt("key"));
				up.setUsernumber(rs.getInt("usernumber"));
				up.setOriginname(rs.getNString("originname"));
				up.setUploadname(rs.getNString("STOREDNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return up;

	}

	@Override
	public int selectCntAll(Connection connection) {
		String sql = "";
		sql += "SELECT count(*) cnt FROM club";

		// 총 게시글 수
		int count = 0;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return count;
	}

	@Override
	public List<CityDto> selectAllCity(Connection conn) {

		String sql = "";
		sql += "SELECT citynumber, cityname FROM city";
		List<CityDto> clist = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				CityDto city = new CityDto();

				city.setCitynumber(rs.getInt("citynumber"));
				city.setCityname(rs.getString("cityname"));

				clist.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return clist;
	}

	@Override
	public List<GuDto> selectAllGu(Connection conn) {
		String sql = "";
		sql += "SELECT gunumber, citynumber, guname FROM gu";
		List<GuDto> glist = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				GuDto gu = new GuDto();

				gu.setCitynumber(rs.getInt("citynumber"));
				gu.setGunumber(rs.getInt("gunumber"));
				gu.setGuname(rs.getString("guname"));

				glist.add(gu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return glist;
	}

	@Override
	public List<ExerciseDto> selectAllExercise(Connection conn) {
		String sql = "";
		sql += "SELECT exercisenumber, exercisename FROM exercise";
		List<ExerciseDto> exlist = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				ExerciseDto exercise = new ExerciseDto();

				exercise.setExercisenumber(rs.getInt("exercisenumber"));

				exercise.setExercisename(rs.getString("exercisename"));

				exlist.add(exercise);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return exlist;

	}

	@Override
	public List<GuDto> selectGuByCityNumber(Connection connection, CityDto city) {

		String sql = "";
		sql += "SELECT gunumber, guname from gu";
		sql += " WHERE citynumber = ?";
		sql += " OrDER BY guname asc";

		List<GuDto> glist = new ArrayList<>();

		try {
			ps = connection.prepareStatement(sql);

			ps.setInt(1, city.getCitynumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				GuDto gu = new GuDto();

				gu.setGunumber(rs.getInt("gunumber"));
				gu.setGuname(rs.getString("guname"));

				glist.add(gu);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return glist;
	}

	@Override
	public int insertClubMemberList(Connection conn, ClubDto club, HttpServletRequest req) {

		String sql = "";
		sql += "INSERT INTO clubmemberlist (clubmemberlistnumber, clubnumber, clubmembernumber, approved)";
		sql += " VALUES(clubmemberlistnumber_seq.nextval, ?,?, ?)";

		int result = 0; // INSERT 수행 결과 저장 변수

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, club.getClubNumber());
			ps.setInt(2, club.getClubChiefNumber());
			ps.setInt(3, 1);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;

	}

	@Override
	public ClubDto selectClub(Connection conn, HttpServletRequest req) {
		String sql = "";
		sql += "SELECT * from club";
		sql += " WHERE clubnumber = ?";

		ClubDto club = new ClubDto();
		try {
			ps = conn.prepareStatement(sql);
			System.out.println(req.getParameter("clubnumber"));
			ps.setInt(1, Integer.parseInt(req.getParameter("clubnumber")));

			rs = ps.executeQuery();

			while (rs.next()) {
				club.setClubNumber(rs.getInt("clubnumber"));
				club.setClubChiefNumber(rs.getInt("clubchiefnumber"));
				club.setClubName(rs.getString("clubname"));
				club.setClubExerciseNumber(rs.getInt("exercisenumber"));
				club.setClubArea(rs.getInt("clubarea"));
				club.setIntroduction(rs.getString("introduction"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		System.out.println("[ClubDao] 추출한 클럽정보 : " + club);

		return club;
	}

	@Override
	public List<Map<String, Object>> selectMember(Connection conn, ClubDto club) {

		String sql = "";
		sql += "select cml.clubnumber, cml.memberdate, cml.approved, ui.usernumber, ui.nickname";
		sql += " from clubmemberlist cml, userinfo ui";
		sql += " where cml.clubmembernumber = ui.usernumber (+)";
		sql += " and cml.clubnumber = ?";

		List<Map<String, Object>> llmap = new ArrayList<>();
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, club.getClubNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				ClubMemberListDto cml = new ClubMemberListDto();
				cml.setClubNumber(rs.getInt("clubnumber"));
				cml.setClubMember(rs.getInt("usernumber"));
				cml.setMemberdate(rs.getDate("memberdate"));
				cml.setApproved(rs.getBoolean("approved"));

				User user = new User();
				user.setUserno(rs.getInt("usernumber"));
				user.setUsername(rs.getString("nickname"));

				map.put("cml", cml);
				map.put("user", user);

				llmap.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return llmap;
	}

	@Override
	public int deleteMember(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "DELETE clubmemberlist";
		sql += " WHERE clubnumber = ? and CLUBMEMBERNUMBER = ?";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, Integer.parseInt(req.getParameter("clubnumber")));
			ps.setInt(2, Integer.parseInt(req.getParameter("userno")));

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int updateClub(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "UPDATE club set clubname = ?, introduction = ?";
		sql += " WHERE clubnumber = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, req.getParameter("clubname"));
			ps.setString(2, req.getParameter("introduction"));
			ps.setInt(3, Integer.parseInt(req.getParameter("clubnumber")));

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int clubJoin(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "INSERT INTO clubmemberlist (clubmemberlistnumber, clubnumber, clubmembernumber)";
		sql += " VALUES (clubmemberlistnumber_seq.nextval, ?,?)";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("clubnumber")));
			ps.setInt(2, (int) (req.getSession().getAttribute("userno")));
			System.out.println((req.getSession().getAttribute("userno")));
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int memberApprove(Connection conn, ClubMemberListDto cml) {

		String sql = "";
		sql += "UPDATE clubmemberlist SET approved = 1, memberdate = sysdate";
		sql += " WHERE clubnumber = ? and clubmembernumber = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cml.getClubNumber());
			ps.setInt(2, cml.getClubMember());

			res = ps.executeUpdate();
			System.out.println("approve 의 res 값 : " + res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

}
