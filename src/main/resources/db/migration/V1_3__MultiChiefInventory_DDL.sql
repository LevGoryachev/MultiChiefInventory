-- MultiChiefInventory, version 1.3, syntax: Postgres

CREATE TABLE "warehouse"(
id BIGSERIAL PRIMARY KEY,
wh_code_number VARCHAR(80) NOT NULL,
description TEXT,
phones VARCHAR(80)
);

CREATE TABLE "bom"(
id BIGSERIAL PRIMARY KEY,
internal_doc_num BIGINT
);

CREATE TABLE "material"(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(80) NOT NULL,
um VARCHAR(25) NOT NULL,
unit_weight_kg INTEGER,
notes TEXT,
UNIQUE (name)
);

CREATE TABLE "availability"(
warehouse_id BIGINT REFERENCES "warehouse"(id) ON DELETE CASCADE,
material_id BIGINT REFERENCES "material"(id) ON DELETE SET NULL,
PRIMARY KEY(warehouse_id, material_id),
warehouse_qty BIGINT,
CHECK (warehouse_qty > 0)
);

CREATE TABLE "bom_item"(
bom_id BIGINT REFERENCES "bom"(id) ON DELETE CASCADE,
material_id BIGINT REFERENCES "material"(id) ON DELETE SET NULL,
bom_qty INTEGER NOT NULL,
PRIMARY KEY(bom_id, material_id),
CHECK (bom_qty >= 0)
);

CREATE TABLE "im_order"(
id BIGSERIAL,
bom_id BIGINT NOT NULL REFERENCES "bom"(id) ON DELETE SET NULL,
im_order_time TIMESTAMP,
posted BOOLEAN NOT NULL DEFAULT false,
sent BOOLEAN NOT NULL DEFAULT false,
status_executed BOOLEAN NOT NULL DEFAULT false,
PRIMARY KEY (bom_id, id),
UNIQUE(id)
);

CREATE TABLE "im_order_item"(
bom_id BIGINT,
material_id BIGINT,
im_order_id BIGINT,
FOREIGN KEY  (bom_id, im_order_id) REFERENCES "im_order" (bom_id, id) ON DELETE CASCADE,
FOREIGN KEY  (bom_id, material_id) REFERENCES "bom_item" (bom_id, material_id) ON DELETE SET NULL,
PRIMARY KEY (bom_id, material_id, im_order_id),
status_delivered BOOLEAN NOT NULL DEFAULT false,
im_order_qty INTEGER NOT NULL,
CHECK (im_order_qty > 0)
);