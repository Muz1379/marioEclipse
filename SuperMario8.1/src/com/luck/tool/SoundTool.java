package com.luck.tool;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * 
 * @author ������
 *����һ��������������������
 */
public class SoundTool
{
	public static Clip backSound=null;
	public static AudioInputStream backSoundLoad=null;
	public static Clip jumpSound=null;
	public static AudioInputStream jumpSoundLoad=null;   
	public static Clip eatSound=null;
	public static AudioInputStream eatSoundLoad=null;
	public static Clip growingSound=null;
	public static AudioInputStream growingSoundLoad=null;
	public static Clip killSound=null;
	public static AudioInputStream killSoundLoad=null;
	public static Clip eatMoneySound=null;
	public static AudioInputStream eatMoneySoundLoad=null;
	public static Clip fireSound=null;
	public static AudioInputStream fireSoundLoad=null; 
	public static Clip die1Sound=null;
	public static AudioInputStream die1SoundLoad=null;
	public static Clip die2Sound=null;
	public static AudioInputStream die2SoundLoad=null;
	public static Clip crashSound=null;
	public static AudioInputStream crashSoundLoad=null;
	public static Clip noEnemy=null;
	public static AudioInputStream noEnemyLoad=null;
	public static Clip hite=null;
	public static AudioInputStream hiteLoad=null;
	public static Clip win=null;
	public static AudioInputStream winLoad=null;
	public static Clip allWin=null;
	public static AudioInputStream allWinLoad=null;
	static 
	{
		try
		{
			jumpSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/��.wav"));
			jumpSound=AudioSystem.getClip();
			jumpSound.open(jumpSoundLoad);
			//getit
			jumpSound.start();
			jumpSound.stop();
			jumpSound.setMicrosecondPosition(0);
			backSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/a.wav"));
		 	backSound=AudioSystem.getClip();
		 	backSound.open(backSoundLoad);
		 	//getit
		 	backSound.start();
		 	backSound.stop();
		 	backSound.setMicrosecondPosition(0);
			eatSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/�Ե�Ģ����.wav"));
			eatSound=AudioSystem.getClip();
			eatSound.open(eatSoundLoad);
			//getit
			eatSound.start();
			eatSound.stop();
			eatSound.setMicrosecondPosition(0);
			growingSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/����Ģ��,������.wav"));
			growingSound=AudioSystem.getClip();
			growingSound.open(growingSoundLoad);
			//getit
			growingSound.start();
			growingSound.stop();
			growingSound.setMicrosecondPosition(0);
			eatMoneySoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/eatmoney.wav"));
			eatMoneySound=AudioSystem.getClip();
			eatMoneySound.open(eatMoneySoundLoad);
			//getit
			eatMoneySound.start();
			eatMoneySound.stop();
			eatMoneySound.setMicrosecondPosition(0);
			killSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/�ȵ���.wav"));
			killSound=AudioSystem.getClip();
			killSound.open(killSoundLoad);
			//getit
			killSound.start();
			killSound.stop();
			killSound.setMicrosecondPosition(0);
			fireSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/�ӻ���.wav"));
			fireSound=AudioSystem.getClip();
			fireSound.open(fireSoundLoad);
			//getit
			fireSound.start();
			fireSound.stop();
			fireSound.setMicrosecondPosition(0);
			die1SoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/����1.wav"));
			die1Sound=AudioSystem.getClip();
			die1Sound.open(die1SoundLoad);
			//getit
			die1Sound.start();
			die1Sound.stop();
			die1Sound.setMicrosecondPosition(0);
			die2SoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/����2.wav"));
			die2Sound=AudioSystem.getClip();
			die2Sound.open(die2SoundLoad);
			//getit
			die2Sound.start();
			die2Sound.stop();
			die2Sound.setMicrosecondPosition(0);
			crashSoundLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/����ש.wav"));
			crashSound=AudioSystem.getClip();
			crashSound.open(crashSoundLoad);
			//getit
			crashSound.start();
			crashSound.stop();
			crashSound.setMicrosecondPosition(0);
			noEnemyLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/�޵���.wav"));
			noEnemy=AudioSystem.getClip();
			noEnemy.open(noEnemyLoad);
			//getit
			noEnemy.start();
			noEnemy.stop();
			noEnemy.setMicrosecondPosition(0);
			hiteLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/�����ǻ�����.wav"));
			hite=AudioSystem.getClip();
			hite.open(hiteLoad);
			//getit
			hite.start();
			hite.stop();
			hite.setMicrosecondPosition(0);
			winLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/�Ǳ�ͨ��.wav"));
			win=AudioSystem.getClip();
			win.open(winLoad);
			//getit
			win.start();
			win.stop();
			win.setMicrosecondPosition(0);
			allWinLoad=AudioSystem.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/��Ϸ����.wav"));
			allWin=AudioSystem.getClip();
			allWin.open(allWinLoad);
			//getit
			allWin.start();
			allWin.stop();
			allWin.setMicrosecondPosition(0);
		}catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	/**
	 * ���ű�������
	 */
	 public static void back()
	 {
		 backSound.loop(Integer.MAX_VALUE);
	 }
	 /**
	  * ������������
	  */
	 public static void over()
	 {
		 backSound.stop();
		 backSound.setMicrosecondPosition(0);
	 }
	 /**
	  * 
	  * @param clip �����ļ���Ӧ��clip
	  * ��������
	  */
	 public static void play(Clip clip)
	 {
		 clip.setMicrosecondPosition(0);
		 clip.start();
	 }
	 public static void clear()
	 {
		 noEnemy.stop();
		 noEnemy.setMicrosecondPosition(0);
		 win.stop();
		 win.setMicrosecondPosition(0);
	 }
}
