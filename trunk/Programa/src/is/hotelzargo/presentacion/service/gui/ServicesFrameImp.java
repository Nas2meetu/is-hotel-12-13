package is.hotelzargo.presentacion.service.gui;

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
public class ServicesFrameImp extends ServicesFrame {

	private JButton addServicesButton;
	private JButton delServicesButton;
	private JButton modServicesButton;
	private JButton listServicesButton;
	private JButton exit;
	private ImageIcon hotelImage; // imagen hotel
	private JLabel lbHotelIcon; // etiqueta con imagen hotel
	
	private ServicesFormAdd addForm;
	private ServicesFormDel delForm;
	private ServicesFormList listForm;
	private ServicesFormMod modForm;
	
	public ServicesFrameImp() {
		
		this.setTitle("Servicios");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		/* formularios */
		
		addForm = new ServicesFormAdd(this,true);
		delForm = new ServicesFormDel(this,true);
		listForm = new ServicesFormList(this,true);
		modForm = new ServicesFormMod(this,true);
		
		/* botones */
		
		addServicesButton = new JButton("Dar de alta");
		delServicesButton = new JButton("Dar de baja");
		modServicesButton = new JButton("Modificar");
		listServicesButton = new JButton("Listar");
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
		
		btPanel.add(addServicesButton);
		btPanel.add(delServicesButton);
		btPanel.add(modServicesButton);
		btPanel.add(listServicesButton);
		btPanel.add(exit);
		
		mainPanel.setBorder(new TitledBorder("Servicios"));
		mainPanel.add(imagePanel);
		mainPanel.add(btPanel);
		this.add(mainPanel);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	
	private void addServices() {
		addForm.setVisible(true);
	}
	
	private void delServices(){
		delForm.setVisible(true);
	}
	
	private void listServices() {
		listForm.setVisible(true);
	}
	
	private void modServices() {
		modForm.setVisible(true);
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_SERVICES_FRAME,false,null);
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
		
		this.addServicesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addServices();
			}
		});
		
		this.delServicesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delServices();
			}
		});
		
		this.modServicesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modServices();
			}
		});
		
		this.listServicesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listServices();
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
