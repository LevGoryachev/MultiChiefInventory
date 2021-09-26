CREATE TABLE "warehouse"(
id SERIAL PRIMARY KEY,
internal_doc_num INTEGER
);

CREATE TABLE "availability"(
warehouse_id INTEGER REFERENCES "warehouse"(id) ON DELETE CASCADE,
material_id INTEGER REFERENCES "material"(id) ON DELETE SET NULL,
warehouse_qty INTEGER,
PRIMARY KEY(warehouse_id, material_id),
);

CREATE TABLE "bom"(
id SERIAL PRIMARY KEY,
internal_doc_num INTEGER
);

CREATE TABLE "material"(
id SERIAL PRIMARY KEY,
name VARCHAR(80) NOT NULL,
um VARCHAR(25) NOT NULL,
unitweight_kg INTEGER,
notes TEXT,
UNIQUE (name)
);

CREATE TABLE "bom_item"(
bom_id INTEGER REFERENCES "bom"(id) ON DELETE CASCADE,
material_id INTEGER REFERENCES "material"(id) ON DELETE SET NULL,
bom_qty INTEGER NOT NULL,
PRIMARY KEY(bom_id, material_id),
CHECK (bom_qty >= 0)
);

CREATE TABLE "order"(
id SERIAL,
bom_id INTEGER NOT NULL REFERENCES "bom"(id) ON DELETE SET NULL,
ordertime TIMESTAMP,
posted BOOLEAN NOT NULL DEFAULT false,
sent BOOLEAN NOT NULL DEFAULT false,
status_executed BOOLEAN NOT NULL DEFAULT false,
PRIMARY KEY (bom_id, id),
UNIQUE(id)
);

CREATE TABLE "order_item"(
bom_id INTEGER,
material_id INTEGER,
order_id INTEGER,
FOREIGN KEY  (bom_id, order_id) REFERENCES "order" (bom_id, id) ON DELETE CASCADE,
FOREIGN KEY  (bom_id, material_id) REFERENCES "bom_item" (bom_id, material_id) ON DELETE SET NULL,
PRIMARY KEY (bom_id, material_id, order_id),
status_delivered BOOLEAN NOT NULL DEFAULT false,
order_qty INTEGER NOT NULL,
CHECK (order_qty > 0)
);