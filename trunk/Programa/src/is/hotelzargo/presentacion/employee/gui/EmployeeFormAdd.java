package is.hotelzargo.presentacion.employee.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferServices;
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
public class EmployeeFormAdd extends JDialog {
	
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel dniLabel;
	private JLabel phoneLabel;
	private JLabel passwdLabel;
	private JLabel shiftLabel;
	private JLabel payLabel;
	
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField dniText;
	private JTextField phoneText;
	private JTextField passwdText;
	private JTextField shiftText;
	private JTextField payText;
	
	private JRadioButton adminButton;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public EmployeeFormAdd(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de alta Empleado");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		nameLabel       = new JLabel("Nombre");
		surnameLabel    = new JLabel("Apellidos");
		dniLabel        = new JLabel("DNI");
		phoneLabel      = new JLabel("Telefono");
		passwdLabel     = new JLabel("password");
		shiftLabel      = new JLabel("Turno");
		payLabel        = new JLabel("Sueldo");
		
		/* text */
		nameText = new JTextField(20);
		surnameText = new JTextField(20);
		dniText = new JTextField(20);
		phoneText = new JTextField(20);
		passwdText = new JTextField(20);
		shiftText = new JTextField(20);
		payText = new JTextField(20);
		
		/* boton empresa */
		adminButton = new JRadioButton("Administrador");
		adminButton.setSelected(false);
		
		/* botones aceptar y cancelar */
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		/* listener */
		addListener();
		
		/* Paneles */
		JPanel radioPanel = new JPanel();
		radioPanel.add(adminButton);
		
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
		
		JPanel passPanel = new JPanel();
		passPanel.setLayout(new GridLayout(1, 2));
		passPanel.add(passwdLabel);
		passPanel.add(passwdText);
		
		JPanel shiftPanel = new JPanel();
		shiftPanel.setLayout(new GridLayout(1, 2));
		shiftPanel.add(shiftLabel);
		shiftPanel.add(shiftText);
		
		JPanel payPanel = new JPanel();
		payPanel.setLayout(new GridLayout(1, 2));
		payPanel.add(payLabel);
		payPanel.add(payText);
		
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
		this.add(passPanel);
		this.add(shiftPanel);
		this.add(payPanel);
		this.add(acPanel);
		
		this.pack();
	}
	
	private void accept(){
		
		EmployeeTransfer t;
		
		int shift;
		try{
			shift = Integer.valueOf(shiftText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+shiftLabel.getText()+" debe ser un numero entero");
			return;
		}
		float pay;
		try{
			pay = Float.valueOf(payText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+payLabel.getText()+" debe ser un numero entero");
			return;
		}
		
		//TODO employeeFormAdd se necesita el turno... NO SE NECESITA EL TURNO SE NECESITA EL ID, UN INT
		
		if(adminButton.isSelected()){
			t = new EmployeeTransferAdmin(-1,
										  shift,
										  pay,
										  nameText.getText(),
										  surnameText.getText(),
										  dniText.getText(),
										  phoneText.getText(),
										  passwdText.getText());
		}else {
			t = new EmployeeTransferServices(-1,
					                         shift,
					                         pay,
					                         nameText.getText(),
					                         surnameText.getText(),
					                         dniText.getText(),
					                         phoneText.getText());
		}
		Controller.getInstance().event(Event.ADD_EMPLOYEE,t,null);
	}
	
	private void exit(){
		this.setVisible(false);
		adminButton.setSelected(false);
		nameText.setText("sdf");
		surnameText.setText("fdsa");
		dniText.setText("587496325");
		phoneText.setText("658714298");
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
