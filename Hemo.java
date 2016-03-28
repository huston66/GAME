package jolie;

import java.awt.Color;
import java.awt.Graphics;

//Ѫ��
public class Hemo {
	public static final int BLOODLENGTH=50;
	int totalBlood=50;
	int currBlood;
	Warrior Aeneas;
	
	public Hemo(int currBlood,Warrior Aeneas)
	{
		if(currBlood>50||currBlood<=0)
		{
			this.currBlood=50;
		}
		else
		this.currBlood=currBlood;
		
		this.Aeneas=Aeneas;
	}
	
	//��Ѫ��
	public void draw(Graphics g)
	{
		Color c=g.getColor();
		
		g.setColor(Color.WHITE);
		g.drawRect(Aeneas.x-4, Aeneas.y-15, BLOODLENGTH, 5);
		g.setColor(Color.RED);
		g.fillRect(Aeneas.x-3, Aeneas.y-14, currBlood*BLOODLENGTH/totalBlood-1, 4);
		
		g.setColor(c);
	}
	
	//����ײ��Ѫ
	public void reduceWithHit(int minus)
	{
		currBlood-=minus;
		if(isEmpty())
		{
			Aeneas.dead();
		}
	}
	
	//���ӵ���Ѫ
	public void reduceWithShot(int minus)
	{
		currBlood-=minus;
		if(isEmpty())
		{
			Aeneas.dead();
		}
	}
	
	//��Ѫ
	public void raiseBlood(int plus)
	{
		currBlood+=plus;
		if(currBlood>totalBlood)
		{
			currBlood=totalBlood;
		}
	}
	
	//�ж�Ѫ�Ƿ�Ϊ��
	public boolean isEmpty()
	{
		if(currBlood<=0)
			return true;
		else
			return false;
	}
}
