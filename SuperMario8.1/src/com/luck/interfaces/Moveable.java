package com.luck.interfaces;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * ���п��ƶ��඼Ҫʵ�ָýӿ�
 * @author ������
 *
 */
public interface Moveable 
{
	/**
	 * 
	 * @return �������µ��˶�״̬
	 */
	public int getDown();
	/**
	 * ���ƶ�������ƶ�
	 */
	public void move();
	/**
	 * 
	 * @return ������Ӿ���
	 */
	public Rectangle getRec();
	public void paint(Graphics g);
	/**
	 * 
	 * @return ���ظÿ��ƶ����flint����ײ����
	 */
	public boolean getCrashType();
	/**
	 * 
	 * @return ����������
	 */
	public int getLocaX();
	/**
	 * 
	 * @param downDie ���øÿ��ƶ�������
	 */
	public void setDownDie(boolean downDie);
	/**
	 * 
	 * @return ���ظÿ��ƶ����Ƿ�����
	 */
	public boolean isDownDie();
	/**
	 * 
	 * @param locaX ���ú�����
	 */
	public void setLocaX(int locaX);
	/**
	 * �����䵽hole��֮����ٶ����0
	 */
	public void clearSpeed();
}
 