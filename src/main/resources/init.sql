
INSERT INTO USUARIO
VALUES
    (1, 'homer', 'homer'),
    (2, 'marge', 'marge'),
    (3, 'lisa', 'lisa'),
    (4, 'bart', 'bart');

INSERT INTO CIRCUITO
VALUES
    (1, 'Mi circuito');

INSERT INTO PERTENENCIAUSUARIOCIRCUITO
VALUES
    (1, 0, 1, 1),
    (2, 1, 1, 2),
    (3, 2, 1, 3);

INSERT INTO PERTENENCIATIPOFIRMANTE
VALUES
    (1),
    (3);

INSERT INTO PERTENENCIATIPOVALIDADOR
VALUES
    (2);