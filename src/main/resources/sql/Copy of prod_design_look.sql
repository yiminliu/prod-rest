DROP TABLE IF EXISTS prod_design_look CASCADE;
CREATE TABLE prod_design_look
(
   id                         numeric(10)         PRIMARY KEY   NOT NULL,
   description                varchar(30)
);
   

INSERT INTO prod_design_look VALUES
      (1, 'Wood'),
      (2, 'Travertine');
      
COMMIT;