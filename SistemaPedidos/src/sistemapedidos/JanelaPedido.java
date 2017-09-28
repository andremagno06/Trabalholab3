package sistemapedidos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class JanelaPedido extends JFrame {

    private final List<Item> dados;
    private JTable tabela;
    private final JButton btnAdicionar = new JButton("Adicionar");
    private final JButton btnExcluir = new JButton("Excluir");

    private final JTextField txtCodigo = new JTextField("Codigo");
    private final JTextField txtNome = new JTextField("Nome");
    private final JTextField txtPreco = new JTextField("Preço");

    private final JLabel HoraInicio = new JLabel("Data Inico do Pedido: ");
    private final JLabel HoraFinal = new JLabel("Data final final do Pedido: ");

    public JanelaPedido(List<Item> dados) throws HeadlessException {
        super("Tabelas 01");
        this.dados = dados;

        //tabela
        tabela = new JTable(new ItemTableModel(dados));
        btnAdicionar.setEnabled(true);
        btnExcluir.setEnabled(true);

        //Botões
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(2, 3));
        formulario.add(txtNome);
        formulario.add(txtCodigo);
        formulario.add(txtPreco);
        formulario.add(btnAdicionar);
        formulario.add(btnExcluir);
        
        //hora
        HoraInicio.setText("Data inicio do Pedido: "+horaAtual());
        formulario.add(HoraInicio);
        //formulario.add(HoraFinal);
        
        

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabela.getSelectedRowCount() == 0) {
                    btnExcluir.setEnabled(false);
                    btnAdicionar.setEnabled(true);
                } else {
                    btnAdicionar.setEnabled(false);
                    btnExcluir.setEnabled(true);
                    ItemTableModel modelo = (ItemTableModel) tabela.getModel();
                    int linha = tabela.getSelectedRow();
                    txtCodigo.setText((String)modelo.getValueAt(linha, 0));
                    txtNome.setText(modelo.getValueAt(linha, 1).toString());
                    txtPreco.setText((String)modelo.getValueAt(linha, 2));
                }
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemTableModel modelo = (ItemTableModel) tabela.getModel();
                Item p = new Item(
                    txtNome.getText(),
                    Integer.parseInt(txtCodigo.getText()),
                    Integer.parseInt(txtPreco.getText())   
                );
                modelo.add(p);
                txtNome.setText("");
                txtCodigo.setText("");
                txtPreco.setText("");
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRowCount()==0) return;
                DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
                modelo.removeRow(tabela.getSelectedRow());
            }
        });
        
     
    }

    public String horaAtual() {
        Date date = new Date();
        int minutos = date.getMinutes();
        int hora = date.getHours();
        int min = date.getMinutes(); //(retorna o ano atual subtraido 1900)
        int seg = date.getSeconds();
        String s = String.valueOf(hora + ":" + min + ":" + seg);
        return s;
    }
    
    

}
