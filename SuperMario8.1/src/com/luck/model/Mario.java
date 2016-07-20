package com.luck.model;
import com.luck.interfaces.Flint;
import com.luck.interfaces.Moveable;
import com.luck.main.*;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.luck.interfaces.*;
public class Mario implements Moveable,Kill
{
	private boolean winSound=false;
	private Down downThread=null;
	private Thread upThread=null;
	private int locaX=0;
	private int locaY=0;
	private boolean speedUp=false;
	private int pressCount=0;
	private int few=5;
	private double downSpeed=0;
	private JumpThread jumpThread=null;
	private boolean noEn=false;
	private static double Bbei=1.2;
	private static double Sbei=0.9;
	private static final int MAX_SIZE=24;
	private static final int[] BIG_sizeX={(int)(56*Bbei),(int)(58*Bbei),(int)(58*Bbei),(int)(53*Bbei),(int)(53*Bbei),(int)(53*Bbei),(int)(53*Bbei),(int)(48*Bbei),(int)(43*Bbei),(int)(44*Bbei),(int)(49*Bbei),(int)(55*Bbei),(int)(59*Bbei),(int)(56*Bbei),(int)(55*Bbei),(int)(43*Bbei),(int)(38*Bbei),(int)(35*Bbei),(int)(37*Bbei),(int)(35*Bbei),(int)(39*Bbei),(int)(40*Bbei),(int)(48*Bbei),(int)(53*Bbei),(int)(54*Bbei)};
	private static final int[] BIG_sizeY={(int)(81*Bbei),(int)(78*Bbei),(int)(78*Bbei),(int)(76*Bbei),(int)(76*Bbei),(int)(76*Bbei),(int)(79*Bbei),(int)(76*Bbei),(int)(80*Bbei),(int)(80*Bbei),(int)(80*Bbei),(int)(81*Bbei),(int)(82*Bbei),(int)(81*Bbei),(int)(79*Bbei),(int)(77*Bbei),(int)(79*Bbei),(int)(79*Bbei),(int)(76*Bbei),(int)(75*Bbei),(int)(80*Bbei),(int)(83*Bbei),(int)(83*Bbei),(int)(82*Bbei),(int)(81*Bbei)};
	private static final int BIG_sizeX_STAND=(int)(55*Bbei);
	private static final int BIG_sizeY_STAND=(int)(74*Bbei);
	private static final int BIG_sizeX_JUMPING_L=43;
	private static final int BIG_sizeY_JUMPING_L=92;
	private static final int BIG_sizeX_JUMPING_R=(int)(47*Bbei);
	private static final int BIG_sizeY_JUMPING_R=(int)(73*Bbei);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final int[] SMALL_sizeX={(int)(56*Sbei),(int)(58*Sbei),(int)(58*Sbei),(int)(53*Sbei),(int)(53*Sbei),(int)(53*Sbei),(int)(53*Sbei),(int)(48*Sbei),(int)(43*Sbei),(int)(44*Sbei),(int)(49*Sbei),(int)(55*Sbei),(int)(59*Sbei),(int)(56*Sbei),(int)(55*Sbei),(int)(43*Sbei),(int)(38*Sbei),(int)(35*Sbei),(int)(37*Sbei),(int)(35*Sbei),(int)(39*Sbei),(int)(40*Sbei),(int)(48*Sbei),(int)(53*Sbei),(int)(54*Sbei)};
	private static final int[] SMALL_sizeY={(int)(81*Sbei),(int)(78*Sbei),(int)(78*Sbei),(int)(76*Sbei),(int)(76*Sbei),(int)(76*Sbei),(int)(79*Sbei),(int)(76*Sbei),(int)(80*Sbei),(int)(80*Sbei),(int)(80*Sbei),(int)(81*Sbei),(int)(82*Sbei),(int)(81*Sbei),(int)(79*Sbei),(int)(77*Sbei),(int)(79*Sbei),(int)(79*Sbei),(int)(76*Sbei),(int)(75*Sbei),(int)(80*Sbei),(int)(83*Sbei),(int)(83*Sbei),(int)(82*Sbei),(int)(81*Sbei)};
	private static final int SMALL_sizeX_STAND=(int)(55*Sbei);
	private static final int SMALL_sizeY_STAND=(int)(74*Sbei);
	private static final int SMALL_sizeX_JUMPING_L=37;
	private static final int SMALL_sizeY_JUMPING_L=80;
	private static final int SMALL_sizeX_JUMPING_R=(int)(47*Sbei);
	private static final int SMALL_sizeY_JUMPING_R=(int)(73*Sbei);
	private int step=0;
	private boolean canWork=true;
	private boolean pressA=false;//����
	private boolean pressD=false;//����
	private Control control=null;
	private boolean big=false;//�Ƿ�����/*************************************************/
	private boolean canAtack=false;//�Ƿ���Թ���
	public void setCanAtack(boolean canAtack)
	{
		this.canAtack = canAtack;
	}
	public boolean isCanAtack() 
	{
		return canAtack;
	}
	private boolean noEnemy=false;//�Ƿ��޵�
	private int speed=1;//�ٶ�
	private int jumpHight=250;//���ø߶�
	private Flint withFlint=null;//��������Ӳ��
	private int down=0;//�˶�״��1��ʾ���� 0��ʾ���� -1��ʾ����
	private int crashType=0;//��ײ������
	private int direction=1;//������ 1��ʾ���� -1��ʾ����
	private List<Bullet> bullets=new ArrayList<Bullet>();//�ӵ�
	private Set<Bullet> deleteBullet=new HashSet<Bullet>();
	private boolean downDie=false;
	/**
	 *  �����Ƿ��ǽ���״̬
	 */
	public boolean isDownDie() 
	{
		return downDie;
	}
	/**
	 * ����һ������
	 */
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	/**
	 * 
	 * @param locaX ������
	 * @param locaY ������
	 */
	public Mario(int locaX, int locaY)
	{
		this.locaX=locaX;
		this.locaY=locaY;
	}
	/**
	 * 
	 * @return �Ƿ�Ϊ����״̬
	 */
	public boolean isNoEn() 
	{
		return noEn;
	}
	/**
	 * 
	 * @param control �ܼ��������
	 */
	public void setControl(Control control)
	{
		this.control = control;
		new stepThread().start();
	}
	/**
	 * 
	 * @return �����Ƿ����������
	 */
	public boolean isCanWork()
	{
		return canWork;
	}
	/**
	 * 
	 * @param canWork �����Ƿ����������
	 */
	public void setCanWork(boolean canWork) 
	{
		this.canWork = canWork;
	}
	/**
	 * 
	 * @return ��ȡ������ײ��Ӳ��
	 */
	public Flint getWithFlint() 
	{
		return withFlint;
	}
	/**
	 *  ��ȡ���ڵķ���
	 */
	public int getDirection()
	{
		return direction;
	}
	/**
	 *  
	 * @return �Ƿ����޵�״̬
	 */
	public boolean isNoEnemy() 
	{
		return noEnemy;
	}
	/**
	 * 
	 * @param noEnemy ����Ϊ�޵�״̬
	 */
	public void setNoEnemy(boolean noEnemy) 
	{
		this.noEnemy = noEnemy;
	}
	/**
	 * 
	 * @return �������ڵ���ײ����
	 */
	public int getcrashType() 
	{
		return crashType;
	}
	/**
	 * 
	 * @param big �Ƿ�Ϊ�������
	 */
	public void setBig(boolean big)
	{
		this.big=big;
	}
	/**
	 * 
	 * @return �����Ƿ�Ϊ�������
	 */
	public boolean isBig() 
	{
		return big;
	}
	/**
	 *  
	 * @return ��ȡ�ӵ�����
	 */
	public List<Bullet> getBullets()
	{
		return bullets;
	}
	/**
	 * 
	 * @return �����ٶ�
	 */
	public int getSpeed() 
	{
		return speed;
	}
	/**
	 *  
	 * @param speed �����ٶ�
	 */
	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}
	/**
	 *  �����˶�״̬
	 */
	public int getDown() 
	{
		return down;
	}
	/**
	 * ��ȡ������
	 */
	public int getLocaX() 
	{
		return locaX;
	}
	/**
	 * ���ú�����
	 */
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	/**
	 * 
	 * @return ����������
	 */
	public int getLocaY() 
	{
		return locaY;
	}
	/**
	 * 
	 * @param locaY ����������
	 */
	public void setLocaY(int locaY) 
	{
		this.locaY = locaY;
	}
	/**
	 * 
	 * @return ��ȡ����Ĵ�С
	 */
	public int getSizeX() 
	{
		if(pressA||pressD)
		{
			if(step>MAX_SIZE)return isBig()?BIG_sizeX[0]:SMALL_sizeX[0];
			return isBig()?BIG_sizeX[step]:SMALL_sizeX[step];
		}
		if(down==0)return isBig()?BIG_sizeX_STAND:SMALL_sizeX_STAND;
		if(down!=0&&direction==1)return isBig()?BIG_sizeX_JUMPING_R:SMALL_sizeX_JUMPING_R;
		return isBig()?BIG_sizeX_JUMPING_L:SMALL_sizeX_JUMPING_L;
	}
	/**
	 * 
	 * @return ��ȡ����Ĵ�С
	 */
	public int getSizeY() 
	{
		if(pressA||pressD)
		{
			if(step>MAX_SIZE)return isBig()?BIG_sizeY[0]:SMALL_sizeY[0];
			return isBig()?BIG_sizeY[step]:SMALL_sizeY[step];
		}
		if(down==0)return isBig()?BIG_sizeY_STAND:SMALL_sizeY_STAND;
		if(down!=0&&direction==1)return isBig()?BIG_sizeY_JUMPING_R:SMALL_sizeY_JUMPING_R;
		return isBig()?BIG_sizeY_JUMPING_L:SMALL_sizeY_JUMPING_L;
	}
	private class SpeedUpThread extends Thread 
	{
		public void run()
		{
			try 
			{
				sleep(500);
				speedUp=false;
				speed=1;
				System.out.println(speed);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	private class getCrash extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				getCrashType();
		//		System.out.println(crashType+"  "+downThread+" "+down);
			/*	if(crashType==CrashType.NO_CRASH&&downThread==null&&down==0&&locaY!=control.getCutLine())
				{
					System.out.println(crashType+"  "+downThread+" "+down);
					new Down().start();
				}*/
				try 
				{
					sleep(10);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	private class stepThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				try 
				{
					if(pressA||pressD)
					{
						if(step+1>MAX_SIZE)step=0;else step++;
					}
					sleep(30);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * ����Ϊ�޵�״̬
	 */
	public void setNoEn()
	{
		new NoEnThread().start();
	}
	private class NoEnThread extends Thread
	{
		public void run()
		{
			noEn=true;
			try 
			{
				sleep(2000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			noEn=false;
		}
	}
	/**
	 *  �滭
	 */
	public void paint(Graphics g)//����Mario��ͼƬ
	{
	//	System.out.println(crashType);
	//	System.out.println(downDie);
		move();//���ƶ�
		Color c=g.getColor();
		if (locaY>1000) 
		{
			locaY=-1000;
			locaX=-500;
			control.die();
		}
		g.setColor(Color.black);
//		g.drawRect(this.getLocaX(), this.getLocaY()-this.getSizeY(), this.getSizeX(), this.getSizeY());
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		for(int i=0;i<bullets.size();i++)//�����ӵ�
		{
			Bullet b=bullets.get(i);
			b.hit(control.getDangerous(), control.getFlints(),this);//�ӵ���Ͱ���߹���
			for(int j=0;j<control.getHoles().size();j++)
			{
				if(control.getHoles().get(j).canPaint())
				control.getHoles().get(j).DownDie((Moveable)b);
			}
			if(b.getLocaY()>750)deleteBullet.add(b);
			if(b!=null)b.paint(g);
		}
		bullets.removeAll(deleteBullet);
		deleteBullet.clear();
		if(isNoEn()||noEnemy)
		{
			int p=(int)(100*Math.random());
			if(p<50&&!control.isWin())return;
		}
		if(down!=0)//������
		{
			if(direction==1)g.drawImage(isBig()?ImageTool.BIG_JUMPING_R:ImageTool.SMALL_JUMPING_R, locaX, locaY-(isBig()?BIG_sizeY_JUMPING_L:SMALL_sizeY_JUMPING_L), null);else
			if(direction==-1)g.drawImage(isBig()?ImageTool.BIG_JUMPING_L:ImageTool.SMALL_JUMPING_L, locaX, locaY-(isBig()?BIG_sizeY_JUMPING_L:SMALL_sizeY_JUMPING_L), null);
		}else
		if(down==0)//û��
		{
			if((pressA&&pressD)||(!pressA&&!pressD))
			{
				if(direction==1)g.drawImage(isBig()?ImageTool.BIG_STAND_R:ImageTool.SMALL_STAND_R, locaX, locaY-(isBig()?BIG_sizeY_STAND:SMALL_sizeY_STAND), null);else
				if(direction==-1)g.drawImage(isBig()?ImageTool.BIG_STAND_L:ImageTool.SMALL_STAND_L, locaX, locaY-(isBig()?BIG_sizeY_STAND:SMALL_sizeY_STAND),null);
			}else
			if(pressD)//��������
			{	if(step>MAX_SIZE)step=0;
				g.drawImage(isBig()?ImageTool.BIG_RUNNING_IMAGES_R[step]:ImageTool.SMALL_RUNNING_IMAGES_R[step], locaX, locaY-(isBig()?BIG_sizeY[step]:SMALL_sizeY[step]), null);
			}else
			if(pressA)//��������
			{
				if(step>MAX_SIZE)step=0;
				g.drawImage(isBig()?ImageTool.BIG_RUNNING_IMAGES_L[step]:ImageTool.SMALL_RUNNING_IMAGES_L[step], locaX, locaY-(isBig()?BIG_sizeY[step]:SMALL_sizeY[step]),null);
			}
			g.setColor(c);
		}
		g.setColor(c);
	}
	/**
	 * 
	 * @param e �����¼�
	 *  �����¼����¼�
	 */
	public void disposeKey(KeyEvent e)//������̰����¼�
	{
		int key=e.getKeyCode();
		if(key==e.VK_J)
		{
			if(upThread!=null)
			{
				upThread.stop();
				speedUp=true;
			}
		}
		if(key==e.VK_A||key==e.VK_D)
		{
			if(key==KeyEvent.VK_A)
			{
				pressA=true;
				direction=-1;
			}else
			{
				pressCount++;
				pressD=true;
				direction=1;
			}
		}else
		if(key==e.VK_K&&down==0&&!isDownDie())new JumpThread().start();//�����������jump����
	}
	/**
	 * ������
	 */
	public void jump()
	{
		new LJumpThread().start();
	}
	private class LJumpThread extends Thread
	{
		public void run()
		{
			try
			{
				if(downThread!=null)
				{
					downThread.stop();
				}
				int n=60;
				int site=locaY;
				for(int i=site;i>=site-n;i--)//��������ʱ��ı�״̬
				{
					if(direction==1&&((i&1)==1))locaX++;else locaX--;
					locaY=i;
					sleep(2);
					down=1;
				}
				int now=site;
				site=locaY;
				for(int i=site;i<=control.getCutLine();i++)//��������ʱ��ı�״̬
				{
					if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U))//�����ĳ��pipe���Ϸ����������������߳�
					{
						down=0;
						new Down().start();
						return;
					}
					locaY=i;
					sleep(2);
					down=-1;
				}
				down=0;
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * 
	 * @param e �����¼�
	 *  �����ͷż����¼�
	 */
	public void releaseKey(KeyEvent e)//��������ͷ��¼� 
	{
		int key=e.getKeyCode();
		if(key==e.VK_A||key==e.VK_D)pressCount=0;
		if(key==e.VK_A)pressA=false;else 
		if(key==e.VK_D)pressD=false;
		if(key==e.VK_J)
		{
			if(upThread!=null)
			{
				upThread.stop();
				upThread=new SpeedUpThread();
				upThread.start();
			}
		}
		if(key==e.VK_J&&canAtack)this.attach();//�ͷ�j���ҿ��Թ����򹥻�
	}
	private void attach()//���� 
	{
		SoundTool.play(SoundTool.fireSound);
		bullets.add(new Bullet(locaX+(getSizeX()>>1), locaY-(getSizeY()>>1), this.control, direction));//����ӵ� 
	}
	/**
	 *  �ƶ�����
	 */
	public void move()//�ƶ����� 
	{
		if(pressA^pressD)
		{
			pressCount++;
			if(pressCount<20)speed=2;
			if(pressCount>20&&pressCount<40)speed=3;
			if(pressCount>40)speed=4;
			if(speedUp)speed=5;
			
		}
		if(getCrashType()&&((crashType==CrashType.WALL_L||crashType==CrashType.PIPE_L||crashType==CrashType.WUWL))&&direction==1)
		{
			locaX-=3;
			return;
		}else
		if(getCrashType()&&((crashType==CrashType.WALL_R||crashType==CrashType.PIPE_R||crashType==CrashType.WUWR))&&direction==-1)
		{
			locaX+=3;//��ײ��Ӳ�ﱻ������
			return;
		}
		if(pressA&&pressD)return;//ad��������ʱ�����ƶ�
		if(pressA)
		{
			if(locaX<=100&&control.getGroundLocaX()<0)control.allMoveR();else //�ߵ���߽绹�ܼ���������
			if(locaX-speed>0)locaX=locaX-speed;
		}else
		if(pressD)
		{
			if(control.getFinal_line()+control.getGroundLocaX()<612||locaX+speed<(control.getSizeX()>>1))locaX=locaX+speed;else control.allMoveL();//�ߵ��м䳡���ƶ����������ƶ�����
		}
	}
	private class Down extends Thread//���������߳�   //��������������߳��������������ʱ������ײ��������������̼߳����Ƿ���Ҫ�ٴ�����
	{
		public void run()
		{
			if(down==1)return;
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			if(downThread!=null)
			{
				downThread.stop();
			}
			downThread=this;
		//	System.out.println("enter down!");
		//	while(down!=-1&&locaY!=control.getCutLine())//û���������һֱ��ѭ��
			while(locaY<control.getCutLine())
			{
				boolean flag=false;//
				int site=locaY;
		//		System.out.println(crashType+"  "+down+"  "+canWork);
				if((getCrashType()&&(crashType==CrashType.NO_CRASH)&&(down!=1))||canWork==false)//���߳���Ҫ�ж�Ӧ�ÿ���
				{
					flag=true;//�Ѿ�����
					double count=1;
					for(int i=site;i<=control.getCutLine();i+=(count+=0.1))
					{
						down=-1;
						locaY=i;
						downSpeed=count;
				//		System.out.println(i+"  "+control.getCutLine());
						if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U||crashType==CrashType.WUWL||crashType==CrashType.WUWR))//�����ĳ��Ӳ����Ϸ����������������߳����˳����߳�
						{
							down=0;//���Ӳ���������˶�״̬Ϊ0
							downSpeed=0;
							downThread=null;
							new Down().start();
							setCanWork(true);
							return;
						}
						try 
						{
							sleep(10);
						} catch (InterruptedException e) 
						{
							down=0;
							downThread=null;
							downSpeed=1;
							setCanWork(true);
							new Down().start();
						}
					}
					locaY=control.getCutLine();
				}
				if(flag)//�ڸ��߳�������Ѿ����������� 
				{
					if(!control.getMario().isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(control.getMario());
					}
					if(!control.getMario().isDownDie())downSpeed=1;
					down=0;
					downThread=null;
					setCanWork(true);
					return;
				}
			}
		}
	}
	private class JumpThread extends Thread//�����߳�
	{
		private int n=jumpHight;
		public void run()
		{
			if(down==-1)return;
			if(downThread!=null)
			{
				downThread.stop();
				downThread=null;
			}
			if(jumpThread!=null)
			{
				jumpThread.stop();
				jumpThread=null;
			}
			jumpThread=this;
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			SoundTool.play(SoundTool.jumpSound);
			try
			{
				int site=locaY;
				double count=Math.sqrt((2*0.085*n));
				for(int i=site;count>0;i-=(count-=0.1))//��������ʱ��ı�״̬
				{
					if(getCrashType()&&(crashType==CrashType.WALL_D||crashType==CrashType.WUWL||crashType==CrashType.WUWR))//������ִ���ײ����Ӳ�����������ϵĹ��̸�Ϊ����
					{
						down=-1;
						break;
					}
					locaY=i;
					sleep(10);
					down=1;
				}
				new Down().start();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			down=0;
		}
	}
	public Rectangle getRec()
	{
		return new Rectangle(locaX-few,locaY-getSizeY()-few,getSizeX()+2*few,getSizeY()+2*few);
	}
	public boolean getCrashType()//��ȡ��ײ����
	{
		List<Flint> flints=control.getFlints();
		Rectangle rec2=this.getRec();
		Rectangle rec1=null;
		boolean[] type=new boolean[65];
		int sumType=0;
		for(int i=0;i<flints.size();i++)//�ж��Ƿ���ײ��pipe
		{
			if(flints.get(i)!=null)rec1=flints.get(i).getRec();
			if(rec1!=null&&rec1.intersects(rec2))
			{
				withFlint=flints.get(i);
				try 
				{
					if(withFlint!=null)
					{
						int x=withFlint.getCrashType(this.getDown(),direction,withFlint.getRec(),this.getRec());//����Ӳ��Ļ�ȡ��ײ���ͷ���
						if(type[x]!=true)
						{
							sumType+=x;
							type[x]=true;
						}
					}
				} catch (Exception e) 
				{
					continue;
				}
			}
		}
		crashType=sumType;
		return true;
	}
	/**
	 * 
	 * @param b �����Ƿ���Թ���
	 */
	public void canAtack(boolean b)// �Ƿ���Թ���
	{
		this.canAtack=b;
	}
	/**
	 * ��ȡ����
	 */
	public int getType() 
	{
		return 1;
	}
	/**
	 *  ����
	 */
	public void down() 
	{
	//	System.out.println("down"+downThread);
		if(downThread==null)new Down().start();
	}
	/**
	 * ʤ��״̬�滭
	 * @param g ����������
	 * @param loca λ��
	 */
	public void paintWin(Graphics g,int loca) 
	{
		if(jumpThread!=null)
		{
			jumpThread.stop();
			jumpThread=null;
		}
		if(downThread!=null)
		{
			downThread.stop();
			downThread=null;
		}
		speed=5;
		setNoEnemy(true);
		
		if(locaY<=control.getCutLine()-40)
		{
			locaY++;
			if(isBig())g.drawImage(ImageTool.winB, loca+10, locaY-111, null);else
			g.drawImage(ImageTool.winS, loca+17, locaY-83, null);	
			g.drawImage(ImageTool.flag, loca-45, locaY-72, null);
		}else
		{
			locaY=control.getCutLine();
			pressD=true;
			down=0;
			g.drawImage(ImageTool.flag, loca-45, control.getCutLine()-114, null);
			paint(g);
		}
	}
	/**
	 *  ��ʼ��
	 */
	public void recover() 
	{
		setLocaX(0);
		setLocaY(655);
		downDie=false;
		downSpeed=1;
		speedUp=false;
		speed=1;
		pressA=false;
		pressD=false;
		setBig(false);
		setNoEnemy(false);
		setCanWork(true);
		canAtack=false;
		setSpeed(4);
		withFlint=null;
		down=0;
		direction=1;

	}
	/**
	 * 
	 * @param b �����Ǳ���״̬
	 */
	public void NoEnemy(boolean b)
	{
		this.noEn=b;
	}
	/**
	 *  ������ٶ�Ϊ0
	 */
	public void clearSpeed() 
	{
		this.speed=0;
	}
}
