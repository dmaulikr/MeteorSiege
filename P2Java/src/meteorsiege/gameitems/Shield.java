
package meteorsiege.gameitems;

import org.newdawn.slick.Graphics;

public class Shield
	{


	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Shield(int life, int regen)
		{
		super();
		this.life = life;
		this.maxLife = life;
		this.regen = regen;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public int takeDamage(int damage)
		{
		life -= damage;
		if (life < 0)
			{
			int unabsorbedDamage = -life;
			life = 0;
			return unabsorbedDamage;
			}
		else
			{
			return 0;
			}
		}

	public void upgradeLife(int value)
		{
		maxLife += value;
		life += value;
		}

	public void upgradeRegen(int value)
		{
		regen += value;
		}

	public void regen(int deltaTime)
		{
		// TODO régéneration du bouclier en fonction de la vitesse de régénération
		}

	public void draw(Graphics g)
		{
		// TODO dessiner l'effet du bouclier
		if (life <= 0)
			{
			// actif
			}
		else
			{
			// détruit
			}
		}

	public void addLife(int value)
		{
		this.maxLife += value;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getLife()
		{
		return life;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int life;
	private int maxLife;
	private int regen;

	}
