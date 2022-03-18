CREATE TABLE IF NOT EXISTS USERS (
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES
(
    noteId
    INT
    PRIMARY
    KEY
    auto_increment,
    noteTitle
    VARCHAR
(
    20
),
    noteDescription VARCHAR
(
    1000
),
    userid INT,
    foreign key
(
    userid
) references USERS
(
    id
)
    );

CREATE TABLE IF NOT EXISTS FILES
(
    id
    INT
    PRIMARY
    KEY
    auto_increment,
    filename
    VARCHAR,
    contenttype
    VARCHAR,
    filesize
    VARCHAR,
    userid
    INT,
    filedata
    BLOB,
    foreign
    key
(
    userid
) references USERS
(
    id
)
    );

CREATE TABLE IF NOT EXISTS CREDENTIALS
(
    credentialsId
    INT
    PRIMARY
    KEY
    auto_increment,
    credentialsUrl
    VARCHAR
(
    100
),
    credentialsUsername VARCHAR
(
    30
),
    key VARCHAR,
    credentialsPassword VARCHAR,
    userid INT,
    foreign key
(
    userid
) references USERS
(
    id
)
    );