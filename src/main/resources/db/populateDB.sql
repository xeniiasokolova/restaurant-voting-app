DELETE FROM user_role;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;

ALTER SEQUENCE global_seq_dishes RESTART WITH 100000;
ALTER SEQUENCE global_seq_users RESTART WITH 100000;
ALTER SEQUENCE global_seq_restaurants RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100000),
       ('USER', 100001);

INSERT INTO restaurants (name)
VALUES ('Sushi'), ('Maneki'), ('Мамука');


INSERT INTO dishes (name, description, price, restaurant_id)
VALUES ('Филадельфия', 'Состав: сыр, нори, рис, лосось', 500.10, (SELECT id FROM restaurants WHERE name like 'Sushi')),
       ('Удон с соусом тирияки', 'Состав: удон, курица, тирияки', 1000.30, (SELECT id FROM restaurants WHERE name like 'Maneki')),
       ('Хачапури', 'Состав: мука, сыр, яйца', 140, (SELECT id FROM restaurants WHERE name like 'Мамука')),
       ('Чкмерули', 'СоСтав: курица, сливки, чеснок', 100, (SELECT id FROM restaurants WHERE name like 'Мамука')),
       ('Рол с лосем', 'Состав: нори, рис, сливочный сыр, лосось', 256.20, (SELECT id FROM restaurants WHERE name like 'Sushi')),
       ('Канада', 'Состав: угорь, сыр, нори, рис', 1000, (SELECT id FROM restaurants WHERE name like 'Sushi')),
       ('Жареный лосось', 'Состав: лосось', 510, (SELECT id FROM restaurants WHERE name like 'Maneki')),
       ('Рис с курицей', 'Состав: курица, рис на пару', 280.00, (SELECT id FROM restaurants WHERE name like 'Мамука')),
       ('Салат с фунчозой', 'Состав: фунчоза, морковь, перец красный, лук, чеснок', 150.99, (SELECT id FROM restaurants WHERE name like 'Maneki'));
