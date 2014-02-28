DROP TABLE if exists product CASCADE;
CREATE TABLE product
(
   product_id                      varchar(20),
   description                     varchar(100),
   series_name                     varchar(40),
   category                        varchar(16),
   color                           varchar(30),
   color_category                  varchar(30),
   mps_code                        varchar(3),
   grade                           varchar(20),																																																																								
   status                          varchar(20),
   mbrp                            numeric(9,4),
   prior_mbrp                      numeric(9,4),  
   material_class                  varchar(5),
   material_type                   varchar(24),
   material_category               varchar(10),
   material_style                  varchar(7),
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
   created_date                    date,
   last_modified_date              date
      
   PRIMARY KEY (product_id),
   
      CONSTRAINT color_category_fkey FOREIGN KEY (color_category)
      REFERENCES prod_color_category (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT material_class_fkey FOREIGN KEY (material_class)
      REFERENCES prod_material_class (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT material_type_fkey FOREIGN KEY (material_type)
      REFERENCES prod_material_type (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT material_style_fkey FOREIGN KEY (material_style)
      REFERENCES prod_material_style (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT material_category_fkey FOREIGN KEY (material_category)
      REFERENCES prod_material_category (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT material_product_status_fkey FOREIGN KEY (mps_code)
      REFERENCES prod_material_product_status (mps_code) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
   
      CONSTRAINT grade_fkey FOREIGN KEY (grade)
      REFERENCES prod_grade (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT status_fkey FOREIGN KEY (status)
      REFERENCES prod_status (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT origin_fkey FOREIGN KEY (origin)
      REFERENCES prod_origin (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
            
);


CREATE INDEX product_material_class_idx ON product USING btree (material_class);

GRANT SELECT ON product TO bedrock;
GRANT UPDATE, TRIGGER, TRUNCATE, REFERENCES, INSERT, DELETE, SELECT ON product TO postgres;
GRANT SELECT ON product TO bedsale;
GRANT SELECT ON product TO pat;
GRANT SELECT ON product TO price;
GRANT SELECT ON product TO larry;
GRANT SELECT ON product TO bedcook;
GRANT DELETE, INSERT, TRIGGER, TRUNCATE, REFERENCES, SELECT, UPDATE ON product TO grege;

COMMIT;