-- create database hemeraTech;
-- O DO DOCKEERR

use hemeraTech;
show databases;
create table Empresa (
idEmpresa int primary key auto_increment,
nome varchar(45),
email varchar(60),
CNPJ char(14) unique,
ddd char(2),
telefone varchar(9),
cep char(8),
logradouro varchar(100),
complemento varchar(30)
);

create table Funcionario (
idFuncionario int auto_increment,
nome varchar(45),
sobrenome varchar(45),
cpf char(11),
ddd char(2),
telefone varchar(9),
email varchar(75),
senha varchar(45),
funcao varchar(45),
flagAdministrador boolean,
idEmpresa int,
constraint fk_func_emp foreign key (idEmpresa) references Empresa(idEmpresa),
primary key(idFuncionario, idEmpresa));

create table Computador (
idComputador int auto_increment,
sistema_operacional varchar(45),
modelo varchar(45),
MacAddress char(17),
total_memoria varchar(45),
total_armazenamento varchar(45),
idEmpresa int,
constraint fk_emp_comp foreign key (idEmpresa) references Empresa(idEmpresa),
primary key(idComputador, MacAddress, idEmpresa)
);

create table Registros(
momento datetime default current_timestamp(),
uso_cpu double,
utilizado_memoria double,
utilizado_armazenamento double,
download_rede double,
upload_rede double,
idComputador int,
MacAddress char(17),
idEmpresa int,
constraint fk_comp1_reg foreign key (idComputador, MacAddress, idEmpresa) REFERENCES Computador(idComputador, MacAddress, idEmpresa)
);

CREATE TABLE LogAcesso (
    idLogAcesso INT PRIMARY KEY AUTO_INCREMENT,
    idFuncionario INT,
    MacAddress CHAR(17),
    idComputador INT,
    idEmpresa INT,
    horario_inicio DATETIME,
    horario_final DATETIME,
    constraint fk_comp1_logA foreign key (idComputador, MacAddress, idEmpresa) REFERENCES Computador(idComputador, MacAddress, idEmpresa),
    constraint fk_func_logA foreign key (idFuncionario) REFERENCES Funcionario (idFuncionario)
);

insert into Empresa (nome) values ('hemeraTech');
insert into Funcionario (idFuncionario, email, senha, idEmpresa) values (12,"viviana.hmarca@gmail.com", 'senha123', 1);

select * from funcionario;