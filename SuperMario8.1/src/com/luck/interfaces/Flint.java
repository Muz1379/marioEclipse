package com.luck.interfaces;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.luck.main.Model;
/**
 * ���еĿɱ���ײ��Ӳ�ﶼ����ʵ�ָýӿ�
 * @author ������
 *
 */
public interface Flint
{
	/**
	 * ��ȡ��Ӿ���
	 * @return ��Ӿ���
	 */
	public Rectangle getRec();//��ȡ��Ӿ���
	/**
	 * 
	 * @return ����Ӳ���Ψһ�����ʶ��
	 */
	public int getFlag();//��ȡӲ������
	/**
	 * ��ȡ��Ӳ�����ײ����
	 * @param down ��ʾ����ȥ�Ķ���������˶�״̬
	 * @param direction ��ʾ����ȥ�Ķ���������˶�״̬
	 * @param rec1 Ӳ�����Ӿ���
	 * @param rec2 ���ƶ������Ӿ���
	 * @return ������ײ����
	 */
	public int getCrashType(int down,int direction,Rectangle rec1,Rectangle rec2);//��ȡ��ײ����
	/**
	 * 
	 * @return Ӳ��ĺ�����
	 */
	public int getLocaX();
	/**
	 * 
	 * @return Ӳ���Ϸ������Ŀ���������ԭ���������ڲ����ڵĿ�������ļ���
	 */
	public Set<Growable> getDeletes();
	/**
	 * ���ú�����
	 * @param locaX ����ȥ�ĺ�����
	 */
	public void setLocaX(int locaX);
	/**
	 * ����
	 */
	public void moveL();
	/**
	 * ����
	 */
	public void moveR();
	/**
	 * ����������
	 * @param g ����������
	 */
	public void paint(Graphics g);
	/**
	 * 
	 * @return ��ǰ�����Ƿ������ƶ�
	 */
	public boolean getMoving();
	/**
	 *  ����������Ŀ�������
	 * @param growable  ����ӵĿ�������
	 */
	public void setGrowable(Growable growable); 
	/**
	 * 
	 * @return ���ص�ǰ�����Ŀ�������ļ���
	 */
	public List<Growable> getGrowable();
	/**
	 * 
	 * @return �����Ƿ��ڻ����п���
	 */
	public boolean canPaint();
	public int save(int count, Map<Integer, Model> saveModels);
}
