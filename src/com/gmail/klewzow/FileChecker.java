package com.gmail.klewzow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileChecker implements Runnable {

	private File fileA;
	private File fileB;
	private File fileRes;
	private String textA;
	private String textB;

	public FileChecker(File fileA, File fileB) {
		super();
		this.fileA = fileA;
		this.fileB = fileB;
		this.fileRes = null;
		this.textA = "";
		this.textB = "";
	}
	public FileChecker() {
		super();
	}

	private String readText(File file) {
		StringBuilder sb = new StringBuilder();
		isFile(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String str; (str = br.readLine()) != null;) {
				sb.append(str + " ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private void isFile(File file) {
		if (file.isFile() == false) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void setToRead(File a, File b) {
		setTextA(readText(a));
		setTextB(readText(b));

	}

	private void toEquals() {
		setToRead(this.getFileA(), this.getFileB());
		String[] first = this.getTextA().split(" ");
		String[] last = this.getTextB().split(" ");
		StringBuilder sb = new StringBuilder();
		for (String str : first) {
			for (String string : last) {
				if (str.equals(string)) {
					sb.append(str + " ");
				}
			}
		}
		saveToFile(sb);

	}

	private void saveToFile(StringBuilder sb) {
		File z = new File("z.txt");
		isFile(z);
		try (PrintWriter pw = new PrintWriter(z)) {
			pw.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setFileRes(z);
	}



	public String getTextA() {
		return textA;
	}

	public void setTextA(String textA) {
		this.textA = textA;
	}

	public void setFileRes(File file) {
		this.fileRes = file;
	}

	public File getFileRes() {
		return this.fileRes;
	}

	public String getTextB() {
		return textB;
	}

	public void setTextB(String textB) {
		this.textB = textB;
	}

	public File getFileA() {
		return fileA;
	}

	public File getFileB() {
		return fileB;
	}

	@Override
	public void run() {
		toEquals();
	}

}
