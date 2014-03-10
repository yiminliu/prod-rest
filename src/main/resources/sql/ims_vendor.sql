DROP TABLE IF EXISTS ims_vendor CASCADE;
CREATE TABLE ims_vendor
(
   vendor_id               numeric(10),
   name                    varchar(60),
   name2                   varchar(60),
  
   PRIMARY KEY (vendor_id)
);


INSERT INTO ims_vendor (vendor_id)
SELECT DISTINCT vendornbr1   
FROM ims
WHERE vendornbr1 > 0;


COMMIT;   