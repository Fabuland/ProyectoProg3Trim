package proyecto;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bbdd.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	JButton btnAdd, btnUpdate;
	// String[] anadFila;

	public Pokemon() {

	}
	
	/**
	 * Esta funcion crea todos los componentes que se le anaden al panel pkm, el cual es el que
	 * se visualiza ahora
	 * 
	 * @param pkm panel que se visualiza en este momento
	 * @param menuP panel menu principal que se deja de visualizar
	 */
	public void añadirPkm(JPanel pkm, JPanel menuP) {

		pkm.validate();
		modeloPoke = new DefaultTableModel();
		Object[] columnas = { "ID", "Nombre", "PC", "Ataque", "Defensa", "Stamina", "Tipo" };
		modeloPoke.setColumnIdentifiers(columnas);
		crearModelo();

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
		JScrollPane scroll = new JScrollPane(tablaPoke);
		scroll.setBounds(60, 110, 824, 360);
		scroll.setVisible(true);
		scroll.setViewportView(tablaPoke);
		add(scroll);
		pkm.add(scroll);
		pkm.setLayout(null);

		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\pic\\back.png"));
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

		JLabel lblAnadir = new JLabel("Añadir:");
		lblAnadir.setBounds(60, 67, 80, 23);
		lblAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		pkm.add(lblAnadir);

		JButton botonAnadir = new JButton(new ImageIcon("src\\pic\\añadir.jpg"));
		botonAnadir.setBounds(140, 57, 100, 40);
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirPokemon();
			}
		});
		pkm.add(botonAnadir);

		JLabel lblEliminar = new JLabel("Eliminar:");
		lblEliminar.setBounds(380, 67, 250, 23);
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		pkm.add(lblEliminar);

		JButton botonEliminar = new JButton(new ImageIcon("src\\pic\\eliminar.png"));
		botonEliminar.setBounds(470, 57, 100, 40);
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaPoke.getModel();
				int i = tablaPoke.getSelectedRow();

				if (i >= 0) {
					String nombrePkmElim = (String) tablaPoke.getValueAt(i, 1);
					modeloPoke.removeRow(i);
					eliminarBaseDatos(nombrePkmElim);

				} else {
					JOptionPane.showMessageDialog(null, "Error al eliminar");

				}

			}
		});

		pkm.add(botonEliminar);

		JLabel lblUpdate = new JLabel("Actualizar:");
		lblUpdate.setBounds(680, 67, 250, 23);
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		pkm.add(lblUpdate);

		btnUpdate = new JButton(new ImageIcon("src\\pic\\update.png"));
		btnUpdate.setBounds(784, 57, 100, 40);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarPokemon();
			}
		});
		pkm.add(btnUpdate);

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
	
	/**
	 * Crea el modelo base de la primera tabla que se crea al entrar en el panel pkm
	 */
	public void crearModelo() {

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

	/**
	 * Cuenta el numero total de filas, las elimina y vuelve a crear la tabla de manera ordenada dependiendo
	 * de los parametros
	 * 
	 * @param dato columna de la tabla sobre la que quieres ordenarla
	 * @param ascendencia ascendente o descendente
	 */
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
	
	/**
	 * Elimina todas las filas de una tabla
	 * 
	 * @param nFilas numero de filas totales
	 */
	public void borrarFilas(int nFilas) {

		for (int i = 0; i < nFilas; i++) {
			modeloPoke.removeRow(0);
		}
	}
	
	/**
	 * Crea un nuevo frame con los componentes necesarios para introducir el tipo de un Pokemon
	 * y actualizarlo tanto en la tabla como en la base de datos
	 * 
	 */
	public void actualizarPokemon() {

		JFrame actualizarFrame = new JFrame();
		JPanel contenidoActualizar = new JPanel();
		actualizarFrame.setVisible(true);
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		actualizarFrame.setSize(anchoPantalla / 4, alturaPantalla / 2);
		actualizarFrame.setLocation(anchoPantalla / 4, alturaPantalla / 4);
		actualizarFrame.setTitle("Actualizar tipo");
		actualizarFrame.setVisible(true);
		contenidoActualizar.setBounds(0, 0, 472, 502);
		contenidoActualizar.setVisible(true);
		contenidoActualizar.setLayout(null);
		actualizarFrame.add(contenidoActualizar);

		JLabel lblActualizaDatos = new JLabel("Actualiza el tipo del Pokemon:");
		lblActualizaDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblActualizaDatos.setBounds(10, 11, 361, 36);
		contenidoActualizar.add(lblActualizaDatos);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(20, 58, 100, 20);
		contenidoActualizar.add(lblNombre);

		JTextField textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textNombre.setEditable(true);
		textNombre.setBounds(150, 58, 221, 20);
		contenidoActualizar.add(textNombre);

		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipo.setBounds(20, 108, 100, 20);
		contenidoActualizar.add(lblTipo);

		JTextField textTipo = new JTextField();
		textTipo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textTipo.setEditable(true);
		textTipo.setBounds(150, 108, 221, 20);
		contenidoActualizar.add(textTipo);

		JButton btnActBBDD = new JButton("ACTUALIZAR");
		btnActBBDD.setBounds(200, 425, 120, 30);
		btnActBBDD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarBaseDatos(textNombre.getText(), textTipo.getText());
				actualizarFrame.setVisible(false);
			}
		});
		contenidoActualizar.add(btnActBBDD);

	}
	
	/**
	 * Crea un nuevo frame con los componentes necesarios para introducir los datos de un nuevo Pokemon
	 * y anadirlo tanto a la tabla como a la base de datos
	 * 
	 */
	public void añadirPokemon() {

		JFrame añadirFrame = new JFrame();
		JPanel contenidoAñadir = new JPanel();
		añadirFrame.setVisible(true);
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		añadirFrame.setSize(anchoPantalla / 4, alturaPantalla / 2);
		añadirFrame.setLocation(anchoPantalla / 2, alturaPantalla / 4);
		añadirFrame.setTitle("Añadir Pokemon");
		añadirFrame.setVisible(true);
		contenidoAñadir.setBounds(0, 0, 472, 502);
		contenidoAñadir.setVisible(true);
		contenidoAñadir.setLayout(null);
		añadirFrame.add(contenidoAñadir);

		JLabel lblIntroduceLosDatos = new JLabel("Introduce los datos del nuevo Pokemon");
		lblIntroduceLosDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIntroduceLosDatos.setBounds(10, 11, 361, 36);
		contenidoAñadir.add(lblIntroduceLosDatos);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(20, 58, 100, 20);
		contenidoAñadir.add(lblId);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(20, 108, 100, 14);
		contenidoAñadir.add(lblNombre);

		JLabel lblPc = new JLabel("PC");
		lblPc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPc.setBounds(20, 158, 100, 14);
		contenidoAñadir.add(lblPc);

		JLabel lblAtaque = new JLabel("Ataque");
		lblAtaque.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAtaque.setBounds(20, 208, 100, 14);
		contenidoAñadir.add(lblAtaque);

		JLabel lblDefensa = new JLabel("Defensa");
		lblDefensa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDefensa.setBounds(20, 258, 100, 14);
		contenidoAñadir.add(lblDefensa);

		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStamina.setBounds(20, 308, 100, 14);
		contenidoAñadir.add(lblStamina);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipo.setBounds(20, 358, 100, 14);
		contenidoAñadir.add(lblTipo);

		JTextField textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textId.setEditable(true);
		textId.setBounds(150, 58, 221, 20);
		contenidoAñadir.add(textId);

		JTextField textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textNombre.setEditable(true);
		textNombre.setBounds(150, 107, 221, 20);
		contenidoAñadir.add(textNombre);

		JTextField textPc = new JTextField();
		textPc.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textPc.setEditable(true);
		textPc.setBounds(150, 157, 221, 20);
		contenidoAñadir.add(textPc);

		JTextField textAtaque = new JTextField();
		textAtaque.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textAtaque.setBounds(150, 207, 221, 20);
		contenidoAñadir.add(textAtaque);

		JTextField textDefensa = new JTextField();
		textDefensa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textDefensa.setBounds(150, 257, 221, 20);
		contenidoAñadir.add(textDefensa);

		JTextField textStamina = new JTextField();
		textStamina.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textStamina.setEditable(true);
		textStamina.setBounds(150, 307, 221, 20);
		contenidoAñadir.add(textStamina);

		JTextField textTipo = new JTextField();
		textTipo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textTipo.setBounds(150, 357, 221, 20);
		contenidoAñadir.add(textTipo);
		
		Object[] nuevaFila = new String[7];

		nuevaFila[0] = textId.getText();
		nuevaFila[1] = textNombre.getText();
		nuevaFila[2] = textPc.getText();
		nuevaFila[3] = textAtaque.getText();
		nuevaFila[4] = textDefensa.getText();
		nuevaFila[5] = textStamina.getText();
		nuevaFila[6] = textTipo.getText();

		JButton btnAñadirBBDD = new JButton("AÑADIR");
		btnAñadirBBDD.setBounds(215, 425, 90, 30);
		btnAñadirBBDD.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(buscarPokeBBDD(textNombre.getText(), textId.getText()) == false) {
				añadirBaseDatos(textId.getText(), textNombre.getText(), textPc.getText(), textAtaque.getText(),
						textDefensa.getText(), textStamina.getText(), textTipo.getText());
				añadirFrame.setVisible(false);
				newFila(textId.getText(), textNombre.getText(), textPc.getText(), textAtaque.getText(),
						textDefensa.getText(), textStamina.getText(), textTipo.getText());
				btnAdd.doClick();
				}else {
					JOptionPane.showMessageDialog(null, "El Pokemon o el ID ya existen en la BBDD");
				}
			}

		});
		contenidoAñadir.add(btnAñadirBBDD);

	}
	
	public boolean buscarPokeBBDD(String nombre, String id) {
		boolean existe = false;
		ResultSet buscarNombre = Conexion.EjecutarSentencia("SELECT * FROM pokemon ORDER BY Nombre");
		try {
			while (buscarNombre.next()) {
				if (nombre.equals(buscarNombre.getString("nombre")) || id.equals(buscarNombre.getString("idPokemon"))) {
					existe = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	/**
	 * Cambia las variables de las estadisticas para poder anadir una nueva fila
	 * 
	 * @param a id
	 * @param b nombre
	 * @param c pc
	 * @param d ataque
	 * @param e defensa
	 * @param f stamina
	 * @param g tipo
	 */
	public void newFila(String a, String b, String c, String d, String e, String f, String g) {
		idPr = a;
		nombrePr = b;
		pcPr = c;
		ataquePr = d;
		defensaPr = e;
		staminaPr = f;
		tipoPr = g;

	}
	
	/**
	 * Anade a la base de datos un nuevo Pokemon con las variables que se han introducido
	 * 
	 * @param idPokemon id nuevo
	 * @param nombre nombre nuevo
	 * @param pc pc nuevo
	 * @param ataque ataque nuevo
	 * @param defensa defensa nueva
	 * @param stamina stamina nueva
	 * @param tipo tipo nuevo
	 */
	public void añadirBaseDatos(String idPokemon, String nombre, String pc, String ataque, String defensa,
			String stamina, String tipo) {

		Conexion.EjecutarUpdate("INSERT INTO pokemon VALUES (" + idPokemon + ", \"" + nombre + "\", " + pc + ", "
				+ ataque + ", " + defensa + ", " + stamina + ", \"" + tipo + "\");");

	}

	/**
	 * Actualiza el tipo de un Pokemon en la base de datos
	 * 
	 * @param nombre nombre del Pokemon al que quieres cambiarle el tipo
	 * @param tipo tipo nuevo
	 */
	public void actualizarBaseDatos(String nombre, String tipo) {

		Conexion.EjecutarUpdate("UPDATE pokemon SET tipo = \"" + tipo + "\" WHERE nombre = \"" + nombre + "\";");

	}
	
	/**
	 * Elimina el Pokemon que se haya seleccionado en la tabla de la base de datos
	 * 
	 * @param nombre nombre del Pokemon que se elimina
	 */
	public void eliminarBaseDatos(String nombre) {

		Conexion.EjecutarUpdate("DELETE from pokemon WHERE nombre = \"" + nombre + "\";");

	}
}
