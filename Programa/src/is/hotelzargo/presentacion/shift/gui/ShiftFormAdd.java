package is.hotelzargo.presentacion.shift.gui;

import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Time;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ShiftFormAdd extends JDialog {
	
	private JLabel nameLabel;
	private JLabel example;
	private JLabel example2;
	private JLabel checkInLabel;
	private JLabel checkOutLabel;

	
	private JTextField nameText;
	private JTextField checkInText;
	private JTextField checkOutText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public ShiftFormAdd(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de alta turno");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		nameLabel       = new JLabel("Nombre");
		example 		= new JLabel("Ejemplo: hh:mm   09:30");
		example2 		= new JLabel("Ejemplo: hh:mm   11:30");
		checkInLabel    = new JLabel("Hora entrada");
		checkOutLabel   = new JLabel("Hora salida");

		
		/* text */
		nameText = new JTextField(20);
		checkInText = new JTextField(20);
		checkOutText = new JTextField(20);
		
		/* botones aceptar y cancelar */
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		/* listener */
		addListener();
		
		/* Paneles */
//		JPanel radioPanel = new JPanel();
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 1));
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		
		JPanel checkInPanel = new JPanel();
		checkInPanel.setLayout(new GridLayout(1, 2));
		checkInPanel.add(checkInLabel);
		checkInPanel.add(checkInText);
		
		JPanel examplePanel = new JPanel();
		examplePanel.setLayout(new GridLayout(1, 2));
		examplePanel.add(Box.createVerticalGlue());
		examplePanel.add(example);
		
		JPanel checkOutPanel = new JPanel();
		checkOutPanel.setLayout(new GridLayout(1, 2));
		checkOutPanel.add(checkOutLabel);
		checkOutPanel.add(checkOutText);
		
		JPanel examplePanel2 = new JPanel();
		examplePanel2.setLayout(new GridLayout(1, 2));
		examplePanel2.add(Box.createVerticalGlue());
		examplePanel2.add(example2);
		
		JPanel acPanel = new JPanel();
		acPanel.setLayout(new GridLayout(1, 2));
		acPanel.add(acceptButton);
		acPanel.add(cancelButton);
		
		this.setLayout(new GridLayout(6, 1, 5, 5));
//		this.add(radioPanel);
		this.add(namePanel);
		this.add(checkInPanel);
		this.add(examplePanel);
		this.add(checkOutPanel);
		this.add(examplePanel2);
		this.add(acPanel);
		
		this.pack();
	}
	
	private void accept(){
		
		ShiftTransfer t = new ShiftTransfer(-1,nameText.getText(),checkInText.getText(), checkOutText.getText());
		
			
		Controller.getInstance().event(Event.ADD_SHIFT,t,null);
		
		this.setVisible(false);
		
	}
	
	private void exit(){
		this.setVisible(false);
		nameText.setText("");
		checkInText.setText("");
		checkOutText.setText("");
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
		});
		
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
	}

}
