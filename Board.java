import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

class Board extends JComponent {

	private static final int SCALE = 16; // number of pixels per square

	private int cols;
	private int rows;
	private Piece activePiece;
	private boolean[][] filled;
	private Color[][] colorGrid;

	public Board(int cols, int rows) {
		super();
		activePiece = new Piece();
		setPreferredSize(new Dimension(cols * SCALE, rows * SCALE));
		this.cols = cols;
		this.rows = rows;
		filled = new boolean[cols][rows];
		colorGrid = new Color[cols][rows];

	}
	private void freezePiece()
	{
		for (Location b : activePiece.getAbsoluteBlocks())
		{
			if (b.x >= 0 && b.x < cols && b.y >=0 && b.y < rows)
			{
				filled[b.x][b.y] = true;
				colorGrid[b.x][b.y] = activePiece.getColor(); 
			}
		}
		clearFullRows();
		activePiece = new Piece();
		
		for (Location b : activePiece.getAbsoluteBlocks())
		{
			if (b.x >= 0 && b.x < cols && b.y >=0 && b.y < rows && filled[b.x][b.y])
			{
				System.out.println("Game Over!");
				javax.swing.JOptionPane.showMessageDialog(this, "Game Over!");
				System.exit(0);
			}
		}
		repaint();
	}
	private void clearFullRows()
	{
		for (int y = rows-1; y>=0; y--)
		{
			boolean full = true;
			for (int x = 0; x < cols; x++)
			{
				if(!filled[x][y])
				{
					full=false;
					break;
				}
			}
			if (full)
			{
				for (int ty = y; ty>0; ty--)
				{
					for (int x = 0; x < cols; x++)
					{
						filled[x][ty] = filled[x][ty - 1];
					}
				}
				for (int x = 0; x < cols; x++)
				{
					filled[x][0] = false;
				}
				y++;
			}
		}
	}
	

	public void paintComponent(Graphics g) {
		// clear the screen with black
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int x = 0; x<cols; x++)
		{
			for (int y=0; y<rows; y++)
			{
				if (filled[x][y])
				{
					g.setColor(colorGrid[x][y]);
					g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
				}
			}
		}
		g.setColor(activePiece.getColor());
		for (Location block : activePiece.getAbsoluteBlocks())
		{
			int pixelX = block.x * SCALE;
			int pixelY = block.y * SCALE;
			g.fillRect(pixelX, pixelY, SCALE, SCALE);
		}

	}

	// Move the active piece down one step. Check for collisions.
	//  Check for complete rows that can be destroyed.
	public void nextTurn() {
		for (Location block: activePiece.getAbsoluteBlocks())
		{
			int newY = block.y + 1;
			if (newY >= rows)
			{
				freezePiece();
				return;
			}
			if (block.y >= 0 && block.x >= 0 && block.x < cols && newY >=0 && newY < rows && filled[block.x][newY])
			{
				freezePiece();
				return;
			}
		}
		activePiece.translate(0,1);
		repaint();
	}

	public void slide(int dx) {
		// TO DO: move the active piece one square in the direction dx
		// (which has a value -1 or 1):

		// request that paintComponent be called again:
		for (Location block : activePiece.getAbsoluteBlocks())
		{
			int newX = block.x + dx;
			if (newX < 0 || newX >= cols) {
				return;
			}
		}
		activePiece.translate(dx, 0);
		repaint();

	}

	public void rotateRight() {
		// TO DO: rotate the active piece to the right:

		activePiece.rotateRight();
		repaint();
	}

	public void rotateLeft() {
		// TO DO: rotate the active piece to the left:
		activePiece.rotateLeft();
		repaint();
	}

}
