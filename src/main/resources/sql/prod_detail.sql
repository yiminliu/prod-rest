DROP TABLE IF EXISTS prod_detail CASCADE;
CREATE TABLE prod_detail
(
   product_id                      varchar(30),
   type_code                       varchar(10), --char(1),
   lot_type                        char(1),
   update_code                     char(10),
   product_manager                 varchar(25),
   buyer                           varchar(25), 
   tax_class                       varchar(1),  --char(1),
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost               numeric(9,4),
   prior_vendor_freight_rate_cwt   numeric(9,4),
   off_shade                       char(1)          DEFAULT 'N',
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
   created_date                    date,
   launched_date                   date,
   droped_date                     date,
   last_modified_date              date,
    
   PRIMARY KEY (product_id),
   
   CONSTRAINT prod_detail_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT tax_class_fkey FOREIGN KEY (tax_class)
     REFERENCES prod_tax_class (tax_class) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION  
);   

INSERT INTO prod_detail
(
   product_id,
   type_code,
   lot_type,
   update_code,
   product_manager,
   buyer,
   prior_vendor_price_unit,
   prior_vendor_cost,
   prior_vendor_freight_rate_cwt,
   off_shade,
   show_on_website,
   show_on_alysedwards,
   droped_date
 )
 SELECT
   itemcd,
   itemtypecd,
   lottype,
   updatecd,
   purchaser,
   purchaser2,
   priorvendorpriceunit,
   priorvendorlistprice,
   priorvendorfreightratecwt,
   offshade,
   showonwebsite,
   showonalysedwards,
   dropdate

FROM ims; 

COMMIT;