package jolie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import jolie.RouteNode.RSTYLE;

//铺路的类
public class Route {
	static final int WIDTH=64;
	static final int HEIGHT=64;
	static final int width=16;
	static final int height=16;
	//不同类型的路的坐标
	public static int[][] DoorC=
	{
		{WIDTH-3*width,width},{WIDTH-width,width},
		{WIDTH-3*width,3*width-1},{WIDTH-width,3*width-1},
		{WIDTH-3*width,5*width-2},{WIDTH-width,5*width-2},
	};
	
	public static int[][] DoorR=
	{
		{WIDTH+54*width+1,27*width},{WIDTH+54*width+1,29*width-1},{WIDTH+54*width+1,31*width-2},
		{WIDTH+56*width+1,27*width},{WIDTH+56*width+1,29*width-1},{WIDTH+56*width+1,31*width-2},
	};
	
	public static int[][] RouteBlue={
		{WIDTH-2*width,8*width-2},{WIDTH,8*width-2},
		{WIDTH-2*width,11*width-2},{WIDTH,11*width-2},
		{WIDTH-2*width,14*width-2},{WIDTH,14*width-2},
		{WIDTH+width,14*width-2},{WIDTH+width,11*width-2},
		{WIDTH+27*width,22*width-2},
	};
	
	public static int[][] RouteMap={
		{WIDTH-3*width,width},{WIDTH+1*width,width},{WIDTH-3*width,4*width},
		{WIDTH,width},{WIDTH+4*width,width},
		{WIDTH,3*width-2},{WIDTH+4*width,3*width-2},
		{WIDTH-2*width+1,25*width-3},{WIDTH-2*width+1,28*width-2},{WIDTH-2*width+1,29*width-2},
		{WIDTH+2*width+1,25*width-3},{WIDTH+2*width+1,28*width-2},{WIDTH+2*width+1,29*width-2},
		{WIDTH+6*width+1,29*width-2},{WIDTH+10*width+1,29*width-2},{WIDTH+14*width+1,29*width-2},{WIDTH+18*width+1,29*width-2},{WIDTH+22*width+1,29*width-2},{WIDTH+24*width+1,29*width-2},
		{WIDTH+27*width+1,27*width-2},{WIDTH+28*width+1,27*width-2},
		{WIDTH+32*width,25*width-2},{WIDTH+32*width,22*width},
		{WIDTH+35*width+1,25*width-2},{WIDTH+35*width+1,22*width},
		{WIDTH+39*width+1,25*width-2},{WIDTH+39*width+1,22*width},
		{WIDTH+43*width+1,25*width-2},{WIDTH+43*width+1,22*width},
		{WIDTH+39*width+1,20*width-2},{WIDTH+43*width+1,20*width-2},
		{WIDTH+39*width+1,16*width-2},{WIDTH+40*width+1,16*width-2},
		{WIDTH+39*width+1,13*width-2},{WIDTH+40*width+1,13*width-2},
		{WIDTH+34*width+1,13*width-2},{WIDTH+35*width+1,13*width-2},
		{WIDTH+34*width+1,15*width},{WIDTH+35*width+1,15*width},
		{WIDTH+34*width+1,17*width},{WIDTH+30*width+1,17*width},{WIDTH+26*width+1,17*width},{WIDTH+22*width+1,17*width},{WIDTH+18*width+1,17*width},{WIDTH+14*width+1,17*width},{WIDTH+12*width+1,17*width},
		{WIDTH+48*width+1,15*width-2},{WIDTH+45*width+1,15*width-2},
		{WIDTH+48*width+1,11*width-2},{WIDTH+45*width+1,11*width-2},
		{WIDTH+48*width+1,7*width-2},{WIDTH+45*width+1,7*width-2},
		{WIDTH+48*width+1,3*width-2},{WIDTH+45*width+1,3*width-2},
		{WIDTH+42*width+1,3*width-2},{WIDTH+40*width+1,8*width-2},
		{WIDTH+36*width+1,8*width-2},{WIDTH+32*width+1,8*width-2},{WIDTH+28*width+1,8*width-2},{WIDTH+24*width+1,8*width-2},{WIDTH+20*width+1,8*width-2},{WIDTH+18*width+1,8*width-2},
		{WIDTH+18*width+1,10*width-2},{WIDTH+22*width+1,10*width-2},{WIDTH+26*width+1,10*width-2},{WIDTH+29*width+1,10*width-2},
		{WIDTH+40*width+1,6*width-2},{WIDTH+36*width+1,6*width-2},{WIDTH+32*width+1,6*width-2},{WIDTH+28*width+1,6*width-2},
		{WIDTH+23*width+1,3*width},{WIDTH+25*width+1,3*width},{WIDTH+17*width+1,3*width},{WIDTH+18*width+1,4*width},
		{WIDTH+52*width+1,29*width},{WIDTH+53*width+1,29*width},
		{WIDTH+54*width+1,27*width},{WIDTH+54*width+1,29*width},
	};
	
