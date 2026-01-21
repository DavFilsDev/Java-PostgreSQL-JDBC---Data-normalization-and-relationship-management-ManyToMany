CREATE TYPE unit_type AS ENUM ('PCS', 'KG', 'L');

CREATE TABLE dish_ingredient
(
    id                 serial primary key,
    id_dish            int references dish (id),
    id_ingredient      int references ingredient (id),
    quantity_required  numeric(10, 3),
    unit               unit_type
);

ALTER TABLE ingredient
    DROP COLUMN IF EXISTS id_dish;