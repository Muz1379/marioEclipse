package com.luck.model;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.luck.interfaces.*;
import com.luck.main.Control;
import com.luck.main.Main;
import com.luck.main.Model;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;
public class Mushroom implements Growable,Moveable
{
	public static final int flagNumber=2;
	private Down downThread=null;
	private int locaX;
	private int locaY;
	private Mushroom mushroom=this;
	private double downSpeed=1;
	private int direction=1;
	private int speed=1;
	private boolean uping=false;//�Ƿ�����ש�ƶ�
	private boolean moving=false;//�Ƿ������ƶ�
	public static final int sizeX=50;
	public Flint withFlint=null;
	public static final int[] sizeY={10,20,30,40,50};
	private int drawstep=0;
	private boolean down=false;//�Ƿ������½� 
	private boolean needDraw=false;
	private int crashType=CrashType.WALL_U;
	private Control control;
	private boolean downDie=false;
	public boolean isDownDie() 
	{
		return downDie;
	}
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	public Mushroom(int locaX,int locaY)
	{
		this.locaX=locaX;
		this.locaY=locaY;
	}
	public void setControl(Control control)
	{
		this.control = control;
	}
	public void setWithFlint(Flint withFlint) 
	{
		this.withFlint = withFlint;
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public int getLocaX() 
	{
		return locaX;
	}
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	public void setLocaY(int locaY)
	{
		this.locaY=locaY;
	}
	public void setNeedDraw() 
	{
		new StepThread().start();
		needDraw=true;
	}
	public int getSizeX() 
	{
		return sizeX;
	}
	public int getSizeY() 
	{
		return sizeY[drawstep];
	}
	public int getSizeY_M() 
	{
		return sizeY[4];
	}
	public int getFlag()
	{
		return flagNumber;
	}
	public boolean isNeedDraw()
	{
		return needDraw;
	}
	public boolean isUping() 
	{
		return uping;
	}
	public void setUping(boolean uping) 
	{
		this.uping = uping;
	}
	public void eaten(Mario mario)
	{
		if(getRec().intersects(mario.getRec()))
		{
			mario.setBig(true);
			control.addScore(locaX, locaY, 1000);
			SoundTool.play(SoundTool.eatSound);
			control.getGrowables().remove(this);
		}
	}
	public void moveL()
	{
		setLocaX(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getGrowables().remove(this);	
	}
	public void moveR()
	{
		setLocaX(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getGrowables().remove(this);	
	}
	public Rectangle getRec()
	{
		if(drawstep>4)drawstep=4;
		return new Rectangle(locaX,locaY,sizeX,sizeY[drawstep]);
	}
	private class StepThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			for(int i=0;i<5;i++)
			{
				try 
				{
					sleep(20);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				drawstep=i;
			}
		}
	}
	public void paint(Graphics g)
	{
		Color c=g.getColor();
		if(!needDraw)return;
		eaten(control.getMario());
		move();
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		g.drawImage(ImageTool.mushroomImage[drawstep],locaX,locaY, null);
		if(drawstep>=4&&!moving)
		{
			new Down().start();
			moving=true;
		}
		g.setColor(c);
	}
	public boolean getCrashType()//��ȡ��ײ����
	{
		List<Flint> flints=control.getFlints();
		boolean[] type=new boolean[65];
		int sumType=0;
		try
		{
		synchronized (flints)
		{
			for(Flint flint:flints)//�ж��Ƿ���ײ��pipe
			{
				Rectangle rec1=flint.getRec();
				Rectangle rec2=this.getRec();
				if(rec1.intersects(rec2))
				{
					if(withFlint==flint)continue;
					if(withFlint!=null&&withFlint!=flint)
					{
						if(withFlint!=null&&withFlint.getGrowable().contains(this))
						try
						{
							if(withFlint!=null&&withFlint.getDeletes()!=null)withFlint.getDeletes().add(this);
						}catch (Exception e)
						{
							e.printStackTrace();
							return true;
						}
					}
					withFlint=flint;
					if(flint!=null)
					{
						int x=flint.getCrashType(this.getDown(),direction,flint.getRec(),this.getRec());//����Ӳ��Ļ�ȡ��ײ���ͷ���
						if(x==CrashType.WALL_U)
						{
							withFlint=flint;
							if(withFlint!=null&&!withFlint.getGrowable().contains(this))withFlint.setGrowable(this);
						}
						if(type[x]!=true)
						{
							sumType+=x;
							type[x]=true;
						}
					}
				}
			}
			crashType=sumType;
			if(crashType==0)withFlint=null;
			if(withFlint!=null&&withFlint.getGrowable().contains(this))
			if(withFlint!=null&&withFlint.getDeletes()!=null)withFlint.getDeletes().add(this);
		//	withFlint=null;
		//	crashType=CrashType.NO_CRASH;//û�з�����ײ������ײ����Ϊ0
			return true;
		}
		}catch(Exception e)
		{
			return true;
		}
	}
	private class Down extends Thread//���������߳�   //��������������߳��������������ʱ������ײ��������������̼߳����Ƿ���Ҫ�ٴ�����
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			if(downThread!=null)
			{
				downThread.stop();
				downThread=null;
			}
			downThread=this;
			while(true)//û���������һֱ��ѭ��
			{
				int site=locaY;
				if(locaY+sizeY[4]>=control.getCutLine())break;
				if(getCrashType()&&crashType==CrashType.NO_CRASH)//���߳���Ҫ�ж�Ӧ�ÿ���
				{
					down=true;
					double count=1;
					for(int i=site;i<=control.getCutLine()-sizeY[4];i+=(count+=0.1))
					{
						locaY=i;
						if(!isDownDie())downSpeed=count;
						if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U||crashType==CrashType.WUWL||crashType==CrashType.WUWR))//�����ĳ��Ӳ����Ϸ����������������߳����˳����߳�
						{
							down=false;
							new Down().start();
							return;
						}
						try 
						{
							sleep(10);
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					if(!mushroom.isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(mushroom);
					}
					if(!mushroom.isDownDie())downSpeed=1;
				}
				if(down)//�ڸ��߳�������Ѿ����������� 
				{
					down=false;
					downThread=null;
					return;
				}
			}
		}
	}
	public int getDown()
	{
		if(down)return-1;else return 0;
	}
	public void move()//�ƶ����� 
	{
		if(direction==1)locaX+=speed;else locaX-=speed;
		if(getCrashType()&&(crashType==CrashType.WALL_L||crashType==CrashType.WALL_R||crashType==CrashType.PIPE_L||crashType==CrashType.PIPE_R||crashType==CrashType.WUWL||crashType==CrashType.WUWR))
		{
			direction=-direction;
			if(direction==1)
			locaX+=2;else locaX-=2;
		}
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int getType() 
	{
		return 3;
	}
	public void clearSpeed() 
	{
		this.speed=0;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		if(!isNeedDraw())
		{
			saveModels.put(count,new Model(locaX,locaY,count+1, Main.MUSHROOM));
			return Main.WALL_WITH_MUSHROOM;
		}else
		{
			saveModels.put(count, new Model(locaX, locaY, -1, Main.MUSHROOM));
			System.out.println("#mushroom");
			return count+1;
		}
	}
}
