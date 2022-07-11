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
import dao.face.ClubSNSDao;
import dto.club.ClubDto;
import dto.club.ClubSNSAttachedPhoto;
import dto.club.ClubSNSDto;
import dto.club.User;
import dto.club.UserProfile;

public class ClubSNSDaoImpl implements ClubSNSDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<ClubSNSDto> selectByClubNumber(Connection conn, ClubDto club) {

		List<ClubSNSDto> cSNSlist = new ArrayList<>();

		String sql = "";
		sql += "SELECT  clubsnsnumber, clubnumber, writer, CONTENT , postingdate, revisiondate";
		sql += " FROM CLUBSNS";
		sql += " WHERE clubnumber = ?";
		sql += " ORDER BY clubsnsnumber desc";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, club.getClubNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				ClubSNSDto clubSNS = new ClubSNSDto();

				clubSNS.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				clubSNS.setClubNumber(rs.getInt("clubnumber"));
				clubSNS.setWriter(rs.getInt("writer"));
				clubSNS.setContent(rs.getString("content"));
				clubSNS.setPostingDate(rs.getDate("postingDATE"));
				clubSNS.setRevisionDate(rs.getDate("revisionDATE"));

				cSNSlist.add(clubSNS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cSNSlist;
	}

	@Override
	public int selectClubNo(Connection conn) {

		String sql = "";
		sql += "SELECT clubsns_seq.nextval AS nextClubSNSNo from dual";

		int clubsnsno = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				clubsnsno = rs.getInt("nextClubSNSNo");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return clubsnsno;
	}

