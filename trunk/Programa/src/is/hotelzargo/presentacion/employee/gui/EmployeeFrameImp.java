package is.hotelzargo.presentacion.employee.gui;

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
public class EmployeeFrameImp extends EmployeeFrame {

	private JButton addEmployeeButton;
	private JButton delEmployeeButton;
	private JButton modEmployeeButton;
	private JButton listEmployeeButton;
	private JButton exit;
	private ImageIcon hotelImage; // imagen hotel
	private JLabel lbHotelIcon; // etiqueta con imagen hotel
	private JPanel mainPanel;
	
	private EmployeeFormAdd addForm;
	private EmployeeFormDel delForm;
	private EmployeeFormList listForm;
	private EmployeeFormMod modForm;
	
	public EmployeeFrameImp() {
		
		this.setTitle("Empleados");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		
		/* formularios */
		addForm = new EmployeeFormAdd(this,true);
		delForm = new EmployeeFormDel(this,true);
		listForm = new EmployeeFormList(this,true);
		modForm = new EmployeeFormMod(this,true);
		
		addEmployeeButton = new JButton("Dar de alta");
		delEmployeeButton = new JButton("Dar de baja");
		modEmployeeButton = new JButton("Modificar");
		listEmployeeButton = new JButton("Listar");
		exit = new JButton("Salir");
		
		setListener();
		createMenu();
		
		JPanel btPanel = new JPanel();
		JPanel imagePanel = new JPanel();
		JPanel mainPanel = new JPanel();

		btPanel.setLayout(new GridLayout(3, 2, 5, 5));

		String curDir = System.getProperty("user.dir");

		hotelImage = new ImageIcon(curDir + "/res/IconoZargoMini.png");
		lbHotelIcon = new JLabel(hotelImage);
		imagePanel.add(this.lbHotelIcon, BorderLayout.CENTER);
		
		btPanel.add(addEmployeeButton);
		btPanel.add(delEmployeeButton);
		btPanel.add(modEmployeeButton);
		btPanel.add(listEmployeeButton);
		btPanel.add(exit);
		
		mainPanel.setBorder(new TitledBorder("Empleados"));
		mainPanel.add(imagePanel);
		mainPanel.add(btPanel);
		this.add(mainPanel);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_EMPLOYEE_FRAME,false,null);
	}
	
	private void setListener() {
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
		
		this.addEmployeeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addForm.setVisible(true);
			}
		});
		
		this.delEmployeeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delForm.setVisible(true);
			}
		});
		
		this.listEmployeeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listForm.setVisible(true);
			}
		});
		
		this.modEmployeeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modForm.setVisible(true);
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
