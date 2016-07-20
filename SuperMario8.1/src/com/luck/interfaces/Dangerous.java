package com.luck.interfaces;
import java.awt.*;
import java.util.Map;

import com.luck.main.Model;
/**
 * 
 * @author ������
 * ���п�ɱ������µ��඼Ҫʵ�ָýӿ�
 *
 */
public interface Dangerous  
{
	/**
	 * �滭����
	 * @param g ����������
	 */
	public void paint(Graphics g);
	/**
	 * ��ȡ�������Ӿ���
	 * @return �������Ӿ���
	 */
	public Rectangle getRec();
	/**
	 * 
	 * @param kill ��������
	 * @return �����Ƿ�ɱ��kill
	 */
	public boolean kill(Kill kill);
	/**
	 * ȫ�����ƣ�����ȫ���ƶ�����
	 */
	public void moveL();
	/**
	 * ȫ�����ƣ�����ȫ���ƶ�����
	 */
	public void moveR(); 
	/**
	 * 
	 * @return �ж��Ƿ�����������ж��Ƿ���Ҫ���滭
	 */
	public boolean canPaint();
	/**
	 * 
	 * @return ����������
	 */
	public int getLocaY();
	/**
	 *  
	 * @return ��ȡ����
	 */
	public int getType();
	/**
	 * �ı��˶�����
	 */
	public void changeDirection();
	public boolean isDownDie();
	public int save(int count, Map<Integer, Model> saveModels);
}
