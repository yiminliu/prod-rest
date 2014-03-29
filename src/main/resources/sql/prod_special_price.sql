DROP TABLE IF EXISTS prod_special_price CASCADE;
CREATE TABLE prod_special_price
(
   price_id                        SERIAL PRIMARY KEY,
   price                           numeric(9,4),
   date_from                       date,
   date_thru                       date,   
   product_id                      varchar(20),   
   CONSTRAINT price_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);


INSERT INTO prod_special_price
(
   price,
   date_from,
   date_thru,
   product_id
)
SELECT
   tempprice,
   tempdatefrom,
   tempdatethru,
   itemcd
FROM ims
WHERE tempprice > 0;

COMMIT;   
