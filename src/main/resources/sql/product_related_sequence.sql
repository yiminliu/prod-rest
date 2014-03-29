CREATE SEQUENCE prod_application_id_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;
ALTER SEQUENCE prod_application_id_seq OWNER TO prod_application_id;
COMMIT;


CREATE prod_dimension_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;
       
CREATE SEQUENCE prod_note_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;
       
CREATE SEQUENCE prod_test_seq
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;