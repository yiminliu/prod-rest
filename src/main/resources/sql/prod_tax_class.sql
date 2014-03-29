DROP TABLE IF EXISTS prod_tax_class CASCADE;
CREATE TABLE prod_tax_class
(   
   abbrivation        varchar(1)  PRIMARY KEY,
   description        varchar(15)
);
   

INSERT INTO prod_tax_class VALUES
      ('T', 'Tax'),
      ('N", ''Non-Tax'),
      ('E', 'Exempt');
     
  COMMIT;  