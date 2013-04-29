package is.hotelzargo.presentacion.gui.shift;

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
public class ShiftFrameImp extends ShiftFrame {

	private JButton addShiftButton;
	private JButton delShiftButton;
	private JButton modShiftButton;
	private JButton listShiftButton;
	private JButton exit;
	
	public ShiftFrameImp() {
		
		this.setTitle("Turnos");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addShiftButton = new JButton("Dar de alta");
		delShiftButton = new JButton("Dar de baja");
		modShiftButton = new JButton("Modificar");
		listShiftButton = new JButton("Listar");
		exit = new JButton("Salir");
		
		setListener();
		
		this.setLayout(new GridLayout(5, 1, 5, 5));
		this.add(addShiftButton);
		this.add(delShiftButton);
		this.add(modShiftButton);
		this.add(listShiftButton);
		this.add(exit);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_SHIFT_FRAME,false);
	}
	
	private void setListener(){
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
		
		this.addShiftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
