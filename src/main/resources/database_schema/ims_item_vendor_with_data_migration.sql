DROP TABLE IF EXISTS ims_item_vendor;
CREATE TABLE ims_item_vendor
(
   vendor_id                      numeric(6),
   vendor_name                    varchar(60),
   vendor_name2                   varchar(60),
   item_code                      char(18), 
   vendor_order                   numeric(2),
   vendor_xref_id                 varchar(60),
   vendor_list_price              numeric(9,4), 
   vendor_price_unit              varchar(4),
   vendor_fob                     varchar(10),
   vendor_discount_pct            numeric(5,2),
   vendor_round_accuracy          numeric(1),
   vendor_net_price               numeric(9,4),
   vendor_markup_pct              numeric(4),
   lead_time                      numeric(4),
   vendor_freight_rate_cwt        numeric(9,4),
   vendor_landed_base_cost        numeric(9,4),
   duty_pct                       numeric(7,4)     DEFAULT 0,
   version                        numeric(15),
   
   PRIMARY KEY (vendor_id, item_code), 
   
   CONSTRAINT item_vendor_ims_fkey FOREIGN KEY (item_code)
      REFERENCES ims (itemcd) MATCH SIMPLE
      ON DELETE CASCADE,
      
   CONSTRAINT vendor_apv_fkey FOREIGN KEY (vendor_id)
      REFERENCES apv (vendornbr) MATCH SIMPLE
   
);

CREATE INDEX ims_item_vendor_itemcd_idx ON ims_item_vendor USING btree (item_code);

COMMIT;


INSERT INTO ims_item_vendor (vendor_id, vendor_name, vendor_order, item_code, vendor_xref_id, vendor_list_price, 
                        vendor_price_unit, vendor_fob, vendor_discount_pct, vendor_round_accuracy, 
                        vendor_net_price, vendor_markup_pct, lead_time, vendor_freight_rate_cwt,  vendor_landed_base_cost,  duty_pct)
SELECT DISTINCT vendornbr1, name, 1, itemcd, vendorxrefcd, vendorlistprice, vendorpriceunit, vendorfob, 
                vendordiscpct1, vendorroundaccuracy, vendornetprice, 
                ims.vendormarkuppct, leadtime, ims.vendorfreightratecwt,  vendorlandedbasecost,  dutypct   
FROM ims, apv
WHERE itemcd IS NOT NULL AND itemcd <> '' AND vendornbr1 IS NOT NULL AND vendornbr1 > 0 AND vendornbr1 = apv.vendornbr;

INSERT INTO ims_item_vendor (vendor_id, vendor_order, item_code, vendor_xref_id, vendor_list_price, 
                        vendor_price_unit, vendor_fob, vendor_discount_pct, vendor_round_accuracy, 
                        vendor_net_price, vendor_markup_pct, lead_time, vendor_freight_rate_cwt,  vendor_landed_base_cost,  duty_pct)
SELECT DISTINCT vendornbr1, 1, itemcd, vendorxrefcd, vendorlistprice, vendorpriceunit, vendorfob, 
                vendordiscpct1, vendorroundaccuracy, vendornetprice, 
                ims.vendormarkuppct, leadtime, ims.vendorfreightratecwt,  vendorlandedbasecost,  dutypct   
FROM ims
WHERE itemcd IS NOT NULL AND itemcd <> '' AND vendornbr1 IS NOT NULL AND vendornbr1 > 0 AND vendornbr1 NOT IN (SELECT vendor_id from ims_item_vendor); 

INSERT INTO ims_item_vendor (vendor_id, vendor_order, item_code)
SELECT DISTINCT vendornbr2, 2, itemcd
FROM ims
WHERE itemcd IS NOT NULL AND itemcd <> '' AND vendornbr2 IS NOT NULL AND vendornbr2 > 0 AND itemcd not like 'MARREALETUM12%' AND vendornbr2 <> 468120;

INSERT INTO ims_item_vendor (vendor_id, vendor_order, item_code)
SELECT DISTINCT vendornbr3, 3, itemcd
FROM ims
WHERE itemcd IS NOT NULL AND itemcd <> '' AND vendornbr3 IS NOT NULL AND vendornbr3 > 0;

COMMIT;
