
package meteorsiege.gameitems.projectiles;

import org.newdawn.slick.Graphics;

import meteorsiege.gameitems.GameItemInterface;
import meteorsiege.tools.ImageMagasin;

public class ProjectileM2 extends Projectile implements GameItemInterface
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProjectileM2(float x, float y, int life, int damage, float speed, float direction, float size)
		{
		super(x, y, life, damage*2, speed, direction, size);
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public synchronized void draw(Graphics g)
		{
		g.rotate(this.getCenterX(), this.getCenterY(), (float)Math.toDegrees(direction));
		g.drawImage(ImageMagasin.laserBlue, this.getCenterX() - (ImageMagasin.laserBlue.getWidth() / 2), this.getCenterY() - ImageMagasin.laserBlue.getHeight());

		g.resetTransform();
		}
	}
