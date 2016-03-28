package jolie;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import jolie.Warrior.WTYPE;

//主类
public class Expedition extends Frame{
	
	final static int UP = 3, DOWN = 0, LEFT = 1, RIGHT = 2;
	static final int width = 36;
	long initTime, currTime;
	ArrayList<Monster> zombie = new ArrayList<Monster>();
	ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	ArrayList<Bomb> bomb = new ArrayList<Bomb>();
	ArrayList<Gift> gift = new ArrayList<Gift>();
	ArrayList<Fire> fire = new ArrayList<Fire>();
	Monster zom;
	int numOfZom = 35;
	int numOfFire = 20;
	Random rand = new Random();
	Map map;
	Image cover = TLK.getImage(Blow.class.getClassLoader().getResource("Image/myPerson/cover.png") );
	Image theDoor = TLK.getImage(Blow.class.getClassLoader().getResource("Image/myPerson/2007826154714999.gif") );
	static final Toolkit TLK=Toolkit.getDefaultToolkit();
	static Point mousePoint=new Point(600,500);
	static Font ft=new Font("verdana",Font.PLAIN,35);
	static AudioClip jbt=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/button.wav"));
	static AudioClip ter=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/for the love of a princess.wav"));
	static AudioClip sta=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/The Ludlows.wav"));
	static AudioClip audio=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/puyo.wav"));
	static AudioClip success=Applet.newAudioClip(Expedition.class.getClassLoader().getResource("Sound/success.wav"));
	static int sequency=0;
	
	boolean isStart1=false;
	boolean isStart2=false;
	boolean iswr=false;
	boolean isCrystalstart=true;
	boolean isCrystalhelp=true;
	boolean isCrystaloption=true;
	boolean isCrystalattack=true;
	boolean isCrystalbomb=true;
	boolean isCrystalbullet=true;
	boolean isCrystalmoney=true;
	boolean isCrystalwall=true;
	boolean isCrystalquit=true;
	boolean isCrystalcontinue=true;
	boolean isCrystalback=true;
	
	private static final Image[] img=
	{
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/bomb.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/bombZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/bullet.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/bulletZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/wall.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/wallZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/money.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/assault.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/assaultZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/outlook.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/Start.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/StartZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/help.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/helpZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/option.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/optionZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/midground.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/gameover.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/tryagain.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/tryagainZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/quit.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/quitZ.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/success.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/helplook.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/back.png")),
		TLK.getImage(Blow.class.getClassLoader().getResource("Image/Item/backZ.png")),
	};
	
	
	
	public static final int WIDTH=1000;
	public static final int HEIGHT=650;
	public static int[][] path=new int[700][700];
	public static point[] pos=new point[700];
	
	public static int [][] id=new int[700][700];
	static int [] pre=new int[700];
	
	static int [][] dir={{1,0},{0,1},{0,-1},{-1,0}}; 
	static boolean[][] wall=new boolean[32][32];
	
	int totalcnt;
	
	boolean isRepaint=true;
	Image bkImage=null;
	
	UserWarrior uw=new UserWarrior(10,120,true,Warrior.WARRIOR_DIR.R,this);
	//myPerson person = new myPerson( 0, 0, true, Warrior.WARRIOR_DIR.R,this );
	myPerson person;
	List<Warrior> WarriorsList=Collections.synchronizedList(new ArrayList<Warrior>());
	List<Shot> ShotsList=Collections.synchronizedList(new ArrayList<Shot>());
	List<Wall> WallList=Collections.synchronizedList(new ArrayList<Wall>());
	List<Item> ItemList=Collections.synchronizedList(new ArrayList<Item>());
	List<Route> RouteList=Collections.synchronizedList(new ArrayList<Route>());
	
	Color colorbk=new Color(54,54,54);
	
	Expedition(){
		//myFrame();
		this.setLocation(200,50);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Tomb Raider");
		this.setResizable(false);
		this.setBackground(colorbk);
		
		this.addWindowListener(new FrameClose());
		this.addKeyListener(new WarriorMoveLis());
		this.addMouseMotionListener(new MouseMotionLis());
		this.addMouseListener(new MouseCheckLis());
		new Thread(new RepaintThread(this)).start();
		new Thread(new PcWarriorThread(this)).start();
		
		this.setVisible(true);
		//myFrame();
	
	}
	
