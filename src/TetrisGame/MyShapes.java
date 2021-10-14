package TetrisGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;


public class MyShapes { // Produces new Shapes and has functions for handling rotation,movements.

	public Point[] currentShape;
	private int randNum;

	private Point[][] shapes = {

			// we want rotate around Point(0,1) !
            // we draw each shape on the center of screen so we added 4 to their x positions
			// I
			{ new Point(0+4, 1), new Point(1+4, 1), new Point(2+4, 1), new Point(3+4, 1) },
			// L
			{ new Point(0+4, 1), new Point(1+4, 1), new Point(2+4, 1), new Point(2+4, 2) },
			// J
			{ new Point(0+4, 1), new Point(1+4, 1), new Point(2+4, 1), new Point(2+4, 0) },
			// Square Shape
			{ new Point(0+4, 1), new Point(1+4, 1), new Point(0+4, 2), new Point(1+4, 2) },
			// T Shape
			{ new Point(0+4, 1), new Point(1+4, 1), new Point(1+4, 2), new Point(1+4, 0) },
			// S Shape
			{ new Point(0+4, 2), new Point(1+4, 2), new Point(1+4, 1), new Point(2+4, 1) },
			// Z Shape
			{ new Point(0+4, 1), new Point(1+4, 1), new Point(1+4, 2), new Point(2+4, 2) }

	};

	//we select random color from this array
	private Color currentColor;
	private Color[] colors = { 
			Color.getHSBColor(0.1f, 0.5f, 0.3f), Color.getHSBColor(0.2f, 0.3f, 0.3f),
			Color.getHSBColor(0.3f, 0.6f, 0.3f), Color.getHSBColor(0.4f, 0.7f, 0.3f),
			Color.getHSBColor(0.5f, 0.4f, 0.3f), Color.getHSBColor(0.6f, 0.1f, 0.3f),
			Color.getHSBColor(0.8f, 0.1f, 0.3f) };

	private int RECTWIDTH = 20;
	private int RECTHEIGHT = 20;

	public MyShapes() { // Just updates/provides Shapes coordinates

		setNextShape();

	}

	public void setNextShape() {

		Random rand = new Random();
		randNum = Math.abs(rand.nextInt() % 7);
		// randNum = 0;

		currentShape = shapes[randNum]; // change this!
		currentColor = colors[randNum];

	}

	// Movements
	public void moveDown(Board board) {

		for (int i = 0; i < 4; i++) {
			currentShape[i].y += 1;
		}

		if (board.checkCollision(this)) { // if collided
			for (int i = 0; i < 4; i++) {
				currentShape[i].y -= 1;
			}

			MyPanel.isStopped = true;
		}

	}

	public void moveRight(Board board) { 

		for (int i = 0; i < 4; i++) {
			currentShape[i].x += 1;
		}

		if (board.checkCollision(this)) { // if collided
			for (int i = 0; i < 4; i++)
				currentShape[i].x -= 1;
		}

	}

	public void moveLeft(Board board) {

		for (int i = 0; i < 4; i++) {
			currentShape[i].x -= 1;
		}

		if (board.checkCollision(this)) { // if collided

			for (int i = 0; i < 4; i++)
				currentShape[i].x += 1;
		}

	}
	

	int count = 0;
	int xx, yy;
	
	// Rotate left when key up is pressed
	public void RotateLeft(Board board) {
		
		Point p = currentShape[1]; // rotation axis

		for (int i = 0; i < 4; i++) {

			xx = currentShape[i].x;
			yy = currentShape[i].y;

			// rotated shape
			currentShape[i].x = (-yy + p.x + p.y);
			currentShape[i].y = (xx - p.x + p.y);

		}

		if (board.checkCollision(this)) { // check collision for rotated shape
			
			
			RotateRight(board);
			
			
		}

		
	
	}

	//if collision occurs while rotating rotate shape in opposite direction!
	int xx1, yy1;
	public void RotateRight(Board board) {
	
		Point p = currentShape[1]; // rotation axis

		// Rotation OK!

				for (int i = 0; i < 4; i++) {

					xx1 = currentShape[i].x;
					yy1 = currentShape[i].y;
					
					// rotated shape right
					currentShape[i].x = (yy1 + p.x - p.y);
					currentShape[i].y = (-xx1 + p.x + p.y);

				}
		

		if (board.checkCollision(this)) { // check collision for rotated shape
			
			//System.out.println("collided...") ;
			
			 RotateLeft(board);
			
			
		}

	
	}
	
	// getters and setters
	public Point[] getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Point[] p) {
		currentShape = p;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color color) {
		currentColor = color;
	}
}
