CREATE TABLE POST (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content VARCHAR(255) NULL,
                      created TIMESTAMP
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         post_id BIGINT NOT NULL,
                         content VARCHAR(255) NULL,
                         created TIMESTAMP
);

ALTER TABLE comment
    ADD CONSTRAINT comment_post_id
        FOREIGN KEY (post_id) REFERENCES post(id);