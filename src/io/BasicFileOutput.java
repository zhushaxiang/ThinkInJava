//: io/BasicFileOutput.java
package io;

import java.io.*;

public class BasicFileOutput {
	static String file = "D://BasicFileOutput.out";

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("D://Users//LIYU//workspace//TIJ4//src//io//BufferedInputFile.java")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		int lineCount = 1;
		String s;
		while ((s = in.readLine()) != null)
			out.println(lineCount++ + ": " + s);
		out.close();
		// Show the stored file:
		System.out.println(BufferedInputFile.read(file));
		System.out.println(new File("BasicFileOutput.out").getAbsolutePath());
	}
} /* (Execute to see output) */// :~
