--a
create table aluno(id_aluno int primary key,
nome varchar (50),
cidade varchar(50));

create table treinador(id_treinador int primary key,
nome varchar(50),
especialidade varchar(50));

create table treino(id_treino int primary key,
descricao varchar(50),
id_treinador int,
foreign key(id_treinador) references treinador(id_treinador));

create table aluno_treino(id_aluno int,
id_treino int,
data_inicio date,
primary key(id_aluno, id_treino),
foreign key(id_aluno) references aluno(id_aluno),
foreign key(id_treino) references treino(id_treino));

DROP TABLE aluno CASCADE;
DROP TABLE treinador CASCADE;
DROP TABLE treino CASCADE;
DROP TABLE aluno_treino CASCADE;

--b
insert into aluno(id_aluno, nome, cidade) values
(1, 'Maria Fernanda', 'Itajubá'),
(2, 'Josué', 'Itajubá'),
(3, 'José', 'Pouso Alegre'),
(4, 'Mariana', 'Paraisópolis'),
(11, 'Francisco', 'São Paulo');

insert into treinador(id_treinador, nome, especialidade) values
(5, 'Tite', 'futebol'),
(6, 'Ana', 'vôlei'),
(7, 'Júlia', 'natação');

insert into treino(id_treino, descricao, id_treinador) values 
(8, 'nado raso', 7),
(9, 'treinar penâlti', 5),
(10, 'treino de manchete', 6);

insert into aluno_treino(id_aluno, id_treino, data_inicio) values
(2, 8, '2025-05-04'),
(1, 9, '2025-09-08'),
(4, 10, '2025-07-06'),
(3, 8, '2025-08-08');

--c
select nome from aluno;

--d
select t.id_treino, t.descricao
from treino t
join aluno_treino a on t.id_treino = a.id_treino
where a.data_inicio between '2025-05-09' and '2025-10-01';

--e
select t.nome
from treinador t
order by t.nome asc;

--f
select count(*) as total
from treinador;

--g
select avg(contagem) as media_aluno_treino
from(
		select a.id_treino, count(a.id_aluno) as contagem
		from aluno_treino a
		group by id_treino
)as media;

--h
select t.id_treino, t.descricao, count(a.id_aluno) as total_aluno
from treino t
join aluno_treino a on t.id_treino = a.id_treino
group by t.id_treino, t.descricao
order by total_aluno desc
limit 1;

--i
select a.cidade, count(a.id_aluno) as total
from aluno a
group by a.cidade;

--j
select t.id_treino, t.descricao, t.id_treinador, count(a.id_aluno) as total
from treino t
join aluno_treino a on t.id_treino = a.id_treino
group by t.id_treino, t.descricao, t.id_treinador
having count(a.id_aluno) > 1;

--k
select a.nome, t.id_treino, t.descricao, t.id_treinador, ta.data_inicio
from aluno a
join aluno_treino ta on a.id_aluno = ta.id_aluno
join treino t on ta.id_treino = t.id_treino;

--l
select a.nome, t.id_treino, t.descricao, t.id_treinador, ta.data_inicio
from aluno a
left join aluno_treino ta on a.id_aluno = ta.id_aluno
left join treino t on ta.id_treino = t.id_treino;








