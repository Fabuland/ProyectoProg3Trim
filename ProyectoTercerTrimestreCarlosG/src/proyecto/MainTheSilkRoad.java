package proyecto;

import java.awt.EventQueue;

import bbdd.Conexion;

public class MainTheSilkRoad {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TheSilkRoad frame = new TheSilkRoad();
					frame.setVisible(true);
					Conexion.conectar();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
