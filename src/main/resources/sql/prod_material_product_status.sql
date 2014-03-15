DROP TABLE IF EXISTS prod_material_product_status CASCADE;
CREATE TABLE prod_material_product_status
(
   material_product_status              varchar(30)     PRIMARY KEY
);
   

INSERT INTO prod_material_product_status(material_product_status) VALUES
      ('New Product'),
      ('Active Product'),
      ('Pre Drop'),
      ('Drop'),
      ('Obsolete'),
      ('Do Not Inventory'),
      ('Seconde/Promo'),
      ('Claims Inventory'),
      ('Direct Ship'),
      ('Special Order');
      
   COMMIT;  