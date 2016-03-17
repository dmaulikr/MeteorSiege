
package ch.hearc.p2.axel.test.tuto;

import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SimpleSlickGame extends BasicGame
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public SimpleSlickGame(String gamename)
		{
		super(gamename);
		}

	@Override
	public void init(GameContainer gc) throws SlickException
		{
		rectangle1 = new Vaisseau(10, 300, 20, 20, 0.1f, 0.3f);
		rectangle2 = new Vaisseau(200, 150, 20, 20, 0.1f, 0.01f);

		listFormes.add(rectangle1);
		listFormes.add(rectangle2);

		Random rnd = new Random();
		float vitesseX;
		float vitesseY;
		for(int i = 0; i < NBRECT; i++)
			{
			vitesseX = rnd.nextFloat() - 0.5f;
			vitesseY = rnd.nextFloat() - 0.5f;
			listFormes.add(new Vaisseau(900, i * 80, 20, 20, vitesseX, vitesseY));
			}
		}

	@Override
	public void update(GameContainer gc, int deltaTime) throws SlickException
		{
		Vaisseau forme;
		Vaisseau formeCollision;

		//Ne marche evidemment pas, car il faut breaker la boucle
		for(int i = 0; i < listFormes.size(); i++)
			{
			forme = listFormes.get(i);

			int j = 0;
			boolean intersect = false;

			while((!intersect) && (j < NBRECT - 1))
				{
				if (i != j)
					{
					formeCollision = listFormes.get(j);
					intersect = (forme.intersects(formeCollision));
					if (!intersect)
						{
						j++;
						}
					}
				else
					{
					j++;
					}
				}

			if (!intersect)
				{
				forme.setCenterX(forme.getCenterX() + forme.getSpeedX() * deltaTime);
				forme.setCenterY(forme.getCenterY() + forme.getSpeedY() * deltaTime);
				}
			}

		/*
		if (!rectangle1.intersects(rectangle2))
			{
			rectangle1.setCenterX(rectangle1.getCenterX() + rectangle1.getSpeedX() * deltaTime);
			rectangle1.setCenterY(rectangle1.getCenterY() + rectangle1.getSpeedY() * deltaTime);
			}
		*/
		}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
		{
		g.drawString("Howdy!", 100, 100);
		g.drawRect(rectangle1.getX(), rectangle1.getY(), rectangle1.getWidth(), rectangle1.getHeight());
		g.drawRect(rectangle2.getX(), rectangle2.getY(), rectangle2.getWidth(), rectangle2.getHeight());

		for(Rectangle forme:listFormes)
			{
			g.drawRect(forme.getX(), forme.getY(), forme.getWidth(), forme.getHeight());
			}
		}

	@Override
	public void keyReleased(int key, char c)
		{
		if (Input.KEY_ESCAPE == key)
			{
			container.exit();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Tools
	private GameContainer container;
	private Vaisseau rectangle1;
	private Vaisseau rectangle2;
	private LinkedList<Vaisseau> listFormes = new LinkedList<Vaisseau>();

	private static final float SPEED = (float)0.1;
	private static final int NBRECT = 200;
	}
