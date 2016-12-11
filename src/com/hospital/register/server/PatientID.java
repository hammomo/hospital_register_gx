package com.hospital.register.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientID {
	
	private static List<Integer> ids = new ArrayList<Integer>();
	private static final int RANGE = 10000;
	
	private static int index = 0;
	
	static {
		for(int i = 0; i < RANGE; i++) {
			ids.add(i);
		}
		Collections.shuffle(ids);
	}
	
	private PatientID() {
		
	}
	
	public static int getIdentifier() {
		if(index > ids.size() - 1) index = 0;
		return ids.get(index++);
	}
	
	public static String getPatientID() {
		String str1 = getIdentifier() + "";
		String str2 = getIdentifier() + "";
		return "9006" + str1 + str2;
	}
	
	public static void main(String[] args) {
		System.out.println(getPatientID());
	}
}


