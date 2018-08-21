package com.tianjiqx.dataGenerator;


/**
 * 字符参数
 * 
 * @author Administrator
 *
 */

public class VarCharArgs {

	
	public String prefix="ABC"; //前缀
	public int len=15; //  字符串长度
	public int slen=0;
	public int delt=1;  // 增量
	public int min=1; //最小值
	public int rows=200; // 行数
	public double diff_num_ratio=10; //不同值比例
	public boolean is_shuffle=true; //是否混洗
	public double null_ratio=0;  //null值比例 
	
	public VarCharArgs(String prefix, int len, int delt, int min, int rows,
			int diff_num_ratio, boolean is_shuffle) {
		super();
		this.prefix = prefix;
		this.len = len;
		this.delt = delt;
		this.min = min;
		this.rows = rows;
		this.diff_num_ratio = diff_num_ratio;
		this.is_shuffle = is_shuffle;
	}
	
	public VarCharArgs(){}
	
	
}
