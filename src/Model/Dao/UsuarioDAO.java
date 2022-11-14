package Model.Dao;

import Connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Renata
 */
public class UsuarioDAO {
    public boolean login(String u, String s){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        String sql = "SELECT * FROM users WHERE usuario = ? AND senha = ?";
        
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u);
            stmt.setString(2, s);
            rs = stmt.executeQuery();
            
            while (rs.next()){  
                    check = true;
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Página vazia.");
            JOptionPane.showMessageDialog(null,"Erro: "+e);
            
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return check;
        
    }
}
