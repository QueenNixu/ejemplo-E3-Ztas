package test;

import javax.swing.*;
public class Alerta {
    public Alerta(String a) {
        JOptionPane.showMessageDialog(
                 null, //padre
                 a, //mensaje
                 "ERROR", // t�tulo
                 JOptionPane.INFORMATION_MESSAGE); //hay 5 tipos de mensajes
                }
}
