CREATE TYPE creador as
	(
		nick character varying(10),
		nomUsuari character varying(40),
		majorEdat  boolean,
		residencia character varying(25)
	);


CREATE TABLE Canal
	(
		id integer NOT NULL,
		nom character varying(30),
		tema character varying(40),
		numVideos integer,
  		propietari creador,
		subscriptors character varying(40)[],
  		CONSTRAINT clauPrimariaCanal PRIMARY KEY (id)
	)
	WITH (
  		OIDS=FALSE
	);


ALTER TABLE Canal
	OWNER TO ioc;

