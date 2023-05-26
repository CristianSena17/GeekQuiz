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
public class Questionario {
    private int Correta;
    private int id;
    private String Descricao;
    private Tema idTema;

    public Questionario() {
    }

    public Questionario(int id) {
        this.id = id;
        
        int n = 0;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Questionario WHERE id = '" + this.id + "'";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                this.idTema =new Tema( rs.getInt("Tema_idTema"));
                this.id = rs.getInt("id");
                this.Correta = rs.getInt("Correta");
                this.Descricao = rs.getString("Descricao");
                n++;
            }
             
            if (n == 0) {
                this.Correta = 0;
                this.Descricao = "";
                this.idTema = null;
                this.id = id;
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Questionario(int Correta, int id, String Descricao, Tema idTema) {
        this.Correta = Correta;
        this.id = id;
        this.Descricao = Descricao;
        this.idTema = idTema;
    }

    public int getCorreta() {
        return Correta;
    }

    public void setCorreta(int Correta) {
        this.Correta = Correta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    
    public static ArrayList<Questionario> listar() {
        
        ArrayList<Questionario> questoes = new ArrayList();
        Questionario questao;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Questionario";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                questao = new Questionario();
                
                questao.Descricao = rs.getString("Descricao");
                questao.id = rs.getInt("id");
                questao.idTema =new Tema( rs.getInt("Tema_idTema"));
                questao.Correta = rs.getInt("Correta");
                questoes.add(questao);
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return questoes;
    }
    
    public void inserir() {
              
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "INSERT INTO Questionario VALUES ('" + Correta + "','" + idTema + "','" + id + "','" + Descricao + "')";
            
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
            
            String sql = "UPDATE Questionario SET Correta = '" + Correta + "' WHERE id = '" + id + "'";
            
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
            
            String sql = "DELETE FROM Pergunata WHERE id = '" + id + "'";
            
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
        return "Questionario{" + "Correta=" + Correta + ", id=" + id + ", Descricao=" + Descricao + ", idTema=" + idTema + '}';
    }


    public static ArrayList<Questionario> questoesDoTema(Tema tema) {
        ArrayList<Questionario> questoes = new ArrayList();
        Questionario questao;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Questionario WHERE Tema_idTema = " + tema.getIdTema();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                questao = new Questionario();
                
                questao.Descricao = rs.getString("Descricao");
                questao.id = rs.getInt("id");
                questao.idTema =new Tema( rs.getInt("Tema_idTema"));
                questao.Correta = rs.getInt("Correta");
                questoes.add(questao);
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return questoes;
    }
    
}
