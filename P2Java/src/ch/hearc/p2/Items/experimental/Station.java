
package ch.hearc.p2.Items.experimental;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

import ch.hearc.p2.tools.Tools;

public class Station extends Circle
	{

	public Station(float centerPointX, float centerPointY, float radius)
		{
		super(centerPointX, centerPointY, radius);
		turret = new Turret(centerPointX,centerPointY,radius/3);
		}

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void draw(Graphics g)
		{
		g.drawImage(Station.image, this.getCenterX() - (Station.image.getWidth() / 2), this.getCenterY() - Station.image.getHeight() / 2);
		g.rotate(this.getCenterX(), this.getCenterY(), 180+(float)Math.toDegrees(this.getTurretDirection()));
		g.drawImage(this.getTurretImage(),this.getCenterX() - (this.getTurretImage().getWidth() / 2), this.getCenterY());
		g.drawImage(Station.imageHat, this.getCenterX() - (Station.imageHat.getWidth() / 2), this.getCenterY() - Station.imageHat.getHeight() / 2);
		g.resetTransform();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setTurretDirection(float angle)
		{
		turret.setDirection(angle);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public float getTurretDirection()
		{
		return turret.getDirection();
		}
	public Image getTurretImage()
		{
		return Turret.image;
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Turret turret;
	public static final Image image = Tools.loadImage("res/ufoRed.png") ;
	public static final Image imageHat = Tools.loadImage("res/Parts/turretBase_small.png") ;
	}