	//响应窗口关闭
	public void windowClosing(WindowEvent e)
	 {
	  System.exit(0);
	 }
	
	//第二关初始化
	public void myFrame(){
		//Map.init();
		map = new Map();
		person = new myPerson( 0, 0, true, Warrior.WARRIOR_DIR.R,this );
		myPerson.zombie = zombie;
		initTime = System.currentTimeMillis();
		Monster.person = person;
		person.isStart = true;
		zombie.clear();
		Gift.person = person;
		Gift.ex = yc;
		for( int i = 0; i < numOfZom; ++i ){
			Monster mon = new Monster();
			zombie.add( mon );
			new Thread( mon ).start();
		}
		Monster.person = person;
		bomb.add( new Bomb( 10000, 10000) );
		fire.clear();
		for( int i = 0; i < numOfFire; ++i ){
			int index = rand.nextInt( Map.cnt - 8 ) + 3;
			int x = Map.queOfNoWall.get(index).x;
			int y = Map.queOfNoWall.get(index).y;
			fire.add( new Fire( (x+1)*width, (y+1)*width ) );
		}
	}
	static Expedition yc;
	public static void main(String[] args) throws MalformedURLException{
		yc=new Expedition();
		yc.setSize(1000,650);
		yc.setVisible(true);
		
		//audio.loop();
		sta.loop();
		
		yc.init();
	}
	
	//第一关初始化
	void launchFrame()
	{
		Warrior wr=null;
		for(int i=0;i<WarriorsList.size();i++)
		{
			wr=WarriorsList.get(i);
			if(wr.type!=WTYPE.HERO)
				WarriorsList.remove(wr);
		}
		
		this.WallList.clear();
		this.WallList.add(new Wall(0,100,Wall.WALLTYPE.MAZE,this));
		this.RouteList.clear();
		this.RouteList.add(new Route(-1,100,this));
		this.ItemList.clear();
		this.ItemList.add(new RaiseBlood(60,250,this));
		this.ItemList.add(new shotItem(70,360,this));
		this.ItemList.add(new shotItem(35,360,this));
		this.ItemList.add(new Radish(380,450,this));
		this.ItemList.add(new Antidote(380,170,this));
		
		uw.cash=1000;
		uw.crump=5;
		uw.tube=3;
		uw.shotcurr=50;
		
		if(!iswr)
		{
			WarriorsList.add(uw);
			iswr=true;
		}
		PcWarrior.add(1,Warrior.WTYPE.GHOST,this);
		PcWarrior.add(1,Warrior.WTYPE.SOLDIER,this);
	}

//窗口关闭响应
class FrameClose extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		Frame f=(Frame)e.getWindow();
		isRepaint=false;
		sta.stop();
		audio.stop();
		ter.stop();
		success.stop();
		f.dispose();
		System.exit(0);
	}
}

