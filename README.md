# Aplicativo de Comandas

Este projeto é um aplicativo de gerenciamento de comandas, desenvolvido em Java com Spring Boot. O aplicativo permite o controle de mesas, garçons, produtos e o consumo realizado pelos clientes em um ambiente de restaurante ou bar.

## Tabelas do Banco de Dados

As seguintes tabelas são utilizadas no banco de dados H2:

1. **Comanda**
    - `id_comanda`: float (gerado automaticamente)
    - `data_hora_abertura`: date (não nulo)
    - `data_hora_fechamento`: date (não nulo)
    - `id_garcom`: float (não nulo)
    - `id_mesa`: float (não nulo)
    - `idforma_pagamento`: float (não nulo)
    - `status`: varchar(255) (não nulo)
    - `valor_gorjeta`: float (não nulo)
    - `valor_total_comanda`: float (não nulo)

2. **Consumo**
    - `id_consumo`: float (gerado automaticamente)
    - `data_hora_consumo`: date (não nulo)
    - `preco_total`: float (não nulo)
    - `preco_unitario_vendido`: float (não nulo)
    - `quantidade`: integer (não nulo)

3. **Forma de Pagamento**
    - `id_forma_pagamento`: float (gerado automaticamente)
    - `descricao`: varchar(255) (não nulo)
    - `nome`: varchar(255) (não nulo)
    - `status`: varchar(255) (não nulo)

4. **Garçom**
    - `id_garcom`: float (gerado automaticamente)
    - `cpf`: varchar(11) (não nulo, único)
    - `data_nascimento`: date (não nulo)
    - `email`: varchar(255) (não nulo, único)
    - `nome`: varchar(255) (não nulo)
    - `sexo`: varchar(1) (não nulo)
    - `telefone`: varchar(11) (não nulo, único)

5. **Mesa**
    - `id_mesa`: float (gerado automaticamente)
    - `numero`: varchar(255) (não nulo)
    - `status`: varchar(255) (não nulo)

6. **Produto**
    - `id_produto`: float (gerado automaticamente)
    - `descricao`: varchar(255) (não nulo)
    - `nome`: varchar(255) (não nulo)
    - `preco`: float (opcional)
    - `status`: varchar(255) (não nulo)

## Funcionalidades

- Cadastrar e gerenciar mesas, garçons e produtos.
- Registrar comandas e consumos.
- Gerar relatórios de faturamento por garçom e período.
- Suporte para múltiplas formas de pagamento.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Hibernate
- Banco de Dados H2

## Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/ivitor0/comanda.git
