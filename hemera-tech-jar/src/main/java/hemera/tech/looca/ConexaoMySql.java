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
public class ConexaoMySql {

    private JdbcTemplate connection;

    public ConexaoMySql() {

        BasicDataSource dataSource = new BasicDataSource();

        //CONEXAO MYSQL
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");  
        dataSource​.setUrl("jdbc:mysql://localhost:3306/hemeratech?serverTimezone=America/Sao_Paulo");
        dataSource​.setUsername("root");
        dataSource​.setPassword("abbeydawn2001");  
        
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");
     //dataSource​.setUrl("jdbc:mysql://100.26.99.31:3306/HemeraTech?autoReconnect=true&useSSL=false");

     //   dataSource​.setUsername("root");
    // dataSource​.setPassword("urubu100");

        this.connection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConnection() {

        return connection;

    }

}
