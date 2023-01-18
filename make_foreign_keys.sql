DROP TABLE IF EXISTS public."Rooms";

CREATE TABLE IF NOT EXISTS public."Rooms"
(
    id integer NOT NULL,
    country text COLLATE pg_catalog."default" NOT NULL,
    city text COLLATE pg_catalog."default" NOT NULL,
    "beginTerm" text COLLATE pg_catalog."default",
    "endTerm" text COLLATE pg_catalog."default",
    price integer NOT NULL,
    rating integer,
    "userId" integer NOT NULL,
    "ownerId" integer NOT NULL,
    "flagFree" integer,
    CONSTRAINT "Rooms_pkey" PRIMARY KEY (id),
	foreign key ("userId") references public."Users" (id),
	foreign key ("ownerId") references public."Owners" (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Rooms"
    OWNER to postgres;