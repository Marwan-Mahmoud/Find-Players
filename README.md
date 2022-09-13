# Find players
#### Problem Statement
Three weeks ago, MyOwnBusiness Inc. received an urgent call from the IIHF (International Ice Hockey Federation) requesting a system to raise an alarm to the referee when there are too many players from the same team inside the rink. The system will be composed of three parts:
- A digital camera in the ceiling to take photos of the rink every second.
- A software module to extract the position of each player from the photo taken by the digital camera.
- A software module to count the number of players from the same team inside the hockey rink.

The team has just finished the module to count the number of players inside the hockey rink, so now it is time to implement the module to extract the players' positions from the photo taken by the digital camera.  
The photo taken by the camera is given as a String[], where the x-th character of the y-th element is the color of the 2 x 2 square whose upper-left corner is at (2*x, 2*y).  
Colors are either uppercase letters ('A'-'Z') or digits ('0'-'9'). Uppercase letters represent terrain features (floor, chairs, spectators, etc.) and each digit X represents the color of the uniform used by the X-th team.  
Two squares A and B belong to the same object if and only if there exists a chain of squares where the first square is A, the last square is B, each pair of consecutive squares in the chain shares a common edge and all the squares in the chain have the same color.  
The position of an object C is the center of its bounding box, where its bounding box is defined as the smallest axis-aligned box that contains all the object’s squares.  
An object’s area is defined as the sum of the areas of all the squares that compose the object.  
An object is a player from the i-th team if and only if it is colored with the digit i and its area is at least threshold.  
Return a java.awt.Point[] containing all the players in the photo from the k-th team.  
Each element should represent a single player and be formatted java.awt.Point(X,Y), where (X,Y) is the center of the player’s bounding box, and X and Y have no extra leading zeros.  
Sort the players in increasing order by x-coordinate. Sort players with the same x-coordinate in increasing order by y-coordinate.  

### Input Format
First line contains a comma-separated list of the board dimenstions(rows, colums). The board rows are then printed in order. Next line contains the team indetifier. Last line contains the minimum area for an element.

### Output Format
Comma-separated list of points of team locations.

#### Sample Input 1
15, 15  
8D88888J8L8E888  
88NKMG8N8E8JI88  
888NS8EU88HN8EO  
LUQ888A8TH8OIH8  
888QJ88R8SG88TY  
88ZQV88B88OUZ8O  
FQ88WF8Q8GG88B8  
8S888HGSB8FT8S8  
8MX88D88888T8K8  
8S8A88MGVDG8XK8  
M88S8B8I8M88J8N  
8W88X88ZT8KA8I8  
88SQGB8I8J88W88  
U88H8NI8CZB88B8  
8PK8H8T8888TQR8  
8  
9

#### Sample Output 1
[(1, 17), (3, 3), (3, 10), (3, 25), (5, 21), (8, 17), (9, 2), (10, 9), (12, 23), (17, 16), (18, 3), (18, 11), (18, 28), (22, 20), (23, 26), (24, 15), (27, 2), (28, 26), (29, 16)]

#### Sample Input 2
6, 8  
33JUBU33  
3U3O4433  
O33P44NB  
PO3NSDP3  
VNDSD333  
OINFD33X  
3  
16

#### Sample Output 2
[(4, 5), (13, 9), (14, 2)]
