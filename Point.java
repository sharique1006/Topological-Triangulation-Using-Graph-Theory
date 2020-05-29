public class Point implements PointInterface {

    float X, Y, Z;
    float[] XYZ;

    public Point(float x, float y, float z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public float getX() {
        return X;
    }
    public float getY() {
        return Y;
    }
    public float getZ() {
        return Z;
    }
    public float[] getXYZcoordinate() {
        XYZ[0] = X;
        XYZ[1] = Y;
        XYZ[2] = Z;
        return XYZ;
    }

    public int compareTo (Point pt) {
        if(X == pt.X && Y == pt.Y && Z == pt.Z) {
            return 1;
        }
        else {
            return 0;
        }
    }
}