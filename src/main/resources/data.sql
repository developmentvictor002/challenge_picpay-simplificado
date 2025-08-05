INSERT IGNORE INTO tb_users (user_id, cpf, email, name, password, type) VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), '12345678901', 'alice.silva@example.com', 'Alice Silva', 'senha123hashed', 'common'),
  (UNHEX(REPLACE(UUID(), '-', '')), '23456789012', 'bob.souza@example.com', 'Bob Souza', 'senha456hashed', 'merchant'),
  (UNHEX(REPLACE(UUID(), '-', '')), '34567890123', 'carol.lima@example.com', 'Carol Lima', 'senha789hashed', 'common'),
  (UNHEX(REPLACE(UUID(), '-', '')), '45678901234', 'daniel.martins@example.com', 'Daniel Martins', 'senha321hashed', 'merchant'),
  (UNHEX(REPLACE(UUID(), '-', '')), '56789012345', 'erica.alves@example.com', 'Erica Alves', 'senha654hashed', 'common'),
  (UNHEX(REPLACE(UUID(), '-', '')), '67890123456', 'fabio.pereira@example.com', 'Fábio Pereira', 'senha987hashed', 'merchant'),
  (UNHEX(REPLACE(UUID(), '-', '')), '78901234567', 'giovana.ferreira@example.com', 'Giovana Ferreira', 'senha159hashed', 'common'),
  (UNHEX(REPLACE(UUID(), '-', '')), '89012345678', 'henrique.costa@example.com', 'Henrique Costa', 'senha753hashed', 'merchant'),
  (UNHEX(REPLACE(UUID(), '-', '')), '90123456789', 'isabela.ramos@example.com', 'Isabela Ramos', 'senha852hashed', 'common'),
  (UNHEX(REPLACE(UUID(), '-', '')), '01234567890', 'joao.mendes@example.com', 'João Mendes', 'senha147hashed', 'merchant');