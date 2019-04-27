INSERT INTO users(username, password, firstName, lastName, email, enabled)
VALUES ('senpai', '1234', 'Stepan', 'Yersh', 'step@gmail.com', true);
INSERT INTO users(username, password, firstName, lastName, email, enabled)
VALUES ('admin', '1111', 'alex', 'akka', 'aaaa123@gmail.com', true);
insert into comments (user_id, message, date, latitude, longitude)
VALUES (1, 'really useful comment', '2019-12-28', 432.2, 212.6);
insert into comments (user_id, message, date, latitude, longitude)
VALUES (1, 'another really useful comment', '2019-06-21', 301.2, 12.4);
insert into comments (user_id, message, date, latitude, longitude)
VALUES (2, 'admin comment uuuuu', '2019-01-03', 128.6, 105.9);