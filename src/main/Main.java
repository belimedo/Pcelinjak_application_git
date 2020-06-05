package main;

import java.util.LinkedList;

import controller.LoginController;
import model.dao.DrustvoDao;
import model.dao.PcelinjakDao;
import model.dto.Drustvo;

public class Main  {

	public static void main(String[] args) {
		PcelinjakDao pd = new PcelinjakDao();
		pd.getById(1);
		LoginController lc = new LoginController();
		lc.startStage();
	

	}

}
