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

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookFormAdd extends JDialog {
	
	private JLabel roomsLabel;
	private JLabel clientLabel;
	private JLabel checkInLabel;
	private JLabel checkOutLabel;
	private JLabel depositLabel;
	private JLabel numpersonLabel;
	private JLabel servicesLabel;
	
	//TODO pensar como mete el usuario las habitaciones y servicios de una reserva
	//solucion sucia entre comas 
	//solucion mejor crear nuevas ventanitas con estas funcionalidades a añadir
	
	private JTextField roomsText;
	private JTextField clientText;
	private JTextField checkInText;
	private JTextField checkOutText;
	private JTextField depositText;
	private JTextField numpersonText;
	private JTextField servicesText;	
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public BookFormAdd(JFrame owner,boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de alta reserva");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		roomsLabel    		= new JLabel("Habitaciones");
		clientLabel      	= new JLabel("ID Cliente");
		checkInLabel     	= new JLabel("Fecha entrada");
		checkOutLabel 		= new JLabel("Fecha salida");
		depositLabel    	= new JLabel("Fianza");
		numpersonLabel   	= new JLabel("Numero de personas");
		servicesLabel    	= new JLabel("Servicios");
		
		/* text */
		roomsText = new JTextField(20);
		clientText = new JTextField(20);
		checkInText = new JTextField(20);
		checkOutText = new JTextField(20);
		depositText = new JTextField(20);
		numpersonText = new JTextField(20);
		servicesText = new JTextField(20);
		
		/* botones aceptar y cancelar */
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		/* listener */
		addListener();
		
		/* Paneles */		
		JPanel roomsPanel = new JPanel();
		roomsPanel.setLayout(new GridLayout(1, 2));
		roomsPanel.add(roomsLabel);
		roomsPanel.add(roomsText);
		
		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new GridLayout(1, 2));
		clientPanel.add(clientLabel);
		clientPanel.add(clientText);
		
		JPanel checkInPanel = new JPanel();
		checkInPanel.setLayout(new GridLayout(1, 2));
		checkInPanel.add(checkInLabel);
		checkInPanel.add(checkInText);
		
		JPanel checkOutPanel = new JPanel();
		checkOutPanel.setLayout(new GridLayout(1, 2));
		checkOutPanel.add(checkOutLabel);
		checkOutPanel.add(checkOutText);
		
		JPanel depositPanel = new JPanel();
		depositPanel.setLayout(new GridLayout(1, 2));
		depositPanel.add(depositLabel);
		depositPanel.add(depositText);
		
		JPanel numpersonPanel = new JPanel();
		numpersonPanel.setLayout(new GridLayout(1, 2));
		numpersonPanel.add(numpersonLabel);
		numpersonPanel.add(numpersonText);
		
		JPanel servicesPanel = new JPanel();
		servicesPanel.setLayout(new GridLayout(1, 2));
		servicesPanel.add(servicesLabel);
		servicesPanel.add(servicesText);
		
		JPanel acPanel = new JPanel();
		acPanel.setLayout(new GridLayout(1, 2));
		acPanel.add(acceptButton);
		acPanel.add(cancelButton);
		
		/* paneles de labels de info */
		JPanel roomsInfoPanel = new JPanel();
		roomsInfoPanel.setLayout(new GridLayout(1,2));
		roomsInfoPanel.add(Box.createVerticalGlue());
		//roomsInfoPanel.add(new JLabel("Numeros separados por '-'"));
		roomsInfoPanel.add(new JLabel("Ejemplo: 123-456-1253"));
		
		JPanel clientInfoPanel = new JPanel();
		clientInfoPanel.setLayout(new GridLayout(1,2));
		clientInfoPanel.add(Box.createVerticalGlue());
		//clientInfoPanel.add(new JLabel("Numero entero"));
		clientInfoPanel.add(new JLabel("Ejemplo: 143"));
		
		JPanel checkinInfoPanel = new JPanel();
		checkinInfoPanel.setLayout(new GridLayout(1,2));
		checkinInfoPanel.add(Box.createVerticalGlue());
		//checkinInfoPanel.add(new JLabel("Formato dd/mm/yy HH:mm:ss"));
		checkinInfoPanel.add(new JLabel("Ejemplo: 01/06/12 12:00:00"));
		
		JPanel checkoutInfoPanel = new JPanel();
		checkoutInfoPanel.setLayout(new GridLayout(1,2));
		checkoutInfoPanel.add(Box.createVerticalGlue());
		//checkoutInfoPanel.add(new JLabel("Formato dd/mm/yy HH:mm:ss"));
		checkoutInfoPanel.add(new JLabel("Ejemplo: 01/06/12 12:00:00"));
		
		JPanel depositInfoPanel = new JPanel();
		depositInfoPanel.setLayout(new GridLayout(1,2));
		depositInfoPanel.add(Box.createVerticalGlue());
		//depositInfoPanel.add(new JLabel("Numero real"));
		depositInfoPanel.add(new JLabel("Ejemplo: 50.0"));
		
		JPanel numpersonInfoPanel = new JPanel();
		numpersonInfoPanel.setLayout(new GridLayout(1,2));
		numpersonInfoPanel.add(Box.createVerticalGlue());
		//numpersonInfoPanel.add(new JLabel("Numero entero"));
		numpersonInfoPanel.add(new JLabel("Ejemplo: 4"));
		
		JPanel servicesInfoPanel = new JPanel();
		servicesInfoPanel.setLayout(new GridLayout(1,2));
		servicesInfoPanel.add(Box.createVerticalGlue());
		//servicesInfoPanel.add(new JLabel("Numeros separados por '-'"));
		servicesInfoPanel.add(new JLabel("Ejemplo: 43-2-213"));
		
		this.setLayout(new GridLayout(15, 1, 5, 5));
		
		this.add(roomsPanel);
		this.add(roomsInfoPanel);
		this.add(clientPanel);
		this.add(clientInfoPanel);
		
		this.add(checkInPanel);
		this.add(checkinInfoPanel);
		
		this.add(checkOutPanel);
		this.add(checkoutInfoPanel);
		
		this.add(depositPanel);
		this.add(depositInfoPanel);
		
		this.add(numpersonPanel);
		this.add(numpersonInfoPanel);
		
		this.add(servicesPanel);
		this.add(servicesInfoPanel);
		this.add(acPanel);
		
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
		
		float deposit;
		
		try{
			deposit = Float.valueOf(depositText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+depositLabel.getText()+" debe ser numerico");
			return;
		}
		
		int idClient;
		
		try{
			idClient = Integer.valueOf(clientText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+clientLabel.getText()+" debe ser un numero entero");
			return;
		}
		
		BookTransfer t = new BookTransfer(-1,rooms,
										  idClient,
										  checkInText.getText(), 
										  checkOutText.getText(),
										  deposit,
										  numperson,
										  services,
										  false);
		
		Controller.getInstance().event(Event.ADD_BOOK,t,null);
		exit();
	}
	
	private void exit(){
		this.setVisible(false);
		roomsText.setText("2-3-4-12");
		clientText.setText("1");
		checkInText.setText("12/12/12 12:12:12");
		checkOutText.setText("14/12/12 12:12:12");
		depositText.setText("100");
		numpersonText.setText("2");
		servicesText.setText("3-5");
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
