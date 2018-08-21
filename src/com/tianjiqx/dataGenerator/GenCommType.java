package com.tianjiqx.dataGenerator;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.tianjiqx.dataGenerator.GenReduncyCols.Column;

public class GenCommType {

	public static ArrayList<String> generateCommType(CommTypeArgs cta) throws Exception
	{
		ArrayList<String> list = new ArrayList<String>();

		int icur=0;
		double dcur=0.0;
		Date tcur = new Date();
		
		int delt_i=1;
		double delt_d=0.5;
		long delt_t=1;
		
		DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		switch (cta.type) {
		case Column.CINT:
			icur = Integer.parseInt(cta.min);
			delt_i  = Integer.parseInt(cta.delt);
			break;
		case Column.CDOUBLE:
			dcur = Double.parseDouble(cta.min);
			delt_d  = Double.parseDouble(cta.delt);
			break;
		case Column.CDATE:
			tcur =  f1.parse(cta.min);
			delt_t  =  Integer.parseInt(cta.delt)*24*60*60*1000L;
			break;

		default:
			break;
		}

		int total_row=cta.rows;

		int null_num= (int) (cta.rows * cta.null_ratio / 100);

		int rows=  cta.rows - null_num;

		int diff_num= (int) (rows * cta.diff_num_ratio / 100);
		
		
		//first
		for (int i =0 ; i < diff_num;i++)
		{

			String value ="";
			
			switch (cta.type) {
			case Column.CINT:
				value+=String.valueOf(icur);
				icur=icur+delt_i;
				list.add(value);
				break;
			case Column.CDOUBLE:
				value+=String.format("%.2f", dcur);
				dcur=dcur+delt_d;
				list.add(value);
				break;
			case Column.CDATE:
				value = f1.format(tcur);
				tcur = new Date(tcur.getTime() + delt_t);
				list.add(value);
				break;

			default:
				break;
			}
		}

		//repeat
		System.out.println("diff_num="+diff_num);
		for (int i=diff_num,j=0;i< rows;i++)
		{
			System.out.println("i="+i);
			list.add(list.get(j));	
			j=(++j)%diff_num;
		}

		//null
		int ii=list.size();
		for (;ii < total_row ;ii++)
		{
			list.add("null");
		}
		if (cta.is_shuffle)
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
