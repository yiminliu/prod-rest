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
