-- Script de inicialização do banco de dados
-- Executado automaticamente quando o container é criado pela primeira vez

-- Criar schema adicional (opcional)
CREATE SCHEMA IF NOT EXISTS app_schema;

-- Criar tabela de exemplo
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserir dados iniciais
INSERT INTO usuarios (nome, email)
VALUES
    ('João Silva', 'joao@email.com'),
    ('Maria Santos', 'maria@email.com'),
    ('Pedro Oliveira', 'pedro@email.com')
ON CONFLICT (email) DO NOTHING;

-- Criar índice para performance
CREATE INDEX IF NOT EXISTS idx_usuarios_email ON usuarios(email);

-- Mensagem de confirmação (aparece nos logs)
DO $$
BEGIN
    RAISE NOTICE 'Banco de dados inicializado com sucesso!';
END $$;