package jolie;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

public class Map {                          //dfs随机生成的迷宫地图
	public static Toolkit TLK=Toolkit.getDefaultToolkit();
	static final int dir[][] = { {0, -1}, {1, 0}, {-1, 0}, {0, 1} };            
	static final int UP = 3, DOWN = 0, LEFT = 1, RIGHT = 2;
	static final int width = 36;
	static final int length = 36;
	static int numOfRow = 20, numOfCol = 20, numOfFire = 50;       //
	static int numX = 2*numOfRow + 3, numY = numOfCol*2 + 3;       //行数列数和火焰的数量
	static int x = width, y = 0;
	static int pre[];
	static int move[][];
	static int doorx, doory;                                       //出口坐标
	static Rectangle rec = new Rectangle( 0, 0, 36, 36 );
	static Cell[][] cell = new Cell[numX][numY]; 
	static Random rand = new Random();
	static ArrayList<Cell> queOfNoWall = new ArrayList<Cell>();
	static int cnt = 0;
	static Image image = TLK.getImage(Item.class.getClassLoader().getResource("Image/myPerson/12.gif"));
	Map(){
		x = width; y = 0;
		for( int i = 0; i < numX; ++i )
			for( int j = 0; j < numY; ++j ) cell[i][j] = new Cell( i, j );
		for( int i = 0, j = numY-1; i < numX; ++i ) cell[j][i].isWall = cell[0][i].isWall = false;
		for( int i = 0, j = numX-1; i < numY; ++i ) cell[i][j].isWall = cell[i][0].isWall = false;
		rand.setSeed(System.currentTimeMillis());
		search( rand.nextInt(numOfRow), rand.nextInt(numOfCol) );
		findPath();
		doorx = (numX-2)*36; doory = (numY-2)*36;
	}
	Map( int r, int c ){
		numOfRow = r; numOfCol = c;
		numX = 2*numOfRow + 3; numY = numOfCol*2 + 3;
		for( int i = 0; i < numX; ++i )
			for( int j = 0; j < numY; ++j ) cell[i][j] = new Cell( i, j );
		for( int i = 0, j = numY-1; i < numX; ++i ) cell[j][i].isWall = cell[0][i].isWall = false;
		for( int i = 0, j = numX-1; i < numY; ++i ) cell[i][j].isWall = cell[i][0].isWall = false;
		rand.setSeed(System.currentTimeMillis());
		search( rand.nextInt(numOfRow), rand.nextInt(numOfCol) );
		findPath();
	}
	static void init(){
		x = width; y = 0;
		for( int i = 0; i < numX; ++i )
			for( int j = 0; j < numY; ++j ) cell[i][j] = new Cell( i, j );
		for( int i = 0, j = numY-1; i < numX; ++i ) cell[j][i].isWall = cell[0][i].isWall = false;
		for( int i = 0, j = numX-1; i < numY; ++i ) cell[i][j].isWall = cell[i][0].isWall = false;
		rand.setSeed(System.currentTimeMillis());
		search( rand.nextInt(numOfRow), rand.nextInt(numOfCol) );
		findPath();
	}
	private static void findPath(){                //初始化追踪的路径
		cnt = 0;
		for( int i = 1; i < numX-1; ++i )
			for( int j = 1; j < numY-1; ++j ) if( !cell[i][j].isWall ){
				cell[i][j].id = cnt++;
				queOfNoWall.add( cell[i][j] );
			}
		pre = new int[cnt+3]; move = new int[cnt+3][cnt+3];
		for( int i = 0; i < cnt; ++i )
			bfs( i );
	}
	static void bfs( int from )                     //bfs查找路径
	{
		for( int i = 0; i < cnt; ++i ) pre[i] = -1;
		int que[] = new int[cnt+3];
		int tail = 0, top = 0;
		pre[from] = 0;
		int ux = queOfNoWall.get( from ).x, uy = queOfNoWall.get( from ).y;
		que[tail++] = ux*numY + uy;
		while( tail > top ){
			ux = que[top] / numY;
			uy = que[top++] % numY;
			for( int i = 0; i < 4; ++i ){
				int x = ux + dir[i][0];
				int y = uy + dir[i][1];
				if( inside(x,y) &&cell[x][y].isWall == false && cell[x][y].id >= 0 && pre[cell[x][y].id] == -1 ){
					pre[cell[x][y].id] = 4 - i - 1;
					que[tail++] = x*numY+y;
				}
			}
		}
		for( int i = 0; i < cnt; ++i ) move[i][from] = pre[i];
	}
	private static void search( int x, int y ){                  //随机生成迷宫的函数
		int dx = 2*x, dy = 2*y;
		cell[dx][dy].isWall = false;
		int i, next, turn = 3;
		if( rand.nextInt(2) == 1 ) turn = 1;
		for( i = 0, next = rand.nextInt(4); i < 4; ++i, next = (next+turn)%4 ) if( inside( dx+2*dir[next][0], dy+2*dir[next][1]) ){
			if( cell[dx+2*dir[next][0]][dy+2*dir[next][1]].isWall ){
				cell[dx+dir[next][0]][dy+dir[next][1]].isWall = false;
				search( x + dir[next][0], y + dir[next][1] );
			}
		}
	}
	static boolean inside( int x, int y ){                          //判断坐标是否在地图范围内
		if( x >= 0&& x < numX && y >= 0 && y <= numY ) return true;
		else return false;
	}
	static Rectangle getTheDoor(){          //出口位置
		rec.x = doorx - Map.x; rec.y = doory-Map.y;
		return rec;
	}
}
class Cell{            //地图元素
	static int width = 36;
	int x, y, id = 0;
	Rectangle rec;
	boolean isWall = true;
	Cell( int x, int y ){
		this.x = x; this.y = y;
		rec =  new Rectangle(x*width+width/3, y*width+width/3, width/3, width/3);
	}
}
