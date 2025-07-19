create database employee_management_db;
use employee_management_db;

create table employees (
    id int auto_increment primary key,
    name varchar(255) not null
);

create table projects (
    id int auto_increment primary key,
    project_name varchar(255) not null
);

create table employee_projects (
    employee_id int,
    project_id int,
    primary key (employee_id, project_id),
    foreign key (employee_id) references employees(id) on delete cascade,
    foreign key (project_id) references projects(id) on delete cascade
);

delimiter //

create procedure assign_employee_to_project (
    emp_id int,
    proj_id int
)
begin
    -- kiểm tra đã tồn tại bản ghi chưa
    if not exists (
        select 1 from employee_projects
        where employee_id = emp_id and project_id = proj_id
    ) then
        insert into employee_projects (employee_id, project_id)
        values (emp_id, proj_id);
    end if;
end //

delimiter ;
