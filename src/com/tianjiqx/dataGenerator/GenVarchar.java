package com.tianjiqx.dataGenerator;

import java.util.ArrayList;
import java.util.Random;



public class GenVarchar {

	
	/**
	 * 生成字符串递增编码
	 * such as: FAGN20120603001
	 * @param pres 字符串前缀
	 * @param length 编码长度
	 */
	
	public static ArrayList<String> genVarcharCol(VarCharArgs vca) {
		
		
		ArrayList<String> list = new ArrayList<String>();
		
		//当前值
		int cur=vca.min;
		
		int total_row=vca.rows;

		int null_num= (int) (vca.rows * vca.null_ratio / 100);
		
		int rows=  vca.rows - null_num;
		
		int diff_num= (int) (rows * vca.diff_num_ratio / 100);
		
		

		for (int i=0;i < diff_num ;i++)
		{
			String value=""+vca.prefix;
			value+=String.format("%0"+(vca.len-vca.prefix.length())+"d", cur)+"";
			cur += vca.delt;
			list.add(value);
		}
		
		for (int i = diff_num,j=0; i < rows;i++)
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

		
		if (vca.is_shuffle)
		{
			Random rd = new Random();
			
			for (int i=0; i <total_row /2;i++)
			{
				int j =  rd.nextInt(total_row);
				
				int k = rd.nextInt(total_row);
				String tmp= list.get(j);
				list.set(j, list.get(k));
				list.set(k, tmp);
				//System.out.println("j = "+j+" k= "+k);
			}
		}
		
		
		
		return list;
	}
	
	
	/**
	 * 生成随机字符串
	 * 
	 */
	
	String randVarChar(int len)
	{		
		return RandRow.randVarChar(len);
	}
	
}
