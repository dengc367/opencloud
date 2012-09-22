create table user(
    id INT(11) NOT NULL AUTO_INCREMENT,
    uid INT(11) NOT NULL, 
    uname varchar(32) NOT NULL,
    password varchar(32) NOT NULL,
    
    email varchar(255) NOT NULL,
    mobile_phone varchar(15) NOT NULL,
    code varchar(5) NOT NULL  DEFAULT 0,
    telephone varchar(15) NOT NULL  DEFAULT 0,
    gender enum('f','m') NOT NUll,

    create_time datetime not null,
    primary key (id),
    unique key uniq_email (email),
    unique key uniq_mobile_phone (mobile_phone),
    unique key uniq_code_telephone (code,telephone)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
    --hash int() NOT NULL  DEFAULT 0,
create table app(
    id INT(11) NOT NULL AUTO_INCREMENT,
    app_key varchar(215) NOT NULL, 
    name varchar(20) NOT NULL,
    uid INT(11) NOT NULL, 
    run_env enum('java','php', 'python', 'c', 'c++') NOT NUll,
    create_time datetime not null,
    options varchar(255) not null,
    primary key (id),
    unique key uniq_app_key (app_key)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table cluster(
    id int(11) not null auto_increment,
    name varchar(32) not null default '',
    node_number int(4) not null default 0,
    info varchar(255) not null default '',
    primary key(id)
);

create table node(
    id int(11) not null auto_increment,
    cluster_id int(11) not null,
    name varchar(32) not null default '',
    ip varchar(32) not null default '',
    service_number tinyint(4) not null default 0,
    primary key( id ),
    unique key uniq_cluster_ip(cluster_id, ip)
);

--
-- 需不需要 user_cluster 或者 user_node,好像如果是虚拟机的话，就不需要吧。
-- 

create table service(
    id INT(11) NOT NULL AUTO_INCREMENT,
    cluster_id INT(11) NOT NULL,
    port int(11) NOT NUll,
    type enum('memcache','mysql') NOT NUll,
    permission set('r','w', 'rw') NOT NUll,
    primary key (id),
    unique key uniq_type_port (type, port)
)ENGINE=InnoDB AUTO_INCREMENT=10645008 DEFAULT CHARSET=utf8 COLLATE=utf8_bin

create table app_service(
    id int(11) not null auto_increment,
    app_id int(11) not null,
    service_id int(11) not null,
    primary key(id),
    unique key uniq_app_service( app_id, service_id )
);






