package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Equipo extends JPanel {

	JTable tablaEq1, tablaEq2, tablaEq3, tablaEq4;
	DefaultTableModel modeloEq1, modeloEq2, modeloEq3, modeloEq4;
	JTextField textNombreEq1, textNombreEq2, textNombreEq3, textNombreEq4;
	JLabel nombreEq1, nombreEq2, nombreEq3, nombreEq4;

	public Equipo() {

	}

	public void añadirEquipo(JPanel eq, JPanel menuP) {

		eq.validate();
		modeloEq1 = new DefaultTableModel(3, 2);
		modeloEq2 = new DefaultTableModel(3, 2);
		modeloEq3 = new DefaultTableModel(3, 2);
		modeloEq4 = new DefaultTableModel(3, 2);

		tablaEq1 = new JTable(modeloEq1);
		tablaEq1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaEq1.setRowHeight(30);
		Font fuente = new Font("", 1, 18);
		tablaEq1.setFont(fuente);
		eq.add(tablaEq1);
		tablaEq1.setBounds(60, 110, 370, 90);

		tablaEq2 = new JTable(modeloEq2);
		tablaEq2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaEq2.setRowHeight(30);
		tablaEq2.setFont(fuente);
		eq.add(tablaEq2);
		tablaEq2.setBounds(540, 110, 370, 90);

		tablaEq3 = new JTable(modeloEq3);
		tablaEq3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaEq3.setRowHeight(30);
		tablaEq3.setFont(fuente);
		eq.add(tablaEq3);
		tablaEq3.setBounds(60, 380, 370, 90);

		tablaEq4 = new JTable(modeloEq4);
		tablaEq4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaEq4.setRowHeight(30);
		tablaEq4.setFont(fuente);
		eq.add(tablaEq4);
		tablaEq4.setBounds(540, 380, 370, 90);

		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon("src\\proyecto\\pic\\back.png"));
		btnBack.setBounds(10, 10, 40, 20);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eq.setVisible(false);
				menuP.setVisible(true);
			}
		});
		eq.add(btnBack);

		textNombreEq1 = new JTextField();
		textNombreEq1.setEditable(true);
		textNombreEq1.setBounds(60, 40, 180, 25);
		eq.add(textNombreEq1);

		nombreEq1 = new JLabel();
		nombreEq1.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombreEq1.setBounds(60, 80, 200, 25);
		nombreEq1.setForeground(Color.red);
		eq.add(nombreEq1);

		JButton añadirNombre1 = new JButton("Nombra el equipo");
		añadirNombre1.setBounds(270, 40, 140, 23);
		añadirNombre1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreEq1.setText(textNombreEq1.getText());
			}
		});
		eq.add(añadirNombre1);

		JButton guardarEq1 = new JButton("Guarda el equipo");
		guardarEq1.setBounds(270, 70, 140, 23);
		guardarEq1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// nombreEq1.setText(textNombreEq1.getText());
				// borrarContenidoTxt("1");
				guardarEquipo("1", nombreEq1, modeloEq1);
			}
		});
		eq.add(guardarEq1);

		textNombreEq2 = new JTextField();
		textNombreEq2.setEditable(true);
		textNombreEq2.setBounds(540, 40, 180, 25);
		eq.add(textNombreEq2);

		nombreEq2 = new JLabel();
		nombreEq2.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombreEq2.setBounds(540, 80, 200, 25);
		nombreEq2.setForeground(Color.red);
		eq.add(nombreEq2);

		JButton añadirNombre2 = new JButton("Nombra el equipo");
		añadirNombre2.setBounds(750, 40, 140, 23);
		añadirNombre2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreEq2.setText(textNombreEq2.getText());
			}
		});
		eq.add(añadirNombre2);

		JButton guardarEq2 = new JButton("Guarda el equipo");
		guardarEq2.setBounds(750, 70, 140, 23);
		guardarEq2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// nombreEq1.setText(textNombreEq1.getText());
				// borrarContenidoTxt("1");
				guardarEquipo("2", nombreEq2, modeloEq2);
			}
		});
		eq.add(guardarEq2);

		textNombreEq3 = new JTextField();
		textNombreEq3.setEditable(true);
		textNombreEq3.setBounds(60, 310, 180, 25);
		eq.add(textNombreEq3);

		nombreEq3 = new JLabel();
		nombreEq3.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombreEq3.setBounds(60, 350, 200, 25);
		nombreEq3.setForeground(Color.red);
		eq.add(nombreEq3);

		JButton añadirNombre3 = new JButton("Nombra el equipo");
		añadirNombre3.setBounds(270, 310, 140, 23);
		añadirNombre3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreEq3.setText(textNombreEq3.getText());
			}
		});
		eq.add(añadirNombre3);

		JButton guardarEq3 = new JButton("Guarda el equipo");
		guardarEq3.setBounds(270, 340, 140, 23);
		guardarEq3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// nombreEq1.setText(textNombreEq1.getText());
				// borrarContenidoTxt("1");
				guardarEquipo("3", nombreEq3, modeloEq3);
			}
		});
		eq.add(guardarEq3);

		textNombreEq4 = new JTextField();
		textNombreEq4.setEditable(true);
		textNombreEq4.setBounds(540, 310, 180, 25);
		eq.add(textNombreEq4);

		nombreEq4 = new JLabel();
		nombreEq4.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombreEq4.setBounds(540, 350, 200, 25);
		nombreEq4.setForeground(Color.red);
		eq.add(nombreEq4);

		JButton añadirNombre4 = new JButton("Nombra el equipo");
		añadirNombre4.setBounds(750, 310, 140, 23);
		añadirNombre4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombreEq4.setText(textNombreEq4.getText());
			}
		});
		eq.add(añadirNombre4);

		JButton guardarEq4 = new JButton("Guarda el equipo");
		guardarEq4.setBounds(750, 340, 140, 23);
		guardarEq4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// nombreEq1.setText(textNombreEq1.getText());
				// borrarContenidoTxt("1");
				guardarEquipo("4", nombreEq4, modeloEq4);
			}
		});
		eq.add(guardarEq4);
		
		rellenarDatos();

	}

	public void sacarEquipo(String n, JLabel nombre, DefaultTableModel modelo) {

		String ruta = "src/proyecto/EquipoGuardado" + n + ".txt";
		File fichero = new File(ruta);

		try {
			Scanner reader = new Scanner(fichero);
			for (int i = 0; i < 7; i++) {
				if (i == 0) {
					String data = reader.nextLine();
					nombre.setText(data);

				} else if (i == 1) {
					String data = reader.nextLine();
					modelo.setValueAt(data, 0, 0);

				} else if (i == 2) {
					String data = reader.nextLine();
					modelo.setValueAt(data, 0, 1);

				} else if (i == 3) {
					String data = reader.nextLine();
					modelo.setValueAt(data, 1, 0);

				} else if (i == 4) {
					String data = reader.nextLine();
					modelo.setValueAt(data, 1, 1);

				} else if (i == 5) {
					String data = reader.nextLine();
					modelo.setValueAt(data, 2, 0);

				} else if (i == 6) {
					String data = reader.nextLine();
					modelo.setValueAt(data, 2, 1);
				}

			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void guardarEquipo(String n, JLabel nombre, DefaultTableModel modelo) {

		try {
			FileWriter writer = new FileWriter("src/proyecto/EquipoGuardado" + n + ".txt");
			String nombreEq = nombre.getText();
			writer.write(nombreEq);
			writer.write(System.lineSeparator());
			String pkm1 = modelo.getValueAt(0, 0) + "";
			writer.write(pkm1);
			writer.write(System.lineSeparator());
			String pkm2 = modelo.getValueAt(0, 1) + "";
			writer.write(pkm2);
			writer.write(System.lineSeparator());
			String pkm3 = modelo.getValueAt(1, 0) + "";
			writer.write(pkm3);
			writer.write(System.lineSeparator());
			String pkm4 = modelo.getValueAt(1, 1) + "";
			writer.write(pkm4);
			writer.write(System.lineSeparator());
			String pkm5 = modelo.getValueAt(2, 0) + "";
			writer.write(pkm5);
			writer.write(System.lineSeparator());
			String pkm6 = modelo.getValueAt(2, 1) + "";
			writer.write(pkm6);
			writer.close();
			System.out.println("Se ha escrito en el fichero");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void rellenarDatos() {
		sacarEquipo("1", nombreEq1, modeloEq1);
		sacarEquipo("2", nombreEq2, modeloEq2);
		sacarEquipo("3", nombreEq3, modeloEq3);
		sacarEquipo("4", nombreEq4, modeloEq4);
	}

	public String getStrNombreEq1() {
		return nombreEq1.getText();
	}

	public String getStrNombreEq2() {
		return nombreEq2.getText();
	}

	public String getStrNombreEq3() {
		return nombreEq3.getText();
	}

	public String getStrNombreEq4() {
		return nombreEq4.getText();
	}

}
