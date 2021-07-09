package com.gmail.klewzow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ReaderFiles implements Runnable, Comparison {
	private File file;
	private String[] textArray;

	public ReaderFiles(File file) {
		super();
		this.file = file;
		this.textArray = new String[3];
		this.textArray[1] = "sas";
	}

	public ReaderFiles() {
		super();
	}

	public void reads(File file) {

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
			for (String line; (line = br.readLine()) != null;) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		setTextArray(sb.toString().split(" "));

	}

	public String[] getTextArray() {
		return textArray;
	}

	public void setTextArray(String[] textArray) {
		this.textArray = textArray;
	}

	public File getFile() {
		return this.file;
	}

	@Override
	public void run() {

		reads(this.file);

	}

	@Override
	public void comparison(ReaderFiles a, ReaderFiles b) {
		StringBuilder sb = new StringBuilder();

		for (String strA : a.getTextArray()) {
			for (String strB : b.getTextArray()) {
				if (strA.equals(strB)) {
					sb.append(strA + " ");

				}

			}

			try (PrintWriter pw = new PrintWriter(this.file)) {
				pw.println(sb.toString());
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public String toString() {
		return "ReaderFiles [file=" + file + ", text=" + Arrays.toString(this.textArray) + "]";
	}

}
