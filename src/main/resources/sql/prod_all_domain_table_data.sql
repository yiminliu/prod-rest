DROP TABLE if exists product CASCADE;
CREATE TABLE product
(
   product_id                      varchar(20),
   description                     varchar(100),
   series_name                     varchar(40),
   category                        varchar(16),
   color                           varchar(30),
   color_category                  varchar(30),
   material_class                  varchar(30),
   material_type                   varchar(24),
   material_category               varchar(10),
   material_style                  varchar(7),
   length                          varchar(10),
   width                           varchar(10),
   thickness                       varchar(10),
   nominal_length                  numeric(9,4),
   nominal_width                   numeric(9,4),
   nominal_thickness               numeric(9,4),
   dimension_unit                  varchar(3),
   thickness_unit                  varchar(3),
   mps_code                        varchar(25),
   grade                           varchar(20),																																																																
   status                          varchar(20),
   mbrp                            numeric(9,4),
   future_mbrp                     numeric(9,4),
   prior_mbrp                      numeric(9,4),
   origin                          varchar(18),
   label_code                      varchar(40),
   inventory_prod_id               varchar(20), 
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
      REFERENCES prod_material_product_status (material_product_status) MATCH SIMPLE
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

CREATE INDEX product_series_idx ON product USING btree (series_name);
CREATE INDEX product_material_class_idx ON product USING btree (material_class);
CREATE INDEX product_material_type_idx ON product USING btree (material_type);
CREATE INDEX product_material_style_idx ON product USING btree (material_style);
CREATE INDEX product_mbrp_idx ON product USING btree (mbrp);
CREATE INDEX product_status_idx ON product USING btree (status);
CREATE INDEX product_grade_idx ON product USING btree (grade);

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
   mbrp,
   future_mbrp,
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
   dimension_unit,
   thickness_unit,
   shade_variation,
   label_code,
   inventory_prod_id,
   mps_code
   )
 SELECT
   itemcd,
   itemdesc1,
   seriesname,
   color,
   colorcategory,
   sellprice,
   futuresell,
   priorsellprice,
   category,
   case when origin='Usa' then 'USA' 
        when origin='' then ''
        else origin end,
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
   case when shadevariation = '' then 'V0'
        when shadevariation = 'VO' then 'V0'
        else shadevariation
   end,      
   itemcd2,
   inventory_itemcd,
   case when inactivecd = 'N' then 'Active Product'
        when inactivecd = 'Y' then 'Pre Drop'
        when inactivecd = 'D' then 'Drop'
   end     
   
FROM ims
ORDER BY itemcd; 


DROP TABLE IF EXISTS prod_detail CASCADE;
CREATE TABLE prod_detail
(
   product_id                      varchar(30),
   type_code                       varchar(10), --char(1),
   lot_type                        char(1),
   update_code                     char(10),
   product_manager                 varchar(25),
   buyer                           varchar(25), 
   tax_class                       varchar(1),  --char(1),
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost               numeric(9,4),
   prior_vendor_freight_rate_cwt   numeric(9,4),
   off_shade                       char(1)          DEFAULT 'N',
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
   created_date                    date,
   launched_date                   date,
   droped_date                     date,
   last_modified_date              date,
    
   PRIMARY KEY (product_id),
   
   CONSTRAINT prod_detail_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT tax_class_fkey FOREIGN KEY (tax_class)
     REFERENCES prod_tax_class (tax_class) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION  
);   

INSERT INTO prod_detail
(
   product_id,
   type_code,
   lot_type,
   update_code,
   product_manager,
   buyer,
   prior_vendor_price_unit,
   prior_vendor_cost,
   prior_vendor_freight_rate_cwt,
   off_shade,
   show_on_website,
   show_on_alysedwards,
   droped_date
 )
 SELECT
   itemcd,
   itemtypecd,
   lottype,
   updatecd,
   purchaser,
   purchaser2,
   priorvendorpriceunit,
   priorvendorlistprice,
   priorvendorfreightratecwt,
   offshade,
   showonwebsite,
   showonalysedwards,
   dropdate
FROM ims; 


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
   baseisstdord,
   baseisfractqty,
   baseispackunit,
   baseupc,
   basevolperunit,
   basewgtperunit
FROM ims
WHERE baseunit is not null AND baseunit <>'';   

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
WHERE unit1unit is not null AND unit1unit <>''; 

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
WHERE unit2unit is not null AND unit2unit <>''; 


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
WHERE unit3unit is not null AND unit3unit <>'';  

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
WHERE unit4unit is not null AND unit4unit <>''; 


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


