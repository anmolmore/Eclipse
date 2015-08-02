create schema GLOBOMART

CREATE table GLOBOMART.PRODUCTS 
(ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
NAME VARCHAR(40) NOT NULL,
DESCRIPTION VARCHAR(100),
CATEGORYID INTEGER,
UOM VARCHAR(20),
PRIMARY KEY (ID));

INSERT INTO GLOBOMART.PRODUCT values (1, "Colgate Active", "Toothpaste from colgate", 1, "PC")