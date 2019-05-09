DROP TABLE users IF EXISTS;

CREATE TABLE IF NOT EXISTS users
(
  id                INT         NOT NULL AUTO_INCREMENT,
  username          VARCHAR(45) NOT NULL,
  password          VARCHAR(45) NOT NULL,
  email             VARCHAR(45) NOT NULL,
  registration_date DATE        NOT NULL,
  first_name        VARCHAR(45) NOT NULL,
  last_name         VARCHAR(45) NOT NULL,
  enabled           BOOLEAN DEFAULT true,
  PRIMARY KEY (id),
  UNIQUE INDEX login_UNIQUE (username ASC),
  UNIQUE INDEX email_UNIQUE (email ASC)
);

DROP TABLE authorities IF EXISTS;

create table authorities
(
  username  VARCHAR(50) not null,
  authority VARCHAR(50) not null,
  foreign key (username) references users (id)
);
create unique index ix_auth_username on authorities (username, authority);

DROP TABLE comments if exists;

create table comments
(
  id        LONG        not null auto_increment,
  user_id   LONG        not null,
  message   varchar(50) not null,
  date      date        not null,
  latitude  double      not null,
  longitude double      not null,
  foreign key (user_id) references users (id) on delete cascade,
  primary key (id)
);