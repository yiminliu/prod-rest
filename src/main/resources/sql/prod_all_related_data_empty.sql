DROP TABLE if exists product CASCADE;
CREATE TABLE product
(
   product_id                      varchar(20),
   description                     varchar(100),
   series_name                     varchar(40),
   category                        varchar(16),
   color                           varchar(30),
   color_category                  varchar(30), 
   material_class                  varchar(5),
   material_type                   varchar(24),
   material_category               varchar(10),
   material_style                  varchar(7),
   length                          numeric(9,4),
   width                           numeric(9,4),
   thickness                       numeric(9,4),
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
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      CONSTRAINT shade_variation_fkey FOREIGN KEY (shade_variation)
      REFERENCES prod_shade_variation (code) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
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



DROP TABLE IF EXISTS prod_price CASCADE;
CREATE TABLE prod_price
(
   price_id                        numeric(15), 
   type                            varchar(10), 
   mbrp                            numeric(9,4),
   date_from                       date,
   date_thru                       date,   
   product_id                      varchar(20),   
   PRIMARY KEY (price_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

DROP TABLE IF EXISTS prod_application CASCADE;
CREATE TABLE prod_application
(
   residential                     varchar(20),
   light_commercial                 varchar(20),
   commercial                      varchar(20),
   product_id                      varchar(20),
   PRIMARY KEY (product_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
   
DROP TABLE IF EXISTS prod_price CASCADE;
CREATE TABLE prod_price
(
   price_id                        numeric(15), 
   type                            varchar(10), 
   mbrp                            numeric(9,4),
   date_from                       date,
   date_thru                       date,   
   product_id                      varchar(20),   
   PRIMARY KEY (price_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);


DROP TABLE IF EXISTS prod_material_feature CASCADE;
CREATE TABLE prod_material_feature
(
   feature_id                      numeric(15),
   name                            numeric(10),
   description                     varchar(30),
   product_id                      varchar(20),
   
   PRIMARY KEY (feature_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

DROP TABLE IF EXISTS prod_note CASCADE;
CREATE TABLE prod_note
(
   note_id                  numeric(15),
   type                     varchar(10),
   note                     varchar(120),
   product_id                varchar(20),
   PRIMARY KEY (note_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);


DROP TABLE IF EXISTS prod_test CASCADE;
CREATE TABLE prod_test
(
   product_id                      varchar(20),
   water_absorption                numeric(4,2),
   frost_resistance                char(1),
   chemical_resistance             char(1),
   pei_abrasion                    numeric(4,1),
   scof_wet                        varchar(6)       DEFAULT 'N/A',
   scof_dry                        varchar(6)       DEFAULT 'N/A',
   breaking_strength               numeric(5),
   pre_consummer                   numeric(5,2)     DEFAULT 0,
   pos_consummer                   numeric(5,2)     DEFAULT 0,
   warpage                         varchar(5)       DEFAULT 'N/A',
   wedging                         varchar(5)       DEFAULT 'N/A',
   dcof                            varchar(6)       DEFAULT 'N/A',
   thermal_shock                   varchar(6)       DEFAULT 'N/A',
   bond_strength                   varchar(6)       DEFAULT 'N/A',
   green_friendly                   char(1)          DEFAULT 'N',
   PRIMARY KEY (product_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

DROP TABLE IF EXISTS prod_unit CASCADE;
CREATE TABLE prod_unit
(
   unit_id             numeric(15),
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
   PRIMARY KEY (unit_id),
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);


    

DROP TABLE IF EXISTS prod_vendor CASCADE;
CREATE TABLE prod_vendor
(
   vendor_id               numeric(10),
   name                    varchar(60),
   name2                   varchar(60),
  
   PRIMARY KEY (vendor_id)
);

DROP TABLE IF EXISTS prod_vendor_product_link CASCADE;
CREATE TABLE prod_vendor_product_link
(
   product_id              varchar(20), 
   vendor_id               numeric(10),
   xref_id                 varchar(60),
   price_unit              varchar(4),
   fob                     char(10),
   cost                    numeric(9,4), 
   discount_pct            numeric(5,2),
   price_round_accuracy    numeric(1),
   net_price               numeric(9,4),
   lead_time               numeric(4),
   freight_rate_cwt        numeric(9,4),
   duty_pct                numeric(7,4)     DEFAULT 0,

   PRIMARY KEY (product_id, vendor_id),
   
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,   
      
   CONSTRAINT vendor_fkey FOREIGN KEY (vendor_id)
      REFERENCES prod_vendor (vendor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION   
);

DROP TABLE IF EXISTS prod_detail CASCADE;
CREATE TABLE prod_detail
(
   product_id                      varchar(30),
   product_manager                 varchar(25),
   buyer                           varchar(25), 
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost               numeric(9,4),
   prior_vendor_freight_rate_cwt   numeric(9,4),
   off_shade                       char(1)          DEFAULT 'N',
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
   created_date                    date,
   last_modified_date              date,
    
   PRIMARY KEY (product_id),
   
   CONSTRAINT product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION 
     
);   

COMMIT;   