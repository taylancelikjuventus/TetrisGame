package TetrisGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame {

	private MyPanel panel ; 
	public static MyShapes shapes;
	
	private int PANELWIDTH = 200;
	private int PANELHEIGHT = 400;
	private int RECTWIDTH = 20;
	private int RECTHEIGHT = 20;
	
	private static JLabel labelscore;
	private JLabel labelExtras1;
	private JLabel labelExtras2;
	private JLabel labelExtras3;
	private static JButton restartButton ;
	
	
	
	public MyFrame() {
          
        	super() ;
    		setTitle("TETRIS GAME");
    		setResizable(false);
    		setLayout(null);
    		setBounds(100, 100, 450, 460);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    setVisible(true);
            
    	    
    	    labelscore = new JLabel("Score : 0" ) ;
    	    labelscore.setBounds(220, 10, 120,20 );
    	    labelscore.setForeground(Color.RED);
    	    //labelscore.setFont(new Font("Ariel",Font.BOLD,20));
    	    add(labelscore);
    	    
    	    labelExtras1 = new JLabel("Press 'p' to pause game");
    	    labelExtras1.setBounds(220, 50, 180,20 );
    	    add(labelExtras1);
    	  
    	    labelExtras2 = new JLabel("Press 'space' to continue");
    	    labelExtras2.setBounds(220, 80, 180,20 );
    	    add(labelExtras2);

    	    labelExtras3 = new JLabel("Press 'r' to restart game");
    	    labelExtras3.setBounds(220, 110, 180,20 );
    	    add(labelExtras3);
    	    
    	    
    	    panel = new MyPanel();
    	    panel.setBounds(10,10,PANELWIDTH,PANELHEIGHT);
    	    panel.setBackground(Color.BLACK);
    	    add(panel);

    	    
    	   //Key events
    	    addKeyListener(new KeyAdapter() {
    	    	
    	    	@Override
    	    	public void keyPressed(KeyEvent e) {
    	    		// TODO Auto-generated method stub
    	    		super.keyPressed(e);
    	    	
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT)  
    	    		      shapes.moveRight(panel.getBoard());
                    if(e.getKeyCode() == KeyEvent.VK_LEFT)  
                    	  shapes.moveLeft(panel.getBoard());
                    if(e.getKeyCode() == KeyEvent.VK_UP)    //rotate
                    	shapes.RotateLeft(panel.getBoard());
                    if(e.getKeyCode() == KeyEvent.VK_DOWN )  //accelarate
                    	panel.getTimer().setDelay(40);
                   
                   
                    if(e.getKeyCode() == KeyEvent.VK_P)       //pause
                          panel.getTimer().stop();     	    	
                    if(e.getKeyCode() == KeyEvent.VK_SPACE)  //continue to play
                    	panel.getTimer().start();     	  
                    if(e.getKeyCode() == KeyEvent.VK_R)  //restart
                        new MyFrame() ;     	  
    	    	
    	    	}
			});
    	    
    	 addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyReleased(KeyEvent e) {
    			// TODO Auto-generated method stub
    			super.keyReleased(e);
    			
    			//slow down
    			 if(e.getKeyCode() == KeyEvent.VK_DOWN )  
                 	panel.getTimer().setDelay(300);	
    			
    		}
		});
    	 
    	
	
	}
	
	public void setShape(MyShapes shapes) {
		this.shapes = shapes;
	}
	
	public MyFrame getFrame() {
		return this;
	}
	
	public static JLabel getScore() {
		return labelscore ;
	}
	
}
