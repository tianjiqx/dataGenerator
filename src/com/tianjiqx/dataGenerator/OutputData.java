package com.tianjiqx.dataGenerator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * 输出数据
 * @author Administrator
 *
 */



public class OutputData {
	
	//清空文件
	public static void clear(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					fos);
			bufferedOutputStream.write("".getBytes());
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void appendWriteFile(String filename, ArrayList<String> list) {

		long st = System.currentTimeMillis();
		try {
			FileOutputStream fos = new FileOutputStream(filename, true);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					fos);
			String tmp = "";
			for (int i = 0; i < list.size(); i++) {
				tmp = list.get(i) + "\n";
				bufferedOutputStream.write(tmp.getBytes());
			}
			
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		long et = System.currentTimeMillis();

		System.out.println("write file time :" + (et - st) / 1000);

	}

}
