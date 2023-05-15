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
        //dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");  
        //dataSource​.setUrl("jdbc:mysql://localhost:3306/hemeratech?serverTimezone=America/Sao_Paulo");
        //dataSource​.setUsername("root");
        //dataSource​.setPassword("urubu100");  
        
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource​.setUrl("jdbc:mysql://52.90.114.219:3306/hemeraTech?autoReconnect=true&useSSL=false");

        dataSource​.setUsername("root");
        dataSource​.setPassword("urubu100");

        this.connection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConnection() {

        return connection;

    }

}
