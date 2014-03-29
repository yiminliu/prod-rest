DROP TABLE IF EXISTS prod_dimension CASCADE;
CREATE TABLE prod_dimension
(
   length                          numeric(9,4),
   width                           numeric(9,4),
   thickness                       numeric(9,4),
   nominal_length                  numeric(9,4),
   nominal_width                   numeric(9,4),
   nominal_thickness               numeric(9,4),
   size_unit                       varchar(3),
   thickness_unit                  varchar(3),
   product_id                      varchar(20),
   PRIMARY KEY (product_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

INSERT INTO prod_dimension
(
   product_id,  
   length,
   width,
   thickness,
   nominal_length,
   nominal_width,
   nominal_thickness,
   size_unit,
   thickness_unit   
 )
 SELECT
   itemcd,   
   to_number(length, '999999'),
   to_number(width,'999999'),
   to_number(thickness,'999999'),
   nm_length,
   nm_width,
   nm_thickness,
   sizeunits,
   thicknessunit
FROM ims; 

COMMIT;  