-- TRIGERI --

#drop trigger inkrementuj_zaposlene;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko inkrementovanje broja zaposlenih u pčelinjaku
create trigger inkrementuj_zaposlene after insert 
on zaposleni 
for each row
update pčelinjak as p
set p.BrojZaposlenih =  p.BrojZaposlenih + 1
where p.IdPčelinjaka = new.PČELINJAK_IdPčelinjaka;
$$
DELIMITER ;

#drop trigger dekrementuj_zaposlene;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko dekrementovanje broja zaposlenih
create trigger dekrementuj_zaposlene after delete 
on zaposleni 
for each row
update pčelinjak as p
set p.BrojZaposlenih =  p.BrojZaposlenih - 1
where p.IdPčelinjaka = old.PČELINJAK_IdPčelinjaka;
$$
DELIMITER ;

#drop trigger inkrementuj_društva;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko inkrementovanje broja društava u pčelinjaku
create trigger inkrementuj_društva after insert 
on društvo 
for each row
update pčelinjak as p
set p.BrojDruštava =  p.BrojDruštava + 1
where p.IdPčelinjaka = new.PČELINJAK_IdPčelinjaka;
$$
DELIMITER ;

#drop trigger dekrementuj_društva;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko dekrementovanje broja društava u pčelinjaku
create trigger dekrementuj_društva after delete 
on društvo 
for each row
update pčelinjak as p
set p.BrojDruštava =  p.BrojDruštava - 1
where p.IdPčelinjaka = old.PČELINJAK_IdPčelinjaka;
$$
DELIMITER ;

#drop trigger inkrementuj_sanduk;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko dekrementovanje broja društava u pčelinjaku
create trigger inkrementuj_sanduk after insert 
on sanduk 
for each row
update društvo as d
set d.BrojSanduka =  d.BrojSanduka + 1
where d.IdDruštva = new.DRUŠTVO_IdDruštva;
$$
DELIMITER ;

# drop trigger dekrementuj_sanduk;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko dekrementovanje broja društava u pčelinjaku
create trigger dekrementuj_sanduk after delete 
on sanduk 
for each row
update društvo as d
set d.BrojSanduka =  d.BrojSanduka - 1
where d.IdDruštva = old.DRUŠTVO_IdDruštva;
$$
DELIMITER ;

#drop trigger dodaj_med;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko dodavanje količine meda
create trigger dodaj_med after insert 
on posjeduje_med 
for each row
update izvrcani_med as im
set im.Količina =  im.Količina + new.Količina
where im.IdIzvrcanogMeda = new.IZVRCANI_MED_MED_IdMeda;
$$
DELIMITER ;

#drop trigger ažuriraj_med;
--
#DELIMITER $$
#use pcelinjak_db $$
# Triger za automatsko ažuriranje količine meda
#create trigger ažuriraj_med after update 
#on posjeduje_med 
#for each row
#update izvrcani_med as im
#set im.Količina =  im.Količina + new.Količina - old.Količina
#where im.IdIzvrcanogMeda = old.IZVRCANI_MED_MED_IdMeda;
#$$
#DELIMITER ;

#drop trigger dodaj_propolis;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko ažuriranje svih propolisa
create trigger dodaj_propolis after insert 
on posjeduje_propolis 
for each row
update propolis as p
set p.Količina =  p.Količina + new.Količina
where p.IdPropolisa = new.PROPOLIS_IdPropolisa;
$$
DELIMITER ; 

#drop trigger ažuriraj_propolis;
--
#DELIMITER $$
#use pcelinjak_db $$
## Triger za automatsko ažuriranje količine propolisa u pčelinjaku
#create trigger ažuriraj_propolis after update 
#on posjeduje_propolis 
#for each row
#update propolis as p
#set p.Količina =  p.Količina + new.Količina - old.Količina
#where p.IdPropolisa = old.PROPOLIS_IdPropolisa;
#$$
#DELIMITER ;

