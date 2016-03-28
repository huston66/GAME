package jolie;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import jolie.Warrior.WTYPE;

//±¬Õ¨³éÏóÀà
abstract class Blow {
	int x,y;
	int step=0;
	static final Toolkit TLK=Toolkit.getDefaultToolkit();
	static AudioClip audio=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/Bomb.wav"));
	
	public Blow(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	abstract void draw(Graphics g);
}

//×Óµ¯±¬Õ¨Àà
class ShotBlow extends Blow
{
	Shot s;
	boolean isStart=false;
	private static final Image[] SBIMGS=
	{
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/1.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/2.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/3.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/4.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/5.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/6.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/7.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/ShotBlow/8.png"))
	};
	
	static boolean init=false;
	
	public ShotBlow(int x,int y,Shot s)
	{
		super(x-10,y+10);
		this.s=s;
	}
	
	//»­×Óµ¯±¬Õ¨µÄº¯Êý
	void draw(Graphics g)
	{
		if(!init)
		{
			for(int i=0;i<SBIMGS.length;i++)
			{
				g.drawImage(SBIMGS[i], x, y, null);
			}
			init=true;
		}
		
		/*if(!isStart)
		{
			audio.loop();
			isStart=false;
		}*/
		
		if( step==SBIMGS.length )
		{
			//audio.stop();
			s.ex.ShotsList.remove(s);
			s.shb=null;
			s=null;
			return;
		}
		
		g.drawImage(SBIMGS[step],x,y,null);
		step++;
		}
}

//ÓÂÊ¿±¬Õ¨Àà
class WrBlow extends Blow
{
	Warrior wr;
	public static final Image[] WRIMGS=
		{
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/1.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/2.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/3.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/4.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/5.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/6.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/7.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/8.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/9.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/10.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/11.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/12.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/13.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/14.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/15.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/16.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/17.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/18.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/19.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/20.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/21.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/22.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/23.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/24.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/25.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/26.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/27.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/28.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/29.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/30.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/31.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/32.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/33.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/34.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/35.png")),
			TLK.getImage(Blow.class.getClassLoader().getResource("Image/WrBlow/36.png"))
		};
	
	static boolean init=false;
	
	public WrBlow(int x,int y,Warrior wr)
	{
		super(x,y);
		this.wr=wr;
	}
	
	//»­ÓÂÊ¿±¬Õ¨Í¼Ïñ
	void draw(Graphics g)
	{
		
		if(!init)
		{
			for(int i=0;i<WRIMGS.length;i++)
			{
				g.drawImage(WRIMGS[i],x,y,null);
			}
			init=true;
		}
		
		
		
		if(step==WRIMGS.length)
		{
			wr.WrBlow=null;
			//wr.ex.WarriorsList.remove(wr);
			if(wr.type!=WTYPE.HERO)
				wr.ex.WarriorsList.remove(wr);
			if(wr.type==WTYPE.HERO)
			{
				wr.ex.sequency=2;
				wr.ex.audio.stop();
				wr.ex.ter.loop();
			}
			wr=null;
			return;
		}
		g.drawImage(WRIMGS[step],x,y,null);
		step++;
	}
}

//Õ¨µ¯Àà
class Bomb extends Blow{
	
	Image image;
	static Rectangle rec = new Rectangle( 0, 0, 36, 36 );
	static List list = new List();
	int mx, my;
	public Bomb(int x, int y) {
		super(x, y);
		image = List.img.get(0);
		mx = Map.x; my = Map.y;
	}
	boolean isAlive = true, isBlow = false;
	int step = -50;
	@Override
	void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage( image, x-Map.x+mx, y-Map.y+my, 36, 36, null );
	}
	public Rectangle getRect(){
		rec.x = x-Map.x+mx; rec.y = y-Map.y+my;
		return rec;
	}
	
	void move(){
		++step;
		if( step >= 1 && step <= 70 ){
			isBlow = true;
			image = List.img.get(step/2);
		}
		if( step > 70 ) isAlive = false;
	}
	static class List{
		static ArrayList<Image> img = new ArrayList<Image>();
		List(){
			String str  = "Image/WrBlow/" + 0 + ".png";
			img.add( TLK.getImage(Blow.class.getClassLoader().getResource(str)) );
			for( int i = 1; i <= 36; ++i ){
				str  = "Image/WrBlow/" + i + ".png";
				img.add( TLK.getImage(Blow.class.getClassLoader().getResource(str)) );
			}
		}
	}
	
}


