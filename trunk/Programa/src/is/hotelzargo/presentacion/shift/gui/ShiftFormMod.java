package is.hotelzargo.presentacion.shift.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ShiftFormMod extends JDialog {
	
	private JLabel IDLabel;
	private JLabel nameLabel;
	private JLabel checkInLabel;
	private JLabel checkOutLabel;
	
	private JTextField IDText;
	private JTextField nameText;
	private JTextField checkInText;
	private JTextField checkOutText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public ShiftFormMod(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Modificar Turno");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		IDLabel         = new JLabel("ID Servicio");
		nameLabel       = new JLabel("Nuevo nombre turno");
		checkInLabel    = new JLabel("Nueva hora entrada");
		checkOutLabel   = new JLabel("Nueva hora salida");
		
		/* text */
		IDText = new JTextField(20);
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
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(1,2));
		idPanel.add(IDLabel);
		idPanel.add(IDText);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2));
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
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
		
		this.setLayout(new GridLayout(10, 1, 5, 5));
		this.add(radioPanel);
		this.add(idPanel);
		this.add(namePanel);
		this.add(checkInPanel);
		this.add(checkOutPanel);
		this.add(acPanel);
		
		this.pack();
	}
	
	private void exit(){
		this.setVisible(false);
		nameText.setText("");
		checkInText.setText("");
		checkOutText.setText("");
	}
	
	private void accept(){
		
		int id;
		try{
			id = Integer.valueOf(IDText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+IDLabel.getText()+" debe ser un numero entero");
			return;
		}
		
		 ShiftTransfer t = new ShiftTransfer(id,nameText.getText(),checkInText.getText(),checkOutText.getText());
		
		Controller.getInstance().event(Event.MOD_SHIFT,t,null);
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
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
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
	}
}
