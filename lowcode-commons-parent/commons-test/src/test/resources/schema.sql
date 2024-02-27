/* 初始化A表 */
DROP TABLE IF EXISTS A;
CREATE TABLE A
(
    `ID`       INT          NOT NULL AUTO_INCREMENT,
    `NAME`     VARCHAR(32)  NOT NULL,
    `DATE`     DATE         NOT NULL,
    `TIME`     TIME         NOT NULL,
    `DESC`     VARCHAR(200) NOT NULL DEFAULT '',
    `PRICE`    DECIMAL      NOT NULL,
    `NUMBER`   INTEGER      NOT NULL,
    `SCHEMA`   TEXT         NOT NULL,
    PRIMARY KEY (`ID`)
) COMMENT = 'A表';