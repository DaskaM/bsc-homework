insert into note (title, finished, created, last_updated)
values ('Koupit americkou lednici',  true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into note (title, finished, created, last_updated)
values ('Find out how to sing like Freddy Mercury', false,
        PARSEDATETIME('2019-03-04 17:36:14.741', 'yyyy-MM-dd hh:mm:ss.SS'), CURRENT_TIMESTAMP());

insert into note (title, finished, created, last_updated)
values ('Learn AWS deployment', false,
        PARSEDATETIME('2019-07-11 10:15:32.125', 'yyyy-MM-dd hh:mm:ss.SS'), CURRENT_TIMESTAMP());

insert into note (title, finished, created, last_updated)
values ('Koupit kola na jaro', true,
        PARSEDATETIME('2019-01-20 14:05:56.231', 'yyyy-MM-dd hh:mm:ss.SS'), CURRENT_TIMESTAMP());