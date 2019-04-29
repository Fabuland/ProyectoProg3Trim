package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	JLabel nombrePkm1, nombrePkm2, gengar;
	JLabel efectivo1, efectivo2;

	public Simulador() {

	}

	public void a�adirSimulador(JPanel sim, JPanel menuP) {

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
		nombrePkm1.setFont(new Font("NSimSun", Font.BOLD, 25));
		nombrePkm1.setBounds(20, 5, 150, 40);
		nombrePkm1.setForeground(Color.black);
		sim.add(nombrePkm1);

		btnBuscarNombre1 = new JButton("Elegir");
		btnBuscarNombre1.setBounds(200, 60, 70, 30);
		btnBuscarNombre1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existePkm1 = buscarYAsignarStats1(textNombreSim1.getText(), nombrePkm1);
				barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\BarraLlena.png"));
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
		nombrePkm2.setFont(new Font("NSimSun", Font.BOLD, 25));
		nombrePkm2.setBounds(20, 5, 150, 40);
		nombrePkm2.setForeground(Color.black);
		sim.add(nombrePkm2);

		btnBuscarNombre2 = new JButton("Elegir");
		btnBuscarNombre2.setBounds(850, 60, 70, 30);
		btnBuscarNombre2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existePkm2 = buscarYAsignarStats2(textNombreSim2.getText(), nombrePkm2);
				barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\BarraLlena.png"));
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

		gengar = new JLabel();
		gengar.setBounds(440, 240, 80, 80);
		sim.add(gengar);

		JButton btnCombatir = new JButton(new ImageIcon("src\\proyecto\\pic\\fight.png"));
		btnCombatir.setFont(fuente);
		btnCombatir.setBounds(400, 356, 160, 68);
		btnCombatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (existePkm1 && existePkm2) {
					combate();
					gengar.setIcon(new ImageIcon("src\\proyecto\\pic\\gengar.gif"));
				} else {
					JOptionPane.showMessageDialog(null, "Elige 2 Pok�mon para comenzar");
				}
			}
		});
		sim.add(btnCombatir);

		vida1 = new JLabel();
		vida1.setFont(new Font("NSimSun", Font.BOLD, 20));
		vida1.setBounds(260, 405, 150, 30);
		sim.add(vida1);

		vida2 = new JLabel();
		vida2.setFont(new Font("NSimSun", Font.BOLD, 20));
		vida2.setBounds(800, 405, 150, 30);
		sim.add(vida2);

		efectivo1 = new JLabel("");
		efectivo1.setFont(new Font("NSimSun", Font.BOLD, 20));
		efectivo1.setBounds(40, 290, 220, 30);
		sim.add(efectivo1);

		efectivo2 = new JLabel("");
		efectivo2.setFont(new Font("NSimSun", Font.BOLD, 20));
		efectivo2.setBounds(680, 290, 220, 30);
		sim.add(efectivo2);

		barraVida1 = new JLabel(new ImageIcon("src\\proyecto\\pic\\BarraLlena.png")) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				// String s = "xddas";
				// g.drawString(s, 50, 350);
			}
		};
		barraVida1.setBounds(40, 340, 340, 100);
		sim.add(barraVida1);
		barraVida1.add(nombrePkm1);

		barraVida2 = new JLabel(new ImageIcon("src\\proyecto\\pic\\BarraLlena.png")) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				// String s = "xddas";
				// g.drawString(s, 50, 350);
			}
		};
		barraVida2.setBounds(580, 340, 340, 100);
		sim.add(barraVida2);
		barraVida2.add(nombrePkm2);

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
			JOptionPane.showMessageDialog(null, "El Pok�mon no existe");
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
			JOptionPane.showMessageDialog(null, "El Pok�mon no existe");
		}

		return existe;
	}

	public void combate() {

		int da�o1 = da�o(at1, def2);
		int da�o2 = da�o(at2, def1);
		String tipo1 = (String) modeloSim1.getValueAt(0, 3);
		String tipo2 = (String) modeloSim2.getValueAt(0, 3);
		int da�oF1 = da�oPorTipo(tipo1, tipo2, da�o1, efectivo1);
		int da�oF2 = da�oPorTipo(tipo2, tipo1, da�o2, efectivo2);
		Timer time = new Timer(250, null);
		ActionListener listener = new ActionListener() {
			int staRest1 = sta1;
			int staRest2 = sta2;

			public void actionPerformed(ActionEvent e) {
				staRest1 -= da�oF2;
				staRest2 -= da�oF1;
				if (staRest1 < 0) {
					staRest1 = 0;
				} else if (staRest2 < 0) {
					staRest2 = 0;
				}
				vida1.setText(staRest1 + "/" + sta1);
				vida2.setText(staRest2 + "/" + sta2);

				if (staRest1 < ((sta1 / 8) * 7) && staRest1 > (sta1 / 8) * 6) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra78.png"));
				} else if (staRest1 < ((sta1 / 8) * 6) && staRest1 > (sta1 / 8) * 5) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra68.png"));
				} else if (staRest1 < ((sta1 / 8) * 5) && staRest1 > (sta1 / 8) * 4) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra58.png"));
				} else if (staRest1 < ((sta1 / 8) * 4) && staRest1 > (sta1 / 8) * 3) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra48.png"));
				} else if (staRest1 < ((sta1 / 8) * 3) && staRest1 > (sta1 / 8) * 2) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra38.png"));
				} else if (staRest1 < ((sta1 / 8) * 2) && staRest1 > (sta1 / 8) * 1) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra28.png"));
				} else if (staRest1 < ((sta1 / 8) * 1) && staRest1 > 0) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra18.png"));
				} else if (staRest1 == 0) {
					barraVida1.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra08.png"));
				}

				if (staRest2 < ((sta2 / 8) * 7) && staRest2 > (sta2 / 8) * 6) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra78.png"));
				} else if (staRest2 < ((sta2 / 8) * 6) && staRest2 > (sta2 / 8) * 5) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra68.png"));
				} else if (staRest2 < ((sta2 / 8) * 5) && staRest2 > (sta2 / 8) * 4) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra58.png"));
				} else if (staRest2 < ((sta2 / 8) * 4) && staRest2 > (sta2 / 8) * 3) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra48.png"));
				} else if (staRest2 < ((sta2 / 8) * 3) && staRest2 > (sta2 / 8) * 2) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra38.png"));
				} else if (staRest2 < ((sta2 / 8) * 2) && staRest2 > (sta2 / 8) * 1) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra28.png"));
				} else if (staRest2 < ((sta2 / 8) * 1) && staRest2 > 0) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra18.png"));
				} else if (staRest2 == 0) {
					barraVida2.setIcon(new ImageIcon("src\\proyecto\\pic\\Barra08.png"));
				}

				if (staRest1 == 0 || staRest2 == 0) {
					time.stop();
				}

				if (staRest1 == 0) {
					gengar.setIcon(null);
					JOptionPane.showMessageDialog(null, "El ganador es " + nombrePkm2.getText() + "!");
				} else if (staRest2 == 0) {
					gengar.setIcon(null);
					JOptionPane.showMessageDialog(null, "El ganador es " + nombrePkm1.getText() + "!");
				} else if (staRest1 == 0 && staRest2 == 0) {
					System.out.println("empate");
				}

			}

		};

		time.addActionListener(listener);
		time.start();

	}

	public int da�o(int at, int def) {
		int da�o = (at / 15) - (def / 50);
		if (da�o < 1) {
			da�o = 1;
		}
		return da�o;
	}

	public int da�oPorTipo(String tipoAt, String tipoDef, int da�oAt, JLabel efectivo) {

		if (tipoAt.equals("Normal") && (tipoDef.equals("Roca") || tipoDef.equals("Acero"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Normal") && tipoDef.equals("Fantasma")) {
			da�oAt = da�oAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Fuego") && (tipoDef.equals("Planta") || tipoDef.equals("Hielo") || tipoDef.equals("Bicho")
				|| tipoDef.equals("Acero"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Fuego") && (tipoDef.equals("Fuego") || tipoDef.equals("Agua")
				|| tipoDef.equals("Roca") || tipoDef.equals("Dragon"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Agua") && (tipoDef.equals("Fuego") || tipoDef.equals("Tierra") || tipoDef.equals("Roca"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Agua")
				&& (tipoDef.equals("Agua") || tipoDef.equals("Planta") || tipoDef.equals("Dragon"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Electrico") && (tipoDef.equals("Agua") || tipoDef.equals("Volador"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Electrico")
				&& (tipoDef.equals("Electrico") || tipoDef.equals("Planta") || tipoDef.equals("Dragon"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Electrico") && tipoDef.equals("Tierra")) {
			da�oAt = da�oAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Planta") && (tipoDef.equals("Agua") || tipoDef.equals("Tierra") || tipoDef.equals("Roca"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Planta") && (tipoDef.equals("Fuego") || tipoDef.equals("Planta")
				|| tipoDef.equals("Veneno") || tipoDef.equals("Volador") || tipoDef.equals("Bicho")
				|| tipoDef.equals("Dragon") || tipoDef.equals("Acero"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Planta") && (tipoDef.equals("Agua") || tipoDef.equals("Tierra") || tipoDef.equals("Roca"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Planta") && (tipoDef.equals("Fuego") || tipoDef.equals("Planta")
				|| tipoDef.equals("Veneno") || tipoDef.equals("Volador") || tipoDef.equals("Bicho")
				|| tipoDef.equals("Dragon") || tipoDef.equals("Acero"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Hielo") && (tipoDef.equals("Planta") || tipoDef.equals("Tierra") || tipoDef.equals("Volador")
				|| tipoDef.equals("Dragon"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Hielo") && (tipoDef.equals("Fuego") || tipoDef.equals("Agua")
				|| tipoDef.equals("Hielo") || tipoDef.equals("Acero"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Lucha") && (tipoDef.equals("Normal") || tipoDef.equals("Hielo") || tipoDef.equals("Roca")
				|| tipoDef.equals("Siniestro") || tipoDef.equals("Acero"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Lucha") && (tipoDef.equals("Veneno") || tipoDef.equals("Volador")
				|| tipoDef.equals("Psiquico") || tipoDef.equals("Bicho"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Lucha") && tipoDef.equals("Fantasma")) {
			da�oAt = da�oAt / 2;
			efectivo.setText("No es nada efectivo");
		}
		
		if (tipoAt.equals("Veneno") && (tipoDef.equals("Planta"))) {
			da�oAt = da�oAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Veneno") && (tipoDef.equals("Veneno") || tipoDef.equals("Tierra")
				|| tipoDef.equals("Roca") || tipoDef.equals("Fantasma"))) {
			da�oAt = (da�oAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Veneno") && tipoDef.equals("Acero")) {
			da�oAt = da�oAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		return da�oAt;
	}
}
