
package meteorsiege.control;

import java.util.Random;

import org.newdawn.slick.geom.Rectangle;

import meteorsiege.gamedata.GameItemsContainer;
import meteorsiege.gameitems.GameItemInterface;
import meteorsiege.gameitems.ennemies.Meteor;
import meteorsiege.tools.Config;


/**
 * @author Julien M'Poy, Sylvain Ramseyer et Axel Roy<br>
 *
 * <h1>
 * Description
 * </h1>
 *
 * <p>
 * Classe qui peuple l'aire de jeux avec des ennemis
 * </p>
 *
 * <p>
 * pour l'instant basic : choisi un des 4 cot�s de l'aire de jeux pour y faire apparaitre un ennemis avec une direction(qui traverce l'aire de jeux) et une vitesse al�atoire.<br>
 * r�pete cette op�ration tout les tant de temps en fonction de la configuration.
 * </p>
 *
 * <p>
 * � utiliser dans un thread
 * </p>
 */
public class Settler implements Runnable
	{


	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	/**
	 * @param containerToSettle : container dans le quel le settler va mettre les items ennemis cr��s
	 * @param border : rectangle qui d�finit la zone d'apparition des items ennemis
	 */
	public Settler(GameItemsContainer<GameItemInterface> containerToSettle, Rectangle border)
		{
		super();
		this.containerToSettle = containerToSettle;
		this.randomGenerator = new Random();
		this.border = border;
		this.pause = false;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		while(!Thread.currentThread().isInterrupted())
			{
			try
				{
				Thread.sleep(50);
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}

			while(!pause)
				{
				try
					{
					Thread.sleep(Config.SETTLE_DELAY);
					}
				catch (InterruptedException e)
					{
					e.printStackTrace();
					}
				settle();
				}
			}
		}

	/**
	 * met en pause l'activit�e du thread
	 */
	public void pause()
		{
		pause = true;
		}

	/**
	 * reprend l'activit�e du thread
	 */
	public void resume()
		{
		pause = false;
		}

	public void stop()
		{
		Thread.currentThread().interrupt();
		}

	/**
	 * @return vitesse al�atoire entre les constante MAXSPEED et MINSPEED
	 */
	public float generateRandomSpeed()
		{
		return randomGenerator.nextFloat() * (Config.MAX_ENNEMIS_SPEED - Config.MIN_ENNEMIS_SPEED) + Config.MIN_ENNEMIS_SPEED;
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/**
	 * choisi un des 4 cot�s de l'aire de jeux pour y faire apparaitre un ennemis
	 * avec une direction(qui traverce l'aire de jeux) et une vitesse al�atoire
	 */
	private void settle()
		{
		int cote = randomGenerator.nextInt(4);
		float x = 0;
		float y = 0;
		float speedX = 0;
		float speedY = 0;
		switch(cote)
			{
			// top
			case 0:
				y = -Config.SETTLE_OFFSET;
				x = randomGenerator.nextInt(Config.getGameWidth());
				speedY = generateRandomSpeed();
				speedX = generateRandomSpeed();
				break;
			// right
			case 1:
				x = Config.getGameWidth() + Config.SETTLE_OFFSET;
				y = randomGenerator.nextInt(Config.getGameHeight());
				speedY = generateRandomSpeed();
				speedX = -generateRandomSpeed();
				break;
			// bottom
			case 2:
				y = Config.getGameHeight() + Config.SETTLE_OFFSET;
				x = randomGenerator.nextInt(Config.getGameWidth());
				speedY = -generateRandomSpeed();
				speedX = -generateRandomSpeed();
				break;
			// left
			default:
				x = -Config.SETTLE_OFFSET;
				y = randomGenerator.nextInt(Config.getGameHeight());
				speedY = generateRandomSpeed();
				speedX = generateRandomSpeed();
			}
		containerToSettle.add(new Meteor(x, y, speedX, speedY, 20, 400));
		}


	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private GameItemsContainer<GameItemInterface> containerToSettle;
	private Random randomGenerator;
	private Rectangle border;
	private boolean pause;
	}
