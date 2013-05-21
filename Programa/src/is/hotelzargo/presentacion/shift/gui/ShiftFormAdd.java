package is.hotelzargo.presentacion.shift.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ShiftFormAdd extends JDialog {
	
	private JLabel nameLabel;
	private JLabel example;
	private JLabel checkInLabel;
	private JLabel checkOutLabel;

	
	private JTextField nameText;
	private JTextField checkInText;
	private JTextField checkOutText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public ShiftFormAdd(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de alta Turno");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		nameLabel       = new JLabel("Nombre              ");
		example 		= new JLabel("hh:mm:ss   09:30:00        ");
		checkInLabel    = new JLabel("Hora entrada           ");
		checkOutLabel   = new JLabel("Hora salida                 ");

		
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
		JPanel radioPanel = new JPanel();
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 1));
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		JPanel examplePanel = new JPanel();
		examplePanel.setLayout(new GridLayout(1, 2));
		examplePanel.add(example);
		
		JPanel checkInPanel = new JPanel();
		checkInPanel.setLayout(new GridLayout(1, 2));
		checkInPanel.add(checkInLabel);
		checkInPanel.add(checkInText);
		
		JPanel checkOutPanel = new JPanel();
		checkOutPanel.setLayout(new GridLayout(1, 2));
		checkOutPanel.add(checkOutLabel);
		checkOutPanel.add(checkOutText);
		
		JPanel acPanel = new JPanel();
		acPanel.setLayout(new GridLayout(1, 2));
		acPanel.add(acceptButton);
		acPanel.add(cancelButton);
		
		this.setLayout(new GridLayout(9, 1, 5, 5));
		this.add(radioPanel);
		this.add(namePanel);
		this.add(examplePanel);
		this.add(checkInPanel);
		this.add(checkOutPanel);
		this.add(acPanel);
		
		this.pack();
	}
	
	private void accept(){
		
		ShiftTransfer t;
		
		//TODO comprobar formato time introducido
		String in = checkInText.getText();
		String out = checkOutText.getText();
		
		Time timeIn = Time.valueOf(in);
		Time timeOut = Time.valueOf(out);

			t = new ShiftTransfer(-1,nameText.getText(),
											 timeIn,
											 timeOut);
			
		Controller.getInstance().event(Event.ADD_SHIFT,t,null);
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
