package jolie;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import jolie.Warrior.WTYPE;
//子弹类
public abstract class Shot {
	int shotSpeed;
	int shotRadius;
	int shotPower;
	int bx,by;
	int shotTotal=30;
	Rectangle rec = new Rectangle( 0, 0, 36, 36 );
	boolean isLive;
	boolean isBlow;
	static Expedition ex=null;
	Warrior wr=null;
	ShotBlow shb=null;
	jolie.Warrior.WARRIOR_DIR dir;
	public static final Toolkit TLK=Toolkit.getDefaultToolkit();
	
	
	public Shot(Warrior wr)
	{
		this.bx=wr.x;
		this.by=wr.y;
		this.ex=wr.ex;
		this.dir=wr.currDir;
		this.wr=wr;
		this.isLive=true;
	}
	
	abstract void draw(Graphics g);
	abstract void move();
	abstract void dead();
	
	//判断子弹是否出界
	public boolean isOutOfWindow()
	{
		if(bx>ex.WIDTH-15||bx<0||by>ex.HEIGHT-15||by<0)
		{
			this.isBlow=false;
			return true;
		}
		else
			return false;
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(bx,by+15+2*shotRadius,(int)(shotRadius),(int)(shotRadius));
	}
}

//普通子弹类
class NormalShot extends Shot
{
	static final Image[] NORSHOT=
	{
		TLK.getImage(Shot.class.getClassLoader().getResource("Image/Shot/Nor1.png")),
		TLK.getImage(Shot.class.getClassLoader().getResource("Image/Shot/Nor2.png")),
		TLK.getImage(Shot.class.getClassLoader().getResource("Image/Shot/Nor3.png")),
	};
	
	public NormalShot(Warrior wr)
	{
		super(wr);
		if(this.wr.type==WTYPE.GHOST)
		this.shotPower=1;
		else if(this.wr.type==WTYPE.HERO)
		this.shotPower=ex.uw.power;
		else
		this.shotPower=2;
		
		this.shotRadius=6;
		this.shotSpeed=10;
	}
	
	//画出子弹
	public void draw(Graphics g)
	{
		if(isLive)
		{
			if(wr.righteous)
			g.drawImage(NORSHOT[0],bx,by+30,null);
			else if(wr.type==WTYPE.SOLDIER)
			g.drawImage(NORSHOT[1],bx,by+30,null);
			else
			g.drawImage(NORSHOT[2],bx,by+30,null);
		}
		else
		{
			if(isBlow&&shb!=null)
			{
				shb.draw(g);
			}
		}
	}
	
	//处理子弹死后
	public void dead()
	{
		if(isLive)
		{
			this.isLive=false;
			wr.shotCnt--;
			
			if(isBlow)
				shb=new ShotBlow(bx-12,by-12,this);
			else
				ex.ShotsList.remove(this);
		}
	}
	
	//处理子弹移动
	public void move()
	{
		if(isLive)
		{
			switch(dir)
			{
			case U:
			{
				by-=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			case D:
			{
				by+=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			case L:
			{
				bx-=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			case R:
			{
				bx+=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			
			default:break;
			}
		}
	}
	
	//判断子弹碰墙
	public boolean isHitWall()
	{
		Wall w=null;
		
		for(int i=0;i<ex.WallList.size();i++)
		{
			w=ex.WallList.get(i);
			if(w.isHit(this))
			{
				this.isBlow=true;
				return true;
			}
		}
		return false;
	}
	
	//判断是否撞到勇士
	public boolean isHitWarrior()
	{
		Warrior tempwr=null;
		for(int i=0;i<ex.WarriorsList.size();i++)
		{
			tempwr=ex.WarriorsList.get(i);
		
		if(tempwr.isLive&&getRect().intersects(tempwr.getRect())&&tempwr.righteous!=this.wr.righteous)
		{
			tempwr.WrHemo.reduceWithShot(shotPower);
			this.isBlow=true;
			return true;
		}
		}
		return false;
	}
}

class Bullet extends Shot{                      //子弹类

	static Rectangle rec1 = new Rectangle( 0, 0, 26, 36 );
	static Image image[] = new Image[4];
	int mx, my;
	int Dir;
	public Bullet(Warrior wr) {
		super(wr);
		if(this.wr.type==WTYPE.GHOST)
		this.shotPower=1;
		else
		this.shotPower=5;
		this.shotRadius=6;
		this.shotSpeed=10;
		for( int i = 0; i < 4; ++i ){
			String str = "Image/myPerson/bullet/" + i + ".png";
			image[i] = TLK.getImage(Shot.class.getClassLoader().getResource( str ) );
		}
	
		Dir = ex.person.Dir;
		mx = ex.person.mapx; my = ex.person.mapy;
	}

	@Override
	void dead() {                   //子弹碰撞之后的处理
		// TODO Auto-generated method stub
		if(isLive)
		{
			this.isLive=false;
			wr.shotCnt--;
			
			if(isBlow)
				shb=new ShotBlow(bx-12,by-12,this);
			else
				ex.bullet.remove(this);
		}
	}

	@Override
	void draw(Graphics g) {              //draw
		// TODO Auto-generated method stub
		if(isLive)
		{
			g.drawImage( image[Dir], bx, by, 36, 36, null );
		}
		else
		{
			if(isBlow&&shb!=null)
			{
				shb.draw(g);
			}
		}
	}

	@Override
	void move() {       //子弹的移动
		// TODO Auto-generated method stub
		if(isLive)
		{
			switch(dir)
			{
			case U:
			{
				by-=shotSpeed;
				my-=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			case D:
			{
				by+=shotSpeed;
				my+=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			case L:
			{
				bx-=shotSpeed;
				mx-=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			case R:
			{
				bx+=shotSpeed;
				mx+=shotSpeed;
				if(isOutOfWindow()||isHitWall()||isHitWarrior())
				{
					this.dead();
				}
				break;
			}
			
			default:break;
			}
		}
	}

	private boolean isHitWarrior() {            //子弹碰撞怪物
		// TODO Auto-generated method stub
		rec.x = bx; rec.y = by;
		Monster zom;
		for( int i = 0; i < ex.zombie.size(); ++i ){
			zom = ex.zombie.get(i);
			if( rec.intersects( zom.getRect() ) ){
				int a = --zom.blood;
				if( a <= 0 ){
					ex.gift.add( new Gift( ex.zombie.get(i).screenx, ex.zombie.get(i).screeny ) );
					zom.isAlive = false;
					ex.zombie.remove( zom );
					--i;
				}
				return true;
			}
		}
		return false;
	}

	private boolean isHitWall() {             //子弹撞墙
		// TODO Auto-generated method stub
		if( ex.person.fit( mx, my ) ) return true;
		return false;
	}
	
}


