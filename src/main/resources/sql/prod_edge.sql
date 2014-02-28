DROP TABLE IF EXISTS prod_edge CASCADE;
CREATE TABLE prod_edge
(   
   description                varchar(30)  PRIMARY KEY
);
   

INSERT INTO prod_edge VALUES
      ('Rectified'),
      ('Squared'),
      ('Chiseled'),
      ('Antiquated'),
      ('Tapered'),
      ('Tumbled'),
      ('Beveled'),
      ('Cushioned'),
      ('Pillowed'),
      ('Staggard');
     
  COMMIT;  