package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.FileDao;
import dto.member.PhotoDto;

public class FileDaoImpl implements FileDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int fileInsert(Connection conn, PhotoDto photoDto) {
		String sql = "";
		sql += "INSERT INTO profilephoto(fileno, usernumber, originname, storedname )";
		sql += " VALUES ( profilephoto_seq.nextval, ?, ?, ? )";

		// 수행 결과 변수
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, photoDto.getUsernumber());
			ps.setString(2, photoDto.getOriginname());
			ps.setString(3, photoDto.getStoredname());

			res = ps.executeUpdate();

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public List<PhotoDto> selectAll(Connection connection) {

		String sql = "";
		sql += "SELECT fileno, originname, storedname FROM uploadfile";
		sql += " ORDER BY fileno DESC";

		List<PhotoDto> list = new ArrayList<>();

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				PhotoDto photoDto = new PhotoDto();

				photoDto.setFileno(rs.getInt("fileno"));
				photoDto.setOriginname(rs.getString("originname"));
				photoDto.setStoredname(rs.getString("storedname"));

				list.add(photoDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return list;
	}

}
