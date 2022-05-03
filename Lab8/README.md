<h2> Laboratory 8 </h2>
<ul>
  <li> <h3> Compulsory </h3> 
    <ul>
      <li> &#9989; Create a relational database using any RDBMS (Oracle, Postgres, MySql, Java DB, etc.). </li> 
      <li> &#9989; Write an SQL script that will create the following tables:
	<ul>
		<li> countries: id, name, code, continent </li>
		<li> continents: id, name </li>
	</ul>
      </li> 
      <li> &#9989; Update pom.xml, in order to add the database driver to the project libraries. </li> 
      <li> &#9989; Create a singleton class in order to manage a connection to the database. </li> 
      <li> &#9989; Create DAO classes that offer methods for creating countries and continents, and finding them by their ids and names; </li>
      <li> &#9989; Implement a simple test using your classes. </li>  
   </ul>
  </li>
  <li> <h3> Homework </h3> 
     <ul>
      <li> &#9989; Create the necessary table in order to store cities in your database. A city may contain: id, country, name, capital(0/1), latitude, longitude </li> 
      <li> &#9989; Create an object-oriented model of the data managed by the application. </li> 
      <li> &#9989; Create a tool to import data from a real dataset: World capitals gps or other. </li> 
      <li> &#9989; Display the distances between various cities in the world. </li> 
      <li> &#9989; (+1p) Create a 2D map (using Swing or JavaFX) and draw on it the cities at their corresponding locations. </li> 
    </ul>
  </li>
  <li> <h3> Bonus </h3> 
    <ul>
      <li> &#9989; Use a connection pool in order to manage database connections, such as C3PO, HikariCP or Apache Commons DBCP. </li> 
      <li> &#9989; Two cities are sisters (or twins) if they have a form of legal or social agreement between for the purpose of promoting cultural and commercial ties. <br>
       Using a ThreadPoolExecutor create and insert into your database a large number of fake cities (>=1000) and random sister relationships among them (the sisterhood probability should be low). </li> 
      <li> &#9989; Using Bron Kerbosch algorithm determine the sets of cities (inclusionwise maximal, with at least 3 elements) that are all sisters with each other. </li> 
    </ul>
  </li>
 </ul>
