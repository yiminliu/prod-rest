DROP TABLE IF EXISTS prod_note CASCADE;
CREATE TABLE prod_note
(
   note_id                  SERIAL PRIMARY KEY,
   type                     varchar(20),
   note                     varchar(120),
   product_id               varchar(20),
   --PRIMARY KEY (note_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
COMMIT;

INSERT INTO prod_note
(
   product_id,
   type,
   note               
)
SELECT 
    itemcd,
   'po_notes',
    po_notes
FROM ims
WHERE po_notes is not null AND po_notes <> '';

INSERT INTO prod_note
(
   product_id,
   type,
   note               
)
SELECT 
   itemcd,
   'buyer_notes',
   notes1 || '. ' || notes2
FROM ims
WHERE notes1 is not null AND notes1 <> '';

INSERT INTO prod_note
(
   product_id,
   type,
   note               
)
SELECT 
   itemcd,
   'invoice_note',
   notes3
FROM ims
WHERE notes3 is not null AND notes3 <> '';

COMMIT;   
