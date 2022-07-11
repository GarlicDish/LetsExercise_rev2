package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.member.PhotoDto;

public interface FileDao {

	public int fileInsert(Connection conn, PhotoDto photoDto);
	public List<PhotoDto> selectAll(Connection connection);
}
