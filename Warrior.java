package jolie;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import jolie.Expedition.point;

//抽象勇士类
public abstract class Warrior {
		Rectangle rec = new Rectangle();
		boolean init=false;
		static final Toolkit TLK=Toolkit.getDefaultToolkit();
		static AudioClip audio=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/Rico.wav"));
		static AudioClip Bomb=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/Bomb.wav"));
		static AudioClip plus=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/getitem.wav"));
		
		int power;
		int shotCnt=0;
		int cash;
		int tube;
		int crump;
		boolean righteous;
		public static final int SHOTSIMU=5;
		public static int WARRIORSPEED=5;
		public static enum WARRIOR_DIR {U,D,L,R,STOP};
		public static enum WTYPE {HERO,GHOST,SOLDIER};
		WARRIOR_DIR currDir;
		WARRIOR_DIR moveDir;
		
		boolean isLive=true;
		boolean isCBC;
		int oldx,oldy;
		int shotcurr;
		int x,y;
		WTYPE type;
		
		Expedition ex=null;
		Hemo WrHemo;
		Blow WrBlow=null;
		static Random rd=new Random();
		
		abstract void hitDispose();
		public static final Image[] ghost=
		{
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/halloween.png")),
		};
		
		public static final Image[] up1=
		{
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/u1.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/u2.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/u3.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/u4.png")),
		};
		
		public static final Image[] down1=
		{
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/d1.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/d2.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/d3.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/d4.png")),
		};
		
		public static final Image[] left1=
		{
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/l1.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/l2.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/l3.png")),
			TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/l4.png")),
		};
		
		public static final Image[] right1=
		{
				TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/r1.png")),
				TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/r2.png")),
				TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/r3.png")),
				TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Aeneas/r4.png")),
		};
		
		public static final Image[] up2=
		{
				TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/u1.png")),
				TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/u2.png")),
		};
		
		public static final Image[] down2=
		{
					TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/d1.png")),
					TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/d2.png")),
		};
		
		public static final Image[] left2=
		{
					TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/l1.png")),
					TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/l2.png")),
		};
		
		public static final Image[] right2=
		{
					TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/r1.png")),
					TLK.getImage(Warrior.class.getClassLoader().getResource("Image/Master/r2.png")),
		};
		
		public Warrior(int x,int y,boolean righteous,WARRIOR_DIR dir,Expedition ex)
		{
			this.x=x;
			this.y=y;
			this.oldx=x;
			this.oldy=y;
			this.ex=ex;
			this.righteous=righteous;
			this.currDir=dir;
			this.moveDir=WARRIOR_DIR.STOP;
		}
		