//重画函数
public void paint(Graphics g)
{
	if(sequency!=3)
	Paint1( g );
	else 
	Paint2( g );
}
//根据Thread重画
public void Paint1( Graphics g ){
	if(sequency==0)
	{
		g.drawImage(img[9],0,0,null);
		if(isCrystalstart)
			g.drawImage(img[11],50,350,null);
		else
			g.drawImage(img[10],50,350,null);
	
		if(isCrystalhelp)
			g.drawImage(img[13],50,500,null);
		else
			g.drawImage(img[12],50,500,null);
	
		//if(isCrystaloption)
			//g.drawImage(img[15],50,375,null);
		//else
			//g.drawImage(img[14],50,375,null);
	}
	else if(sequency==1)
	{
	if(!isStart1)
	{
		yc.launchFrame();
		isStart1=true;
	}
	
	g.drawImage(img[16],0,0,null);
	if(isCrystalattack)
	g.drawImage(img[8],50,40,null);
	else
	g.drawImage(img[7],50,40,null);
	
	//if(isCrystalbomb)
	g.drawImage(img[1],200,40,null);
	//else
	//g.drawImage(img[0],200,40,null);
	
	if(isCrystalbullet)
	g.drawImage(img[3],400,40,null);
	else
	g.drawImage(img[2],400,40,null);
	
	//if(isCrystalwall)
	g.drawImage(img[5],600,40,null);
	//else
	//g.drawImage(img[4],600,40,null);
	
	g.drawImage(img[6],800,40,null);
	
	Route route=null;
	for(int i=0;i<RouteList.size();i++)
	{
		route=RouteList.get(i);
		route.draw(g);
	}
	
	Wall wrWall=null;
	for(int i=0;i<WallList.size();i++)
	{
		wrWall=WallList.get(i);
		wrWall.draw(g);
	}
	
	Item ie=null;
	for(int i=0;i<ItemList.size();i++)
	{
		ie=ItemList.get(i);
		ie.draw(g);
	}
	
	Warrior wr=null;
	for(int i=0;i<WarriorsList.size();i++)
	{
		wr=WarriorsList.get(i);
		wr.draw(g);
	}
	
	Shot wrShots=null;
	for(int i=0;i<ShotsList.size();i++)
	{
		wrShots=ShotsList.get(i);
		wrShots.draw(g);
	}
	
	g.setColor(Color.MAGENTA);
	g.setFont(ft);
	g.drawString(uw.power+"",140,75);
	g.drawString(uw.crump+"",300,75);
	g.drawString(uw.shotcurr+"",500,75);
	g.drawString(uw.tube+"",700,75);
	g.drawString(uw.cash+"",900,75);
	}
	else if(sequency==2)
	{
		g.drawImage(img[17],0,0,null);
		if(isCrystalcontinue)
			g.drawImage(img[19],200,200,null);
		else
			g.drawImage(img[18],200,200,null);
		
		if(isCrystalquit)
			g.drawImage(img[21],200,400,null);
		else
			g.drawImage(img[20],200,400,null);
	}
	else if(sequency==4)
		g.drawImage(img[22],0,0,null);
	else if(sequency==5)
	{
		g.drawImage(img[23],0,0,null);
		if(isCrystalback)
			g.drawImage(img[25],700,40,null);
		else
			g.drawImage(img[24],700,40,null);
	}
}
public void Paint2( Graphics g ){             //第二关的绘制函数
	//this.setBackground( Color.blue );
	if(!isStart2)
	{
		myFrame();
		isStart2=true;
	}
	g.drawImage( img[16], 0, 0, 1000, 650, 100+Map.x, 0+Map.y, 1100+Map.x, 600+Map.y, this );  //背景图片
	//g.drawImage(img[16],0,0,null);
	for( int i = 0; i < zombie.size(); ++i ) zombie.get(i).draw( g );
	for( int i = 0; i < bullet.size(); ++i ) bullet.get(i).draw( g );
	for( int i = 0; i < bomb.size(); ++i ) bomb.get(i).draw( g );
	for( int i = 0; i < gift.size(); ++i ) gift.get(i).draw( g );
	for( int i = 0; i < fire.size(); ++i ) fire.get(i).draw( g );

	person.draw(g);
	paintWall( g );
	g.drawImage(img[6],800,40,null);;
	if(isCrystalattack)
	g.drawImage(img[8],50,40,null);
	else
	g.drawImage(img[7],50,40,null);
	
	if(isCrystalbomb)
	g.drawImage(img[1],200,40,null);
	else
	g.drawImage(img[0],200,40,null);
	
	if(isCrystalbullet)
	g.drawImage(img[3],400,40,null);
	else
	g.drawImage(img[2],400,40,null);
	
	if(isCrystalwall)
	g.drawImage(img[5],600,40,null);
	else
	g.drawImage(img[4],600,40,null);
	
	
	g.setColor(Color.MAGENTA);
	g.setFont(ft);
	g.drawString(uw.power+"",140,75);
	g.drawString(person.crump+"",300,75);
	g.drawString(uw.shotcurr+"",500,75);
	g.drawString(person.tube+"",700,75);
	g.drawString(uw.cash+"",900,75);
	
	String str;
	currTime = System.currentTimeMillis();
	g.drawString(100-(currTime - initTime)/1000+"",500,375);
	g.drawImage( theDoor, Map.doorx-Map.x, Map.doory-Map.y, 36, 36, this );
}
void paintWall( Graphics g ){            //地图的绘制函数
	Image img = Map.image;
	int x, y, vec = width/3;
	for( int i = 1; i < Map.numX-1; ++i )
		for( int j = 1; j < Map.numY-1; ++j )if( Map.cell[i][j].isWall){
			g.drawImage( img, (i+1)*width-Map.x+vec, (j+1)*width-Map.y+vec, vec, vec, this );
			for( int k = 0; k < 4; ++k ){
				x = i+dir[k][0]; y = j+dir[k][1];
				if( Map.cell[x][y].isWall ){
					g.drawImage( img, (i+1)*width-Map.x+vec+vec*dir[k][0], (j+1)*width-Map.y+vec+vec*dir[k][1], vec, vec, this );
				}
			}
			
		}
}

