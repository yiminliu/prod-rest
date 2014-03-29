DROP TABLE IF EXISTS ims_status CASCADE;
CREATE TABLE ims_status
(
  description                varchar(30)     PRIMARY KEY
);
   
INSERT INTO ims_status VALUES
      ('Best'),
      ('Better'),
      ('Good');
     

DROP TABLE IF EXISTS ims_grade CASCADE;
CREATE TABLE ims_grade
(
  description                varchar(30)     PRIMARY KEY
);
   
INSERT INTO ims_grade VALUES
      ('First'),
      ('Second'),
      ('Third');
      
      
      
DROP TABLE IF EXISTS ims_material_product_status CASCADE;
CREATE TABLE ims_material_product_status
(
   material_product_status          varchar(30)     PRIMARY KEY
);
   

INSERT INTO ims_material_product_status(material_product_status) VALUES
      ('New Product'),
      ('Active Product'),
      ('Pre Drop'),
      ('Drop'),
      ('Obsolete'),
      ('Do Not Inventory'),
      ('Seconde/Promo'),
      ('Claims Inventory'),
      ('Direct Ship'),
      ('Special Order');
      
      
DROP TABLE IF EXISTS ims_material_type CASCADE;
CREATE TABLE ims_material_type
(
   description                varchar(30)       PRIMARY KEY
);
     
      
DROP TABLE IF EXISTS ims_body CASCADE;
CREATE TABLE ims_body
(   
   description                varchar(30)  PRIMARY KEY
);
   
INSERT INTO ims_body VALUES
      ('Double Loaded'),
      ('Soluable Salt'),      
      ('Thru Body'),
      ('Color Body'),
      ('Red Body'),
      ('White Body'),
      ('Composite Body'),
      ('Porcelain Body Stone Face');

      
DROP TABLE IF EXISTS ims_tax_class CASCADE;
CREATE TABLE ims_tax_class
(   
   tax_class          varchar(1)  PRIMARY KEY,
   description        varchar(15)
);
   
INSERT INTO ims_tax_class VALUES
      ('T', 'Tax'),
      ('N', 'Non-Tax'),
      ('E', 'Exempt');

      
DROP TABLE IF EXISTS ims_edge CASCADE;
CREATE TABLE ims_edge
(   
   description                varchar(30)  PRIMARY KEY
);
   

INSERT INTO ims_edge VALUES
      ('Rectified'),
      ('Squared'),
      ('Chiseled'),
      ('Antiquated'),
      ('Tapered'),
      ('Tumbled'),
      ('Beveled'),
      ('Cushioned'),
      ('Pillowed'),
      ('Staggard');
      
            
DROP TABLE IF EXISTS ims_icon CASCADE;
CREATE TABLE ims_icon
(   
   description                varchar(30)  PRIMARY KEY
);
   
INSERT INTO ims_icon VALUES
      ('Post Recycled'),
      ('Pre Recycled'),
      ('Exterior Product'),
      ('ADA Accessibility'),
      ('Pos Consumer'),
      ('Pre Consumer'),
      ('Green Friendly'),
      ('Lead Point');
       
      
DROP TABLE IF EXISTS ims_design_look CASCADE;
CREATE TABLE ims_design_look
(
   description                varchar(30)      PRIMARY KEY
);
   

INSERT INTO ims_design_look VALUES
      ('Wood'),
      ('Travertine');  
      
 
DROP TABLE IF EXISTS ims_design_style CASCADE;
CREATE TABLE ims_design_style
(
   description                varchar(30)      PRIMARY KEY
);
   
INSERT INTO ims_design_style VALUES
      ('Contemporary'),
      ('Modern'),
      ('Eclectic'),
      ('Traditional'),
      ('Asin'),
      ('Beach Style'),
      ('Craftsman'),
      ('Industrial'),
      ('Mediterranean'),
      ('Midcentury'),
      ('Rustic'),
      ('Tropical');
      

DROP TABLE IF EXISTS ims_surface_application CASCADE;
CREATE TABLE ims_surface_application
(
   description                varchar(30)        PRIMARY KEY
);
   

INSERT INTO ims_surface_application VALUES
      ('Ink Jet'),
      ('Roto'),
      ('Silk'),
      ('Hand Painted/Crafted');
      
      
DROP TABLE IF EXISTS ims_surface_finish CASCADE;
CREATE TABLE ims_surface_finish
(
   description                varchar(30)    PRIMARY KEY
);
   

INSERT INTO ims_surface_finish VALUES
      ('Antiquated'),
      ('Filled & Honed'),
      ('Fully Gauged'),
      ('Leathered'),
      ('Honed'),
      ('Metallic'),
      ('Nat. Clef'),
      ('Polished & Honed'),
      ('Stain'),
      ('Tumbled'),
      ('Textured');

      
DROP TABLE IF EXISTS ims_surface_type CASCADE;
CREATE TABLE ims_surface_type
(
   description                varchar(30)        PRIMARY KEY
);
   

INSERT INTO ims_surface_type VALUES
      ('Abrasive'),
      ('Flamed'),
      ('Glazed'),
      ('Polished'),
      ('Resined'),
      ('Unglazed'),
      ('Vein-Cut'),
      ('Cross Cut');
      
      
 COMMIT;     