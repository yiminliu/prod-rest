DROP TABLE IF EXISTS prod_design_style CASCADE;
CREATE TABLE prod_design_style
(
   id                         numeric(10)         PRIMARY KEY   NOT NULL,
   description                varchar(30)
);
   

INSERT INTO prod_design_style VALUES
      (1, 'Antiquated'),
      (2, 'Filled & Honed'),
      (3, 'Fully Gauged'),
      (4, 'Leathered'),
      (5, 'Honed'),
      (6, 'Metallic'),
      (7, 'Nat. Clef'),
      (8, 'Polished & Honed'),
      (5, 'Stain'),
      (6, 'Tumbled'),
      (7, 'Filled & Honed'),
      (8, 'Textured');

  COMMIT;  