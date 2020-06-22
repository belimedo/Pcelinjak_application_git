USE pcelinjak_db;
-- Dodavanje vlasnika
insert into vlasnik (KorisničkoIme, Lozinka, JMBG, Ime, Prezime)
values ('petar_nalog','Lozinka123','0101965129910','Petar','Petrović');

insert into vlasnik (KorisničkoIme, Lozinka, JMBG, Ime, Prezime)
values ('milan_nalog','Lozinka123','2701965129910','Milan','Medić');

insert into vlasnik (KorisničkoIme, Lozinka, JMBG, Ime, Prezime)
values ('admin','admin','1101965129910','Admin','Test');

-- Dodavanje pčelinjaka
insert into pčelinjak (NazivPčelinjaka, AdresaPčelinjaka, BrojDruštava, BrojVrcalica, BrojTegliZaAmbalažu,BrojZaposlenih,VLASNIK_IdVlasnika)
values ('Pčelinjak Medić','Donji Jelovac bb',0,1,0,0,3);

-- 
insert into pčelinjak (NazivPčelinjaka, AdresaPčelinjaka, BrojDruštava, BrojVrcalica, BrojTegliZaAmbalažu,BrojZaposlenih,VLASNIK_IdVlasnika)
values ('Pčelinjak Test','Donji Bakarnik bb',0,1,0,0,3);

-- Dodavanje zaposlenih
insert into zaposleni (`Plata`,`KorisničkoIme`,`Lozinka`,`JMBG`,`Ime`,`Prezime`,`PČELINJAK_IdPčelinjaka`)
values (600,'jovan_nalog','jovan_lozinka',1312999123451,'Jovan','Jovanovic',1);

insert into zaposleni (`Plata`,`KorisničkoIme`,`Lozinka`,`JMBG`,`Ime`,`Prezime`,`PČELINJAK_IdPčelinjaka`)
value (600,'dragan_nalog','dragan_lozinka',2012999123451,'Dragan','Draganovic',1);

insert into zaposleni (`Plata`,`KorisničkoIme`,`Lozinka`,`JMBG`,`Ime`,`Prezime`,`PČELINJAK_IdPčelinjaka`)
value (600,'sluz','sluz',2001999123451,'Sluzbenik','Test',1);
--
insert into zaposleni (`Plata`,`KorisničkoIme`,`Lozinka`,`JMBG`,`Ime`,`Prezime`,`PČELINJAK_IdPčelinjaka`)
value (600,'sluz1','sluz1',2011999123451,'Sluzbenik1','Test1',2);


-- Dodavanje vrsta izvrcanog meda
insert into izvrcani_med (`IdIzvrcanogMeda`,`Vrsta`,`Količina`,`Cijena`)
values(1,'Bagremov',0,13.00);

insert into izvrcani_med (`IdIzvrcanogMeda`,`Vrsta`,`Količina`,`Cijena`)
values(2,'Livadski',0,13.00);

-- Dodavanje propolisa
insert into propolis (`IdPropolisa`,`Vrsta`,`Količina`,`Cijena`) 
values (1,'Rastvor 20%',0,5);

insert into propolis (`IdPropolisa`,`Vrsta`,`Količina`,`Cijena`) 
values (2,'Rastvor 10%',0,5);

-- Popunjavanje pcelinjaka sa medom i propolisom
insert into posjeduje_propolis (`PČELINJAK_IdPčelinjaka`,`PROPOLIS_IdPropolisa`,`Količina`)
values (1,1,20);

insert into posjeduje_med (`PČELINJAK_IdPčelinjaka`,`IZVRCANI_MED_MED_IdMeda`,`Količina`)
values (1,1,150);

insert into posjeduje_med (`PČELINJAK_IdPčelinjaka`,`IZVRCANI_MED_MED_IdMeda`,`Količina`)
values (1,2,120);

insert into posjeduje_propolis (`PČELINJAK_IdPčelinjaka`, `PROPOLIS_IdPropolisa`, `Količina`)
values (1,2,10);
 --
insert into posjeduje_med (`PČELINJAK_IdPčelinjaka`,`IZVRCANI_MED_MED_IdMeda`,`Količina`)
values (2,2,20);

insert into posjeduje_propolis (`PČELINJAK_IdPčelinjaka`, `PROPOLIS_IdPropolisa`, `Količina`)
values (2,2,15);


-- Dodavanje kupaca
insert into kupac (`IdKupca`) values (1);

insert into kupac (`IdKupca`) values (2);

insert into kupac (`IdKupca`) values (3);

-- Dodavanje fizičkih lica
insert into fizičko_lice (`KUPAC_IdKupca`,`JMBG`,`Ime`,`Prezime`)
values (1,1231234556777,'Petar','Marković');

insert into fizičko_lice (`KUPAC_IdKupca`,`JMBG`,`Ime`,`Prezime`)
values (3,1247234556777,'Ana','Janković');

-- Dodavanje trgovine
insert into trgovina (`KUPAC_IdKupca`,`Naziv`,`Adresa`)
values (2,'STR Pčelica','Stepe Stepanovića 15, Banja Luka');



