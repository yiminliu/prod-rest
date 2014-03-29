DROP TABLE IF EXISTS prod_design_style CASCADE;
CREATE TABLE prod_design_style
(
   id                         numeric(10)         PRIMARY KEY   NOT NULL,
   description                varchar(30)
);
   

INSERT INTO prod_design_style VALUES
      (1, 'Contemporary'),
      (2, 'Modern'),
      (3, 'Eclectic'),
      (4, 'Traditional'),
      (5, 'Asin'),
      (6, 'Beach Style'),
      (7, 'Craftsman'),
      (8, 'Industrial'),
      (9, 'Mediterranean'),
      (10, 'Midcentury'),
      (11, 'Rustic'),
      (12, 'Tropical');
     
 COMMIT;     