//双缓冲技术解决屏幕闪烁
public void update(Graphics g)
{
	if(bkImage==null)
	{
		bkImage=this.createImage(WIDTH,HEIGHT);
	}
	
	g.drawImage(bkImage,0,0,null);
															
	Graphics gBkImg=bkImage.getGraphics();
	gBkImg.clearRect(0, 0, WIDTH , HEIGHT);
	this.paint(gBkImg);
}

//第一关初始化
public void init()
{
	totalcnt=0;
	
	for(int i=0;i<=1000;i++)
		for(int j=0;j<=650;j++)
		{
			int cx=i/32;
			if(i%32>16)
				cx++;
			int cy=j/32;
			if(j%32>16)
				cy++;
			
			if(id[cx][cy]>0)
				continue;
			
			pos[++totalcnt]=new point(cx,cy);
			id[cx][cy]=totalcnt;
		}
	
	//System.out.println(totalcnt);
	
	for(int j=1;j<=totalcnt;j++)
		pre[j]=-1;
	
	for(int i=1;i<=totalcnt;i++)
	{
		for(int j=1;j<=totalcnt;j++)
			pre[j]=-1;
		bfs(pos[i]);
	}
}

//广搜路径
public void bfs(point sta)
{
	boolean[][] visit=new boolean[32][32];
	
	point temp=null;
	
	int sx=sta.x;
	int sy=sta.y;
	
	Queue<point>q=new LinkedList<point>();
	
	q.offer(sta);
	
	while(!q.isEmpty())
	{
		temp=q.poll();
		visit[temp.x][temp.y]=true;
		
		int currx=temp.x;
		int curry=temp.y;
		
		for(int i=0;i<4;i++)
		{
			int tx=currx+dir[i][0];
			int ty=curry+dir[i][1];
			if(tx>=0&&tx<=31&&ty>=0&&ty<=20&&!visit[tx][ty]&&id[tx][ty]>0)
			{
				point nex=new point(tx,ty);
				visit[tx][ty]=true;
				q.offer(nex);
				
				if(pre[id[tx][ty]]==-1)
					pre[id[tx][ty]]=i;
			}
		}
	}
	
	for(int i=1;i<=totalcnt;i++)
	{
		if(i==id[sx][sy])
			continue;
		if(pre[i]==-1)
			continue;
		path[i][id[sx][sy]]=3-pre[i];
	}
}

//重画线程
class RepaintThread implements Runnable
{
	Expedition ex;
	
	RepaintThread(Expedition ex){
		this.ex=ex;
	}
	
