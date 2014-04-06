/* @author Aditya
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

import javax.swing.JLabel;
/* <applet code="Cal" width=300 height=300> </applet> */

public class TextRecognizer extends Applet{
	//GUI elements
	private JLabel statusBar;
	private final int wdHt=500,wdWt=500;
	//Neural Network Components
	private final int resolution=20;
	private boolean bitmap[][]=new boolean[wdHt+1][wdWt+1];
	private boolean image[][]=new boolean[resolution][resolution];
	public void init() {
		setSize(wdWt, wdHt);
		
		statusBar=new JLabel("default");
		this.add(statusBar,BorderLayout.SOUTH);
		
		HandlerClass handler= new HandlerClass();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}
	private void imresize(boolean bitmap[][]){
		int gridHt=wdHt/resolution,gridWt=wdWt/resolution;
		for(int i=0;i<resolution;++i)
			for(int j=0;j<resolution;++j){
				boolean present=false;
				for(int it=1+i*gridHt;present==false && it<=(i+1)*gridHt;++it)
					for(int jt=1+j*gridWt;jt<=(j+1)*gridWt;++jt){
						if(it<1||it>wdHt||jt<1||jt>wdWt)System.out.println("out");
						//System.out.println(it+" "+jt);
						if(bitmap[it][jt]==true){present=true;break;}
					}
				if(present)image[i][j]=true;
				else image[i][j]=false;
			}
	}
	private class HandlerClass implements 
		MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			statusBar.setText("Drawing@ "+e.getX()+" "+e.getY() );
			bitmap[e.getY()][e.getX()]=true;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			//statusBar.setText("mouse moved");
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			for(int i=0;i<500;++i)
				for(int j=0;j<500;++j)
					bitmap[i][j]=false;
			statusBar.setText("bitmap cleared, Draw a digit");
			setForeground(Color.RED);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			statusBar.setText("Digit successfully Drawn");
			imresize(bitmap);
			Writefile f=new Writefile();
			f.openFile("D:/Study/Courses/Machine Learning Andrew Ng/project/ex.txt");
			f.addImage(image, resolution, resolution);
			statusBar.setText("Digit image matrix stored in ex.txt");
		}	
	}
}
