package is.hotelzargo.presentacion.client.gui;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ClientFrameImp extends ClientFrame {

	private JButton addClientButton;
	private JButton delClientButton;
	private JButton modClientButton;
	private JButton listClientButton;
	private JButton exit;
	private ImageIcon hotelImage; // imagen hotel
	private JLabel lbHotelIcon; // etiqueta con imagen hotel
	private JPanel mainPanel;

	
	private ClientFormAdd addForm;
	private ClientFormDel delForm;
	private ClientFormList listForm;
	private ClientFormMod modForm;
	
	public ClientFrameImp() {
		
		this.setTitle("Clientes");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		/* formularios */
		
		addForm = new ClientFormAdd(this,true);
		delForm = new ClientFormDel(this,true);
		listForm = new ClientFormList(this,true);
		modForm = new ClientFormMod(this,true);
		
		/* botones */
		
		addClientButton = new JButton("Dar de alta");
		delClientButton = new JButton("Dar de baja");
		modClientButton = new JButton("Modificar");
		listClientButton = new JButton("Listar");
		exit = new JButton("Salir");
		
		createMenu();
		setListener();
		
		JPanel btPanel = new JPanel();
		JPanel imagePanel = new JPanel();
		JPanel mainPanel = new JPanel();

		btPanel.setLayout(new GridLayout(3, 2, 5, 5));

		String curDir = System.getProperty("user.dir");

		hotelImage = new ImageIcon(curDir + "/res/IconoZargoMini.png");
		lbHotelIcon = new JLabel(hotelImage);
		imagePanel.add(this.lbHotelIcon, BorderLayout.CENTER);
		
		btPanel.add(addClientButton);
		btPanel.add(delClientButton);
		btPanel.add(modClientButton);
		btPanel.add(listClientButton);
		btPanel.add(exit);
		
		mainPanel.setBorder(new TitledBorder("Clientes"));
		mainPanel.add(imagePanel);
		mainPanel.add(btPanel);
		this.add(mainPanel);

		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void addClient() {
		addForm.setVisible(true);
	}
	
	private void delClient(){
		delForm.setVisible(true);
	}
	
	private void listClient() {
		listForm.setVisible(true);
	}
	
	private void modClient() {
		modForm.setVisible(true);
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_CLIENT_FRAME,false,null);
	}
	
	private void setListener(){
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
		
		this.addClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addClient();
			}
		});
		
		this.delClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delClient();
			}
		});
		
		this.listClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listClient();
			}
		});
		
		this.modClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modClient();
			}
		});
	
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}

	private void createMenu() {
		JMenuBar mbMenu = new JMenuBar();
		this.setJMenuBar(mbMenu);
		JMenu mDialogs = new JMenu("Archivo");
		mbMenu.add(mDialogs);
		initializeMenu(mDialogs);
	}

	private void initializeMenu(JMenu mDialogs) {

		JMenuItem mQuit = new JMenuItem("Salir");
		mDialogs.add(mQuit);
		mQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
	}
}
