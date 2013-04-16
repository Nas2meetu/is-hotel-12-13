package is.hotelzargo.presentacion.gui;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

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
public class RoomFrameImp extends RoomFrame {

	private JButton addRoomButton;
	private JButton delRoomButton;
	private JButton modRoomButton;
	private JButton listRoomButton;
	private JButton exit;
	
	public RoomFrameImp() {
		
		this.setTitle("Habitaciones");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addRoomButton = new JButton("Dar de alta");
		delRoomButton = new JButton("Dar de baja");
		modRoomButton = new JButton("Modificar");
		listRoomButton = new JButton("Listar");
		exit = new JButton("Salir");
		
		setListener();
		
		this.setLayout(new GridLayout(5, 1, 5, 5));
		this.add(addRoomButton);
		this.add(delRoomButton);
		this.add(modRoomButton);
		this.add(listRoomButton);
		this.add(exit);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_ROOM_FRAME,false);
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
		
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}
}
