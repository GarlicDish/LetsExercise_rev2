package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dto.member.MemberDto;
import dto.member.PhotoDto;

public class LoginDaoImpl implements LoginDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public MemberDto loadUserInfo(Connection conn, String userid) {
		System.out.println("loadUserInfo(): " + userid);

		String sql = "";
		MemberDto logmem = new MemberDto();

		// sql문 실행하여 결과테이블 resultset 얻기
		sql += "SELECT * FROM USERINFO ";
		sql += "WHERE UserID = ? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);

			System.out.println("목표 " + userid);

			rs = ps.executeQuery();

			while (rs.next()) {

				logmem.setUserID(rs.getString("UserID"));
				logmem.setUserPW(rs.getString("USERPW"));
				logmem.setNickname(rs.getString("NICKNAME"));
				logmem.setAreaNumber(rs.getInt("areanumber"));
				logmem.setEmail(rs.getString("email"));
				logmem.setRePwDate(rs.getString("rePwDate"));
				logmem.setBlackListed(rs.getInt("blackListed"));
				logmem.setSocialMem(rs.getInt("socialMem"));
				logmem.setDeActivated(rs.getInt("deActivated"));
				logmem.setUserNumber(rs.getInt("userNumber"));
				// 남자는 0, 여자는 1으로 처리
				if (rs.getString("gender") == "M") {
					logmem.setGender(0);
				} else if (rs.getString("gender") == "F") {
					logmem.setGender(1);
				}
			}

			System.out.println("기본 정보 로드됨");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return logmem;
	}

	@Override
	public int idpwMatch(Connection conn, String userid, String userpw) {

		// 결과 저장을 위한 변수
		int chk = 0;
		String sql = "";

//		sql += "SELECT count(*) FROM userinfo";
		sql += "SELECT * FROM userinfo";
		sql += " WHERE userid = ?";
//		sql += "AND userpw = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
//			ps.setString(2, userpw);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getNString("userpw").equals(userpw)) { // 패스워드 비교
					if(rs.getInt("deActivated") == 1) { // 탈퇴한 회원 확인
						return 3; //로그인에 실패
					}
					return 1; // 로그인에 성공
				}
				return 2; // 비밀번호가 틀림
			} else {
				return 0; // 해당 사용자가 존재하지 않음
			}
			
//			while (rs.next()) {
//				chk = rs.getInt(1);
//				// 일치하는 데이터가 1개 있다면 chk는 1이다. 아닐경우 변화없이 0
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return -1; // 데이터 베이스 오류
//		return chk;
	}

	@Override
	public PhotoDto loadPhotoInfo(Connection conn, MemberDto memberDto) {

		// 프로필사진의 저장된 이름(pk)을 불러온다.

		String sql = "";
		sql = "";
		sql += "SELECT * FROM PROFILEPHOTO ";
		sql += "WHERE usernumber = ? ";
		PhotoDto photo = new PhotoDto();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberDto.getUserNumber());
			rs = ps.executeQuery();
			while (rs.next()) {
				photo.setUsernumber(rs.getInt("usernumber"));
				photo.setUsernumber(rs.getInt("FILENO"));
				photo.setOriginname(rs.getString("ORIGINNAME"));
				photo.setStoredname(rs.getString("STOREDNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		System.out.println("프로필사진 로드됨");
		return null;
	}

}
