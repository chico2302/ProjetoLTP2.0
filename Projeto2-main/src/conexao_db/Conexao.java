package conexao_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/db_viagem";
    private static final String USUARIO = "root"; //mudar pro usuario ou deixar como esta caso ele nao tenha sido alterado
    private static final String SENHA = "Franc1sco13!"; //mudar pra senha do dispositivo

    
    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(URL, USUARIO, SENHA);

    }

    public static void desconectar(Connection conn) {

        try {

            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (SQLException e) {

            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

}