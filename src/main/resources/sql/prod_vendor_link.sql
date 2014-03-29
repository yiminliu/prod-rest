DROP TABLE IF EXISTS prod_vendor_link CASCADE;
CREATE TABLE prod_vendor_link
(
   product_id              varchar(20), 
   vendor_id               numeric(10),
   xref_id                 varchar(60),
   price_unit              varchar(4),
   fob                     char(10),
   cost                    numeric(9,4), 
   discount_pct            numeric(5,2),
   price_round_accuracy    numeric(1),
   net_price               numeric(9,4),
   lead_time               numeric(4),
   freight_rate_cwt        numeric(9,4),
   duty_pct                numeric(7,4)     DEFAULT 0,

   PRIMARY KEY (product_id, vendor_id),
   
   CONSTRAINT vendor_link_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,   
      
   CONSTRAINT vendor_fkey FOREIGN KEY (vendor_id)
      REFERENCES prod_vendor (vendor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION   
);

INSERT INTO prod_vendor_link
(
   product_id, 
   vendor_id,
   xref_id,
   price_unit,
   fob,
   cost,
   discount_pct,
   price_round_accuracy,
   net_price,
   lead_time,
   freight_rate_cwt,
   duty_pct
)
SELECT
   itemcd,
   vendornbr1,
   vendorxrefcd,
   vendorpriceunit,
   vendorfob,
   vendorlistprice,
   vendordiscpct1,
   sellpriceroundaccuracy,
   vendornetprice,
   leadtime,
   dutypct,
   vendorfreightratecwt
FROM ims
WHERE vendornbr1 is not null AND vendornbr1 > 0;

COMMIT; 
