/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemapedidos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author USUARIO
 */
public class main {

    
    
    
    
    public static void main(String[] args) {
        List<Item> dados = getSampleData();
        JanelaItem janela = new JanelaItem(dados);
        janela.setSize(800, 500);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    private static List<Item> getSampleData() {
        List<Item> itens = new ArrayList<Item>() {
            {
                add(new Item("Batata", 1, 2.31));
                add(new Item("Refrigerante", 22, 4.21));
                add(new Item("Hamburgue", 15, 6.71));
            }
        };
        return itens;
    }

}