	public static int[][] RouteR={
		{WIDTH+6*width+1,width},{WIDTH+6*width+1,6*width-2},{WIDTH+6*width+1,11*width-2},
		{WIDTH+6*width+1,16*width-2},{WIDTH+6*width+1,21*width-2},{WIDTH+6*width+1,22*width-2},
		{WIDTH+46*width+1,23*width-2},{WIDTH+46*width+1,20*width-2},
		{WIDTH+52*width+1,6*width},{WIDTH+52*width+1,11*width},{WIDTH+52*width+1,16*width},{WIDTH+52*width+1,20*width},
	};
	
	public static int[][] RouteU={
		{WIDTH+11*width+1,27*width-2},{WIDTH+16*width+1,27*width-2},{WIDTH+21*width+1,27*width-2},{(int) (WIDTH+19.7*width+1),27*width-2},
		{WIDTH-width+5,20*width-3},{WIDTH-2*width+1,20*width-3},
		{WIDTH+27*width,15*width-2},{WIDTH+22*width,15*width-2},{WIDTH+19*width,15*width-2},
		{WIDTH+17*width+1,1*width},{WIDTH+23*width+1,1*width},{WIDTH+28*width+1,1*width},{WIDTH+33*width+1,1*width},{WIDTH+38*width+1,1*width},{WIDTH+43*width+1,1*width},{WIDTH+48*width+1,1*width},
		
	};
	
	public static int[][] RouteD={
		{WIDTH+33*width+1,28*width-2},{WIDTH+38*width+1,28*width-2},{WIDTH+43*width+1,28*width-2},
		{WIDTH+17*width+1,21*width},{WIDTH+21*width+1,21*width},
	};
	public static int[][] RouteL={
		{WIDTH+12*width+1,16*width},{WIDTH+12*width+1,11*width},{WIDTH+12*width+1,6*width},
	};
	
	public static int[][] Rs={
		{WIDTH+6*width+1,27*width-2},{WIDTH+14*width,15*width-2},
		{WIDTH+52*width+1,25*width},
	};
	
	public static int[][] Zs={
		{(int) (WIDTH+4.3*width+1),20*width-2},{(int) (WIDTH+26.05*width-7),27*width-1},
		{WIDTH+32*width,15*width-2},
	};
	
	public static int[][] Rx={
		{WIDTH+28*width+1,28*width-2},
	};
	
	public static int[][] Rxl={
		{WIDTH+46*width+1,28*width-2},
	};
	
	public static int[][] Zxl={
		{WIDTH+12*width+1,21*width},
	};
	
	public static int[][] Zsl={
		{WIDTH+12*width+1,1*width},
	};
	
	public static int[][] Rsl={
		{WIDTH+52*width+1,1*width},
	};
	
	ArrayList<RouteNode> RouteList=new ArrayList<RouteNode>();
	
	Expedition ex;
	int x,y;
	
