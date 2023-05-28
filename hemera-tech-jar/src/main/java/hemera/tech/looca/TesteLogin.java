/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import com.github.britooo.looca.api.group.discos.Disco;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author VivianaMarca
 */
public class TesteLogin {

    public static void main(String[] args) {
        API api = new API();

        Conexao conexaoAzure = new Conexao();
        JdbcTemplate conAzure = conexaoAzure.getConnection();

        ConexaoMySql conexaoMySql = new ConexaoMySql();
        JdbcTemplate conMySql = conexaoMySql.getConnection();
        

        Scanner leitor = new Scanner(System.in);
        System.out.println("Seja bem vindo de volta ao sistema de monitoramento da hemera Tech");
        System.out.println("Email:");
        String emailDigitado = leitor.nextLine();
        System.out.println("Senha:");
        String senhaDigitada = leitor.nextLine();

        //SELECT PARA AUTENTICAR LOGIN
        String selectUsuario = "select nome,sobrenome,email,senha,idEmpresa from Funcionario where email= ? and  senha= ?";
        Usuario usuarioLogadoAzure = conAzure.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);
        Usuario usuarioLogadoMySql = conMySql.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);
        
        //System.out.println(usuarioLogado);
        if (usuarioLogadoAzure == null || usuarioLogadoMySql == null) {
            System.out.println("Usuário ou senha incorretos");                   
        } else {
            //PUXAR SE EXISTE COMPUTADORES DA EMPRESA COM MACADDRESS 
            List<Empresa> computadoresAzure = conAzure.query("select * from Computador where idEmpresa = ? and MacAddress = ?",
                    new BeanPropertyRowMapper(Empresa.class), usuarioLogadoAzure.getIdEmpresa(), api.macAddress);
            List<Empresa> computadoresMySql = conMySql.query("select idEmpresa, MacAddress from Computador where idEmpresa = ? and MacAddress = ?",
                    new BeanPropertyRowMapper(Empresa.class), usuarioLogadoMySql.getIdEmpresa(), api.macAddress);

            if (computadoresAzure.isEmpty() && computadoresMySql.isEmpty()) {
                System.out.println("vamos cadastrar esse  computador");
                // FAZER INSERT NA TABELA COMPUTADOR 
                conAzure.update(String.format("insert into Computador (sistema_operacional, modelo, MacAddress, total_memoria, total_armazenamento, idEmpresa) values ('%s','%s','%s','%s','%s',%d)", api.sistemaOperacional,api.modeloProcessador,api.macAddress,api.totalMemoria,api.totalDisco,usuarioLogadoAzure.getIdEmpresa()));
                conMySql.update(String.format("insert into Computador (sistema_operacional, modelo, MacAddress, total_memoria, total_armazenamento, idEmpresa) values ('%s','%s','%s','%s','%s', %d)", api.sistemaOperacional,api.modeloProcessador,api.macAddress,api.totalMemoria,api.totalDisco,usuarioLogadoMySql.getIdEmpresa()));

            } else {
                System.out.println("computador já está cadastrado");
            }

        }
        
    }
}
