package proyecto;

import java.awt.Color;
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
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import bbdd.Conexion;

public class Simulador {

	JTextField textNombreSim1, textNombreSim2;
	JTable tablaSim1, tablaSim2;
	DefaultTableModel modeloSim1, modeloSim2;
	int at1, def1, sta1, at2, def2, sta2;
	String tipo1, tipo2;
	JButton btnBuscarNombre1, btnBuscarNombre2;
	Boolean existePkm1, existePkm2;
	JLabel vida1, vida2;
	JLabel barraVida1, barraVida2;
	JLabel nombrePkm1, nombrePkm2;

	public Simulador() {

	}

	public void añadirSimulador(JPanel sim, JPanel menuP) {

		existePkm1 = false;
		existePkm2 = false;

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
		pvpLogo.setBounds(410, 0, 140, 120);
		sim.add(pvpLogo);

		textNombreSim1 = new JTextField();
		textNombreSim1.setEditable(true);
		textNombreSim1.setBounds(40, 60, 140, 30);
		sim.add(textNombreSim1);

		nombrePkm1 = new JLabel("");
		nombrePkm1.setFont(new Font("Impact", Font.BOLD, 44));
		nombrePkm1.setBounds(120, 250, 350, 50);
		nombrePkm1.setForeground(Color.red);
		sim.add(nombrePkm1);

		btnBuscarNombre1 = new JButton("Elegir");
		btnBuscarNombre1.setBounds(200, 60, 70, 30);
		btnBuscarNombre1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existePkm1 = buscarYAsignarStats1(textNombreSim1.getText(), nombrePkm1);
				vida1.setText(sta1 + "/" + sta1);
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

		textNombreSim2 = new JTextField();
		textNombreSim2.setEditable(true);
		textNombreSim2.setBounds(690, 60, 140, 30);
		sim.add(textNombreSim2);

		nombrePkm2 = new JLabel("");
		nombrePkm2.setFont(new Font("Impact", Font.BOLD, 44));
		nombrePkm2.setBounds(600, 250, 350, 50);
		nombrePkm2.setForeground(Color.red);
		sim.add(nombrePkm2);

		btnBuscarNombre2 = new JButton("Elegir");
		btnBuscarNombre2.setBounds(850, 60, 70, 30);
		btnBuscarNombre2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existePkm2 = buscarYAsignarStats2(textNombreSim2.getText(), nombrePkm2);
				vida2.setText(sta2 + "/" + sta2);
			}
		});
		sim.add(btnBuscarNombre2);

		modeloSim2 = new DefaultTableModel(1, 4);
		modeloSim2.setColumnIdentifiers(columnasAt);

		tablaSim2 = new JTable(modeloSim2);
		tablaSim2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaSim2.setAutoCreateColumnsFromModel(true);
		tablaSim2.setEnabled(false);
		tablaSim2.setRowHeight(30);
		tablaSim2.setFont(fuente);
		tablaSim2.getTableHeader().setFont(fuente);
		tablaSim2.setBounds(520, 130, 400, 60);
		sim.add(tablaSim2);
		JScrollPane jp2 = new JScrollPane(tablaSim2);
		jp2.setBounds(520, 130, 400, 61);
		jp2.setVisible(true);
		jp2.setViewportView(tablaSim2);
		sim.add(jp2);

		JButton btnCombatir = new JButton("COMBATIR");
		btnCombatir.setFont(fuente);
		btnCombatir.setBounds(400, 400, 160, 40);
		btnCombatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (existePkm1 && existePkm2) {
					combate();
				} else {
					JOptionPane.showMessageDialog(null, "Elige 2 Pokémon para comenzar");
				}
			}
		});
		sim.add(btnCombatir);

		vida1 = new JLabel();
		vida1.setFont(fuente);
		vida1.setBounds(175, 360, 80, 20);
		sim.add(vida1);

		vida2 = new JLabel();
		vida2.setFont(fuente);
		vida2.setBounds(710, 360, 80, 20);
		sim.add(vida2);

		barraVida1 = new JLabel(new ImageIcon("src\\proyecto\\pic\\Vida1.png"));
		barraVida1.setBounds(40, 400, 340, 40);
		sim.add(barraVida1);
		
		barraVida2 = new JLabel(new ImageIcon("src\\proyecto\\pic\\Vida1.png"));
		barraVida2.setBounds(580, 400, 340, 40);
		sim.add(barraVida2);

	}

	public boolean buscarYAsignarStats1(String nombre, JLabel nombreLbl) {
		boolean existe = false;
		ResultSet buscarNombre = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY Nombre");
		try {
			while (buscarNombre.next()) {
				if (nombre.equals(buscarNombre.getString("nombre"))) {
					at1 = buscarNombre.getInt("ataque");
					def1 = buscarNombre.getInt("defensa");
					sta1 = buscarNombre.getInt("stamina");
					tipo1 = buscarNombre.getString("tipo");
					existe = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (existe == true) {
			nombreLbl.setText(nombre);
			modeloSim1.setValueAt(at1, 0, 0);
			modeloSim1.setValueAt(def1, 0, 1);
			modeloSim1.setValueAt(sta1, 0, 2);
			modeloSim1.setValueAt(tipo1, 0, 3);
		} else if (existe == false) {
			nombreLbl.setText("");
			modeloSim1.setValueAt(null, 0, 0);
			modeloSim1.setValueAt(null, 0, 1);
			modeloSim1.setValueAt(null, 0, 2);
			modeloSim1.setValueAt(null, 0, 3);
			JOptionPane.showMessageDialog(null, "El Pokémon no existe");
		}

		return existe;
	}

	public boolean buscarYAsignarStats2(String nombre, JLabel nombreLbl) {
		boolean existe = false;
		ResultSet buscarNombre = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY Nombre");
		try {
			while (buscarNombre.next()) {
				if (nombre.equals(buscarNombre.getString("nombre"))) {
					at2 = buscarNombre.getInt("ataque");
					def2 = buscarNombre.getInt("defensa");
					sta2 = buscarNombre.getInt("stamina");
					tipo2 = buscarNombre.getString("tipo");
					existe = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (existe == true) {
			nombreLbl.setText(nombre);
			modeloSim2.setValueAt(at2, 0, 0);
			modeloSim2.setValueAt(def2, 0, 1);
			modeloSim2.setValueAt(sta2, 0, 2);
			modeloSim2.setValueAt(tipo2, 0, 3);
		} else if (existe == false) {
			nombreLbl.setText("");
			modeloSim2.setValueAt(null, 0, 0);
			modeloSim2.setValueAt(null, 0, 1);
			modeloSim2.setValueAt(null, 0, 2);
			modeloSim2.setValueAt(null, 0, 3);
			JOptionPane.showMessageDialog(null, "El Pokémon no existe");
		}

		return existe;
	}

	public void combate() {

		int daño1 = at1 / 15;
		int daño2 = at2 / 15;
		Timer time = new Timer(500, null);
		ActionListener listener = new ActionListener() {
			int staRest1 = sta1;
			int staRest2 = sta2;

			public void actionPerformed(ActionEvent e) {
				staRest1 -= daño2;
				staRest2 -= daño1;
				if (staRest1 < 0) {
					staRest1 = 0;
				} else if (staRest2 < 0) {
					staRest2 = 0;
				}
				vida1.setText(staRest1 + "/" + sta1);
				vida2.setText(staRest2 + "/" + sta2);
				
				if(staRest1 < ((sta1/4)*3) && staRest1 > (sta1/2)) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida2.png"));
				}else if(staRest1 < ((sta1/4)*2) && staRest1 > (sta1/4)) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida3.png"));
				}else if(staRest1 < ((sta1/4)) && staRest1 > 0) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida4.png"));
				}else if(staRest1 == 0) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida5.png"));
				}
				
				if(staRest2 < ((sta2/4)*3) && staRest2 > (sta2/2)) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida2.png"));
				}else if(staRest2 < ((sta2/4)*2) && staRest2 > (sta2/4)) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida3.png"));
				}else if(staRest2 < ((sta2/4)) && staRest2 > 0) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida4.png"));
				}else if(staRest2 == 0) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Vida5.png"));
				}

				if (staRest1 == 0 || staRest2 == 0) {
					time.stop();
				}
				
				if(staRest1 == 0) {
					JOptionPane.showMessageDialog(null, "El ganador es "+nombrePkm2.getText()+"!!!");
				}else if(staRest2 == 0) {
					JOptionPane.showMessageDialog(null, "El ganador es "+nombrePkm1.getText()+"!!!");;
				}
			}

		};
		
		

		time.addActionListener(listener);
		time.start();

	}

}
