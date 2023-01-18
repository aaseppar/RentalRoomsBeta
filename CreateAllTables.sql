DROP TABLE IF EXISTS public.booking;

CREATE TABLE IF NOT EXISTS public.booking
(
    id integer NOT NULL,
    beginterm date NOT NULL,
    endterm date NOT NULL,
    price integer NOT NULL,
    clientid integer NOT NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (id),
    CONSTRAINT ff_client FOREIGN KEY (clientid)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);


DROP TABLE IF EXISTS public.city;
CREATE TABLE IF NOT EXISTS public.city
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    countryid integer NOT NULL,
    CONSTRAINT city_pkey1 PRIMARY KEY (id),
    CONSTRAINT firstkey FOREIGN KEY (countryid)
        REFERENCES public.country (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

DROP TABLE IF EXISTS public.client;
CREATE TABLE IF NOT EXISTS public.client
(
    id integer NOT NULL,
    userfullname text COLLATE pg_catalog."default" NOT NULL,
    login text COLLATE pg_catalog."default" NOT NULL,
    passwd text COLLATE pg_catalog."default" NOT NULL,
    birthday text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    ratingid integer NOT NULL,
    cityid integer NOT NULL,
    CONSTRAINT "Users_pkey" PRIMARY KEY (id),
    CONSTRAINT ff_client FOREIGN KEY (cityid)
        REFERENCES public.city (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ff_rating FOREIGN KEY (ratingid)
        REFERENCES public.rating (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

DROP TABLE IF EXISTS public.country;

CREATE TABLE IF NOT EXISTS public.country
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT city_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.rating
(
    id integer NOT NULL,
    value integer NOT NULL,
    CONSTRAINT rating_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.rooms;

CREATE TABLE IF NOT EXISTS public.rooms
(
    id integer NOT NULL,
    rating integer,
    clientid integer NOT NULL,
    "flagFree" integer,
    cityid integer NOT NULL,
    bookingid integer,
    CONSTRAINT "Rooms_pkey" PRIMARY KEY (id),
    CONSTRAINT ff_roms_booking FOREIGN KEY (bookingid)
        REFERENCES public.booking (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION ,
    CONSTRAINT ff_rooms_city FOREIGN KEY (cityid)
        REFERENCES public.city (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ff_rooms_client FOREIGN KEY (clientid)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

	
	
	