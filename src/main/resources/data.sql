INSERT INTO USER_DETAILS (id, birth_date, name)
values (10001, current_date(), 'Robert');

INSERT INTO USER_DETAILS (id, birth_date, name)
values (10002, current_date(), 'Trobor');

INSERT INTO USER_DETAILS (id, birth_date, name)
values (10003, current_date(), 'Claire');

INSERT INTO POST (id, description, user_id)
values (20001, 'I want to learn Spring Boot''', '10001');

INSERT INTO POST (id, description, user_id)
values (20002, 'I want to learn AWS''', '10001');

INSERT INTO POST (id, description, user_id)
values (20003, 'I want to learn Spring Security', '10001');

INSERT INTO POST (id, description, user_id)
values (20004, 'I want to learn Spring Boot', '10002');

INSERT INTO POST (id, description, user_id)
values (20005, 'I want to learn Spring MVC', '10002');

INSERT INTO POST (id, description, user_id)
values (20006, 'I want to learn Python', '10002');

INSERT INTO POST (id, description, user_id)
values (20007, 'I want to buy Emgrand', '10003');

INSERT INTO POST (id, description, user_id)
values (20008, 'I want to buy gold', '10003');

INSERT INTO POST (id, description, user_id)
values (20009, 'I love bebbe', '10003');
