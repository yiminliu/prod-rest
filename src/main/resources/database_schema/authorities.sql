DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities
(
	user_id       integer, 
	role          varchar(20),
    
    PRIMARY KEY(user_id, role),
    
    CONSTRAINT users_fkey FOREIGN KEY (user_id)
      REFERENCES users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION 
 
);

insert into authorities values(1, 'ROLE_ADMIN');
 
insert into authorities values(2, 'ROLE_ADMIN');

insert into authorities values(3, 'ROLE_USER');

insert into authorities values(4, 'ROLE_ANONYMOUS');
 

COMMIT;   