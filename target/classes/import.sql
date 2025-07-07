-- Pessoas
INSERT INTO tb_pessoa (nome, cpf, endereco, telefone, email) VALUES ('Maria Costureira', '111.111.111-11', 'Rua das Linhas, 100', '(31) 99999-0001', 'maria@costura.com');
INSERT INTO tb_pessoa (nome, cpf, endereco, telefone, email) VALUES ('João Cliente', '222.222.222-22', 'Av. dos Clientes, 200', '(31) 98888-0002', 'joao@cliente.com');
INSERT INTO tb_pessoa (nome, cpf, endereco, telefone, email) VALUES ('Ana Cliente', '333.333.333-33', 'Rua dos Alfaiates, 300', '(31) 97777-0003', 'ana@cliente.com');
INSERT INTO tb_pessoa (nome, cpf, endereco, telefone, email) VALUES ('Carlos Cliente', '444.444.444-44', 'Travessa dos Tecidos, 400', '(31) 96666-0004', 'carlos@cliente.com');

-- Usuários
INSERT INTO tb_usuario (login, senha, tipo, id_pessoa, nivel_acesso) VALUES ('costureira', '$2a$10$ZQDZDXqV/LKQTq13NKJ2aeUmrxV787/U1BSEfXS.pGPu0Sd9CgYX6', 'COSTUREIRA', 1, 'ADMIN');
INSERT INTO tb_usuario (login, senha, tipo, id_pessoa, nivel_acesso) VALUES ('joao', '$2a$10$ZQDZDXqV/LKQTq13NKJ2aeUmrxV787/U1BSEfXS.pGPu0Sd9CgYX6', 'CLIENTE', 2, 'ADMIN');
INSERT INTO tb_usuario (login, senha, tipo, id_pessoa, nivel_acesso) VALUES ('ana', '$2a$10$ZQDZDXqV/LKQTq13NKJ2aeUmrxV787/U1BSEfXS.pGPu0Sd9CgYX6', 'CLIENTE', 3, 'ADMIN');
INSERT INTO tb_usuario (login, senha, tipo, id_pessoa, nivel_acesso) VALUES ('carlos', '$2a$10$ZQDZDXqV/LKQTq13NKJ2aeUmrxV787/U1BSEfXS.pGPu0Sd9CgYX6', 'CLIENTE', 4, 'ADMIN');

-- Pedidos
INSERT INTO tb_pedido (descricao, data_pedido, data_entrega, status, forma_pagamento, numero_parcelas, valor, id_usuario_cliente, id_usuario_costureira) VALUES ('Vestido de festa', '2025-06-01', '2025-06-20', 'EM ANDAMENTO', 'CARTAO', 3, 300.00, 2, 1);
INSERT INTO tb_pedido (descricao, data_pedido, data_entrega, status, forma_pagamento, numero_parcelas, valor, id_usuario_cliente, id_usuario_costureira) VALUES ('Camisa social', '2025-06-02', '2025-06-15', 'FINALIZADO', 'DINHEIRO', 1, 120.00, 3, 1);
INSERT INTO tb_pedido (descricao, data_pedido, data_entrega, status, forma_pagamento, numero_parcelas, valor, id_usuario_cliente, id_usuario_costureira) VALUES ('Calça jeans', '2025-06-03', '2025-06-18', 'EM ANDAMENTO', 'PIX', 2, 150.00, 4, 1);

-- Lembretes
INSERT INTO tb_lembrete (descricao, data, hora, status, id_pedido) VALUES ('Provar vestido', '2025-06-10', '14:00:00', 'PENDENTE', 1);
INSERT INTO tb_lembrete (descricao, data, hora, status, id_pedido) VALUES ('Buscar camisa', '2025-06-15', '10:00:00', 'CONCLUIDO', 2);
INSERT INTO tb_lembrete (descricao, data, hora, status, id_pedido) VALUES ('Ajustar calça', '2025-06-12', '16:00:00', 'PENDENTE', 3);

-- Visitas
INSERT INTO tb_visita (data, hora, descricao, id_usuario_cliente, id_usuario_costureira) VALUES ('2025-06-05', '15:00:00', 'Primeira prova do vestido', 2, 1);
INSERT INTO tb_visita (data, hora, descricao, id_usuario_cliente, id_usuario_costureira) VALUES ('2025-06-07', '11:00:00', 'Entrega da camisa', 3, 1);
INSERT INTO tb_visita (data, hora, descricao, id_usuario_cliente, id_usuario_costureira) VALUES ('2025-06-09', '17:00:00', 'Ajuste da calça', 4, 1);