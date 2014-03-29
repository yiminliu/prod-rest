DROP TABLE IF EXISTS prod_note CASCADE;
CREATE TABLE prod_note
(
   note_id                  numeric(15),
   type                     varchar(10),
   note                     varchar(120),
   --po_note                   varchar(120),
   --buyer_note                varchar(120),
   --invoice_note              varchar(120),
   --intranet_note             varchar(120),
   product_id                varchar(20),
   PRIMARY KEY (note_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
COMMIT;

INSERT INTO prod_note
(
   product_id,
   po_note,
   buyer_note, 
   invoice_note               
)
SELECT 
   itemcd,
   po_notes,
   notes1,  --notes2,
   notes3
   
FROM ims;   

COMMIT;   