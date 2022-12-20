-- 깃허브 클론, JDBC 계정 만들고 권한부여하기
-- 테이블 삭제
DROP TABLE MEMBER;
-- 시퀀스 삭제
DROP SEQUENCE SEQ_USERNO;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                      -- 회원번호
    USERID VARCHAR2(15) NOT NULL UNIQUE,            -- 회원아이디
    USERPWD VARCHAR2(15) NOT NULL,                  -- 회원비밀번호
    USERNAME VARCHAR2(20) NOT NULL,                 -- 회원명
    GENDER CHAR(1) CHECK(GENDER IN('M', 'F')),      -- 성별
    AGE NUMBER,                                     -- 나이
    EMAIL VARCHAR2(30),                             -- 이메일
    PHONE CHAR(11),                                 -- 전화번호
    ADDRESS VARCHAR2(100),                          -- 주소
    HOBBY VARCHAR2(50),                             -- 취미
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL        -- 회원가입일
);

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '관리자', 'M', 45, 'admin@iei.or.kr', '01012345678', '서울', NULL, '2021-07-27');

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '홍길동', null, 23, 'user01@iei.or.kr', '01023456789', '부산', '등산,영화보기', '2021-08-02');

COMMIT;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

/*PRODUCT 테이블 만들기
PNO
PNAME
PRICE
REG_DATE

사용자한테 입력 받음
1=> SELECT 2=> INSERT 3=>DELETE 4=> 종료

쿼리수행해보기*/
DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
    PNO NUMBER PRIMARY KEY,
    PNAME VARCHAR2(50) NOT NULL,
    PRICE NUMBER,
    REG_DATE DATE DEFAULT SYSDATE
);
DROP SEQUENCE SEQ_PNO;

CREATE SEQUENCE SEQ_PNO;

COMMIT;

SELECT * FROM PRODUCT;

SELECT * FROM MEMBER WHERE USERID = 'admin';

SELECT * FROM MEMBER; WHERE USERNAME LIKE '%관%';

UPDATE MEMBER 
SET USERPWD = ''.
    EMAIL = '',
    PHONE = '',
    ADDRESS = '',
WHERE USERID = '';

DELETE FROM MEMBER
WHERE USERID = '';

SELECT * FROM MEMBER;