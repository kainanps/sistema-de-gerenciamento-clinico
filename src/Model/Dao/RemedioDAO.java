/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Connection.Conexao;
import Model.Bean.Remedio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class RemedioDAO {
    public boolean create(Remedio r){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        boolean res;
        try {
            stmt = con.prepareStatement("insert into medicamentos(medicamento, categoria, descricao, quantidade) values (?,?,?,?)");
            stmt.setString(1,r.getNome());
            stmt.setString(2,r.getCategoria());
            stmt.setString(3,r.getDescricao());
            stmt.setInt(4,0);
            stmt.execute();
            res = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        return res;        
    }
    
    public void update(Remedio r){
        
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE medicamentos SET medicamento = ?, categoria = ?, descricao = ? WHERE id = ?");
            stmt.setString(1,r.getNome());
            stmt.setString(2,r.getCategoria());
            stmt.setString(3,r.getDescricao());
            stmt.setInt(4,r.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar!");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
    }
    
    public void delete(int id){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            
            stmt = con.prepareStatement("DELETE FROM medicamentos WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir!");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
    }
    
    public List<Remedio> select(String remedio){
    
        Connection con = Conexao.getConnection();
        List<Remedio> remedios = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM estoques WHERE remedio LIKE ?");
                stmt.setString(1,"%"+remedio+"%");
            rs = stmt.executeQuery();
            
            if(rs.next()){
                do{
                    
                    Remedio r = new Remedio();
                
                    r.setNome(rs.getString("remedio"));

                    remedios.add(r);
                    
                }while(rs.next());
            }else{
                JOptionPane.showMessageDialog(null, "Esse remedio não está cadastrado");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao selecionar");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt, rs);
            
        }   
        
        return remedios;
    
    }
    public List<Remedio> read(int pag, int qtd_pag){
    
        Connection con = Conexao.getConnection();
        List<Remedio> remedios = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            int de = (pag * qtd_pag) - qtd_pag;

            stmt = con.prepareStatement("SELECT * FROM medicamentos ORDER BY medicamento ASC LIMIT ?, ?");
            stmt.setInt(1, de);
            stmt.setInt(2, qtd_pag);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Remedio r = new Remedio();
                 r.setId(rs.getInt("id"));
                 r.setNome(rs.getString("medicamento"));
                 r.setCategoria(rs.getString("categoria"));
                 r.setDescricao(rs.getString("descricao"));
                 r.setQuantidade(rs.getInt("quantidade"));
 
                 remedios.add(r);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return remedios;
    }
    
    public List<Remedio> readForNome(String nome){
    
        Connection con = Conexao.getConnection();
        List<Remedio> remedios = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM medicamentos WHERE medicamento LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Remedio r = new Remedio();
                 r.setId(rs.getInt("id"));
                 r.setNome(rs.getString("medicamento"));
                 r.setCategoria(rs.getString("categoria"));
                 r.setDescricao(rs.getString("descricao"));
                 r.setQuantidade(rs.getInt("quantidade"));
 
                 remedios.add(r);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return remedios;
    }

    public int numPags(int qtd_pag){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num_pags = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(id) AS num_result FROM medicamentos");
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



