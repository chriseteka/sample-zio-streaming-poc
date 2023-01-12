-- DATABASE
CREATE DATABASE naive_db;
\c naive_db;

-- SCHEMAS
CREATE TABLE IF NOT EXISTS Communities(
    community_id    UUID DEFAULT gen_random_uuid(),
    community_name  VARCHAR(255),
    country         VARCHAR(150),
    PRIMARY KEY(community_id)
);

CREATE TABLE IF NOT EXISTS Human(
    human_id    UUID DEFAULT gen_random_uuid(),
    human_name  VARCHAR(255),
    age         INT,
    gender      VARCHAR(50),
    born_in     UUID,
    PRIMARY KEY(human_id),
    CONSTRAINT fk_community   FOREIGN KEY(born_in) REFERENCES Communities(community_id)
);

CREATE TABLE IF NOT EXISTS Families(
    family_id    UUID DEFAULT gen_random_uuid(),
    family_name  VARCHAR(255),
    lives_in     UUID,
    PRIMARY KEY(family_id),
    CONSTRAINT fk_community   FOREIGN KEY(lives_in) REFERENCES Communities(community_id)
);

CREATE TABLE IF NOT EXISTS Parents(
    parent_id  UUID,
    family_id  UUID,
    PRIMARY KEY(parent_id, family_id),
    CONSTRAINT fk_human    FOREIGN KEY(parent_id)   REFERENCES Human(human_id),
    CONSTRAINT fk_family   FOREIGN KEY(family_id)   REFERENCES Families(family_id)
);

CREATE TABLE IF NOT EXISTS Children(
    child_id    UUID,
    family_id    UUID,
    PRIMARY KEY(child_id, family_id),
    CONSTRAINT fk_human FOREIGN KEY(child_id)   REFERENCES Human(human_id),
    CONSTRAINT fk_child FOREIGN KEY(family_id)   REFERENCES Families(family_id)
);

-- SOME INSERTS
WITH ng_community_uuid AS (
    INSERT INTO Communities(community_name, country) VALUES ('Masaka', 'Nigeria') RETURNING community_id
) INSERT INTO Human(human_name, age, gender, born_in)
    SELECT 'NAT', 12, 'MALE', community_id FROM ng_community_uuid;

WITH nl_community_uuid AS (
    INSERT INTO Communities(community_name, country) VALUES ('Rotterdam', 'Netherlands') RETURNING community_id
) INSERT INTO Human(human_name, age, gender, born_in)
    SELECT 'RUI', 29, 'MALE', community_id FROM nl_community_uuid;

WITH ro_community_uuid AS (
    INSERT INTO Communities(community_name, country) VALUES ('Tank', 'Romania') RETURNING community_id
) INSERT INTO Human(human_name, age, gender, born_in)
    SELECT 'FATE', 50, 'FEMALE', community_id FROM ro_community_uuid;