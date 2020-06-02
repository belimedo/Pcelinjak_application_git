module pcelinjak {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.swt;
	requires javafx.web;
	
	opens controller;
	exports main;
	exports model.dao;
	exports model.dto;
	exports util;
	
}