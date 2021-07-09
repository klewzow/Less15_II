package com.gmail.klewzow;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		ReaderFiles a = new ReaderFiles(new File("a.txt"));
		ReaderFiles b = new ReaderFiles(new File("b.txt"));
		ReaderFiles c = new ReaderFiles(new File("z.txt"));
		Thread rfA = new Thread(a);
		Thread rfB = new Thread(b);
		rfA.start();
		rfB.start();
		try {
			rfA.join();
			rfB.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		c.comparison(a, b);

		System.out.println("End program");
	}
}
