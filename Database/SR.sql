drop database if exists spaceride;
create database spaceride;
use spaceride;
drop table if exists usuariosspaceride;
CREATE TABLE usuariosspaceride (
    u_id INT AUTO_INCREMENT PRIMARY KEY,
    u_nombre NVARCHAR(255) NOT NULL,
    u_contra NVARCHAR(255) NOT NULL,
    u_correo NVARCHAR(255) NOT NULL,
    u_type INT NOT NULL,
    u_victorias INT NOT NULL,
    u_derrotas INT NOT NULL,
    u_muerto BOOLEAN NOT NULL
);
drop table if exists areaspaceride;
CREATE TABLE areaspaceride (
    area_u_id INT PRIMARY KEY AUTO_INCREMENT,
    area_max INT,
    area_max_id INT,
    area_mate INT,
    area_fis INT,
    area_esp INT,
    area_huni INT,
    area_geo INT,
    area_ing INT,
    area_qui INT,
    area_bio INT,
    area_hmex INT,
    area_astro INT,
    area_ent INT,
    area_arte INT,
    area_tec INT,
    FOREIGN KEY (area_u_id)
        REFERENCES usuariosspaceride (u_id)
);
drop table if exists preguntaspaceride;
CREATE TABLE preguntaspaceride (
    p_id INT PRIMARY KEY AUTO_INCREMENT,
    p_p NVARCHAR(350),
    p_r1 NVARCHAR(350),
    p_r2 NVARCHAR(350),
    p_r3 NVARCHAR(350),
    p_r4 NVARCHAR(350),
    p_correcta INT,
    p_dif INT,
    p_area NVARCHAR(50),
    p_seleccion BOOLEAN
);
drop table if exists muertospaceride;
CREATE TABLE muertospaceride (
    m_u_id1 INT PRIMARY KEY,
    m_u_id2 INT,
    m_area INT,
    m_dif INT,
    m_correcta INT,
    FOREIGN KEY (m_u_id1)
        REFERENCES usuariosspaceride (u_id),
    FOREIGN KEY (m_u_id2)
        REFERENCES usuariosspaceride (u_id)
);
drop table if exists faqspaceride;
CREATE TABLE faqspaceride (
    faq_id INT AUTO_INCREMENT PRIMARY KEY,
    faq_pregunta NVARCHAR(750),
    faq_respuesta NVARCHAR(750),
    faq_tema NVARCHAR(20),
    faq_prioridad INT,
    faq_aprobacion BOOLEAN,
    faq_u_id INT,
    faq_a_id INT,
    FOREIGN KEY (faq_u_id)
        REFERENCES usuariosspaceride (u_id),
    FOREIGN KEY (faq_a_id)
        REFERENCES usuariosspaceride (u_id)
);
drop table if exists eventreportspaceride;
CREATE TABLE eventreportspaceride(
	er_id int auto_increment primary key,
	er_u_Id int not null,
	er_transcript nvarchar(750) not null,
	er_description nvarchar (255) not null,
	er_category nvarchar(20) not null,
	er_status nvarchar(20) not null,
	er_solution nvarchar(750),
	er_s_id	int not null,
    er_timestamp timestamp not null,
	FOREIGN KEY (er_u_Id)
        REFERENCES usuariosspaceride (u_id),
    FOREIGN KEY (er_s_id)
        REFERENCES usuariosspaceride (u_id)
);
drop table if exists maintenancereportspaceride;
CREATE TABLE maintenancereportspaceride(
	mr_id int auto_increment primary key,
	mr_module nvarchar(750) not null,
	mr_description nvarchar (255) not null,
	mr_status nvarchar(20) not null,
	mr_solution nvarchar(750),
	mr_s_id	int not null,
    mr_timestamp timestamp not null,
    FOREIGN KEY (mr_s_id)
        REFERENCES usuariosspaceride (u_id)
);
drop table if exists chatspaceride;
CREATE TABLE chatspaceride(
	chat_id int primary key auto_increment,
	chat_s_id int not null,
    chat_u_id int not null, 
	chat_u_msgs nvarchar(750),
    chat_s_msgs nvarchar(750),
    chat_time nvarchar(100),
    chat_timestamp timestamp,
    FOREIGN KEY (chat_u_id)
        REFERENCES usuariosspaceride (u_id),
    FOREIGN KEY (chat_s_id)
        REFERENCES usuariosspaceride (u_id)
);
drop procedure if exists createUser;
delimiter $$
create procedure createUser(
	in u_id_sp int,
    in u_nombre_sp nvarchar(255),
    in u_contra_sp nvarchar(255),
    in u_correo_sp nvarchar(255),
    in u_admin_sp int
)
begin
	declare created boolean;
	declare exist int;
	set exist = (select count(*) from usuariosspaceride where u_contra=u_contra_sp);
    if(exist = 0)
		then
			insert into usuariosspaceride values(u_id_sp, u_nombre_sp, u_contra_sp, u_correo_sp, u_admin_sp, 0, 0, false);
            insert into areaspaceride values(u_id_sp, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            set created = true;
            select created;
		else
            set created = false;
            select created;
	end if;
end$$
delimiter ;
drop procedure if exists loginUser;
delimiter $$
create procedure loginUser(
    in u_nombre_sp nvarchar(255),
    in u_contra_sp nvarchar(255)
)
begin
	declare caselogin int;
	declare id int;
	set id = (select usuariosspaceride.u_id from usuariosspaceride where u_nombre = u_nombre_sp and u_contra = u_contra_sp);
    if id is null
		then
			set id = (select usuariosspaceride.u_id from usuariosspaceride where u_nombre = u_nombre_sp);
            if id is null
				then
					set id = 0;
                    set caselogin = 0;
				else
					set caselogin = 2;
			end if;
		else
			set caselogin = 1;
	end if;
    select id, caselogin;
end$$
delimiter ;
drop procedure if exists readUser;
delimiter $$
create procedure readUser(
    in u_id_sp int
)
begin
	select * from usuariosspaceride where u_id = u_id_sp;
end$$
delimiter ;
drop procedure if exists updateUserData;
delimiter $$
create procedure updateUserData(
	in u_id_sp int,
    in u_nombre_sp nvarchar(255),
    in u_correo_sp nvarchar(255)
)
begin
	declare updated boolean;
	update usuariosspaceride set u_nombre = u_nombre_sp, u_correo = u_correo_sp where u_id= u_id_sp;
	set updated = true;
	select updated;
end$$
delimiter ;
drop procedure if exists updateUserPsd;
delimiter $$
create procedure updateUserPsd(
	in u_id_sp int,
    in u_contra_sp nvarchar(255)
)
begin
	declare updated boolean;
	update usuariosspaceride set u_contra = u_contra_sp where u_id= u_id_sp;
	set updated = true;
	select updated;
end$$
delimiter ;
drop procedure if exists readU_derrotasAndU_victorias;
delimiter $$
create procedure readU_derrotasAndU_victorias(
    in u_id_sp int
)
begin
	select u_derrotas, u_victorias from usuariosspaceride where u_id = u_id_sp;
end$$
delimiter ;
SET GLOBAL max_connections = 500;
SET @@session.block_encryption_mode = 'aes-256-ecb';
drop procedure if exists encryptidSR;
delimiter $$
create procedure encryptidSR(
	in id int
)
begin
	SELECT HEX(AES_ENCRYPT(id, 'spaceride_1327'));
end$$
delimiter ;
drop procedure if exists encryptSR;
delimiter $$
create procedure encryptSR(
	in st nvarchar(255)
)
begin
	SELECT HEX(AES_ENCRYPT(st, 'spaceride_1327'));
end$$
delimiter ;
drop procedure if exists decryptSR;
delimiter $$
create procedure decryptSR(
	in encrypted blob
)
begin
	SELECT AES_DECRYPT(UNHEX(encrypted),'spaceride_1327');
end$$
delimiter ;
drop procedure if exists createPregunta;
delimiter $$
create procedure createPregunta(
	in p_p nvarchar(500),
    in p_r1 nvarchar(350),
    in p_r2 nvarchar(350),
    in p_r3 nvarchar(350), 
	in p_r4 nvarchar(350),
	in p_correcta int,
	in p_dif int,
	in p_area nvarchar(50),
	in p_seleccion boolean
)
begin
	declare created boolean;
	insert into preguntaspaceride values(0, p_p, p_r1, p_r2, p_r3, p_r4, p_correcta, p_dif, p_area, p_seleccion);
    set created = true;
	select created;
end$$
delimiter ;
drop procedure if exists createFaq;
delimiter $$
create procedure createFaq( 
    in faq_pregunta_sp nvarchar(500), 
    in faq_respuesta nvarchar(500),
    in faq_tema nvarchar(20),
    in faq_prioridad int, 
    in faq_aprobacion boolean,
    in faq_usuario int,
    in faq_reviso int
)
begin
	declare created boolean;
    declare exist int;
	set exist = (select count(*) from faqspaceride where faq_pregunta = faq_pregunta_sp);
    if(exist = 0)
		then
			insert into faqspaceride values(0, faq_pregunta_sp, faq_respuesta, faq_tema, faq_prioridad, faq_aprobacion, faq_usuario, faq_reviso);
            set created = true;
            select created;
	end if;
end$$
delimiter ;
drop procedure if exists readFaq;
delimiter $$
create procedure readFaq(
	in faq_id_sp int
)
begin
	select * from faqspaceride where faq_id = faq_id_sp;
end$$
delimiter ;
drop procedure if exists readAllFaq_tema;
delimiter $$
create procedure readAllFaq_tema()
begin
	select faq_tema from faqspaceride;
end$$
delimiter ;
drop procedure if exists readAllFaqwt;
delimiter $$
create procedure readAllFaqwt(
	in faq_tema_sp nvarchar(50)
)
begin
	select * from faqspaceride where faq_tema = faq_tema_sp and faq_aprobacion = true;
end$$
delimiter ;

drop procedure if exists readAllFaq;
delimiter $$
create procedure readAllFaq()
begin
	select * from faqspaceride;
end$$
delimiter ;
drop procedure if exists readAllFaqAdmin;
delimiter $$
create procedure readAllFaqAdmin()
begin
	select * from faqspaceride where faq_aprobacion = false;
end$$
delimiter ;
drop procedure if exists deleteFaq;
delimiter $$
create procedure deleteFaq(
	in faq_id_sp int
)
begin
	declare deleted boolean;
	delete from faqspaceride where faq_id = faq_id_sp limit 1;
    set deleted = true;
	select deleted;
end$$
delimiter ;
drop procedure if exists updateFaq_respuesta;
delimiter $$
create procedure updateFaq_respuesta(
	in faq_id_sp int,
    in faq_respuesta_sp nvarchar(500),
    in faq_a_id_sp int
)
begin
	declare updated boolean;
	update faqspaceride set faq_respuesta = faq_respuesta_sp, faq_aprobacion = true, faq_a_id = faq_a_id_sp where faq_id = faq_id_sp;
    set updated = true;
    select updated;
end$$
delimiter ;
drop procedure if exists createMuerto;
delimiter $$
create procedure createMuerto(
	in m_u_id1_sp int,
    in m_u_id2_sp int,
    in m_area_sp int,
    in m_dif_sp int
)
begin
	declare created boolean;
	insert into muertospaceride values(m_u_id1_sp, m_u_id2_sp, m_area_sp, m_dif_sp, 0);
    set created = true;
	select created;
end$$
delimiter ;
drop procedure if exists updateAndReadPregunta_seleccion;
delimiter $$
create procedure updateAndReadPregunta_seleccion(
	in p_id_sp int
)
begin
	select * from preguntaspaceride where p_id = p_id_sp and p_seleccion = false;
    update preguntaspaceride set p_seleccion = true where p_id = p_id_sp and p_seleccion = false;
end$$
delimiter ;
drop procedure if exists updateAndReadCustomPregunta_seleccion;
delimiter $$
create procedure updateAndReadCustomPregunta_seleccion(
	in p_area_sp nvarchar(50),
    in p_dif_sp int
)
begin
	select * from preguntaspaceride where p_dif = p_dif_sp and p_area = p_area_sp and p_seleccion = false limit 1;
	update preguntaspaceride set p_seleccion = true where p_dif = p_dif_sp and p_area = p_area_sp and p_seleccion = false limit 1;
end$$
delimiter ;
drop procedure if exists readRandomPregunta;
delimiter $$
CREATE PROCEDURE readRandomPregunta(
	in p_area_sp nvarchar(50),
    in p_dif_sp int
)
BEGIN
	select * from preguntaspaceride where p_dif = p_dif_sp and p_seleccion = false and p_area = p_area_sp limit 1;
END $$
delimiter ;
drop procedure if exists updatePregunta_seleccion;
delimiter $$
create procedure updatePregunta_seleccion(
    in p_id_sp int
)
begin
	declare updated boolean;
	update preguntaspaceride set p_seleccion = true where p_id = p_id_sp and p_seleccion = false;
	set updated = true;
    select updated;
end$$
delimiter ;
drop procedure if exists updateResetP_seleccion;
delimiter $$
create procedure updateResetP_seleccion()
begin
	declare updated boolean;
	update preguntaspaceride set p_seleccion = false;
	set updated = true;
    select updated;
end$$
delimiter ;
drop procedure if exists updateU_muerto;
delimiter $$
CREATE PROCEDURE updateU_muerto(
	in u_id_sp int
)
BEGIN
	declare updated boolean;
	UPDATE usuariosspaceride SET u_muerto = true  WHERE u_id = u_id_sp;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists readU_muerto;
delimiter $$
CREATE PROCEDURE readU_muerto()
BEGIN
	select * from usuariosspaceride where u_muerto = true;
END$$
delimiter ;
drop procedure if exists updateMuerto;
delimiter $$
CREATE PROCEDURE updateMuerto(
	in m_u_id1_sp int,
    in m_u_id2_sp int,
    in m_area_sp int,
    in m_dif_sp int,
    in old int
)
BEGIN
	declare updated boolean;
	update muertospaceride set m_u_id1 = m_u_id1_sp, m_u_id2 = m_u_id2_sp, m_area = m_area_sp, m_dif = m_dif_sp where m_u_id1 = old;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateM_areaAndM_dif;
delimiter $$
CREATE PROCEDURE updateM_areaAndM_dif(
	in m_u_id1_sp int,
    in m_area_sp int,
    in m_dif_sp int
)
BEGIN
	declare updated boolean;
	update muertospaceride set m_area = m_area_sp, m_dif = m_dif_sp where m_u_id1 = m_u_id1_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists readMuerto;
delimiter $$
CREATE PROCEDURE readMuerto()
BEGIN
	select * from muertospaceride where m_u_id1 is not null and m_u_id2 is not null;
end$$
delimiter ;
drop procedure if exists updateU_victorias;
delimiter $$
CREATE PROCEDURE updateU_victorias(
	in u_id_sp int
)
BEGIN
	declare updated boolean;
	UPDATE usuariosspaceride SET u_victorias = u_victorias + 1 WHERE u_id = u_id_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateU_derrotas;
delimiter $$
CREATE PROCEDURE updateU_derrotas(
	in u_id_sp int
)
BEGIN
	declare updated boolean;
	UPDATE usuariosspaceride SET u_derrotas = u_derrotas + 1 where u_id = u_id_sp;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateFirstU_derrotas;
delimiter $$
CREATE PROCEDURE updateFirstU_derrotas(
	in area_u_id_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	UPDATE usuariosspaceride SET u_derrotas = u_derrotas + 1  where u_id = area_u_id_sp;
	update areaspaceride set area_max_id = area_max_id_sp , area_max = 1 where area_u_id = area_u_id_sp;
    set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists readArea_max;
delimiter $$
CREATE PROCEDURE readArea_max(
	in area_u_id_sp int
)
BEGIN
	select area_max_id, area_max from areaspaceride where area_u_id = area_u_id_sp;
END$$
delimiter ;
drop procedure if exists updateU_muertoReset;
delimiter $$
CREATE PROCEDURE updateU_muertoReset(
	in u_id1_sp int,
	in u_id2_sp int
)
BEGIN
	declare updated boolean;
	update usuariosspaceride set u_muerto = false Where u_id = u_id1_sp;
	update usuariosspaceride set u_muerto = false Where u_id = u_id2_sp;
	update muertospaceride set m_u_id1 = u_id1_sp, m_u_id2 = u_id2_sp, m_area = 0, m_dif = 0, m_correcta = 0 where m_u_id1 = u_id1_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_mate;
delimiter $$
CREATE PROCEDURE updateArea_mate(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride Set area_mate = area_mate + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_fis;
delimiter $$
CREATE PROCEDURE updateArea_fis(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_fis = area_fis + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_esp;
delimiter $$
CREATE PROCEDURE updateArea_esp(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_esp = area_esp + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_huni;
delimiter $$
CREATE PROCEDURE updateArea_huni(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_huni = area_huni + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_geo;
delimiter $$
CREATE PROCEDURE updateArea_geo(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_geo = area_geo + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_ing;
delimiter $$
CREATE PROCEDURE updateArea_ing(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_ing = area_ing + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_qui;
delimiter $$
CREATE PROCEDURE updateArea_qui(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_qui = area_qui + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_bio;
delimiter $$
CREATE PROCEDURE updateArea_bio(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_bio = area_bio + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END$$
delimiter ;
drop procedure if exists updateArea_hmex;
delimiter $$
CREATE PROCEDURE updateArea_hmex(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_hmex = area_hmex + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateArea_astro;
delimiter $$
CREATE PROCEDURE updateArea_astro(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_astro = area_astro + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateArea_ent;
delimiter $$
CREATE PROCEDURE updateArea_ent(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_ent = area_ent + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateArea_arte;
delimiter $$
CREATE PROCEDURE updateArea_arte(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_arte = area_arte + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateArea_tec;
delimiter $$
CREATE PROCEDURE updateArea_tec(
	in area_u_id_sp int,
    in area_max_sp int,
    in area_max_id_sp int
)
BEGIN
	declare updated boolean;
	update areaspaceride set area_tec = area_tec + 1, area_max = area_max_sp, area_max_id = area_max_id_sp where area_u_id = area_u_id_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists readArea;
delimiter $$
CREATE PROCEDURE readArea(
	in area_u_id_sp int
)
BEGIN
	select * from areaspaceride where area_u_id = area_u_id_sp;
END $$
delimiter ;
drop procedure if exists readM_correcta;
delimiter $$
CREATE PROCEDURE readM_correcta(
	in m_u_id1_sp int
)
BEGIN
	select m_correcta from muertospaceride where m_u_id1 = m_u_id1_sp;
END $$
delimiter ;
drop procedure if exists updateM_correcta;
delimiter $$
CREATE PROCEDURE updateM_correcta(
	in m_u_id1_sp int,
	in m_correcta_sp int
)
BEGIN
	declare updated boolean;
	update muertospaceride set m_correcta = m_correcta_sp where m_u_id1 = m_u_id1_sp;
	set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists readAllChat;
delimiter $$
CREATE PROCEDURE readAllChat(
	in s_id int,
	in u_id int
)
BEGIN
	select * from chatspaceride where chat_s_id = s_id and chat_u_id = u_id;
END $$
delimiter ;
select * from chatspaceride;
drop procedure if exists readSAllChats;
delimiter $$
CREATE PROCEDURE readSAllChats(
	in s_id int
)
BEGIN
	select * from chatspaceride where chat_s_id = s_id;
END $$
delimiter ;
drop procedure if exists createChat;
delimiter $$
CREATE PROCEDURE createChat(
	in s_id int,
    in u_id int
)
BEGIN
	declare created boolean;
    declare exist int;
	set exist = (select count(*) from chatspaceride where chat_s_id = s_id and chat_u_id = u_id);
    if(exist = 0)
		then
			insert into chatspaceride values(0 ,s_id , u_id, '', '', '', current_timestamp());
            set created = true;
		else
			set created = false;
    end if;
    select created;
END $$
delimiter ;
drop procedure if exists updateChat_S_Msgs;
delimiter $$
CREATE PROCEDURE updateChat_S_Msgs(
	in s_id int,
	in u_id int,
	in s_msgs nvarchar(750)
)
BEGIN
	declare updated boolean;
	update chatspaceride set chat_s_msgs = s_msgs where chat_s_id = s_id and chat_u_id = u_id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateChat_U_Msgs;
delimiter $$
CREATE PROCEDURE updateChat_U_Msgs(
	in s_id int,
	in u_id int,
	in u_msgs nvarchar(750)
)
BEGIN
	declare updated boolean;
	update chatspaceride set chat_u_msgs = u_msgs where chat_s_id = s_id and chat_u_id = u_id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists readChatTime;
delimiter $$
CREATE PROCEDURE readChatTime(
	in id int
)
BEGIN
	select chat_time from chatspaceride where chat_id = id;
END $$
delimiter ;
drop procedure if exists updateChatTime;
delimiter $$
CREATE PROCEDURE updateChatTime(
	in id int,
	in time_sp nvarchar(75)
)
BEGIN
	declare updated boolean;
	update chatspaceride set chat_time = time_sp where chat_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists createEventReport;
delimiter $$
CREATE PROCEDURE createEventReport(
	in u_id int,
	in transcript nvarchar(750),
	in description nvarchar(255),
	in category nvarchar(20),
	in s_id int
)
BEGIN
	declare created boolean;
    declare exist int;
	set exist = (select count(*) from eventreportspaceride where er_s_id = s_id and er_u_id = u_id and er_transcript = transcript);
    if(exist = 0)
		then
			insert into eventreportspaceride values(0 ,u_id, transcript, description, category, 'Pendiente', '' , s_id, current_timestamp());
            set created = true;
		else
			set created = false;
    end if;
    select created;
END $$
delimiter ;
drop procedure if exists readERUser;
delimiter $$
CREATE PROCEDURE readERUser(
	in u_id int
)
BEGIN
	select * from eventreportspaceride where er_u_id = u_id;
END $$
delimiter ;
drop procedure if exists readERMaintenance;
delimiter $$
CREATE PROCEDURE readERMaintenance()
BEGIN
	select * from eventreportspaceride where er_status = 'Mantenimiento';
END $$
delimiter ;
drop procedure if exists readEventReport;
delimiter $$
CREATE PROCEDURE readEventReport(
	in id int
)
BEGIN
	select * from eventreportspaceride where er_id = id;
END $$
delimiter ;
drop procedure if exists readERSupport;
delimiter $$
CREATE PROCEDURE readERSupport(
	in s_id int
)
BEGIN
	select * from eventreportspaceride where er_s_id = s_id;
END $$
delimiter ;
drop procedure if exists readAllER;
delimiter $$
CREATE PROCEDURE readAllER()
BEGIN
	select * from eventreportspaceride;
END $$
delimiter ;
drop procedure if exists updateERSupport;
delimiter $$
CREATE PROCEDURE updateERSupport(
	in id int,
    in solution nvarchar(750)
)
BEGIN
	declare updated boolean;
	update eventreportspaceride set er_solution = solution, er_status = 'Revisado' where er_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateERChief;
delimiter $$
CREATE PROCEDURE updateERChief(
	in id int,
	in s_id int
)
BEGIN
	declare updated boolean;
	update eventreportspaceride set er_s_id = s_id, er_status = 'En revisión' where er_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateERMaintenance;
delimiter $$
CREATE PROCEDURE updateERMaintenance(
	in id int
)
BEGIN
	declare updated boolean;
	update eventreportspaceride set er_status = 'Mantenimiento' where er_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateERUser;
delimiter $$
CREATE PROCEDURE updateERUser(
	in id int,
    in status_sp nvarchar(20)
)
BEGIN
	declare updated boolean;
	update eventreportspaceride set er_status = status_sp where er_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists deleteEventReport;
delimiter $$
create procedure deleteEventReport(
	in id int
)
begin
	declare deleted boolean;
	delete from eventreportspaceride where er_id = id limit 1;
    set deleted = true;
	select deleted;
end$$
delimiter ;
drop procedure if exists createMaintenanceReport;
delimiter $$
CREATE PROCEDURE createMaintenanceReport(
	in module_sp nvarchar(750),
	in description_sp nvarchar(255),
	in s_id int
)
BEGIN
	declare created boolean;
    declare exist int;
	set exist = (select count(*) from maintenancereportspaceride where mr_s_id = s_id and mr_description = description_sp and mr_module = module_sp);
    if(exist = 0)
		then
			insert into maintenancereportspaceride values(0 , module_sp, description_sp, 'Pendiente', '' , s_id, current_timestamp());
            set created = true;
		else
			set created = false;
    end if;
    select created;
END $$
delimiter ;
drop procedure if exists readMaintenanceReport;
delimiter $$
CREATE PROCEDURE readMaintenanceReport(
	in id int
)
BEGIN
	select * from maintenancereportspaceride where mr_id = id;
END $$
delimiter ;
drop procedure if exists readDeveloperMR;
delimiter $$
CREATE PROCEDURE readDeveloperMR(
	in s_id int
)
BEGIN
	select * from maintenancereportspaceride where mr_s_id = s_id;
END $$
delimiter ;
drop procedure if exists readAllMR;
delimiter $$
CREATE PROCEDURE readAllMR()
BEGIN
	select * from maintenancereportspaceride;
END $$
delimiter ;
drop procedure if exists readAllMRList;
delimiter $$
CREATE PROCEDURE readAllMRList()
BEGIN
	select * from maintenancereportspaceride where mr_module = 'Soporte';
END $$
delimiter ;
drop procedure if exists updateMRDeveloper;
delimiter $$
CREATE PROCEDURE updateMRDeveloper(
	in id int,
    in solution nvarchar(750)
)
BEGIN
	declare updated boolean;
	update maintenancereportspaceride set mr_solution = solution, mr_status = 'Desarrollado' where mr_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists updateMRChief;
delimiter $$
CREATE PROCEDURE updateMRChief(
	in id int,
	in s_id int
)
BEGIN
	declare updated boolean;
	update maintenancereportspaceride set mr_s_id = s_id, er_status = 'En desarrollo' where mr_id = id;
    set updated = true;
    select updated;
END $$
delimiter ;
drop procedure if exists deleteMaintenanceReport;
delimiter $$
create procedure deleteMaintenanceReport(
	in id int
)
begin
	declare deleted boolean;
	delete from maintenancereportspaceride where mr_id = id limit 1;
    set deleted = true;
	select deleted;
end$$
delimiter ;
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
	declare altas boolean;
	insert into maintenancereportspaceride values(0, modulo, descripcion, stat, solucion, mrsid, fecha);
    set altas = true; 
    select altas;
END $$
delimiter ;
delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RequestTodo`()
BEGIN
	select * from maintenancereportspaceride order by mr_id asc;
END $$
delimiter ;
delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RequestEspecifico`(
	in fol int
)
BEGIN
	select * from maintenancereportspaceride where mr_id = fol;
END $$
delimiter ;
delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RequestDesarrollador`(
	in idres int
)
BEGIN
	select * from maintenancereportspaceride where mr_s_id = idres;
END $$
delimiter ;
delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Actualizar`(
	in fecha timestamp, 
    in descripcion nvarchar(1000),
    in solucion nvarchar(1000),
    in stat nvarchar(100),
    in fol int
)
BEGIN
declare altas boolean;
update maintenancereportspaceride set mr_timestamp = fecha, mr_description = descripcion, mr_solution = solucion,mr_status = stat where mr_id = fol limit 1;
set altas = true; 
select altas;
END $$
delimiter ;
delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Borrar`(
	in fol int
)
BEGIN
	declare borrar boolean;
	delete from maintenancereportspaceride where mr_id = fol limit 1;
    set borrar = true; 
    select borrar;
END $$
delimiter ;
