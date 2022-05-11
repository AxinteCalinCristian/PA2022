<h2> Laboratory 9 </h2>
<ul>
  <li> <h3> Compulsory </h3> 
    <ul>
      <li> &#9989; Create a persistence unit (use EclipseLink or Hibernate or other JPA implementation). </li> 
      <li> &#9989; Verify the presence of the persistence.xml file in your project. Make sure that the driver for EclipseLink or Hibernate was added to to your project classpath (or add it yourself). </li> 
      <li> &#9989; Define the entity classes for your model (at least one) and put them in a dedicated package. You may use the IDE support in order to generate entity classes from database tables.</li> 
      <li> &#9989; Create a singleton responsible with the management of an EntityManagerFactory object. </li> 
      <li> &#9989; Define repository clases for your entities (at least one). They must contain the following methods:
     	<ul>
		<li> create - receives an entity and saves it into the database; </li>
		<li> findById - returns an entity based on its primary key; </li>
		<li> findByName - returns a list of entities that match a given name pattern. Use a named query in order to implement this method. </li>
	</ul>
      </li>
      <li> &#9989; Test your application. </li>  
   </ul>
  </li>
  <li> <h3> Homework </h3> 
     <ul>
      <li> &#9989; Create all entity classes and repositories. Implement properly the one-to-many relationships. </li> 
      <li> &#9989; Create a generic AbstractRepository using generics in order to simplify the creation of the repository classes. You may take a look at the CrudRepository interface from Spring Framework. </li> 
      <li> &#9989; Insert, using JPA, a large number of cities in the database and log the execution time of your JPQL queries. </li> 
      <li> &#9989; (+1p) Assume each city has a new property, its population. Use a constraint solver, such as Choco solver, OptaPlanner or ORTools, in order to find a set of cities having names that start with the same letter, the total sum of their population is between two given bounds and they are from different countries. </li> 
    </ul>
  </li>
  <li> <h3> Bonus </h3> 
    <ul>
      <li> &#9989; Implement properly the many-to-many sisterhood relationship. </li> 
      <li> &#9989; Implement both the JDBC and JPA implementations and use an AbstractFactory in order to create the DAO objects (the repositories). </li> 
      <li> &#9989; The application will use JDBC or JPA depending on a parameter given in an initialization file. </li> 
      <li> &#9989; You may also use an IoC container in order to inject the DAO implementations. </li>
    </ul>
	 <br> >>> For local config: <br>
	  >>> In << ./bonus/resources/application.properties >> change the following props: 
	  <ul>
		  <li> db_implementation_type= << jpa/jdbc >> </li>
		  <li> jdbc_database_url= << jdbc_db_url >> </li>
		  <li> jdbc_database_user= << username >> </li>
		  <li> jdbc_database_pass= << password >> </li>
	  </ul>
  </li>
 </ul>
