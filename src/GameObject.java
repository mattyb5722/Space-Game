import java.awt.Rectangle;

public class GameObject {
	
	public double x, y;
	public String ID;
	
	MainBody main;
	Textures tex;

	public GameObject(double x, double y, String ID, 
			MainBody main, Textures tex){
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.main = main;
		this.tex = tex;
	}
	
	public Rectangle getBound(int width, int height){ // Hit box
		return new Rectangle((int) x, (int) y, width, height);
	}
}
