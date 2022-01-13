package signal.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

import signal.codes.Code;
import signal.codes.Ligne;

public class PanelSignal extends JPanel {
	
	private final int HEIGHT = 200;
	
	
	private Fenetre f = Fenetre.getInstance();
	private Code code = f.getCode();
	
	
	public PanelSignal() {
		super();
		
		setPreferredSize(new Dimension(HEIGHT,f.getWidth()));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		code = f.getCode();
		
		
		int nbBits = f.getTxtTrame().getText().length()+1;
		int nbSubDivision = nbBits;
		
		
		
		drawGraph(g, nbSubDivision>5?nbSubDivision:5);
		
	}
	
	
	private void drawGraph(Graphics g, int nbSD) {
		Graphics2D g2d = (Graphics2D) g;
		
		float[] dashingPattern1 = {2f, 2f};
		Stroke stroke = new BasicStroke(2f, BasicStroke.CAP_BUTT,
		        BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
		
		
		g2d.setStroke(stroke);
		
		int wSD = getWidth() / nbSD;
		
		for(int i = 0; i<nbSD; i++) {
			g2d.drawLine(wSD * i,0,wSD * i,HEIGHT+50);
		}
		
		stroke = new BasicStroke(0.5f);
		g2d.setStroke(stroke);
		
		for(int i = 0; i<3; i++) {
			g2d.drawLine(0, (i * HEIGHT/2), f.getWidth(), (i * HEIGHT/2));
		}
		
		drawSignal(g2d, wSD);
		drawBits(g2d, wSD);
	}
	
	private void drawSignal(Graphics2D g2d, int wSD) {
		g2d.setColor(Color.RED);
		Stroke stroke = new BasicStroke(2f);
		g2d.setStroke(stroke);
		
		
		ArrayList<Ligne> lignes = code.calculSignal(f.getTxtTrame().getText(), wSD, HEIGHT);

		
		for(Ligne l : lignes) {
			g2d.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
		}
	}
	
	private void drawBits(Graphics2D g2d, int wSD) {
		g2d.setColor(Color.GREEN);
		int cpt = 0;
		
		for(char bit : f.getTxtTrame().getText().toCharArray()) {
			g2d.drawString(""+bit, (cpt*wSD) + (wSD)/2, HEIGHT + 25);
			cpt++;
		}
		
	}

}
