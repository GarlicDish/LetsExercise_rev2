package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import common.JDBCTemplate;
import dao.face.JoinDao;
import dto.member.MemberDto;
import dto.member.MyExerciseDto;

public class JoinDaoImpl implements JoinDao {

	PreparedStatement ps = null;

	// 결과 저장용 변수
	boolean result = false;

	// 프로필사진, 운동정보 제외한 회원기본정보 DB저장 실행 - 프로필사진은 fileDao 에서 실행함.
	@Override
	public boolean insert(Connection conn, MemberDto member) {

		String sql = "";

		sql += "INSERT INTO userinfo ";
		sql += "(userID, userPW, email,";
		sql += "nickname, joindate, repwdate, ";
		sql += "gender, blacklisted, socialmem, ";
		sql += "deactivated, areanumber, ";
		sql += "usernumber)  ";
		sql += "VALUES (?, ?, ?, ";
		sql += "?, ?, ?, ";
		sql += "?, ?, ?, ";
		sql += "?, ?, ";
		sql += "?) ";

		try {

			System.out.println("여기까지 왔니? 회원가입 insertdao");

			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserID());
			ps.setString(2, member.getUserPW());
			ps.setString(3, member.getEmail());

			// DB 상에는 문자열로 저장되어 보여진다. (DB에서 날짜로 봐야할 경우 oracle 내에서 변환)

			ps.setString(4, member.getNickname());
			ps.setString(5, member.getJoinDate());
			ps.setString(6, member.getRePwDate());

			ps.setInt(7, member.getGender());
			ps.setInt(8, member.getBlackListed());

			ps.setInt(9, member.getSocialMem());
			ps.setInt(10, member.getDeActivated());
			ps.setInt(11, member.getAreaNumber());
			ps.setInt(12, member.getUserNumber());

			int res = ps.executeUpdate();

			if (res == 1)
				result = true;
			else
				result = false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(ps);
		}
		return result;
	}

	// 운동정보 DB에 삽입
	@Override
	public boolean Exerciseinsert(Connection conn, MemberDto member, MyExerciseDto myExerciseDto) {
		String sql = "";

		sql += "INSERT INTO MYEXERCISE ";
		sql += "(userid, InterestNumber, ";
		sql += " ExerciseNumber,ExerciseExperience)  ";
		sql += "VALUES (?, ?, ?, ? ) ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserID());
			ps.setInt(2, myExerciseDto.getInterestNumber());
			ps.setInt(3, myExerciseDto.getExerciseNumber());
			ps.setString(4, myExerciseDto.getExerciseExperience());

			System.out.println("InterestNumber() : "+myExerciseDto.getInterestNumber());

			int res = ps.executeUpdate();
			if (res == 1)
				result = true;
			else
				result = false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}

	// 아이디 중복체크
	@Override
	public boolean idCheck(String userID) {
		Connection conn = JDBCTemplate.getConnection();

		System.out.println("idCheck() " + userID);

		int rs = 0;
		String sql = "";

		sql += "SELECT * FROM userinfo ";
		sql += "WHERE userID = ? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeUpdate();
			System.out.println("rs" + rs);

			if (rs == 1)
				result = true;
			else
				result = false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//	-----DB 중복체크-------

	// 닉네임 중복체크
	@Override
	public boolean nicknameCheck(String nickname) {
		Connection conn = JDBCTemplate.getConnection();

		System.out.println("nicknameCheck() " + nickname);

		// DB체크
		int rs = 0;
		String sql = "";

		sql += "SELECT * FROM userinfo ";
		sql += "WHERE nickname = ? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			rs = ps.executeUpdate();

			if (rs == 1)
				result = true;
			else
				result = false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//	---------유효성검사 ------------

	// 패스워드 확인 검사
	@Override
	public boolean pwCheck(String userPW, String userPW2) {
		return userPW.equals(userPW2);
	}

	// 닉네임 유효성 검사
	@Override
	public boolean isokaynick(String nickname) {
		boolean res = Pattern.matches("^[a-z0-9A-Z가-힣]{4,8}$", nickname);

		return res;
	}

	// 아이디 유효성 검사
	@Override
	public boolean isokayID(String userID) {
		boolean res = Pattern.matches("^[a-z0-9A-Z_-]{4,12}$", userID);

		return res;
	}

	// 패스워드 유효성 검사
	@Override
	public boolean isokayPW(String userPW) {

		boolean res = Pattern.matches("^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{6,20}$", userPW);

		return res;

	}

	// 이메일 유효성검사
	@Override
	public boolean isokayEmail(String email) {
		boolean res = Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", email);

		return res;
	}

	@Override
	public int getNextUserNo(Connection conn) {

		String sql = "";
		sql += "SELECT USERINFO_SEQ.nextval next from dual";

		ResultSet rs = null;
		int nextnum = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				nextnum = rs.getInt("next");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return nextnum;
	}

} // joinDaoImpl end
