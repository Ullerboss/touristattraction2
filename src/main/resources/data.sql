INSERT INTO tag (name)
VALUES ('KUNST'),
       ('MUSEUM'),
       ('NATUR'),
       ('BØRNEVENLIG'),
       ('GRATIS');

INSERT INTO tourist_attraction (name, description, city)
VALUES ('SMK', 'Museum for Kunst', 'København'),
       ('Odense Zoo', 'TEST123', 'Odense'),
--        ('Odense Zoo', 'Europas bedste zoo', 'Odense'),
       ('Dyrehaven', 'Naturpark med skovområder', 'Kongens Lyngby'),
       ('Tivoli', 'Forlystelsespark midt i Københavns centrum', 'København');

INSERT INTO attraction_tag (attraction_id, tag_id)
VALUES (1, 1), -- SMK -> KUNST
       (1, 2), -- SMK -> MUSEUM
       (2, 4), -- Odense Zoo -> BØRNEVENLIG
       (3, 3), -- Dyrehaven -> NATUR
       (3, 5), -- Dyrehaven -> GRATIS
       (4, 4); -- Tivoli -> BØRNEVENLIG