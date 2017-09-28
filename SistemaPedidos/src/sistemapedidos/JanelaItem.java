package sistemapedidos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class JanelaItem extends JFrame {

    private JTextField txtNome = new JTextField(20);
    private JTextField txtCodigo = new JTextField(2);
    private JTextField txtPreco = new JTextField(40);
    private JButton btnAdicionar = new JButton("Adicionar");
    private JButton btnRemover = new JButton("Remover");
    private JButton btnSalvar = new JButton("Salvar");

    private JTable tabela;
    private List<Item> dados;

    public JanelaItem(List<Item> dados) throws HeadlessException {
        super("Tabelas 01");
        this.dados = dados;
        tabela = new JTable(new ItemTableModel(dados));
        btnSalvar.setEnabled(false);
        btnRemover.setEnabled(false);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(2, 3));
        formulario.add(txtCodigo);
        formulario.add(txtNome);
        formulario.add(txtPreco);
        formulario.add(btnSalvar);
        formulario.add(btnRemover);
        formulario.add(btnAdicionar);

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tabela.getSelectedRowCount() == 0) {
                    btnRemover.setEnabled(false);
                    btnSalvar.setEnabled(false);
                    btnAdicionar.setEnabled(true);
                } else {
                    btnAdicionar.setEnabled(false);
                    btnRemover.setEnabled(true);
                    btnSalvar.setEnabled(true);
                    ItemTableModel modelo = (ItemTableModel) tabela.getModel();
                    int linha = tabela.getSelectedRow();
                    txtCodigo.setText((String) modelo.getValueAt(linha, 0));
                    txtNome.setText(modelo.getValueAt(linha, 1).toString());
                    txtPreco.setText((String) modelo.getValueAt(linha, 2));
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
                txtNome.requestFocus();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRowCount() == 0) {
                    return;
                }
                DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                modelo.removeRow(tabela.getSelectedRow());
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRowCount() == 0) {
                    return;
                }
                ItemTableModel modelo = (ItemTableModel) tabela.getModel();
                int linha = tabela.getSelectedRow();
                for (int i = 0; i < tabela.getColumnCount(); i++) {
                    dados.get(linha).setCodigo(Integer.parseInt(txtCodigo.getText()));
                    dados.get(linha).setNome(txtNome.getText());
                    dados.get(linha).setPreco(Integer.parseInt(txtPreco.getText()));
                    txtNome.setText("");
                    txtCodigo.setText("");
                    txtPreco.setText("");
                    tabela.clearSelection();
                    txtNome.requestFocus();
                    System.out.println(dados.get(linha));
                }

            }
        });
    }

}