#drop trigger ažuriraj_vrca_posjeduje;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko ažuriranje količine meda u pčelinjaku nakon akcije vrcanja
create trigger ažuriraj_vrca_posjeduje after insert 
on vrca_med 
for each row
update posjeduje_med as p
set p.Količina =  p.Količina + new.KoličinaMeda
where p.IZVRCANI_MED_MED_IdMeda = (select IdIzvrcanogMeda from izvrcani_med where izvrcani_med.Vrsta =new.VrstaMeda)
and p.PČELINJAK_IdPčelinjaka = (select PČELINJAK_IdPčelinjaka from društvo where društvo.IdDruštva = new.DRUŠTVO_IdDruštva) ;
$$
DELIMITER ;

#drop trigger ažuriraj_vrca_ukupno;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko ažuriranje ukupne količine meda, bez obzira na pčelinjak
create trigger ažuriraj_vrca_ukupno after insert 
on vrca_med 
for each row
update izvrcani_med as im
set im.Količina = im.Količina + new.KoličinaMeda
where im.Vrsta = new.VrstaMeda;
$$
DELIMITER ;

#drop trigger ažuriraj_pregleda;
DELIMITER $$
use pcelinjak_db $$
# Triger za automatsko ažuriranje stanja u društvu nakon akcije pregledanja društva 
create trigger ažuriraj_pregleda after insert 
on pregleda 
for each row
update društvo as d 
set d.KoličinaMedaURezervi = new.KoličinaMedaURezervi,
d.VeličinaLegla = new.VeličinaLegla, 
d.ProizveloRoj = new.ProizveloRoj
where d.IdDruštva = new.DRUŠTVO_IdDruštva;
$$
DELIMITER ;

#drop trigger kupovina_meda;
DELIMITER $$
use pcelinjak_db $$
# Triger za ispitivanje i ažuriranje količine meda u pčelinjakuKUPOVINA_IdKupovine
create trigger kupovina_meda before insert 
on stavka_med  
for each row
update posjeduje_med as pm 
set pm.Količina = pm.Količina - new.Količina
-- Gdje je količina koja se kupuje manja ili jednaka od količine koju taj pčelinjak posjeduje
where new.Količina <= pm.Količina and
pm.PČELINJAK_IdPčelinjaka = (select PČELINJAK_IdPčelinjaka from kupovina where IdKupovine = new.KUPOVINA_IdKupovine) and
pm.IZVRCANI_MED_MED_IdMeda = new.IZVRCANI_MED_MED_IdMeda;
$$
DELIMITER ;

#drop trigger kupovina_meda_ukupno;
DELIMITER $$
use pcelinjak_db $$
# Triger za ispitivanje i ažuriranje količine meda u pčelinjakuKUPOVINA_IdKupovine
create trigger kupovina_meda_ukupno before insert 
on stavka_med  
for each row
update izvrcani_med as im 
set im.Količina = im.Količina - new.Količina
-- Gdje je količina koja se kupuje manja ili jednaka od količine koju taj pčelinjak posjeduje
where new.Količina <= im.Količina and
im.IdIzvrcanogMeda = new.IZVRCANI_MED_MED_IdMeda;
$$
DELIMITER ;

#drop trigger kupovina_propolisa;
DELIMITER $$
use pcelinjak_db $$
# Triger za ispitivanje i ažuriranje količine meda u pčelinjakuKUPOVINA_IdKupovine
create trigger kupovina_propolisa before insert 
on stavka_propolis  
for each row
update posjeduje_propolis as pp 
set pp.Količina = pp.Količina - new.Količina
-- Gdje je količina koja se kupuje manja ili jednaka od količine koju taj pčelinjak posjeduje
where new.Količina <= pp.Količina and
pp.PČELINJAK_IdPčelinjaka = (select PČELINJAK_IdPčelinjaka from kupovina where IdKupovine = new.KUPOVINA_IdKupovine) and
pp.PROPOLIS_IdPropolisa = new.PROPOLIS_IdPropolisa;
$$
DELIMITER ;

# drop trigger brisanje_posjeduje_med;
DELIMITER $$
use pcelinjak_db $$
# Triger koji umanjuje ukupnu kolicinu izvrcanog meda nakon brisanja tabele posjeduje_med
create trigger brisanje_posjeduje_med after delete 
on posjeduje_med
for each row 
update izvrcani_med as im
set im.Količina = im.Količina - old.Količina
where old.IZVRCANI_MED_MED_IdMeda = IdIzvrcanogMeda;
$$
DELMITER ;

