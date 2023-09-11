drop database if exists ReportMant;
create database ReportMant;
use ReportMant;
create table if not exists Mantenimiento(
	mr_id int primary key auto_increment,
    mr_module nvarchar(100),
    mr_description nvarchar(100),
    mr_status nvarchar(100), 
    mr_solution nvarchar(1000),
    mr_s_id int not null,
    mr_timestamp timestamp
);

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Altas`(
	in modulo nvarchar(100),
    in descripcion nvarchar(100),
    in stat nvarchar(100),
    in solucion nvarchar(1000),
    in mrsid int,
    in fecha timestamp
)
BEGIN	
insert into Mantenimiento (mr_id , mr_module, mr_description, mr_status, mr_solution,mr_s_id, mr_timestamp) values(0,modulo,descripcion,stat,solucion,mrsid,fecha);
END $$
delimiter 

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RequestTodo`()
BEGIN
select * from Mantenimiento order by mr_id asc;
END $$
delimiter ;

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RequestEspecifico`(in fol int)
BEGIN
select * from Mantenimiento where mr_id = fol;
END $$
delimiter ;

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RequestDesarrollador`(in idres int)
BEGIN
select * from Mantenimiento where mr_s_id = idres;
END $$
delimiter ;

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Actualizar`(
	in fecha timestamp, 
    in descripcion nvarchar(1000),
    in solucion nvarchar(1000),
    in stat nvarchar(100),
    in fol int)
BEGIN
update Mantenimiento set mr_timestamp = fecha, mr_description = descripcion, mr_solution = solucion,mr_status = stat where mr_id = fol;
END $$
delimiter ;

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Borrar`(in fol int)
BEGIN
delete from Mantenimiento where mr_id = fol;
END $$
delimiter ;

select * from Mantenimiento;
