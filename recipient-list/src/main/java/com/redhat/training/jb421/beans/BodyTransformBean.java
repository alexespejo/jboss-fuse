package com.redhat.training.jb421.beans;

public class BodyTransformBean {
	
	public String replaceCommaWithSemiColon(String parameters){
		return parameters.replace(',',';');
	}
	
	public String replaceCommaWithColon(String parameters){
		return parameters.replace(',', ':');
	}

}
