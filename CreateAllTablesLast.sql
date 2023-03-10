DROP TABLE IF EXISTS public.rooms;
DROP TABLE IF EXISTS public.booking;
DROP TABLE IF EXISTS public.client;
DROP TABLE IF EXISTS public.rating;
DROP TABLE IF EXISTS public.city;
DROP TABLE IF EXISTS public.country;



CREATE TABLE IF NOT EXISTS public.country
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name text

);
insert into "country" (name)
values
    ('USA'),
    ('Poland'),
    ('Norway'),
    ('Ukraine'),
    ('Germany'),
    ('Hungary');

CREATE TABLE IF NOT EXISTS public.city
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name text,
    countryid integer,
    CONSTRAINT firstkey FOREIGN KEY (countryid)
        REFERENCES public.country (id)
);
insert into "city" (name,countryid)
values
    ('Boston',1),
    ('Krakov',2),
    ('Oslo',3),
    ('Kiev',4),
    ('Drezden',5),
    ('Buda',6);

CREATE TABLE IF NOT EXISTS public.rating
(
    id integer primary key generated by default as identity,
    value integer NOT NULL

);
insert into "rating" (value)
values
    (1),
    (2),
    (3),
    (4),
    (5);


CREATE TABLE IF NOT EXISTS public.client
(
    id integer primary key generated by default as identity,
    userfullname text NOT NULL,
    login text  NOT NULL,
    passwd text  NOT NULL,
    birthday text  NOT NULL,
    email text NOT NULL,
    ratingid integer,
    cityid integer NOT NULL,
    role text,
    status text,
    CONSTRAINT ff_client FOREIGN KEY (cityid)
        REFERENCES public.city (id),
    CONSTRAINT ff_rating FOREIGN KEY (ratingid)
        REFERENCES public.rating (id)
);
insert into "client" (userfullname,login,passwd,birthday,email,ratingid,cityid,role,status)
values
    ('Sara Connor', 'sara','$2a$12$05iXrzrVTmZfeapa9epCPO3rL8dB73NtwEEEEh184tuGX2FwEKU5y', '02.08.1974','sara@gmail.com',4,1,'ADMIN','ACTIVE'),
    ('Bjisko Mrazek', 'mra','mramra', '01.07.1980','mra@ralpol.com',4,2,'USER','ACTIVE'),
    ('Trumm Sanderson', 'trum','$2a$12$rrjZTFiTcM6iUo6GFZyJjute/9o8vgRLtSv/3ejoOdxFK1O89cOXy', '03.06.1981','trum@norw.com',4,3,'USER','ACTIVE'),
    ('Sergio Franco', 'ser','serser', '04.07.1982','ser@ukrain.uk',4,4,'USER','ACTIVE'),
    ('Gans Shmulke', 'gans','$2a$12$rYHC8ZAj6fjqzArjNT8nreGUim7C9mjCJFaFkVAdRfTs3/XxT8Vpy', '05.07.1984','gans@raih.gm',4,5,'USER','ACTIVE'),
    ('Moise Blum', 'moi','$2a$12$lIrIT2tcq4oziSl/ftfvIekAB7mV5ydrQRJCmDvy/LeXiR1GWl9k6', '04.07.1985','moi@izrael.com',4,6,'USER','ACTIVE');


CREATE TABLE IF NOT EXISTS public.booking
(
    id integer Primary key generated by default as identity,
    beginterm date,
    endterm date,
    price integer,
    clientid integer,
     CONSTRAINT ff_client FOREIGN KEY (clientid)
        REFERENCES public.client (id)
);

insert into "booking" (beginterm,endterm,price,clientid)
values
    ('2022-02-10','2022-03-10',12000,1),
    ('2022-01-11','2022-04-11',24000,2),
    ('2022-06-01','2022-07-15',23000,3),
    ('2022-08-20','2022-09-20',12000,4),
    ('2023-01-10','2023-01-25',12000,5),
    ('2022-02-10','2022-03-10',12000,6);

CREATE TABLE IF NOT EXISTS public.rooms
(
    id integer primary key generated by default as identity,
    rating integer,
    clientid integer NOT NULL,
    flagFree integer,
    cityid integer NOT NULL,
    bookingid integer,
    CONSTRAINT ff_roms_booking FOREIGN KEY (bookingid)
        REFERENCES public.booking (id),
    CONSTRAINT ff_rooms_city FOREIGN KEY (cityid)
        REFERENCES public.city (id),
    CONSTRAINT ff_rooms_client FOREIGN KEY (clientid)
        REFERENCES public.client (id)
);

insert into rooms (rating,clientid,flagFree,cityid,bookingid)
values
    (3,1,0,1,1),
    (3,2,0,2,2),
    (4,3,1,3,null),
    (4,4,0,4,3),
    (3,5,1,5,null),
    (5,6,0,6,4);