	public Route(int x,int y,Expedition ex)
	{
		this.x=x;
		this.y=y;
		this.ex=ex;
		
		//根据不同类型的路加入到list类
		for(int i=0;i<RouteMap.length;i++)
		{
			RouteList.add(new RouteNode(x+RouteMap[i][0],y+RouteMap[i][1],RSTYLE.TREE));
		}
		
		for(int i=0;i<RouteL.length;i++)
		{
			RouteList.add(new RouteNode(x+RouteL[i][0],y+RouteL[i][1],RSTYLE.TREEL));
		}
		
		for(int i=0;i<RouteR.length;i++)
		{
			RouteList.add(new RouteNode(x+RouteR[i][0],y+RouteR[i][1],RSTYLE.TREER));
		}
		
		for(int i=0;i<RouteU.length;i++)
		{
			RouteList.add(new RouteNode(x+RouteU[i][0],y+RouteU[i][1],RSTYLE.TREEU));
		}
		
		for(int i=0;i<RouteD.length;i++)
		{
			RouteList.add(new RouteNode(x+RouteD[i][0],y+RouteD[i][1],RSTYLE.TREED));
		}
		
		for(int i=0;i<Rxl.length;i++)
		{
			RouteList.add(new RouteNode(x+Rxl[i][0],y+Rxl[i][1],RSTYLE.RXL));
		}
		
		for(int i=0;i<Zxl.length;i++)
		{
			RouteList.add(new RouteNode(x+Zxl[i][0],y+Zxl[i][1],RSTYLE.ZXL));
		}
		
		for(int i=0;i<Zsl.length;i++)
		{
			RouteList.add(new RouteNode(x+Zsl[i][0],y+Zsl[i][1],RSTYLE.ZSL));
		}
		
		for(int i=0;i<Rsl.length;i++)
		{
			RouteList.add(new RouteNode(x+Rsl[i][0],y+Rsl[i][1],RSTYLE.RSL));
		}
		
		for(int i=0;i<Rs.length;i++)
		{
			RouteList.add(new RouteNode(x+Rs[i][0],y+Rs[i][1],RSTYLE.RS));
		}
		
		for(int i=0;i<Zs.length;i++)
		{
			RouteList.add(new RouteNode(x+Zs[i][0],y+Zs[i][1],RSTYLE.ZS));
		}
		
		for(int i=0;i<Rx.length;i++)
		{
			RouteList.add(new RouteNode(x+Rx[i][0],y+Rx[i][1],RSTYLE.RX));
		}
		
		for(int i=0;i<RouteBlue.length;i++)
		{
			RouteList.add(new RouteNode(x+RouteBlue[i][0],y+RouteBlue[i][1],RSTYLE.RB));
		}
		
		for(int i=0;i<DoorR.length;i++)
		{
			RouteList.add(new RouteNode(x+DoorR[i][0],y+DoorR[i][1],RSTYLE.DOORR));
		}
		
		for(int i=0;i<DoorC.length;i++)
		{
			RouteList.add(new RouteNode(x+DoorC[i][0],y+DoorC[i][1],RSTYLE.DOORC));
		}
	}
	
	//画路
	public void draw(Graphics g)
	{
		for(int i=0;i<RouteList.size();i++)
			RouteList.get(i).draw(g);
	}
}

//路的类型
class RouteNode
{
	public static enum RSTYLE {TREE,TREEL,TREEU,TREER,RS,ZS,RX,TREED,RXL,ZXL,ZSL,RSL,RB,DOORR,DOORC};
	static Toolkit TLK=Toolkit.getDefaultToolkit();
	public static final Image[] img={
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/brownl.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/treeR.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/treeU.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/treeL.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/RS.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/ZS.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/RX.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/treeD.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/RXL.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/ZXL.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/ZSL.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/RSL.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/blue.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/doorR.png")),
		TLK.getImage(WallNode.class.getClassLoader().getResource("Image/Wall/doorC.png")),
	};
	
	int x,y;
	RSTYLE style;
	RouteNode(int x,int y,RSTYLE style)
	{
		this.x=x;
		this.y=y;
		this.style=style;
	}
	
	//根据路的不同类型来画
	public void draw(Graphics g)
	{
		if(this.style==RSTYLE.TREE)
			g.drawImage(img[0],x,y,null);
		else if(this.style==RSTYLE.TREER)
			g.drawImage(img[1],x,y,null);
		else if(this.style==RSTYLE.TREEU)
			g.drawImage(img[2],x,y,null);
		else if(this.style==RSTYLE.TREEL)
			g.drawImage(img[3],x,y,null);
		else if(this.style==RSTYLE.RS)
			g.drawImage(img[4],x,y,null);
		else if(this.style==RSTYLE.ZS)
			g.drawImage(img[5],x,y,null);
		else if(this.style==RSTYLE.RX)
			g.drawImage(img[6],x,y,null);
		else if(this.style==RSTYLE.TREED)
			g.drawImage(img[7],x,y,null);
		else if(this.style==RSTYLE.RXL)
			g.drawImage(img[8],x,y,null);
		else if(this.style==RSTYLE.ZXL)
			g.drawImage(img[9],x,y,null);
		else if(this.style==RSTYLE.ZSL)
			g.drawImage(img[10],x,y,null);
		else if(this.style==RSTYLE.RSL)
			g.drawImage(img[11],x,y,null);
		else if(this.style==RSTYLE.RB)
			g.drawImage(img[12],x,y,null);
		else if(this.style==RSTYLE.DOORR)
			g.drawImage(img[13],x,y,null);
		else if(this.style==RSTYLE.DOORC)
			g.drawImage(img[14],x,y,null);
	}
}
