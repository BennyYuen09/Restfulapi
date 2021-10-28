create table employee (
    id int not null auto_increment primary key ,
    name varchar(255) not null,
    age int not null ,
    salary int not null,
    companyId int not null ,
    foreign key (companyId) references company(id)
)