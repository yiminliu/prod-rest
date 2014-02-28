DROP TABLE IF EXISTS prod_note CASCADE;
CREATE TABLE prod_note
(
   product_id                varchar(20),
   buyer_id                  numeric(10),
   first_name                varchar(15),
   last_name                 varchar(15),
   role                      varchar(10)
   PRIMARY KEY (product_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
COMMIT;

INSERT INTO prod_note
(
   product_id,
   po_note,
   vendor_note,
   invoice_note,
   buyer_note             
)
SELECT 
   itemcd,
   po_notes,
   notes1,
   notes2,
   notes3
FROM ims;   

COMMIT;   