-- Popunjavamo društva,6 društava sa po 2 sanduka u 2 reda
insert into društvo (`IdDruštva`,`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
values (1,2, 0, 4, 4, 1, 1, 1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(1,2019,'Plava',10,1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(2,2019,'Plava',10,1);

insert into društvo (`IdDruštva`,`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
values (2,2, 0, 4, 4, 1, 2, 1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(1,2019,'Žuta',10,2);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(2,2019,'Žuta',10,2);

insert into društvo (`IdDruštva`,`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
values (3,2, 0, 4, 4, 1, 3, 1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(1,2019,'Bijela',10,3);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(2,2019,'Bijela',10,3);

insert into društvo (`IdDruštva`,`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
values (4,2, 0, 4, 4, 2, 1, 1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(1,2019,'Plava',10,4);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(2,2019,'Plava',10,4);

insert into društvo (`IdDruštva`,`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
values (5,2, 0, 4, 4, 2, 2, 1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(1,2019,'Žuta',10,5);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(2,2019,'Žuta',10,5);

insert into društvo (`IdDruštva`,`BrojSanduka`,`ProizveloRoj`,`VeličinaLegla`,`KoličinaMedaURezervi`,`Red`,`PozicijaURedu`,`PČELINJAK_IdPčelinjaka`)
values (6,2, 0, 4, 4, 2, 3, 1);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(1,2019,'Bijela',10,6);

insert into sanduk (`BrojSandukaUDruštvu`,`GodinaKupovine`,`Boja`,`BrojRamova`,`DRUŠTVO_IdDruštva`)
values(2,2019,'Bijela',10,6);

-- Dodavanje akcije pregleda društva
insert into pregleda (`DatumPregleda`,`VeličinaLegla`,`KoličinaMedaURezervi`,`ProizveloRoj`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('15-5-2020','%d-%m-%Y'),3,5,0,1,1);

insert into pregleda (`DatumPregleda`,`VeličinaLegla`,`KoličinaMedaURezervi`,`ProizveloRoj`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('17-5-2020','%d-%m-%Y'),4,6,0,3,2);

insert into pregleda (`DatumPregleda`,`VeličinaLegla`,`KoličinaMedaURezervi`,`ProizveloRoj`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('12-5-2020','%d-%m-%Y'),1,8,0,5,1);

insert into pregleda (`DatumPregleda`,`VeličinaLegla`,`KoličinaMedaURezervi`,`ProizveloRoj`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('21-5-2020','%d-%m-%Y'),2,7,1,2,2);

-- Dodavanje akcije liječi društva
insert into liječi (`DatumLiječenja`,`VrstaLijeka`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-10-2020','%d-%m-%Y'),'Varolik',2,1);

insert into liječi (`DatumLiječenja`,`VrstaLijeka`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-10-2020','%d-%m-%Y'),'Varolik',3,1);

-- Dodavanje akcije vrca med
insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-8-2019','%d-%m-%Y'),'Livadski',8,2,1);

insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-8-2019','%d-%m-%Y'),'Livadski',12,3,2);

insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-8-2019','%d-%m-%Y'),'Livadski',10,1,1);

insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-8-2019','%d-%m-%Y'),'Livadski',4,4,2);

insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-8-2019','%d-%m-%Y'),'Livadski',6,5,1);

insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('10-8-2019','%d-%m-%Y'),'Livadski',10,6,2);

insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`)
values (str_to_date('11-8-2019','%d-%m-%Y'),'Livadski',2,6,2);

-- dodavanje kupovine
insert into kupovina (`KUPAC_IdKupca`,`PČELINJAK_IdPčelinjaka`,`Datum`,`Cijena`)
values (1,1,str_to_date('11-2-2020','%d-%m-%Y'),13);

insert into kupovina (`KUPAC_IdKupca`,`PČELINJAK_IdPčelinjaka`,`Datum`,`Cijena`)
values (1,1,str_to_date('11-3-2020','%d-%m-%Y'),18);

insert into kupovina (`KUPAC_IdKupca`,`PČELINJAK_IdPčelinjaka`,`Datum`,`Cijena`)
values (2,1,str_to_date('15-2-2020','%d-%m-%Y'),130);

insert into kupovina (`KUPAC_IdKupca`,`PČELINJAK_IdPčelinjaka`,`Datum`,`Cijena`)
values (2,1,str_to_date('16-2-2020','%d-%m-%Y'),50);

insert into kupovina (`KUPAC_IdKupca`,`PČELINJAK_IdPčelinjaka`,`Datum`,`Cijena`)
values (3,1,str_to_date('15-2-2020','%d-%m-%Y'),26);

-- Dodavanje stavka_med
insert into stavka_med (`KUPOVINA_IdKupovine`,`IZVRCANI_MED_MED_IdMeda`,`Količina`,`Cijena`) 
values (1,1,1,13);

insert into stavka_med (`KUPOVINA_IdKupovine`,`IZVRCANI_MED_MED_IdMeda`,`Količina`,`Cijena`) 
values (2,2,1,13);

insert into stavka_med (`KUPOVINA_IdKupovine`,`IZVRCANI_MED_MED_IdMeda`,`Količina`,`Cijena`) 
values (3,2,10,130);

insert into stavka_med (`KUPOVINA_IdKupovine`,`IZVRCANI_MED_MED_IdMeda`,`Količina`,`Cijena`) 
values (5,2,2,26);

-- Dodavanje stavka_propolis
insert into stavka_propolis (`KUPOVINA_IdKupovine`,`PROPOLIS_IdPropolisa`,`Količina`,`Cijena`)
values (2,1,1,5);

insert into stavka_propolis (`KUPOVINA_IdKupovine`,`PROPOLIS_IdPropolisa`,`Količina`,`Cijena`)
values (4,1,10,50);

call dodaj_pcelinjak('Procedura pcelinjak 4','Stepe Stepanovica 16',4,2,20,2,3,2,0,4,4,2020,'Crvena',10);






