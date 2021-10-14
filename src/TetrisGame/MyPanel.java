package TetrisGame;

/***************** TETRIS GAME ***************************/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import TetrisGame.MyShapes;

public class MyPanel extends JPanel {


	private int PANELWIDTH = 200;
	private int PANELHEIGHT = 400;
	private int RECTWIDTH = 20;
	private int RECTHEIGHT = 20;

	private MyShapes shapes;
	private Point[] currentShape;
	private Color currentColor;

	
	public Board board; // stores occupied positions
	private Timer timer;
	public static boolean isStopped = false; 
	public boolean playstatus = true;
	public int score = 0;
	private int delay = 300;

	public MyPanel() {

		super();
		shapes = new MyShapes();
		board = new Board();

		// timer
		timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(playstatus) //if it is not game over
				updatePosition();
			}
		});

		timer.start();
	}

	public void updatePosition() {
        
		MyFrame.shapes = shapes;

		//if shapes movement is stopped
		if (isStopped ) {
			
			//new shape
			for (int i = 0; i < 4; i++)
				// fill map
				board.setMap(shapes.getCurrentShape()[i].y, shapes.getCurrentShape()[i].x);
			shapes = null;
			shapes = new MyShapes();
			MyFrame.shapes = shapes;
			shapes.setNextShape(); 
			
			//score
			detectFullRow();
			
			//check game over status
			checkGameOver() ;
			
			isStopped = false;
			
			
		}
			
		else { //else move down current shape
           
			shapes.moveDown(board);
	     
		}
		
		repaint();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		renderShape(g);

	}

	public void renderShape(Graphics g) {

		currentShape = shapes.getCurrentShape();
		Point[] points = shapes.getCurrentShape();
		currentColor = shapes.getCurrentColor();

		for (int i = 0; i < points.length; i++) {

			
			g.setColor(currentColor);

			g.fillRect(points[i].x * 20  , points[i].y * 20, RECTWIDTH, RECTHEIGHT);

			g.setColor(Color.GRAY);
			((Graphics2D) g).setStroke(new BasicStroke(2.0f));
			g.drawRect( points[i].x * 20 , points[i].y * 20, RECTWIDTH, RECTHEIGHT);

			
			// draw all occupied squares before.
			board.drawMap(g);

			
		}

		if(playstatus == false) { //render followings if game is over
			
			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,24) );
			g.drawString("Game Over", PANELWIDTH/2-60, PANELHEIGHT/2);
			g.setFont(new Font("Arial",Font.BOLD,12) );
			g.drawString("Press 'R' to restart", PANELWIDTH/2-60, PANELHEIGHT/2+30);
		}
		
		g.dispose();
	}

	public MyShapes getShapes() {
		return this.shapes;
	}
	
	public Board getBoard() {
		return board ;
	}

	public Timer getTimer() {
		return timer;
	}
	
	
	int row = 0;
	int[][]  newMat = new int[20][10];
	
	public void detectFullRow() {
		
		
		for(int i=0 ; i<=19  ; i++)
		{
			int count = 0;
			
			for(int j = 0 ; j <10 ;j++)
			{
				//System.out.print( board.map[i][j] + " ") ;
				if(board.map[i][j] == 1) {
					count++;
				}  
		
				
			}
         
			if(count == 10) {
				  
				  newMat[0] = new int[10];
				  getAboveLines(i);
				  getBelowLines(i);
				  board.map = newMat ;
				 
				  //showNewMatrix(i);       //for checking purpose
				  newMat = new int[20][10]; //clear new matrix
				  
				  score += 10;
				  MyFrame.getScore().setText("Score :"+ score);
			}
		
			
			//System.out.println();
		}
		
	}


	public void getAboveLines(int row) {
		
		/******Above Matrix**/
		
		
		for(int i= 1 ; i <= row  ; i++)
		{
			for(int j = 0 ; j < 10 ;j++)
			{
				newMat[i][j] = board.map[i-1][j];
			}
	
		}
		
	}
	
	public void getBelowLines(int row) {
		
		/******below Matrix******/
	
		for(int i= row+1 ; i < newMat.length  ; i++)
		{
			for(int j = 0 ; j < 10 ;j++)
			{
				newMat[i][j] = board.map[i][j];
			}
			
		}
		
	}

	/*    this method used only for check
	public void showNewMatrix(int row) {
		
		System.out.println("\n******new Matrix******\n");
		
		for(int i= 0 ; i < newMat.length  ; i++)
		{
			for(int j = 0 ; j < 10 ;j++)
			{
			   System.out.print( newMat[i][j] + " ");
			}

			System.out.println();
		}
		
	}
	*/
	
	public void  checkGameOver() {
		
		for(int i = 0  ; i<4  ;i++) {
		if(currentShape[i].y == 1) {
			System.out.println("Game Over");
			playstatus = false;
			timer.stop();
			
		}
		}
		
	}
	
	
	

	
}