DROP TABLE IF EXISTS prod_design_look CASCADE;
CREATE TABLE prod_design_look
(
   description                varchar(30)      PRIMARY KEY
);
   

INSERT INTO prod_design_look VALUES
      ('Wood'),
      ('Travertine');
      
COMMIT;