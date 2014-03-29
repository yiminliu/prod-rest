DROP TABLE IF EXISTS prod_material_class CASCADE;
CREATE TABLE prod_material_class
(
   id                         numeric(10)         PRIMARY KEY   NOT NULL,
   description                varchar(30)
);
   

INSERT INTO prod_material_class VALUES
      (1, 'STNTL'),
      (2, 'CTSRC'),
      (3, 'SLABS'),
      (4, 'SAMPL'),
      (5, 'FRT'),
      (6, 'DECOR'),
      (7, 'OTHER'),
      (8, 'CTMNF'),
      (9, 'ALLIE');     
 COMMIT;  
 
 



  



