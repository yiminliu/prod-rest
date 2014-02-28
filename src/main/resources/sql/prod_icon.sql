DROP TABLE IF EXISTS prod_icon CASCADE;
CREATE TABLE prod_icon
(   
   description                varchar(30)  PRIMARY KEY
);
   

INSERT INTO prod_icon VALUES
      ('Post Recycled'),
      ('Pre Recycled'),
      ('Exterior Product'),
      ('ADA Accessibility'),
      ('Pos Consumer'),
      ('Pre Consumer'),
      ('Green Friendly'),
      ('Lead Point');
      
  COMMIT;  