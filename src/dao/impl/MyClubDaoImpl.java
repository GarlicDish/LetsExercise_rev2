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
import dao.face.MyClubDao;
import dto.club.ClubDto;
import dto.club.ExerciseDto;
import dto.club.User;
import util.Paging;

public class MyClubDaoImpl implements MyClubDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Map<String, Object>> selectMyClub(Connection conn, User user, Paging paging) {

		// -- DB에서 불러올 데이터 SQL 작성 ---
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, I.* FROM(";
		sql += "  SELECT cl.clubnumber, cl.clubname, cl.clubchiefnumber, cl.introduction, cl.creationdate, ui.nickname, ex.exercisename, city.cityname, gu.guname, cml.memberdate, (";
		sql += "	select count(*) from clubmemberlist cml";
		sql += "	where cl.clubnumber = cml.clubnumber and cml.approved = 1";
		sql += "	) cnt";
		sql += "  FROM club cl, userinfo ui, clubmemberlist cml,  exercise ex, city, gu";
		sql += "  WHERE cl.clubnumber = cml.clubnumber and ui.usernumber = cml.clubmembernumber and cl.exercisenumber =ex.exercisenumber(+) and gu.citynumber = city.citynumber(+) and cl.clubarea = gu.gunumber(+) and cml.approved = 1 and cl.activate = 1";
		sql += "  and cml.clubmembernumber=? ) I";
		sql += " )";
		sql += " WHERE rnum BETWEEN ? and ?";

		List<Map<String, Object>> mcList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUserno());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				ClubDto club = new ClubDto();
				club.setClubNumber(rs.getInt("clubnumber"));
				club.setClubName(rs.getString("clubname"));
				club.setIntroduction(rs.getString("introduction"));
				club.setClubChiefNumber(rs.getInt("clubchiefnumber"));
				club.setCreationDate(rs.getDate("creationdate"));

				if (rs.getString("nickname") != null) {
					user.setUsername(rs.getString("nickname"));
				} else {
					user.setUsername("홍길동");
				}

				ExerciseDto exercise = new ExerciseDto();
				if (rs.getString("exercisename") != null) {
					exercise.setExercisename(rs.getString("exercisename"));
				} else {
					exercise.setExercisename("정해지지 않음");
				}

				String area = null;
				if (rs.getString("cityname") != null || rs.getString("guname") != null) {
					area = rs.getString("cityname") + " " + rs.getString("guname");
				} else {
					area = "미정";
				}

				int memberCNT = rs.getInt("cnt");

				map.put("club", club);
				map.put("user", user);
				map.put("exercise", exercise);
				map.put("area", area);
				map.put("memberdate", rs.getDate("memberdate"));
				map.put("memberCNT", memberCNT);

				mcList.add(map);

				System.out.println("map : " + map);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return mcList;
	}

	@Override
	public User selectMemberByUserid(Connection conn, User user) {

		String sql = "";
		sql += "SELECT usernumber, userid, userpw, nickname FROM userinfo";
		sql += " WHERE userid = ?";

		User getUser = new User();

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUserid());

			rs = ps.executeQuery();

			while (rs.next()) {

				getUser.setUserid(rs.getString("userid"));
				getUser.setUsername(rs.getString("nickname"));
				getUser.setUserno(rs.getInt("usernumber"));
				getUser.setUserpassword(rs.getString("userpw"));

				System.out.println(getUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		System.out.println("");
		return getUser;
	}

	@Override
	public int deactivateClub(Connection conn, HttpServletRequest req) {

		System.out.println("[MyClubDaoImpl] DEACTIVATECLUB() 진입");
		System.out.println("req 객체에서 전달 받은 clubnumber 의 값 : " + req.getParameter("clubnumber"));
		String sql = "";
		sql += "UPDATE club SET activate = 0";
		sql += " WHERE clubnumber = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("clubnumber")));

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int selectCntAll(Connection connection, HttpServletRequest req) {
		String sql = "";
		sql += "SELECT count(*) cnt FROM clubmemberlist";
		sql += " WHERE clubmembernumber = ?";

		// 총 게시글 수
		int count = 0;

		try {
			ps = connection.prepareStatement(sql);

			ps.setInt(1, (int) req.getSession().getAttribute("usernumber"));
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
	public User selectMemberByUserno(Connection conn, User user) {
		String sql = "";
		sql += "SELECT usernumber, userid, userpw, nickname FROM userinfo";
		sql += " WHERE usernumber = ?";

		User getUser = new User();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUserno());

			rs = ps.executeQuery();

			while (rs.next()) {

				getUser.setUserid(rs.getString("userid"));
				getUser.setUsername(rs.getString("nickname"));
				getUser.setUserno(rs.getInt("usernumber"));
				getUser.setUserpassword(rs.getString("userpw"));

				System.out.println("[MyClubDaoImpl] selectMemberByUserno() get User 정보 - " + getUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return getUser;
	}

	@Override
	public int withdraw(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "DELETE clubmemberlist where clubnumber = ? and clubmembernumber = ?";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, Integer.parseInt(req.getParameter("clubnumber")));
			ps.setInt(2, (int) req.getSession().getAttribute("userno"));

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
}
