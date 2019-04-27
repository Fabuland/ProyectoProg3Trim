package proyecto;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bbdd.Conexion;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Ataques extends JPanel{

	String nombrePr, da�oPr, cargaNrgPr, dpsPr, epsPr, duracionPr;
	DefaultTableModel modeloAt;
	JTable tablaAt;
	JButton btnAddAt;

	public Ataques() {

	}

	public void a�adirAt(JPanel movim, JPanel menuP) {

		movim.validate();
		modeloAt = new DefaultTableModel();
		Object[] columnasAt = { "Nombre", "Da�o", "Carga Energia", "DPS", "EPS", "Duracion" };
		modeloAt.setColumnIdentifiers(columnasAt);
		crearModelo(movim);

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
		movim.setLayout(null);

		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\proyecto\\pic\\back.png"));
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

		JLabel lblAnadir = new JLabel("A�adir nuevo Ataque:");
		lblAnadir.setBounds(85, 67, 250, 23);
		lblAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		movim.add(lblAnadir);

		JButton botonAnadir = new JButton(new ImageIcon("src\\proyecto\\pic\\a�adir.jpg"));
		botonAnadir.setBounds(300, 57, 100, 40);
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a�adirAtaque();
			}
		});
		movim.add(botonAnadir);

		JLabel lblEliminar = new JLabel("Eliminar Pokemon:");
		lblEliminar.setBounds(470, 67, 250, 23);
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		movim.add(lblEliminar);

		JButton botonEliminar = new JButton(new ImageIcon("src\\proyecto\\pic\\eliminar.png"));
		botonEliminar.setBounds(645, 57, 100, 40);
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// eliminarPokemon();
				tablaAt.getModel();
				int i = tablaAt.getSelectedRow();
				String nombrePkmElim = (String) tablaAt.getValueAt(i, 0);

				eliminarBaseDatos(nombrePkmElim);

				if (i >= 0) {
					modeloAt.removeRow(i);

				} else {
					System.out.println("error al eliminar");

				}

			}
		});

		movim.add(botonEliminar);

		JButton btnOrdIDa�o = new JButton("DA�O");
		btnOrdIDa�o.setBounds(240, 19, 100, 23);
		btnOrdIDa�o.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar("Da�o", "DESC");

			}
		});
		movim.add(btnOrdIDa�o);

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
				nuevFila[1] = da�oPr;
				nuevFila[2] = cargaNrgPr;
				nuevFila[3] = dpsPr;
				nuevFila[4] = epsPr;
				nuevFila[5] = duracionPr;
				modeloAt.addRow(nuevFila);
			}
		});
		movim.add(btnAddAt);

	}

	public void crearModelo(JPanel pkm) {

		Object[] fila = new Object[6];
		ResultSet ponerTabla = Conexion.EjecutarSentencia("SELECT * FROM ataques ORDER BY Nombre");
		try {
			while (ponerTabla.next()) {

				fila[0] = ponerTabla.getString("Nombre");
				fila[1] = ponerTabla.getString("Da�o");
				fila[2] = ponerTabla.getString("CargaNrg");
				fila[3] = ponerTabla.getString("DPS");
				fila[4] = ponerTabla.getString("EPS");
				fila[5] = ponerTabla.getString("Duracion");

				modeloAt.addRow(fila);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

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
				nuevaFila[1] = datos.getString("Da�o");
				nuevaFila[2] = datos.getString("CargaNrg");
				nuevaFila[3] = datos.getString("DPS");
				nuevaFila[4] = datos.getString("EPS");
				nuevaFila[5] = datos.getString("Duracion");
				modeloAt.addRow(nuevaFila);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void borrarFilas(int nFilas) {

		for (int i = 0; i < nFilas; i++) {
			modeloAt.removeRow(0);
		}
	}

	public void a�adirAtaque() {

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

		JLabel lblIntroduceLosDatos = new JLabel("Introduce los datos del nuevo Ataque");
		lblIntroduceLosDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIntroduceLosDatos.setBounds(10, 11, 361, 36);
		contenidoA�adir.add(lblIntroduceLosDatos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(20, 58, 100, 20);
		contenidoA�adir.add(lblNombre);

		JLabel lblDa�o = new JLabel("Da�o");
		lblDa�o.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDa�o.setBounds(20, 108, 100, 14);
		contenidoA�adir.add(lblDa�o);

		JLabel lblCargaNrg = new JLabel("Carga Energia");
		lblCargaNrg.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCargaNrg.setBounds(20, 158, 130, 20);
		contenidoA�adir.add(lblCargaNrg);

		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDuracion.setBounds(20, 208, 100, 14);
		contenidoA�adir.add(lblDuracion);

		JTextField textNombre = new JTextField();
		textNombre.setEditable(true);
		textNombre.setBounds(150, 58, 221, 20);
		contenidoA�adir.add(textNombre);

		JTextField textDa�o = new JTextField();
		textDa�o.setEditable(true);
		textDa�o.setBounds(150, 107, 221, 20);
		contenidoA�adir.add(textDa�o);

		JTextField textCargaNrg = new JTextField();
		textCargaNrg.setEditable(true);
		textCargaNrg.setBounds(150, 157, 221, 20);
		contenidoA�adir.add(textCargaNrg);

		JTextField textDuracion = new JTextField();
		textCargaNrg.setEditable(true);
		textDuracion.setBounds(150, 207, 221, 20);
		contenidoA�adir.add(textDuracion);

		/*
		 * idPokemon = textId.getText(); nombre = textNombre.getText(); pc =
		 * textPc.getText(); ataque = textAtaque.getText(); defensa =
		 * textDefensa.getText(); stamina = textStamina.getText(); tipo =
		 * textTipo.getText();
		 */
		Object[] nuevaFila = new String[4];

		nuevaFila[0] = textNombre.getText();
		nuevaFila[1] = textDa�o.getText();
		nuevaFila[2] = textCargaNrg.getText();
		nuevaFila[3] = textDuracion.getText();

		JButton btnA�adirBBDD = new JButton("A�ADIR");
		btnA�adirBBDD.setBounds(215, 425, 90, 30);
		btnA�adirBBDD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int da�o = Integer.parseInt(textDa�o.getText());
				int cargaNrg = Integer.parseInt(textCargaNrg.getText());
				int duracion = Integer.parseInt(textDuracion.getText());

				double dps = da�o / duracion;
				double eps = cargaNrg / duracion;

				DecimalFormat df = new DecimalFormat("#.##");

				double dpsFinal = Double.parseDouble(df.format(dps));
				double epsFinal = Double.parseDouble(df.format(eps));

				String dpsTexto = Double.toString(dpsFinal);
				String epsTexto = Double.toString(epsFinal);
				// a�adirBaseDatos(nuevaFila[0], nuevaFila[1], nuevaFila[2], nuevaFila[3],
				// nuevaFila[4], nuevaFila[5], nuevaFila[6]);
				a�adirBaseDatos(textNombre.getText(), textDa�o.getText(), textCargaNrg.getText(), dpsTexto, epsTexto,
						textDuracion.getText());
				// modelo.addRow(nuevaFila);
				a�adirFrame.setVisible(false);
				newFila(textNombre.getText(), textDa�o.getText(), textCargaNrg.getText(), dpsTexto, epsTexto,
						textDuracion.getText());
				btnAddAt.doClick();
			}
		});
		contenidoA�adir.add(btnA�adirBBDD);

	}

	public void newFila(String a, String b, String c, String d, String e, String f) {

		nombrePr = a;
		da�oPr = b;
		cargaNrgPr = c;
		dpsPr = d;
		epsPr = e;
		duracionPr = f;

	}

	public void a�adirBaseDatos(String Nombre, String Da�o, String CargaNrg, String DPS, String EPS, String Duracion) {

		Conexion.EjecutarUpdate("INSERT INTO ataques VALUES (\"" + Nombre + "\", " + Da�o + ", " + CargaNrg + ", " + DPS
				+ ", " + EPS + ", " + Duracion + ");");

	}

	public void eliminarBaseDatos(String nombre) {

		Conexion.EjecutarUpdate("DELETE from ataques WHERE nombre = \"" + nombre + "\";");

	}

}
