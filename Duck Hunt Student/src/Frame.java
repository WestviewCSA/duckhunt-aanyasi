import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	Font bigFont = new Font("Serif", Font.BOLD, 100);
	Font medFont = new Font("Serif", Font.BOLD, 50);
	// create ghist object
	Ghost d = new Ghost();

	
	//make the candy corn disappear when it touches the ground after being shot 
	//make the boxes disappear 
	//make sound
	//change the timer back
	// make next round have less time
	//make the ghost go along the bottom of the screen in front of the bushes when searching for the candy corn
	
	// create candy corn happy
		CandycornHappy1 happyCandy = new CandycornHappy1();
		CandycornGasp gasp = new CandycornGasp();
		
		
	// create bush object
	Bush bush1 = new Bush();
	Bush bush2 = new Bush();

	// create cloud object
//	Cloud cloud1 = new Cloud();

	// create a ground object
	GameBackground ground = new GameBackground("background.png");

	// score related vars and timeer
	int roundTimer = 30;
	int score;
	long time;
	int currRound = 1;
	
	// boolean to draw gasp
	boolean gasp1 = false;

	// initualize any variables, objects etc for the "start" of the game
	public void init() {

		// guess and check size for now

		// random direction timer

		// init the roundtimer and score
		roundTimer = 30;
		score = 0;
		time = 0;

	

		// bush position and other things to initialize about it
		bush2.setXY(-50, 403);
		bush2.setScale(0.8, 0.8);
		bush1.setXY(400, 403);
		bush1.setScale(0.8, 0.8);

		// initialize gasping candycorn
		
		
		happyCandy.setScale(.25, .25);
		happyCandy.setXY(0,0);
		happyCandy.setVx((int)(Math.random()*(10-1)+1));
		happyCandy.setVy((int)(Math.random()*(10-1)+1));
	
		happyCandy.setWidthHeight(250, 250);

		
		//gasp candy
		gasp.setScale(.25, .25);
		gasp.setVx((int)(Math.random()*(10-1)+1));
		gasp.setVy((int)(Math.random()*(10-1)+1));
	
		gasp.setWidthHeight(250, 250);

		 
		// cloud position
//		cloud1.setScale(0.3, 0.3);
//		cloud1.setXY(280, 80);

		d.setScale(0.25, 0.25);

		d.setWidthHeight(300, 300); // mnot needed if not colliding or anythig
		d.setXY(400, 1000); // off screen at bottom at first
		// set background inits
		ground.setScale(1, 0.85);
	}

	public void reset() {
		// stuff to init the dog object

	}
	//initialize vars for next round
	public void nextRound() {
		//resetn round counter
		roundTimer = 30;
		currRound++ ;
		// init the roundtimer and score
		roundTimer -= 5;
		score = 0;
		time = 0;
		
		
		

	//ADDITIONAL OBJECTS SETUP OFF THE SCREEN like -1000y
		// maybe previous characters have vx 0 and vy 0 and off the screen
		//maybe the speed of objects will get faster over time each round ** multipliers of some sort
		
		//int val  = -->
		int randVx = (int)(Math.random() * (4)) +1;
		happyCandy.setVx(randVx + currRound);

		// bush position and other things to initialize about it
		bush2.setXY(-50, 403);
		bush2.setScale(0.8, 0.8);
		bush1.setXY(400, 403);
		bush1.setScale(0.8, 0.8);

		// initialize gasping candycorn
		

		
		
		happyCandy.setScale(.25, .25);
		happyCandy.setXY(0,0);
		happyCandy.setVx(((int)(Math.random()*(10-1)+1))*3);
		happyCandy.setVy(((int)(Math.random()*(10-1)+1))*3);
	
		happyCandy.setWidthHeight(250, 250);

		// cloud position
//		cloud1.setScale(0.3, 0.3);
//		cloud1.setXY(280, 80);

	//	d.setScale(0.1, 0.1);
	//	d.setXY(30, 500);

		// set background inits
		ground.setScale(1, 0.85);
		// re-calibrate your objects
		
		
		
	}

	
	// create bush

	// ground state

	public void paint(Graphics g) {
		super.paintComponent(g);
		
	
	

		// add 16 to thr timr since paint is called every 20 milli seconds
		time += 20;
		

		if (time %1000== 0) { // has it been 1 sec
			roundTimer -= 1;

			if (roundTimer == 0) {
				// what do you do after complete one round
				
				nextRound(); //setup next round
		
				
				
				t.stop(); // stops the timer after this round is over
				 
				
			}

		}
		
		//text for moving to next round
		if(!t.isRunning()) {
			
			g.setFont(medFont);
			g.drawString("Press the space bar for next round", 50, 0);
		}
		
		//updating happy candy position 
		happyCandy.setX(happyCandy.getX() + happyCandy.getVx());
		happyCandy.setY(happyCandy.getY() + happyCandy.getVy());
		
		
		//collision
		if(happyCandy.getX() <0 || happyCandy.getX() > getWidth()-happyCandy.getWidth()) {
			happyCandy.setVx(-(happyCandy.getVx()));
		}
		if(happyCandy.getY() <0 || happyCandy.getY() >getHeight() - happyCandy.getHeight()) {
			happyCandy.setVy(-(happyCandy.getVy()));
		}

		// layer objects as you want to layer them
		// visually
		ground.paint(g);

	//	cloud1.paint(g);

		happyCandy.paint(g);
		d.paint(g); //draw ghost
		// add if statement for drawing candy gasp 
		if(gasp1) {
			
			gasp.setX(happyCandy.getX());
			gasp.setY(happyCandy.getY());
			
			
			gasp.paint(g);
			
		}else{
			
		}
		
		bush1.paint(g);
		bush2.paint(g);


	//	d.paint(g);
		// draw font last so ir goes on top
		g.setFont(bigFont); // draw the round string

		g.drawString("" + this.roundTimer, 200, 100);
		
		
		g.drawString(score + "", 800, 100);
		
		// round text is smaller
				setFont(medFont);
				
				g.drawString("Round " + this.currRound, 50, 760);


		
			
		// logic for resetting the dog position
		// and or making it bouncy bouncy
		
		
		if(happyCandy.getVx() == 0 && happyCandy.getY() >200) // && happyCandy.getY() > 00
		{
			d.setXY(400, 3000);
			d.setVy(-5);// goes up
			
			//candy neds to stop moving!
			happyCandy.setVy(0);
		}
		
		
		if (happyCandy.getY() > 800 || happyCandy.getY() < 0) {
			happyCandy.setVy((int)(Math.random()*(10-1)+1));
			happyCandy.setVy(happyCandy.getVx() * -1);
//			happyCandy.setX(200);
//			happyCandy.setY(150);
		
		} if (happyCandy.getY() > 700 && gasp.getY() >700) {
			gasp.setXY(400, 1000);
			happyCandy.setXY(400, 1000);
		}
		
		if(d.getY() < 450) {
		
			d.setVy(5);
			
		}
//		
//		//if the duck is free falling 
		
//		
		
		if (happyCandy.getY() > 800 || happyCandy.getY() < 0) {
			happyCandy.setVy((int)(Math.random()*(10-1)+1));
			happyCandy.setVy(happyCandy.getVx() * -1);
//			happyCandy.setX(200);
//			happyCandy.setY(150);
		
		} if (happyCandy.getY() > 700 && gasp.getY() >700) {
			gasp.setXY(400, 1000);
			happyCandy.setXY(400, 1000);
		}
		
//	d.setX(happyCandy.getX());
////		d.setY(500); // in case the dog is way in the abyss we must bring it back
//		d.setVy(-3);
		
		
		
		
		
		
		
		
		
		
		

	}

	public static void main(String[] arg) {
		Frame f = new Frame();
	}

	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(1200, 1000));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);

		init(); // call init method to give

	
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	
	//maake timer visible to  other methods
	Timer t = new Timer(16, this);

	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
		// TODO Auto-generated method stub

		Rectangle rMouse = new Rectangle(mouse.getX(), mouse.getY(), 100, 100); // guess and check size for now

		// perform a rectangle collision with the mouse and object
		


		// 2nd rectangle will be for your object aka the candy corn
		Rectangle rMain = new Rectangle(happyCandy.getX(), happyCandy.getY(), happyCandy.getWidth(),
				happyCandy.getHeight());

		// check if theyre colliding
		if (rMouse.intersects(rMain)) {// do the 2 rect intersects
			
			gasp1 = true;
			
			happyCandy.setVx(1);
			happyCandy.setVy(10);
//			happyCandy.setVy(0);
//			happyCandy.setVx(0);
			score = score+1;
		}
		if(happyCandy.getY() > 790) {
			gasp1 = false;
		}
		
		
		d.setX(happyCandy.getX());
		d.setY(1000);
//	d.setY(500); // in case the dog is way in the abyss we must bring it back
	d.setVy(-5);
	
	}
	
	
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		//space bar continues the round
		// if the timer is stopped we can start it agan
		if(arg0.getKeyCode() == 32) {
			//start timer again
			if(!t.isRunning()) {
				t.start();
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
