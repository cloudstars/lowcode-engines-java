DROP TABLE IF EXISTS STU;
CREATE TABLE STU
(
    `ID`       INT          NOT NULL AUTO_INCREMENT,
    `KEY`      VARCHAR(10)  NOT NULL,
    `NAME`     VARCHAR(32)  NOT NULL,
    `AGE`      INT          DEFAULT 0,
    PRIMARY KEY (`ID`)
) COMMENT = '学生表';