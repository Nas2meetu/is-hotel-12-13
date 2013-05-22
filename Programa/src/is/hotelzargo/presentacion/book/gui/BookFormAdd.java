package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookFormAdd extends JDialog {
	
	private JLabel nameLabel;
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
		this.setTitle("Dar de alta Reserva");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		/* Labels */
		roomsLabel    	= new JLabel("Habitaciones           ");
		clientLabel      = new JLabel("ID Cliente                 ");
		checkInLabel     = new JLabel("Fecha entrada            ");
		checkOutLabel 	= new JLabel("Fecha salida  ");
		depositLabel    	= new JLabel("Fianza           ");
		numpersonLabel   = new JLabel("Número de personas             ");
		servicesLabel    = new JLabel("Servicios             ");
		
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
		
		this.setLayout(new GridLayout(9, 1, 5, 5));
		this.add(roomsPanel);
		this.add(clientPanel);
		this.add(checkInPanel);
		this.add(checkOutPanel);
		this.add(depositPanel);
		this.add(numpersonPanel);
		this.add(servicesPanel);
		this.add(acPanel);
		
		this.pack();
	}
	
	private void accept(){
		
		/*BookTransfer t;
		
		Date dateIn = null;
		Date dateOut = null;
		try {
			dateIn = (Date) new SimpleDateFormat("dd MM yyyy", Locale.FRANCE).parse(checkInText.getText());
			dateOut = (Date) new SimpleDateFormat("dd MM yyyy", Locale.FRANCE).parse(checkOutText.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
			//throw new BookAppServicesException("Problema al parsear formato fecha en findBook");
		}
		//provisional pruebas
		Vector<Integer> r = new Vector<Integer>();
		r.add(2);
		r.add(11);
		Vector<ServiceTransfer> s = new Vector<ServiceTransfer>();
		ServiceTransfer st = new ServiceTransfer(1, "cosas");
		s.add(st);
		

		t = new BookTransfer (-1,r,
										  Integer.parseInt(clientText.getText()),
										  dateIn,
										  dateOut,
										  Float.parseFloat(depositText.getText()),
										  Integer.parseInt(numpersonText.getText()),
										  s,
										  false);*/

		Vector<String> dataBook = new Vector<String>();
		dataBook.add(roomsText.getText());
		dataBook.add(clientText.getText());
		dataBook.add(checkInText.getText());
		dataBook.add(checkOutText.getText());
		dataBook.add(depositText.getText());
		dataBook.add(numpersonText.getText());
		dataBook.add(servicesText.getText());
		//y añadir false en app

		Controller.getInstance().event(Event.ADD_BOOK,dataBook,null);
		
	}
	
	private void exit(){
		this.setVisible(false);
		roomsText.setText("2,3,4,12");
		clientText.setText("ads");
		checkInText.setText("");
		checkOutText.setText("658714298");
		depositText.setText("4444444444444");
		numpersonText.setText("");
		servicesText.setText("didiidididididi");
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
