package proyecto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import proyecto.*;

public class TheSilkRoad extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	JPanel menuP;
	JPanel pkm;
	JPanel movim;
	JPanel simul;
	JPanel equipo;

	public TheSilkRoad() {
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		setSize(anchoPantalla / 2, alturaPantalla / 2);
		setLocation(anchoPantalla / 4, alturaPantalla / 4);
		setTitle("The Silk Road");
		Image icono = miPantalla.getImage("masterball.png");
		setIconImage(icono);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		menuP = new JPanel();
		menuP.setBounds(0, 0, 944, 502);
		getContentPane().add(menuP);
		menuP.setVisible(true);
		menuP.setLayout(null);

		pkm = new JPanel();
		pkm.setBounds(0, 0, 944, 502);
		getContentPane().add(pkm);
		pkm.setVisible(false);
		pkm.setLayout(null);

		movim = new JPanel();
		movim.setBounds(0, 0, 944, 502);
		getContentPane().add(movim);
		movim.setVisible(false);
		movim.setLayout(null);

		simul = new JPanel();
		simul.setBounds(0, 0, 944, 502);
		getContentPane().add(simul);
		simul.setVisible(false);
		simul.setLayout(null);
		
		equipo = new JPanel();
		equipo.setBounds(0, 0, 944, 502);
		getContentPane().add(equipo);
		equipo.setVisible(false);
		equipo.setLayout(null);

		JButton botonPkm = new JButton();
		botonPkm.setIcon(new ImageIcon("src\\proyecto\\pic\\pkmlogo.png"));
		botonPkm.setBounds(100, 100, 200, 100);
		botonPkm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuP.setVisible(false);
				pkm.setVisible(true);
				Pokemon pkmLogo = new Pokemon();
				pkmLogo.añadirPkm(pkm, menuP);

			}
		});
		menuP.add(botonPkm);

		JButton botonMov = new JButton();
		botonMov.setIcon(new ImageIcon("src\\proyecto\\pic\\movimientos.png"));
		botonMov.setBounds(100, 220, 200, 100);
		botonMov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuP.setVisible(false);
				movim.setVisible(true);
				Ataques ataqueLogo = new Ataques();
				ataqueLogo.añadirAt(movim, menuP);
			}
		});
		menuP.add(botonMov);

		JButton botonSim = new JButton();
		botonSim.setIcon(new ImageIcon("src\\proyecto\\pic\\simulador.png"));
		botonSim.setBounds(100, 340, 200, 100);
		botonSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuP.setVisible(false);
				simul.setVisible(true);
				Simulador simLogo = new Simulador();
				simLogo.añadirSimulador(simul, menuP);
			}
		});
		menuP.add(botonSim);
		
		JButton botonEquipo = new JButton();
		botonEquipo.setIcon(new ImageIcon("src\\proyecto\\pic\\Equipo.png"));
		botonEquipo.setBounds(715, 400, 120, 60);
		botonEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuP.setVisible(false);
				equipo.setVisible(true);
				Equipo equipoLogo = new Equipo();
				equipoLogo.añadirEquipo(equipo, menuP);
			}
		});
		menuP.add(botonEquipo);

		JLabel lblMenuPrincipal = new JLabel("MENU PRINCIPAL");
		lblMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMenuPrincipal.setBounds(100, 21, 247, 44);
		menuP.add(lblMenuPrincipal);

		JLabel pokemonlabel = new JLabel(new ImageIcon("src\\proyecto\\pic\\pkmimg.png"));
		pokemonlabel.setBounds(400, 100, 200, 100);
		menuP.add(pokemonlabel);
		JLabel movimientoslabel = new JLabel(new ImageIcon("src\\proyecto\\pic\\movimientosimg.jpg"));
		movimientoslabel.setBounds(400, 220, 200, 100);
		menuP.add(movimientoslabel);
		JLabel simuladorlabel = new JLabel(new ImageIcon("src\\proyecto\\pic\\simuladorimg.png"));
		simuladorlabel.setBounds(400, 340, 200, 100);
		menuP.add(simuladorlabel);

		JLabel torchic = new JLabel(new ImageIcon("src\\proyecto\\pic\\combate.gif"));
		torchic.setBounds(625, 160, 300, 200);
		menuP.add(torchic);

	}

	public JPanel getMenuPrincipal() {
		return menuP;
	}

	public void setMenuPrincipal(JPanel menuPrincipal) {
		menuP = menuPrincipal;
	}
}
