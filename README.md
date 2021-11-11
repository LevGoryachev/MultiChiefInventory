# MultiChiefInventory
<p><b>MultiChief</b> is an experimental microservice-based ERP system for construction industry (on-line and off-line interaction between departments and employees). <p/>
<p><b>MultiChiefInventory</b> is a microservice that is responsible for preparing data (DTO) for orchestrators (service-modules of <a href="https://github.com/LevGoryachev/MultiChiefMain">MultiChiefMain</a>).</p>
<p><b>Summary: Java 11, Spring-Boot, Spring Security, Spring Data JPA (Hibernate)</b></p>
<p><b>Database: PostgreSQL</b></p>

<p>Database structure in files:</p>
<ul>
<li><b>MultiChiefInventory_DDL_v1.2.sql</b></li>
</ul>

<h3>Database scheme</h3>

![MultiChiefInventory_DB_v1_3](https://user-images.githubusercontent.com/61917893/140859298-2dbf4725-b494-4ee0-b5f3-3c88ef9254ef.jpg)


<p>Descriptions:</p>

<ul>
<li>bom - bill of materials</li>
<li>im_order - internal material imOrder</li>
<li>qty - quantity (warehouse_qty, bom_qty, im_order_qty)</li>
<li>primary key (bom_id, id) could be optional if it were not necessary to relate im order with construction
(as the busines rules provide that bom belongs to only one construction)</li>
</ul>

<p>MultiChief (ERP). License: GNU GPL v3. Copyright (C) 2021 Lev Goryachev.</p>
