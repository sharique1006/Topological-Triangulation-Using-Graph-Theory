# Topological-Triangulation-Using-Graph-Theory
Designed algorithms to handle and store 3D shapes using data structures backed by Graph Theory concepts. Developed an eﬃcient program to process properties like Neighbors, Centroid, Diameter, Connected Components, Boundary edges, etc.

*********************
We need to implement a data structure similar to graphs and make different functions related to a graph
Since no imports were allowed i have implemented my own ArrayList to store the vertices, edges & triangles made in a graph

Classes in src :
1. Driver.java
2. ShapeInterface.java
3. Shape.java
4. TriangleInterface.java
5. Triangle.java
6. EdgeInterface.java
7. Edge.java
8. PointInterface.java
9. Point.java
10. AList.java

**********************
1. Driver.java:
This runs all other implemented classes & is not supposed to be changed.
The Input file is read in the driver by args[0] & correspondinly by matching the case it calls differernt functions of 
Shape class & throws exception accordingly if the given input is absurd or if file is not found.

2. Point.java:
Point class implements the Point Interface. This class is used to make the points i.e X, Y, Z co-ordinates of the
triangle.It has functions getX(), getY(), getZ() which returns the x, y, z co-ordinates of the triangle respectively
There is also a compareTo function which returns 1 if two are points are same and 0 otherwise.
Another method called getXYZcoordinate returns a float[] of x,y,z co-ordinates.

3. Edge.java:
Edge class implements EdgeInterface.java. It stores the two edges of the triangle as first and second and also has a 
Point[] which stores two points which are the endpoints of that edge. It has a method called edgeEndPoints() which returns the endpoints first & 
second of an edge.
It has also has a compareTo function which returns 1 if two edges are same and 0 otherwise.

4. Triangle.java:
Triangle implements TriangleInterface.java. It stores the three points of any triangle & also the three edges corresponding to 
that triangle i.e AB, BC, CA. It also has a static arrivaltime which increases by 1 evertime a triangle object is created.
arrivaltime helps to sort the triangle in order of their insertion as required in the queries.
It has methods named triangle_coord() which returns a 3 size array which has the three points A,B, c of  a triangle.
Another function called triangle_edges() which returns an array of Edge of size 3 which stores the 3 edges of the triangle AB, BC, CA

5. Shape.java
Shape class implements ShapeInterface.java. This class is like the heart of the entire code. An object of the shape class has been created
in the Driver which ius in turn used to call the different functions of the ShapeInterface.

-----All Queries asked / Methods in Shape.java-------

1. ADD_Triangle(float[] triangle_coordinates):
In this function it was to be checked if the given coordinates form a valid triangle or not. If they form a valid one return true else 
return false. To check whether a given triangle is valid or not i have made a helper function called crossProduct(float vect_A[], float vect_B[])
Using two sides of the triangle we calculate the cross product. If all i, j, k components of the cross Product are zero then area of the triangle is zero
& a triangle with zero area does not exist & hence accordingly return true or false.
If it is a valid triangle then add that triangle to AList of triangles calles alltriangles which is static.


2. TYPE_MESH():
In this query we are required to check if at this stage after adding all the past triangles what kind of mesh do they form if it is proper, semi or improper
We start checking all the triangles one by one present in alltriangles and check if the edge of a given triangle is present for how many times in all other triangles.
If all the edges come twice we say it is proper mesh & return 1, if all edges appear less than two times and there is atleast ine triangle we return 2 as it is semi proper mesh,
and if there is any triangle which appears in more than 2 triangles return 3 as it is improper mesh.

3. NEIGHBORS_OF_TRIANGLE(float[] triangle_coord):
First check if a triangle with co-ordinates exist in alltriangles. If it doesnot return the neighbours as null and if it does exist
then make 3 edges e1, e2, e3 corresponding to the triangle and start iterating over alltriangles and find all triangles which have one of their edges as e1, e2, e3
and return all those triangles as neighbors of the given triangle by storing them in an array of TriangleInterface.
In case their are no other triangles which have their edges as e1, e2, e3 in that case return null.

4. NEIGHBORS_OF_POINT(float[] point_coordinates):
Start iterating over all triangles and check if the co-ordinates of the given point matches withany point of a triangle present if alltriangles by using the compareTo function made in Point class
If the point is not present return neighbors of that point as null as that point is not even present in all triangles list.
If it is present then as it is found store the other 2 points of that triangle in a list. keep doing this for all triangles and add in the list only if that point was not present in that list as we do not want a point to be present
repeated no of times. Finally return an array of all the points in list.

5. EDGE_NEIGHBORS_OF_POINT(float[] point_coordinates):
Simiarly as previous query first check if the point matches with a point in a triangle then store the two edges of the a triangle in which that point is present by comparing the first and second and if the point matches with any of the first and second, 
store that edge, there will only be two such edges in each triangle corresponding to each point keep storing them in a list and finally return it as an array of edges.
