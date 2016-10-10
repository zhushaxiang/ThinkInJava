//: net/mindview/util/BinaryFile.java
// Utility for reading files in binary form.
package net.mindview.util;

import java.io.*;

public class BinaryFile {
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
		try {
			System.out.println(bf.available());
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}

	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}

	public static void main(String[] args) throws Exception {
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(new File("test.txt")));
		try {
			System.out.println(bf.available());
		} finally {
			bf.close();
		}
	}
} /// :~
