# MultiChiefInventory
<p><b>MultiChief</b> is an experimental microservice-based ERP system (implementation for construction industry).<p/>
<p><b>MultiChiefInventory</b> is a microservice that is responsible for preparing data (DTO) for orchestrators (modules of <a href="https://github.com/LevGoryachev/MultiChiefMain">MultiChiefMain</a>).</p>
<p><b>Summary: Java 11, Spring-Boot, Spring Security, Spring Data JPA (Hibernate)</b></p>
<p><b>Database: PostgreSQL</b></p>
<p><b>API:</b> deploy and follow /swagger-ui/</p>


<h3>Architecture</h3>
<p>patterns: multi-layered REST service<p/>
<p><b>MultiChiefInventory</b> is responsible for a certain functions of domain "Inventory" (CRUD operations of subdomains, preparing special DTOs)
and does not interact with both other microservices and clients.</p>

![MultiChiefInventoryDiagram](https://user-images.githubusercontent.com/61917893/141861463-67b93f3e-aaa3-4003-a107-d32012a4e613.jpg)

<p><b>MultiChiefInventory</b> has subdomains:
<ul>
<li>material - catalogue of materials</li>
<li>warehouse - warehouses of company</li>
<li>bom - bill of materials</li>
<li>im_order - internal material imOrder</li>
</ul>

<p>Database structure in files:</p>
<ul>
<li><b>MultiChiefInventory_DDL_v1.3.sql</b></li>
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
