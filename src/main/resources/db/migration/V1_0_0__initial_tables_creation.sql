CREATE TABLE IF NOT EXISTS USERS (
ID  INTEGER NOT NULL AUTO_INCREMENT,
EMAIL VARCHAR(100) NOT NULL,
PASSWORD VARCHAR(255),
ROLE VARCHAR(500) NOT NULL,
CONSTRAINT pk_usr_id PRIMARY KEY(ID),
CONSTRAINT uq_email UNIQUE(EMAIL)
);

CREATE TABLE IF NOT EXISTS RECIPE (
ID INTEGER NOT NULL AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL,
DESCRIPTION VARCHAR(255),
CATEGORY VARCHAR(100),
CREATED_MODIFIED_DATETIME TIMESTAMP,
USER_ID INTEGER,
CONSTRAINT pk_rcp_id PRIMARY KEY(ID),
CONSTRAINT uq_rcp_name_descr UNIQUE(NAME),
CONSTRAINT fk_rcp_usr FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE IF NOT EXISTS INGREDIENTS (
RECIPE_ID INTEGER,
INGREDIENT VARCHAR(100) NOT NULL,
CONSTRAINT fk_ingr_rcp FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID)
);

CREATE TABLE IF NOT EXISTS DIRECTIONS (
RECIPE_ID INTEGER,
DIRECTION VARCHAR(100) NOT NULL,
CONSTRAINT fk_dir_rcp FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID)
);

