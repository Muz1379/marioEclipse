package com.luck.model;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import com.luck.interfaces.*;
import com.luck.main.*;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;
public class Wall implements Flint
{
	private Set<Growable> deletes=new HashSet<Growable>();
	private int step=0;
	private boolean canRemove=true;
	private boolean withMoney=false;
	private boolean eatMoney=false;
	private int[] f={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200};
	public static enum statement{normal,abnormal,afternormal}
	private static final int size=50; 
	private boolean die=false;
	private int locaX=0;
	private int locaY=0;
	private int sizeX=50;
	private int sizeY=50;
	private int moneyLocaX=0;
	private int moneyLocaY=0;
	private statement sta; //״̬
	int site=0;//�ƶ���ʱ��ĵ�ǰλ�� 
	int aim=0;//�ƶ���ʱ���Ŀ��λ��
	private boolean moving=false;//�Ƿ������ƶ�
	public static final int flag=2;//����һ��Ӳ��
	private Control control=null;
	private List<Growable> growables=(new ArrayList<Growable>());//�����Ŀ�������
	public Wall(int locaX, int locaY,boolean normal, Growable growable) 
	{
		this.locaX = locaX;
		this.locaY = locaY;
		if(normal)sta=statement.normal;else sta=statement.abnormal;
		if(growable!=null&&growable.getRec()!=null)this.growables.add(growable);
		if(!normal&&growable.getRec()==null)
		{
			withMoney=true;
		}
	}
	public void setSta(statement sta)
	{
		this.sta = sta;
	}
	public void setControl(Control control)
	{
		this.control = control;
		site=locaY;
		aim=site-(sizeY>>1);
		new RemovtThread().start();
	}
	public Set<Growable> getDeletes() 
	{
		return deletes;
	}
	public void setDie(boolean die) 
	{
		this.die = die;
	}
	public void setGrowable(Growable growable) 
	{
		try
		{
			if(growable!=null&&!this.getGrowable().contains(growable))this.growables.add(growable);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	private class MoneyThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			for(int i=0;i<=20;i++)
			{
				try 
				{
					sleep(10);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				moneyLocaY--;
			}
			eatMoney=false;
		}
	}
	public boolean isMoving()
	{
		return moving;
	}
	public int getLocaX()
	{
		return locaX;
	}
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public void setLocaY(int locaY) 
	{
		this.locaY = locaY;
	}
	public int getSizeX() 
	{
		return sizeX;
	}
	public void setSizeX(int sizeX) 
	{
		this.sizeX = sizeX;
	}
	public int getSizeY() 
	{
		return sizeY;
	}
	public void setSizeY(int sizeY) 
	{
		this.sizeY = sizeY;
	}
	public List<Growable> getGrowable() 
	{
		return growables;
	}
	public int getFlag()
	{
		return flag;
	}
	public void moveL()
	{
		setLocaX(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getFlints().remove(this);
	}
	public void moveR()
	{
		setLocaX(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getFlints().remove(this);
	}
	public void paint(Graphics g)//�滭Ӳ��
	{
		if(die)
		{
			Color c=g.getColor();
			g.drawImage(ImageTool.badWall[0],locaX-size, locaY-size+f[step], null);
			g.drawImage(ImageTool.badWall[1],locaX+size, locaY-size+f[step], null);
			g.drawImage(ImageTool.badWall[2],locaX-size, locaY+size+f[step], null);
			g.drawImage(ImageTool.badWall[3],locaX+size, locaY+size+f[step], null);
			step++;
			if(step>=20)control.getFlints().remove(this);
			return;
		}
		if(eatMoney)
		{
			g.drawImage(ImageTool.moneyImage, moneyLocaX, moneyLocaY-Money.sizeY,null);
		}
		Color c=g.getColor();
		if(sta==statement.normal)g.drawImage(ImageTool.normalWallImage,locaX,locaY,null);else//������ͨ��wall
		if(sta==statement.abnormal)g.drawImage(ImageTool.abnormalWallImage,locaX,locaY,null);else//���ƺ��а������wall
		g.drawImage(ImageTool.afterabnormalWallImage, locaX, locaY, null);//���ƺ��а�����ı�ײ��֮���wall
		g.setColor(c);
	}
	public Rectangle getRec()//��ȡ��Ӿ���
	{
		return new Rectangle(locaX,locaY,sizeX,sizeY);
	}
	public boolean getMoving()
	{
		return moving;
	}
	private class CanMoveThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			try 
			{
				sleep(1000);
			} catch(Exception e) 
			{
				e.printStackTrace();
			}
			moving=false;
		}
	}
	private void work()//�����������ײ�����Ϳ�ʼ����
	{
		if(die)return;
		control.getMario().setCanWork(false);
//		System.out.println(control.getMario().isCanWork());
		control.getMario().down();
		if((sta!=statement.afternormal)&&!moving)//���Ǳ�ײ֮����Ҳ��������ƶ���
		{
			if(control.getMario().isBig()&&sta==statement.normal)//�������ͨ����ôһײ����
			{
				SoundTool.play(SoundTool.crashSound);
				this.setDie(true);
			}
			new moveThread().start();//��ʼ�ƶ�
			moving=true;
			new CanMoveThread().start();
		}
		if(sta==statement.abnormal)//����ǲ���ͨ����ôһײ������
		{
			if(withMoney)
			{
				eatMoney=true;
				withMoney=false;
				control.addMoneys();
				moneyLocaX=locaX;
				moneyLocaY=locaY-sizeX;
				control.addScore(locaX,locaY, 100);
				new MoneyThread().start();
				sta=statement.afternormal;
				SoundTool.play(SoundTool.eatMoneySound);
				return;
			}
			sta=statement.afternormal;
			SoundTool.play(SoundTool.growingSound);
			growables.get(0).setNeedDraw();//������������
		}
	}
	private class RemovtThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				synchronized (growables) 
				{
					for(Iterator<Growable> delIterator=deletes.iterator();delIterator.hasNext();)
					{
						Growable g=delIterator.next();
						if(growables.contains(g))growables.remove(g);
					}
					synchronized (deletes)
					{
						deletes.clear();
					}
				}
				try
				{
					sleep(50);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
	}
	private class moveThread extends Thread//wall�����߳� 
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			synchronized (growables) 
			{
				Growable g=null;
				for(Iterator<Growable> growable=growables.iterator();growable.hasNext();)
				if(growable!=null&&(g=growable.next())!=null)g.setUping(true);
				try 
				{
					canRemove=false;
					for(int i=site;i>=aim;i--)
					{
						for(Iterator<Growable> growable=growables.iterator();growable.hasNext();)
						{
							if(growable!=null&&(g=growable.next())!=null)g.setLocaY(i-50);
						}
						locaY=i;
						sleep(2);
					}
					for(int i=aim;i<=site;i++)
					{
						for(Iterator<Growable> growable=growables.iterator();growable.hasNext();)
						{
							if(growable!=null&&(g=growable.next())!=null)g.setLocaY(i-50);
						}
						locaY=i;
						sleep(2);
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					locaY=site;
					for(Iterator<Growable> growable=growables.iterator();growable.hasNext();)
					{
						if(growable!=null&&(g=growable.next())!=null)g.setLocaY(site-50);
					}
				}finally
				{
					for(Iterator<Growable> growable=growables.iterator();growable.hasNext();)
					if(growable!=null&&(g=growable.next())!=null)g.setUping(false);
				}
			}
		}
	}
	public int getCrashType(int down,int direction,Rectangle rec1,Rectangle rec2)//rec1Ϊwall��rec2ΪMario ��ȡײ������
	{
		//rec1��Ӳ�rec2���ƶ���
		if(die)return CrashType.NO_CRASH;
		int rec1X=(rec1.x+rec1.x+rec1.width)>>1;
		int rec1Y=(rec1.y+rec1.y+rec1.height)>>1;
		int rec2X=(rec2.x+rec2.x+rec2.width)>>1;
		int rec2Y=(rec2.y+rec2.y+rec2.height)>>1;
		int width=rec1.width+rec2.width;
		int hight=rec1.height+rec2.height;
		if(rec2Y>=rec1Y)
		{
			if(rec1X>=rec2X)
			{
				if(down!=1||(rec1X-rec2X)/((double)width)>((rec2Y-rec1Y)/(double)hight)+CrashType.POINT)
				return CrashType.WALL_L;else 
				{
					if(control.getMario().isCanWork())work();
					control.getMario().down();
					return CrashType.WALL_D; 
				}
			}else
			{
				if(down!=1||(rec2X-rec1X)/((double)width)>((rec2Y-rec1Y)/(double)hight)+CrashType.POINT)
				return CrashType.WALL_R;else 
				{
					if(control.getMario().isCanWork())work();
					control.getMario().down();
					return CrashType.WALL_D; 
				}
			}
		}else
		{
			if(rec1X>=rec2X)
			{
				if((rec1X-rec2X)/((double)width)>=((rec1Y-rec2Y)/(double)hight)+CrashType.POINT)
				return CrashType.WALL_L;else
				{
					return CrashType.WALL_U;
				}
			}else
			{
				if((rec2X-rec1X)/((double)width)>=((rec1Y-rec2Y)/(double)hight)+CrashType.POINT)return CrashType.WALL_R;else return CrashType.WALL_U; 
			}
		}
	}
	public String toString()
	{
		return ""+locaX+"*"+locaY;
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		if(withMoney)
		{
			saveModels.put(count,new Model(locaX,locaY,-2, Main.WALL_WITH_MONEY));
			count++;
			return count;
		}
		
		if(getGrowable().size()>0)
		{
			if((getGrowable().get(0)!=null&&getGrowable().get(0).isNeedDraw()))
			{
				saveModels.put(count,new Model(locaX,locaY,sta==statement.afternormal?-2:-1, Main.WALL));
				count++;
				return count;
			}else if(getGrowable().get(0)!=null&&getGrowable().get(0).isNeedDraw()==false)
			{
				int type=getGrowable().get(0).save(count,saveModels);
				count++;
				saveModels.put(count,new Model(locaX, locaY, count-1, type));
				count++;
			}
		}else
		{
			saveModels.put(count,new Model(locaX,locaY,sta==statement.afternormal?-2:-1, Main.WALL));
			count++;
		}
		return count;
	}
}
