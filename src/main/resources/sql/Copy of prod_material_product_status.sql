DROP TABLE IF EXISTS prod_material_product_status CASCADE;
CREATE TABLE prod_material_product_status
(
   mps_code                        varchar(10)         PRIMARY KEY   NOT NULL,
   description                     varchar(30)
);
   

INSERT INTO prod_material_product_status(mps_code, description) VALUES
      ('01', 'New Product'),
      ('02', 'Active Product'),
      ('03', 'Pre Drop'),
      ('04', 'Drop'),
      ('05', 'Obsolete'),
      ('06', 'Do Not Inventory'),
      ('07', 'Seconde/Promo'),
      ('08', 'Claims Inventory'),
      ('09', 'Direct Ship'),
      ('10', 'Special Order');
      
   COMMIT;  