package Model.Dao;

import Connection.Conexao;
import Model.Bean.HistoricoDeConsulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HistoricoDeConsultaDAO {
    
    public List<HistoricoDeConsulta> historicoConsultas(int pag, int qtd_pag){
    
        Connection con = Conexao.getConnection();
        List<HistoricoDeConsulta> consultas = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            int de = (pag * qtd_pag) - qtd_pag;
            
            stmt = con.prepareStatement("SELECT * FROM relatorio_consultas  ORDER BY data DESC LIMIT ?, ?");
            stmt.setInt(1, de);
            stmt.setInt(2, qtd_pag);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 HistoricoDeConsulta a = new HistoricoDeConsulta();
                 
                 a.setId(rs.getInt("id"));
                 a.setPaciente(rs.getString("paciente"));
                 a.setProcedimento(rs.getString("procedimento"));
                 a.setData(rs.getString("data"));
                 a.setHora(rs.getString("hora"));
                 a.setBairro(rs.getString("bairro"));
                 
                 consultas.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return consultas;
    }
    
    public List<HistoricoDeConsulta> hcForPaciente(String pac){
    
        Connection con = Conexao.getConnection();
        List<HistoricoDeConsulta> consultas = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement("SELECT * FROM relatorio_consultas WHERE paciente LIKE ?");
            stmt.setString(1,"%"+pac+"%");

            rs = stmt.executeQuery();

            while(rs.next()){

                 HistoricoDeConsulta a = new HistoricoDeConsulta();

                 a.setId(rs.getInt("id"));
                 a.setPaciente(rs.getString("paciente"));
                 a.setProcedimento(rs.getString("procedimento"));
                 a.setData(rs.getString("data"));
                 a.setHora(rs.getString("hora"));
                 a.setBairro(rs.getString("bairro"));

                 consultas.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }finally{

            Conexao.closeConnection(con, stmt, rs);

        }
        
        return consultas;
    }
        
    public int numPags(int qtd_pag){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num_pags = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(id) AS num_result FROM relatorio_consultas");
            rs = stmt.executeQuery();
            
            rs.next();
            num_pags = (int) rs.getInt("num_result")/qtd_pag;
            
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
    
    public void create(HistoricoDeConsulta h){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        
        String hora = hourFormat.format(date);
        String data = dateFormat.format(date);
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO relatorio_consultas (paciente, data, hora, procedimento, bairro, idpaciente) VALUES (?,?,?,?,?,?)");
            
            stmt.setString(1,h.getPaciente());
            stmt.setString(2,data);
            stmt.setString(3,hora);
            stmt.setString(4,h.getProcedimento());
            stmt.setString(5,h.getBairro());
            stmt.setInt(6,h.getIdpaciente());
            stmt.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao finalizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(HistoricoDeConsulta h){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE relatorio_consultas set  procedimento = ?, data = ?, hora = ? WHERE id = ?");
            stmt.setString(1,h.getProcedimento());
            stmt.setString(2,h.getData());
            stmt.setString(3,h.getHora());
            stmt.setInt(4,h.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        
    }
    
    public void updatePaciente(String nome, String bairro, int id){
        
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE relatorio_consultas set paciente = ?, bairro = ? WHERE idpaciente =  ?");
            stmt.setString(1,nome);
            stmt.setString(2,bairro);
            stmt.setInt(3,id);
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        
    }
    
    public void delete(int id){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM relatorio_consultas WHERE id = ?");
            
            stmt.setInt(1,id);
            stmt.execute();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        
    }
}
