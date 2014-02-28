DROP TABLE IF EXISTS prod_surface_application CASCADE;
CREATE TABLE prod_surface_application
(
   description                varchar(30)        PRIMARY KEY
);
   

INSERT INTO prod_surface_application VALUES
      ('Ink Jet'),
      ('Roto'),
      ('Silk'),
      ('Hand Painted/Crafted');
      
  COMMIT;  