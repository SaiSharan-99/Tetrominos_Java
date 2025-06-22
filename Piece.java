import java.util.Random;
import java.awt.Color;
public class Piece {
	private Location[] blocks = new Location[4];
	private Location position = new Location(4,0);
	private Color color;
	public Piece()
	{
		generateRandomShape();
	}
	
	private void generateRandomShape()
	{
		Random rand = new Random();
		int shape = rand.nextInt(7);
		this.color = Block.colors[rand.nextInt(Block.colors.length)];
		switch (shape)
		{
		case 0: // I-shape (vertical line)
			blocks[0] = new Location(0, -1);
			blocks[1] = new Location(0, 0);
			blocks[2] = new Location(0, 1);
			blocks[3] = new Location(0, 2);
			break;
		case 1: // O-shape (square)
			blocks[0] = new Location(0 ,0);
			blocks[1] = new Location(1, 0);
			blocks[2] = new Location(0, 1);
			blocks[3] = new Location(1, 1);
			break;
		case 2: // T-shape
			blocks[0] = new Location(0 ,0);
			blocks[1] = new Location(-1, 0);
			blocks[2] = new Location(1, 0);
			blocks[3] = new Location(0, 1);
			break;
		case 3: // L-shape
			blocks[0] = new Location(0 ,0);
			blocks[1] = new Location(0, 1);
			blocks[2] = new Location(0, 2);
			blocks[3] = new Location(1, 2);
			break;
		case 4: // J-shape (reverse L)
			blocks[0] = new Location(0 ,0);
			blocks[1] = new Location(0, 1);
			blocks[2] = new Location(0, 2);
			blocks[3] = new Location(-1, 2);
			break;
		case 5: // S-shape
			blocks[0] = new Location(0 ,0);
			blocks[1] = new Location(1, 0);
			blocks[2] = new Location(0, 1);
			blocks[3] = new Location(-1, 1);
			break;
		case 6: // Z-shape (reverse S)
			blocks[0] = new Location(0 ,0);
			blocks[1] = new Location(-1, 0);
			blocks[2] = new Location(0, 1);
			blocks[3] = new Location(1, 1);
			break;
		}
	}
	
	public Location[] getAbsoluteBlocks()
	{
		Location[] absolute = new Location[4];
		for (int i = 0; i < 4; i++)
		{
			absolute[i] = new Location(position.x + blocks[i].x, position.y + blocks[i].y);
		}
		return absolute;
	}
	public void translate(int dx, int dy)
	{
		position.x+=dx;
		position.y+=dy;
	}
	public void moveDown()
	{
		translate(0,1);
	}
	public void moveLeft()
	{
		translate(-1,0);
	}
	public void moveRight()
	{
		translate(1,0);
	}
	public void rotateRight()
	{
		for (int i = 0; i < blocks.length; i++)
		{
			int x = blocks[i].x;
			int y = blocks[i].y;
			blocks[i] = new Location(y, -x);
		}
	}
	public void rotateLeft()
	{
		for (int i = 0; i < blocks.length; i++)
		{
			int x = blocks[i].x;
			int y = blocks[i].y;
			blocks[i] = new Location(-y, x);
		}
	}
	public int getX() {
		return position.x;
	}
	public int getY() {
		return position.y;
	}
	public Color getColor()
	{
		return this.color;
	}
}
