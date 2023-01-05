-- DATABASE
CREATE DATABASE naive_db;
\c naive_db;

-- SCHEMAS
CREATE TABLE IF NOT EXISTS Communities(
    entity_id   UUID DEFAULT gen_random_uuid(),
    name        VARCHAR(255),
    country     VARCHAR(150),
    PRIMARY KEY(entity_id)
);

CREATE TABLE IF NOT EXISTS Human(
    entity_id   UUID DEFAULT gen_random_uuid(),
    name        VARCHAR(255),
    age         INT,
    gender      VARCHAR(50),
    community   UUID,
    PRIMARY KEY(entity_id),
    CONSTRAINT fk_community   FOREIGN KEY(community) REFERENCES Communities(entity_id)
);

CREATE TABLE IF NOT EXISTS Families(
    entity_id    UUID DEFAULT gen_random_uuid(),
    family_name  VARCHAR(255),
    PRIMARY KEY(entity_id)
);

CREATE TABLE IF NOT EXISTS Parents(
    human_id   UUID,
    family_id  UUID,
    PRIMARY KEY(human_id, family_id),
    CONSTRAINT fk_human    FOREIGN KEY(human_id)    REFERENCES Human(entity_id),
    CONSTRAINT fk_family   FOREIGN KEY(family_id)   REFERENCES Families(entity_id)
);

CREATE TABLE IF NOT EXISTS Children(
    human_id    UUID,
    child_id    UUID,
    PRIMARY KEY(human_id, child_id),
    CONSTRAINT fk_human FOREIGN KEY(human_id)   REFERENCES Human(entity_id),
    CONSTRAINT fk_child FOREIGN KEY(child_id)   REFERENCES Families(entity_id)
);

-- SOME INSERTS
WITH ng_community_uuid AS (
    INSERT INTO Communities(name, country) VALUES ('Masaka', 'Nigeria') RETURNING entity_id
) INSERT INTO Human(name, age, gender, community)
    SELECT 'NAT', 12, 'MALE', entity_id FROM ng_community_uuid;

WITH nl_community_uuid AS (
    INSERT INTO Communities(name, country) VALUES ('Rotterdam', 'Netherlands') RETURNING entity_id
) INSERT INTO Human(name, age, gender, community)
    SELECT 'RUI', 29, 'MALE', entity_id FROM nl_community_uuid;

WITH ro_community_uuid AS (
    INSERT INTO Communities(name, country) VALUES ('Tank', 'Romania') RETURNING entity_id
) INSERT INTO Human(name, age, gender, community)
    SELECT 'FATE', 50, 'FEMALE', entity_id FROM ro_community_uuid;