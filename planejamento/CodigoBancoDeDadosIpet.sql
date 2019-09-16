create table Animais(id int PRIMARY key AUTO_INCREMENT, nomeAnimal varchar(100), dataNascimento date, peso decimal(11,2), especie varchar(20), sexo varchar(10))
create table Vacinas (id int AUTO_INCREMENT PRIMARY KEY, idAnimal int, nomeVacina varchar(50), dataVacina date, diasValidade int, peso decimal(11,2))

insert into animais(nomeAnimal, dataNascimento, peso, especia, sexo) values ("Nina","2015-12-25","5.20","cachorro","macho")
**padrao de data é utilize YYYY-MM-DD   /  em decimal utilize 11,2 (11 numeros antes da virgula 2 depois)**

INSERT INTO vacinas(idAnimal, nomeVacina, dataVacina, diasValidade, peso) VALUES ("2","V10","2015-02-25","150","1.5")
**o peso da vacila é dado automaticamente de acordo com o peso do animal no dia**


SELECT * FROM vacinas where idAnimal = 2 **onde 2 é o id do animal da tabela animais**
select * from vacinas INNER JOIN animais on vacinas.idAnimal = animais.id where idAnimal = 2 **onde 2 é o id do animal da tabela animais** 



