DROP TABLE IF EXISTS ims_note;
CREATE TABLE ims_note
(
   note_id                  SERIAL PRIMARY KEY,
   note_type                varchar(20),
   text                     varchar(120),
   item_code                char(18), 
   created_date             date,
   last_modified_date       date,
   version                  numeric(10),
   
   CONSTRAINT note_ims_fkey FOREIGN KEY (item_code)
      REFERENCES ims (itemcd) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE    
);


INSERT INTO ims_note
(
   item_code,
   note_type,
   text               
)
SELECT 
    itemcd,
   'po',
    po_notes
FROM ims
WHERE po_notes IS NOT NULL AND po_notes <> '';

INSERT INTO ims_note
(
   item_code,
   note_type,
   text               
)
SELECT 
   itemcd,
   'buyer',
    notes1
FROM ims
WHERE notes1 IS NOT NULL AND notes1 <> '';

INSERT INTO ims_note
(
   item_code,
   note_type,
   text               
)
SELECT 
   itemcd,
   'additional',
    notes2
FROM ims
WHERE notes2 IS NOT NULL AND notes2 <> '';

INSERT INTO ims_note
(
   item_code,
   note_type,
   text               
)
SELECT 
   itemcd,
   'invoice',
   notes3
FROM ims
WHERE notes3 IS NOT NULL AND notes3 <> '';

COMMIT;   
