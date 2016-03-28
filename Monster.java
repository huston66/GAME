package jolie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Monster implements Runnable{              //利用线程控制的怪物，会追踪player的位置
	public static Toolkit TLK=Toolkit.getDefaultToolkit();
	final static int dir[][] = { {0, -1}, {1, 0}, {-1, 0}, {0, 1} };
	static Image image = TLK.getImage(Item.class.getClassLoader().getResource("Image/myPerson/monster.png") );
	static Random rand = new Random();
	static Rectangle rec = new Rectangle( 0, 0, 36, 36 );
	int mapx, mapy;
	int screenx, screeny;
	int speed = 1;
	int step = 0;
	int Dir = 0;
	int blood = 2;
	static myPerson person;
	boolean isAlive = true;
	static int target = 4;
	Monster(){                       //初始化出现的位置
		int index = rand.nextInt( Map.cnt );
		mapx = Map.queOfNoWall.get(index).x;
		mapy = Map.queOfNoWall.get(index).y;
		screenx = (mapx+1)*Map.width;
		screeny = (mapy+1)*Map.length;
	}
	static void setTarget( int t ){              //设置追踪的坐标
		target = t;
	}
	void init( int a, int b, int c, int d, Image img ){
		mapx = a; mapy = b; image = img;
		screenx = c; screeny = d;
	}
	int getDis( int x1, int y1, int x2, int y2 ){        //在一定的范围内才会追踪，不然随机运动
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}
	void draw( Graphics g ){                               //显示自己
		if( isAlive ){
			int x1 = step%4, y1 = Dir;
			g.drawImage( image, screenx-Map.x, screeny-Map.y, screenx+36-Map.x, screeny+36-Map.y, x1*51, y1*70, (x1+1)*51, (y1+1)*70, null );
		}
	}
	Rectangle getRect(){       //自身位置
		rec.x = screenx-Map.x; rec.y = screeny-Map.y;
		return rec;
	}
	@Override
	public void run() {                               //自身的线程，用于移动
		// TODO Auto-generated method stub
		while( true ){
			try{
				Thread.sleep(20);
			}
			catch( Exception ep ){
				ep.printStackTrace();
			}
			int from = Map.cell[mapx][mapy].id;
			int next;
			if( getDis( mapx, mapy, Map.queOfNoWall.get( target ).x, Map.queOfNoWall.get( target ).y ) <= 200 )
						next = Map.move[from][target];
			else next = rand.nextInt(4);            //判断是否在追踪半径范围之内
			if( next < 0 || next >= 4 ) continue;
			int x = mapx+dir[next][0];
			int y = mapy+dir[next][1];
			if( Map.inside( x, y ) && Map.cell[x][y].isWall == false ){      //判断路径是否合法
				Dir = 4 - next - 1;
				for( int i = 0; i < Map.width/speed; ++i ){
					try{
						Thread.sleep(20);
					}
					catch( Exception ep ){
						ep.printStackTrace();
					}
					screenx += dir[next][0]*speed;
					screeny += dir[next][1]*speed;
					if( (screeny+screenx)%12 == 0 )
						step = (step+1)%4;
					if( person.getRect().intersects( this.getRect() ) ){        //和player碰撞的处理
						screenx -= 12*dir[next][0]*speed;
						screeny -= 12*dir[next][1]*speed;
						i -= 12;
						if( isAlive ){
							person.WrHemo.reduceWithHit( 2 );
							person.mapx -= dir[person.Dir][0];
							person.mapy -= dir[person.Dir][1];
							person.x -= dir[person.Dir][0];
							person.y -= dir[person.Dir][1];
						}
					}
				}
				
				mapx += dir[next][0];
				mapy += dir[next][1];
			}
			if( !isAlive ) return;
		}
	}
}
