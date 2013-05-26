package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookFormMod extends JDialog {

	private JLabel bookIdLabel;
	private JLabel roomsLabel;
	private JLabel checkinLabel;
	private JLabel checkoutLabel;
	private JLabel numpersonLabel;
	private JLabel servicesLabel;
	
	private JTextField bookIdText;
	private JTextField roomsText;
	private JTextField checkinText;
	private JTextField checkoutText;
	private JTextField numpersonText;
	private JTextField servicesText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public BookFormMod(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Modificar reserva");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		bookIdLabel    = new JLabel("ID de la reserva (debe existir)");
		roomsLabel     = new JLabel("Habitaciones");
		checkinLabel   = new JLabel("Fecha de entrada");
		checkoutLabel  = new JLabel("Fecha de salida");
		numpersonLabel = new JLabel("Numero de personas");
		servicesLabel  = new JLabel("Servicios");
		
		bookIdText = new JTextField(20);
		roomsText = new JTextField(20);
		checkinText = new JTextField(20);
		checkoutText = new JTextField(20);
		numpersonText = new JTextField(20);
		servicesText = new JTextField(20);
		
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		addListener();
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(1,2));
		idPanel.add(bookIdLabel);
		idPanel.add(bookIdText);
		
		JPanel roomsPanel = new JPanel();
		roomsPanel.setLayout(new GridLayout(1,2));
		roomsPanel.add(roomsLabel);
		roomsPanel.add(roomsText);
		
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new GridLayout(1,2));
		checkinPanel.add(checkinLabel);
		checkinPanel.add(checkinText);
		
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new GridLayout(1,2));
		checkoutPanel.add(checkoutLabel);
		checkoutPanel.add(checkoutText);
		
		JPanel numpersonPanel = new JPanel();
		numpersonPanel.setLayout(new GridLayout(1,2));
		numpersonPanel.add(numpersonLabel);
		numpersonPanel.add(numpersonText);
		
		JPanel servicesPanel = new JPanel();
		servicesPanel.setLayout(new GridLayout(1,2));
		servicesPanel.add(servicesLabel);
		servicesPanel.add(servicesText);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(acceptButton);
		buttonPanel.add(cancelButton);
		
		this.setLayout(new GridLayout(7, 1, 5, 5));
		this.add(idPanel);
		this.add(roomsPanel);
		this.add(checkinPanel);
		this.add(checkoutPanel);
		this.add(numpersonPanel);
		this.add(servicesPanel);
		this.add(buttonPanel);
		
		this.pack();
	}
	
	private Vector<Integer> parseString(String data) throws NumberFormatException {
		Vector<Integer> v = new Vector<Integer>();
		
		StringTokenizer st = new StringTokenizer(data,"-");
		
		while(st.hasMoreTokens()){
			String room = st.nextToken();
			
			v.add(Integer.valueOf(room));
			
		}
		
		return v;
	}

	private void accept(){
		Vector<Integer> rooms = null;
		Vector<Integer> services = null;
		
		int id;
		try{
			id = Integer.valueOf(bookIdText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+bookIdLabel.getText()+" debe ser un numero entero");
			return;
		}
		
		try{
			rooms = parseString(roomsText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+roomsLabel.getText()+" deben ser numeros enteros separados por -");
			return;
		}
		
		try{
			services = parseString(servicesText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+servicesLabel.getText()+" deben ser numeros enteros separados por -");
			return;
		}
		
		int numperson;
		
		try{
			 numperson = Integer.valueOf(numpersonText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+numpersonLabel.getText()+" debe ser un numero entero");
			return;
		}
		
		BookTransfer t = new BookTransfer(id,
										  rooms,
										  -1,//id del cliente, no se modifica
										  checkinText.getText(),
										  checkoutText.getText(),
										  -1,//deposito no se modifica
										  numperson,
										  services,
										  false);//confirmacion no se modifica
		Controller.getInstance().event(Event.MOD_BOOK,t,null);
		exit();
	}
	
	private void exit(){
		bookIdText.setText("");
		roomsText.setText("");
		checkinText.setText("");
		checkoutText.setText("");
		numpersonText.setText("");
		servicesText.setText("");
		this.setVisible(false);
	}
	
	private void addListener() {
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
