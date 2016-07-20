package com.luck.interfaces;
import java.awt.*;
import java.util.Map;

import com.luck.main.Model;
import com.luck.model.*;
/**
 * 
 * @author ������
 *���еĿ������ﶼ����ʵ�ָýӿ�
 */
public interface Growable
{
	/**
	 * ������Ҫ��������
	 */
	public void setNeedDraw();//������Ҫ��������
	public void paint(Graphics g);
	/**
	 * 
	 * @return �����Ƿ���Ҫ��������
	 */
	public boolean isNeedDraw();//�鿴�Ƿ���Ҫ������
	/**
	 * �ÿ������ﱻ����³�
	 * @param mario ����ȥ�����������
	 */
	public void eaten(Mario mario);//������³�
	/**
	 * 
	 * @return ��ȡ������
	 */
	public int getLocaX();
	/**
	 * 
	 * @return ��ȡ������
	 */
	public int getLocaY();
	public void setLocaX(int locaX);
	public Rectangle getRec();
	/**
	 * 
	 * @return ��ȡ��Ӿ���
	 */
	public int getFlag();//��ȡ����һ�ֱ��Ե�
	public void setLocaY(int locaY);
	public int getSizeY();
	public void moveL(); 
	public void moveR();
	/**
	 * 
	 * @param uping �Ƿ����ڱ�����ȥ
	 */
	public void setUping(boolean uping);
	public int getType();
	public boolean canPaint();
	public int save(int count, Map<Integer, Model> saveModels);
}
