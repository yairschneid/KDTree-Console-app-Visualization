# KDTree-Console-app-Visualization
A console application in java for visualizing a 4-dimention KD tree

Main loop includes 6 options
1. insert a spesific node by keyboard
  The node consists out of 4 integers entered in one string and seperated by space
2. serach for a spesific node in the tree
  the output: the keys of all nodes in the route to the spesific node
3. search by range
  enter a minimum representation of a node
  enter a maximum representation of a node
  the output: all nodes in the tree that are in range
4. random input of nodes
  enter the number of nodes wished to insert to the tree
  enter the boundary of integer for the random function
  the output: the KD tree representation
5. enter nodes from a file
  enter the path (the file must be *.txt)
  the output: the representation of the KD tree
6. exit option
  the output: "Bye Bye"
  
spesifications:
1. the node class is a sub class in the KDTree class
2. every node contains:
  int[] key
  int node number
  Node left, right
3. the printer prints the node number and under the tree a table with each <node_number>:<key>
4. the file must end with no blank lines

thanks to Tehila Bitarov (@tehilbi) for working together on this project!!
