package proyecto;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bbdd.Conexion;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Pokemon extends JPanel {

	String idPr, nombrePr, pcPr, ataquePr, defensaPr, staminaPr, tipoPr;
	DefaultTableModel modeloPoke;
	JTable tablaPoke;
	JButton btnAdd;
	// String[] anadFila;

	public Pokemon() {

	}

	public void a�adirPkm(JPanel pkm, JPanel menuP) {

		pkm.validate();
		modeloPoke = new DefaultTableModel();
		Object[] columnas = { "ID", "Nombre", "PC", "Ataque", "Defensa", "Stamina", "Tipo" };
		modeloPoke.setColumnIdentifiers(columnas);
		crearModelo(pkm);

		tablaPoke = new JTable(modeloPoke);
		tablaPoke.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaPoke.setAutoCreateColumnsFromModel(true);
		// tablaId.setEnabled(false);
		tablaPoke.setRowHeight(30);
		Font fuente = new Font("", 1, 18);
		tablaPoke.setFont(fuente);
		tablaPoke.getTableHeader().setFont(fuente);
		tablaPoke.setBounds(60, 110, 824, 360);
		pkm.add(tablaPoke);
		JScrollPane jp = new JScrollPane(tablaPoke);
		jp.setBounds(60, 110, 824, 360);
		jp.setVisible(true);
		jp.setViewportView(tablaPoke);
		add(jp);
		pkm.add(jp);
		pkm.setLayout(null);

		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\proyecto\\pic\\back.png"));
		btnBack.setBounds(10, 10, 40, 20);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pkm.setVisible(false);
				menuP.setVisible(true);
			}
		});
		pkm.add(btnBack);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrdenarPor.setBounds(85, 17, 111, 23);
		pkm.add(lblOrdenarPor);

		JLabel lblAnadir = new JLabel("A�adir nuevo Pokemon:");
		lblAnadir.setBounds(85, 67, 250, 23);
		lblAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		pkm.add(lblAnadir);

		JButton botonAnadir = new JButton(new ImageIcon("src\\proyecto\\pic\\a�adir.jpg"));
		botonAnadir.setBounds(300, 57, 100, 40);
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a�adirPokemon();
			}
		});
		pkm.add(botonAnadir);

		JLabel lblEliminar = new JLabel("Eliminar Pokemon:");
		lblEliminar.setBounds(470, 67, 250, 23);
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		pkm.add(lblEliminar);
		
		JButton botonEliminar = new JButton(new ImageIcon("src\\proyecto\\pic\\eliminar.png"));
		botonEliminar.setBounds(645, 57, 100, 40);
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaPoke.getModel();
				int i = tablaPoke.getSelectedRow();
				String nombrePkmElim = (String) tablaPoke.getValueAt(i, 1);

				eliminarBaseDatos(nombrePkmElim);

				if (i >= 0) {
					modeloPoke.removeRow(i);

				} else {
					System.out.println("error al eliminar");

				}

			}
		});

		pkm.add(botonEliminar);

		JButton btnOrdId = new JButton("ID");
		btnOrdId.setBounds(240, 19, 89, 23);
		btnOrdId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("idPokemon", "ASC");

			}
		});
		pkm.add(btnOrdId);

		JButton btnOrdPc = new JButton("PC");
		btnOrdPc.setBounds(370, 19, 89, 23);
		btnOrdPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("pc", "DESC");

			}
		});
		pkm.add(btnOrdPc);

		JButton btnOrdAtaque = new JButton("ATAQUE");
		btnOrdAtaque.setBounds(500, 19, 89, 23);
		btnOrdAtaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("ataque", "DESC");

			}
		});
		pkm.add(btnOrdAtaque);

		JButton btnOrdDefensa = new JButton("DEFENSA");
		btnOrdDefensa.setBounds(630, 19, 89, 23);
		btnOrdDefensa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("defensa", "DESC");

			}
		});
		pkm.add(btnOrdDefensa);

		JButton btnOrdStamina = new JButton("STAMINA");
		btnOrdStamina.setBounds(760, 19, 89, 23);
		btnOrdStamina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("stamina", "DESC");

			}
		});
		pkm.add(btnOrdStamina);

		btnAdd = new JButton();
		btnAdd.setBounds(765, 60, 0, 0);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] nuevFila = new Object[7];
				nuevFila[0] = idPr;
				nuevFila[1] = nombrePr;
				nuevFila[2] = pcPr;
				nuevFila[3] = ataquePr;
				nuevFila[4] = defensaPr;
				nuevFila[5] = staminaPr;
				nuevFila[6] = tipoPr;
				modeloPoke.addRow(nuevFila);
			}
		});
		pkm.add(btnAdd);

	}

	public void crearModelo(JPanel pkm) {

		Object[] fila = new Object[7];
		ResultSet ponerTabla = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY idPokemon");
		try {
			while (ponerTabla.next()) {

				fila[0] = ponerTabla.getString("idPokemon");
				fila[1] = ponerTabla.getString("nombre");
				fila[2] = ponerTabla.getString("pc");
				fila[3] = ponerTabla.getString("ataque");
				fila[4] = ponerTabla.getString("defensa");
				fila[5] = ponerTabla.getString("stamina");
				fila[6] = ponerTabla.getString("tipo");

				modeloPoke.addRow(fila);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void ordenar(String dato, String ascendencia) {

		ResultSet contador = Conexion.EjecutarSentencia("SELECT * FROM pokemon");
		int counter = 0;

		try {
			while (contador.next()) {
				counter++;

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet datos = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY " + dato + " " + ascendencia);
		borrarFilas(counter);
		Object[] nuevaFila = new Object[7];

		try {
			while (datos.next()) {
				nuevaFila[0] = datos.getString("idPokemon");
				nuevaFila[1] = datos.getString("nombre");
				nuevaFila[2] = datos.getString("pc");
				nuevaFila[3] = datos.getString("ataque");
				nuevaFila[4] = datos.getString("defensa");
				nuevaFila[5] = datos.getString("stamina");
				nuevaFila[6] = datos.getString("tipo");
				modeloPoke.addRow(nuevaFila);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void borrarFilas(int nFilas) {

		for (int i = 0; i < nFilas; i++) {
			modeloPoke.removeRow(0);
		}
	}

	public void a�adirPokemon() {

		// String idPokemon, nombre, pc, ataque, defensa, stamina, tipo;

		JFrame a�adirFrame = new JFrame();
		JPanel contenidoA�adir = new JPanel();
		a�adirFrame.setVisible(true);
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		a�adirFrame.setSize(anchoPantalla / 4, alturaPantalla / 2);
		a�adirFrame.setLocation(anchoPantalla / 2, alturaPantalla / 4);
		a�adirFrame.setTitle("A�adir Pokemon");
		a�adirFrame.setVisible(true);
		contenidoA�adir.setBounds(0, 0, 472, 502);
		contenidoA�adir.setVisible(true);
		contenidoA�adir.setLayout(null);
		a�adirFrame.add(contenidoA�adir);

		JLabel lblIntroduceLosDatos = new JLabel("Introduce los datos del nuevo Pokemon");
		lblIntroduceLosDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIntroduceLosDatos.setBounds(10, 11, 361, 36);
		contenidoA�adir.add(lblIntroduceLosDatos);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(20, 58, 100, 20);
		contenidoA�adir.add(lblId);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(20, 108, 100, 14);
		contenidoA�adir.add(lblNombre);

		JLabel lblPc = new JLabel("PC");
		lblPc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPc.setBounds(20, 158, 100, 14);
		contenidoA�adir.add(lblPc);

		JLabel lblAtaque = new JLabel("Ataque");
		lblAtaque.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAtaque.setBounds(20, 208, 100, 14);
		contenidoA�adir.add(lblAtaque);

		JLabel lblDefensa = new JLabel("Defensa");
		lblDefensa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDefensa.setBounds(20, 258, 100, 14);
		contenidoA�adir.add(lblDefensa);

		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStamina.setBounds(20, 308, 100, 14);
		contenidoA�adir.add(lblStamina);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipo.setBounds(20, 358, 100, 14);
		contenidoA�adir.add(lblTipo);

		JTextField textId = new JTextField();
		textId.setEditable(true);
		textId.setBounds(150, 58, 221, 20);
		contenidoA�adir.add(textId);

		JTextField textNombre = new JTextField();
		textNombre.setEditable(true);
		textNombre.setBounds(150, 107, 221, 20);
		contenidoA�adir.add(textNombre);

		JTextField textPc = new JTextField();
		textPc.setEditable(true);
		textPc.setBounds(150, 157, 221, 20);
		contenidoA�adir.add(textPc);

		JTextField textAtaque = new JTextField();
		textAtaque.setBounds(150, 207, 221, 20);
		contenidoA�adir.add(textAtaque);

		JTextField textDefensa = new JTextField();
		textDefensa.setBounds(150, 257, 221, 20);
		contenidoA�adir.add(textDefensa);

		JTextField textStamina = new JTextField();
		textStamina.setEditable(true);
		textStamina.setBounds(150, 307, 221, 20);
		contenidoA�adir.add(textStamina);

		JTextField textTipo = new JTextField();
		textTipo.setBounds(150, 357, 221, 20);
		contenidoA�adir.add(textTipo);

		/*
		 * idPokemon = textId.getText(); nombre = textNombre.getText(); pc =
		 * textPc.getText(); ataque = textAtaque.getText(); defensa =
		 * textDefensa.getText(); stamina = textStamina.getText(); tipo =
		 * textTipo.getText();
		 */
		Object[] nuevaFila = new String[7];

		nuevaFila[0] = textId.getText();
		nuevaFila[1] = textNombre.getText();
		nuevaFila[2] = textPc.getText();
		nuevaFila[3] = textAtaque.getText();
		nuevaFila[4] = textDefensa.getText();
		nuevaFila[5] = textStamina.getText();
		nuevaFila[6] = textTipo.getText();

		JButton btnA�adirBBDD = new JButton("A�ADIR");
		btnA�adirBBDD.setBounds(215, 425, 90, 30);
		btnA�adirBBDD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// a�adirBaseDatos(nuevaFila[0], nuevaFila[1], nuevaFila[2], nuevaFila[3],
				// nuevaFila[4], nuevaFila[5], nuevaFila[6]);
				a�adirBaseDatos(textId.getText(), textNombre.getText(), textPc.getText(), textAtaque.getText(),
						textDefensa.getText(), textStamina.getText(), textTipo.getText());
				// modelo.addRow(nuevaFila);
				a�adirFrame.setVisible(false);
				newFila(textId.getText(), textNombre.getText(), textPc.getText(), textAtaque.getText(),
						textDefensa.getText(), textStamina.getText(), textTipo.getText());
				btnAdd.doClick();
			}
		});
		contenidoA�adir.add(btnA�adirBBDD);

	}
	
	public void newFila(String a, String b, String c, String d, String e, String f, String g) {
		idPr = a;
		nombrePr = b;
		pcPr = c;
		ataquePr = d;
		defensaPr = e;
		staminaPr = f;
		tipoPr = g;

	}

	public void a�adirBaseDatos(String idPokemon, String nombre, String pc, String ataque, String defensa,
			String stamina, String tipo) {

		Conexion.EjecutarUpdate("INSERT INTO pokemon VALUES (" + idPokemon + ", \"" + nombre + "\", " + pc + ", "
				+ ataque + ", " + defensa + ", " + stamina + ", \"" + tipo + "\");");

	}

	public void eliminarBaseDatos(String nombre) {

		Conexion.EjecutarUpdate("DELETE from pokemon WHERE nombre = \"" + nombre + "\";");

	}
}
