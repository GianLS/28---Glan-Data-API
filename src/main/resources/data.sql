INSERT INTO clientes (cpf, email, nome, data_nascimento) VALUES ('33111472949','kevin_ferreira@thibe.com.br','Kevin Sérgio Caio Ferreira','24/06/1960');
INSERT INTO clientes (cpf, email, nome, data_nascimento) VALUES ('69895631090','alice_lais_nunes@portoweb.com.br','Alice Laís Nunes','13/07/1993');
INSERT INTO clientes (cpf, email, nome, data_nascimento) VALUES ('42339999855','cesar.sergio.aparicio@vivo.com.br','César Sérgio Aparício','06/05/1980');

INSERT INTO roles (nome_role) VALUES ('ROLE_CADASTROS');
INSERT INTO roles (nome_role) VALUES ('ROLE_FATURAMENTO');
INSERT INTO roles (nome_role) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios (login, nome, email, senha) VALUES ('maria', 'Maria', 'maria@teste.com.br','$2a$10$udxISip/UySBN9dCBT0fgOjOvEpS1PyNMM2mJcWlBrmPWexzIiIrW');
INSERT INTO usuarios (login, nome, email, senha) VALUES ('joao', 'João', 'joao@teste.com.br','$2a$10$udxISip/UySBN9dCBT0fgOjOvEpS1PyNMM2mJcWlBrmPWexzIiIrW');
INSERT INTO usuarios (login, nome, email, senha) VALUES ('jose', 'José', 'jose@teste.com.br','$2a$10$9MBN/aN0ehDYm42wLpdG9.svMJ1ItL3CQB7GejFXloxeD6rxSBoWe');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('maria','ROLE_CADASTROS');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('joao','ROLE_FATURAMENTO');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('jose','ROLE_ADMIN');

INSERT INTO categorias (nome, descricao) VALUES ('Eletrônicos','Produtos Eletrônicos Diversos');
INSERT INTO categorias (nome, descricao) VALUES ('Vestuário','Roupas');
INSERT INTO categorias (nome, descricao) VALUES ('Móveis','Desativado');
INSERT INTO categorias (nome, descricao) VALUES ('Acessórios','Diversos');

INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('TV 50"', 'TV Samsung 50 Polegadas', 3500.00, 1, '2022-03-28' );
INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('IPhone 11"', 'Smartphone Apple Iphone 11 5G', 5290.00, 1, '2022-03-27' );
INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('Camisa Social', 'Camisa Social nº 3 Azul', 98.00, 2, '2022-03-26');
INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('Phone Games', 'Phone Tipo Concha Tipo Gamer', 150.00, 4, '2022-03-28');
INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('Cadeira Classca"', 'Caderia Madeira Tipo Clássica', 120.00, 3, '2022-03-28');
INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('Calça Jeans M', 'Calça Jeans Preta Tamanho M', 89.90, 2, '2022-03-28');
INSERT INTO produtos (nome, descricao, preco, categoria_id, data_cadastro) VALUES ('Porta Retrato de Mesa', 'Porta Retrato de Mesa 30x20cm', 25.00, 1, '2022-03-28');
