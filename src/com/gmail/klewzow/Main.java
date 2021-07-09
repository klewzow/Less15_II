package com.gmail.klewzow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		FileChecker fc = new FileChecker(new File("a.txt"), new File("b.txt"));
		Thread tr = new Thread(fc);
		tr.start();

		try {
			tr.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		File res = fc.getFileRes();

		try (BufferedReader br = new BufferedReader(new FileReader(res))) {
			for (String s; (s = br.readLine()) != null;) {
				System.out.println(s);
			}
		} catch (Exception e) {
		}
	}

}
