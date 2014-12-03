DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
  user_id       SERIAL PRIMARY KEY, 
  username      varchar(50) NOT NULL,
  password      varchar(20) NOT NULL,
  email         varchar(50),
  enabled       boolean DEFAULT false,
  dateEnabled   date,
  dateDisabled  date 
);

CREATE INDEX users_username_idx ON users USING btree (username);

--INSERT INTO users(username, password, email, enabled)
--SELECT usercd, passwd, email, 
--       case when active = 'Y' then true
--            when active != 'Y' then false end
--FROM uc

--WHERE username IS NOT NULL AND username != '' AND (active = 'Y' OR active = 'P');

COMMIT;   

insert into users (user_id,username, password, email, enabled)

 values(1, 'ims', 'password', 'test@test.com', true);
 
 values(2, 'keymark', 'password', 'test@test.com', true);

insert into users (user_id,username, password, email, enabled)
 values(3, 'test', 'password', 'test@test.com', true);
 
insert into users (user_id,username, password, email, enabled)
 values(4, 'guest', '', 'test@test.com', true);
 
 commit;
 
