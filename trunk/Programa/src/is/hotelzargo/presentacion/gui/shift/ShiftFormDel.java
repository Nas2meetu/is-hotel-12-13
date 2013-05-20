package is.hotelzargo.presentacion.gui.shift;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ShiftFormDel extends JDialog {

	private JLabel idLabel;
	
	private JTextField idText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public ShiftFormDel(JFrame owner, boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de baja turno");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		idLabel = new JLabel("ID del turno");
		
		idText = new JTextField(10);
		
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		addListener();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(idLabel);
		panel.add(idText);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(1,2));
		panelButtons.add(acceptButton);
		panelButtons.add(cancelButton);
		
		this.setLayout(new GridLayout(2,1));
		this.add(panel);
		this.add(panelButtons);
		
		this.pack();
	}
	
	private void exit(){
		this.setVisible(false);
		idText.setText("");
	}
	
	private void accept(){
		//paso un int ID
		Controller.getInstance().event(Event.DELETE_SHIFT,Integer.parseInt(idText.getText()),null);
	}
	
	private void addListener(){
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
		
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}
	
}
