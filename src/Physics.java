import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

public class Physics {

	// EntityA and EntityB collided
	public static boolean collision(EntityA enta, EntityB entb){
		return enta.getBounds().intersects(entb.getBounds());
	}
	
	// EntityA and EntityC collided
	public static boolean collision(EntityA enta, EntityC entc){
		return enta.getBounds().intersects(entc.getBounds());
	}
	
}
