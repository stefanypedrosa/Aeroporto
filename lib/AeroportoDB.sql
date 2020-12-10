CREATE DATABASE aeroportoDB;
USE aeroportoDB;

CREATE TABLE passageiro (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  telefone varchar(50) DEFAULT NULL,
  usuario varchar(50) DEFAULT NULL,
  senha varchar(30) DEFAULT NULL,
  dataNascimento date,
  documento varchar(14) DEFAULT NULL,
  numeroCartao VARCHAR(16),
  PRIMARY KEY (id)
);


CREATE TABLE funcionario (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  telefone varchar(50) DEFAULT NULL,
  usuario varchar(50) DEFAULT NULL,
  senha varchar(30) DEFAULT NULL,
  dataNascimento date,
  codigo varchar(50) DEFAULT NULL,
  contaCorrente VARCHAR(16),
  PRIMARY KEY (id)
);


CREATE TABLE aviao (
  id int NOT NULL AUTO_INCREMENT,
  codigo varchar(100) DEFAULT NULL,
  vagas int DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE horario (
  id int NOT NULL AUTO_INCREMENT,
  codigo varchar(100) DEFAULT NULL,
  partida date,
  chegada date,
  PRIMARY KEY (id)
);

CREATE TABLE bilhete (
  id int NOT NULL AUTO_INCREMENT,
  numero int DEFAULT 0,
  assento varchar(100) DEFAULT NULL,
  situacao int DEFAULT 0,
  PRIMARY KEY (id)
);