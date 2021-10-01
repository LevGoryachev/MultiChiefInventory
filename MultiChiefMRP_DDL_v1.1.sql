CREATE TABLE "warehouse"(
id BIGSERIAL PRIMARY KEY,
internal_doc_num BIGINT
);

CREATE TABLE "bom"(
id BIGSERIAL PRIMARY KEY,
internal_doc_num BIGINT
);

CREATE TABLE "material"(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(80) NOT NULL,
um VARCHAR(25) NOT NULL,
unitweight_kg INTEGER,
notes TEXT,
UNIQUE (name)
);

CREATE TABLE "availability"(
warehouse_id BIGINT REFERENCES "warehouse"(id) ON DELETE CASCADE,
material_id BIGINT REFERENCES "material"(id) ON DELETE SET NULL,
warehouse_qty BIGINT,
PRIMARY KEY(warehouse_id, material_id)
);

CREATE TABLE "bom_item"(
bom_id BIGINT REFERENCES "bom"(id) ON DELETE CASCADE,
material_id BIGINT REFERENCES "material"(id) ON DELETE SET NULL,
bom_qty INTEGER NOT NULL,
PRIMARY KEY(bom_id, material_id),
CHECK (bom_qty >= 0)
);

CREATE TABLE "order"(
id BIGSERIAL,
bom_id BIGINT NOT NULL REFERENCES "bom"(id) ON DELETE SET NULL,
ordertime TIMESTAMP,
posted BOOLEAN NOT NULL DEFAULT false,
sent BOOLEAN NOT NULL DEFAULT false,
status_executed BOOLEAN NOT NULL DEFAULT false,
PRIMARY KEY (bom_id, id),
UNIQUE(id)
);

CREATE TABLE "order_item"(
bom_id BIGINT,
material_id BIGINT,
order_id BIGINT,
FOREIGN KEY  (bom_id, order_id) REFERENCES "order" (bom_id, id) ON DELETE CASCADE,
FOREIGN KEY  (bom_id, material_id) REFERENCES "bom_item" (bom_id, material_id) ON DELETE SET NULL,
PRIMARY KEY (bom_id, material_id, order_id),
status_delivered BOOLEAN NOT NULL DEFAULT false,
order_qty INTEGER NOT NULL,
CHECK (order_qty > 0)
);