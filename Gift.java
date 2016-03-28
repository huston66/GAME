package jolie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

//��������֮��������ֵĽ���
public class Gift {
	static final Toolkit TLK=Toolkit.getDefaultToolkit();  
	int x, y, id = 0, width = 36;
	Rectangle rec = new Rectangle( 0, 0, 36, 36 );
	int mx, my;
	boolean isAlive = true;
	boolean isGet = false;
	boolean isAdd = false;
	int step = 0, step2 = 0;
	static ImageList img = new ImageList();
	static myPerson person;
	Image image;
	static Expedition ex;
	Gift( int x, int y){               //��ʼ��λ��
		this.x = x; this.y = y;
		id = (x+y)%4;
		image = img.img[id];
		width = 36;
		isAlive = true;
		isGet = false;
		mx = Map.x; my = Map.y;
	}                               //��ʾ����
	void draw( Graphics g ){
		g.drawImage( image, x-Map.x, y-Map.y, width, width, null );
	}
	Rectangle getRect(){             //������ײ
		rec.x = x - Map.x; rec.y = y - Map.y;
		return rec;
	}
	void move(){                    //��̬��ʾ����
		++step;
		if( step >= 200 ) isAlive = false;
		if( !isGet && step % 4 == 0 ){
			id = (id+1) % 4;
			image = img.img[id];
		}
		if( isGet ){
			if( !isAdd ){
				switch( id ){
				case 0: person.crump++; break;
				case 1:	ex.uw.shotcurr+=3; break;
				case 2: person.WrHemo.raiseBlood(50); break;
				case 3: person.tube++; break;
				}
				isAdd = true;
			}
			width += 2;
			++step2;
			if( step2 >= 60 ) isAlive = false;
		}
	}
}
class ImageList{                //������ͼƬ�б�
	static final Toolkit TLK=Toolkit.getDefaultToolkit();
	Image img[] = new Image[4];
	ImageList(){
		for( int i = 0; i < 4; ++i ){
			String str  = "Image/myPerson/gift/" + i + ".png";
			img[i] =  TLK.getImage(Blow.class.getClassLoader().getResource(str)) ;
		}
	}
}
class Fire extends Gift{              //������ֵĻ��棬 �ή��player���ٶȺ�Ѫ
	int x1, y1;
	static Random rand = new Random();
	Fire(int x, int y) {
		super(x, y);
		image = TLK.getImage( Blow.class.getClassLoader().getResource("Image/myPerson/fire2_2.png") );
		step += rand.nextInt(100);
	}
	void draw( Graphics g ){
		x1 = step2%4; y1 = step2/2;
		if( isAlive )
			g.drawImage( image, x-Map.x, y-Map.y, x-Map.x+50,  y-Map.y+50, x1*100, y1*150, (x1+1)*100, (y1+1)*150, null );
	}
	void move(){
		++step;
		step2 = (step2+1)%8;
		if( (step/100)%2 == 0 ) isAlive = true;
		else isAlive = false;
	}
}
