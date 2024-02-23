CREATE TABLE category (
                          id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
                         id INT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(255) NOT NULL,
                         category_id INTEGER REFERENCES category(id)
);
