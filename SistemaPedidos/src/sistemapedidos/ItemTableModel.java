package sistemapedidos;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ItemTableModel extends AbstractTableModel {

    private List<Item> itens;

    public ItemTableModel(List<Item> dados) {
        itens = dados;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return itens.get(rowIndex).getCodigo();
            case 1:
                return itens.get(rowIndex).getNome();
            case 2:
                return itens.get(rowIndex).getPreco();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Preço";
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void add(Item p) {
       itens.add(p);
       //this.fireTableDataChanged();
       this.fireTableRowsInserted(itens.size()-1, itens.size()-1);
    }
    
    public void remove(int l){
        itens.remove(l);
        this.fireTableRowsDeleted(l, l);
    }

    
}
