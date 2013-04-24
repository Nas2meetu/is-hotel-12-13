package is.hotelzargo.presentacion.gui;

import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ClientFormAdd extends JDialog {
	
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel dniLabel;
	private JLabel phoneLabel;
	private JLabel creditCardLabel;
	private JLabel companyLabel;
	private JLabel addressLabel;
	
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField dniText;
	private JTextField phoneText;
	private JTextField creditCardText;
	private JTextField companyText;
	private JTextField addressText;
	
	private JRadioButton companyButton;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public ClientFormAdd(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de alta Cliente");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		nameLabel       = new JLabel("Nombre              ");
		surnameLabel    = new JLabel("Apellidos           ");
		dniLabel        = new JLabel("DNI                 ");
		phoneLabel      = new JLabel("Telefono            ");
		creditCardLabel = new JLabel("Tarjeta de credito  ");
		addressLabel    = new JLabel("Direccion           ");
		companyLabel    = new JLabel("Empresa             ");
		
		/* text */
		nameText = new JTextField(20);
		surnameText = new JTextField(20);
		dniText = new JTextField(20);
		phoneText = new JTextField(20);
		creditCardText = new JTextField(20);
		companyText = new JTextField(20);
		addressText = new JTextField(20);
		
		/* boton empresa */
		companyButton = new JRadioButton("Empresa");
		companyButton.setSelected(false);
		
		/* botones aceptar y cancelar */
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		/* listener */
		addListener();
		
		/* Paneles */
		JPanel radioPanel = new JPanel();
		radioPanel.add(companyButton);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2));
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		
		JPanel surnamePanel = new JPanel();
		surnamePanel.setLayout(new GridLayout(1, 2));
		surnamePanel.add(surnameLabel);
		surnamePanel.add(surnameText);
		
		JPanel dniPanel = new JPanel();
		dniPanel.setLayout(new GridLayout(1, 2));
		dniPanel.add(dniLabel);
		dniPanel.add(dniText);
		
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new GridLayout(1, 2));
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneText);
		
		JPanel creditCardPanel = new JPanel();
		creditCardPanel.setLayout(new GridLayout(1, 2));
		creditCardPanel.add(creditCardLabel);
		creditCardPanel.add(creditCardText);
		
		JPanel companyPanel = new JPanel();
		companyPanel.setLayout(new GridLayout(1, 2));
		companyPanel.add(companyLabel);
		companyPanel.add(companyText);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new GridLayout(1, 2));
		addressPanel.add(addressLabel);
		addressPanel.add(addressText);
		
		JPanel acPanel = new JPanel();
		acPanel.setLayout(new GridLayout(1, 2));
		acPanel.add(acceptButton);
		acPanel.add(cancelButton);
		
		this.setLayout(new GridLayout(9, 1, 5, 5));
		this.add(radioPanel);
		this.add(namePanel);
		this.add(surnamePanel);
		this.add(dniPanel);
		this.add(phonePanel);
		this.add(creditCardPanel);
		this.add(companyPanel);
		this.add(addressPanel);
		this.add(acPanel);
		
		this.pack();
	}
	
	private void accept(){
		
		ClientTransfer t;
		
		if(companyButton.isSelected()){
			t = new ClientTransferCompany(companyText.getText(),
										  dniText.getText(),
										  phoneText.getText(),
										  creditCardText.getText(),
										  addressText.getText());
		}else {
			t = new ClientTransferIndividual(nameText.getText(),
											 surnameText.getText(),
											 dniText.getText(),
											 phoneText.getText(),
											 creditCardText.getText(),
											 addressText.getText());
		}
		
		Controller.getInstance().event(Event.ADD_CLIENT,t);
	}
	
	private void exit(){
		this.setVisible(false);
		companyButton.setSelected(false);
		nameText.setText("");
		surnameText.setText("");
		dniText.setText("");
		phoneText.setText("");
		creditCardText.setText("");
		companyText.setText("");
		addressText.setText("");
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
