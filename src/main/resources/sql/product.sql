DROP TABLE if exists product CASCADE;
CREATE TABLE product
(
   product_id                      varchar(20)         NOT NULL,
   description                     varchar(100),
   color                           varchar(30),
   color_category                  varchar(30),
   series_name                     varchar(40),
   lable_code                      varchar(40),  
   inventory_prod_id               varchar(20),
   abc_code                        varchar(4),
   category                        varchar(16),
   origin                          varchar(18),  
   
   length                          numeric(9,4),
   width                           numeric(9,4),
   thickness                       numeric(9,4),
   nominal_length                  numeric(9,4),
   nominal_width                   numeric(9,4),
   nominal_thickness               numeric(9,4),
   size_unit                       varchar(3),
   thickness_unit                  varchar(3), 
   product_manager                 varchar(25),
   buyer                           varchar(25),    
   mps_code                        varchar(3),
   grade                           varchar(20),																																																																								
   status                          varchar(20),
   
   material_class                  varchar(5),
   material_type                   varchar(24),
   material_category               varchar(10),
   material_style                  varchar(7),
   material_feature1               varchar(15),
   material_feature2               varchar(15),
   material_feature3               varchar(15),

   mbrp                            numeric(9,4),
   prior_mbrp                      numeric(9,4),
   future_mbrp                     numeric(9,4),
   temp_date_from                  date,
   temp_date_thru                  date,
   
   base_unit                       varchar(4),
   base_is_std_sell                char(1),
   base_is_std_order               char(1),
   base_is_fract_qty               char(1),
   base_is_pack_unit               char(1),
   base_upc                        numeric(17),
   base_volumn                     numeric(10,6),
   base_weight                     numeric(10,6),
 
   unit1_unit                      varchar(4),
   unit1_ratio                     numeric(9,4),
   unit1_is_std_sell               char(1),
   unit1_is_std_order              char(1),
   unit1_is_fract_qty              char(1),
   unit1_is_pack_unit              char(1),
   unit1_upc                       numeric(17),
   unit1_weight                    numeric(10,6),
   
   unit2_unit                      varchar(4),
   unit2_ratio                     numeric(9,4),
   unit2_is_std_sell               char(1),
   unit2_is_std_order              char(1),
   unit2_is_fract_qty              char(1),
   unit2_is_pack_unit              char(1),
   unit2_upc                       numeric(17),
   unit2_weight                    numeric(10,6),
   
   unit3_unit                      varchar(4),
   unit3_ratio                     numeric(9,4),
   unit3_is_std_sell               char(1),
   unit3_is_std_order              char(1),
   unit3_is_fract_qty              char(1),
   unit3_is_pack_unit              char(1),
   unit3_upc                       numeric(17),
   unit3_weight                    numeric(10,6),
   
   unit4_unit                      varchar(4),
   unit4_ratio                     numeric(9,4),
   unit4_is_std_sell               char(1),
   unit4_is_std_order              char(1),
   unit4_is_fract_qty              char(1),
   unit4_is_pack_unit              char(1),
   unit4_upc                       numeric(17),
   unit4_weight                    numeric(10,6),
   
   vendor_fob_cost                 varchar(10),
   vendor_xref_id                  varchar(50),
   vendor_id1                      numeric(10),
   vendor_id2                      numeric(10),
   vendor_id3                      numeric(10),
   vendor_price_unit               char(4),
   vendor_cost                     numeric(9,4), 
   vendor_disc_pct                 numeric(5,2),
   vendor_price_round              numeric(1),
   vendor_net_price                numeric(9,4),
   lead_time                       numeric(4),
   duty_pct                        numeric(7,4)     DEFAULT 0,
   
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost               numeric(9,4), 
   prior_vendor_freight_rate_cwt   numeric(9,4),
  
   residential                     varchar(20),
   light_commercial                varchar(20),
   commercial                      varchar(20),
     
   water_absorption                numeric(4,2),
   frost_resistance                char(1),
   chemical_resistance             char(1),
   pei_abrasion                    numeric(4,1),
   scof_wet                        numeric(4,2),
   scof_dry                        numeric(4,2),
   breaking_strength               numeric(5),
   pre_consummer                   numeric(5,2)     DEFAULT 0,
   pos_consummer                   numeric(5,2)     DEFAULT 0,
   warpage                         varchar(5)       DEFAULT 'N/A',
   wedging                         varchar(5)       DEFAULT 'N/A',
   dcof                            numeric(4,4),
   thermal_shock                   varchar(6)       DEFAULT 'N/A',
   bond_strength                   varchar(6)       DEFAULT 'N/A',
   green_friendly                   char(1)          DEFAULT 'N',
     
   offshade                        char(1)          DEFAULT 'N',
   shade_variation                 char(2),
   show_on_website                 char(1)          DEFAULT 'Y',
   show_on_alysedwards             char(1)          DEFAULT 'Y',
  
   po_note                         varchar(120),
   vendor_note                     varchar(120),
   invoice_note                    varchar(120),
   buyer_note                      varchar(120),
   intranet_note                   varchar(120),
 
   edge	                           varchar(25),
   icon	                           varchar(25),
   body	                           varchar(25),
   design_look	                   varchar(25),
   design_style	                   varchar(25),
   surface_type                    varchar(25),
   surface_finish	               varchar(25),
   surface_application             varchar(25),
   
    PRIMARY KEY (product_id),
    CONSTRAINT ims_check4 CHECK ((((unit4_unit <= '    ') AND (unit4_ratio = (0))) OR ((unit4_unit IS NULL) AND (unit4_ratio = (0)))) OR (((unit4_unit > '    ') AND (unit4_ratio <> (0))) AND (unit4_ratio IS NOT NULL)))
   ,CONSTRAINT ims_check3 CHECK ((((unit3_unit <= '    ') AND (unit3_ratio = (0))) OR ((unit3_unit IS NULL) AND (unit3_ratio = (0)))) OR (((unit3_unit > '    ') AND (unit3_ratio <> (0))) AND (unit3_ratio IS NOT NULL)))
   ,CONSTRAINT ims_check2 CHECK ((((unit2_unit <= '    ') AND (unit2_ratio = (0))) OR ((unit2_unit IS NULL) AND (unit2_ratio = (0)))) OR (((unit2_unit > '    ') AND (unit2_ratio <> (0))) AND (unit2_ratio IS NOT NULL)))
   ,CONSTRAINT ims_check1 CHECK ((((unit1_unit <= '    ') AND (unit1_ratio = (0))) OR ((unit1_unit IS NULL) AND (unit1_ratio = (0)))) OR (((unit1_unit > '    ') AND (unit1_ratio <> (0))) AND (unit1_ratio IS NOT NULL)))
);

CREATE INDEX product_unit2_upc_idx ON product USING btree (unit2_upc);
CREATE INDEX product_unit1_upc_idx ON product USING btree (unit1_upc);
CREATE INDEX product_material_class_idx ON product USING btree (material_class);
CREATE INDEX product_base_upc_idx ON product USING btree (base_upc);

GRANT SELECT ON product TO bedrock;
GRANT UPDATE, TRIGGER, TRUNCATE, REFERENCES, INSERT, DELETE, SELECT ON product TO postgres;
GRANT SELECT ON product TO bedsale;
GRANT SELECT ON product TO pat;
GRANT SELECT ON product TO price;
GRANT SELECT ON product TO larry;
GRANT SELECT ON product TO bedcook;
GRANT DELETE, INSERT, TRIGGER, TRUNCATE, REFERENCES, SELECT, UPDATE ON product TO grege;

COMMIT;
