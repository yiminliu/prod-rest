DROP TABLE IF EXISTS prod_material_type CASCADE;
CREATE TABLE prod_material_type
(
   description                varchar(30)       PRIMARY KEY
);
   

INSERT INTO prod_material_type VALUES
      ('Brick'),
      ('Ceramic'),
      ('Decorative'),
      ('Engineered Quartz'),
      ('Glass'),
      ('Glass Block'),
      ('Other'),
      ('Porcelain'),
      ('Pebble Rock'),
      ('Quarry'),
      ('Resin/Metal');
       
      --('Fracture'),
      --('Onyx'),
      --('Saltillo'),
      --('Travertine'),
      --('Sandstone'),
      --('Sealer'),
      --('Slate'),
      --('Marble'),
      --('Quartzite'),
      --('Limestone'),
      --('Marketing'),
      --('Granite'),
      --('Basalts'),
      --('Schist'),
      --('Trowel'),
     
                   
  COMMIT;       
             