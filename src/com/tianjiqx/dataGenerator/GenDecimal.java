package com.tianjiqx.dataGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GenDecimal {

	
	/**
	 * 生成decimal 列
	 */
	public static ArrayList<String>  generateDecimal(DecimalArgs da)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		//当前值
		double cur=da.min;

		int total_row=da.rows;

		int null_num= (int) (da.rows * da.null_ratio / 100);

		int rows=  da.rows - null_num;

		int diff_num= (int) (rows * da.diff_num_ratio / 100);
		//first
		for (int i =0 ; i < diff_num;i++)
		{
			
			String value = da.prefix;
			value+=String.format("%0"+(da.len-da.prefix.length())+"."+da.slen+"f", cur)+"";	
			System.out.println(value);
			cur+=da.delt;
			list.add(value);
		}
		//repeat
		for (int i=diff_num,j=0;i< rows;i++)
		{
			list.add(list.get(j));	
			j=(++j)%diff_num;
		}
		
        //null
		int ii=list.size();
		for (;ii < total_row ;ii++)
		{
			list.add("null");
		}
		
		if (da.is_shuffle)
		{
			Random rd = new Random();
			
			for (int i=0; i < total_row /2;i++)
			{
				int j =  rd.nextInt(total_row);
				int k = rd.nextInt(total_row);
				String tstr= list.get(j);
				list.set(j, list.get(k));
				list.set(k, tstr);
			}
		}
		
		
		
		return list;
	}
 	
}
 