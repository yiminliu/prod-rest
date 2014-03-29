DROP TABLE IF EXISTS prod_surface_style CASCADE;
CREATE TABLE prod_surface_style
(
   description                varchar(30)        PRIMARY KEY
);
   

INSERT INTO prod_surface_style VALUES
      (1, 'Abrasive'),
      (2, 'Flamed'),
      (3, 'Glazed'),
      (4, 'Polished'),
      (5, 'Resined'),
      (6, 'Unglazed'),
      (7, 'Vein-Cut'),
      (8, 'Cross Cut');
    
  COMMIT;  
      
      