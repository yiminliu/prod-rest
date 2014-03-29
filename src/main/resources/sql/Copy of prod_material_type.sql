DROP TABLE IF EXISTS prod_material_type CASCADE;
CREATE TABLE prod_material_type
(
   id                         numeric(10)         PRIMARY KEY   NOT NULL,
   description                varchar(30)
);
   

INSERT INTO prod_material_type VALUES
      (1, 'Brick'),
      (2, 'Engineered Quartz'),
      (3, 'Fracture'),
      (4, 'Ceramic'),
      (5, 'Onyx'),
      (6, 'Saltillo'),
      (7, 'Travertine'),
      (8, 'Sandstone'),
      (9, 'Glass'),
      (10, 'Porcelain'),
      (11, 'Decorative'),
      (12, 'Sealer'),
      (13, 'Slate'),
      (14, 'Marble'),
      (15, 'Quartzite'),
      (16, 'Limestone'),
      (17, 'Other'),
      (18, 'Marketing'),
      (19, 'Granite'),
      (20, 'Basalts'),
      (21, 'Schist'),
      (22, 'Quarry'),
      (23, 'Trowel'),
      (24, 'Resin/Metal');
                   
  COMMIT;       
             