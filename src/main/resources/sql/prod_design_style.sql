DROP TABLE IF EXISTS prod_design_style CASCADE;
CREATE TABLE prod_design_style
(
   description                varchar(30)      PRIMARY KEY
);
   

INSERT INTO prod_design_style VALUES
      ('Contemporary'),
      ('Modern'),
      ('Eclectic'),
      ('Traditional'),
      ('Asin'),
      ('Beach Style'),
      ('Craftsman'),
      ('Industrial'),
      ('Mediterranean'),
      ('Midcentury'),
      ('Rustic'),
      ('Tropical');
     
 COMMIT;     