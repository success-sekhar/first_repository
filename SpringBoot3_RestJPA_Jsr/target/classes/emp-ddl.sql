DROP TABLE EMPLOYEE_HR;
CREATE Table EMPLOYEE_HR(
EMPID number(6) primary key,
FIRSTNAME varchar2(30),
LASTNAME varchar2(30),
email varchar2(30),
age number(3),
dtBirth date,
dtRetire date
);
DROP sequence emp_hr_seq; 
create sequence emp_hr_seq start with 10 increment by 1;