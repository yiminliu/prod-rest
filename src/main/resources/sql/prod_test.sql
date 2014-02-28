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
   CONSTRAINT test_product_fkey FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);
COMMIT;


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
   to_char(scof_wet, '99999'),
   to_Char(scof_dry, '99999'),
   breaking_strength,
   pre_consummer,
   pos_consummer,
   warpage,
   wedging,
   to_char(dcof, '99999'),
   thermal_shock,
   bond_strength,
   greenfriendly
FROM ims;   

COMMIT;   