package com.tianjiqx.dataGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.tianjiqx.dataGenerator.GenReduncyCols.Column;


/*
 * 功能数据生成器
 * 生成数据
 * 
 */



public class DataGenerator {



	public static String [] readVar(VarCharArgs inputArgs, BufferedReader reader ) throws IOException
	{
		inputArgs.rows = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		System.out.println(inputArgs.rows);

		inputArgs.diff_num_ratio = Double.parseDouble(reader.readLine()
				.split("=")[1].trim());
		inputArgs.null_ratio = Double.parseDouble(reader.readLine()
				.split("=")[1].trim());
		inputArgs.prefix = reader.readLine().split("=")[1].trim();
		inputArgs.len = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.slen = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.delt = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.min = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.is_shuffle = reader.readLine().split("=")[1].trim()
				.toLowerCase().equals("true");

		String [] redundancyCols = reader.readLine().split("=")[1].split(",");
		
		return redundancyCols;
	}
	
	
	public static String [] readDecimal(DecimalArgs inputArgs, BufferedReader reader ) throws IOException
	{
		inputArgs.rows = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.diff_num_ratio = Double.parseDouble(reader.readLine()
				.split("=")[1].trim());
		inputArgs.null_ratio = Integer.parseInt(reader.readLine()
				.split("=")[1].trim());
		inputArgs.prefix = reader.readLine().split("=")[1].trim();
		inputArgs.len = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.slen = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.delt = Double.parseDouble(reader.readLine().split("=")[1]
				.trim());
		inputArgs.min = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.is_shuffle = reader.readLine().split("=")[1].trim()
				.toLowerCase().equals("true");

		String [] redundancyCols = reader.readLine().split("=")[1].split(",");
		
		return redundancyCols;
	}
	
	
	public static String [] readComm(CommTypeArgs inputArgs,BufferedReader reader ) throws IOException
	{
		
		inputArgs.rows = Integer.parseInt(reader.readLine().split("=")[1]
				.trim());
		inputArgs.diff_num_ratio = Double.parseDouble(reader.readLine()
				.split("=")[1].trim());
		inputArgs.null_ratio = Integer.parseInt(reader.readLine()
				.split("=")[1].trim());
		String prefix = reader.readLine().split("=")[1].trim();
		String len = reader.readLine().split("=")[1].trim();
		String slen = reader.readLine().split("=")[1].trim();
		inputArgs.delt = reader.readLine().split("=")[1].trim();
		inputArgs.min = reader.readLine().split("=")[1].trim();
		inputArgs.is_shuffle = reader.readLine().split("=")[1].trim()
				.toLowerCase().equals("true");

		String [] redundancyCols = reader.readLine().split("=")[1].split(",");
		
		return redundancyCols;
		
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("generate begin...");
		//读取配置文件生成表

		if (args.length <2)
		{
			System.err.println("please input config and outputfile"); 
			return;
		}

		File conf = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(conf));

		String coltype = reader.readLine().split("=")[1].trim(); 
		System.out.println(coltype);

		

		String [] redundancyCols = null; //冗余列
		ArrayList<String>  list = null;
		
		if (coltype.toLowerCase().equals("varchar"))
		{
			//类型
			VarCharArgs inputArgs = new VarCharArgs();
			redundancyCols = readVar(inputArgs, reader);

			list = GenVarchar.genVarcharCol(inputArgs);
			
		}
		else if (coltype.toLowerCase().equals("decimal"))
		{
			DecimalArgs inputArgs = new DecimalArgs();
			
			redundancyCols = readDecimal(inputArgs, reader);

			list = GenDecimal.generateDecimal(inputArgs);
			
		}
		else if (coltype.toLowerCase().equals("alltype"))
		{
			//类型
			
			CommTypeArgs inputArgs =new CommTypeArgs();
			
			String type = reader.readLine().split("=")[1].trim();
			
			if (type.toLowerCase().equals("int"))
			{
				inputArgs.type=Column.CINT;
				redundancyCols =  readComm(inputArgs, reader);
				list = GenCommType.generateCommType(inputArgs);
			}
			else if (type.toLowerCase().equals("double"))
			{
				inputArgs.type=Column.CDOUBLE;
				redundancyCols = readComm(inputArgs, reader);
				list = GenCommType.generateCommType(inputArgs);
			}
			else if (type.toLowerCase().equals("date"))
			{
				inputArgs.type=Column.CDATE;
				redundancyCols = readComm(inputArgs, reader);
				list = GenCommType.generateCommType(inputArgs);
			}
			else if (type.toLowerCase().contains("decimal"))
			{
				DecimalArgs inputArgs1 = new DecimalArgs();
				redundancyCols = readDecimal(inputArgs1, reader);
				list = GenDecimal.generateDecimal(inputArgs1);
			}
			else if (type.toLowerCase().contains("varchar"))
			{
				VarCharArgs inputArgs1 = new VarCharArgs();
				redundancyCols = readVar(inputArgs1, reader);
				list = GenVarchar.genVarcharCol(inputArgs1);
			}
			
			
		}


		if (list != null)
		{
			//清空文件
			OutputData.clear(args[1]);

			ArrayList<String> tmp = new ArrayList<String>();
			
			int segment=1000;
			
			int rowkey=0;
			boolean isbreak = false;
			int num = (int) Math.ceil(list.size()*1.0/segment);
			for (int i=0;i < num ;i++)
			{
				if (list.size() - rowkey - segment < 0)
				{
					segment = list.size() - rowkey;
					isbreak = true;
				}

				ArrayList<String> red = GenReduncyCols.generateReduncyCols(redundancyCols,segment);

				for (int j=0;j < segment;j++)
				{
					String trow = ""+String.valueOf(rowkey+1)+""+GenReduncyCols.delimiter+list.get(rowkey)+GenReduncyCols.delimiter+red.get(j);
					tmp.add(trow);
					rowkey++;
				}

				OutputData.appendWriteFile(args[1], tmp);
				tmp.clear();

				if (isbreak) break;
			}
		}
		
	


		//System.out.println(varlist.toString());
		System.out.println("generate end...");

	}





}
