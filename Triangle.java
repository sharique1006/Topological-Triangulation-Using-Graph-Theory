public class Triangle implements TriangleInterface {

    Point[] PTriangle;
    Point A, B, C;
    Edge AB, BC, CA;
    Edge[] edg = new Edge[3];
    Point[] poin = new Point[3];
    public static int arrivaltime = 0;
    int time = 0;
    boolean visited;

    public Triangle (float[] pTriangle) {
        this.A = new Point(pTriangle[0], pTriangle[1], pTriangle[2]);
        this.B = new Point(pTriangle[3], pTriangle[4], pTriangle[5]);
        this.C = new Point(pTriangle[6], pTriangle[7], pTriangle[8]);
        AB = new Edge(this.A, this.B);
        BC = new Edge(this.B, this.C);
        CA = new Edge(this.C, this.A);
        poin[0] = this.A;
        poin[1] = this.B;
        poin[2] = this.C;
        edg[0] = this.AB;
        edg[1] = this.BC;
        edg[2] = this.CA;
        arrivaltime++;
        this.time = arrivaltime;
        this.visited = false;
    }

    @Override
    public PointInterface[] triangle_coord() {
        return this.poin;
    }
    public Edge[] triangle_edges() {
        return this.edg;
    } 
}