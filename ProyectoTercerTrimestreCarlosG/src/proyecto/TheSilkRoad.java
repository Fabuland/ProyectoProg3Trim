package proyecto;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;

public class TheSilkRoad extends JFrame {

	JPanel menuP;
	JPanel pkm;
	JPanel movim;
	JPanel simul;
	JPanel equipo;

	/**
	 * Frame principal que contiene todos los botones que llevan al resto de paneles, imágenes de referencia y un título
	 */
	public TheSilkRoad() {
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		int alturaPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		setSize(anchoPantalla / 2, alturaPantalla / 2);
		setLocation(anchoPantalla / 4, alturaPantalla / 4);
		setTitle("The Silk Road");
		Image icono = miPantalla.getImage("src\\pic\\masterball.png");
		setIconImage(icono);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		menuP = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src\\pic\\Background.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
			}
		};
		menuP.setBounds(0, 0, 944, 502);
		getContentPane().add(menuP);
		menuP.setVisible(true);
		menuP.setLayout(null);

		pkm = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src\\pic\\Background2.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
			}
		};
		pkm.setBounds(0, 0, 944, 502);
		getContentPane().add(pkm);
		pkm.setVisible(false);
		pkm.setLayout(null);

		movim = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src\\pic\\Background2.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
			}
		};
		movim.setBounds(0, 0, 944, 502);
		getContentPane().add(movim);
		movim.setVisible(false);
		movim.setLayout(null);

		simul = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src\\pic\\Background2.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
			}
		};
		simul.setBounds(0, 0, 944, 502);
		getContentPane().add(simul);
		simul.setVisible(false);
		simul.setLayout(null);

		equipo = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("src\\pic\\Background2.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
			}
		};
		equipo.setBounds(0, 0, 944, 502);
		getContentPane().add(equipo);
		equipo.setVisible(false);
		equipo.setLayout(null);
		 
		JButton botonPkm = new JButton();
		botonPkm.setIcon(new ImageIcon("src\\pic\\pkmlogo.png"));
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
		botonMov.setIcon(new ImageIcon("src\\pic\\movimientos.png"));
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
		botonSim.setIcon(new ImageIcon("src\\pic\\simulador.png"));
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
		botonEquipo.setIcon(new ImageIcon("src\\pic\\Equipo.png"));
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

		JLabel lblMenuPrincipal = new JLabel(new ImageIcon("src\\pic\\MenúPrincipal.png"));

		lblMenuPrincipal.setBounds(330, 8, 300, 100);

		menuP.add(lblMenuPrincipal);

		JLabel pokemonlabel = new JLabel(new ImageIcon("src\\pic\\PokemonLogo.gif"));
		pokemonlabel.setBounds(350, 100, 200, 100);
		menuP.add(pokemonlabel);
		JLabel movimientoslabel = new JLabel(new ImageIcon("src\\pic\\AtaqueLogo.gif"));
		movimientoslabel.setBounds(350, 220, 200, 100);
		menuP.add(movimientoslabel);
		JLabel simuladorlabel = new JLabel(new ImageIcon("src\\pic\\CombateLogo.gif"));
		simuladorlabel.setBounds(350, 340, 200, 100);
		menuP.add(simuladorlabel);

		JLabel torchic = new JLabel(new ImageIcon("src\\pic\\EquipoLogo.png"));
		torchic.setBounds(625, 160, 300, 200);
		menuP.add(torchic);

	}

}
