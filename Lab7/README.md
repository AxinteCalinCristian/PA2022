<h2> Laboratory 3 </h2>
<ul>
  <li> <h3> Compulsory </h3> 
	Create the following components:
    <ul>
      <li> &#9989; Create an object oriented model of the problem. You may assume that there are 10 tiles for each letter in the alphabet and each letter is worth a random number of points (between 1 and 10). </li> 
      <li> &#9989; Each player will have a name and they must perform in a concurrent manner, extracting repeatedly tokens from the board. </li> 
      <li> &#9989; After each extraction, the player will submit to the board all the letters. </li> 
      <li> &#9989; Simulate the game using a thread for each player. </li> 
   </ul>
  </li>
  <li> <h3> Homework </h3> 
     <ul>
      <li> Use the following number of tiles for each letter: A-9, B-2, C-2, D-4, E-12, F-2, G-3, H-2, I-9, J-1, K-1, L-4, M-2, N-6, O-8, P-2, Q-1, R-6, S-4, T-6, U-4, V-2, W-2, X-1, Y-2, Z-1 </li> 
      <li> Use the following points for the letters:
	<ul>
		<li> (1 point)-A, E, I, O, U, L, N, S, T, R </li>
		<li> (2 points)-D, G </li>
		<li> (3 points)-B, C, M, P </li>
		<li> (4 points)-F, H, V, W, Y </li>
		<li> (5 points)-K </li>
		<li> (8 points)- J, X </li>
		<li> (10 points)-Q, Z </li>
	</ul>
     </li> 
      <li> Create an implementation of a dictionary, using some collection of words. Use an appropriate collection to represent the dictionary. This collection should be large enough; you may use aspell to generate a text file containing English words, or anything similar: WordNet, dexonline, etc. </li> 
      <li> Implement the scoring and determine who the winner is at the end of the game. </li> 
      <li> Make sure that players wait their turns, using a wait-notify approach. </li> 
      <li> Implement a timekeeper thread that runs concurrently with the player threads, as a daemon. This thread will display the running time of the game and it will stop the game if it exceeds a certain time limit. </li> 
    </ul>
  </li>
  <li> <h3> Bonus </h3> 
    <ul>
      <li> The dictionay must offer the possibility to search not only for a word, but for words which start with a given prefix (lookup). </li> 
      <li> Implement the prefix search (for a classical collection) using a multi-threaded approach (parallel streams, ForkJoin, etc). </li> 
      <li> Represent the words in the dictionary as a prefix tree or directed acyclic word graph for memory efficiency and prefix lookups. </li> 
      <li> Compare the running time for the lookup operation between a standard collection and the one above (prefix tree or dawg). </li> 
    </ul>
  </li>
 </ul>
