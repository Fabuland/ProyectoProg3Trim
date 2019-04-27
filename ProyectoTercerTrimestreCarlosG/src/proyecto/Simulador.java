package proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Simulador {
	
	JTextField textNombreSim1, textNombreSim2;
	JTable tablaSim1, tablaSim2;
	DefaultTableModel modeloSim1, modeloSim2;
	
	public Simulador() {
		
	}
	
	public void añadirSimulador(JPanel sim, JPanel menuP) {
		
		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\proyecto\\pic\\back.png"));
		btnBack.setBounds(10, 10, 40, 20);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sim.setVisible(false);
				menuP.setVisible(true);
			}
		});
		sim.add(btnBack);
		
	}

}
