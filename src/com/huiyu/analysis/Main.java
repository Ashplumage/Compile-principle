package com.huiyu.analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.xml.sax.HandlerBase;

import com.huiyu.utils.GetSortNum;
import com.huiyu.utils.IsNumberOrChar;

public class Main {
	static char optSingle[]={'+','-','*','/','<','>','='};//单目运算符
	static String optDouble[]={":=","<=","<>",">="};//双目运算符
	static char End[]={':' ,';' ,'(' , ')' , '#' };//终结符
	static String keyWords[]={"begin","if","then","while","do","end"};//关键词
	
	IsNumberOrChar i=new IsNumberOrChar();
	/**
	 * 读取一个文件的内容，分行将所有的内容保存到一个list集合中
	 * @param filename
	 * @return 
	 * @return
	 * @throws IOException
	 */
	public  List<String> readTestFile(String filename) throws IOException{
		List<String >list=new ArrayList<String>();
		String fileContent="";
		FileReader fread = new FileReader(filename);   
		BufferedReader bf=new BufferedReader(fread);
		String strLine=bf.readLine();
		while(strLine!=null){
			System.out.println("strLine:"+strLine);
			list.add(strLine);
			strLine=bf.readLine();
		}
		bf.close();
		fread.close();
		return list;
	}
	
	/**
	 * 将整行的字符串进行划分
	 * @param str 代表整行需要处理的字符
	 * @return 返回一个根据空格划分的list
	 */
	public String[] Divide(String str){
		String[] list;
		list=str.split(" ");

		return list;
	}
	
	/**
	 * 根据输入的list进行划分和判断，并将判断的结果输出
	 * @param list
	 */
	public void Sort(String[] list){
		//map里面的key有word,sortNum
		List<Map> mList=new ArrayList<Map>(); 
		//获取经过处理的种别码和关键字系列集合
		mList=i.GetStringAndSortNum(list);
		for(Map m0:mList){
			String keyword=(String) m0.get("keyword");
			String sortNum=(String) m0.get("sortNum");
			System.out.println("("+sortNum+","+keyword+")");
		}
	}
	
}
