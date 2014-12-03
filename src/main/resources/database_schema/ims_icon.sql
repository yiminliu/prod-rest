DROP TABLE IF EXISTS ims_icon;
CREATE TABLE ims_icon
(
    item_code                    char(18),
    made_in_country              varchar(20),
    exterior_product             boolean      DEFAULT 'FALSE', 
    ada_accessibility            boolean      DEFAULT 'FALSE',
    through_color                boolean      DEFAULT 'FALSE',
    color_body                   boolean      DEFAULT 'FALSE',
    ink_jet                      boolean      DEFAULT 'FALSE',
    glazed                       boolean      DEFAULT 'FALSE',
    unglazed                     boolean      DEFAULT 'FALSE',
    rectified_edge               boolean      DEFAULT 'FALSE',
    chiseled_edge                boolean      DEFAULT 'FALSE',
    recycled                     boolean      DEFAULT 'FALSE',
    post_recycled                boolean      DEFAULT 'FALSE',
    pre_recycled                 boolean      DEFAULT 'FALSE',
    lead_point                   boolean      DEFAULT 'FALSE',
    green_friendly               boolean      DEFAULT 'FALSE',
    coefficient_of_friction      boolean      DEFAULT 'FALSE',
    versailles_pattern           boolean      DEFAULT 'FALSE',
    version                      numeric(15),
     
    PRIMARY KEY (item_code),
     
    CONSTRAINT icon_ims_fkey FOREIGN KEY (item_code)
       REFERENCES ims (itemcd) MATCH SIMPLE
       ON UPDATE CASCADE ON DELETE CASCADE   
 
);

CREATE INDEX ims_icon_itemcd_idx ON ims_icon USING btree (item_code);

COMMIT;   