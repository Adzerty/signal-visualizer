package signal.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import signal.codes.Code;
import signal.codes.Manchester;
import signal.codes.ManchesterDiff;
import signal.codes.Miller;
import signal.codes.NRZ;
import signal.codes.NRZI;

public class Fenetre extends JFrame implements KeyListener, ActionListener{
	
	final String[] CODES = { "NRZ","NRZI","MANCHESTER","MANCHESTER DIFFERENTIEL","MILLER"};
	
	private JPanel panelOptions;
	private JPanel panelSignal;
	
	private JComboBox<String> comboCode;
	
	private JTextField txtTrame;
	
	private Code code = new NRZ();
	
	//DP Singleton
	private static Fenetre instance;
	
	public Fenetre() {
		super();
		
		if(Fenetre.instance == null) {
			instance = this;
		}
		
		initComponent();
		initFrame();
	}
	
	private void initFrame() {
		this.setTitle("TP1 - Réseaux - Adrien PESTEL");
		this.setLocationRelativeTo(null);
		this.setSize(500,500);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initComponent() {
		panelOptions = new JPanel();
		panelOptions.setLayout(new BoxLayout(panelOptions,BoxLayout.Y_AXIS));
		
		comboCode = new JComboBox<String>(CODES);
		comboCode.addActionListener(this);
		
		
		txtTrame = new JTextField();
		txtTrame.addKeyListener(this);
		
		panelOptions.add(comboCode);
		panelOptions.add(txtTrame);
		
		panelSignal = new PanelSignal();
		
		add(panelOptions, BorderLayout.NORTH);
		add(panelSignal, BorderLayout.CENTER);
		
		
		panelSignal = new PanelSignal();
		
		
	}
	
	public static Fenetre getInstance() {
		return instance;
	}
	
	public JTextField getTxtTrame() {
		return txtTrame;
	}
	
	public Code getCode() {
		return code;
	}
	
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(! txtTrame.getText().matches("[01]*")) {
			txtTrame.setText(txtTrame.getText().replaceAll("[^01]*",""));
		}
		
		panelSignal.revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch((String)comboCode.getSelectedItem()) {
			case "NRZ": code = new NRZ(); break;
			case "NRZI" : code = new NRZI(); break;
			case "MANCHESTER" : code = new Manchester(); break;
			case "MANCHESTER DIFFERENTIEL" : code = new ManchesterDiff(); break;
			case "MILLER" : code = new Miller(); break;
		}
		
		panelSignal.revalidate();
		repaint();
		
	}
}