	@Override
	public int insert(Connection conn, ClubSNSDto clubSNS) {

		String sql = "";
		sql += "INSERT INTO clubsns (clubsnsnumber, clubnumber, writer, CONTENT, postingdate, revisiondate)";
		sql += " VALUES (?,?,?,?,sysdate,null)";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, clubSNS.getClubSNSNumber());
			ps.setInt(2, clubSNS.getClubNumber());
			ps.setInt(3, clubSNS.getWriter());
			ps.setString(4, clubSNS.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int insertPhoto(Connection conn, ClubSNSAttachedPhoto attachedPhoto) {
		System.out.println(attachedPhoto);
		String sql = "";
		sql += "INSERT INTO clubsnsattachedphoto (clubsnsattachedphotonumber, clubsnsnumber, originname, changedname, FILESIZE , uploaddate )";
		sql += " VALUES (clubsnsattachedphoto_seq.nextval, ?, ?, ?, ?, sysdate )";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, attachedPhoto.getClubSNSNumber());
			ps.setString(2, attachedPhoto.getOriginName());
			ps.setString(3, attachedPhoto.getChangedName());
			ps.setInt(4, attachedPhoto.getFileSize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int getWriterNo(Connection conn, ClubSNSDto i) {

		String sql = "";
		sql += "SELECT clubSNSnumber, writer from clubsns";
		sql += " WHERE clubSNSnumber = ?";

		int userno = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getClubSNSNumber());

			rs = ps.executeQuery();
			while (rs.next()) {
				userno = rs.getInt("writer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return userno;
	}

	@Override
	public List<Map<String, Object>> getUserinfoAndProfileBySNSnumber(Connection conn, ClubDto club) {
		System.out.println("getUserinfoAndProfileBySNSnumber 진입");
		System.out.println("club 객체 : " + club);

		String sql = "";
		sql += "SELECT cs.clubsnsnumber";
		sql += "	, cs.clubnumber";
		sql += "	, cs.writer";
		sql += "	, cs.content";
		sql += "	, ui.nickname";
		sql += "	, up.originname";
		sql += "	, up.STOREDNAME";
		sql += "	, jjj.originname";
		sql += "	, jjj.changedname";
		sql += " from clubsns cs";
		sql += "	, userinfo ui";
		sql += "	, profilephoto up";
		sql += "	, (";
		sql += "		select * from(";
		sql += "			        select rownum rnum, j.*";
		sql += "			FROM (";
		sql += "				select csap.clubsnsnumber, csap.originname";
		sql += "					, csap.changedname";
		sql += "					, csap.filesize";
		sql += "				from clubsns css, CLUBSNSATTACHEDPHOTO  csap";
		sql += "				where css.clubsnsnumber = csap.clubsnsnumber";
		sql += "				ORDER BY CLUBSNSATTACHEDPHOTOnumber desc";
		sql += "			) j )";
		sql += "		where rnum = 1 ) jjj";
		sql += " WHERE cs.clubnumber = ?";
		sql += "	and cs.writer = ui.usernumber (+)";
		sql += "	and cs.writer = up.usernumber (+)";
		sql += "	and cs.clubsnsnumber = jjj.clubsnsnumber (+)";
		sql += " ORDER BY cs.clubsnsnumber DESC";

		List<Map<String, Object>> lMap = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, club.getClubNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				ClubSNSDto clubSNS = new ClubSNSDto();
				User user = new User();
				UserProfile up = new UserProfile();
				ClubSNSAttachedPhoto csap = new ClubSNSAttachedPhoto();

				clubSNS.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				clubSNS.setClubNumber(rs.getInt("clubnumber"));
				clubSNS.setContent(rs.getString("content"));
				clubSNS.setWriter(rs.getInt("writer"));

				user.setUserno(rs.getInt("writer"));
				user.setUsername(rs.getString("nickname"));

				up.setUsernumber(rs.getInt("writer"));
				up.setOriginname(rs.getString("originname"));
				up.setUploadname(rs.getString("STOREDNAME"));

				csap.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				csap.setOriginName(rs.getString("originname"));
				csap.setChangedName(rs.getString("changedname"));

				map.put("sns", clubSNS);
				map.put("user", user);
				map.put("profile", up);
				map.put("photo", csap);

				System.out.println("DB에서 조회된 map :" + map);
				lMap.add(map);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return lMap;
	}

	@Override
	public void delete(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "DELETE clubsns";
		sql += " WHERE clubSNSnumber = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("clubsnsnumber")));

			if (ps.executeUpdate() > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}

	@Override
	public ClubSNSDto selectSNSbySNSno(Connection conn, ClubSNSDto snSno) {
		System.out.println("///clubSNSDao selectSNSbySNSno() 실행");

		String sql = "";
		sql += "SELECT clubsnsnumber, clubnumber, writer, CONTENT, postingdate, revisiondate from clubsns";
		sql += " WHERE clubSNSnumber = ?";

		ClubSNSDto clubSNS = new ClubSNSDto();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, snSno.getClubSNSNumber());

			rs = ps.executeQuery();

			while (rs.next()) {

				clubSNS.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				clubSNS.setClubNumber(rs.getInt("clubnumber"));
				clubSNS.setWriter(rs.getInt("writer"));
				clubSNS.setContent(rs.getString("content"));
				clubSNS.setPostingDate(rs.getDate("postingdate"));
				clubSNS.setRevisionDate(rs.getDate("revisiondate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return clubSNS;
	}

	@Override
	public int checkMember(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "SELECT count(*) cnt from clubmemberlist";
		sql += " WHERE clubmembernumber = ? and clubnumber = ? and approved = 1";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (int) (req.getSession().getAttribute("userno")));
			ps.setInt(2, Integer.parseInt(req.getParameter("clubnumber")));
			rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		if (res > 0) {
			System.out.println("request의 클럽 번호와 클럽멤버리스트에 있는 번호가 일치함");
			return 1;
		} else {
			System.out.println("일치하지 않음");
			return 0;
		}
	}

	@Override
	public int update(Connection conn, ClubSNSDto clubSNS) {

		String sql = "";
		sql += "UPDATE clubsns SET CONTENT = ?, revisiondate = sysdate";
		sql += " WHERE clubsnsnumber = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, clubSNS.getContent());
			ps.setInt(2, clubSNS.getClubSNSNumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public ClubDto getClubNo(Connection connection, ClubSNSDto clubSNS) {

		String sql = "";
		sql += "SELECT * from clubsns";
		sql += " WHERE clubsnsnumber = ?";

		ClubDto club = new ClubDto();

		try {
			ps = connection.prepareStatement(sql);

			ps.setInt(1, clubSNS.getClubSNSNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				club.setClubNumber(rs.getInt("clubnumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return club;
	}

	@Override
	public Map<String, Object> getUserUserProfileClubSNSbyClubSNSno(Connection connection, ClubSNSDto clubSNS) {

		String sql = "";
		sql += "SELECT cs.clubsnsnumber";
		sql += "	, cs.clubnumber";
		sql += "	, cs.writer";
		sql += "	, cs.content";
		sql += "	, ui.nickname";
		sql += "	, up.originname";
		sql += "	, up.STOREDNAME";
		sql += "	, jjj.originname";
		sql += "	, jjj.changedname";
		sql += " from clubsns cs";
		sql += "	, userinfo ui";
		sql += "	, profilephoto up";
		sql += "	, (";
		sql += "		select * from(";
		sql += "			        select rownum rnum, j.*";
		sql += "			FROM (";
		sql += "				select csap.clubsnsnumber, csap.originname";
		sql += "					, csap.changedname";
		sql += "					, csap.filesize";
		sql += "				from clubsns css, CLUBSNSATTACHEDPHOTO  csap";
		sql += "				where css.clubsnsnumber = csap.clubsnsnumber";
		sql += "				ORDER BY CLUBSNSATTACHEDPHOTOnumber desc";
		sql += "			) j )";
		sql += "		where rnum = 1 ) jjj";
		sql += " WHERE cs.clubsnsnumber = ?";
		sql += "	and cs.writer = ui.usernumber (+)";
		sql += "	and cs.writer = up.usernumber (+)";
		sql += "	and cs.clubsnsnumber = jjj.clubsnsnumber (+)";
		sql += " ORDER BY cs.clubsnsnumber DESC";

		Map<String, Object> map = new HashMap<>();

		try {
			ps = connection.prepareStatement(sql);

			ps.setInt(1, clubSNS.getClubSNSNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				ClubSNSDto clubsns = new ClubSNSDto();
				User user = new User();
				UserProfile up = new UserProfile();
				ClubSNSAttachedPhoto csap = new ClubSNSAttachedPhoto();

				clubsns.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				clubsns.setClubNumber(rs.getInt("clubnumber"));
				clubsns.setContent(rs.getString("content"));
				clubsns.setWriter(rs.getInt("writer"));

				user.setUserno(rs.getInt("writer"));
				user.setUsername(rs.getString("nickname"));

				up.setUsernumber(rs.getInt("writer"));
				up.setOriginname(rs.getString("originname"));
				up.setUploadname(rs.getString("STOREDNAME"));

				csap.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				csap.setOriginName(rs.getString("originname"));
				csap.setChangedName(rs.getString("changedname"));

				map.put("sns", clubsns);
				map.put("user", user);
				map.put("profile", up);
				map.put("photo", csap);
				System.out.println("/////map 출력" + map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return map;
	}

}
