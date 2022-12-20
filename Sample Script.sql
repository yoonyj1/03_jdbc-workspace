-- ����� Ŭ��, JDBC ���� ����� ���Ѻο��ϱ�
-- ���̺� ����
DROP TABLE MEMBER;
-- ������ ����
DROP SEQUENCE SEQ_USERNO;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                      -- ȸ����ȣ
    USERID VARCHAR2(15) NOT NULL UNIQUE,            -- ȸ�����̵�
    USERPWD VARCHAR2(15) NOT NULL,                  -- ȸ����й�ȣ
    USERNAME VARCHAR2(20) NOT NULL,                 -- ȸ����
    GENDER CHAR(1) CHECK(GENDER IN('M', 'F')),      -- ����
    AGE NUMBER,                                     -- ����
    EMAIL VARCHAR2(30),                             -- �̸���
    PHONE CHAR(11),                                 -- ��ȭ��ȣ
    ADDRESS VARCHAR2(100),                          -- �ּ�
    HOBBY VARCHAR2(50),                             -- ���
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL        -- ȸ��������
);

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '������', 'M', 45, 'admin@iei.or.kr', '01012345678', '����', NULL, '2021-07-27');

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', 'ȫ�浿', null, 23, 'user01@iei.or.kr', '01023456789', '�λ�', '���,��ȭ����', '2021-08-02');

COMMIT;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

/*PRODUCT ���̺� �����
PNO
PNAME
PRICE
REG_DATE

��������� �Է� ����
1=> SELECT 2=> INSERT 3=>DELETE 4=> ����

���������غ���*/
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

SELECT * FROM MEMBER; WHERE USERNAME LIKE '%��%';

UPDATE MEMBER 
SET USERPWD = ''.
    EMAIL = '',
    PHONE = '',
    ADDRESS = '',
WHERE USERID = '';

DELETE FROM MEMBER
WHERE USERID = '';

SELECT * FROM MEMBER;