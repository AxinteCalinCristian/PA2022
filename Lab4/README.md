<h2> Laboratory 3 </h2>
<ul>
  <li> <h3> Compulsory </h3> 
    <ul>
      <li> &#9989; Create an object-oriented model of the problem. You should have at least the following classes Network, Node, Computer, Router, Switch. The natural ordering of the nodes is given by their names.  </li> 
      <li>  &#9989; Create the interfaces Identifiable and Storage. The classes above must implement these interfaces accordingly.  </li> 
      <li>  &#9989; The Network class will contain a List of nodes.  </li> 
      <li>  &#9989; Create and print all the nodes in the network (without the time costs).  </li> 
    </ul>
  </li>
  <li> <h3> Homework </h3> 
     <ul>
      <li> &#9989; Each node will contain a Map representing the time costs. Create and print the complete network in the example.  </li> 
      <li> &#9989; Create a default method in the interface Storage, that is able to return the storage capacity in other units of storage (megabyte, kilobyte, byte). </li> 
      <li> &#9989; In the Network class, create a method to display the nodes that are identifiable, sorted by their addresses. </li> 
      <li> &#9989; Implement an efficient agorithm to determine all the shortests times required for data packets to travel from an identifiable node to another. </li> 
    </ul>
  </li>
  <li> <h3> Bonus </h3> 
    <ul>
      <li> &#9989; In addition to the time cost, each link between two network nodes has a probability of failure. </li> 
      <li> &#9989; Implement an efficient agorithm to determine the safest route for a packet to travel between two given nodes. </li> 
      <li> &#9989; Generate random network instances with known optimum and test your algorithm using JUnit or other framework. </li> 
    </ul>
  </li>
 </ul>
