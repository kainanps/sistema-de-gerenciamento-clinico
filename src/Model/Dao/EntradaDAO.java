/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Connection.Conexao;
import Model.Bean.Entrada;
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
 * @author Renata
 */
public class EntradaDAO {
    public boolean create(Entrada en){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        boolean res;
        try {
            stmt = con.prepareStatement("insert into entradas(remedio, quantidade, data, hora) values (?,?,?,?)");
            stmt.setString(1,en.getRemedio());
            stmt.setInt(2,en.getQuantidade());
            stmt.setString(3,en.getData());
            stmt.setString(4,en.getHora());
            stmt.execute();

            stmt = con.prepareStatement("UPDATE medicamentos SET quantidade = quantidade + ? WHERE medicamento = ?");
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
    
    public void update(Entrada en){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("UPDATE entradas SET data = ?, hora = ? WHERE id = ?");
            
            stmt.setString(1,en.getData());
            stmt.setString(2,en.getHora());
            stmt.setInt(3,en.getId());
            
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

            stmt = con.prepareStatement("DELETE FROM entradas WHERE id = ?");
            
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
    
    public List<Entrada> read(int pag, int qtd_pag){
    
        Connection con = Conexao.getConnection();
        List<Entrada> entradas = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            int de = (pag * qtd_pag) - qtd_pag;
            
            if(de < 1)
                de = 1;

            stmt = con.prepareStatement("SELECT * FROM entradas ORDER BY data DESC LIMIT ?, ?");
            stmt.setInt(1, de);
            stmt.setInt(2, qtd_pag);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Entrada en = new Entrada();
                 
                 en.setId(rs.getInt("id"));
                 en.setData(rs.getString("data"));
                 en.setHora(rs.getString("hora"));
                 en.setQuantidade(rs.getInt("quantidade"));
                 en.setRemedio(rs.getString("remedio"));
                 
                 entradas.add(en);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return entradas;
    }
    
    public List<Entrada> readForNome(String nome){
    
        Connection con = Conexao.getConnection();
        List<Entrada> entradas = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            

            stmt = con.prepareStatement("SELECT * FROM entradas WHERE remedio LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Entrada en = new Entrada();
                 
                 en.setId(rs.getInt("id"));
                 en.setData(rs.getString("data"));
                 en.setHora(rs.getString("hora"));
                 en.setQuantidade(rs.getInt("quantidade"));
                 en.setRemedio(rs.getString("remedio"));
                 
                 entradas.add(en);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return entradas;
    }
    public int numPags(int qtd_pag){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num_pags = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(id) AS num_result FROM entradas");
            rs = stmt.executeQuery();
            
            rs.next();
            num_pags = (int) Math.ceil(rs.getInt("num_result")/qtd_pag);
            
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