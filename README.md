# MultiChiefMRP
<p>MultiChief is an experimental microservice-based ERP system for construction industry. <p/>
<p><b>MultiChiefMRP</b> is a microservice that is responsible for preparing data (DTO) for MultiChiefMain (main service <a href="https://github.com/LevGoryachev/MultiChiefMain">MultiChiefMain</a>).</p> 
<p>Generally, the whole MultiChief web-app is intended for on-line and off-line interaction between departments and employees.</p>
<p><b>Summary: Java 11, Spring-Boot, Spring Security, Spring Data JPA (Hibernate)</b></p>
<p><b>Database: PostgreSQL</b></p>

<p>Database structure in files:</p>
<ul>
<li><b>MultiChiefMRP_DDL_v1.1.sql</b></li>
</ul>

<h3>Database scheme</h3>

![MultiChiefMRP_DB_v1_1](https://user-images.githubusercontent.com/61917893/134813791-eff22c25-8e58-47b9-ae89-4bcde96800f2.jpg)


<p>Descriptions:</p>

<ul>
<li>bomOld - bill of materials</li>
<li>qty - quantity (warehouse_qty, bom_qty, order_qty)</li>
</ul>

<p>MultiChief (ERP). License: GNU GPL v3. Copyright (C) 2021 Lev Goryachev.</p>
