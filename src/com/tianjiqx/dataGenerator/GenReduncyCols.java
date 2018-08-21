package com.tianjiqx.dataGenerator;

import java.util.ArrayList;



/**
 * 生成冗余列
 * @author Administrator
 *
 */

public class GenReduncyCols {

	
	class Column{
		public final static int CINT=1;
		public final static int CBOOL=2;
		public final static int CDOUBLE=3;
		public final static int CVARCHAR=4;
		public final static int CDATE=5;
		public final static int CTIME=6;
		public final static int CDECIMAL=7;
	}
	
	public static char delimiter=',';
	

	
	public static ArrayList<String> generateReduncyCols(String [] cols,int rows) {
		
		ArrayList<String>  list = new ArrayList<String>();
		
		for (int i=0;i<rows;i++)
		{
			String row="";
			for (int j=0;j<cols.length;j++)
			{
				
				switch (Integer.parseInt(cols[j])) {
				
				case Column.CBOOL:
					row += RandRow.randBool() + delimiter;
					break;
				case Column.CDOUBLE:
					row += RandRow.randDouble(0, 1000) + delimiter;
					break;
				case Column.CVARCHAR:
					row += RandRow.randVarChar(10) + delimiter;
					break;
				case Column.CDATE:
					row += RandRow.randDate("2000-01-01", "2020-12-29") + delimiter;
					break;
				case Column.CTIME:
					row += RandRow.randTime("00:00:00", "23:59:59") + delimiter;
					break;
				case Column.CDECIMAL:
					row += RandRow.randDecimal(0, 9999999) + delimiter;
					break;
				default:
					break;
				}
				RandRow.delt++;
				
			}
			list.add(row.substring(0, row.length()-1));
				
		}
		
	    return list;
	}
	
}
