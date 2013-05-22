package is.hotelzargo.presentacion.maingui;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MainFrameImp extends MainFrame {
	/*
	 * Atributos
	 */
	private JButton shiftButton;// boton de turno
	private JButton clientButton;// boton de cliente
	private JButton employeeButton;// boton de empleado
	private JButton bookButton;// boton de reserva
	private JButton roomButton;// boton de habitacion
	private JButton servicesButton;// boton de servicios
	private ImageIcon hotelImage; // imagen hotel
	private JLabel lbHotelIcon; // etiqueta con imagen hotel

	/*
	 * Metodos privados
	 */
	private void showShiftFrame() {

		Controller.getInstance().event(Event.SHOW_SHIFT_FRAME, true,null);
	}

	private void showClientFrame() {

		Controller.getInstance().event(Event.SHOW_CLIENT_FRAME, true,null);
	}

	private void showRoomFrame() {

		Controller.getInstance().event(Event.SHOW_ROOM_FRAME, true,null);
	}

	private void showEmployeeFrame() {

		Controller.getInstance().event(Event.SHOW_EMPLOYEE_FRAME, true,null);
	}

	private void showBookFrame() {

		Controller.getInstance().event(Event.SHOW_BOOK_FRAME, true,null);
	}

	private void showServicesFrame() {

		Controller.getInstance().event(Event.SHOW_SERVICES_FRAME, true,null);
	}
	
	
	/*
	 * Constructora
	 */
	public MainFrameImp() {
		this.setTitle("Hotel Zargo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.shiftButton = new JButton("Turnos");
		this.clientButton = new JButton("Cliente");
		this.employeeButton = new JButton("Empleado");
		this.bookButton = new JButton("Reserva");
		this.roomButton = new JButton("Habitacion");
		this.servicesButton = new JButton("Servicios");
		this.lbHotelIcon = new JLabel(hotelImage);
		createMenu();
		setListener();

		JPanel btPanel = new JPanel();
		JPanel imagePanel = new JPanel();
		JPanel mainPanel = new JPanel();
		btPanel.setLayout(new GridLayout(3,2, 5, 5));

		String curDir = System.getProperty("user.dir");
		
		hotelImage = new ImageIcon(curDir+"/res/IconoZargoMini.png");
		lbHotelIcon = new JLabel(hotelImage);
		imagePanel.add(Box.createVerticalGlue());
		imagePanel.add(this.lbHotelIcon,BorderLayout.CENTER);
		imagePanel.add(Box.createVerticalGlue());
		btPanel.add(this.bookButton);
		btPanel.add(this.clientButton);
		btPanel.add(this.employeeButton);
		btPanel.add(this.roomButton);
		btPanel.add(this.servicesButton);
		btPanel.add(this.shiftButton);
		mainPanel.setBorder(new TitledBorder("Menu principal"));
		mainPanel.add(imagePanel);
		mainPanel.add(btPanel);
		this.add(mainPanel);


		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width / 2 - this.getWidth() / 2,
				d.height / 2 - this.getHeight() / 2);

		this.pack();
	}

	private void setListener() {
		this.clientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showClientFrame();
			}
		});

		this.roomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showRoomFrame();
			}
		});

		this.employeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEmployeeFrame();
			}
		});

		this.bookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBookFrame();
			}
		});

		this.servicesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showServicesFrame();
			}
		});

		this.shiftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showShiftFrame();
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

	/* MAIN */
	public static void main(String[] args) {
		MainFrame.getInstance().setVisible(true);
	}
}
