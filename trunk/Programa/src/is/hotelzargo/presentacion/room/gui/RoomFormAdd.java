package is.hotelzargo.presentacion.room.gui;

import is.hotelzargo.negocio.room.transfer.RoomTransfer;
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
public class RoomFormAdd extends JDialog {

	private JLabel numBedsLabel;
	private JLabel numRoomLabel;
	private JLabel priceLabel;

	private JTextField numBedsText;
	private JTextField numRoomText;
	private JTextField priceText;

	private JButton acceptButton;
	private JButton cancelButton;

	public RoomFormAdd(JFrame owner, boolean mod) {
		super(owner, mod);
		this.setTitle("Dar de alta Habitacion");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);

		/* Labels */
		numBedsLabel = new JLabel("Numero de camas 			");
		numRoomLabel = new JLabel("Numero de habitacion		");
		priceLabel = new JLabel("Precio 					");

		/* text */
		numBedsText = new JTextField(20);
		numRoomText = new JTextField(20);
		priceText = new JTextField(20);

		/* botones aceptar y cancelar */
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");

		/* listener */
		addListener();

		/* Paneles */
		JPanel numBedsPanel = new JPanel();
		numBedsPanel.setLayout(new GridLayout(1, 2));
		numBedsPanel.add(numBedsLabel);
		numBedsPanel.add(numBedsText);

		JPanel numRoomPanel = new JPanel();
		numRoomPanel.setLayout(new GridLayout(1, 2));
		numRoomPanel.add(numRoomLabel);
		numRoomPanel.add(numRoomText);

		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new GridLayout(1, 2));
		pricePanel.add(priceLabel);
		pricePanel.add(priceText);
		
		
		JPanel acPanel = new JPanel();
		acPanel.setLayout(new GridLayout(1, 2));
		acPanel.add(acceptButton);
		acPanel.add(cancelButton);

		this.setLayout(new GridLayout(3, 1, 5, 5));
		this.add(numBedsPanel);
		this.add(numRoomPanel);
		this.add(pricePanel);
		this.add(acPanel);

		this.pack();
	}

	private void accept() {

		RoomTransfer t;

		t = new RoomTransfer(-1, Integer.parseInt(numBedsText.getText()),
				Integer.parseInt(numRoomText.getText()),
				Integer.parseInt(priceText.getText()));

		Controller.getInstance().event(Event.ADD_ROOM, t, null);
	}

	private void exit() {
		this.setVisible(false);
	}

	private void addListener() {
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

		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
	}
}
