/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hemera.tech.looca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author VivianaMarca
 */
public class LogAcesso {

    API api = new API();

    Conexao conexaoAzure = new Conexao();
    JdbcTemplate conAzure = conexaoAzure.getConnection();

    ConexaoMySql conexaoMySql = new ConexaoMySql();
    JdbcTemplate conMySql = conexaoMySql.getConnection();
    private Integer idLogAcesso;
    private LocalDateTime dataHoraInicio;

    public void puxarIdLogAcesso() {
        //preciso puxar o id do Logacesso pra atualizar,fiz gamb
        String ultimoInsertDoLog = "select top 1 idLogAcesso from LogAcesso where MacAddress = ? order by horario_inicio desc";
       idLogAcesso = conAzure.queryForObject(ultimoInsertDoLog, Integer.class, api.macAddress());
    }

    public void salvar(String email, Boolean logou) {
        dataHoraInicio = LocalDateTime.now();
        System.out.println(dataHoraInicio);
        String horaAtual = String.format("%d:%02d:%02d", dataHoraInicio.getHour(), dataHoraInicio.getMinute(), dataHoraInicio.getSecond());
        String diaAtual = dataHoraInicio.toLocalDate().toString();

        //CRIA INSTANCIA DA PASTA
        File pasta = new File("logsDeAcesso");
        //SE A PASTA NAO EXISTIR ELE CRIA;
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        //CRIA O ARQUIVO NA PASTA DEFINIDA
        File arquivo = new File(pasta, diaAtual);

        try {
            arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter escrever = new BufferedWriter(fw);

            if (logou) {
                escrever.write(diaAtual + " - " + horaAtual + " - " + email + " - " + " login feito com sucesso \n");
            } else {
                escrever.write(diaAtual + " - " + horaAtual + " - " + email + " - " + " tentativa de login não bem sucedida\n");
            }
            escrever.close();
            fw.close();
            System.out.println("Cadastrei no log");

        } catch (IOException ex) {
            System.out.println("deu erro ao criar");
        }
    }

    public void inserirLoginBanco(Usuario u, Computador c) {
        System.out.println("Cadastrar esse log no banco");
        System.out.println(u);
        String insertTabelaLogAcesso = "insert into LogAcesso (idFuncionario, MacAddress, idComputador, idEmpresa, horario_inicio) values( ?, ?, ?,?, ?)";
        conAzure.update(insertTabelaLogAcesso, u.getIdFuncionario(), api.macAddress(), c.getIdComputador(), c.getIdEmpresa(), dataHoraInicio);
    }

    public void setIdLogAcesso(Integer idLogAcesso) {
        this.idLogAcesso = idLogAcesso;
    }

}