	public void run()
	{
		while(isRepaint)
		{
			try
			{
				repaint();
				
				int x=mousePoint.x;
				int y=mousePoint.y;
				
				if(sequency==0)
				{
				if(x>=50&&x<=320&&y>=350&&y<=430)
					isCrystalstart=false;
				else
					isCrystalstart=true;
				
				if(x>=50&&x<=320&&y>=500&&y<=600)
					isCrystalhelp=false;
				else
					isCrystalhelp=true;
				
				//if(x>=50&&x<=340&&y>=375&&y<=450)
					//isCrystaloption=false;
				//else
					//isCrystaloption=true;
				}
				else if(sequency==1)
				{
				if(!isStart1)
				{
					yc.launchFrame();
					isStart1=true;
				}
				
				if(uw.x>=950&&uw.y>=500)
				{
					sequency=3;
					//System.out.print(uw.x+""+uw.y);
				}
				if(x>=50&&x<=100&&y>=40&&y<=90)
					isCrystalattack=false;
				else
					isCrystalattack=true;
				
				if(x>=200&&x<=250&&y>=40&&y<=90)
					isCrystalbomb=false;
				else
					isCrystalbomb=true;
				
				if(x>=400&&x<=450&&y>=40&&y<=90)
					isCrystalbullet=false;
				else
					isCrystalbullet=true;
				
				if(x>=600&&x<=650&&y>=40&&y<=90)
					isCrystalwall=false;
				else
					isCrystalwall=true;
				
				if(uw.isLive)
				uw.move();
				
				Shot wrShots=null;
				for(int i=0;i<ShotsList.size();i++)
				{
					wrShots=ShotsList.get(i);
					wrShots.move();
				}
				}
				else if(sequency==2)
				{
					isStart1=false;
					isStart2=false;
					if(x>=200&&x<=700&&y>=200&&y<=285)
						isCrystalcontinue=false;
					else
						isCrystalcontinue=true;
					
					if(x>=200&&x<=440&&y>=400&&y<=490)
						isCrystalquit=false;
					else
						isCrystalquit=true;
					
					
					System.out.println(x+""+y);
				}
				else if(sequency==3)
				{
					if(x>=50&&x<=100&&y>=40&&y<=90)
						isCrystalattack=false;
					else
						isCrystalattack=true;
					
					if(x>=200&&x<=250&&y>=40&&y<=90)
						isCrystalbomb=false;
					else
						isCrystalbomb=true;
					
					if(x>=400&&x<=450&&y>=40&&y<=90)
						isCrystalbullet=false;
					else
						isCrystalbullet=true;
					
					if(x>=600&&x<=650&&y>=40&&y<=90)
						isCrystalwall=false;
					else
						isCrystalwall=true;
					if( person != null && person.getRect().intersects( Map.getTheDoor()) ){
						person.isStart = false;
						audio.stop();
						success.loop();
						sequency=4;
					}
				}
				else if(sequency==5)
				{
					if(x>=700&&x<=1000&&y>=40&&y<=140)
					{
						isCrystalback=false;
						System.out.println(1);
					}
					else
						isCrystalback=true;
				}
				Thread.sleep(18);
				
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
			myRun();
		}
	}
}

public void myRun(){             //第二关的线程

	if(person != null && person.isLive)
		person.move();
	else return;
	//else
		//sequency=2;
	currTime = System.currentTimeMillis();
	if( 100000 < currTime - initTime ){
		person.WrHemo.reduceWithHit( 50 );
	}
	for( int i = 0; i < gift.size(); ++i ){
		gift.get(i).move();
		if( !gift.get(i).isAlive ){
			gift.remove(i);
			--i;
			continue;
		}
		if( gift.get(i).getRect().intersects( person.getRect() ) ){
			gift.get(i).isGet = true;
		}
	}
	for( int i = 0; i < bullet.size(); ++i ) bullet.get(i).move();
	for( int i = 0; i < fire.size(); ++i ){
		fire.get(i).move();
		if( fire.get(i).isAlive && fire.get(i).getRect().intersects( person.getRect() ) ){
			person.WrHemo.reduceWithHit( 1 );
			person.isFreeze = true;
			person.freezeTime = 0;
		}
	}
	Rectangle rec;

	for( int i = 0; i < bomb.size(); ++i ){
		bomb.get(i).move();
		rec = bomb.get(i).getRect();
		for( int j = 0; j < zombie.size(); ++j ){
			zom = zombie.get(j);
			if( bomb.get(i).isBlow && zom.getRect().intersects(rec) ){
				gift.add( new Gift( zom.screenx, zom.screeny ) );
				zom.isAlive = false;
				zombie.remove(j);
				--j;
			}
		}
		if( !bomb.get(i).isAlive ){
			bomb.remove(i);
			--i;
		}
	}
	
}

//机器人线程
class PcWarriorThread implements Runnable
{
	Expedition ex;
	
	public PcWarriorThread(Expedition ex)
	{
		this.ex=ex;
	}
	
