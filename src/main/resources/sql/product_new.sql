DROP TABLE product CASCADE;
CREATE TABLE product
(
   id                              varchar(20)         NOT NULL,
   description                     varchar(100),
   color                           varchar(30),
   color_category                  varchar(30),
   series_name                     varchar(40),
   lable_code                      varchar(40)  
   inventory_prod_id               varchar(20)      DEFAULT NULL::bpvarchar,
   abc_code                        varchar(4),
   category                        varchar(16),
   origin                          varchar(18),      
   demension 
   product_manager                 varchar(25)         DEFAULT NULL::bpvarchar,
   buyer                           varchar(25)         DEFAULT NULL::bpvarchar,    
   mps_code                        varchar(3),
   grade                           varchar(20)      DEFAULT NULL::bpvarchar,																																																																								
   status                          varchar(20)      DEFAULT NULL::bpvarchar,

   material_class                  varchar(5)          DEFAULT NULL::bpvarchar,
   material_type                   varchar(24),
   material_category               varchar(10)         DEFAULT NULL::bpvarchar,
   material_style                  varchar(7)          DEFAULT NULL::bpvarchar,
   material_feature_1              varchar(15)         DEFAULT NULL::bpvarchar,
 ? material_feature_2              varchar(15)         DEFAULT NULL::bpvarchar,
 ? material_feature_3              varchar(15)         DEFAULT NULL::bpvarchar,

   mbrp                            numeric(9,4),

   prior_mbrp                      numeric(9,4),
   future_mbrp                     numeric(9,4),
   temp_date_from                  date,
   temp_date_thru                  date,
   
   vendor_fob_cost                 varchar(10),
   package_id
   vendor_id
   
   prior_vendor_price_unit         varchar(4),
   prior_vendor_cost(priorvendorlistprice)       numeric(9,4), 
   prior_vendor_freight_rate_cwt   numeric(9,4),
  
   application_id
     
   test_id
   notes_id
 
   
   Edge	 Rectified / Squared	 Chiseled / Antiquated 	 Tapered	 Tumbled	 Beveled 	 Cushioned / Pillowed 	 Staggard																							
Icons	 Post Recycled	 Pre Recycled	 Exterior Product		
ADA Accessibility
Pos Consumer	 Pre Consumer	 Green Friendly	 Lead Point																					
Body	 Double Loaded	 Soluable Salt	 Thru Body	 Color Body	 Red Body	 White Body	
Composite Body (Porcelain Body Stone Face)
Design Look	 Wood	 Travertine																												
Design Styles	 Contemporary	 Eclectic	 Modern	 Traditional	 Asin	 Beach Style	 Craftsman	 Industrial	 Mediterranean	 Midcentury	 Rustic	 Transitional	 Tropical																	
Surface Type	 Abrasive	 Flamed	 Glazed	 Polished	 Resined	 Unglazed	 Vein-Cut	 Cross Cut																						
Surface Finish	 Antiquated	 Filled & Honed	 Fully Gauged	 Honed	 Leathered	 Metallic	 Nat. Clef	 Polished & Honed		 Stain	 Tumbled	 Filled & Honed												 Textured						
Surface Application
Ink Jet	 Roto	 Silk	 Hand Painted / Crafted																										

   ---- removed fields ----
   
   samplenbr                  numeric(8)       DEFAULT NULL::numeric,
   water_absorption_sign      varchar(2)          DEFAULT NULL::bpvarchar,
   scof_wet_sign              varchar(2)          DEFAULT NULL::bpvarchar,
   scof_dry_sign              varchar(2)          DEFAULT NULL::bpvarchar,
    price                      varchar(1)          DEFAULT NULL::bpvarchar,
   qualitychoice              varchar(12)         DEFAULT NULL::bpvarchar,
   greenfriendly              varchar(1)          DEFAULT NULL::bpvarchar,
   type                       varchar(16)         DEFAULT NULL::bpvarchar,
   subtype                    varchar(32)         DEFAULT NULL::bpvarchar,
   icons                      varchar(20)         DEFAULT NULL::bpvarchar,
   po_notes                   varchar(120),
   pricegroup                 varchar(2)          DEFAULT NULL::bpvarchar,
   sr_standard                varchar(15)         DEFAULT NULL::bpvarchar,    
   
   listpricemarginpct         numeric(5,2),
   sellpricemarginpct         numeric(5,2),
   sellpriceroundaccuracy     numeric(1),   
 
   vendorlistprice            numeric(9,4),
   vendordiscpct2             numeric(5,2),
   vendordiscpct3             numeric(5,2),
   vendormarkuppct            numeric(4,1),
   vendorfreightratecwt       numeric(9,4),
   vendorlandedbasecost       numeric(13,6),
  
   priorvendorfob             varchar(10),
   priorvendordiscpct1        numeric(5,2),
   priorvendordiscpct2        numeric(5,2),
   priorvendordiscpct3        numeric(5,2),
   priorvendorroundaccuracy   numeric(1),
   priorvendornetprice        numeric(9,4),
   priorvendormarkuppct       numeric(4,1),
   priorvendorlandedbasecost  numeric(13,6),
     
   stdunit                    varchar(4),
   stdratio                   numeric(9,4),
   ordunit                    varchar(4),
   ordratio                   numeric(9,4),
     
   moh                        numeric(5,2)     DEFAULT 0,
    
   listprice                  numeric(9,4),
   priorlastupdated           date,
   priorlistprice             numeric(9,4),
   priorsellprice             numeric(9,4),
    
   directship                 varchar(1),
   dropdate                   date,
  
   itemgroupnbr               numeric(2),
   itemtaxclass               varchar(1),
   itemtypecd                 varchar(1),
 
   lottype                    varchar(1),
   minmarginpct               numeric(4,1),
   nonstockcostpct            numeric(4,1),
   
   printlabel                 varchar(1),
   productline                varchar(8),
   
   specialhandlecd1           varchar(10),
   specialhandlecd2           varchar(10),
   specialhandlecd3           varchar(10),
   
   tempprice                  numeric(9,4),
   typealf                    varchar(8),
   updatecd                   varchar(10),
   vendorxrefcd               varchar(30),
   vendornbr                  numeric(6),
   listpricemarginpct         numeric(5,2),
   sellpricemarginpct         numeric(5,2),
   sellpriceroundaccuracy     numeric(1),
  
   vendorlistprice            numeric(9,4),
   vendordiscpct1             numeric(5,2),
   vendordiscpct2             numeric(5,2),
   vendordiscpct3             numeric(5,2),
   vendormarkuppct            numeric(4,1),
   vendorfreightratecwt       numeric(9,4),
   vendorlandedbasecost       numeric(13,6),
   priorvendorpriceunit       varchar(4),
   priorvendorfob             varchar(10),
   priorvendorlistprice       numeric(9,4),
   priorvendordiscpct1        numeric(5,2),
   priorvendordiscpct2        numeric(5,2),
   priorvendordiscpct3        numeric(5,2),
   priorvendorroundaccuracy   numeric(1),
   priorvendornetprice        numeric(9,4),
   priorvendormarkuppct       numeric(4,1),
   priorvendorfreightratecwt  numeric(9,4),
   priorvendorlandedbasecost  numeric(13,6),
   calcsellprice              numeric(9,4),
   calclistprice              numeric(9,4),
   costrangepct               numeric(4,1),
   poincludeinvendorcost      varchar(1),
   listprice1                 numeric(9,4),
   listprice2                 numeric(9,4),
   listprice3                 numeric(9,4),
   listprice4                 numeric(9,4),
   listprice5                 numeric(9,4),
   sellprice1                 numeric(9,4),
   sellprice2                 numeric(9,4),
   sellprice3                 numeric(9,4),
   sellprice4                 numeric(9,4),
   sellprice5                 numeric(9,4),
   priorlistprice1            numeric(9,4),
   priorlistprice2            numeric(9,4),
   priorlistprice3            numeric(9,4),
   priorlistprice4            numeric(9,4),
   priorlistprice5            numeric(9,4),
   priorsellprice1            numeric(9,4),
   priorsellprice2            numeric(9,4),
   priorsellprice3            numeric(9,4),
   priorsellprice4            numeric(9,4),
   priorsellprice5            numeric(9,4),
   futurelist                 numeric(9,4)     DEFAULT 0,
   futuresell                 numeric(9,4)     DEFAULT 0,
   futurelist1                numeric(9,4)     DEFAULT 0,
   futuresell1                numeric(9,4)     DEFAULT 0,
   cost1                      numeric(9,4)     DEFAULT (0)::numeric,
   priorcost                  numeric(9,4)     DEFAULT (0)::numeric,
   priorcost1                 numeric(9,4)     DEFAULT (0)::numeric,
   futurecost                 numeric(9,4)     DEFAULT (0)::numeric,
   futurecost1                numeric(9,4)     DEFAULT (0)::numeric,
   vendornbr1                 numeric(6),
   vendornbr2                 numeric(6),
   vendornbr3                 numeric(6),
   
   sizeunits                  varchar(3),
   fulldesc                   varchar(70),
   application                varchar(20),
   
   offshade                   varchar(1)          DEFAULT 'N'::bpvarchar,
   itemcd2                    varchar(18)         DEFAULT NULL::bpvarchar,
 
   bk_standard                varchar(15)         DEFAULT NULL::bpvarchar,
      
   lead_point                 varchar(4)          DEFAULT 0,
   unit1wgtperunit            numeric(12,6)    DEFAULT 0,
   unit2wgtperunit            numeric(12,6)    DEFAULT 0,
   unit3wgtperunit            numeric(12,6)    DEFAULT 0,
   unit4wgtperunit            numeric(12,6)    DEFAULT 0,
    
   known_aliases1             varchar(30)         DEFAULT NULL::bpvarchar,
   known_aliases2             varchar(30)         DEFAULT NULL::bpvarchar,
   known_aliases3             varchar(30)         DEFAULT NULL::bpvarchar,
   known_aliases4             varchar(30)         DEFAULT NULL::bpvarchar,
   known_aliases5             varchar(30)         DEFAULT NULL::bpvarchar,
   known_aliases6             varchar(30)         DEFAULT NULL::bpvarchar,
   known_aliases7             varchar(30)         DEFAULT NULL::bpvarchar,
   similar_itemcd1            varchar(18)         DEFAULT NULL::bpvarchar,
   similar_itemcd2            varchar(18)         DEFAULT NULL::bpvarchar,
   similar_itemcd3            varchar(18)         DEFAULT NULL::bpvarchar,
   similar_itemcd4            varchar(18)         DEFAULT NULL::bpvarchar,
   similar_itemcd5            varchar(18)         DEFAULT NULL::bpvarchar,
   similar_itemcd6            varchar(18)         DEFAULT NULL::bpvarchar,
   similar_itemcd7            varchar(18)         DEFAULT NULL::bpvarchar,
   restricted                 varchar(1)          DEFAULT 'N'::bpvarchar,
    ,CONSTRAINT ims_check6 CHECK (((((((((((((((((((((((((((((((((((((((((((((baseunit <> 'EA'::bpvarchar) AND (baseunit <> 'EACH'::bpvarchar)) AND (baseunit <> 'PC'::bpvarchar)) AND (baseunit <> 'LF'::bpvarchar)) AND (baseunit <> 'SF'::bpvarchar)) AND (baseunit <> 'CT'::bpvarchar)) AND (baseunit <> 'CTM'::bpvarchar)) AND (baseunit <> 'CTNH'::bpvarchar)) AND (baseunit <> 'PK'::bpvarchar)) AND (unit1unit <> 'EA'::bpvarchar)) AND (unit1unit <> 'EACH'::bpvarchar)) AND (unit1unit <> 'PC'::bpvarchar)) AND (unit1unit <> 'LF'::bpvarchar)) AND (unit1unit <> 'SF'::bpvarchar)) AND (unit1unit <> 'CT'::bpvarchar)) AND (unit1unit <> 'CTM'::bpvarchar)) AND (unit1unit <> 'CTNH'::bpvarchar)) AND (unit1unit <> 'PK'::bpvarchar)) AND (unit2unit <> 'EA'::bpvarchar)) AND (unit2unit <> 'EACH'::bpvarchar)) AND (unit2unit <> 'PC'::bpvarchar)) AND (unit2unit <> 'LF'::bpvarchar)) AND (unit2unit <> 'SF'::bpvarchar)) AND (unit2unit <> 'CT'::bpvarchar)) AND (unit2unit <> 'CTM'::bpvarchar)) AND (unit2unit <> 'CTNH'::bpvarchar)) AND (unit2unit <> 'PK'::bpvarchar)) AND (unit3unit <> 'EA'::bpvarchar)) AND (unit3unit <> 'EACH'::bpvarchar)) AND (unit3unit <> 'PC'::bpvarchar)) AND (unit3unit <> 'LF'::bpvarchar)) AND (unit3unit <> 'SF'::bpvarchar)) AND (unit3unit <> 'CT'::bpvarchar)) AND (unit3unit <> 'CTM'::bpvarchar)) AND (unit3unit <> 'CTNH'::bpvarchar)) AND (unit3unit <> 'PK'::bpvarchar)) AND (unit4unit <> 'EA'::bpvarchar)) AND (unit4unit <> 'EACH'::bpvarchar)) AND (unit4unit <> 'PC'::bpvarchar)) AND (unit4unit <> 'LF'::bpvarchar)) AND (unit4unit <> 'SF'::bpvarchar)) AND (unit4unit <> 'CT'::bpvarchar)) AND (unit4unit <> 'CTM'::bpvarchar)) AND (unit4unit <> 'CTNH'::bpvarchar)) AND (unit4unit <> 'PK'::bpvarchar))
   ,CONSTRAINT ims_check5 CHECK ((((((((((((((((((((vendorpriceunit IS NOT NULL) AND ((((((vendorpriceunit <= '    '::bpvarchar) OR (vendorpriceunit = baseunit)) OR (vendorpriceunit = unit1unit)) OR (vendorpriceunit = unit2unit)) OR (vendorpriceunit = unit3unit)) OR (vendorpriceunit = unit4unit))) AND (vendorlistprice IS NOT NULL)) AND (vendordiscpct1 IS NOT NULL)) AND (vendordiscpct2 IS NOT NULL)) AND (vendordiscpct3 IS NOT NULL)) AND (vendorroundaccuracy IS NOT NULL)) AND (vendorroundaccuracy >= (0)::numeric)) AND (vendorroundaccuracy <= (4)::numeric)) AND (vendornetprice IS NOT NULL)) AND (vendormarkuppct IS NOT NULL)) AND (vendorfreightratecwt IS NOT NULL)) AND (vendorlandedbasecost IS NOT NULL)) AND (sellpricemarginpct IS NOT NULL)) AND (sellpricemarginpct <> (100)::numeric)) AND (sellpriceroundaccuracy IS NOT NULL)) AND (sellpriceroundaccuracy >= (0)::numeric)) AND (sellpriceroundaccuracy <= (4)::numeric)) AND (listpricemarginpct IS NOT NULL)) AND (listpricemarginpct <> (100)::numeric))
   ,CONSTRAINT ims_check4 CHECK ((((unit4unit <= '    '::bpvarchar) AND (unit4ratio = (0)::numeric)) OR ((unit4unit IS NULL) AND (unit4ratio = (0)::numeric))) OR (((unit4unit > '    '::bpvarchar) AND (unit4ratio <> (0)::numeric)) AND (unit4ratio IS NOT NULL)))
   ,CONSTRAINT ims_check3 CHECK ((((unit3unit <= '    '::bpvarchar) AND (unit3ratio = (0)::numeric)) OR ((unit3unit IS NULL) AND (unit3ratio = (0)::numeric))) OR (((unit3unit > '    '::bpvarchar) AND (unit3ratio <> (0)::numeric)) AND (unit3ratio IS NOT NULL)))
   ,CONSTRAINT ims_check2 CHECK ((((unit2unit <= '    '::bpvarchar) AND (unit2ratio = (0)::numeric)) OR ((unit2unit IS NULL) AND (unit2ratio = (0)::numeric))) OR (((unit2unit > '    '::bpvarchar) AND (unit2ratio <> (0)::numeric)) AND (unit2ratio IS NOT NULL)))
   ,CONSTRAINT ims_check1 CHECK ((((unit1unit <= '    '::bpvarchar) AND (unit1ratio = (0)::numeric)) OR ((unit1unit IS NULL) AND (unit1ratio = (0)::numeric))) OR (((unit1unit > '    '::bpvarchar) AND (unit1ratio <> (0)::numeric)) AND (unit1ratio IS NOT NULL)))
);

ALTER TABLE ims
   ADD CONSTRAINT ims_id
   PRIMARY KEY (itemcd);

CREATE INDEX ims_unit2upc_idx ON ims USING btree (unit2upc);
CREATE INDEX ims_unit1upc_idx ON ims USING btree (unit1upc);
CREATE INDEX ims_materialclass_cd_idx ON ims USING btree (materialclass_cd);
CREATE INDEX ims_baseupc_idx ON ims USING btree (baseupc);


GRANT SELECT ON ims TO bedrock;
GRANT UPDATE, TRIGGER, TRUNCATE, REFERENCES, INSERT, DELETE, SELECT ON ims TO postgres;
GRANT SELECT ON ims TO bedsale;
GRANT SELECT ON ims TO pat;
GRANT SELECT ON ims TO price;
GRANT SELECT ON ims TO larry;
GRANT SELECT ON ims TO bedcook;
GRANT DELETE, INSERT, TRIGGER, TRUNCATE, REFERENCES, SELECT, UPDATE ON ims TO grege;

COMMIT;
