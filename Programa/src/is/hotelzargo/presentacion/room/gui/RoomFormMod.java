package is.hotelzargo.presentacion.room.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferServices;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RoomFormMod extends JDialog {
	
	private JLabel idLabel;
	private JLabel numBedsLabel;
	private JLabel numRoomLabel;
	private JLabel priceLabel;

	private JTextField idText;
	private JTextField numBedsText;
	private JTextField numRoomText;
	private JTextField priceText;

	private JButton acceptButton;
	private JButton cancelButton;
	
	public RoomFormMod(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Modificar Cliente");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		idLabel      = new JLabel("ID");
		numBedsLabel = new JLabel("Numero de camas");
		numRoomLabel = new JLabel("Numero de habitacion");
		priceLabel   = new JLabel("Precio");

		/* text */
		idText = new JTextField(20);
		numBedsText = new JTextField(20);
		numRoomText = new JTextField(20);
		priceText = new JTextField(20);

		/* botones aceptar y cancelar */
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");

		/* listener */
		addListener();

		/* Paneles */
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(1,2));
		idPanel.add(idLabel);
		idPanel.add(idText);
		
		JPanel numBedsPanel = new JPanel();
		numBedsPanel.setLayout(new GridLayout(1, 2));
		numBedsPanel.add(numBedsLabel);
		numBedsPanel.add(numBedsText);

		JPanel numRoomPanel = new JPanel();
		numRoomPanel.setLayout(new GridLayout(1, 2));
		numRoomPanel.add(numRoomLabel);
		numRoomPanel.add(numRoomText);

		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new GridLayout(1, 2));
		pricePanel.add(priceLabel);
		pricePanel.add(priceText);
		
		
		JPanel acPanel = new JPanel();
		acPanel.setLayout(new GridLayout(1, 2));
		acPanel.add(acceptButton);
		acPanel.add(cancelButton);

		this.setLayout(new GridLayout(4, 1, 5, 5));
		this.add(idPanel);
		this.add(numBedsPanel);
		this.add(numRoomPanel);
		this.add(pricePanel);
		this.add(acPanel);

		this.pack();
	}
	
	private void exit(){
		this.setVisible(false);
	}
	
	private void accept(){
		
		RoomTransfer t;

		int id,numBeds,numRoom;
		float price;
		
		try{
			id = Integer.valueOf(idText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+idLabel.getText()+" debe ser un numero entero");
			return;
		}
		
		try{
			numBeds = Integer.valueOf(numBedsText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+numBedsLabel.getText()+" debe ser un numero entero");
			return;
		}
		try{
			numRoom = Integer.valueOf(numRoomText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+numRoomLabel.getText()+" debe ser un numero entero");
			return;
		}
		try{
			price = Float.valueOf(priceText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+priceLabel.getText()+" debe ser un numero entero");
			return;
		}
		t = new RoomTransfer(id, numBeds,numRoom,price);

		Controller.getInstance().event(Event.MOD_ROOM, t, null);
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
