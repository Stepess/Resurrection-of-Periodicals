DROP TABLE users IF EXISTS;

create table users
(
  id LONG not null auto_increment primary key,
  username VARCHAR(50) not null,
  password VARCHAR(50) not null,
  firstName VARCHAR(50) not null,
  lastName VARCHAR(50) not null,
  email VARCHAR(50) not null,
  enabled  boolean     not null
);

DROP TABLE authorities IF EXISTS;

create table authorities
(
  username  VARCHAR(50) not null,
  authority VARCHAR(50) not null,
  constraint fk_authorities_users foreign key (username) references users (id)
);
create unique index ix_auth_username on authorities (username, authority);