		int currFrame;
		int totalFrames=4;
		int timeDelay;
		private Object NormalShot;
		//画出勇士
		public void draw(Graphics g)
		{
			if(isLive)
			{
				if(this.righteous)
				{
				if(moveDir==WARRIOR_DIR.STOP)
				{
				switch(currDir)
				{
				case U:
				{
					g.drawImage(up1[0],x,y,null);
					currFrame=0;
					timeDelay=0;
					break;
				}
				case D:
				{
					g.drawImage(down1[0],x,y,null);
					currFrame=0;
					timeDelay=0;
					break;
				}
				case L:
				{
					g.drawImage(left1[0],x,y,null);
					currFrame=0;
					timeDelay=0;
					break;
				}
				case R:
				{
					g.drawImage(right1[0],x,y,null);
					currFrame=0;
					timeDelay=0;
					break;
				}
				default:
					break;
				}
				}
				else
				{
					switch(currDir)
					{
					case U:
					{
						if(timeDelay==5)
						{
						currFrame++;
						if(currFrame>totalFrames-1)
							currFrame=1;
						timeDelay=0;
						}
						g.drawImage(up1[currFrame],x,y,null);
						timeDelay++;
						break;
					}
					case D:
					{
						if(timeDelay==5)
						{
						currFrame++;
						if(currFrame>totalFrames-1)
							currFrame=1;
						timeDelay=0;
						}
						g.drawImage(down1[currFrame],x,y,null);
						timeDelay++;
						break;
					}
					case L:
					{
						if(timeDelay==5)
						{
						currFrame++;
						if(currFrame>totalFrames-1)
							currFrame=1;
						timeDelay=0;
						}
						g.drawImage(left1[currFrame],x,y,null);
						timeDelay++;
						break;
					}
					case R:
					{
						if(timeDelay==5)
						{
						currFrame++;
						if(currFrame>totalFrames-1)
							currFrame=1;
						timeDelay=0;
						}
						g.drawImage(right1[currFrame],x,y,null);
						timeDelay++;
						break;
					}
					default:
						break;
					}
				}
				}
				else if(this.type==WTYPE.SOLDIER)
				{
					if(moveDir==WARRIOR_DIR.STOP)
					{
					switch(currDir)
					{
					case U:
					{
						g.drawImage(up2[0],x,y,null);
						break;
					}
					case D:
					{
						g.drawImage(down2[0],x,y,null);
						break;
					}
					case L:
					{
						g.drawImage(left2[0],x,y,null);
						break;
					}
					case R:
					{
						g.drawImage(right2[0],x,y,null);
						break;
					}
					default:
						break;
					}
					}
					else
					{
						switch(currDir)
						{
						case U:
						{
							g.drawImage(up2[1],x,y,null);
							break;
						}
						case D:
						{
							g.drawImage(down2[1],x,y,null);
							break;
						}
						case L:
						{
							g.drawImage(left2[1],x,y,null);
							break;
						}
						case R:
						{
							g.drawImage(right2[1],x,y,null);
							break;
						}
						default:
							break;
						}
					}
				}
				else
				{
					switch(currDir)
					{
					case U:
					{
						g.drawImage(ghost[0],x,y,null);
						break;
					}
					case D:
					{
						g.drawImage(ghost[0],x,y,null);
						break;
					}
					case L:
					{
						g.drawImage(ghost[0],x,y,null);
						break;
					}
					case R:
					{
						g.drawImage(ghost[0],x,y,null);
						break;
					}
					default:
						break;
					}
				}
			this.WrHemo.draw(g);
		}
		else
		{
			if(WrBlow!=null)
				WrBlow.draw(g);
		}
	}
	
	//勇士移动
	public void move()
	{
		if(this.isLive&&moveDir!=WARRIOR_DIR.STOP)
		{
			currDir=moveDir;
			switch(moveDir)
			{
				case U:
				{
					if(y>50)
					{
						oldy=y;
						y-=WARRIORSPEED;
						if(isHitWall())
							y=oldy;
					}
					break;
				}
				case D:
				{
					if(y<ex.HEIGHT-40)
					{
						oldy=y;
						y+=WARRIORSPEED;
						if(isHitWall())
							y=oldy;
					}
					break;
				}
				case L:
				{
					if(x>40)
					{
						oldx=x;
						x-=WARRIORSPEED;
						if(isHitWall())
							x=oldx;
					}
					break;
				}
				case R:
				{
					if(x<ex.WIDTH-25)
					{
						oldx=x;
						x+=WARRIORSPEED;
						if(isHitWall())
							x=oldx;
					}
					break;
				}
				default:
					break;
			}
		}
		
		Item ie=null;
		for(int i=0;i<ex.ItemList.size();i++)
		{
			ie=ex.ItemList.get(i);
			if(ie.getRec().intersects(this.getRect()))
			{
				ie.eat(this);
				
				if(this.isCBC==false)
				plus.play();
			}
		}
		
		this.hitDispose();
	}
	
	public void setMoveDir(WARRIOR_DIR dir)
	{
		this.moveDir=dir;
	}
	
	//勇士开火
	public void fire()
	{
		if(shotCnt<SHOTSIMU)
		{
			if(this.type==WTYPE.HERO)
			{
				if(this.shotcurr>0)
				{
					audio.play();
					shotCnt++;
					this.shotcurr--;
					ex.ShotsList.add(new NormalShot(this));
				}
			}
			else
			{
				shotCnt++;
				ex.ShotsList.add(new NormalShot(this));
			}
		}
	}
	
	//处理勇士死亡
	public void dead()
	{
		if(this.type==WTYPE.SOLDIER)
			ex.uw.cash+=100;
		else if(this.type==WTYPE.GHOST)
			ex.uw.cash+=200;
		
		isLive=false;
		WrBlow=new WrBlow(x,y,this);
		Bomb.play();
	}
	
