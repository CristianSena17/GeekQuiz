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
public class Respostas {
    private int idRespostas;
    private String Descricao;
    private Tema idTema;
    private Questionario idQuestionario;
    
    

    public Respostas() {
    }

    public Respostas(int idRespostas, Tema idTema, Questionario idQuestionario) {
        this.idRespostas = idRespostas;
        this.idTema = idTema;
        this.idQuestionario = idQuestionario;
        
        int n = 0;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Respostas WHERE idRespostas = '" + this.idRespostas  + "' and Questionario_Tema_idTema = '" + this.idTema  + "' and Questionario_id = '" + this.idQuestionario + "'";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                this.idRespostas = rs.getInt("idRespostas");
                this.Descricao = rs.getString("Descricao");
                this.idTema =new Tema( rs.getInt("Questionario_Tema_idTema"));
                this.idQuestionario =new Questionario(rs.getInt("Questionario_id"));
                //this.idQuestionario =new Questionario(rs.getInt("Questionario_id"),idTema);
                n++;
            }
            
            if (n == 0) {
                this.idRespostas = idRespostas;
                this.Descricao = "";
                this.idTema = idTema;
                this.idQuestionario = idQuestionario;
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public Respostas(int idRespostas, String Descricao, Tema idTema, Questionario idQuestionario) {
        this.idRespostas = idRespostas;
        this.Descricao = Descricao;
        this.idTema = idTema;
        this.idQuestionario = idQuestionario;
    }

    public int getIdRespostas() {
        return idRespostas;
    }

    public void setIdRespostas(int idRespostas) {
        this.idRespostas = idRespostas;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Tema getIdTema() {
        return idTema;
    }

    public void setIdTema(Tema idTema) {
        this.idTema = idTema;
    }

    public Questionario getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Questionario idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    
    public static ArrayList<Respostas> listar() {
        
        ArrayList<Respostas> respostas = new ArrayList();
        Respostas resposta;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Respostas";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                resposta = new Respostas();
                resposta.idRespostas = rs.getInt("idRespostas");
                resposta.Descricao = rs.getString("Descricao");
                resposta.idTema =new Tema( rs.getInt("Questionario_Tema_idTema"));
                resposta.idQuestionario =new Questionario(rs.getInt("Questionario_id"));
                respostas.add(resposta);
                
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return respostas;
    }
    
    
    public void inserir() {
              
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "INSERT INTO Respostas VALUES ('" + idRespostas + "','" + Descricao + "','" + idTema + "','" + idQuestionario + "')";
            
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
            
            String sql = "UPDATE Respostas SET Descricao = '" + Descricao + "' WHERE idRespostas = '" + idRespostas + "' and Questionario_Tema_idTema = '" + idTema + "' and Questionario_id = '" + idQuestionario + "'";
            
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
            
            String sql = "DELETE FROM Respostas WHERE idRespostas = '" + idRespostas + "' and Questionario_Tema_idTema = '" + idTema + "' and Questionario_id = '" + idQuestionario + "'";
            
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
        return "Respostas{" + "idRespostas=" + idRespostas + ", Descricao=" + Descricao + ", idTema=" + idTema + ", idQuestionario=" + idQuestionario + '}';
    }
   
    public static ArrayList<Respostas> respostasDoTema(Tema tema,Questionario questionario) {
        ArrayList<Respostas> respostas = new ArrayList();
        Respostas resposta;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Respostas WHERE Questionario_Tema_idTema = " + tema.getIdTema()+" and Questionario_Tema_idTema = "+questionario.getId();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                resposta = new Respostas();
                
                resposta.Descricao = rs.getString("Descricao");
                resposta.idRespostas = rs.getInt("idRespostas");
                resposta.idTema =new Tema( rs.getInt("Questionario_Tema_idTema"));
                resposta.idQuestionario =new Questionario (rs.getInt("Questionario_id"));
                respostas.add(resposta);
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return respostas;
    }
    
}
