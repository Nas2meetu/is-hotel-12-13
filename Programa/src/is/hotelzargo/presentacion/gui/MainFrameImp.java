package is.hotelzargo.presentacion.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

@SuppressWarnings("serial")
public class MainFrameImp extends MainFrame{
	/*
	 * Atributos
	 */
	private JButton shiftButton;// boton de turno
	private JButton clientButton;// boton de cliente
	private JButton employeeButton;//boton de empleado
	private JButton bookButton;//boton de reserva
	private JButton roomButton;//boton de habitacion
	private JButton servicesButton;//boton de servicios
	
	/*
	 * Metodos privados
	 */
	private void showShiftFrame(){

		Controller.getInstance().event(Event.SHOW_SHIFT_FRAME,true);
	}
	
	private void showClientFrame(){

		Controller.getInstance().event(Event.SHOW_CLIENT_FRAME,true);
	}
	
	private void showRoomFrame(){

		Controller.getInstance().event(Event.SHOW_ROOM_FRAME,true);
	}
	
	private void showEmployeeFrame(){

		Controller.getInstance().event(Event.SHOW_EMPLOYEE_FRAME,true);
	}
	
	private void showBookFrame(){

		Controller.getInstance().event(Event.SHOW_BOOK_FRAME,true);
	}
	
	private void showServicesFrame(){

		Controller.getInstance().event(Event.SHOW_SERVICES_FRAME,true);
	}
	
	/*
	 * Constructora
	 */
	public MainFrameImp(){
		this.setTitle("Hotel Zargo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.shiftButton = new JButton("Turnos");
		this.clientButton = new JButton("Cliente");
		this.employeeButton = new JButton("Empleado");
		this.bookButton = new JButton("Reserva");
		this.roomButton = new JButton("Habitacion");
		this.servicesButton = new JButton("Servicios");
		
		setListener();
		
		this.setLayout(new GridLayout(3, 2, 5, 5));
		this.add(this.shiftButton);
		this.add(this.clientButton);
		this.add(this.employeeButton);
		this.add(this.bookButton);
		this.add(this.roomButton);
		this.add(this.servicesButton);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void setListener(){
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
		
		this.servicesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				showServicesFrame();
			}
		});
		
		this.shiftButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				showShiftFrame();
			}
		});
	}
	
	/* MAIN */
	public static void main(String[] args) {
		MainFrame.getInstance().setVisible(true);
	}
}
