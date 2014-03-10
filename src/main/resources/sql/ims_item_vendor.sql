DROP TABLE IF EXISTS ims_item_vendor CASCADE;
CREATE TABLE ims_item_vendor
(
   itemcd              varchar(20), 
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

   PRIMARY KEY (itemcd, vendor_id),
   
   CONSTRAINT ims_item_vendor_fkey FOREIGN KEY (itemcd)
      REFERENCES ims (itemcd) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,   
      
   CONSTRAINT vendor_fkey FOREIGN KEY (vendor_id)
      REFERENCES ims_vendor (vendor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION   
);

COMMIT; 
