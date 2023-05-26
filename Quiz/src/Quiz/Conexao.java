/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Renan
 */
public class Conexao {
    
    private static final String STR_DRIVER = "com.mysql.jdbc.Driver";
    // identificacao url do driver do banco de dados

    private static final String USER = "root"; // usuario do database
    private static final String PASSWORD = "root"; // senha do database

    private static final String IP = "localhost";
    // "localhost ou 192.168.0.1" para banco de dados em maquina local
    // ou o ip ou nome da maquina servidor de banco de dados

    private static final String DATABASE = "Quiz";
    // Nome do banco de dados que ainda nao sera usado

    private static final String STR_CON =
    "jdbc:mysql://" + IP + ":3307/" + DATABASE;
    // Aqui é criado uma string str_com que tem o endereco completo
    // da conexao

    public static Connection getConexao() throws SQLException {
	Connection conn = null;
	try {
		Class.forName(STR_DRIVER);
		conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
	return conn;
	// acima obtenho uma conexao passando os parametros do database, usuario e senha

	} catch (ClassNotFoundException e) {
		String errorMsg = "Driver nao encontrado";
		throw new SQLException(errorMsg, e);
		// acima faço uma exceção para o driver do MySQL nao encontrado
	} catch (SQLException e) {
		String errorMsg = "Erro ao obter a conexao";
		throw new SQLException(errorMsg, e);
		// acima tenho uma excessao caso dê outro problema de conexao da
		// aplicacao com o banco como problemas de rede, servico mysql parado, etc
	}
    }	
    // observe que para criar uma conexao é necessário um ter um try catch
    // que permite dar outra saida ao codigo em caso de erro, parecido com IF/ELSE

    public static void closeAll(Connection conn) {
            try {
                    if (conn != null) {
                            conn.close();
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    // acima tenho um metodo que fecha a conexao, importante para nao ficar
    // com instancias desnecessarias conectadas ao banco de dados. 
}
