
package meteorsiege;

import java.util.concurrent.atomic.AtomicBoolean;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import meteorsiege.control.BorderGuard;
import meteorsiege.control.Collider;
import meteorsiege.control.Settler;
import meteorsiege.gamedata.GameItemsContainer;
import meteorsiege.gameitems.GameItemInterface;
import meteorsiege.gameitems.Station;
import meteorsiege.sounds.MeteorSiegeSoundStore;

public class MeteorSiege extends BasicGame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MeteorSiege(String gamename)
		{
		super(gamename);
		soundStore = MeteorSiegeSoundStore.getInstance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
		{
		// draw the station
		station.draw(g);

		// draw projectils
		for(int i = 0; i < Config.SIZE_PROJECTILS_CONTAINER; i++)
			{
			GameItemInterface itemToDraw = projectilsContainer.get(i);
			if (itemToDraw != null)
				{
				itemToDraw.draw(g);
				}
			}

		for(int i = 0; i < ennemisContainer.length(); i++)
			{
			GameItemInterface itemToDraw = ennemisContainer.get(i);
			if (itemToDraw != null)
				{
				itemToDraw.draw(g);
				}
			}
		// TODO draw annimation

		}

	@Override
	public void init(GameContainer gc) throws SlickException
		{

		// TODO dynamique size
		station = new Station(950, 500);

		interupOrder = new AtomicBoolean(false);
		playerIsShooting = false;
		ennemisContainer = new GameItemsContainer<GameItemInterface>(Config.SIZE_ENNEMIS_CONTAINER);
		projectilsContainer = new GameItemsContainer<GameItemInterface>(Config.SIZE_PROJECTILS_CONTAINER);

		// init BorderGuards
		Rectangle border = new Rectangle(100, 100, gc.getWidth() - 200, gc.getHeight() - 200);
		ennemisBorderGuard = new BorderGuard(interupOrder, border, ennemisContainer);
		projectilsBorderGuard = new BorderGuard(interupOrder, border, projectilsContainer);

		Thread ennemisBorderGuardThread = new Thread(ennemisBorderGuard);
		Thread projectilsBorderGuardThread = new Thread(projectilsBorderGuard);

		// init Collider
		collider = new Collider(interupOrder, station, ennemisContainer, projectilsContainer);

		Thread colliderThread = new Thread(collider);

		// init Settler
		settler = new Settler(interupOrder, ennemisContainer);
		Thread settlerThread = new Thread(settler);

		// démarrage des Threads

		ennemisBorderGuardThread.start();
		projectilsBorderGuardThread.start();
		colliderThread.start();
		settlerThread.start();

		}

	@Override
	public void update(GameContainer gc, int deltaTime) throws SlickException
		{

		for(int i = 0; i < ennemisContainer.length(); i++)
			{
			GameItemInterface itemToMove = ennemisContainer.get(i);
			if (itemToMove != null)
				{
				itemToMove.nextPosition(deltaTime);
				}
			}

		for(int i = 0; i < projectilsContainer.length(); i++)
			{
			GameItemInterface itemToMove = projectilsContainer.get(i);
			if (itemToMove != null)
				{
				itemToMove.nextPosition(deltaTime);
				}
			}

		station.decreaseTimerShoot(deltaTime);
		if (station.canShoot() && playerIsShooting)
			{
			station.fireMainTurret(projectilsContainer);
			MeteorSiegeSoundStore.turretBlaster.play();
			}

		}

	/*------------------------------------------------------------------*\
	|*							Controls Methodes						*|
	\*------------------------------------------------------------------*/
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy)
		{
		// TODO taille dynamique
		station.setTurretDirection(Tools.getAngle(newx - 1900 / 2, -1 * (newy - 1000 / 2)));
		}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy)
		{
		// TODO taille dynamique
		station.setTurretDirection(Tools.getAngle(newx - 1900 / 2, -1 * (newy - 1000 / 2)));
		}

	@Override
	public void mouseReleased(int button, int x, int y)
		{
		playerIsShooting = false;
		}

	@Override
	public void mousePressed(int button, int x, int y)
		{
		playerIsShooting = true;
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
	private GameItemsContainer<GameItemInterface> ennemisContainer;
	private GameItemsContainer<GameItemInterface> projectilsContainer;
	private Station station;
	private MeteorSiegeSoundStore soundStore;
	private boolean playerIsShooting;
	private AtomicBoolean interupOrder;
	private BorderGuard ennemisBorderGuard;
	private BorderGuard projectilsBorderGuard;
	private Settler settler;
	private Collider collider;

	}