	public void run()
	{
		Warrior wr=null;
		int cnt=-1;
		
		while(isRepaint)
		{
			/*if(cnt==0)
			{
				PcWarrior.add(1,Warrior.WTYPE.GHOST,ex);
			}*/
			
			try
			{
				cnt=0;
				
				for(int i=0;i<WarriorsList.size();i++)
				{
					wr=WarriorsList.get(i);
					if(wr.isCBC&&wr.isLive)
						cnt++;
					else
						continue;
					
					if(wr.type==WTYPE.SOLDIER)
					((PcWarrior)wr).autoAction();
					//else
					//((Specter)wr).autoAction();*/
					
					int fx=uw.x/32;
					//if(uw.x%32>16)
						//fx++;
					int fy=uw.y/32;
					//if(uw.y%32>16)
						//fy++;
					int sx=wr.x/32;
					//if(sx%32>16)
						//sx++;
					int sy=wr.y/32;
					//if(sy%32>16)
						//sy++;
					
					point a=new point(sx,sy);
					point b=new point(fx,fy);
					//((PcWarrior)wr).chase(a,b);
					if(wr.type==WTYPE.GHOST)
					{
						if((fx-sx)*(fx-sx)+(fy-sy)*(fy-sy)<50&&uw.isLive)
						{
							((Specter)wr).chase(a,b);
							if(fx==sx||fy==sy)
								((Specter)wr).fire();
						}
						else
							((Specter)wr).autoAction();
					}
						
				}
				
				Thread.sleep(40);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}

//键盘响应
class WarriorMoveLis extends KeyAdapter
{
	public void keyPressed(KeyEvent e)
	{
		if(uw.isLive)
		{
			uw.keyDispose(e.getKeyCode(), true);
		}
		if(person != null && person.isLive)
		{
			person.keyDispose(e.getKeyCode(), true);
		}
		
	}
		
	public void keyReleased(KeyEvent e)
	{
		if(uw.isLive)
		{
			uw.keyDispose(e.getKeyCode(), false);
		}
		if(person != null && person.isLive)
		{
			person.keyDispose(e.getKeyCode(), false);
		}
	}
}

//鼠标移动响应
class MouseMotionLis extends MouseMotionAdapter
{
	public void mouseMoved(MouseEvent e)
	{
		if(uw.isLive||sequency==2)
		mousePoint=e.getPoint();
	}
}

//鼠标单击响应
class MouseCheckLis extends MouseAdapter
{
	public void mouseClicked(MouseEvent e)
	{
		if(uw.isLive||sequency==2)
		{
			int x=mousePoint.x;
			int y=mousePoint.y;
			
			if(sequency==0)
			{
				if(x>=50&&x<=320&&y>=350&&y<=430)
				{
					jbt.play();
					sta.stop();
					
					audio.loop();
					sequency=1;
				}
				//else if(x>=50&&x<=340&&y>=375&&y<=450)
				//{
					//jbt.play();
					
				//}
				else if(x>=50&&x<=320&&y>=500&&y<=600)
				{
					jbt.play();
					sequency=5;
				}
			}
			else if(sequency==1)
			{
				if(x>=50&&x<=100&&y>=40&&y<=90&&uw.cash>=500)
				{
					jbt.play();
					uw.cash-=500;
					uw.power+=2;
				}
				else if(x>=400&&x<=450&&y>=40&&y<=90&&uw.cash>=200)
				{
					jbt.play();
					uw.cash-=200;
					uw.shotcurr+=50;
				}
			}
			else if(sequency==3)
			{
			if(x>=50&&x<=100&&y>=40&&y<=90&&uw.cash>=500)
			{
				jbt.play();
				uw.cash-=500;
				uw.power+=2;
			}
			else if(x>=200&&x<=250&&y>=40&&y<=90&&uw.cash>=200)
			{
				jbt.play();
				uw.cash-=200;
				person.crump++;
			}
			else if(x>=400&&x<=450&&y>=40&&y<=90&&uw.cash>=200)
			{
				jbt.play();
				uw.cash-=200;
				uw.shotcurr+=50;
			}
			else if(x>=600&&x<=650&&y>=40&&y<=90&&uw.cash>=200)
			{
				jbt.play();
				uw.cash-=200;
				person.tube++;
			}
			}
			else if(sequency==2)
			{
				if(x>=200&&x<=700&&y>=200&&y<=285)
				{
					jbt.play();
					ter.stop();
					audio.loop();
					
					uw.resurgence();
					uw.x=10;
					uw.y=120;
					uw.currDir=Warrior.WARRIOR_DIR.R;
					sequency=1;
					System.out.println(sequency);
				}
				else if(x>=200&&x<=440&&y>=400&&y<=490)
				{
					jbt.play();
					ter.stop();
					yc.dispose();
					System.exit(0);
				}
			}
			else if(sequency==5)
			{
				if(x>=700&&x<=1000&&y>=40&&y<=140)
				{
					jbt.play();
					sequency=0;
				}
			}
		}
	}
}

//屏幕坐标点类
class point{
	int x;
	int y;
	
	point()
	{
		
	}
	
	point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}

}

