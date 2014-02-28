DROP TABLE IF EXISTS prod_surface_finish CASCADE;
CREATE TABLE prod_surface_finish
(
   description                varchar(30)    PRIMARY KEY
);
   

INSERT INTO prod_surface_finish VALUES
      ('Antiquated'),
      ('Filled & Honed'),
      ('Fully Gauged'),
      ('Leathered'),
      ('Honed'),
      ('Metallic'),
      ('Nat. Clef'),
      ('Polished & Honed'),
      ('Stain'),
      ('Tumbled'),
      ('Textured');

  COMMIT;  