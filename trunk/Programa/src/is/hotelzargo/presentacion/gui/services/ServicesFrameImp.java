package is.hotelzargo.presentacion.gui.services;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;
import is.hotelzargo.presentacion.gui.client.ClientFormAdd;
import is.hotelzargo.presentacion.gui.client.ClientFormDel;
import is.hotelzargo.presentacion.gui.client.ClientFormList;
import is.hotelzargo.presentacion.gui.client.ClientFormMod;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ServicesFrameImp extends ServicesFrame {

	private JButton addServicesButton;
	private JButton delServicesButton;
	private JButton modServicesButton;
	private JButton listServicesButton;
	private JButton exit;
	
	private ServicesFormAdd addForm;
	private ServicesFormDel delForm;
	private ServicesFormList listForm;
	private ServicesFormMod modForm;
	
	public ServicesFrameImp() {
		
		this.setTitle("Servicios");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
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
		
		setListener();
		
		this.setLayout(new GridLayout(5, 1, 5, 5));
		this.add(addServicesButton);
		this.add(delServicesButton);
		this.add(modServicesButton);
		this.add(listServicesButton);
		this.add(exit);
		
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
}
