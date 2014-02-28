DROP TABLE IF EXISTS prod_surface_type CASCADE;
CREATE TABLE prod_surface_type
(
   description                varchar(30)        PRIMARY KEY
);
   

INSERT INTO prod_surface_type VALUES
      ('Abrasive'),
      ('Flamed'),
      ('Glazed'),
      ('Polished'),
      ('Resined'),
      ('Unglazed'),
      ('Vein-Cut'),
      ('Cross Cut');
    
  COMMIT;  
      
      