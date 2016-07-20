package com.luck.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 
 * @author ������
 * ����һ����ȡspring��������
 */
public class ApplicationUtil 
{
	private ApplicationContext ac=null;
	/**
	 * 
	 * @param step ��step��
	 */
	public ApplicationUtil(int step)
	{
		if(step==1024)
		{
			ac=new ClassPathXmlApplicationContext("save.xml");
		}else
		if(step==-1)
		{
			ac=new ClassPathXmlApplicationContext("beans.map.default.xml");
		}else
		ac=new ClassPathXmlApplicationContext("beans.map"+step+".xml");
	}
	public  ApplicationContext getContext()
	{
		return ac;
	}
}
 