DROP TABLE IF EXISTS prod_material_style CASCADE;
CREATE TABLE prod_material_style
(
   description                varchar(50)       PRIMARY KEY
);
   

INSERT INTO prod_material_style VALUES
      ('Beak'),
      ('Cove Base'),
      ('Cove Base Inside Corner'),
      ('Cove Base Outside Corner'),
      ('Cane'),
      ('Chair Rail'),
      ('Floor'),
      ('Floor/Wall'),
      ('Quarter Round'),
      ('Surface'),
      ('Surface Corner'),
      ('Surface Corner Right'),
      ('Surface Corner Left'),
      ('V-Cap'),
      ('V-Cap Corner'),
      ('Wall');

  COMMIT;       
             