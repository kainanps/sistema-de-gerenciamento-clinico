/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Connection.Conexao;
import Model.Bean.Agendamento;
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
/**
 *
 * @author Renata
 */
public class AgendamentoDAO {
    
    public boolean agendar(Agendamento a){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        boolean res;
        try {
            if(a.getStatus().equals("Finalizado")){
                HistoricoDeConsultaDAO hdao = new HistoricoDeConsultaDAO();
                HistoricoDeConsulta h = new HistoricoDeConsulta();
                PacienteDAO pdao = new PacienteDAO();
                String bairro = pdao.buscaBairro(a.getPaciente());
                h.setPaciente(a.getPaciente());
                h.setProcedimento(a.getProcedimento());
                h.setBairro(bairro);
                h.setIdpaciente(a.getIdpaciente());
                
                hdao.create(h);
            }else{
                
                stmt = con.prepareStatement("insert into agendamentos(paciente, area, procedimento, hora, data, status, idpaciente) values (?,?,?,?,?,?,?)");
                stmt.setString(1,a.getPaciente());
                stmt.setString(2,a.getArea());
                stmt.setString(3,a.getProcedimento());
                stmt.setString(4,a.getHora());
                stmt.setString(5,a.getData());
                stmt.setString(6,a.getStatus());
                stmt.setInt(7,a.getIdpaciente());
                stmt.execute();
                
            }
            
            res = true;
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao agendar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }finally{
            
            Conexao.closeConnection(con, stmt);
        }
        return res;        
    }
    
    public void update(Agendamento a){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            if(!"Finalizado".equals(a.getStatus())){
                
                stmt = con.prepareStatement("UPDATE agendamentos SET area = ?, procedimento = ?, hora = ?, data = ?, status = ? WHERE id = ?");
                stmt.setString(1,a.getArea());
                stmt.setString(2,a.getProcedimento());
                stmt.setString(3,a.getHora());
                stmt.setString(4,a.getData());
                stmt.setString(5,a.getStatus());
                stmt.setInt(6,a.getId());
                stmt.execute();
                
                JOptionPane.showMessageDialog(null,"Atualizado com sucesso ");
                
            }else{
                
                finalizar(a);
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void updatePaciente(String nome, int id){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            
                stmt = con.prepareStatement("UPDATE agendamentos SET paciente = ? WHERE idpaciente = ?");
                stmt.setString(1,nome);
                stmt.setInt(2,id);
                stmt.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao Atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete(int id){
        
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM agendamentos WHERE id = ?");
            stmt.setInt(1,id);
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            
            JOptionPane.showConfirmDialog(null,"Erro ao excluir"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public List<Agendamento> agendados(int pag, int qtd_pag){
    
        Connection con = Conexao.getConnection();
        List<Agendamento> agendamentos = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
                        
            int de = (pag * qtd_pag) - qtd_pag;
            
            stmt = con.prepareStatement("SELECT * FROM agendamentos ORDER BY data DESC LIMIT ?, ?");
            
            stmt.setInt(1, de);
            stmt.setInt(2, qtd_pag);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Agendamento a = new Agendamento();
                 
                 a.setId(rs.getInt("id"));
                 a.setIdpaciente(rs.getInt("idpaciente"));
                 a.setPaciente(rs.getString("paciente"));
                 a.setArea(rs.getString("area"));
                 a.setProcedimento(rs.getString("procedimento"));
                 a.setHora(rs.getString("hora"));
                 a.setData(rs.getString("data"));
                 a.setStatus(rs.getString("status"));
                 
                 agendamentos.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return agendamentos;
    }

    public int numPags(int qtd_pag){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num_pags = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(id) AS num_result FROM agendamentos");
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

    public List<Agendamento> agendadosDia(){
    
        Connection con = Conexao.getConnection();
        List<Agendamento> agendamentos = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String data = dateFormat.format(date);
        
        try {
                        
            stmt = con.prepareStatement("SELECT * FROM agendamentos WHERE data = ?");
            stmt.setString(1, data);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Agendamento a = new Agendamento();
                 
                 a.setId(rs.getInt("id"));
                 a.setPaciente(rs.getString("paciente"));
                 a.setArea(rs.getString("area"));
                 a.setProcedimento(rs.getString("procedimento"));
                 a.setHora(rs.getString("hora"));
                 a.setData(rs.getString("data"));
                 a.setStatus(rs.getString("status"));
                 a.setIdpaciente(rs.getInt("idpaciente"));
                 
                 agendamentos.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return agendamentos;
    }
    
    public List<Agendamento> readForPaciente(String pac){
    
        Connection con = Conexao.getConnection();
        List<Agendamento> agendamentos = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String data = dateFormat.format(date);
        
        try {
                        
            stmt = con.prepareStatement("SELECT * FROM agendamentos WHERE paciente LIKE ?");
            
            stmt.setString(1,"%"+pac+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Agendamento a = new Agendamento();
                 
                 a.setId(rs.getInt("id"));
                 a.setIdpaciente(rs.getInt("idpaciente"));
                 a.setPaciente(rs.getString("paciente"));
                 a.setArea(rs.getString("area"));
                 a.setProcedimento(rs.getString("procedimento"));
                 a.setHora(rs.getString("hora"));
                 a.setData(rs.getString("data"));
                 
                 agendamentos.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return agendamentos;
    }

    public void finalizar(Agendamento a){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        HistoricoDeConsultaDAO hdao = new HistoricoDeConsultaDAO();
        HistoricoDeConsulta h = new HistoricoDeConsulta();
        PacienteDAO pdao = new PacienteDAO();
        String bairro = pdao.buscaBairro(a.getPaciente());
        
        try {
            stmt = con.prepareStatement("DELETE FROM `agendamentos` WHERE `agendamentos`.`id` = ?");
            
            stmt.setInt(1,a.getId());
            stmt.execute();
            
            h.setPaciente(a.getPaciente());
            h.setProcedimento(a.getProcedimento());
            h.setBairro(bairro);
            h.setIdpaciente(a.getIdpaciente());
            
            hdao.create(h);
            
            JOptionPane.showMessageDialog(null,"Finalizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
}