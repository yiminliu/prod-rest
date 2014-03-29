DROP TABLE if exists product CASCADE;
CREATE TABLE product
(
   product_id                      varchar(20),
   description                     varchar(100),
   series_name                     varchar(40),
   category                        varchar(16),
   color                           varchar(30),
   color_category                  varchar(30),
   label_code                      varchar(40),
   inventory_prod_id               varchar(20), 
   material_class                  varchar(5),
   material_type                   varchar(24),
   material_category               varchar(10),
   material_style                  varchar(7),
   length                          varchar(10),
   width                           varchar(10),
   thickness                       varchar(10),
   nominal_length                  numeric(9,4),
   nominal_width                   numeric(9,4),
   nominal_thickness               numeric(9,4),
   size_unit                       varchar(3),
   thickness_unit                  varchar(3),
   mps_code                        varchar(3),
   grade                           varchar(20),																																																																								
   status                          varchar(20),
   mbrp                            numeric(9,4),
   prior_mbrp                      numeric(9,4),
   origin                          varchar(18),  
   shade_variation                 char(2),
   icon	                           varchar(25),
   body                            varchar(30),
   edge                            varchar(30),
   surface_type                    varchar(30),   
   surface_finish                  varchar(30),
   surface_application             varchar(30),
   design_style                    varchar(30),   
   design_look                     varchar(30),
      
   PRIMARY KEY (product_id),
   
      --CONSTRAINT color_category_fkey FOREIGN KEY (color_category)
      --REFERENCES prod_color_category (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT material_class_fkey FOREIGN KEY (material_class)
      --REFERENCES prod_material_class (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT material_type_fkey FOREIGN KEY (material_type)
      --REFERENCES prod_material_type (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT material_style_fkey FOREIGN KEY (material_style)
      --REFERENCES prod_material_style (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
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
      
     -- CONSTRAINT origin_fkey FOREIGN KEY (origin)
     -- REFERENCES prod_origin (description) MATCH SIMPLE
     -- ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT shade_variation_fkey FOREIGN KEY (shade_variation)
      --REFERENCES prod_shade_variation (code) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT icon_fkey FOREIGN KEY (icon)
      REFERENCES prod_icon (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT body_fkey FOREIGN KEY (body)
     REFERENCES prod_body (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT surface_type_fkey FOREIGN KEY (surface_type)
     REFERENCES prod_surface_type (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
     
   CONSTRAINT surface_finish_fkey FOREIGN KEY (surface_finish)
     REFERENCES prod_surface_finish (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
   
   CONSTRAINT surface_application_fkey FOREIGN KEY (surface_application)
     REFERENCES prod_surface_application (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
     
   CONSTRAINT edge_fkey FOREIGN KEY (edge)
     REFERENCES prod_edge (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
   
   CONSTRAINT design_style_fkey FOREIGN KEY (design_style)
     REFERENCES prod_design_style (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
     
   CONSTRAINT design_look_fkey FOREIGN KEY (design_look)
     REFERENCES prod_design_look (description) MATCH SIMPLE
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


INSERT INTO product
(
   product_id,
   description,
   series_name,
   color,
   color_category,
   label_code,
   inventory_prod_id,
   mbrp,
   prior_mbrp, 
   category,
   origin,
   material_class,
   material_type,
   material_category,
   material_style,
   length,
   width,
   thickness,
   nominal_length,
   nominal_width,
   nominal_thickness,
   size_unit,
   thickness_unit,
   shade_variation,
   mps_code
   )
 SELECT
   trim(itemcd),
   itemdesc1,
   seriesname,
   color,
   colorcategory,
   itemcd2,
   inventory_itemcd,
   sellprice,
   priorsellprice,
   category,
   case when origin = 'Usa' then 'USA' else origin end,
   materialclass_cd,
   mattype,
   matcategory,
   matstyle,
   length,
   width,
   thickness,
   nm_length,
   nm_width,
   nm_thickness,
   sizeunits,
   thicknessunit,
   shadevariation,
   case when inactivecd = 'N' then '02'
        when inactivecd = 'Y' then '04'
        when inactivecd = 'D' then '05' 
   end
   
FROM ims
ORDER BY itemcd; 

COMMIT;
