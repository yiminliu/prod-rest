DROP TABLE IF EXISTS prod_vendor CASCADE;
CREATE TABLE prod_vendor
(
   vendor_id               numeric(10),
   name                    varchar(60),
   name2                   varchar(60),
  
   PRIMARY KEY (vendor_id)
);


INSERT INTO prod_vendor (vendor_id)
SELECT DISTINCT vendornbr1   
FROM ims
WHERE vendornbr1 > 0;

--INSERT INTO prod_vendor (vendor_id)
--SELECT DISTINCT vendornbr2   
--FROM ims
--WHERE vendornbr2 > 0 and vendornbr2 not in (select vendor_id from prod_vendor); --<> vendornbr1;


--INSERT INTO prod_vendor (vendor_id)
--SELECT DISTINCT vendornbr3   
--FROM ims
--WHERE vendornbr3 > 0 and vendornbr3 not in (select vendor_id from prod_vendor);

--COMMIT;   