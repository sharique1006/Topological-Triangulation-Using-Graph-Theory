public class Shape implements ShapeInterface {

    public static AList<Triangle> alltriangles = new AList();

    public Shape() {}

    public float[] crossProduct(float vect_A[], float vect_B[]) {
        float cross_P[] = new float[3]; 
        cross_P[0] = vect_A[1] * vect_B[2] - vect_A[2] * vect_B[1]; 
        cross_P[1] = vect_A[0] * vect_B[2] - vect_A[2] * vect_B[0]; 
        cross_P[2] = vect_A[0] * vect_B[1] - vect_A[1] * vect_B[0]; 
        
        return cross_P;
    } 
    
    public boolean ADD_TRIANGLE(float[] triangle_coord) {

        float x1 = triangle_coord[0];
        float y1 = triangle_coord[1];
        float z1 = triangle_coord[2];
        float x2 = triangle_coord[3];
        float y2 = triangle_coord[4];
        float z2 = triangle_coord[5];
        float x3 = triangle_coord[6];
        float y3 = triangle_coord[7];
        float z3 = triangle_coord[8];

        float vect_AB[] = {x2-x1, y2-y1, z2-z1}; 
        float vect_AC[] = {x3-x1, y3-y1, z3-z1};
        
        float vect_Area[] = crossProduct(vect_AB, vect_AC);

        if(vect_Area[0] == 0 && vect_Area[1] == 0 && vect_Area[2] == 0) {
            //System.out.println("FALSE");
            return false;
        }
        else {
            //System.out.println("TRUE");
            Triangle triang = new Triangle(triangle_coord);
            alltriangles.add(triang);
            return true;
        }
    }

    public int searchTriangles(Edge ed) {
        int count = 0;
        for (int i = 0; i < alltriangles.size(); i++) {
            Edge[] edf = alltriangles.get(i).triangle_edges();
            for (int j = 0; j < edf.length; j++) {
                if(ed.compareTo(edf[j]) == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int TYPE_MESH() {
        int checkproper = 0;
        int checkimproper = 0;
        AList<Integer> edgecount = new AList();
        for (int i = 0; i < alltriangles.size(); i++) {
            Triangle t = alltriangles.get(i);
            Edge[] tedg = t.triangle_edges();
            int count1 = searchTriangles(tedg[0]);
            int count2 = searchTriangles(tedg[1]);
            int count3 = searchTriangles(tedg[2]);
            edgecount.add(count1);
            edgecount.add(count2);
            edgecount.add(count3);
        }
        for (int j = 0; j < edgecount.size(); j++) {
            if(edgecount.get(j) == 2) {
                checkproper++;
            }
            if(edgecount.get(j) > 2) {
                checkimproper++;
            }
        }
        if(checkproper == edgecount.size()) {
            return 1;
        }
        else if (checkimproper > 0) {
            return 3;
        }
        else {
            return 2;
        }
    }

    public AList<Triangle> neighbour_triangles(Edge E) {
        AList<Triangle> temp = new AList();
        for (int i = 0; i < alltriangles.size(); i++) {
            Triangle t = alltriangles.get(i);
            Edge[] tedg = t.triangle_edges();
            for (int j = 0; j < tedg.length; j++) {
                if(E.compareTo(tedg[j]) == 1) {
                    temp.add(t);
                    break;
                }
            }
        }

        return temp;
    }

    public boolean triangle_match(Triangle T, float [] t_coord ) {
        if( (T.A.X == t_coord[0] && T.A.Y == t_coord[1] && T.A.Z == t_coord[2] && T.B.X == t_coord[3] && T.B.Y == t_coord[4] && T.B.Z == t_coord[5] && T.C.X == t_coord[6] && T.C.Y == t_coord[7] && T.C.Z == t_coord[8]) 
        || (T.A.X == t_coord[0] && T.A.Y == t_coord[1] && T.A.Z == t_coord[2] && T.B.X == t_coord[6] && T.B.Y == t_coord[7] && T.B.Z == t_coord[8] && T.C.X == t_coord[3] && T.C.Y == t_coord[4] && T.C.Z == t_coord[5])
        || (T.A.X == t_coord[6] && T.A.Y == t_coord[7] && T.A.Z == t_coord[8] && T.B.X == t_coord[3] && T.B.Y == t_coord[4] && T.B.Z == t_coord[5] && T.C.X == t_coord[0] && T.C.Y == t_coord[1] && T.C.Z == t_coord[2])
        || (T.A.X == t_coord[3] && T.A.Y == t_coord[4] && T.A.Z == t_coord[5] && T.B.X == t_coord[0] && T.B.Y == t_coord[1] && T.B.Z == t_coord[2] && T.C.X == t_coord[6] && T.C.Y == t_coord[7] && T.C.Z == t_coord[8])
        || (T.A.X == t_coord[6] && T.A.Y == t_coord[7] && T.A.Z == t_coord[8] && T.B.X == t_coord[0] && T.B.Y == t_coord[1] && T.B.Z == t_coord[2] && T.C.X == t_coord[3] && T.C.Y == t_coord[4] && T.C.Z == t_coord[5])
        || (T.A.X == t_coord[3] && T.A.Y == t_coord[4] && T.A.Z == t_coord[5] && T.B.X == t_coord[6] && T.B.Y == t_coord[7] && T.B.Z == t_coord[8] && T.C.X == t_coord[0] && T.C.Y == t_coord[1] && T.C.Z == t_coord[2])) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean triangle_match2(Triangle T1, AList<Triangle> neigh) {
        int count = 0;
        for (int i = 0; i < neigh.size(); i++) {
            Triangle T2 = neigh.get(i);
            if(T1.A.X == T2.A.X && T1.A.Y == T2.A.Y && T1.A.Z == T2.A.Z && T1.B.X == T2.B.X && T1.B.Y == T2.B.Y && T1.B.Z == T2.B.Z && T1.C.X == T2.C.X && T1.C.Y == T2.C.Y && T1.C.Z == T2.C.Z) {
                count++;
            }
        }
        if(count == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public AList<Triangle> triangle_sort_arrivaltime(AList<Triangle> TD) {
        for (int i = 0; i < TD.size() - 1; i++) {
            for (int j = i+1; j < TD.size(); j++) {
                if(TD.get(i).time > TD.get(j).time) {
                    Triangle temp = TD.get(i);
                    TD.set(i, TD.get(j));
                    TD.set(j, temp);
                }
            }
        }
        return TD;
    }

    public TriangleInterface[] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
        int tempcount = 0;
        for (int i = 0; i < alltriangles.size(); i++) {
            if(triangle_match(alltriangles.get(i), triangle_coord)) {
                tempcount++;
            }
        }
        if(tempcount == 0) {
            //System.out.println("null");
            return null;
        }
        else {
            Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
            Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
            Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
            Edge e1 = new Edge(p1, p2);
            Edge e2 = new Edge(p2, p3);
            Edge e3 = new Edge(p1, p3);
            AList<Triangle> tp1 = neighbour_triangles(e1);
            AList<Triangle> tp2 = neighbour_triangles(e2);
            AList<Triangle> tp3 = neighbour_triangles(e3);
            AList<Triangle> neighbourtriangles = new AList();
            for (int i = 0; i < tp1.size(); i++) {
                if(!(triangle_match(tp1.get(i), triangle_coord))) {
                    neighbourtriangles.add(tp1.get(i));
                }
            }
            for (int i = 0; i < tp2.size(); i++) {
                if(triangle_match2(tp2.get(i), neighbourtriangles) == false && triangle_match(tp2.get(i), triangle_coord) == false) {
                    neighbourtriangles.add(tp2.get(i));
                }
            }
            for (int i = 0; i < tp3.size(); i++) {
                if(triangle_match2(tp3.get(i), neighbourtriangles) == false && triangle_match(tp3.get(i), triangle_coord) == false) {
                    neighbourtriangles.add(tp3.get(i));
                }
            }
            neighbourtriangles = triangle_sort_arrivaltime(neighbourtriangles);
            /*for (int i = 0; i < neighbourtriangles.size(); i++) {
                Triangle TN = neighbourtriangles.get(i);
                System.out.println("[(" + TN.A.X + " " + TN.A.Y + " " + TN.A.Z  + "), (" + TN.B.X + " " + TN.B.Y + " " + TN.B.Z + "), (" + TN.C.X + " " + TN.C.Y + " " + TN.C.Z + ")]");
            }*/
            TriangleInterface[] NEIGHBOUR_T = new TriangleInterface[neighbourtriangles.size()];
            for (int i = 0; i < neighbourtriangles.size(); i++) {
                NEIGHBOUR_T[i] = neighbourtriangles.get(i);
            }
            if(NEIGHBOUR_T.length == 0) {
                //System.out.println("null");
                return null;
            }
            else {
                return NEIGHBOUR_T;
            }
        }
    }

    public boolean neigh_point_present(Point P, AList<Point> lpt) {
        for (int i = 0; i < lpt.size(); i++) {
            if(P.compareTo(lpt.get(i)) == 1) {
                return true;
            }
        }
        return false;
    }

    public PointInterface[] NEIGHBORS_OF_POINT(float [] point_coordinates) {
        int tcount = 0;
        AList<Point> neighbour_point = new AList();
        Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
        for (int i = 0; i < alltriangles.size(); i++) {
            Point[] temppoint = (Point[]) alltriangles.get(i).triangle_coord();
            if(P.compareTo(temppoint[0]) == 1) {
                if(!neigh_point_present(temppoint[1], neighbour_point)) {
                    neighbour_point.add(temppoint[1]);
                }
                if(!neigh_point_present(temppoint[2], neighbour_point)) {
                    neighbour_point.add(temppoint[2]);
                }
                tcount++;
            }
            else if(P.compareTo(temppoint[1]) == 1) {
                if(!neigh_point_present(temppoint[0], neighbour_point)) {
                    neighbour_point.add(temppoint[0]);
                }
                if(!neigh_point_present(temppoint[2], neighbour_point)) {
                    neighbour_point.add(temppoint[2]);
                }
                tcount++;
            }
            else if (P.compareTo(temppoint[2]) == 1){
                if(!neigh_point_present(temppoint[0], neighbour_point)) {
                    neighbour_point.add(temppoint[0]);
                }
                if(!neigh_point_present(temppoint[1], neighbour_point)) {
                    neighbour_point.add(temppoint[1]);
                }
                tcount++;
            }
        }
        if(tcount == 0) {
            return null;
        }
        else {
            /*for (int i = 0; i < neighbour_point.size(); i++) {
                Point PR = neighbour_point.get(i);
                System.out.println("[(" + PR.X + " " + PR.Y + " " + PR.Z + ")]");
            }*/
            PointInterface[] NEIGHBOUR_P = new PointInterface[neighbour_point.size()];
            for (int i = 0; i < neighbour_point.size(); i++) {
                NEIGHBOUR_P[i] = neighbour_point.get(i);
            }
            return NEIGHBOUR_P;
        }
    }

    public boolean neigh_edge(Edge E, AList<Edge> EDP) {
        for (int i = 0; i < EDP.size(); i++) {
            if(E.compareTo(EDP.get(i)) == 1) {
                return true;
            }
        }
        return false;
    }

    public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
        int tcount = 0;
        AList<Edge> edge_neighbour_point = new AList();
        Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
        for (int i = 0; i < alltriangles.size(); i++) {
            Point[] temppoint = (Point[]) alltriangles.get(i).triangle_coord();
            Edge[] edp = alltriangles.get(i).triangle_edges();
            if(P.compareTo(temppoint[0]) == 1 || P.compareTo(temppoint[1]) == 1 || P.compareTo(temppoint[2]) == 1) {
                if(P.compareTo(edp[0].first) == 1 || P.compareTo(edp[0].second) == 1)  {
                    if(!neigh_edge(edp[0], edge_neighbour_point)) {
                        edge_neighbour_point.add(edp[0]);
                    }
                }
                if(P.compareTo(edp[1].first) == 1 || P.compareTo(edp[1].second) == 1)  {
                    if(!neigh_edge(edp[1], edge_neighbour_point)) {
                        edge_neighbour_point.add(edp[1]);
                    }
                }
                if(P.compareTo(edp[2].first) == 1 || P.compareTo(edp[2].second) == 1)  {
                    if(!neigh_edge(edp[2], edge_neighbour_point)) {
                        edge_neighbour_point.add(edp[2]);
                    }
                }
                tcount++;
            }
        }
        if(tcount == 0) {
            return null;
        }
        else {
            /*for (int i = 0; i < edge_neighbour_point.size(); i++) {
                Edge mn = edge_neighbour_point.get(i);
                System.out.println("[(" + mn.first.X + " " + mn.first.Y + " " + mn.first.Z + "), (" + mn.second.X + " " + mn.second.Y + " " + mn.second.Z + ")]");
            }*/
            EdgeInterface[] NEIGHBOUR_EP = new EdgeInterface[edge_neighbour_point.size()];
            for (int i = 0; i < edge_neighbour_point.size(); i++) {
                NEIGHBOUR_EP[i] = edge_neighbour_point.get(i);
            }
            return NEIGHBOUR_EP;
        }
    }

    public TriangleInterface[] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates) { 
        int tcount = 0;
        AList<Triangle> triangle_neighbour_point = new AList();
        Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
        for (int i = 0; i < alltriangles.size(); i++) {
            Point[] temppoint = (Point[]) alltriangles.get(i).triangle_coord();
            if(P.compareTo(temppoint[0]) == 1 || P.compareTo(temppoint[1]) == 1 || P.compareTo(temppoint[2]) == 1) {
                if(!triangle_match2(alltriangles.get(i) , triangle_neighbour_point)) {
                    triangle_neighbour_point.add(alltriangles.get(i));
                }
            }
            tcount++;
        }
        if(tcount == 0) {
            return null;
        }
        else {
            triangle_neighbour_point = triangle_sort_arrivaltime(triangle_neighbour_point);
            /*for (int i = 0; i < triangle_neighbour_point.size(); i++) {
                Triangle TN = triangle_neighbour_point.get(i);
                System.out.println("[(" + TN.A.X + " " + TN.A.Y + " " + TN.A.Z  + "), (" + TN.B.X + " " + TN.B.Y + " " + TN.B.Z + "), (" + TN.C.X + " " + TN.C.Y + " " + TN.C.Z + ")]");
            }*/
            TriangleInterface[] NEIGHBOUR_TP = new TriangleInterface[triangle_neighbour_point.size()];
            for (int i = 0; i < triangle_neighbour_point.size(); i++) {
                NEIGHBOUR_TP[i] = triangle_neighbour_point.get(i);
            } 
            return NEIGHBOUR_TP;
        }
    }

    public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
        for (int j = 0; j < alltriangles.size(); j++) {
            if(triangle_match(alltriangles.get(j), triangle_coord)) {
                Edge[] EDT = alltriangles.get(j).triangle_edges();
                /*for (int i = 0; i < EDT.length; i++) {
                    Edge mn = EDT[i];
                    System.out.println("[(" + mn.first.X + " " + mn.first.Y + " " + mn.first.Z + "), (" + mn.second.X + " " + mn.second.Y + " " + mn.second.Z + ")]");
                }*/
                return EDT;
            }
        }
        return null;
    }

    public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
        for (int j = 0; j < alltriangles.size(); j++) {
            if(triangle_match(alltriangles.get(j), triangle_coord)) {
                Point[] PNV = (Point[]) alltriangles.get(j).triangle_coord();
                /*for (int i = 0; i < PNV.length; i++) {
                    Point PR = PNV[i];
                    System.out.println("[(" + PR.X + " " + PR.Y + " " + PR.Z + ")]");
                }*/
                return PNV;
            }
        }
        //System.out.println("null");
        return null;
    }

    public boolean T_hasEdge (Triangle TRI, Edge EDC) {
        Edge[] EDX = TRI.triangle_edges();
        for (int i = 0; i < EDX.length; i++) {
            if(EDC.compareTo(EDX[i]) == 1) {
                return true;
            }
        }
        return false;
    }

    public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates) {
        int trcount = 0;
        AList<Triangle> triangle_neigh_edge = new AList();
        Point p1 = new Point(edge_coordinates[0], edge_coordinates[1], edge_coordinates[2]);
        Point p2 = new Point(edge_coordinates[3], edge_coordinates[4], edge_coordinates[5]);
        Edge ED = new Edge(p1, p2);
        for (int i = 0; i < alltriangles.size(); i++) {
            Triangle TR = alltriangles.get(i);
            if(T_hasEdge(TR, ED)) {
                triangle_neigh_edge.add(TR);
                trcount++;
            }
        } 
        if(trcount == 0) {
            //System.out.println("null");
            return null;
        }
        else {
            triangle_neigh_edge = triangle_sort_arrivaltime(triangle_neigh_edge);
            /*for (int i = 0; i < triangle_neigh_edge.size(); i++) {
                Triangle TN = triangle_neigh_edge.get(i);
                System.out.println("[(" + TN.A.X + " " + TN.A.Y + " " + TN.A.Z  + "), (" + TN.B.X + " " + TN.B.Y + " " + TN.B.Z + "), (" + TN.C.X + " " + TN.C.Y + " " + TN.C.Z + ")]");
            }*/
            TriangleInterface[] TNE = new TriangleInterface[triangle_neigh_edge.size()];
            for (int i = 0; i < triangle_neigh_edge.size(); i++) {
                TNE[i] = triangle_neigh_edge.get(i);
            }
            return TNE;
        }
    }

    public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates) {
        AList<Triangle> incident_triangle = new AList();
        int tcount = 0;
        Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
        for (int i = 0; i < alltriangles.size(); i++) {
            Point[] temppoint = (Point[]) alltriangles.get(i).triangle_coord();
            if(P.compareTo(temppoint[0]) == 1 || P.compareTo(temppoint[1]) == 1 || P.compareTo(temppoint[2]) == 1) {
                if(!triangle_match2(alltriangles.get(i) , incident_triangle)) {
                    incident_triangle.add(alltriangles.get(i));
                    tcount++;
                }
            }
        }
        if (tcount == 0) {
            return null;
        }
        else {
            incident_triangle = triangle_sort_arrivaltime(incident_triangle);
            /*for (int i = 0; i < incident_triangle.size(); i++) {
                Triangle TN = incident_triangle.get(i);
                System.out.println("[(" + TN.A.X + " " + TN.A.Y + " " + TN.A.Z  + "), (" + TN.B.X + " " + TN.B.Y + " " + TN.B.Z + "), (" + TN.C.X + " " + TN.C.Y + " " + TN.C.Z + ")]");
            }*/
            TriangleInterface[] ITR = new TriangleInterface[incident_triangle.size()];
            for (int i = 0; i < incident_triangle.size(); i++) {
                ITR[i] = incident_triangle.get(i);
            }
            return ITR;
        }
    }

    public AList<Triangle> Incident_Triangles(float [] point_coordinates) {
        AList<Triangle> incident_triangle = new AList();
        int tcount = 0;
        Point P = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
        for (int i = 0; i < alltriangles.size(); i++) {
            Point[] temppoint = (Point[]) alltriangles.get(i).triangle_coord();
            if(P.compareTo(temppoint[0]) == 1 || P.compareTo(temppoint[1]) == 1 || P.compareTo(temppoint[2]) == 1) {
                incident_triangle.add(alltriangles.get(i));
                tcount++;
            }
        }
        if (tcount == 0) {
            return null;
        }
        else {
            incident_triangle = triangle_sort_arrivaltime(incident_triangle);
            return incident_triangle;
        }
    }

    public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
        int tmcount = 0;
        AList<Triangle> extended_neighbour = new AList();
        AList<Triangle> TItemp1 = new AList();
        AList<Triangle> TItemp2 = new AList();
        AList<Triangle> TItemp3 = new AList();
        float[] p1 = {triangle_coord[0], triangle_coord[1], triangle_coord[2]};
        float[] p2 = {triangle_coord[3], triangle_coord[4], triangle_coord[5]};
        float[] p3 = {triangle_coord[6], triangle_coord[7], triangle_coord[8]};
        for (int j = 0; j < alltriangles.size(); j++) {
            if(triangle_match(alltriangles.get(j), triangle_coord)) {
                TItemp1 = Incident_Triangles(p1);
                TItemp2 = Incident_Triangles(p2);
                TItemp3 = Incident_Triangles(p3);
                tmcount++;
            }
        }
        if(tmcount == 0) {
            return null;
        }
        else {
            for (int i = 0; i < TItemp1.size(); i++) {
                if(!triangle_match2(TItemp1.get(i), extended_neighbour) && !triangle_match(TItemp1.get(i), triangle_coord)) {
                    extended_neighbour.add(TItemp1.get(i));
                }
            }
            for (int i = 0; i < TItemp2.size(); i++) {
                if(!triangle_match2(TItemp2.get(i), extended_neighbour) && !triangle_match(TItemp2.get(i), triangle_coord)) {
                    extended_neighbour.add(TItemp2.get(i));
                }
            }
            for (int i = 0; i < TItemp3.size(); i++) {
                if(!triangle_match2(TItemp3.get(i), extended_neighbour) && !triangle_match(TItemp3.get(i), triangle_coord)) {
                    extended_neighbour.add(TItemp3.get(i));
                }
            }
            extended_neighbour = triangle_sort_arrivaltime(extended_neighbour);
            /*for (int i = 0; i < extended_neighbour.size(); i++) {
                Triangle TN = extended_neighbour.get(i);
                System.out.println("[(" + TN.A.X + " " + TN.A.Y + " " + TN.A.Z  + "), (" + TN.B.X + " " + TN.B.Y + " " + TN.B.Z + "), (" + TN.C.X + " " + TN.C.Y + " " + TN.C.Z + ")]");
            }*/
            TriangleInterface[] ENT = new TriangleInterface[extended_neighbour.size()];
            for (int i = 0; i < extended_neighbour.size(); i++) {
                ENT[i] = extended_neighbour.get(i);
            }
            return ENT;
        }
    }

    public AList<Triangle> TNE_BOUND(Edge EDA) {
        int trcount = 0;
        AList<Triangle> triangle_neigh_edge = new AList();
        for (int i = 0; i < alltriangles.size(); i++) {
            Triangle TR = alltriangles.get(i);
            if(T_hasEdge(TR, EDA)) {
                triangle_neigh_edge.add(TR);
                trcount++;
            }
        } 
        if(trcount == 0) {
            return null;
        }
        else {
            triangle_neigh_edge = triangle_sort_arrivaltime(triangle_neigh_edge);
            return triangle_neigh_edge;
        }
    }

    public AList<Edge> Edge_Sort(AList<Edge> bedg) {
        for (int i = 0; i < bedg.size() - 1; i++) {
            for (int j = i+1; j < bedg.size(); j++) {
                if(bedg.get(i).edge_length > bedg.get(j).edge_length) {
                    Edge btemp = bedg.get(i);
                    bedg.set(i, bedg.get(j));
                    bedg.set(j, btemp);
                }
            }
        }
        return bedg;
    }

    public EdgeInterface [] BOUNDARY_EDGES() {
        AList<Edge> bedge = new AList();
        for (int i = 0; i < alltriangles.size(); i++) {
            Edge[] EDS = alltriangles.get(i).triangle_edges();
            for (int j = 0; j < EDS.length; j++) {
                int countedge = searchTriangles(EDS[j]);
                if(countedge == 1) {
                    bedge.add(EDS[j]);
                }
            }
        }
        if(bedge.size() == 0) {
            //System.out.println("null");
            return null;
        }
        else {
            bedge = Edge_Sort(bedge);
            /*for (int i = 0; i < bedge.size(); i++) {
                Edge edsa = bedge.get(i);
                System.out.println("[(" + edsa.first.X + " " + edsa.first.Y + " " + edsa.first.Z + "),(" + edsa.second.X + " " + edsa.second.Y + " " + edsa.second.Z + ")]");
            }*/
            EdgeInterface[] BE = new EdgeInterface[bedge.size()];
            for (int i = 0; i < bedge.size(); i++) {
                BE[i] = bedge.get(i);
            }
            return BE;
        }
    }

    public AList<Triangle> NEIGH_TRIAN(float [] triangle_coord){
        int tempcount = 0;
        for (int i = 0; i < alltriangles.size(); i++) {
            if(triangle_match(alltriangles.get(i), triangle_coord)) {
                tempcount++;
            }
        }
        if(tempcount == 0) {
            //System.out.println("null");
            return null;
        }
        else {
            Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
            Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
            Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
            Edge e1 = new Edge(p1, p2);
            Edge e2 = new Edge(p2, p3);
            Edge e3 = new Edge(p1, p3);
            AList<Triangle> tp1 = neighbour_triangles(e1);
            AList<Triangle> tp2 = neighbour_triangles(e2);
            AList<Triangle> tp3 = neighbour_triangles(e3);
            AList<Triangle> neighbourtriangles = new AList();
            for (int i = 0; i < tp1.size(); i++) {
                if(!(triangle_match(tp1.get(i), triangle_coord))) {
                    neighbourtriangles.add(tp1.get(i));
                }
            }
            for (int i = 0; i < tp2.size(); i++) {
                if(triangle_match2(tp2.get(i), neighbourtriangles) == false && triangle_match(tp2.get(i), triangle_coord) == false) {
                    neighbourtriangles.add(tp2.get(i));
                }
            }
            for (int i = 0; i < tp3.size(); i++) {
                if(triangle_match2(tp3.get(i), neighbourtriangles) == false && triangle_match(tp3.get(i), triangle_coord) == false) {
                    neighbourtriangles.add(tp3.get(i));
                }
            }
            neighbourtriangles = triangle_sort_arrivaltime(neighbourtriangles);
            if(neighbourtriangles.size() == 0) {
                return null;
            }
            else {
                return neighbourtriangles;
            }
        }
    }

    public AList<Triangle> ConnectedTriangleList(float[] triangle_coord, AList<Triangle> trs) {
        AList<Triangle> c1 = NEIGH_TRIAN(triangle_coord);
        if(c1 != null) {
            for (int i = 0; i < c1.size(); i++) {
                if(!triangle_match(c1.get(i), triangle_coord) && !triangle_match2(c1.get(i), trs)) {
                    trs.add(c1.get(i));
                    Point[] PTY = (Point[])c1.get(i).triangle_coord();
                    float[] t_coord = {PTY[0].X, PTY[0].Y, PTY[0].Z, PTY[1].X, PTY[1].Y, PTY[1].Z,PTY[2].X, PTY[2].Y, PTY[2].Z};
                    ConnectedTriangleList(t_coord, trs);
                }
            }
        }
        trs = triangle_sort_arrivaltime(trs);
        if(trs.size() == 0) {
            return null;
        }
        else {
            return trs;
        }
    }

    public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2) {
        Triangle T1 = new Triangle(triangle_coord_1);
        Triangle T2 = new Triangle(triangle_coord_2);
        boolean x1 = triangle_match2(T1, alltriangles);
        boolean x2 = triangle_match2(T2, alltriangles);

        if(x1 == true && x2 == true) {
            AList<Triangle> T1trs = new AList();
            AList<Triangle> T1_connected = ConnectedTriangleList(triangle_coord_1, T1trs);
            if(T1_connected == null) {
                return false;
            }
            AList<Triangle> T1_connect = new AList();
            for (int i = 0; i < T1_connected.size(); i++) {
                if(!triangle_match(T1_connected.get(i), triangle_coord_1)) {
                    T1_connect.add(T1_connected.get(i));
                }
            }
            if(T1_connect != null && triangle_match2(T2, T1_connect)) {
                /*for (int i = 0; i < T1_connect.size(); i++) {
                    Triangle TN = T1_connect.get(i);
                    System.out.println("[(" + TN.A.X + " " + TN.A.Y + " " + TN.A.Z  + "), (" + TN.B.X + " " + TN.B.Y + " " + TN.B.Z + "), (" + TN.C.X + " " + TN.C.Y + " " + TN.C.Z + ")]");
                }*/
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean match_c (AList<Triangle> AT1, AList<Triangle> AT2) {
        int count = 0;
        boolean x = false;
        if(AT1.size() == AT2.size()) {
            for (int i = 0; i < AT1.size(); i++) {
                if(AT1.get(i) == AT2.get(i)) {
                    count++;
                }
            }
            if(count == AT1.size()) {
                x = true;
            }
        }
        return x;
    }

    public AList<AList<Triangle>> List_Components () {
        AList<AList<Triangle>> dis_comp = new AList();
        for (int i = 0; i < alltriangles.size(); i++) {
            if(alltriangles.get(i).visited == false) {
                Triangle TRW = alltriangles.get(i);
                AList<Triangle> TRW_trs = new AList();
                Point[] P_TRW = (Point[]) TRW.triangle_coord();
                float[] tcod = {P_TRW[0].X, P_TRW[0].Y, P_TRW[0].Z, P_TRW[1].X, P_TRW[1].Y, P_TRW[1].Z, P_TRW[2].X, P_TRW[2].Y, P_TRW[2].Z};
                AList<Triangle> TRW_connected = ConnectedTriangleList(tcod, TRW_trs);
                if(TRW_connected == null) {
                    AList<Triangle> temp = new AList();
                    temp.add(TRW);
                    dis_comp.add(temp);
                    TRW.visited = true;
                }
                else {
                    dis_comp.add(TRW_connected);
                    for (int j = 0; j < TRW_connected.size(); j++) {
                        TRW_connected.get(j).visited = true;
                    }
                }
            }
        }
        /*AList<AList<Triangle>> non_repeat = new AList();
        non_repeat.add(dis_comp.get(0));
        int countw = 0;
        for (int i = 1; i < dis_comp.size(); i++) {
            for (int j = 0; j < non_repeat.size(); j++) {
                if(!match_c(dis_comp.get(i), non_repeat.get(j))) {
                    countw++;
                }
            }
            if(countw == non_repeat.size()) {
                non_repeat.add(dis_comp.get(i));
            }
            countw = 0;
        }
        return non_repeat;*/
        return dis_comp;
    }

    public int COUNT_CONNECTED_COMPONENTS() {
        AList<AList<Triangle>> allcomponentS = List_Components();
        for (int i = 0; i < alltriangles.size(); i++) {
            alltriangles.get(i).visited = false;
        }
        return allcomponentS.size();
    }

    public PointInterface[] CENTROID (){ 
        AList<AList<Triangle>> dis_comp = List_Components();
        for (int i = 0; i < alltriangles.size(); i++) {
            alltriangles.get(i).visited = false;
        }
        AList<Point> centroidlist = new AList();
        for (int i = 0; i < dis_comp.size(); i++) {
            AList<Triangle> this_alltriangles = dis_comp.get(i);
            float Centroid_x = 0, Centroid_y = 0, Centroid_z = 0; 
            AList<Point> allpoints_thiscomponent = new AList();
            for (int j = 0; j < this_alltriangles.size(); j++) {
                Triangle TH = this_alltriangles.get(j);
                Point[] TH_Point = (Point[]) TH.triangle_coord();
                for (int k = 0; k < TH_Point.length; k++) {
                    if(!hasPoint3(allpoints_thiscomponent, TH_Point[k])) {
                        allpoints_thiscomponent.add(TH_Point[k]);
                    }
                }  
            }
            for (int j = 0; j < allpoints_thiscomponent.size(); j++) {
                Point PU = allpoints_thiscomponent.get(j);
                Centroid_x = Centroid_x + PU.X;
                Centroid_y = Centroid_y + PU.Y;
                Centroid_z = Centroid_z + PU.Z;
            }
            int total = allpoints_thiscomponent.size();
            Point P_Centroid = new Point(Centroid_x/total, Centroid_y/total, Centroid_z/total);
            centroidlist.add(P_Centroid);
        }
        /*for (int i = 0; i < centroidlist.size(); i++) {
            Point PR = centroidlist.get(i);
            System.out.println("[(" + PR.X + " " + PR.Y + " " + PR.Z + ")]");
        }*/
        PointInterface[] Centroid_Components = new PointInterface[centroidlist.size()];
        for (int i = 0; i < centroidlist.size(); i++) {
            Centroid_Components[i] = centroidlist.get(i);
        }
        return Centroid_Components;
    }

    public boolean has_Point (Triangle TF, float[] p_cord) {
        Point PY = new Point(p_cord[0], p_cord[1], p_cord[2]);
        Point[] TF_point = (Point[]) TF.triangle_coord();
        if(PY.compareTo(TF_point[0]) == 1 || PY.compareTo(TF_point[1]) == 1 || PY.compareTo(TF_point[2]) == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean has_Point2 (AList<Triangle> ATF, float[] p_cord) {
        for (int i = 0; i < ATF.size(); i++) {
            Triangle TG = ATF.get(i);
            if(has_Point(TG, p_cord)) {
                return true;
            }
        }
        return false;
    }

    public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
        AList<AList<Triangle>> dis_comp = List_Components();   
        for (int i = 0; i < alltriangles.size(); i++) {
            alltriangles.get(i).visited = false;
        }            
        for (int i = 0; i < dis_comp.size(); i++) {
            AList<Triangle> this_alltriangles = dis_comp.get(i);
            if(has_Point2(this_alltriangles, point_coordinates)) {
                AList<Point> allpoints_thiscomponent = new AList();
                float Cent_x = 0, Cent_y = 0, Cent_z = 0;
                for (int j = 0; j < this_alltriangles.size(); j++) {
                    Triangle THS = this_alltriangles.get(j);
                    Point[] THS_Point = (Point[]) THS.triangle_coord();
                    for (int k = 0; k < THS_Point.length; k++) {
                        if(!hasPoint3(allpoints_thiscomponent, THS_Point[k])) {
                            allpoints_thiscomponent.add(THS_Point[k]);
                        }
                    }
                }
                for (int j = 0; j < allpoints_thiscomponent.size(); j++) {
                    Point PU = allpoints_thiscomponent.get(j);
                    Cent_x = Cent_x + PU.X;
                    Cent_y = Cent_y + PU.Y;
                    Cent_z = Cent_z + PU.Z;
                }
                int total = allpoints_thiscomponent.size();
                //System.out.println("[(" + Cent_x/total + " " + Cent_y/total + " " + Cent_z/total + ")]");
                PointInterface P_cent =  new Point(Cent_x/total, Cent_y/total, Cent_z/total);
                return P_cent;
            }
        }
        return null;
    }

    public boolean hasPoint3 (AList<Point> APTY, Point PTY) {
        for (int i = 0; i < APTY.size(); i++) {
            if(PTY.compareTo(APTY.get(i)) == 1) {
                return true;
            }
        }
        return false;
    }

    public float Euclidean_Distance (Point p1, Point p2) {
        float f1s = (p2.X - p1.X)*(p2.X - p1.X);
        float f2s = (p2.Y - p1.Y)*(p2.Y - p1.Y);
        float f3s = (p2.Z - p1.Z)*(p2.Z - p1.Z);
        float ed_dis = f1s + f2s + f3s;
        return ed_dis;
    }

    public AList<Float> List_Distance_Point(Point PQW, AList<AList<Point>> allcomponent_pointlist, int dontcheck) {
        AList<Float> distance_of_PQW = new AList();
        for (int i = 0; i < allcomponent_pointlist.size(); i++) {
            if(i != dontcheck) {
                AList<Point> other_comp = allcomponent_pointlist.get(i);
                for (int j = 0; j < other_comp.size(); j++) {
                    Point from_this_point = other_comp.get(j);
                    float distance = Euclidean_Distance(PQW, from_this_point);
                    distance_of_PQW.add(distance);
                }
            }
        }
        return distance_of_PQW;
    }

    public PointInterface [] CLOSEST_COMPONENTS() {
        AList<AList<Triangle>> dis_comp = List_Components();
        for (int i = 0; i < alltriangles.size(); i++) {
            alltriangles.get(i).visited = false;
        }
        if(dis_comp.size() == 1) {
            //System.out.println("null");
            return null;
        }
        else {
            AList<AList<Point>> allcomponent_points = new AList();
            AList<AList<AList<Float>>> distance_list_forall_components = new AList();
            for (int i = 0; i < dis_comp.size(); i++) {
                AList<Triangle> eachcomponent_triangle = dis_comp.get(i);
                AList<Point> eachcomponent_point = new AList();
                for(int j = 0; j < eachcomponent_triangle.size(); j++) {
                    Triangle TH = eachcomponent_triangle.get(j);
                    Point[] TH_point = (Point[]) TH.triangle_coord();
                    for (int k = 0; k < TH_point.length; k++) {
                        if(!hasPoint3(eachcomponent_point, TH_point[k])) {
                            eachcomponent_point.add(TH_point[k]);
                        }
                    }
                }
                allcomponent_points.add(eachcomponent_point);
            }
            if(allcomponent_points.size() > 1) {
                for (int i = 0; i < allcomponent_points.size(); i++) {
                    AList<Point> current_component = allcomponent_points.get(i);
                    AList<AList<Float>> distance_list_foreach_component = new AList();
                    for (int j = 0; j < current_component.size(); j++) {
                        Point this_comp_curr_point = current_component.get(j);
                        AList<Float> distance_foreach_point = List_Distance_Point (this_comp_curr_point, allcomponent_points, i);
                        distance_list_foreach_component.add(distance_foreach_point);
                    }
                    distance_list_forall_components.add(distance_list_foreach_component);
                }
            }
            AList<Float> minfloat_allcomponents = new AList();
            AList<Point[]> minfloat_allcomponents_point = new AList();
            for (int i = 0; i < distance_list_forall_components.size(); i++) {
                AList<AList<Float>> present_component = distance_list_forall_components.get(i);
                AList<Float> minfloat_component = new AList();
                AList<Point[]> minfloat_component_point = new AList();
                for (int j = 0; j < present_component.size(); j++) {
                    AList<Float> each_present_component = present_component.get(j);
                    float min = each_present_component.get(0);
                    int index = 0;
                    for (int k = 1; k < each_present_component.size(); k++) {
                        if(min > each_present_component.get(k)) {
                            min = each_present_component.get(k);
                            index = k;
                        }
                    }
                    minfloat_component.add(min);
                    Point p1 = allcomponent_points.get(i).get(j);
                    AList<Point> allpoints = new AList();
                    AList<Point> currp = allcomponent_points.get(i);
                    for (int h = 0; h < allcomponent_points.size(); h++) {
                        if(allcomponent_points.get(h) != currp) {
                            for (int f = 0; f < allcomponent_points.get(h).size(); f++) {
                                allpoints.add(allcomponent_points.get(h).get(f));
                            }
                        }
                    }
                    Point p2 = allpoints.get(index);
                    Point[] PTR = {p1, p2};
                    minfloat_component_point.add(PTR);
                }
                float temp = minfloat_component.get(0);
                int ind = 0;
                for (int j = 1; j < minfloat_component.size(); j++) {
                    if(temp > minfloat_component.get(j)) {
                        temp = minfloat_component.get(j);
                        ind = j;
                    }
                }
                minfloat_allcomponents.add(temp);
                minfloat_allcomponents_point.add(minfloat_component_point.get(ind));
            }
            int inde = 0;
            float temp = minfloat_allcomponents.get(0);
            for (int i = 0; i < minfloat_allcomponents.size(); i++) {
                if(temp > minfloat_allcomponents.get(i)) {
                    temp = minfloat_allcomponents.get(i);
                    inde = i;
                }
            }
            Point[] CLOS_COMP = minfloat_allcomponents_point.get(inde);
            Point P1W = CLOS_COMP[0];
            Point P2W = CLOS_COMP[1];
            //System.out.println("[(" + P1W.X + " " + P1W.Y + " " + P1W.Z + "), (" + P2W.X + " " + P2W.Y + " " + P2W.Z + ")]");
            return CLOS_COMP;
        }
    }

    public AList<Triangle> list_kth_neighbor(AList<Triangle> list) {
        AList<Triangle> new_List = new AList();
        for (int i = 0; i < list.size(); i++) {
            Triangle TE = list.get(i);
            TE.visited = true;
            float[] tcod = {TE.A.X, TE.A.Y, TE.A.Z, TE.B.X, TE.B.Y, TE.B.Z, TE.C.X, TE.C.Y, TE.C.Z};
            AList<Triangle> neigh_TE = NEIGH_TRIAN(tcod);
            if(neigh_TE != null) {
                for (int j = 0; j  < neigh_TE.size(); j++) {
                    if(neigh_TE.get(j).visited == false) {
                        new_List.add(neigh_TE.get(j));
                        neigh_TE.get(j).visited = true;
                    }
                }
            }
        }
        return new_List;
    }

    public int min_steps(Triangle T1, Triangle T2) {
        AList<Triangle> list = new AList();
        list.add(T1);
        int k = 0;
        while(!triangle_match2(T2, list)) {
            list = list_kth_neighbor(list);
            k++;
        }
        return k;
    }
    
    public int MAXIMUM_DIAMETER() {
        int count = 0;
        AList<AList<Triangle>> dis_comp = List_Components();
        for (int i = 0; i < alltriangles.size(); i++) {
            alltriangles.get(i).visited = false;
        }
        int temper = -1;
        int tempsize = dis_comp.get(0).size();
        for (int i = 0; i < dis_comp.size(); i++) {
            if(dis_comp.get(i).size() == 1) {
                count++;
            }
            else if(tempsize <= dis_comp.get(i).size()) {
                tempsize = dis_comp.get(i).size();
                temper = i;
            }
        }
        if (count == dis_comp.size()) {
            return 0;
        }
        else {
            AList<Triangle> Largest_Set_Triang = dis_comp.get(temper);
            Largest_Set_Triang = triangle_sort_arrivaltime(Largest_Set_Triang);
            AList<Integer> HOP_COUNT = new AList();
            for (int i = 0; i < Largest_Set_Triang.size() - 1; i++) {
                Triangle T1 = Largest_Set_Triang.get(i);
                Point[] PT1 = (Point[]) T1.triangle_coord();
                for (int j = i+1; j < Largest_Set_Triang.size(); j++) {
                    for (int k = 0; k < alltriangles.size(); k++) {
                        alltriangles.get(k).visited = false;
                    }
                    Triangle T2 = Largest_Set_Triang.get(j);
                    Point[] PT2 = (Point[]) T2.triangle_coord();
                    int hop_count = min_steps(T1 ,T2);
                    HOP_COUNT.add(hop_count);
                }
            }
            int tempos = HOP_COUNT.get(0);
            for (int i = 1; i < HOP_COUNT.size(); i++) {
                if(tempos < HOP_COUNT.get(i)) {
                    tempos = HOP_COUNT.get(i);
                }
            }
            int dia = tempos;
            //System.out.println(dia);
            return dia;
        }
    }
}

