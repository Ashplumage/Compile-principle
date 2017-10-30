package com.huiyu.utils;

public class GetSortNum {
	/**
	 * 输入单词获取单词对应的种别码
	 * @param word
	 * @return int
	 */
	public int getSortNum(String word){
		int sortNum=0;
		switch(word){
			case "begin":
				sortNum= 1;
				break;
			case "if":
				sortNum= 2;
				break;
			case "then":
				sortNum= 3;
				break;
			
			case "while":
				sortNum= 4;
				break;
			
			case "do":
				sortNum= 5;
				break;
			case "end":
				sortNum= 6;
				break;
			case "ID":
				sortNum= 10;
				break;
			case "NUM":
				sortNum= 11;
				break;
			case "+":
				sortNum= 13;
				break;
			case "-":
				sortNum= 14;
				break;
			case "*":
				sortNum= 15;
				break;
			case "/":
				sortNum= 16;
				break;
				
			case ":":
				sortNum= 17;
				break;
			case ":=":
				sortNum= 18;
				break;
			case "<":
				sortNum= 20;
				break;
			
			case "<>":
				sortNum= 21;
				break;
			
			case "<=":
				sortNum= 22;
				break;
			case ">":
				sortNum= 23;
				break;
			case ">=":
				sortNum= 24;
				break;
			case "=":
				sortNum= 25;
				break;
			case ";":
				sortNum= 26;
				break;
			case "(":
				sortNum= 27;
				break;
			case ")":
				sortNum= 28;
				break;
			case "#":
				sortNum= 0;
				break;
			default:
				sortNum=-1;
				break;
		}
		return sortNum;
	}
}
