package com.tianjiqx.dataGenerator;

import com.tianjiqx.dataGenerator.GenReduncyCols.Column;

public class CommTypeArgs {

	
	public int type=Column.CINT; // 类型
	public String delt="";  // 增量
	public String min=""; //最小值
	public int rows=200; // 行数
	public double diff_num_ratio=10; //不同值比例
	public double null_ratio=10; //null值比例
	public boolean is_shuffle=true; //是否混洗
	
	
	
}
