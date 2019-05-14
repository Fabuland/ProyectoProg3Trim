package proyecto;

import javax.swing.JPanel;
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
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ataques extends JPanel {

	String nombrePr, dañoPr, cargaNrgPr, dpsPr, epsPr, duracionPr;
	DefaultTableModel modeloAt;
	JTable tablaAt;
	JButton btnAddAt, btnUpdate;

	public Ataques() {

	}

	/**
	 * Esta funcion crea todos los componentes que se le anaden al panel movim, el cual es el que
	 * se visualiza ahora
	 * 
	 * @param movim panel que se visualiza en este momento
	 * @param menuP panel menu principal que se deja de visualizar
	 */
	public void añadirAt(JPanel movim, JPanel menuP) {

		movim.validate();
		modeloAt = new DefaultTableModel();
		Object[] columnasAt = { "Nombre", "Daño", "Carga Energia", "DPS", "EPS", "Duracion" };
		modeloAt.setColumnIdentifiers(columnasAt);
		crearModelo();

		tablaAt = new JTable(modeloAt);
		tablaAt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaAt.setAutoCreateColumnsFromModel(true);
		// tablaId.setEnabled(false);
		tablaAt.setRowHeight(30);
		Font fuente = new Font("", 1, 18);
		tablaAt.setFont(fuente);
		tablaAt.getTableHeader().setFont(fuente);
		tablaAt.setBounds(60, 110, 824, 360);
		movim.add(tablaAt);
		JScrollPane jp = new JScrollPane(tablaAt);
		jp.setBounds(60, 110, 824, 360);
		jp.setVisible(true);
		jp.setViewportView(tablaAt);
		// add(jp);
		movim.add(jp);

		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\pic\\back.png"));
		btnBack.setBounds(10, 10, 40, 20);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movim.setVisible(false);
				menuP.setVisible(true);
			}
		});
		movim.add(btnBack);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrdenarPor.setBounds(85, 17, 111, 23);
		movim.add(lblOrdenarPor);

		JLabel lblAnadir = new JLabel("Añadir:");
		lblAnadir.setBounds(60, 67, 250, 23);
		lblAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		movim.add(lblAnadir);

		JButton botonAnadir = new JButton(new ImageIcon("src\\pic\\añadir.jpg"));
		botonAnadir.setBounds(140, 57, 100, 40);
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirAtaque();
			}
		});
		movim.add(botonAnadir);

		JLabel lblEliminar = new JLabel("Eliminar:");
		lblEliminar.setBounds(380, 67, 250, 23);
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		movim.add(lblEliminar);

		JButton botonEliminar = new JButton(new ImageIcon("src\\pic\\eliminar.png"));
		botonEliminar.setBounds(470, 57, 100, 40);
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// eliminarPokemon();
				tablaAt.getModel();
				int i = tablaAt.getSelectedRow();

				if (i >= 0) {
					String nombreAtElim = (String) tablaAt.getValueAt(i, 0);
					modeloAt.removeRow(i);
					eliminarBaseDatos(nombreAtElim);

				} else {
					JOptionPane.showMessageDialog(null, "Error al eliminar");

				}

			}
		});

		movim.add(botonEliminar);

		JLabel lblUpdate = new JLabel("Actualizar:");
		lblUpdate.setBounds(680, 67, 250, 23);
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		movim.add(lblUpdate);

		btnUpdate = new JButton(new ImageIcon("src\\pic\\update.png"));
		btnUpdate.setBounds(784, 57, 100, 40);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarAtaque();
			}
		});
		movim.add(btnUpdate);

		JButton btnOrdIDaño = new JButton("DAÑO");
		btnOrdIDaño.setBounds(240, 19, 100, 23);
		btnOrdIDaño.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("Daño", "DESC");

			}
		});
		movim.add(btnOrdIDaño);

		JButton btnOrdCargaNrg = new JButton("CARGA NRG");
		btnOrdCargaNrg.setBounds(365, 19, 110, 23);
		btnOrdCargaNrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("cargaNrg", "DESC");

			}
		});
		movim.add(btnOrdCargaNrg);

		JButton btnOrdDps = new JButton("DPS");
		btnOrdDps.setBounds(500, 19, 100, 23);
		btnOrdDps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("dps", "DESC");

			}
		});
		movim.add(btnOrdDps);

		JButton btnOrdEps = new JButton("EPS");
		btnOrdEps.setBounds(630, 19, 100, 23);
		btnOrdEps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("eps", "DESC");

			}
		});
		movim.add(btnOrdEps);

		JButton btnOrdDuracion = new JButton("DURACION");
		btnOrdDuracion.setBounds(760, 19, 100, 23);
		btnOrdDuracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("duracion", "DESC");

			}
		});
		movim.add(btnOrdDuracion);

		btnAddAt = new JButton();
		btnAddAt.setBounds(765, 60, 0, 0);
		btnAddAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] nuevFila = new Object[6];
				nuevFila[0] = nombrePr;
				nuevFila[1] = dañoPr;
				nuevFila[2] = cargaNrgPr;
				nuevFila[3] = dpsPr;
				nuevFila[4] = epsPr;
				nuevFila[5] = duracionPr;
				modeloAt.addRow(nuevFila);
			}
		});
		movim.add(btnAddAt);

	}

	/**
	 * Crea el modelo base de la primera tabla que se crea al entrar en el panel movim
	 */
	public void crearModelo() {

		Object[] fila = new Object[6];
		ResultSet ponerTabla = Conexion.EjecutarSentencia("SELECT * FROM ataques ORDER BY Nombre");
		try {
			while (ponerTabla.next()) {

				fila[0] = ponerTabla.getString("Nombre");
				fila[1] = ponerTabla.getString("Daño");
				fila[2] = ponerTabla.getString("CargaNrg");
				fila[3] = ponerTabla.getString("DPS")+".0";
				fila[4] = ponerTabla.getString("EPS")+".0";
				fila[5] = ponerTabla.getString("Duracion");

				modeloAt.addRow(fila);

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

		ResultSet contador = Conexion.EjecutarSentencia("SELECT * FROM ataques");
		int counter = 0;

		try {
			while (contador.next()) {
				counter++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet datos = Conexion.EjecutarSentencia("SELECT * FROM ataques ORDER BY " + dato + " " + ascendencia);
		borrarFilas(counter);
		Object[] nuevaFila = new Object[6];

		try {
			while (datos.next()) {
				nuevaFila[0] = datos.getString("Nombre");
				nuevaFila[1] = datos.getString("Daño");
				nuevaFila[2] = datos.getString("CargaNrg");
				nuevaFila[3] = datos.getString("DPS")+".0";
				nuevaFila[4] = datos.getString("EPS")+".0";
				nuevaFila[5] = datos.getString("Duracion");
				modeloAt.addRow(nuevaFila);

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
			modeloAt.removeRow(0);
		}
	}

	/**
	 * Crea un nuevo frame con los componentes necesarios para introducir un nuevo ataque
	 * y actualizarlo tanto en la tabla como en la base de datos
	 * 
	 */
	public void añadirAtaque() {

		JFrame añadirFrame = new JFrame();
		JPanel contenidoAñadir = new JPanel();
		añadirFrame.setVisible(true);
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		añadirFrame.setSize(anchoPantalla / 4, alturaPantalla / 2);
		añadirFrame.setLocation(anchoPantalla / 2, alturaPantalla / 4);
		añadirFrame.setTitle("Añadir Ataque");
		añadirFrame.setVisible(true);
		contenidoAñadir.setBounds(0, 0, 472, 502);
		contenidoAñadir.setVisible(true);
		contenidoAñadir.setLayout(null);
		añadirFrame.add(contenidoAñadir);

		JLabel lblIntroduceLosDatos = new JLabel("Introduce los datos del nuevo Ataque");
		lblIntroduceLosDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIntroduceLosDatos.setBounds(10, 11, 361, 36);
		contenidoAñadir.add(lblIntroduceLosDatos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(20, 58, 100, 20);
		contenidoAñadir.add(lblNombre);

		JLabel lblDaño = new JLabel("Daño");
		lblDaño.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDaño.setBounds(20, 108, 100, 14);
		contenidoAñadir.add(lblDaño);

		JLabel lblCargaNrg = new JLabel("Carga Energia");
		lblCargaNrg.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCargaNrg.setBounds(20, 158, 130, 20);
		contenidoAñadir.add(lblCargaNrg);

		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDuracion.setBounds(20, 208, 100, 14);
		contenidoAñadir.add(lblDuracion);

		JTextField textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textNombre.setEditable(true);
		textNombre.setBounds(150, 58, 221, 20);
		contenidoAñadir.add(textNombre);

		JTextField textDaño = new JTextField();
		textDaño.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textDaño.setEditable(true);
		textDaño.setBounds(150, 107, 221, 20);
		contenidoAñadir.add(textDaño);

		JTextField textCargaNrg = new JTextField();
		textCargaNrg.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textCargaNrg.setEditable(true);
		textCargaNrg.setBounds(150, 157, 221, 20);
		contenidoAñadir.add(textCargaNrg);

		JTextField textDuracion = new JTextField();
		textDuracion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textCargaNrg.setEditable(true);
		textDuracion.setBounds(150, 207, 221, 20);
		contenidoAñadir.add(textDuracion);

		Object[] nuevaFila = new String[4];

		nuevaFila[0] = textNombre.getText();
		nuevaFila[1] = textDaño.getText();
		nuevaFila[2] = textCargaNrg.getText();
		nuevaFila[3] = textDuracion.getText();

		JButton btnAñadirBBDD = new JButton("AÑADIR");
		btnAñadirBBDD.setBounds(215, 425, 90, 30);
		btnAñadirBBDD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int daño = Integer.parseInt(textDaño.getText());
				int cargaNrg = Integer.parseInt(textCargaNrg.getText());
				int duracion = Integer.parseInt(textDuracion.getText());

				double dps = daño / duracion;
				double eps = cargaNrg / duracion;

				DecimalFormat df = new DecimalFormat("#.##");

				double dpsFinal = Double.parseDouble(df.format(dps));
				double epsFinal = Double.parseDouble(df.format(eps));

				String dpsTexto = Double.toString(dpsFinal);
				String epsTexto = Double.toString(epsFinal);
				añadirBaseDatos(textNombre.getText(), textDaño.getText(), textCargaNrg.getText(), dpsTexto, epsTexto,
						textDuracion.getText());
				añadirFrame.setVisible(false);
				newFila(textNombre.getText(), textDaño.getText(), textCargaNrg.getText(), dpsTexto, epsTexto,
						textDuracion.getText());
				btnAddAt.doClick();
			}
		});
		contenidoAñadir.add(btnAñadirBBDD);

	}

	/**
	 * Crea un nuevo frame con los componentes necesarios para introducir la nueva duracion de un ataque
	 * y actualizarlo tanto en la tabla como en la base de datos
	 * 
	 */
	public void actualizarAtaque() {

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
				if (!((c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textNombre.setEditable(true);
		textNombre.setBounds(150, 58, 221, 20);
		contenidoActualizar.add(textNombre);

		JLabel lblDuracion = new JLabel("Duracion: ");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDuracion.setBounds(20, 108, 100, 20);
		contenidoActualizar.add(lblDuracion);

		JTextField textDuracion = new JTextField();
		textDuracion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textDuracion.setEditable(true);
		textDuracion.setBounds(150, 108, 221, 20);
		contenidoActualizar.add(textDuracion);

		JButton btnActBBDD = new JButton("ACTUALIZAR");
		btnActBBDD.setBounds(200, 425, 120, 30);
		btnActBBDD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarBaseDatos(textNombre.getText(), textDuracion.getText());
				actualizarDpsEps(textNombre.getText(), textDuracion.getText());
				actualizarFrame.setVisible(false);
			}
		});
		contenidoActualizar.add(btnActBBDD);

	}

	/**
	 * Cambiar las variables de las estadisticas para poder crear una nueva fila
	 * 
	 * @param a nombre
	 * @param b daño
	 * @param c carga energia
	 * @param d dps
	 * @param e eps
	 * @param f duracion
	 */
	public void newFila(String a, String b, String c, String d, String e, String f) {

		nombrePr = a;
		dañoPr = b;
		cargaNrgPr = c;
		dpsPr = d;
		epsPr = e;
		duracionPr = f;

	}

	/**
	 * Hace nuevos calculos para actualizar en la tabla y en la base de datos el dps y el eps
	 * 
	 * @param nombre nombre del pokemon
	 * @param duracion duracion del ataque
	 */
	public void actualizarDpsEps(String nombre, String duracion) {

		int dur = Integer.parseInt(duracion);
		int filasTotales = (tablaAt.getRowCount() - 1);
		while (filasTotales != 0) {
			if (nombre.equals(tablaAt.getValueAt(filasTotales, 0))) {
				int dañoC = Integer.parseInt(tablaAt.getValueAt(filasTotales, 1).toString());
				int cargaNrgC = Integer.parseInt(tablaAt.getValueAt(filasTotales, 2).toString());
				int dpsN = dañoC / dur;
				int epsN = cargaNrgC / dur;
				String dpsText = Integer.toString(dpsN)+"";
				String epsText = Integer.toString(epsN)+"";
				actualizarBaseDatosDpsEps(nombre, dpsText, epsText);
			}
			filasTotales--;
		}

	}

	/**
	 * Anade un nuevo ataque a la base de datos con las estadisticas introducidas
	 * 
	 * @param Nombre nombre nuevo
	 * @param Daño dano nuevo
	 * @param CargaNrg carga energia nueva
	 * @param DPS dps nuevo
	 * @param EPS eps nuevo
	 * @param Duracion duracion nueva
	 */
	public void añadirBaseDatos(String Nombre, String Daño, String CargaNrg, String DPS, String EPS, String Duracion) {

		Conexion.EjecutarUpdate("INSERT INTO ataques VALUES (\"" + Nombre + "\", " + Daño + ", " + CargaNrg + ", " + DPS
				+ ", " + EPS + ", " + Duracion + ");");

	}

	/**
	 * Actualiza la duracion de un ataque en la base de datos
	 * 
	 * @param nombre nombre del ataque a cambiar
	 * @param duracion duracion nueva
	 */
	public void actualizarBaseDatos(String nombre, String duracion) {

		Conexion.EjecutarUpdate("UPDATE ataques SET duracion = " + duracion + " WHERE nombre = \"" + nombre + "\";");

	}

	/**
	 * Cambia el dps y eps del ataque introducido, debido a un cambio en la duracion del mismo
	 * 
	 * @param nombre nombre del ataque a cambiar
	 * @param dps dps nuevo
	 * @param eps eps nuevo
	 */
	public void actualizarBaseDatosDpsEps(String nombre, String dps, String eps) {

		Conexion.EjecutarUpdate("UPDATE ataques SET DPS = "+dps+", EPS = "+eps+" WHERE nombre = \"" + nombre + "\";");

	}

	/**
	 * Elimina un ataque tanto de la tabla como de la base de datos
	 * 
	 * @param nombre nombre del ataque a eliminar
	 */
	public void eliminarBaseDatos(String nombre) {

		Conexion.EjecutarUpdate("DELETE from ataques WHERE nombre = \"" + nombre + "\";");

	}

}
