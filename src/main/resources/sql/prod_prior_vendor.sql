DROP TABLE IF EXISTS prod_prior_vendor CASCADE;
CREATE TABLE prod_prior_vendor
(
   vendor_id               numeric(10),
   product_id              varchar(20),    
   price_unit              varchar(4),
   cost                    numeric(9,4), 
   freight_rate_cwt        numeric(9,4),
   PRIMARY KEY (vendor_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

INSERT INTO prod_prior_vendor
(
   product_id,
   price_unit,
   cost,
   freight_rate_cwt
)
SELECT
   itemcd,
   priorvendorpriceunit,
   priorvendorlistprice,
   priorvendorfreightratecwt,
FROM ims;

COMMIT;   