# drop trigger brisanje_posjeduje_propolis;
DELIMITER $$
use pcelinjak_db $$
# Triger koji umanjuje ukupnu kolicinu propolisa nakon brisanja tabele posjeduje_propolis
create trigger brisanje_posjeduje_propolis after delete 
on posjeduje_propolis
for each row 
update propolis as p
set p.Količina = p.Količina - old.Količina
where old.PROPOLIS_IdPropolisa = IdPropolisa;
$$
DELMITER ;

-- PROCEDURE --

DELIMITER $$
drop procedure dodaj_pcelinjak $$
use pcelinjak_db $$
# Ova procedura unosi pcelinjak, specifikovani broj drustava i broj sanduka za svako drustvo
create procedure dodaj_pcelinjak (in pNazivPcelinjaka varchar(45),in pAdresaPcelinjaka varchar(45),in pBrojDrustava int,in pBrojVrcalica int, in pBrojTegliZaAmbalazu int,in pBrojZaposlenih int,in pIdVlasnika int,
in pBrojSanduka tinyint, in pProizveloRoj tinyint, in pVeličinaLegla tinyint, in pKoličinaMedaURezervi tinyint,
in pGodinaKupovine year, in pBoja varchar(20),in pBrojRamova tinyint)
begin
	declare drustvoCounter tinyint;
    declare sandukCounter tinyint;
    declare pIdPcelinjaka int;
    declare pIdMeda int; 		-- pomocna varijabla za med gdje cemo mu dodati kolicinu 0 zbog pogleda informacije_o_pcelinjaku
    declare pIdPropolis int; 	-- ista stvar kao i sa IdMeda, dodaje se kolicina 0 zbog pogleda 
    declare pIdDrustva int;
    set drustvoCounter = 0;
    set sandukCounter = 0;
    select max(IdPčelinjaka) + 1 from pčelinjak into pIdPcelinjaka;
    select min(IdIzvrcanogMeda) from izvrcani_med into pIdMeda;
    select min(IdPropolisa) from propolis into pIdPropolis;
    insert into pčelinjak (IdPčelinjaka,NazivPčelinjaka, AdresaPčelinjaka, BrojDruštava, BrojVrcalica, BrojTegliZaAmbalažu,BrojZaposlenih,VLASNIK_IdVlasnika)
	values (pIdPcelinjaka,pNazivPcelinjaka,pAdresaPcelinjaka,0,pBrojVrcalica,pBrojTegliZaAmbalazu,pBrojZaposlenih,pIdVlasnika);
    while drustvoCounter < pBrojDrustava do
		insert into društvo (`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
		values(0, pProizveloRoj, pVeličinaLegla, pKoličinaMedaURezervi, 1, drustvoCounter+1, pIdPcelinjaka);
        set pIdDrustva = (select max(IdDruštva) from društvo);
		set sandukCounter = 0;
        while sandukCounter < pBrojSanduka do
			insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
			values(sandukCounter+1, pGodinaKupovine,pBoja,pBrojRamova,pIdDrustva);
			set sandukCounter = sandukCounter + 1;
		end while;
        set drustvoCounter = drustvoCounter + 1;
	end while;
    if pIdMeda = 1 then 
		insert into posjeduje_med (`PČELINJAK_IdPčelinjaka`,`IZVRCANI_MED_MED_IdMeda`,`Količina`)
		values (pIdPcelinjaka,pIdMeda,0);
		end if;
	if pIdPropolis = 1 then
		insert into posjeduje_propolis (`PČELINJAK_IdPčelinjaka`, `PROPOLIS_IdPropolisa`, `Količina`)
		values (pIdPcelinjaka,pIdPropolis,0);
        end if;
end$$
DELIMITER ;

drop procedure dodaj_drustvo;
DELIMITER $$
use pcelinjak_db $$
# Ova procedura unosi jedno društvo i određen broj sanduka koje to društvo ima.
create procedure dodaj_drustvo (in pBrojSanduka tinyint, in pProizveloRoj tinyint, in pVeličinaLegla tinyint, in pKoličinaMedaURezervi tinyint, in pRed int, in pPozicijaURedu int, pPČELINJAK_IdPčelinjaka int,in pGodinaKupovine year, in pBoja varchar(20),in pBrojRamova tinyint)
begin
	declare counter tinyint;
    set counter = 0;
	insert into društvo (`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
	values(0, pProizveloRoj, pVeličinaLegla, pKoličinaMedaURezervi, pRed, pPozicijaURedu, pPČELINJAK_IdPčelinjaka);
	while counter < pBrojSanduka do
		insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
		values(counter+1, pGodinaKupovine,pBoja,pBrojRamova,pIdDruštva);
        set counter = counter + 1;
	end while;
end$$
DELIMITER ;

# Ako se izbrise drustvo, za ocekivati je da radnik ostane da radi (npr ugine drustvo)
# pa samim tim i tabele vrca, lijeci i pregleda trebaju ostati kao dokument rada radnika. Po brisanju pcelinjaka, radnik dobija otkaz, pa se brisu i ove tabele
drop procedure izbrisi_drustvo;
DELIMITER $$
use pcelinjak_db $$
# Ova procedura brise sve sanduke koje jedno drustvo ima, a zatim i samo drustvo
create procedure izbrisi_drustvo (in pIdDrustva int)
begin
	delete from sanduk where `DRUŠTVO_IdDruštva` = pIdDrustva;
    delete from društvo where `IdDruštva` = pIdDrustva;
end$$
DELIMITER ;

drop procedure dodaj_sanduke;
DELIMITER $$
use pcelinjak_db $$
# Ova procedura dodaje jedan sanduk društvu (potrebno jer se često dodaju)
create procedure dodaj_sanduke (in pIdDruštva int,in pBrojSanduka tinyint, pPČELINJAK_IdPčelinjaka int,in pGodinaKupovine year, in pBoja varchar(20),in pBrojRamova tinyint)
begin
	declare counter tinyint;
    declare broj_sanduka tinyint;
    set counter = 0;
    select BrojSanduka from društvo where IdDruštva = pIdDruštva into broj_sanduka;
	while counter < pBrojSanduka do
		insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
		values(broj_sanduka+counter, pGodinaKupovine,pBoja,pBrojRamova,pIdDruštva);
        set counter = counter + 1;
	end while;
end$$
DELIMITER ;

drop procedure ukloni_sanduke;
DELIMITER $$
use pcelinjak_db $$
# Ova procedura uklanja broj sanduka iz društva, vrlo česta radnja u stvarnom svijetu. Uklanjaju se gornji sanduci (sa najvišim indeksom) tako da je takva akcija modelovana i ovdje
create procedure ukloni_sanduke (in pIdDruštva int,in pBrojSanduka tinyint)
begin
	declare counter tinyint;
    declare broj_sanduka tinyint;
    set counter = 1;
    select BrojSanduka from društvo where IdDruštva = pIdDruštva into broj_sanduka;
	while counter <= pBrojSanduka do
		set broj_sanduka = broj_sanduka - counter;
		delete from sanduk where DRUŠTVO_IdDruštva = pIdDruštva and BrojSandukaUDruštvu = broj_sanduka;
        set counter = counter + 1;
	end while;
end$$
DELIMITER ;

-- POGLEDI --

-- drop table if exists informacije_o_pčelinjaku;
use pcelinjak_db;
-- Ovaj pogled prikazuje sve informacije o kupovinama kao što su IdKupovine, naziv pčelinjaka, Vrsta meda, količina, cijena, ime i prezime kupca te njegova adresa
create or replace view informacije_o_pčelinjaku as
select p.IdPčelinjaka as Id, p.NazivPčelinjaka as Naziv, p.AdresaPčelinjaka as Adresa, concat(v.Prezime," ",v.Ime) as Vlasnik, p.BrojDruštava as BrojDruštava, sum(all m.Količina) as UkupnoMeda, sum(all pp.Količina) as UkupnoPropolisa, p.BrojZaposlenih as BrojZaposlenih
from pčelinjak as p 
inner join vlasnik as v
on p.VLASNIK_IdVlasnika = v.IdVlasnika
inner join posjeduje_med as m
on m.PČELINJAK_IdPčelinjaka = p.IdPčelinjaka
inner join posjeduje_propolis as pp
on pp.PČELINJAK_IdPčelinjaka = p.IdPčelinjaka
group by p.IdPčelinjaka;



