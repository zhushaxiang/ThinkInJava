//
package access.cookie2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Cookie {
	public Cookie() {
		System.out.println("Cookie constructor");
	}

	protected void bite() {
		System.out.println("bite");
	}
private static String PATH = "D://Users//LIYU//workspace//TIJ4//src";
	public static void main(String[] args) throws Exception  {
		File file = new File(PATH);
		updateFile(file);
	}
	private static void updateFile(File inFile) throws Exception {
		if(inFile.isDirectory()){
			File[] files = inFile.listFiles();
			for(File f : files){
				updateFile(f);
			}
		}else{
			String _package = inFile.getAbsolutePath().replaceAll("\\\\", "//");
			_package = _package.substring(PATH.length(), _package.lastIndexOf("//")).replaceAll("//", ".");
			// 临时文件
			File outFile = File.createTempFile("name", ".tmp");
			// 输入
			FileInputStream fis = new FileInputStream(inFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			// 输出
			FileOutputStream fos = new FileOutputStream(outFile);
			PrintWriter out = new PrintWriter(fos);
			// 保存一行数据
			String thisLine;
			// 行号从1开始
			int i = 1;
			boolean flag = true;
			while ((thisLine = in.readLine()) != null) {
				// 如果行号等于目标行，则输出要插入的数据
				if (!thisLine.contains("//") && flag && _package.trim().length()!=0) {
					if(thisLine.contains("package")){
						flag = false;
					}else{
						out.println("package "+_package.substring(1)+";");
						flag = false;
					}
					
				}
				// 输出读取到的数据
				out.println(thisLine);
				// 行号增加
				i++;
			}
			out.flush();
			out.close();
			in.close();
			// 删除原始文件
			inFile.delete();
			// 把临时文件改名为原文件名
			outFile.renameTo(inFile);
		}
		
	}
} 
