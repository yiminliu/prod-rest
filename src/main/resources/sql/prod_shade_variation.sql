DROP TABLE IF EXISTS prod_shade_variation CASCADE;
CREATE TABLE prod_shade_variation
(
  code                       varchar(5)     PRIMARY KEY,
  description                varchar(30)
);
   

INSERT INTO prod_shade_variation VALUES
      ('V0', 'N/A'),
      ('V1', 'Low'),
      ('V2', 'Medium'),
      ('V3', 'High'),
      ('V4', 'Extreme');
      
  COMMIT;  
  
           
            
      
           
            
         
            
               
                  
            
           
            
             
            
            
             