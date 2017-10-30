package com.huiyu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IsNumberOrChar {
	GetSortNum g=new GetSortNum();
	
	/**
	 * 将按间隔符分好的list进行判断，判断是否是合法的子串
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> GetStringAndSortNum(String[] list) {
		char firstChar;//用于记录第一个首字符
		String keyword="",sortNum="";//需要输出返回的关键字，种别码
		String cType,word;//第一个首字符的类型
		
		//mlist用于返回整个List判断完成后的含有的字符和种别码
		List<Map> mList=new ArrayList<Map>();

		for(int i=0;i<list.length;i++){
			Map<String,String>map=new HashMap<String,String>();//m用于保存最后返回的已经判别成功的字和种别码
			//word代表需要进行处理判断的字
			word=list[i];
			//判断word是不是空的串，因为有可能根据空格分割的串中有空的换行符或者空串，不进行处理
			if(word==""||word==null||word.trim()=="")continue;
			
			firstChar=word.charAt(0);
			//获取这个字符的类型
			cType=GetCharType(firstChar);
			
			if(cType=="letter"){
				if(firstChar=='w'||firstChar=='i'||firstChar=='b'||firstChar=='d'||firstChar=='e'||firstChar=='t'){
					//获得keyword词
					Map<String,String>m=new HashMap<String,String>();//m用于保存最后返回的已经判别成功的字和种别码
					m=GetKeyWord(word);
					//取出m的值，如果是关键字
					if(m!=null){
						keyword=m.get("keyword");
						sortNum=m.get("sortNum");
					}
					//不是关键字，但是包含关键字的前一个字符串
					else{
						if(IsID(word)){
							keyword=word;
							sortNum=g.getSortNum("ID")+"";
						}
						else{
							System.out.println("这个"+word+"不是合法的ID字符，所在的位置在：第"+(i+1)+"个单词");
						}
					}
				}
				else{//首字母为字符，但是需要进一步判断是不是合法的ID
					if(IsID(word)){
						keyword=word;
						sortNum=g.getSortNum("ID")+"";
					}
					else{
						System.out.println("这个"+word+"不是合法的ID字符，所在的位置在：第"+(i+1)+"个单词");
					}
				}
			}
			if(cType=="digit"){
				if(IsNum(word)){
					keyword=word;
					sortNum=g.getSortNum("NUM")+"";
				}
				else{
					System.out.println("这个"+word+"不是合法的NUM字符，所在的位置在：第"+(i+1)+"个单词");
				}
			}
			if(cType=="opts"){
				//获取这个word的长度，如果是一个进行单运算符的判断，如果是2进行多运算符的判断
				int len=word.length();
				if(len==1){
					if(IsSingleOpt(word)){
						keyword=word;
						sortNum=g.getSortNum(word)+"";
					}
					else if(IsEndOpt(word)){
						keyword=word;
						sortNum=g.getSortNum(word)+"";
					}
					else{
						System.out.println("这个"+word+"不是合法的NUM字符，所在的位置在：第"+(i+1)+"个单词");
					}
				}
				else if(len==2){
					if(IsDoubleOpt(word)){
						keyword=word;
						sortNum=g.getSortNum(word)+"";
					}
					else{
						System.out.println("这个"+word+"不是合法的NUM字符，所在的位置在：第"+(i+1)+"个单词");
					}
				}
				else{
					System.out.println("这个"+word+"不是合法的NUM字符，所在的位置在：第"+(i+1)+"个单词");
				}
			}
			if(keyword.equals("")||sortNum.equals("")||keyword==""||sortNum=="")
				continue;
			else{
				map.put("keyword", keyword);
				map.put("sortNum", sortNum);
				mList.add(map);	
				keyword="";
				sortNum="";
			}
		}
		return mList;
	}


	/**
	 * 根据字符判断这个字符是什么类型
	 * @param c
	 * @return
	 */
	public String GetCharType(char c){
		String type="";
		
		//('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ('0' <= ch && ch <= '9');
		if(('a'<=c&&c<='z')||('A' <= c && c <= 'Z')){
			type="letter";
		}
		else if('0'<=c&&c<='9'){
			type="digit";
		}else{
			type="opts";
		}
		return type;
	}
	
	/**
	 * 判断从现在index开始是否是关键字
	 * @param String word
	 * @return Map<String,String>
	 */
	public Map<String,String> GetKeyWord(String word) {
		Map<String ,String> m=new HashMap<String ,String>();
		char firstChar=word.charAt(0);
		switch(firstChar){
		case 'b':
			if(word=="begin"||word.equals("begin")){
				String sortNum=g.getSortNum(word)+"";
				m.put("sortNum",sortNum);
				m.put("keyword", "begin");
			}
			break;
			
		case 'i':
			if(word=="if"||word.equals("if")){
				String sortNum=g.getSortNum(word)+"";
				m.put("sortNum",sortNum);
				m.put("keyword", "if");
			}
			break;
		
		case 't':
			if(word=="then"||word.equals("then"))
			{
				String sortNum=g.getSortNum(word)+"";
				m.put("sortNum",sortNum);
				m.put("keyword", "then");
			}
			break;
		
		case 'w':
			if(word=="while"||word.equals("while")){
				String sortNum=g.getSortNum(word)+"";
				m.put("sortNum",sortNum);
				m.put("keyword", "while");
			}
			break;
		
		case 'd':
			if(word=="do"||word.equals("do"))
			{
				String sortNum=g.getSortNum(word)+"";
				m.put("sortNum",sortNum);
				m.put("keyword", "do");
			}
			break;
		
		case 'e':
			if(word=="end"||word.equals("end")){
				String sortNum=g.getSortNum(word)+"";
				m.put("sortNum",sortNum);
				m.put("keyword", "end");
			}
			break;
		}
		return m;
	}

	/**
	 * 判断一个字符是不是合法的ID,如果是，则返回true，否则，返回这个word的错误码false
	 * @param string word
	 * @return boolean
	 */
	public boolean IsID(String word) {
		char c;
		char firstChar=word.charAt(0);//获取第一个字符
		
		if(GetCharType(firstChar)=="letter"){//判断第一个字符是不是letter，否则肯定不是合法的
			for(int i=1;i<word.length();i++){//从第二个字符开始
				c=word.charAt(i);
				if(GetCharType(c)=="letter")//如果是letter,下一个字符
					continue;
				else if(GetCharType(c)=="digit")//如果是digit,下一个字符
					continue;
				else//否则报错
					return false;
			}
			return true;
		}
		return false;
	}
	

	/**
	 * 判断一个串是不是合法的digit
	 * @param word
	 * @return
	 */
	public boolean IsNum(String word) {
		char c;
		char firstChar=word.charAt(0);//获取第一个字符
		
		if(GetCharType(firstChar)=="digit"){//判断第一个字符是不是digit，否则肯定不是合法的
			for(int i=1;i<word.length();i++){//从第二个字符开始
				c=word.charAt(i);
				if(GetCharType(c)=="digit")//如果是digit,下一个字符
					continue;
				else//否则报错
					return false;
			}
			return true;
		}
		return false;
	}


	/**
	 * 判断是否是双目运算符
	 * @param word
	 * @return
	 */
	private boolean IsDoubleOpt(String word) {
		String optDouble[]={":=","<=","<>",">="};//双目运算符
		for(int i=0;i<optDouble.length;i++)
			if(word.equals(optDouble[i]))return true;
		return false;
	}

	/**
	 * 判断是否是单目运算符
	 * @param word
	 * @return
	 */
	private boolean IsSingleOpt(String word) {
		char optSingle[]={'+','-','*','/','<','>','='};//单目运算符
		for(int i=0;i<optSingle.length;i++)
			if(word.equals(optSingle[i]+""))return true;
		return false;
	}

	/**
	 * 判断是否是终结符
	 * @param word
	 * @return
	 */
	private boolean IsEndOpt(String word) {
		char End[]={':' ,';' ,'(' , ')' , '#' };//终结符
		for(int i=0;i<End.length;i++)
			if(word.equals(End[i]+""))return true;
		return false;
	}
	
}
