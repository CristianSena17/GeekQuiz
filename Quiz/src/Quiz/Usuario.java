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
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno Tarde
 */
public class Usuario {
    private String Nome;
    private String Senha;
    private String Email;
    private String Tipo;

    public Usuario() {
    }

    public Usuario(String Nome) {
        
        this.Nome = Nome;

     int n = 0;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Usuario WHERE Nome = '" + this.Nome + "'";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                this.Nome = rs.getString("Nome");
                this.Senha = rs.getString("Senha");
                this.Email = rs.getString("Email");
                this.Tipo = rs.getString("Tipo");
                n++;
            }
            
            if (n == 0) {
                this.Nome = Nome;
                this.Senha = "";
                this.Email = "";
                this.Tipo = "";
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    public Usuario(String Nome, String Senha, String Email, String Tipo) {
        this.Nome = Nome;
        this.Senha = Senha;
        this.Email = Email;
        this.Tipo = Tipo;
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    
    
    public static ArrayList<Usuario> listar() {
        
        ArrayList<Usuario> usuarios = new ArrayList();
        Usuario usuario;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Usuario";
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                usuario = new Usuario();
                usuario.Nome = rs.getString("Nome");
                usuario.Senha = rs.getString("Senha");
                usuario.Email = rs.getString("Email");
                usuario.Tipo = rs.getString("Tipo");
                usuarios.add(usuario);
                
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    public static boolean logar(Usuario usuario) {
        
        int n=0;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Usuario where Nome='"+usuario.Nome+"' and "+"Senha=PASSWORD('"+usuario.Senha+"')";
            
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                n++;  
            }
            
            if(n==0){
                return false;
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public static boolean Cadastrar(Usuario usuario) {
        
        boolean n=false;
        
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "SELECT * FROM Usuario where Nome='"+usuario.Nome+"'";
            
            
            ResultSet rs = st.executeQuery(sql);
            
            n=rs.isBeforeFirst();
            
            if (!n){
                    try {


                    String sql2 = "INSERT INTO Usuario VALUES ('"+ usuario.Nome +"', PASSWORD('"+ usuario.Senha +"') ,'"+ usuario.Email +"','c')";
                    st.executeUpdate(sql2);
                    return true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            rs.close();
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public void inserir(Usuario usuario) {
              
        try {
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            
            String sql = "INSERT INTO Usuario VALUES ('"+ Nome +"', PASSWORD('"+ Senha +"') ,'"+ Email +"','"+ Tipo +"')";
            
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
            
            String sql = "UPDATE Usuario SET Senha = PASSWORD('"+ Senha +"') , Email = '"+ Email +"', Tipo = '"+ Tipo +"' WHERE Nome = '"+ Nome +"'";
            
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
            
            String sql = "DELETE FROM Usuario WHERE Nome = '"+ Nome +"'";
            
            st.executeUpdate(sql);
            
            st.close();
            Conexao.closeAll(conexao);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMail (String destinatario,String senha) throws AddressException, javax.mail.MessagingException {   
      //String teste= null;
      String mailServer= "smtp.gmail.com";
      String remetente = "greekquizti04t@gmail.com";
      
      String assunto = "Recuperação de Senha - GeekQuiz";
      String mensagem = "Sua senha de usuario é "+senha;
      Properties props = System.getProperties();   
      props.put ("mail.smtp.host",mailServer);  // smtp.live.com  smtp.yahoo.com.br smtp.gmail.com
      props.put("mail.smtp.auth", "true");    
      props.put("mail.debug", "true");    
      props.put("mail.smtp.debug", "true");    
      props.put("mail.mime.charset", "ISO-8859-1");    
      props.put("mail.smtp.port", "465");    //25
      props.put("mail.smtp.starttls.enable", "true");    
      props.put("mail.smtp.socketFactory.port", "465");    
      props.put("mail.smtp.socketFactory.fallback", "false");    
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
      Session session = Session.getDefaultInstance(props);//recebe props   
      InternetAddress dest = new InternetAddress (destinatario);   
      InternetAddress remete = new InternetAddress (remetente);   
      Message msg = new MimeMessage(session);   
     // msg.setSentDate(new Date());//novo   
      msg.setFrom(remete);   
      msg.setRecipient( Message.RecipientType.TO, dest );   
      msg.setSubject (assunto);   
      msg.setContent (mensagem.toString(), "text/HTML");   
      Transport transport = session.getTransport("smtp");   
      transport.connect(mailServer,"greekquizti04t","1dois3quatro");   
      msg.saveChanges();   
      transport.sendMessage(msg, msg.getAllRecipients());   
      transport.close();   
      JOptionPane.showMessageDialog(null, "Email enviado com sucesso!!!");  
   } 
    
    @Override
    public String toString() {
        return "Usuario{" + "Nome=" + Nome + ", Senha=" + Senha + ", Email=" + Email + ", Tipo=" + Tipo + '}';
    }

    
    
    
    
}
