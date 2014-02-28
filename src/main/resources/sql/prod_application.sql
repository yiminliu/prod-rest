DROP TABLE IF EXISTS prod_application CASCADE;
CREATE TABLE prod_application
(
   residential                     varchar(20),
   light_commercial                 varchar(20),
   commercial                      varchar(20),
   product_id                      varchar(20),
   PRIMARY KEY (product_id),
   CONSTRAINT application_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
   

INSERT INTO prod_application
(
   residential,
   light_commercial,
   commercial,
   product_id
 )
 SELECT
   residential,
   lightcommercial,
   commercial,
   itemcd   
FROM ims
WHERE (residential IS NOT NULL AND residential <> '') OR
       (lightcommercial IS NOT NULL AND lightcommercial <> '') OR
       (commercial IS NOT NULL AND commercial <> '')
