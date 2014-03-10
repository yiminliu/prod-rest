DROP TABLE if exists ims_new_feature CASCADE;
CREATE TABLE ims_new_feature
(
   itemcd                          varchar(15),
   mps_code                        varchar(25),
   grade                           varchar(20),																																																																
   status                          varchar(20),
   icon	                           varchar(25),
   body                            varchar(30),
   edge                            varchar(30),
   surface_type                    varchar(30),   
   surface_finish                  varchar(30),
   surface_application             varchar(30),
   design_style                    varchar(30),   
   design_look                     varchar(30),
   created_date                    date,
   launched_date                   date,
   last_modified_date              date,
      
   PRIMARY KEY (itemcd),
   
      --CONSTRAINT color_category_fkey FOREIGN KEY (color_category)
      --REFERENCES ims_color_category (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT material_class_fkey FOREIGN KEY (material_class)
      --REFERENCES ims_material_class (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT material_type_fkey FOREIGN KEY (material_type)
      --REFERENCES ims_material_type (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
      --CONSTRAINT material_style_fkey FOREIGN KEY (material_style)
      --REFERENCES ims_material_style (description) MATCH SIMPLE
      --ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT ims_fkey FOREIGN KEY (itemcd)
      REFERENCES ims (itemcd) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT mps_fkey FOREIGN KEY (mps_code)
      REFERENCES ims_material_product_status (material_product_status) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
   
   CONSTRAINT grade_fkey FOREIGN KEY (grade)
      REFERENCES ims_grade (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT status_fkey FOREIGN KEY (status)
      REFERENCES ims_status (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT icon_fkey FOREIGN KEY (icon)
      REFERENCES ims_icon (description) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT body_fkey FOREIGN KEY (body)
     REFERENCES ims_body (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
      
   CONSTRAINT surface_type_fkey FOREIGN KEY (surface_type)
     REFERENCES ims_surface_type (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
     
   CONSTRAINT surface_finish_fkey FOREIGN KEY (surface_finish)
     REFERENCES ims_surface_finish (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
   
   CONSTRAINT surface_application_fkey FOREIGN KEY (surface_application)
     REFERENCES ims_surface_application (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
     
   CONSTRAINT edge_fkey FOREIGN KEY (edge)
     REFERENCES ims_edge (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
   
   CONSTRAINT design_style_fkey FOREIGN KEY (design_style)
     REFERENCES ims_design_style (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
     
   CONSTRAINT design_look_fkey FOREIGN KEY (design_look)
     REFERENCES ims_design_look (description) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION
            
);

--CREATE INDEX product_status_idx ON product USING btree (status);
--CREATE INDEX product_grade_idx ON product USING btree (grade);

GRANT SELECT ON ims_new_feature TO bedrock;
GRANT UPDATE, TRIGGER, TRUNCATE, REFERENCES, INSERT, DELETE, SELECT ON ims_new_feature TO postgres;
GRANT SELECT ON ims_new_feature TO bedsale;
GRANT SELECT ON ims_new_feature TO pat;
GRANT SELECT ON ims_new_feature TO price;
GRANT SELECT ON ims_new_feature TO larry;
GRANT SELECT ON ims_new_feature TO bedcook;
GRANT DELETE, INSERT, TRIGGER, TRUNCATE, REFERENCES, SELECT, UPDATE ON ims_new_feature TO grege;

COMMIT;   

