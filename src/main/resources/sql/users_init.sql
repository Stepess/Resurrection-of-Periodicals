insert into users (username, password, email, first_name, last_name, registration_date) values
('senpai', 'qwerty13', 'stepanersh@gmail.com',  'Stepan',  'Yershov', '2018-09-11'),
('skif', '1357', 'karamivan@gmail.com', 'Ivan', 'Karamzin', '2011-12-28'),
('aurora', 'zxc21', 'kreiser@gmail.com','Aurora', 'Berger', '2014-10-01'),
('garik1', 'qawsed', 'grace@gmail.com',  'Igor', 'Mirniy', '2017-02-26');

insert into authority (name) values
('ADMIN'), ('USER');

insert into user_authority (user_id, authority_id) values
(1,1), (1,2), (2,2), (3,2), (4,2);