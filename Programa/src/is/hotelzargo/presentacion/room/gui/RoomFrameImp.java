package is.hotelzargo.presentacion.room.gui;

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
public class RoomFrameImp extends RoomFrame {

	private JButton addRoomButton;
	private JButton delRoomButton;
	private JButton modRoomButton;
	private JButton listRoomButton;
	private JButton exit;
	private ImageIcon hotelImage; // imagen hotel
	private JLabel lbHotelIcon; // etiqueta con imagen hotel
	private JPanel mainPanel;
	
	private RoomFormAdd addForm;
	//TODO Hay que implementar las clases de los formularios, solo esta hecho el de ADD
	//TODO private RoomFormDel delForm;
	//TODO private RoomFormList listForm;
	//TODO private RoomFormMod modForm;
	
	public RoomFrameImp() {
		
		this.setTitle("Habitaciones");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addRoomButton = new JButton("Dar de alta");
		delRoomButton = new JButton("Dar de baja");
		modRoomButton = new JButton("Modificar");
		listRoomButton = new JButton("Listar");
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
		
		btPanel.add(addRoomButton);
		btPanel.add(delRoomButton);
		btPanel.add(modRoomButton);
		btPanel.add(listRoomButton);
		btPanel.add(exit);
		
		mainPanel.setBorder(new TitledBorder("Habitaciones"));
		mainPanel.add(imagePanel);
		mainPanel.add(btPanel);
		this.add(mainPanel);

		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	
	private void addRoom() {
		addForm.setVisible(true);
	}
	
	/* private void delRoom(){
		delForm.setVisible(true);
	}
	
	private void listRoom(){
		listForm.setVisible(true);
	}
	
	private void modRoom(){
		modForm.setVisible(true);
	}*/
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_ROOM_FRAME,false,null);
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
		
		
		this.addRoomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addRoom();
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
