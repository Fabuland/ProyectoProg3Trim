package proyecto;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bbdd.Conexion;

public class Simulador {

	JTextField textNombreSim1, textNombreSim2;
	JTable tablaSim1, tablaSim2;
	DefaultTableModel modeloSim1, modeloSim2;
	int at1, def1, sta1, at2, def2, sta2;
	String tipo1, tipo2;
	JButton btnBuscarNombre1, btnBuscarNombre2;

	public Simulador() {

	}

	public void añadirSimulador(JPanel sim, JPanel menuP) {

		sim.validate();
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
		
		JLabel pvpLogo = new JLabel(new ImageIcon("src\\proyecto\\pic\\logoSilkRoad.png"));
		pvpLogo.setBounds(420, 0, 140, 120);
		sim.add(pvpLogo);

		textNombreSim1 = new JTextField();
		textNombreSim1.setEditable(true);
		textNombreSim1.setBounds(40, 60, 140, 30);
		sim.add(textNombreSim1);

		btnBuscarNombre1 = new JButton("Elegir");
		btnBuscarNombre1.setBounds(200, 60, 70, 30);
		btnBuscarNombre1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscarYAsignarStats1(textNombreSim1.getText());
				
			}
		});
		sim.add(btnBuscarNombre1);

		modeloSim1 = new DefaultTableModel(1, 4);
		Object[] columnasAt = { "Ataque", "Defensa", "Stamina", "Tipo" };
		modeloSim1.setColumnIdentifiers(columnasAt);

		tablaSim1 = new JTable(modeloSim1);
		tablaSim1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaSim1.setAutoCreateColumnsFromModel(true);
		tablaSim1.setEnabled(false);
		tablaSim1.setRowHeight(30);
		Font fuente = new Font("", 1, 18);
		tablaSim1.setFont(fuente);
		tablaSim1.getTableHeader().setFont(fuente);
		tablaSim1.setBounds(40, 130, 400, 60);
		sim.add(tablaSim1);
		JScrollPane jp = new JScrollPane(tablaSim1);
		jp.setBounds(40, 130, 400, 61);
		jp.setVisible(true);
		jp.setViewportView(tablaSim1);
		sim.add(jp);

	}

	public void buscarYAsignarStats1(String nombre) {
		boolean existe = false;
		ResultSet buscarNombre = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY Nombre");
		try {
			while(buscarNombre.next()) {
				if(nombre.equals(buscarNombre.getString("nombre"))) {
					at1 = buscarNombre.getInt("ataque");
					def1 = buscarNombre.getInt("defensa");
					sta1 = buscarNombre.getInt("stamina");
					tipo1 = buscarNombre.getString("tipo");
					existe = true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(existe == false) {
			JOptionPane.showMessageDialog(null, "El Pokémon no existe");
		}
		
		modeloSim1.setValueAt(at1, 0, 0);
		modeloSim1.setValueAt(def1, 0, 1);
		modeloSim1.setValueAt(sta1, 0, 2);
		modeloSim1.setValueAt(tipo1, 0, 3);
	}

}
