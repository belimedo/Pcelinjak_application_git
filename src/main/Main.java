package main;

import controller.LoginController;
import model.dao.PcelinjakDao;

public class Main  {

	public static void main(String[] args) {
		PcelinjakDao pd = new PcelinjakDao();
		pd.getById(1);
		LoginController lc = new LoginController();
		lc.startStage();
	}

}
