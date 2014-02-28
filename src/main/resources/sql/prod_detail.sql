DROP TABLE IF EXISTS prod_detail CASCADE;
CREATE TABLE prod_detail
(
   product_id                      varchar(30),
   product_manager                 varchar(25),
   buyer                           varchar(25), 
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost               numeric(9,4),
   prior_vendor_freight_rate_cwt   numeric(9,4),
   off_shade                       char(1)          DEFAULT 'N',
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
   created_date                    date,
   last_modified_date              date,
    
   PRIMARY KEY (product_id),
   
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION 
     
);   

INSERT INTO prod_detail
(
   product_id,
   product_manager,
   buyer,
   prior_vendor_price_unit,
   prior_vendor_cost,
   prior_vendor_freight_rate_cwt,
   off_shade,
   show_on_website,
   show_on_alysedwards
 )
 SELECT
   itemcd,
   purchaser,
   purchaser2,
   priorvendorpriceunit,
   priorvendorlistprice,
   priorvendorfreightratecwt,
   offshade,
   showonwebsite,
   showonalysedwards

FROM ims; 


DROP TABLE IF EXISTS prod_detail CASCADE;
CREATE TABLE prod_detail
(
   product_id                      varchar(30),
   product_manager                 varchar(25),
   buyer                           varchar(25), 
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost               numeric(9,4),
   prior_vendor_freight_rate_cwt   numeric(9,4),
   off_shade                       char(1)          DEFAULT 'N',
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
   created_date                    date,
   last_modified_date              date,
    
   PRIMARY KEY (product_id),
   
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION 
     
);   

INSERT INTO prod_detail
(
   product_id,
   product_manager,
   buyer,
   prior_vendor_price_unit,
   prior_vendor_cost,
   prior_vendor_freight_rate_cwt,
   off_shade,
   show_on_website,
   show_on_alysedwards
 )
 SELECT
   itemcd,
   purchaser,
   purchaser2,
   priorvendorpriceunit,
   priorvendorlistprice,
   priorvendorfreightratecwt,
   offshade,
   showonwebsite,
   showonalysedwards

FROM ims; 

   
COMMIT;