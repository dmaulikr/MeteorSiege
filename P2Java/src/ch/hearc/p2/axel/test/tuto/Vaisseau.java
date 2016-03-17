
package ch.hearc.p2.axel.test.tuto;

import org.newdawn.slick.geom.Rectangle;

public class Vaisseau extends Rectangle
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Vaisseau(float x, float y, float width, float height, float speedX, float speedY)
		{
		super(x, y, width, height);

		this.speedX = speedX;
		this.speedY = speedY;
		// TODO Auto-generated constructor stub
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public float getSpeedX()
		{
		return this.speedX;
		}

	public void setSpeedX(float speedX)
		{
		this.speedX = speedX;
		}

	public float getSpeedY()
		{
		return this.speedY;
		}

	public void setSpeedY(float speedY)
		{
		this.speedY = speedY;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private float speedX;
	private float speedY;
	}
