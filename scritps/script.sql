create database project_back_end;

use project_back_end;

create table tb_customer_account (
id BIGINT NOT NULL AUTO_INCREMENT,
cpf VARCHAR(11) NOT NULL,
nome VARCHAR(100) NOT NULL,
valor DECIMAL(14,2) NOT NULL,
ativo BOOLEAN NOT NULL DEFAULT 0,
primary key (id));