DROP TABLE IF EXISTS prod_vendor_link CASCADE;
CREATE TABLE prod_vendor_link
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
   
   CONSTRAINT vendor_link_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,   
      
   CONSTRAINT vendor_fkey FOREIGN KEY (vendor_id)
      REFERENCES prod_vendor (vendor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION   
);

INSERT INTO prod_vendor_link
(
   product_id, 
   vendor_id,
   xref_id,
   price_unit,
   fob,
   cost,
   discount_pct,
   price_round_accuracy,
   net_price,
   lead_time,
   freight_rate_cwt,
   duty_pct
)
SELECT
   itemcd,
   vendornbr1,
   vendorxrefcd,
   vendorpriceunit,
   vendorfob,
   vendorlistprice,
   vendordiscpct1,
   sellpriceroundaccuracy,
   vendornetprice,
   leadtime,
   dutypct,
   vendorfreightratecwt
FROM ims
WHERE vendornbr1 is not null AND vendornbr1 > 0;


DROP TABLE IF EXISTS prod_special_price CASCADE;
CREATE TABLE prod_special_price
(
   price_id                        SERIAL PRIMARY KEY,
   price                           numeric(9,4),
   date_from                       date,
   date_thru                       date,   
   product_id                      varchar(20),   
   CONSTRAINT price_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

INSERT INTO prod_special_price
(
   price,
   date_from,
   date_thru,
   product_id
)
SELECT
   tempprice,
   tempdatefrom,
   tempdatethru,
   itemcd
FROM ims
WHERE tempprice > 0;


DROP TABLE IF EXISTS prod_application CASCADE;
CREATE TABLE prod_application
(
   residential                     varchar(20),
   light_commercial                varchar(20),
   commercial                      varchar(20),
   product_id                      varchar(20),
   
   PRIMARY KEY (product_id),
   CONSTRAINT application_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
   

INSERT INTO prod_application
(
   residential,
   light_commercial,
   commercial,
   product_id
 )
 SELECT
   residential,
   lightcommercial,
   commercial,
   itemcd   
FROM ims
WHERE (residential IS NOT NULL AND residential <> '') OR
      (lightcommercial IS NOT NULL AND lightcommercial <> '') OR
      (commercial IS NOT NULL AND commercial <> '');

  
DROP TABLE IF EXISTS prod_test CASCADE;
CREATE TABLE prod_test
(
   product_id                      varchar(20),
   water_absorption                numeric(4,2),
   frost_resistance                char(1),
   chemical_resistance             char(1),
   pei_abrasion                    numeric(4,1),
   scof_wet                        numeric(5,2),
   scof_dry                        numeric(5,2),
   breaking_strength               numeric(5),
   pre_consummer                   numeric(5,2)     DEFAULT 0,
   pos_consummer                   numeric(5,2)     DEFAULT 0,
   warpage                         varchar(5)       DEFAULT 'N/A',
   wedging                         varchar(5)       DEFAULT 'N/A',
   dcof                            varchar(6)       DEFAULT 'N/A',
   thermal_shock                   varchar(6)       DEFAULT 'N/A',
   bond_strength                   varchar(6)       DEFAULT 'N/A',
   green_friendly                  char(1)          DEFAULT 'N',
   
   PRIMARY KEY (product_id),
   CONSTRAINT test_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);


INSERT INTO prod_test
(
   product_id,
   water_absorption,
   frost_resistance,
   chemical_resistance,
   pei_abrasion,
   scof_wet,
   scof_dry,
   breaking_strength,
   pre_consummer,
   pos_consummer,
   warpage,
   wedging,
   dcof,
   thermal_shock,
   bond_strength,
   green_friendly
)
SELECT 
   itemcd,
   water_absorption,
   frost_resistance,
   chemical_resistance,
   pei_abrasion,
   scof_wet,
   scof_dry,
   breaking_strength,
   pre_consummer,
   pos_consummer,
   warpage,
   wedging,
   to_char(dcof, '99999'),
   thermal_shock,
   bond_strength,
   greenfriendly
FROM ims
WHERE itemcd IS NOT NULL;   



DROP TABLE IF EXISTS prod_note CASCADE;
CREATE TABLE prod_note
(
   note_id                  SERIAL PRIMARY KEY,
   type                     varchar(20),
   note                     varchar(120),
   product_id               varchar(20),

   CONSTRAINT note_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

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

