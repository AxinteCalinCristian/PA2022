<h2> Laboratory 3 </h2>
<ul>
  <li> <h3> Compulsory </h3> 
    <ul>
      <li> &#9989;  Create an object-oriented model of the problem. You should have at least the following classes: Catalog and Item. The items should have at least a unique identifier, a title and its location. Consider using an interface or an abstract class in order to describe the items in a catalog. </li> 
      <li>  &#9989; Implement the following methods representing commands that will manage the content of the catalog: 
      <ul>
        <li> &#9989; add: adds a new entry into the catalog; </li>
        <li> &#9989; toString: a textual representation of the catalog; </li>
        <li> &#9989; save: saves the catalog to an external file using JSON format; you may use Jackson or other library; </li>
        <li> &#9989; load: loads the catalog from an external file. </li>
        </ul>
      </li> 
    </ul>
  </li>
  <li> <h3> Homework </h3> 
     <ul>
      <li> Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command. </li> 
      <li> Implement the commands load, list, view, report (create the classes AddCommand, ListCommand, etc.).
       <ul>
         <li> list: prints the list of items on the screen; </li>
         <li> view: opens an item using the native operating system application (see the Desktop class); </li>
         <li> report: creates (and opens) an HTML report representing the content of the catalog. </li>
         <li> Use a template engine such as FreeMarker or Velocity, in order to create the HTML report. </li>
         <li> (+1p) Use Apache Tika in order to extract metadata from your catalog items and implement the command info in order to display them. </li>
        </ul>
       </li> 
      <li> The application will signal invalid date or the commands that are not valid using custom exceptions.</li> 
      <li> The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR.</li> 
    </ul>
  </li>
  <li> <h3> Bonus </h3> 
    <ul>
      <li> Suppose there is an official set of concepts (keywords) C, and that each item has a list of such concepts (example of such a classification system). Evolve your model in order to support this new feature. </li> 
      <li> Write an algorithm that determines: 
      <ul>
        <li> the largest set of pairs (item, concept) such that all items and all concepts in this set are distinct. </li>
         <li> the smallest set of pairs (item, concept) such that all items and all concepts are present in at least one pair. </li>
        </ul>
      </li> 
      <li> Create large instances of the problem and test your algorithm. </li> 
    </ul>
  </li>
 </ul>
