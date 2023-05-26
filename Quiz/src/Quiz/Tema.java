/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Aluno Tarde
 */
public class Tema {
    private int idTema;
    private String nmTema;

    public Tema() {
    }

    public Tema(int idTema) {
        this.idTema = idTema;
        
        
           int n = 0;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Tema WHERE idTema = '" + this.idTema + "'";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                this.idTema = rs.getInt("idTema");
                this.nmTema = rs.getString("nmTema");
                n++;
            }
            
            if (n == 0) {
                this.idTema = idTema;
                this.nmTema = "";
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tema(int idTema, String nmTema) {
        this.idTema = idTema;
        this.nmTema = nmTema;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getNmTema() {
        return nmTema;
    }

    public void setNmTema(String nmTema) {
        this.nmTema = nmTema;
    }
    
    public static ArrayList<Tema> listar() {
        
        ArrayList<Tema> temas = new ArrayList();
        Tema tema;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Tema";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                tema = new Tema();
                tema.idTema = rs.getInt("idTema");
                tema.nmTema = rs.getString("nmTema");
                temas.add(tema);
                
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return temas;
    }
    
    
    
    public void inserir() {
              
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "INSERT INTO Tema VALUES ('" + idTema + "','" + nmTema + "')";
            
            st.executeUpdate(sql);
            
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
  
    }
    
    public void editar() {
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "UPDATE Tema SET nmTema = '" + nmTema + "' WHERE idTema = '" + idTema + "'";
            
            st.executeUpdate(sql);
            
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluir() {
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "DELETE FROM Tema WHERE idTema = '" + idTema + "'";
            
            st.executeUpdate(sql);
            
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Tema{" + "idTema=" + idTema + ", nmTema=" + nmTema + '}';
    }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

