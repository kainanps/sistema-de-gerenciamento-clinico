
package Public;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class methods {
    public int pagina;
    public methods(int pag){
        pagina = pag;
    }
    
    public void disabledButtonDown(JButton j, int d){
        if(pagina - d <= 0){
            j.setEnabled(false);
        }else{
            j.setEnabled(true);
        }
    }
    public void disabledButtonUp(JButton j, int d){
        if(pagina - d >= 0){
            j.setEnabled(false);
        }else{
            j.setEnabled(true);
        }
    }
    
    public void disButton(JButton j1, JButton j2, JButton j3, JButton j4){
        j1.setEnabled(false);
        j2.setEnabled(false);
        j3.setEnabled(false);
        j4.setEnabled(false);
    }
    
    public void eneButton(JButton j1, JButton j2, JButton j3, JButton j4){
        j1.setEnabled(true);
        j2.setEnabled(true);
        j3.setEnabled(true);
        j4.setEnabled(true);
    }
    
    public void ocultaColuna(JTable tbl, int col){
        TableColumnModel  colunaModelo = tbl.getColumnModel();      
        colunaModelo.getColumn(col).setMinWidth(0);     
        colunaModelo.getColumn(col).setPreferredWidth(0);  
        colunaModelo.getColumn(col).setMaxWidth(0);
    }
    
    public void rowSorter(JTable tbl){
        DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
        tbl.setRowSorter(new TableRowSorter(modelo));    
    }
    
}
