package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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

	JTextField textNombreSim1, textNombreSim2, textNombreEq1, textNombreEq2;
	JTable tablaSim1, tablaSim2;
	DefaultTableModel modeloSim1, modeloSim2;
	int at1, def1, sta1, at2, def2, sta2, numTxt1, numTxt2, staEqRest1, staEqRest2, puntosEq1, puntosEq2;
	String tipo1, tipo2, pkm1, pkm2, ganador;
	JButton btnBuscarNombre1, btnBuscarNombre2, btnCombatir, btnCombatirEquipos, btnRandom, btnPlacebo;
	Boolean existePkm1, existePkm2;
	JLabel vida1, vida2;
	JLabel barraVida1, barraVida2;
	JLabel nombrePkm1, nombrePkm2, gengar, logoTipo1, logoTipo2;
	JLabel efectivo1, efectivo2;
	Scanner reader1, reader2;
	int contLineas = 1;

	public Simulador() {

	}

	/**
	 * Esta funcion crea todos los componentes que se le anaden al panel sim, el cual es el que
	 * se visualiza ahora
	 * 
	 * @param sim panel que se visualiza en este momento
	 * @param menuP panel menu principal que se deja de visualizar
	 */
	public void añadirSimulador(JPanel sim, JPanel menuP) {

		existePkm1 = false;
		existePkm2 = false;

		sim.validate();
		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\pic\\back.png"));
		btnBack.setBounds(10, 10, 40, 20);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sim.setVisible(false);
				menuP.setVisible(true);
			}
		});
		sim.add(btnBack);

		JLabel pvpLogo = new JLabel(new ImageIcon("src\\pic\\logoSilkRoad.png"));
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
				buscarYAsignarStats1(textNombreSim1.getText(), nombrePkm1);
				barraVida1.setIcon(new ImageIcon("src\\pic\\BarraLlena.png"));
				vida1.setText(sta1 + "/" + sta1);
				efectivo1.setText("");
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
		textNombreSim2.setBounds(780, 60, 140, 30);
		sim.add(textNombreSim2);

		nombrePkm2 = new JLabel("");
		nombrePkm2.setFont(new Font("NSimSun", Font.BOLD, 25));
		nombrePkm2.setBounds(20, 5, 150, 40);
		nombrePkm2.setForeground(Color.black);
		sim.add(nombrePkm2);

		btnBuscarNombre2 = new JButton("Elegir");
		btnBuscarNombre2.setBounds(690, 60, 70, 30);
		btnBuscarNombre2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarYAsignarStats2(textNombreSim2.getText(), nombrePkm2);
				barraVida2.setIcon(new ImageIcon("src\\pic\\BarraLlena.png"));
				vida2.setText(sta2 + "/" + sta2);
				efectivo2.setText("");

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
		gengar.setBounds(440, 130, 80, 80);
		sim.add(gengar);

		btnCombatir = new JButton(new ImageIcon("src\\pic\\fight.png"));
		btnCombatir.setBounds(400, 345, 160, 45);
		btnCombatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (existePkm1 && existePkm2) {
					combate();
					gengar.setIcon(new ImageIcon("src\\pic\\gengar.gif"));
				} else {
					JOptionPane.showMessageDialog(null, "Elige 2 Pokémon para comenzar");
				}
			}
		});
		sim.add(btnCombatir);

		btnPlacebo = new JButton();
		btnPlacebo.setBounds(400, 352, 0, 0);
		btnPlacebo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combate();
				gengar.setIcon(new ImageIcon("src\\pic\\gengar.gif"));
			}
		});
		sim.add(btnCombatir);

		btnCombatirEquipos = new JButton(new ImageIcon("src\\pic\\teamfight.png"));
		btnCombatirEquipos.setBounds(420, 230, 120, 30);
		btnCombatirEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numTxt1 = sacarEquipo(textNombreEq1);
				numTxt2 = sacarEquipo(textNombreEq2);

				if ((numTxt1 > 0 && numTxt1 <= 4) && (numTxt2 > 0 && numTxt2 <= 4)) {
					if (existenEquipos(numTxt1, numTxt2)) {
						combatePorEquipos();
					} else {
						JOptionPane.showMessageDialog(null, "Algunos de tus Pokémon no existen");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Elige 2 equipos existentes");
				}

			}
		});
		sim.add(btnCombatirEquipos);

		JButton btnReiniciar = new JButton("Restart");
		btnReiniciar.setBounds(440, 270, 80, 20);
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contLineas = 1;
				puntosEq1 = 0;
				puntosEq2 = 0;
				ganador = "";
				System.out.println(ganador);
			}
		});
		sim.add(btnReiniciar);

		btnRandom = new JButton(new ImageIcon("src\\pic\\random.png"));
		btnRandom.setBounds(440, 395, 80, 40);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomCombat();
			}
		});
		sim.add(btnRandom);

		textNombreEq1 = new JTextField();
		textNombreEq1.setEditable(true);
		textNombreEq1.setBounds(170, 230, 140, 30);
		sim.add(textNombreEq1);

		JLabel lblElegiEq1 = new JLabel("Elige equipo");
		lblElegiEq1.setBounds(40, 230, 130, 30);
		lblElegiEq1.setFont(fuente);
		sim.add(lblElegiEq1);

		textNombreEq2 = new JTextField();
		textNombreEq2.setEditable(true);
		textNombreEq2.setBounds(645, 230, 140, 30);
		sim.add(textNombreEq2);

		JLabel lblElegiEq2 = new JLabel("Elige equipo");
		lblElegiEq2.setBounds(805, 230, 130, 30);
		lblElegiEq2.setFont(fuente);
		sim.add(lblElegiEq2);

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

		logoTipo1 = new JLabel();
		logoTipo1.setBounds(290, 6, 150, 40);
		logoTipo1.setForeground(Color.black);
		sim.add(logoTipo1);

		barraVida1 = new JLabel(new ImageIcon("src\\pic\\BarraLlena.png")) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				// String s = "xddas";
				// g.drawString(s, 50, 350);
			}
		};
		barraVida1.setBounds(40, 340, 340, 100);
		sim.add(barraVida1);
		barraVida1.add(nombrePkm1);
		barraVida1.add(logoTipo1);

		logoTipo2 = new JLabel();
		logoTipo2.setBounds(290, 6, 150, 40);
		logoTipo2.setForeground(Color.black);
		sim.add(logoTipo2);

		barraVida2 = new JLabel(new ImageIcon("src\\pic\\BarraLlena.png")) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				// String s = "xddas";
				// g.drawString(s, 50, 350);
			}
		};
		barraVida2.setBounds(580, 340, 340, 100);
		sim.add(barraVida2);
		barraVida2.add(nombrePkm2);
		barraVida2.add(logoTipo2);

	}

	/**
	 * Busca el nombre del Pokemon y comprueba si existe. Si existe pone sus stats tanto en la tabla de informacion
	 * como en la barra de vida
	 * 
	 * @param nombre nombre del Pokemon elegido
	 * @param nombreLbl Label que cambia el nombre
	 */
	public void buscarYAsignarStats1(String nombre, JLabel nombreLbl) {
		existePkm1 = false;
		ResultSet buscarNombre = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY Nombre");
		try {
			while (buscarNombre.next()) {
				if (nombre.equals(buscarNombre.getString("nombre"))) {
					at1 = buscarNombre.getInt("ataque");
					def1 = buscarNombre.getInt("defensa");
					sta1 = buscarNombre.getInt("stamina");
					tipo1 = buscarNombre.getString("tipo");
					existePkm1 = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (existePkm1 == true) {
			nombreLbl.setText(nombre);
			modeloSim1.setValueAt(at1, 0, 0);
			modeloSim1.setValueAt(def1, 0, 1);
			modeloSim1.setValueAt(sta1, 0, 2);
			modeloSim1.setValueAt(tipo1, 0, 3);
			cambiarLogoTipo(modeloSim1, logoTipo1);
		} else if (existePkm1 == false) {
			nombreLbl.setText("");
			modeloSim1.setValueAt(null, 0, 0);
			modeloSim1.setValueAt(null, 0, 1);
			modeloSim1.setValueAt(null, 0, 2);
			modeloSim1.setValueAt(null, 0, 3);
			logoTipo1.setText(null);
			JOptionPane.showMessageDialog(null, "El Pokémon no existe");
		}
	}

	/**
	 * Busca el nombre del Pokemon y comprueba si existe. Si existe pone sus stats tanto en la tabla de informacion
	 * como en la barra de vida
	 * 
	 * @param nombre nombre del Pokemon elegido
	 * @param nombreLbl Label que cambia el nombre
	 */
	public void buscarYAsignarStats2(String nombre, JLabel nombreLbl) {
		existePkm2 = false;
		ResultSet buscarNombre = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY Nombre");
		try {
			while (buscarNombre.next()) {
				if (nombre.equals(buscarNombre.getString("nombre"))) {
					at2 = buscarNombre.getInt("ataque");
					def2 = buscarNombre.getInt("defensa");
					sta2 = buscarNombre.getInt("stamina");
					tipo2 = buscarNombre.getString("tipo");
					existePkm2 = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (existePkm2 == true) {
			nombreLbl.setText(nombre);
			modeloSim2.setValueAt(at2, 0, 0);
			modeloSim2.setValueAt(def2, 0, 1);
			modeloSim2.setValueAt(sta2, 0, 2);
			modeloSim2.setValueAt(tipo2, 0, 3);
			cambiarLogoTipo(modeloSim2, logoTipo2);
		} else if (existePkm2 == false) {
			nombreLbl.setText("");
			modeloSim2.setValueAt(null, 0, 0);
			modeloSim2.setValueAt(null, 0, 1);
			modeloSim2.setValueAt(null, 0, 2);
			modeloSim2.setValueAt(null, 0, 3);
			logoTipo2.setText(null);
			JOptionPane.showMessageDialog(null, "El Pokémon no existe");
		}
	}

	/**
	 * Ejecuta el combate entre los Pokemon que se muestran en la barra de vida
	 */
	public void combate() {

		int daño1 = daño(at1, def2);
		int daño2 = daño(at2, def1);
		String tipo1 = (String) modeloSim1.getValueAt(0, 3);
		String tipo2 = (String) modeloSim2.getValueAt(0, 3);
		int dañoF1 = dañoPorTipo(tipo1, tipo2, daño1, efectivo1);
		int dañoF2 = dañoPorTipo(tipo2, tipo1, daño2, efectivo2);
		Timer time = new Timer(250, null);
		ActionListener listener = new ActionListener() {
			int staRest1 = sta1;
			int staRest2 = sta2;

			public void actionPerformed(ActionEvent e) {
				if (time.isRunning()) {
					btnCombatir.setEnabled(false);
					btnPlacebo.setEnabled(false);
					btnCombatirEquipos.setEnabled(false);
					btnRandom.setEnabled(false);
				}
				staRest1 -= dañoF2;
				staRest2 -= dañoF1;
				if (staRest1 < 0) {
					staRest1 = 0;
				} else if (staRest2 < 0) {
					staRest2 = 0;
				}
				vida1.setText(staRest1 + "/" + sta1);
				vida2.setText(staRest2 + "/" + sta2);

				if (staRest1 < ((sta1 / 8) * 7) && staRest1 > (sta1 / 8) * 6) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra78.png"));
				} else if (staRest1 < ((sta1 / 8) * 6) && staRest1 > (sta1 / 8) * 5) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra68.png"));
				} else if (staRest1 < ((sta1 / 8) * 5) && staRest1 > (sta1 / 8) * 4) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra58.png"));
				} else if (staRest1 < ((sta1 / 8) * 4) && staRest1 > (sta1 / 8) * 3) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra48.png"));
				} else if (staRest1 < ((sta1 / 8) * 3) && staRest1 > (sta1 / 8) * 2) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra38.png"));
				} else if (staRest1 < ((sta1 / 8) * 2) && staRest1 > (sta1 / 8) * 1) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra28.png"));
				} else if (staRest1 < ((sta1 / 8) * 1) && staRest1 > 0) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra18.png"));
				} else if (staRest1 == 0) {
					barraVida1.setIcon(new ImageIcon("src\\pic\\Barra08.png"));
				}

				if (staRest2 < ((sta2 / 8) * 7) && staRest2 > (sta2 / 8) * 6) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra78.png"));
				} else if (staRest2 < ((sta2 / 8) * 6) && staRest2 > (sta2 / 8) * 5) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra68.png"));
				} else if (staRest2 < ((sta2 / 8) * 5) && staRest2 > (sta2 / 8) * 4) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra58.png"));
				} else if (staRest2 < ((sta2 / 8) * 4) && staRest2 > (sta2 / 8) * 3) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra48.png"));
				} else if (staRest2 < ((sta2 / 8) * 3) && staRest2 > (sta2 / 8) * 2) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra38.png"));
				} else if (staRest2 < ((sta2 / 8) * 2) && staRest2 > (sta2 / 8) * 1) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra28.png"));
				} else if (staRest2 < ((sta2 / 8) * 1) && staRest2 > 0) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra18.png"));
				} else if (staRest2 == 0) {
					barraVida2.setIcon(new ImageIcon("src\\pic\\Barra08.png"));
				}

				if (staRest1 == 0 || staRest2 == 0) {
					time.stop();
					pkm1 = nombrePkm1.getText();
					pkm2 = nombrePkm2.getText();
					if (staRest1 == 0) {
						ganador = nombrePkm2.getText();

					} else if (staRest2 == 0) {
						ganador = nombrePkm1.getText();

					}

					System.out.println(pkm1 + pkm2 + ganador);
					try {
						registrarBatallas(pkm1, pkm2, ganador);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				if (staRest1 == 0) {
					gengar.setIcon(null);
					JOptionPane.showMessageDialog(null, "El ganador es " + nombrePkm2.getText() + "!");
					btnCombatir.setEnabled(true);
					btnPlacebo.setEnabled(true);
					btnRandom.setEnabled(true);
					btnCombatirEquipos.setEnabled(true);
					ganador = nombrePkm2.getText();
				} else if (staRest2 == 0) {
					gengar.setIcon(null);
					JOptionPane.showMessageDialog(null, "El ganador es " + nombrePkm1.getText() + "!");
					btnCombatir.setEnabled(true);
					btnPlacebo.setEnabled(true);
					btnRandom.setEnabled(true);
					btnCombatirEquipos.setEnabled(true);
					ganador = nombrePkm1.getText();
				} else if (staRest1 == 0 && staRest2 == 0) {
					JOptionPane.showMessageDialog(null, "Empate");
				}

			}

		};

		time.addActionListener(listener);
		time.start();

	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 */
	public void combatePorEquipos() {
		if (contLineas < 7) {
			if (nombrePkm1.getText().equals(ganador)) {
				puntosEq1++;
			} else if (nombrePkm2.getText().equals(ganador)) {
				puntosEq2++;
			}
			barraVida1.setIcon(new ImageIcon("src\\pic\\BarraLlena.png"));
			barraVida2.setIcon(new ImageIcon("src\\pic\\BarraLlena.png"));

			reader1 = crearReader(numTxt1);
			reader2 = crearReader(numTxt2);
			for (int i = 0; i < contLineas; i++) {
				reader1.nextLine();
				reader2.nextLine();
			}
			String pokActual1 = reader1.nextLine();
			buscarYAsignarStats1(pokActual1, nombrePkm1);
			String pokActual2 = reader2.nextLine();
			buscarYAsignarStats2(pokActual2, nombrePkm2);
			btnPlacebo.doClick();
			contLineas++;
			System.out.println(puntosEq1 + "  //  " + puntosEq2);
		} else if ((puntosEq1 + puntosEq2) < 6) {
			if (nombrePkm1.getText().equals(ganador)) {
				puntosEq1++;
			} else if (nombrePkm2.getText().equals(ganador)) {
				puntosEq2++;
			}
			System.out.println(puntosEq1 + "  //  " + puntosEq2);
		}
		if ((puntosEq1 + puntosEq2) == 6) {
			if (puntosEq1 > puntosEq2) {
				JOptionPane.showMessageDialog(null, "Gana el equipo " + textNombreEq1.getText());
			} else if (puntosEq2 > puntosEq1) {
				JOptionPane.showMessageDialog(null, "Gana el equipo " + textNombreEq2.getText());
			} else {
				JOptionPane.showMessageDialog(null, "Empate");
			}
			System.out.println(puntosEq1 + "  //  " + puntosEq2);
		}
	}

	public boolean existenEquipos(int eq1, int eq2) {

		boolean existe1 = true;
		boolean existe2 = true;
		int contBoolean = 0;
		String nombreActual1 = "";
		String nombreActual2 = "";

		reader1 = crearReader(numTxt1);
		reader2 = crearReader(numTxt2);
		reader1.nextLine();
		reader2.nextLine();
		for (int i = 0; i < 6; i++) {
			nombreActual1 = reader1.nextLine();
			nombreActual2 = reader2.nextLine();
			existe1 = buscarPokeBBDD(nombreActual1);
			existe2 = buscarPokeBBDD(nombreActual2);
			if(existe1 == false || existe2 == false) {
				contBoolean++;
			}
		}
		if(contBoolean == 0) {
			return true;
		}else {
			return false;
		}
		

	}
	
	public boolean buscarPokeBBDD(String nombre) {
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
		
		return existe;
	}

	public int sacarEquipo(JTextField nombreField) {

		Scanner reader1 = crearReader(1);
		Scanner reader2 = crearReader(2);
		Scanner reader3 = crearReader(3);
		Scanner reader4 = crearReader(4);
		String nombreEqReader1 = reader1.nextLine();
		String nombreEqReader2 = reader2.nextLine();
		String nombreEqReader3 = reader3.nextLine();
		String nombreEqReader4 = reader4.nextLine();
		if (nombreEqReader1.equals(nombreField.getText())) {

			return 1;
		} else if (nombreEqReader2.equals(nombreField.getText())) {

			return 2;
		} else if (nombreEqReader3.equals(nombreField.getText())) {

			return 3;
		} else if (nombreEqReader4.equals(nombreField.getText())) {

			return 4;
		} else {

			return 0;
		}

	}

	public Scanner crearReader(int n) {

		String ruta = "src/proyecto/EquipoGuardado" + n + ".txt";
		File fichero = new File(ruta);
		Scanner reader = null;
		try {
			reader = new Scanner(fichero);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return reader;
	}

	public int daño(int at, int def) {
		int daño = (at / 15) - (def / 50);
		if (daño < 1) {
			daño = 1;
		}
		return daño;
	}

	public void randomCombat() {

		ResultSet cons1 = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY RAND() LIMIT 1");
		String pok1Random = null;
		try {
			while (cons1.next()) {
				pok1Random = cons1.getString("nombre");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ResultSet cons2 = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY RAND() LIMIT 1");
		String pok2Random = "";
		try {
			while (cons2.next()) {
				pok2Random = cons2.getString("nombre");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		textNombreSim1.setText(pok1Random);
		textNombreSim2.setText(pok2Random);

		btnBuscarNombre1.doClick();
		btnBuscarNombre2.doClick();

	}

	public void registrarBatallas(String pkm1, String pkm2, String ganador) throws IOException {

		String ruta = "src/proyecto/RegistroCombates.txt";
		File fichero = new File(ruta);
		Scanner reader = new Scanner(fichero);
		FileWriter writer = new FileWriter(ruta, true);
		String combate = pkm1 + " vs " + pkm2 + ". Ganador: " + ganador;
		writer.write(combate);
		writer.write(System.lineSeparator());
		reader.close();
		writer.close();
	}

	public void cambiarLogoTipo(DefaultTableModel modelo, JLabel logo) {
		if (modelo.getValueAt(0, 3).equals("Bicho")) {
			logo.setIcon(new ImageIcon("src\\pic\\bicho.png"));
		} else if (modelo.getValueAt(0, 3).equals("Acero")) {
			logo.setIcon(new ImageIcon("src\\pic\\acero.png"));
		} else if (modelo.getValueAt(0, 3).equals("Fuego")) {
			logo.setIcon(new ImageIcon("src\\pic\\fuego.png"));
		} else if (modelo.getValueAt(0, 3).equals("Agua")) {
			logo.setIcon(new ImageIcon("src\\pic\\agua.png"));
		} else if (modelo.getValueAt(0, 3).equals("Planta")) {
			logo.setIcon(new ImageIcon("src\\pic\\planta.png"));
		} else if (modelo.getValueAt(0, 3).equals("Tierra")) {
			logo.setIcon(new ImageIcon("src\\pic\\tierra.png"));
		} else if (modelo.getValueAt(0, 3).equals("Roca")) {
			logo.setIcon(new ImageIcon("src\\pic\\roca.png"));
		} else if (modelo.getValueAt(0, 3).equals("Electrico")) {
			logo.setIcon(new ImageIcon("src\\pic\\electrico.png"));
		} else if (modelo.getValueAt(0, 3).equals("Volador")) {
			logo.setIcon(new ImageIcon("src\\pic\\volador.png"));
		} else if (modelo.getValueAt(0, 3).equals("Psiquico")) {
			logo.setIcon(new ImageIcon("src\\pic\\psiquico.png"));
		} else if (modelo.getValueAt(0, 3).equals("Veneno")) {
			logo.setIcon(new ImageIcon("src\\pic\\veneno.png"));
		} else if (modelo.getValueAt(0, 3).equals("Lucha")) {
			logo.setIcon(new ImageIcon("src\\pic\\lucha.png"));
		} else if (modelo.getValueAt(0, 3).equals("Normal")) {
			logo.setIcon(new ImageIcon("src\\pic\\normal.png"));
		} else if (modelo.getValueAt(0, 3).equals("Fantasma")) {
			logo.setIcon(new ImageIcon("src\\pic\\fantasma.png"));
		} else if (modelo.getValueAt(0, 3).equals("Dragon")) {
			logo.setIcon(new ImageIcon("src\\pic\\dragon.png"));
		} else if (modelo.getValueAt(0, 3).equals("Hielo")) {
			logo.setIcon(new ImageIcon("src\\pic\\hielo.png"));
		} else if (modelo.getValueAt(0, 3).equals("Siniestro")) {
			logo.setIcon(new ImageIcon("src\\pic\\siniestro.png"));
		}
	}

	public int dañoPorTipo(String tipoAt, String tipoDef, int dañoAt, JLabel efectivo) {

		if (tipoAt.equals("Normal") && (tipoDef.equals("Roca") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Normal") && tipoDef.equals("Fantasma")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Fuego") && (tipoDef.equals("Planta") || tipoDef.equals("Hielo") || tipoDef.equals("Bicho")
				|| tipoDef.equals("Acero"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Fuego") && (tipoDef.equals("Fuego") || tipoDef.equals("Agua")
				|| tipoDef.equals("Roca") || tipoDef.equals("Dragon"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Agua") && (tipoDef.equals("Fuego") || tipoDef.equals("Tierra") || tipoDef.equals("Roca"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Agua")
				&& (tipoDef.equals("Agua") || tipoDef.equals("Planta") || tipoDef.equals("Dragon"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Electrico") && (tipoDef.equals("Agua") || tipoDef.equals("Volador"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Electrico")
				&& (tipoDef.equals("Electrico") || tipoDef.equals("Planta") || tipoDef.equals("Dragon"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Electrico") && tipoDef.equals("Tierra")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Planta") && (tipoDef.equals("Agua") || tipoDef.equals("Tierra") || tipoDef.equals("Roca"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Planta") && (tipoDef.equals("Fuego") || tipoDef.equals("Planta")
				|| tipoDef.equals("Veneno") || tipoDef.equals("Volador") || tipoDef.equals("Bicho")
				|| tipoDef.equals("Dragon") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Planta") && (tipoDef.equals("Agua") || tipoDef.equals("Tierra") || tipoDef.equals("Roca"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Planta") && (tipoDef.equals("Fuego") || tipoDef.equals("Planta")
				|| tipoDef.equals("Veneno") || tipoDef.equals("Volador") || tipoDef.equals("Bicho")
				|| tipoDef.equals("Dragon") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Hielo") && (tipoDef.equals("Planta") || tipoDef.equals("Tierra") || tipoDef.equals("Volador")
				|| tipoDef.equals("Dragon"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Hielo") && (tipoDef.equals("Fuego") || tipoDef.equals("Agua")
				|| tipoDef.equals("Hielo") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Lucha") && (tipoDef.equals("Normal") || tipoDef.equals("Hielo") || tipoDef.equals("Roca")
				|| tipoDef.equals("Siniestro") || tipoDef.equals("Acero"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Lucha") && (tipoDef.equals("Veneno") || tipoDef.equals("Volador")
				|| tipoDef.equals("Psiquico") || tipoDef.equals("Bicho"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Lucha") && tipoDef.equals("Fantasma")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Veneno") && (tipoDef.equals("Planta"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Veneno") && (tipoDef.equals("Veneno") || tipoDef.equals("Tierra")
				|| tipoDef.equals("Roca") || tipoDef.equals("Fantasma"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Veneno") && tipoDef.equals("Acero")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Tierra") && (tipoDef.equals("Fuego") || tipoDef.equals("Electrico")
				|| tipoDef.equals("Veneno") || tipoDef.equals("Roca") || tipoDef.equals("Acero"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Tierra") && (tipoDef.equals("Planta") || tipoDef.equals("Bicho"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Tierra") && tipoDef.equals("Volador")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Volador")
				&& (tipoDef.equals("Planta") || tipoDef.equals("Lucha") || tipoDef.equals("Bicho"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Volador")
				&& (tipoDef.equals("Electrico") || tipoDef.equals("Roca") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Psiquico") && (tipoDef.equals("Lucha") || tipoDef.equals("Veneno"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Psiquico") && (tipoDef.equals("Psiquico") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Psiquico") && tipoDef.equals("Siniestro")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Bicho")
				&& (tipoDef.equals("Planta") || tipoDef.equals("Psiquico") || tipoDef.equals("Siniestro"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Bicho")
				&& (tipoDef.equals("Fuego") || tipoDef.equals("Lucha") || tipoDef.equals("Veneno")
						|| tipoDef.equals("Volador") || tipoDef.equals("Fantasma") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Roca") && (tipoDef.equals("Fuego") || tipoDef.equals("Hielo") || tipoDef.equals("Volador")
				|| tipoDef.equals("Volador"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Roca")
				&& (tipoDef.equals("Lucha") || tipoDef.equals("Tierra") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Fantasma") && (tipoDef.equals("Psiquico") || tipoDef.equals("Fantasma"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Fantasma") && (tipoDef.equals("Siniestro") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		} else if (tipoAt.equals("Fantasma") && tipoDef.equals("Normal")) {
			dañoAt = dañoAt / 2;
			efectivo.setText("No es nada efectivo");
		}

		if (tipoAt.equals("Dragon") && (tipoDef.equals("Dragon"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Dragon") && (tipoDef.equals("Lucha") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Siniestro") && (tipoDef.equals("Psiquico") || tipoDef.equals("Fantasma"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Siniestro")
				&& (tipoDef.equals("Siniestro") || tipoDef.equals("Lucha") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		if (tipoAt.equals("Acero") && (tipoDef.equals("Hielo") || tipoDef.equals("Roca"))) {
			dañoAt = dañoAt * (3 / 2);
			efectivo.setText("Es super efectivo!");
		} else if (tipoAt.equals("Acero") && (tipoDef.equals("Fuego") || tipoDef.equals("Agua")
				|| tipoDef.equals("Electrico") || tipoDef.equals("Acero"))) {
			dañoAt = (dañoAt / 4) * 3;
			efectivo.setText("No es muy efectivo");
		}

		return dañoAt;
	}
}
