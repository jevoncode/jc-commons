package com.jc.commons;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Set;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

public class FileUtils {
	/**
	 * 在Mac下是"\r" <br/>
	 * 在Unix\Linux下是"\n" <br/>
	 * 在Windows下是"\r\n"
	 */
	public static String lineSeparator = System.getProperty("line.separator");
	public static String lineSeparatorDefault = "\n";

	
	
	/**
	 * 读取File对象的内容
	 * @param log
	 * @return
	 * @throws IOException
	 */
	public static String read(File log) throws IOException {
		return read(log.getAbsolutePath());
	}
	
	
	/**
	 * BufferReader读取文件内容，返回字符串的文件内容，换行符是"\n"。
	 * 
	 * @param fileName
	 *            带路径的文件名
	 * @return 字符串形式文件内容
	 * @throws IOException
	 */
	public static String read(String fileName) throws IOException {
		String fileCode = charsetDetector(fileName);
		StringBuffer content = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), fileCode));
			String l;
			String delim = "";
			while ((l = bufferedReader.readLine()) != null) {
				content.append(delim).append(l);
				delim = lineSeparatorDefault;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}
		return content.toString();
	}

	/**
	 * 可优化,有时如果缓存不够多,CharsetDetector可能发现不出是什么格式。需循环递增的校验几次
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String charsetDetector(String fileName) throws IOException {
		BufferedInputStream in = null;
		String charset = "UTF-8";
		try {
			in = new BufferedInputStream(new FileInputStream(fileName));
			CharsetDetector d = new CharsetDetector();
			byte[] b = new byte[1024*10];
			if (in.read(b) != -1) {
				d.setText(b);
				CharsetMatch match = d.detect();
				charset = match.getName();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null)
				in.close();
		}
		return charset;

	}

	/**
	 * 
	 * @param content
	 *            要写入文件的内容(字节)
	 * @param fileName
	 *            带路径的文件名
	 * @param append
	 *            是否在文本末继续添加内容，true为继续添加内容，false代表从文件开始写入
	 * @throws IOException
	 */
	public static void write(byte[] content, String fileName, boolean append) throws IOException {
		FileOutputStream out = new FileOutputStream(fileName, append);
		try {
			out.write(content);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * 默认从文件的开始写入内容
	 * 
	 * @param content
	 *            要写入文件的内容(字节)
	 * @param fileName
	 *            带路径的文件名
	 * @throws IOException
	 */
	public static void write(byte[] content, String fileName) throws IOException {
		write(content, fileName, false);
	}

	/**
	 * 
	 * @param content
	 *            要写入文件的内容(字符串)
	 * @param fileName
	 *            带路径的文件名
	 * @param append
	 *            是否在文本末继续添加内容，true为继续添加内容，false代表从文件开始写入
	 * @throws IOException
	 */
	public static void write(String content, String fileName, boolean append) throws IOException {
		FileWriter out = null;
		try {
			out = new FileWriter(fileName,append);
			out.write(content);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 默认从文件的开始写入内容
	 * 
	 * @param content
	 *            要写入文件的内容(字符串)
	 * @param fileName
	 *            带路径的文件名
	 * @throws IOException
	 */
	public static void write(String content, String fileName) throws IOException {
		write(content, fileName, false);
	}
	
	public static void delete(String fileName){
		File file = new File(fileName);
		if(file.exists())
			file.delete();
	}

	public static void main(String[] args) throws IOException {
		// System.getProperty(Charset.defaultCharset());
//		System.out.println(charsetDetector("E:/dubbo.properties"));
//		String content = read("E:/dubbo.properties");
//		System.out.println(content);
		// System.out.println(new String(content.getBytes(),
		// Charset.defaultCharset()));
		// write(content, "E:/test.txt");
		/**测试文件名排序*/
//		File e = new File("E:/");
//		File[] files = e.listFiles();
//		for(File file:files){
//			System.out.println(file.getName());
//		}
//		System.out.println("=====================");
//		sortByName(files);
//		for(File file:files){
//			System.out.println(file.getName());
//		}
	}

	
	public static void sortByName(File[] files) {
		quicksortByName(files, 0, files.length-1);
	}
	
	
	public static void quicksortByName(File  n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            quicksortByName(n, left, dp - 1);
            quicksortByName(n, dp + 1, right);
        }
    }
	
	/**
	 * 按名字快速排序
	 * @param n
	 * @param left
	 * @param right
	 * @return
	 */
	private static int partition(File n[], int left, int right) {
    	File pivot = n[left];
        while (left < right) {
            while (left < right && n[right].getName().compareTo(pivot.getName()) >=0)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left].getName().compareTo(pivot.getName())  <= 0)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }

	/**
	 * 获取Java支持的字符集
	 * @return
	 */
	public static Set<String> availableCharsets(){
		return Charset.availableCharsets().keySet();
	}
}
