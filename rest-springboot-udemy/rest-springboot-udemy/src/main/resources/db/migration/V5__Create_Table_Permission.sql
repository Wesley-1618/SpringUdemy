CREATE TABLE IF NOT EXISTS public.permission (
  id bigserial NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)