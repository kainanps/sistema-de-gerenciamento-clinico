package Model.Dao;

import Connection.Conexao;
import Model.Bean.Paciente;
import Model.Bean.Remedio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Equipe SGC
 */

public class PacienteDAO {
    
    public boolean create(Paciente p){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        boolean res;
        try {
            stmt = con.prepareStatement("insert into pacientes(nome_paciente, sexo, data_nascimento, idade, bairro, cpf, sus, telefone) values (?,?,?,?,?,?,?,?)");
            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getSexo());
            stmt.setString(3,p.getNascimento());
            stmt.setInt(4,p.getIdade());
            stmt.setString(5,p.getBairro());
            stmt.setString(6,p.getCpf());
            stmt.setString(7,p.getSus());
            stmt.setString(8,p.getTelefone());
            stmt.execute();
            res = true;
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao salvar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        return res;        
    }
    
public List<Paciente> readForNome(String nome){
    
        Connection con = Conexao.getConnection();
        List<Paciente> pacientes = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {

            stmt = con.prepareStatement("SELECT * FROM pacientes WHERE nome_paciente LIKE ?");
            stmt.setString(1,"%"+nome+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Paciente p = new Paciente();
                 
                 p.setNome(rs.getString("nome_paciente"));
                 p.setIdade(rs.getInt("idade"));
                 p.setBairro(rs.getString("bairro"));
                 p.setNascimento(rs.getString("data_nascimento"));
                 p.setCpf(rs.getString("cpf"));
                 p.setSexo(rs.getString("sexo"));
                 p.setTelefone(rs.getString("telefone"));
                 p.setSus(rs.getString("sus"));
                 p.setId(rs.getInt("id"));
                 
                 pacientes.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return pacientes;
    }
public List<Paciente> read(int pag, int qtd_pag){
    
        Connection con = Conexao.getConnection();
        List<Paciente> pacientes = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
                        
            int de = (pag * qtd_pag) - qtd_pag;

            stmt = con.prepareStatement("SELECT * FROM pacientes ORDER BY nome_paciente ASC LIMIT ?, ?");
            stmt.setInt(1, de);
            stmt.setInt(2, qtd_pag);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
        
                 Paciente p = new Paciente();
                 
                 p.setNome(rs.getString("nome_paciente"));
                 p.setIdade(rs.getInt("idade"));
                 p.setBairro(rs.getString("bairro"));
                 p.setNascimento(rs.getString("data_nascimento"));
                 p.setCpf(rs.getString("cpf"));
                 p.setSexo(rs.getString("sexo"));
                 p.setTelefone(rs.getString("telefone"));
                 p.setSus(rs.getString("sus"));
                 p.setId(rs.getInt("id"));
                 
                 pacientes.add(p);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Página vazia.");
        }finally{
        
            Conexao.closeConnection(con, stmt, rs);
            
        }
        
        return pacientes;
    }

    public void update(Paciente p){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE pacientes set nome_paciente = ?, idade = ?, bairro = ?, data_nascimento = ?, cpf = ?, sexo = ?, telefone = ?, sus = ? WHERE id = ?");
            stmt.setString(1,p.getNome());
            stmt.setInt(2,p.getIdade());
            stmt.setString(3,p.getBairro());
            stmt.setString(4,p.getNascimento());
            stmt.setString(5,p.getCpf());
            stmt.setString(6,p.getSexo());
            stmt.setString(7,p.getTelefone());
            stmt.setString(8,p.getSus());
            stmt.setInt(9,p.getId());
            
            new HistoricoDeConsultaDAO().updatePaciente(p.getNome(), p.getBairro(), p.getId());
            new AgendamentoDAO().updatePaciente(p.getNome(), p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
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
            stmt = con.prepareStatement("DELETE FROM pacientes WHERE id = ?");
            
            stmt.setInt(1, id);
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Erro ao atualizar"+ex);
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt);
            
        }
        
    }

    public int numPags(int qtd_pag){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num_pags = 0;
        
        try {
            stmt = con.prepareStatement("SELECT COUNT(id) AS num_result FROM pacientes");
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

public List<Paciente> select(String paciente){
    
        Connection con = Conexao.getConnection();
        List<Paciente> pacientes = new ArrayList<> ();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM pacientes WHERE nome_paciente LIKE ?");
                stmt.setString(1,"%"+paciente+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Paciente p = new Paciente();
                
                p.setNome(rs.getString("nome_paciente"));
                p.setId(rs.getInt("id"));
                
                pacientes.add(p);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao selecionar");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt, rs);
            
        }   
        
        return pacientes;
    
    }
public String buscaBairro(String paciente){
    
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        Paciente p = new Paciente();
        ResultSet rs = null;

        try {
            
            stmt = con.prepareStatement("SELECT * FROM pacientes WHERE nome_paciente LIKE ?");
            stmt.setString(1,"%"+paciente+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                p.setBairro(rs.getString("bairro"));  
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao selecionar");
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            Conexao.closeConnection(con, stmt, rs);
            
        }   
        
        return p.getBairro();
    
    }
}