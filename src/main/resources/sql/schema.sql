DROP TABLE users IF EXISTS;

create table users
(
  id        LONG        not null auto_increment,
  username  VARCHAR(50) not null,
  password  VARCHAR(50) not null,
  firstName VARCHAR(50) not null,
  lastName  VARCHAR(50) not null,
  email     VARCHAR(50) not null,
  enabled   boolean     not null,
  primary key (id)
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