	//判断是否碰墙
	public boolean isHitWall()
	{
		Wall w=null;
		
		for(int i=0;i<ex.WallList.size();i++)
		{
			w=ex.WallList.get(i);
			
			if(w.isHits(this.getRect()))
			{
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getRect()
	{
		rec.setBounds( x, y, 40, 80 );
		return rec;
	}
}

//机器人类
class PcWarrior extends Warrior
{
	public static ArrayList<WARRIOR_DIR> HITDIR=new ArrayList<WARRIOR_DIR>();
	static {HITDIR.add(WARRIOR_DIR.U);HITDIR.add(WARRIOR_DIR.D);HITDIR.add(WARRIOR_DIR.L);
	HITDIR.add(WARRIOR_DIR.R);HITDIR.add(WARRIOR_DIR.STOP);}
	ArrayList<WARRIOR_DIR> hitDir=new ArrayList<WARRIOR_DIR>();
	int dist;
	
	public PcWarrior(int x,int y,boolean righteous,WARRIOR_DIR dir,Expedition ex)
	{
		super(x,y,righteous,dir,ex);
		this.isCBC=true;
		this.type=WTYPE.SOLDIER;
		this.WrHemo=new Hemo(10,this);
	}
	
	//增加机器人
	public static void add(int cnt,WTYPE type,Expedition ex)
	{
		//if(cnt>5)
			//cnt=5;
		
		PcWarrior pcwr=null;
		boolean isOK=true;
		int randx;
		int randy;
		
		for(int i=0;i<cnt;)
		{
			isOK=true;
			randx=(Warrior.rd.nextInt(15)+3)*50;
			randy=(Warrior.rd.nextInt(15)+3)*35;
			
			if(randy<=100||randy>=ex.HEIGHT-50||randx<=100||randx>=ex.WIDTH-50)
			{
				continue;
			}
			
			switch(type)
			{
				case SOLDIER:{pcwr=new PcWarrior(randx,randy,false,WARRIOR_DIR.L,ex);}break;
				case GHOST:{pcwr=new Specter(randx,randy,false,WARRIOR_DIR.L,ex);}break;
				default:
					break;
			}
			
			for(int j=0;j<ex.WarriorsList.size();j++)
			{
				if(pcwr.getRect().intersects(ex.WarriorsList.get(j).getRect()))
				{	
					isOK=false;
					break;
				}
			}
			
			if(isOK)
			{
				Wall w=null;
				
				for(int j=0;j<ex.WallList.size();j++)
				{
					w=ex.WallList.get(j);
					if(w.isHits(pcwr.getRect()))
					{
						isOK=false;
						break;
					}
				}
			}
			if(isOK)
			{
				ex.WarriorsList.add(pcwr);
				i++;
			}
		}
	}
	
	//处理碰撞
	public void hitDispose()
	{
		boolean ishit=false;
		Warrior wrtemp;
		WARRIOR_DIR currDir;
		hitDir.addAll(0,HITDIR);
		for(int j=0;j<ex.WarriorsList.size();j++)
		{
			wrtemp=ex.WarriorsList.get(j);
			if(wrtemp==this||!wrtemp.isLive)
			{
				continue;
			}
			
			if(this.getRect().intersects(wrtemp.getRect()))
			{
				currDir=this.moveDir;
				ishit=true;
				switch(moveDir)
				{
					case U:
					case D:{y=oldy;}break;
					case L:
					case R:{x=oldx;}break;
					default:break;
				}
				this.setMoveDir(WARRIOR_DIR.STOP);
				wrtemp.setMoveDir(WARRIOR_DIR.STOP);
				
				if(hitDir.contains(this.moveDir))
				{
					hitDir.remove(currDir);
				}
				if(righteous!=wrtemp.righteous)
				{
					wrtemp.WrHemo.reduceWithHit(1);
					this.WrHemo.reduceWithHit(1);
				}
			}
		}
		
		if(ishit)
		{
			hitDir.add(WARRIOR_DIR.STOP);
			if(hitDir.size()>0)
			{
				super.setMoveDir(hitDir.get(Warrior.rd.nextInt(hitDir.size())));
			}
		}
	}
	
	//追踪函数
	public void chase(point a,point b)
	{
		int sx=a.x;
		int sy=a.y;
		int fx=b.x;
		int fy=b.y;
		int dir=Expedition.path[Expedition.id[sx][sy]][Expedition.id[fx][fy]];
		if(dir==0)
			setMoveDir(Warrior.WARRIOR_DIR.R);
		else if(dir==1)
			setMoveDir(Warrior.WARRIOR_DIR.D);
		else if(dir==2)
			setMoveDir(Warrior.WARRIOR_DIR.U);
		else
			setMoveDir(Warrior.WARRIOR_DIR.L);
		
		super.move();
	}
	
	//自由行动函数
	public void autoAction()
	{
		int change=Warrior.rd.nextInt(50);
		
		switch(change)
		{
			case 15:
			{
				setMoveDir(Warrior.WARRIOR_DIR.U);
				break;
			}
			case 10:
			{
				setMoveDir(Warrior.WARRIOR_DIR.D);
				break;
			}
			case 5:
			{
				setMoveDir(Warrior.WARRIOR_DIR.L);
				break;
			}
			case 1:
			{
				setMoveDir(Warrior.WARRIOR_DIR.R);
				break;
			}
			case 0:
			{
				setMoveDir(Warrior.WARRIOR_DIR.STOP);
				break;
			}
			default:
				break;
		}
				
		super.move();
	}
}

//鬼类
class Specter extends PcWarrior
{
	public static ArrayList<WARRIOR_DIR> HITDIR=new ArrayList<WARRIOR_DIR>();
	static {HITDIR.add(WARRIOR_DIR.U);HITDIR.add(WARRIOR_DIR.D);HITDIR.add(WARRIOR_DIR.L);
	HITDIR.add(WARRIOR_DIR.R);HITDIR.add(WARRIOR_DIR.STOP);}
	ArrayList<WARRIOR_DIR> hitDir=new ArrayList<WARRIOR_DIR>();
	
	public Specter(int x,int y,boolean righteous,WARRIOR_DIR dir,Expedition ex)
	{
		super(x,y,righteous,dir,ex);
		this.isCBC=true;
		this.WrHemo=new Hemo(5,this);
		this.type=WTYPE.GHOST;
	}
	
	//鬼移动函数
	public void move()
	{
		if(this.isLive&&moveDir!=WARRIOR_DIR.STOP)
		{
			currDir=moveDir;
			switch(moveDir)
			{
				case U:
				{
					if(y>50)
					{
						oldy=y;
						y-=WARRIORSPEED;
					}
					break;
				}
				case D:
				{
					if(y<ex.HEIGHT-20)
					{
						oldy=y;
						y+=WARRIORSPEED;
					}
					break;
				}
				case L:
				{
					if(x>40)
					{
						oldx=x;
						x-=WARRIORSPEED;
					}
					break;
				}
				case R:
				{
					if(x<ex.WIDTH-25)
					{
						oldx=x;
						x+=WARRIORSPEED;
					}
					break;
				}
				default:
					break;
			}
		}
		this.hitDispose();
	}
	
	//鬼自由移动函数
	public void autoAction()
	{
		int change=Warrior.rd.nextInt(50);
		
		switch(change)
		{
			case 15:
			{
				setMoveDir(Warrior.WARRIOR_DIR.U);
				break;
			}
			case 10:
			{
				setMoveDir(Warrior.WARRIOR_DIR.D);
				break;
			}
			case 5:
			{
				setMoveDir(Warrior.WARRIOR_DIR.L);
				break;
			}
			case 1:
			{
				setMoveDir(Warrior.WARRIOR_DIR.R);
				break;
			}
			case 0:
			{
				setMoveDir(Warrior.WARRIOR_DIR.STOP);
				break;
			}
			default:
				break;
		}
		move();
	}
	
	//鬼追踪函数
	public void chase(point a,point b)
	{
		int sx=a.x;
		int sy=a.y;
		int fx=b.x;
		int fy=b.y;
		int dir=Expedition.path[Expedition.id[sx][sy]][Expedition.id[fx][fy]];
		if(dir==0)
			setMoveDir(Warrior.WARRIOR_DIR.R);
		else if(dir==1)
			setMoveDir(Warrior.WARRIOR_DIR.D);
		else if(dir==2)
			setMoveDir(Warrior.WARRIOR_DIR.U);
		else
			setMoveDir(Warrior.WARRIOR_DIR.L);
		
		move();
	}
	
}

	class myPerson extends Warrior                  //继承人物主类的player
	{
		static ArrayList<Monster> zombie = new ArrayList<Monster>();
		LinkedList<WARRIOR_DIR> keyList=new LinkedList<WARRIOR_DIR>(); 
		Map map;
		public static final int Point=50;
		Rectangle rec = new Rectangle( 0, 0, 36, 36 ), rec1;
		Image img = TLK.getImage(Item.class.getClassLoader().getResource("Image/myPerson/person/AKA01.png"));
		Image img1 = TLK.getImage(Item.class.getClassLoader().getResource("Image/myPerson/person/AKA01.png"));
		Image img2 = TLK.getImage(Item.class.getClassLoader().getResource("Image/myPerson/person/brilliant.png"));
		int mapx = 36*2, mapy = 36*2; 
		int screenx = 36*2, screeny = 36*3;
		int step = 0, Dir = 0;
		int haha = 0;
		boolean isStart = false;
		boolean isFreeze = false;
		int freezeTime = 0;
		public myPerson(int x,int y,boolean righteous,WARRIOR_DIR dir,Expedition ex)
		{
			super(x,y,righteous,dir,ex);
			this.x = 36*2; this.y = 36*3;
			this.isCBC=false;
			this.WrHemo=new Hemo(Point,this);
			this.type=WTYPE.HERO;
			this.shotcurr=50;
			keyList.add(WARRIOR_DIR.STOP);
			WARRIORSPEED = 4;
			Dir = 2;
			
			//this.isLive = false;
			this.shotcurr=ex.uw.shotcurr;
			this.crump=ex.uw.crump;
			this.tube=ex.uw.tube;
			this.cash=ex.uw.cash;
			this.power=ex.uw.power;
		}
		
		public boolean isHitWall()          //是否碰撞
		{
			return fit( mapx, mapy );
		}
		public boolean fit( int x, int y ){   //判断是否碰撞的函数
			int width = 36;
			int x1 = (int) Math.ceil(1.0*x/width);
			int x2 = (int) Math.floor(1.0*x/width);
			int y1 = (int) Math.ceil(1.0*y/width);
			int y2 = (int) Math.floor(1.0*y/width);
			if( x1 == 0 || x2 == 0 || y1 == 0 || y2 == 0 ) return true;
			if( x1 == Map.numX-1 || x2 == Map.numX-1 || y1 == Map.numY-1 || y2 == Map.numY-1 ) return true;
			rec.y = y; rec.x = x;
			if( !Map.inside( x1, y1) || Map.cell[x1][y1].isWall && Map.cell[x1][y1].rec.intersects(rec) ) return true;
			if( !Map.inside( x1, y2) || Map.cell[x1][y2].isWall && Map.cell[x1][y2].rec.intersects(rec) ) return true;
			if( !Map.inside( x2, y1) || Map.cell[x2][y1].isWall && Map.cell[x2][y1].rec.intersects(rec) ) return true;
			if( !Map.inside( x2, y2) || Map.cell[x2][y2].isWall && Map.cell[x2][y2].rec.intersects(rec) ) return true;
			return false;
		}
		public void move(){                         //行动函数
			if( isFreeze ){
				WARRIORSPEED = 2;
				++freezeTime;
				img = img2;
			}
			if( freezeTime >= 520 ){
				isFreeze = false;
				WARRIORSPEED = 4;
				img = img1;
			}

			if( this.isStart && this.isLive&&moveDir!=WARRIOR_DIR.STOP )    
			{
				currDir=moveDir;
				switch(moveDir)
				{
					case U:
					{
						Dir = 3;
						if( !fit( mapx, mapy - WARRIORSPEED) ){
							if( (++haha) % 2 == 0 )
								++step;
							mapy -= WARRIORSPEED;
							if( y >= 150 ){
								y -= WARRIORSPEED;
							}
							else{
								Map.y -= WARRIORSPEED;
							}
						}
						
						break;
					}
					case D:
					{
						Dir = 0;
						if( !fit( mapx, mapy + WARRIORSPEED) ){
							if( (++haha) % 2 == 0 )
								++step;
							mapy += WARRIORSPEED;
							if( y <= 450 ){
								y += WARRIORSPEED;
							}
							else{
								Map.y += WARRIORSPEED;
							}
						}
						break;
					}
					case L:
					{
						Dir = 1;
						if( !fit( mapx - WARRIORSPEED, mapy) ){
							if( (++haha) % 2 == 0 )
								++step;
							mapx -= WARRIORSPEED;
							if( x >= 150 ){
								x -= WARRIORSPEED;
							}
							else{
								Map.x -= WARRIORSPEED;
							}
						}
						break;
					}
					case R:
					{
						Dir = 2;
						if( !fit( mapx + WARRIORSPEED, mapy) ){
							if( (++haha) % 2 == 0 )
							++step;
							mapx += WARRIORSPEED;
							if( x <= 550 ){
								x += WARRIORSPEED;
							}
							else{
								Map.x += WARRIORSPEED;
							}
						}
						break;
					}
					default:
						break;
				}
				int x1 = mapx / 36, y1 = mapy / 36;
				if( Map.cell[x1][y1].id > 0 ){
					Monster.setTarget( Map.cell[x1][y1].id );
				}
			}
		}
		public void draw( Graphics g ){               //绘画自身的函数
			if( isLive && isStart ){
				int y1 = Dir, x1 = step%4 ;
				g.drawImage( img, x, y, x+36, y+36, x1*32, y1*48, (x1+1)*32, (y1+1)*48, null );
				this.WrHemo.draw(g);
			}
			else{
				if(WrBlow!=null)
					WrBlow.draw(g);
			}
		}
		public void dead()                        //死亡之后的处理
		{
			if( isStart && isLive ){
				WrBlow=new WrBlow(x,y,this);
				Bomb.play();
			}
			isLive=false;
		}
		public Rectangle getRect(){
			rec.setBounds( x, y, 36, 36 );
			return rec;
		}
		public void keyDispose(int Key,boolean b)                   //处理键盘控制
		{
			if(b)  
			{
				switch(Key)
				{
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:
					{
						keyList.remove(WARRIOR_DIR.U);
						keyList.add(WARRIOR_DIR.U);
					}break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
					{
						keyList.remove(WARRIOR_DIR.D);
						keyList.add(WARRIOR_DIR.D);
					}break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:
					{
						keyList.remove(WARRIOR_DIR.L);
						keyList.add(WARRIOR_DIR.L);
					}break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
					{
						keyList.remove(WARRIOR_DIR.R);
						keyList.add(WARRIOR_DIR.R);
					}break;
					case KeyEvent.VK_SPACE:
					{
						this.fire();
					}break;
					case KeyEvent.VK_Z:{
						this.getBomb();
					} break;
					case KeyEvent.VK_C:{
						this.cross();
					}
					default:break;
				}
			}
			else  
			{
				switch(Key)
				{
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:{ keyList.remove(WARRIOR_DIR.U); }break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:{ keyList.remove(WARRIOR_DIR.D); }break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:{ keyList.remove(WARRIOR_DIR.L); }break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:{ keyList.remove(WARRIOR_DIR.R); }break;
					default:break;
				}
			}
			if(keyList.size()==0) { keyList.add(WARRIOR_DIR.STOP); }
			super.setMoveDir(keyList.get(keyList.size()-1));
		}
		@Override
		void hitDispose() {
			// TODO Auto-generated method stub
			
		}
		public void fire()                         //开枪的函数
		{
			if(shotCnt<SHOTSIMU)
			{
				if(this.type==WTYPE.HERO)
				{
					if(this.shotcurr>0)
					{
						audio.play();
						shotCnt++;
						this.shotcurr--;
						ex.bullet.add(new Bullet(this));
					}
				}
			}
		}
		public void getBomb(){                          //放炸弹
			if( this.crump > 0 ){
				--this.crump;
				int xx =0, yy = 0;
				if( this.isLive ){
					switch( Dir ){
					case 0: xx = x; yy = y + 36; break;
					case 1: xx = x - 36; yy = y; break;
					case 2: xx = x + 36; yy = y; break;
					case 3: xx = x; yy = y - 36; break;
					}
				}
				ex.bomb.add( new Bomb( xx, yy ) );
			}
		}
		public void cross(){                         //穿墙功能
			int xx =0, yy = 0;
			if( this.isLive ){
				switch( Dir ){
				case 0: xx = mapx; yy = mapy + 50; break;
				case 1: xx = mapx - 50; yy = mapy; break;
				case 2: xx = mapx + 50; yy = mapy; break;
				case 3: xx = mapx; yy = mapy - 50; break;
				}
			}
			if( this.tube > 0 && !fit( xx, yy ) ){
				--this.tube;
				x += xx-mapx; y += yy - mapy;
				mapx = xx; mapy = yy;
			}
		}
	}
	
	//用户类
	class UserWarrior extends Warrior
	{
		public static final int Point=50;
		
		LinkedList<WARRIOR_DIR> keyList=new LinkedList<WARRIOR_DIR>(); 
		
		public UserWarrior(int x,int y,boolean righteous,WARRIOR_DIR dir,Expedition ex)
		{
			super(x,y,righteous,dir,ex);
			this.isCBC=false;
			this.WrHemo=new Hemo(Point,this);
			this.type=WTYPE.HERO;
			this.shotcurr=50;
			this.crump=5;
			this.tube=3;
			this.cash=1000;
			this.power=2;
			keyList.add(WARRIOR_DIR.STOP);
		}
		
		public void draw(Graphics g)
		{
			super.draw(g);
		}
		
		public void fire()
		{
			super.fire();
		}
		
		//处理键盘响应
		public void keyDispose(int Key,boolean b)  
		{
			if(b)  
			{
				switch(Key)
				{
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:
					{
						keyList.remove(WARRIOR_DIR.U);
						keyList.add(WARRIOR_DIR.U);
					}break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
					{
						keyList.remove(WARRIOR_DIR.D);
						keyList.add(WARRIOR_DIR.D);
					}break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:
					{
						keyList.remove(WARRIOR_DIR.L);
						keyList.add(WARRIOR_DIR.L);
					}break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
					{
						keyList.remove(WARRIOR_DIR.R);
						keyList.add(WARRIOR_DIR.R);
					}break;
					case KeyEvent.VK_SPACE:
					{
						this.fire();
					}break;
					default:break;
				}
			}
			else  
			{
				switch(Key)
				{
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:{ keyList.remove(WARRIOR_DIR.U); }break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:{ keyList.remove(WARRIOR_DIR.D); }break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:{ keyList.remove(WARRIOR_DIR.L); }break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:{ keyList.remove(WARRIOR_DIR.R); }break;
					default:break;
				}
			}
			if(keyList.size()==0) { keyList.add(WARRIOR_DIR.STOP); }
			super.setMoveDir(keyList.get(keyList.size()-1));
		}
		
		//处理碰撞
		public void hitDispose()
		{
			Warrior wrtemp;
			
			for(int j=0;j<ex.WarriorsList.size();j++)
			{
				wrtemp=ex.WarriorsList.get(j);
				if(wrtemp==this||!wrtemp.isLive)
				{
					continue;
				}
				
				if(this.getRect().intersects(wrtemp.getRect()))
				{
					
					switch(moveDir)
					{
						case U:
						case D:{y=oldy;}break;
						case L:
						case R:{x=oldx;}break;
						default:break;
					}
					this.setMoveDir(WARRIOR_DIR.STOP);
					
					if(righteous!=wrtemp.righteous)
					{
						wrtemp.WrHemo.reduceWithHit(1);
						this.WrHemo.reduceWithHit(1);
					}
				}
			}
		}
		
		//勇士复活
		public void resurgence()
		{
			this.isLive=true;
			this.WrHemo.raiseBlood(50);
			this.keyList.clear();
			this.setMoveDir(WARRIOR_DIR.STOP);
		}
	}




