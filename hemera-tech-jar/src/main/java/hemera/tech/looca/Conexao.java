/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author VivianaMarca
 */
public class Conexao {

    private JdbcTemplate connection;

    public Conexao() {

        BasicDataSource dataSource = new BasicDataSource();

         //CONEXAO SQL SERVER
        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource​.setUrl("jdbc:sqlserver://hemeratech.database.windows.net:1433;database=hemeratech;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");

        dataSource​.setUsername("hemeratech");
        dataSource​.setPassword("#Gfgrupo7");  
        
       //CONEXAO MYSQL
       //dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");  
       //dataSource​.setUrl("jdbc:mysql://localhost:3306/hemeratech?serverTimezone=America/Sao_Paulo");

        //dataSource​.setUsername("root");
        //dataSource​.setPassword("abbeydawn2001");  
        
        

        this.connection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConnection() {

        return connection;

    }

}
