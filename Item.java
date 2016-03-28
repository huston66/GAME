package jolie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

//��Ʒ��
public abstract class Item {
	public static Toolkit TLK=Toolkit.getDefaultToolkit();
	
	int x,y;
	int width,height;
	Expedition ex;
	
	public Item(int x,int y,Expedition ex)
	{
		this.x=x;
		this.y=y;
		this.ex=ex;
	}
	
	abstract void draw(Graphics g);
	abstract void eat(Warrior wr);
	
	public Rectangle getRec()
	{
		return new Rectangle(x,y,width,height);
	}
}

//��Ѫ��
class RaiseBlood extends Item
{
	static final Image plus=TLK.getImage(Item.class.getClassLoader().getResource("Image/Item/plus.png"));
	RaiseBlood(int x,int y,Expedition ex)
	{
		super(x,y,ex);
		this.width=20;
		this.height=20;
	}
	
	void draw(Graphics g)
	{
		g.drawImage(plus,x-width/2,y-height/2,null);
	}
	
	//���˺����Ӧ
	void eat(Warrior wr)
	{
		if(wr.isCBC==false)
		{
			wr.WrHemo.raiseBlood(50);
			ex.ItemList.remove(this);
		}
	}
}

//�����ӵ���
class shotItem extends Item
{
	static final Image slug=TLK.getImage(Item.class.getClassLoader().getResource("Image/Item/ammo.png"));
	shotItem(int x,int y,Expedition ex)
	{
		super(x,y,ex);
		this.width=10;
		this.height=10;
	}
	
	void draw(Graphics g)
	{
		g.drawImage(slug,x,y,null);
	}
	
	void eat(Warrior wr)
	{
		if(wr.isCBC==false)
		{
			wr.shotcurr+=30;
			ex.ItemList.remove(this);
		}
	}
}

//��ҩ��
class Antidote extends Item
{
	static final Image drug=TLK.getImage(Item.class.getClassLoader().getResource("Image/Item/antidote.png"));
	public Antidote(int x, int y, Expedition ex) {
		super(x, y, ex);
		this.height=26;
		this.width=26;
	}
	
	void draw(Graphics g)
	{
		g.drawImage(drug,x,y,null);
	}
	
	void eat(Warrior wr)
	{
		if(wr.isCBC==false)
		{
			wr.WARRIORSPEED=5;
			ex.ItemList.remove(this);
		}
	}
}

//�������ܲ���
class Radish extends Item
{
	static final Image carrot=TLK.getImage(Item.class.getClassLoader().getResource("Image/Item/Radish.png"));
	public Radish(int x, int y, Expedition ex) {
		super(x, y, ex);
		this.height=36;
		this.width=41;
	}
	
	void draw(Graphics g)
	{
		g.drawImage(carrot,x,y,null);
	}
	
	void eat(Warrior wr)
	{
		if(wr.isCBC==false)
		{
			wr.WARRIORSPEED=2;
			ex.ItemList.remove(this);
		}
	}
}
