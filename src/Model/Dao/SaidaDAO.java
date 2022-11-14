/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Connection.Conexao;
import Model.Bean.Saida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipe GCE
 */
public class SaidaDAO {
    
     public boolean create(Saida en){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        boolean res;
        
        try {
            stmt = con.prepareStatement("insert into saidas(remedio, quantidade, data, hora) values (?,?,?,?)");
            stmt.setString(1,en.getRemedio());
            stmt.setInt(2,en.getQuantidade());
            stmt.setString(3,en.getData());
            stmt.setString(4,en.getHora());
            stmt.execute();

            stmt = con.prepareStatement("UPDATE medicamentos SET quantidade = quantidade - ? WHERE medicamento = ?");
            stmt.setInt(1,en.getQuantidade());
            stmt.setString(2,en.getRemedio());
            stmt.executeUpdate();

            res = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }finally{
            
            Conexao.closeConnection(con, stmt);            
        }
        return res;        
    }
     
        
    public void update(Saida sa){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("UPDATE saidas SET data = ?, hora = ? WHERE id = ?");
            
            stmt.setString(1,sa.getData());
            stmt.setString(2,sa.getHora());
            stmt.setInt(3,sa.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
            
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null,"Erro ao atualizar");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        
    }
    
    public void delete(int id){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("DELETE FROM saidas WHERE id = ?");
            
            stmt.setInt(1, id);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso!");
            
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null,"Erro ao excluir");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        
    }
    
    public int qtdRemedios(String nome){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int qtd = 0;
        
        try {
            stmt = con.prepareStatement("SELECT quantidade FROM medicamentos WHERE medicamento = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            
            rs.next();
            qtd = rs.getInt("quantidade");
            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return qtd;
    }
    
    public List<Saida> read(int pag, int qtd_pag){
    
        Connection con = Conexao.getConnection();
        List<Saida> saidas = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            int de = (pag * qtd_pag) - qtd_pag;
            
            if (de < 1)
                de = 1;

            stmt = con.prepareStatement("SELECT * FROM saidas ORDER BY data ASC LIMIT ?, ?");
            stmt.setInt(1, de);
            stmt.setInt(2, qtd_pag);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Saida en = new Saida();
                 
                 en.setId(rs.getInt("id"));
                 en.setRemedio(rs.getString("remedio"));
                 en.setData(rs.getString("data"));
                 en.setHora(rs.getString("hora"));
                 en.setQuantidade(rs.getInt("quantidade"));
                 
                 saidas.add(en);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return saidas;
    }
        
    public List<Saida> readForNome(String nome){
    
        Connection con = Conexao.getConnection();
        List<Saida> saidas = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            

            stmt = con.prepareStatement("SELECT * FROM saidas WHERE remedio LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Saida s = new Saida();
                 
                 s.setId(rs.getInt("id"));
                 s.setData(rs.getString("data"));
                 s.setHora(rs.getString("hora"));
                 s.setQuantidade(rs.getInt("quantidade"));
                 s.setRemedio(rs.getString("remedio"));
                 
                 saidas.add(s);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return saidas;
    }

    public int numPags(int qtd_pag){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num_pags = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(id) AS num_result FROM saidas");
            rs = stmt.executeQuery();
            
            rs.next();
            num_pags = (int) (Math.ceil(rs.getInt("num_result")/(qtd_pag)));
            
            float dec = rs.getInt("num_result")%qtd_pag;
            
            if(dec != 0)
                num_pags++;
            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        return num_pags;
    }
}