
DROP TABLE IF EXISTS prod_unit CASCADE;
CREATE TABLE prod_unit
(
   unit_id             SERIAL PRIMARY KEY,
   product_id          varchar(20),
   name                varchar(10),
   unit                varchar(4),
   unit_ratio          numeric(9,4),
   is_std_sell         char(1),
   is_std_order        char(1),
   is_fract_qty        char(1),
   is_pack_unit        char(1),
   upc                 numeric(17),
   volumn              numeric(10,6),
   weight              numeric(10,6),
   --PRIMARY KEY (unit_id),
   CONSTRAINT unit_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION  
);

 CREATE INDEX prod_unit_product_id_index ON prod_unit USING btree (product_id); 


INSERT INTO prod_unit
(
   product_id,
   name,
   unit,
   is_std_sell,
   is_std_order,
   is_fract_qty,
   is_pack_unit,
   upc,
   volumn,
   weight        
)
SELECT 
   itemcd,
   'base_unit',
   baseunit,
   baseisstdsell,
   unit1isstdord,
   baseisfractqty,
   baseispackunit,
   baseupc,
   basevolperunit,
   basewgtperunit
FROM ims;   


INSERT INTO prod_unit
(
   product_id,
   name,
   unit,
   unit_ratio,
   is_std_sell,
   is_std_order,
   is_fract_qty,
   is_pack_unit,
   upc      
)
SELECT 
   itemcd,
   'unit1',
   unit1unit,
   unit1ratio,
   unit1isstdsell,
   unit1isstdord,
   unit1isfractqty,
   unit1ispackunit,
   unit1upc
FROM ims
WHERE unit1unit is not null OR unit1unit <>''; 

INSERT INTO prod_unit
(
   product_id,
   name,
   unit,
   unit_ratio,
   is_std_sell,
   is_std_order,
   is_fract_qty,
   is_pack_unit,
   upc      
)
SELECT 
   itemcd,
   'unit2',
   unit2unit,
   unit2ratio,
   unit2isstdsell,
   unit2isstdord,
   unit2isfractqty,
   unit2ispackunit,
   unit2upc
FROM ims
WHERE unit2unit is not null OR unit2unit <>''; 


INSERT INTO prod_unit
(
   product_id,
   name,
   unit,
   unit_ratio,
   is_std_sell,
   is_std_order,
   is_fract_qty,
   is_pack_unit,
   upc      
)
SELECT 
   itemcd,
   'unit3',
   unit3unit,
   unit3ratio,
   unit3isstdsell,
   unit3isstdord,
   unit3isfractqty,
   unit3ispackunit,
   unit3upc
FROM ims
WHERE unit3unit is not null OR unit3unit <>'';  

INSERT INTO prod_unit
(
   product_id,
   name,
   unit,
   unit_ratio,
   is_std_sell,
   is_std_order,
   is_fract_qty,
   is_pack_unit,
   upc      
)
SELECT 
   itemcd,
   'unit4',
   unit4unit,
   unit4ratio,
   unit4isstdsell,
   unit4isstdord,
   unit4isfractqty,
   unit4ispackunit,
   unit4upc
FROM ims
WHERE unit4unit is not null OR unit4unit <>''; 
