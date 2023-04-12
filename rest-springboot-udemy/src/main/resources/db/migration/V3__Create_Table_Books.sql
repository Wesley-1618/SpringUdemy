CREATE TABLE IF NOT EXISTS public.books (
  id bigserial NOT NULL,
  author varchar(100) NOT NULL,
  launch_date date NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(100) NOT NULL,
  CONSTRAINT books_pkey PRIMARY KEY (id)
);
