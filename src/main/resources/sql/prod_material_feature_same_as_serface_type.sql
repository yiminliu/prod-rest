DROP TABLE IF EXISTS prod_material_feature CASCADE;
CREATE TABLE prod_material_feature
(
   feature_id                      numeric(15),
   name                            numeric(10),
   description                     varchar(30),
   product_id                      varchar(20),
   
   PRIMARY KEY (feature_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
   

INSERT INTO prod_material_feature
(
   feature_id,
   name,
   description,
   product_id
 )
 SELECT
   "feature1",
   feature1,
   itemcd   
FROM ims
WHERE feature1 is not null; 

INSERT INTO prod_material_feature
(
   feature_id,
   name,
   description,
   product_id
 )
 SELECT
   "feature2",
   feature2,
   itemcd   
FROM ims
WHERE feature2 is not null; 

INSERT INTO prod_material_feature
(
   feature_id,
   name,
   description,
   product_id
 )
 SELECT
   "feature3",
   feature3,
   itemcd   
FROM ims
WHERE feature3 is not null; 

COMMIT;