package test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class clickBoton1 extends MouseAdapter {

	 public void mouseClicked (MouseEvent e) {
		 new Alerta("anduvo click");
	 }

}
