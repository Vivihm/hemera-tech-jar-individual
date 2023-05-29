/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author VivianaMarca
 */
public class TesteLogin {

    public static void main(String[] args) {
        API api = new API();
        LogAcesso log = new LogAcesso();

        Conexao conexaoAzure = new Conexao();
        JdbcTemplate conAzure = conexaoAzure.getConnection();

        ConexaoMySql conexaoMySql = new ConexaoMySql();
        JdbcTemplate conMySql = conexaoMySql.getConnection();

        //LOG ACESSO
        Boolean logou = false;

        Scanner leitor = new Scanner(System.in);
        System.out.println("Seja bem vindo de volta ao sistema de monitoramento da hemera Tech");
        System.out.println("Email:");
        String emailDigitado = leitor.nextLine();
        System.out.println("Senha:");
        String senhaDigitada = leitor.nextLine();

        //SELECT PARA AUTENTICAR LOGIN
        String selectUsuario = "select idFuncionario,nome,sobrenome,email,senha,idEmpresa from Funcionario where email= ? and  senha= ?";
        Usuario usuarioLogadoAzure = conAzure.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);
        //  Usuario usuarioLogadoMySql = conMySql.queryForObject(selectUsuario, new BeanPropertyRowMapper<>(Usuario.class), emailDigitado, senhaDigitada);

        if (usuarioLogadoAzure == null) {
            System.out.println("Usuário ou senha incorretos");
            // LOGGG
            log.salvar(emailDigitado, logou);

        } else {
            // LOGGG
            logou = true;
            log.salvar(emailDigitado, logou);

            //PUXAR SE EXISTE COMPUTADORES DA EMPRESA COM MACADDRESS 
            String selectComputador = "select idComputador, idEmpresa from Computador where idEmpresa = ? and MacAddress = ?";
            List<Computador> computadoresLogadosAzure = conAzure.query(selectComputador, new BeanPropertyRowMapper<>(Computador.class), usuarioLogadoAzure.getIdEmpresa(), api.macAddress());
            // List<Computador> computadoresLogadosMySql = conMySql.query(selectComputador, new BeanPropertyRowMapper<>(Computador.class), usuarioLogadoMySql.getIdEmpresa(), api.macAddress());

            if (computadoresLogadosAzure.isEmpty()) {
                System.out.println("vamos cadastrar esse  computador");
                // FAZER INSERT NA TABELA COMPUTADOR 
                conAzure.update(String.format("insert into Computador (sistema_operacional, modelo, MacAddress, total_memoria, total_armazenamento, idEmpresa) values ('%s','%s','%s','%s','%s',%d)", api.sistemaOperacional(), api.modeloProcessador(), api.macAddress(), api.totalMemoria(), api.totalDisco(), usuarioLogadoAzure.getIdEmpresa()));
                //   conMySql.update(String.format("insert into Computador (sistema_operacional, modelo, MacAddress, total_memoria, total_armazenamento, idEmpresa) values ('%s','%s','%s','%s','%s', %d)", api.sistemaOperacional(), api.modeloProcessador(), api.macAddress(), api.totalMemoria(), api.totalDisco(), usuarioLogadoMySql.getIdEmpresa()));

                System.out.println("Cadastrei os computadores!!");

                computadoresLogadosAzure = conAzure.query(selectComputador, new BeanPropertyRowMapper<>(Computador.class), usuarioLogadoAzure.getIdEmpresa(), api.macAddress());
                log.inserirLoginBanco(usuarioLogadoAzure, computadoresLogadosAzure.get(0));

            } else {
                System.out.println("computador já estava cadastrado");
                log.inserirLoginBanco(usuarioLogadoAzure, computadoresLogadosAzure.get(0));

            }

            System.out.println("COMEÇAR A REGISTRAR DADOS A CADA X SEGUNDOS");
            Timer tempo = new Timer();
            tempo.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Computador computadorLogadoAzure = conAzure.queryForObject(selectComputador, new BeanPropertyRowMapper<>(Computador.class), usuarioLogadoAzure.getIdEmpresa(), api.macAddress());
                    //Computador computadorLogadoMySql = conMySql.queryForObject(selectComputador, new BeanPropertyRowMapper<>(Computador.class), usuarioLogadoMySql.getIdEmpresa(), api.macAddress());

                    try {
                        api.inserirDadosAzure(computadorLogadoAzure);
                        //       api.inserirDadosMySql(computadorLogadoMySql);
                        System.out.println("INSERI EIN");
                    } catch (Exception e) {
                        System.out.println("Erro ao inserir dados: " + e.getMessage());
                    }
                }
            }, 0, 20000);
        }

    }

}
