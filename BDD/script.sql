drop table utilisateur;
drop table forfait;

create table forfait(
	idforfait numeric(10) primary key,
	date_achat Date,
	date_fin Date,
	temps_restant numeric(3)
);

create table utilisateur(
	idutilisateur numeric(10) primary key,
	nom varchar(20),
	prenom varchar(20),
	point_fidelite numeric(5),
	idforfait numeric(10)
);

ALTER TABLE utilisateur
ADD FOREIGN KEY (idforfait) 
REFERENCES utilisateur(idforfait);

select * from utilisateur;

shutdown;
