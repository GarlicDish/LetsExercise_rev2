package service.impl;

import dao.impl.UserDAO;
import service.face.ChatIDService;

public class ChatIDServiceImpl implements ChatIDService {
	
	UserDAO userDAO = new UserDAO();
	
	@Override
	public String RegisterCheckService(String userID) {
		
		return userDAO.registerCheck(userID) + "";
	}
	
}
