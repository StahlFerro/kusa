create table keycard (id varchar(36) not null, access_level int4 not null, name varchar(255), primary key (id))
create table user_base (id varchar(36) not null, email varchar(255), login_name varchar(255) not null, name varchar(255), password_hash varchar(255), primary key (id))
alter table if exists user_base drop constraint if exists UK_1jfwwo2w0q4gkinc2w26nd6h9
alter table if exists user_base add constraint UK_1jfwwo2w0q4gkinc2w26nd6h9 unique (login_name)
