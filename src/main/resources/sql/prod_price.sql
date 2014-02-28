DROP TABLE IF EXISTS prod_price CASCADE;
CREATE TABLE prod_price
(
   price_id                        SERIAL PRIMARY KEY,
   type                            varchar(10), 
   mbrp                            numeric(9,4),
   date_from                       date,
   date_thru                       date,   
   product_id                      varchar(20),   
   --PRIMARY KEY (price_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);


INSERT INTO prod_price
(
   type,
   mbrp,
   date_from,
   date_thru,
   product_id
)
SELECT
   'temp',
   tempprice,
   tempdatefrom,
   tempdatethru,
   itemcd
FROM ims
WHERE tempprice > 0;

INSERT INTO prod_price
(
   type,
   mbrp,
   product_id
)
SELECT
   'future',
   futuresell,
   itemcd
FROM ims
WHERE futuresell > 0;

COMMIT;   
