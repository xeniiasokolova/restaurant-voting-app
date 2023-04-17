DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq_dishes;
DROP SEQUENCE IF EXISTS global_seq_users;
DROP SEQUENCE IF EXISTS global_seq_restaurants;

CREATE SEQUENCE global_seq_users START WITH 100000;
CREATE SEQUENCE global_seq_dishes START WITH 100000;
CREATE SEQUENCE global_seq_restaurants START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_users'),
    name             VARCHAR                        NOT NULL,
    email            VARCHAR                        NOT NULL,
    password         VARCHAR                        NOT NULL,
    registered       TIMESTAMP       DEFAULT now()  NOT NULL,
    voted            BOOL            DEFAULT FALSE  NOT NULL,
    dish_id          INTEGER                        ,
    datetime_vote    TIMESTAMP
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE user_role
(
    user_id INTEGER     NOT NULL,
    role    VARCHAR     NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq_restaurants'),
    name        VARCHAR         NOT NULL,
    registered   TIMESTAMP      DEFAULT now() NOT NULL
);

CREATE TABLE dishes
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq_dishes'),
    restaurant_id   INTEGER              NOT NULL,
    name            VARCHAR              NOT NULL,
    price           NUMERIC(10, 2)       NOT NULL,
    description     TEXT                 NOT NULL,
    voices          INT     DEFAULT 0    NOT NULL,
    registered      TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);