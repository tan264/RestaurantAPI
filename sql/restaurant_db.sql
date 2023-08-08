# run db: docker run -d --rm -p 3309:3306 --name my_container -e MYSQL_ALLOW_EMPTY_PASSWORD -v foodprj-volume:/var/lib/mysql mysql:latest

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    us_id              INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    us_username        VARCHAR(45) UNIQUE NOT NULL,
    us_password        VARCHAR(120)       NOT NULL,
    us_full_name       VARCHAR(45),
    us_address         VARCHAR(120),
    us_mobile          VARCHAR(10)        NOT NULL,
    us_status          TINYINT,
    us_created_date    DATETIME           NOT NULL,
    us_update_password TINYINT,
    us_group           TINYINT            NOT NULL,
    us_email           VARCHAR(45)
);

DROP TABLE IF EXISTS posts;
CREATE TABLE posts
(
    ps_id             INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ps_title          VARCHAR(120)       NOT NULL,
    ps_thumbnail_link VARCHAR(120),
    ps_created_date   DATETIME           NOT NULL,
    ps_published      TINYINT,
    cf_id             INT                NOT NULL,
    us_id             INT                NOT NULL
);

DROP TABLE IF EXISTS book_table;
CREATE TABLE book_table
(
    bt_id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    fd_id            INT                NOT NULL,
    us_id            INT                NOT NULL,
    bt_datetime      DATETIME           NOT NULL,
    bt_number_people INT                NOT NULL,
    bt_note          TEXT
);

DROP TABLE IF EXISTS food;
CREATE TABLE food
(
    fd_id           INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    fd_name         VARCHAR(150)       NOT NULL,
    fd_thumbnail    VARCHAR(120),
    fd_price        INT                NOT NULL,
    fd_description  TEXT,
    fd_youtube_link VARCHAR(120),
    cf_id           INT                NOT NULL,
    us_id           INT                NOT NULL
);

DROP TABLE IF EXISTS image;
CREATE TABLE image
(
    im_id   INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    im_link VARCHAR(120),
    fd_id   INT                NOT NULL
);

DROP TABLE IF EXISTS category;
CREATE TABLE category
(
    cf_id          INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    cf_name        VARCHAR(120)       NOT NULL,
    cf_image_link  VARCHAR(120),
    cf_description TEXT,
    cf_type        TINYINT
);

ALTER TABLE food
    ADD CONSTRAINT fk_food_category
        FOREIGN KEY food (cf_id) REFERENCES category (cf_id);

ALTER TABLE food
    ADD CONSTRAINT fk_food_users
        FOREIGN KEY food (us_id) REFERENCES users (us_id);

ALTER TABLE image
    ADD CONSTRAINT fk_image_food
        FOREIGN KEY image (fd_id) REFERENCES food (fd_id);

ALTER TABLE book_table
    ADD CONSTRAINT fk_book_table_food
        FOREIGN KEY book_table (fd_id) REFERENCES food (fd_id);

ALTER TABLE book_table
    ADD CONSTRAINT fk_book_table_users
        FOREIGN KEY book_table (us_id) REFERENCES users (us_id);

ALTER TABLE posts
    ADD CONSTRAINT fk_posts_users
        FOREIGN KEY posts (us_id) REFERENCES users (us_id);

ALTER TABLE posts
    ADD CONSTRAINT fk_posts_category
        FOREIGN KEY posts (cf_id) REFERENCES category (cf_id);