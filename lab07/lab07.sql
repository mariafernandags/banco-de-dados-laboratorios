CREATE TABLE ALUNO (
    id_aluno INT PRIMARY KEY,
    nome VARCHAR(100),
    cidade VARCHAR(50),
    data_ultima_matricula DATE
);

CREATE TABLE TREINADOR (
    id_treinador INT PRIMARY KEY,
    nome VARCHAR(100),
    especialidade VARCHAR(100)
);

CREATE TABLE TREINO (
    id_treino INT PRIMARY KEY,
    descricao VARCHAR(100),
    id_treinador INT,
    FOREIGN KEY (id_treinador) REFERENCES TREINADOR(id_treinador)
);

CREATE TABLE ALUNO_TREINO (
    id_aluno INT,
    id_treino INT,
    data_inicio DATE,
    PRIMARY KEY (id_aluno, id_treino),
    FOREIGN KEY (id_aluno) REFERENCES ALUNO(id_aluno),
    FOREIGN KEY (id_treino) REFERENCES TREINO(id_treino)
);

CREATE TABLE controle_sistema (
    id SERIAL PRIMARY KEY,
    total_matriculas_canceladas INT DEFAULT 0
);

INSERT INTO ALUNO (id_aluno, nome, cidade, data_ultima_matricula) VALUES
(1, 'Lucas Rocha', 'Belo Horizonte', NULL),
(2, 'Marina Souza', 'São Paulo', NULL),
(3, 'Igor Costa', 'Curitiba', NULL);

INSERT INTO TREINADOR (id_treinador, nome, especialidade) VALUES
(1, 'Fernando Lima', 'Musculação'),
(2, 'Patrícia Moura', 'CrossFit'),
(3, 'Carlos Mendes', 'Pilates');

INSERT INTO TREINO (id_treino, descricao, id_treinador) VALUES
(1, 'Treino de Força', 1),
(2, 'Treino Funcional', 2),
(3, 'Pilates Iniciante', 3);

INSERT INTO ALUNO_TREINO (id_aluno, id_treino, data_inicio) VALUES
(1, 1, '2024-04-01'),
(1, 2, '2024-04-10'),
(2, 3, '2024-04-05'),
(3, 1, '2024-04-15');

INSERT INTO controle_sistema (total_matriculas_canceladas) VALUES (0);


drop table ALUNO cascade;
drop table controle_sistema  cascade;
drop table TREINADOR cascade;
drop table TREINO cascade;
drop table ALUNO_TREINO cascade;

--1
CREATE OR REPLACE FUNCTION fn_atualizar_data_ultima_matricula()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE ALUNO
    SET data_ultima_matricula = NEW.data_inicio
    WHERE id_aluno = NEW.id_aluno;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_data_ultima_matricula
AFTER INSERT ON ALUNO_TREINO
FOR EACH ROW
EXECUTE FUNCTION fn_atualizar_data_ultima_matricula();

--2
CREATE OR REPLACE FUNCTION fn_atualizar_cancelamentos()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE CONTROLE_SISTEMA
    SET total_matriculas_canceladas = total_matriculas_canceladas + 1
    WHERE id = 1;  
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_cancelamentos
AFTER DELETE ON ALUNO_TREINO
FOR EACH STATEMENT
EXECUTE FUNCTION fn_atualizar_cancelamentos();

--3
CREATE OR REPLACE PROCEDURE cancelar_todos_os_treinos(p_id_aluno INT)
LANGUAGE plpgsql
AS $$
DECLARE
    qtd INT;
BEGIN
    
    IF NOT EXISTS (SELECT 1 FROM ALUNO WHERE id_aluno = p_id_aluno) THEN
        RAISE NOTICE 'Aluno não encontrado.';
        RETURN;
    END IF;

    
    SELECT COUNT(*) INTO qtd FROM ALUNO_TREINO WHERE id_aluno = p_id_aluno;

    
    IF qtd = 0 THEN
        RAISE NOTICE 'O aluno não tem nenhuma matrícula.';
        RETURN;
    END IF;

    
    DELETE FROM ALUNO_TREINO WHERE id_aluno = p_id_aluno;

    RAISE NOTICE 'Foram canceladas % matrícula(s).', qtd;
END;
$$;


-- Inserindo um novo treino para o aluno de ID 2
INSERT INTO ALUNO_TREINO (id_aluno, id_treino, data_inicio) 
VALUES (2, 2, '2025-05-15');

-- Deletando o treino de ID 1 do aluno de ID 1
DELETE FROM ALUNO_TREINO 
WHERE id_aluno = 1 AND id_treino = 1;


CALL cancelar_todos_os_treinos(2);  

SELECT * FROM aluno_treino WHERE id_aluno = 2 AND id_treino = 2;
SELECT id FROM controle_sistema;










