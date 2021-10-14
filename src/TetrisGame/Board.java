package TetrisGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Board {

	public int[][] map;
	public int[][] checkMat;

	// creates a matrix map that is used to find collision detections and
	// when redrawing the board with occupied position in the board.
	public Board() {
		map = new int[20][10];

	}

	// fills matrix map with provided row and column position
	public void setMap(int row, int col) {
		try {
			map[row][col] = 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// returns whether given x and y are occupied in matrix map
	public int getMap(int row, int col) {
		try {
		   return map[row][col];
		}catch (Exception e) {
           return 2;
		}
		
	}

	// draws squares to occupied positions in matrix map
	public void drawMap(Graphics g) {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {

				if (getMap(i, j) == 1) {

					g.setColor(Color.BLUE);
					g.fillRect(j * 20, i * 20, 20, 20);

					g.setColor(Color.GRAY);
					((Graphics2D) g).setStroke(new BasicStroke(2.0f));
					g.drawRect(j * 20, i * 20, 20, 20);
				}
			}
		}
	}

	// detects collision between single square and occupied position in matrix map.
	public boolean checkCollision(MyShapes shapes) {// call this 4 times for each vertices

		int check = 1;
		for (int i = 0; i < 4; i++) {
		
				if (this.getMap(shapes.currentShape[i].y , shapes.currentShape[i].x) == 1) {

					check *= 0;

				}
				if (shapes.currentShape[i].x < 0 || shapes.currentShape[i].x > 9 || shapes.currentShape[i].y >19) {
					check *= 0;

				}
			}

		

		if (check == 0)
			return true; // collision detected
		else
			return false;
	}

}