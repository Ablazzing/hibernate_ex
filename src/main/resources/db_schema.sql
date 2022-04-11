-- drop table public.entries;
-- drop table public.holidays;
-- drop table public.holidays_type;
-- drop table public.description_changers;
-- drop table public.categories_rename;
-- drop table public.categories;
-- drop table public.users;


CREATE TABLE IF NOT EXISTS public.users  (
                         id SERIAL PRIMARY KEY,
                         username VARCHAR(50) UNIQUE,
                         password VARCHAR(50),
                         email VARCHAR(50) UNIQUE,
                         role VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS public.categories (
                                                 id SERIAL PRIMARY KEY,
                                                 name VARCHAR(50),
                                                 user_id INTEGER NOT NULL,
                                                 foreign key (user_id) references public.users(id)
);


CREATE TABLE IF NOT EXISTS public.holidays_type (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(50) UNIQUE,
                               category_id INTEGER NOT NULL,
                               foreign key (category_id) references public.categories(id)
    );

CREATE TABLE IF NOT EXISTS public.holidays (
                            id SERIAL PRIMARY KEY,
                            date_start DATE,
                            date_end DATE,
                            user_id INTEGER NOT NULL,
                            holiday_type_id INTEGER NOT NULL,
                            foreign key (user_id) references public.users(id),
                            foreign key (holiday_type_id) references public.holidays_type(id)
    );


CREATE TABLE IF NOT EXISTS public.entries (
                         id SERIAL PRIMARY KEY,
                         amount DECIMAL,
                         description VARCHAR,
                         entry_date TIMESTAMP,
                         number_operation INTEGER,
                         category_id INTEGER NOT NULL,
                         user_id INTEGER NOT NULL,
                         foreign key (user_id) references public.users(id),
                         foreign key (category_id) references public.categories(id)
    );

CREATE TABLE IF NOT EXISTS public.categories_rename (
                                     id SERIAL PRIMARY KEY,
                                     category_before_id INTEGER NOT NULL,
                                     category_after_id INTEGER NOT NULL,
                                     user_id INTEGER NOT NULL,
                                     foreign key (user_id) references public.users(id),
                                     foreign key (category_before_id) references public.categories(id),
                                     foreign key (category_after_id) references public.categories(id)
    );

CREATE TABLE IF NOT EXISTS public.description_changers (
                                        id SERIAL PRIMARY KEY,
                                        description_pattern VARCHAR(100),
                                        category_id INTEGER NOT NULL,
                                        user_id INTEGER NOT NULL,
                                        foreign key (user_id) references public.users(id),
                                        foreign key (category_id) references public.categories(id)
    );