import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;

public class KdTreeST<Value> {
    private Node root;
    //private int N;
    //private RectHV cont = new RectHV(0.0, 0.0, 1.0, 1.0);
    private static boolean cm = true;

    private class Node {
        private Point2D p;      // the point
        private Value value;    // the symbol table maps the point to this value
        private RectHV rect;    // the axis-aligned rectangle corresponding 
        //to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        //private double xaxis;
        //private double yaxis;
        //private boolean v;
        private int N;

        public Node(Point2D p, Value value, int N) //double xaxis, 
        //double yaxis, boolean v, Node lb, Node rt) 
        {
            this.p = p;
            this.value = value;
            //this.xaxis = xaxis;
            //this.yaxis = yaxis;
            //this.v = v;
            //this.lb = lb;
            //this.rt = rt;
            this.N = N;
        } 
    }

    public KdTreeST() {  // construct an empty symbol table of points 
        root = null;
    }

    public boolean isEmpty() {  // is the symbol table empty? 
        if (size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {  // number of points
        return sizehelper(root);
    }

    private int sizehelper(Node node) {
        if (node == null) {
            return 0;
        }
        else return node.N;
    }

    public void put(Point2D p, Value val) { 
        // associate the value val with point p
        if (p == null) 
            throw new NullPointerException("the argument is null");
        if (val == null) 
            throw new NullPointerException("the argument is null");
        root = puthelper(root, p, val, cm);
    }
    //put helper method
    private Node puthelper(Node x, Point2D p, Value val, boolean v) {

        if (x == null) {
            //N++;
            return new Node(p, val, 1);
        }
        int cmp = p.compareTo(x.p);
        if (cmp < 0) {
            x.lb = puthelper(x.lb, p, val, !v);
            //return x;
        }
        else if (cmp >= 0) {
            x.rt = puthelper(x.rt, p, val, !v);
            //return x;
        }
        x.N = 1 + sizehelper(x.lb) + sizehelper(x.rt);

        return x;
    }

    public Value get(Point2D p) {  // value associated with point p 
        return null;
    }

//     private Value gethelper(Point2D p, Node x, boolean v) {
//         if (x == null) {
//             return null;
//         }
//         int cmp = p.compareTo(x.p);
//         if (cmp > 0) {
//             return gethelper(x., p, v);
//         }
//         else if (cmp < 0) {
//             return gethelper(p, x.lb, !v);
//         }
//         else
//             return x.val;
//     }

    public boolean contains(Point2D p) { // does the symbol table contain point p? 
        return get(p) != null;
    }

    public Iterable<Point2D> points() { // all points in the symbol table 
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D point : q) {
            q.enqueue(point);
        }

        return q;
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle 
        Queue<Point2D> q = new Queue<Point2D>();
        //rangehelper(rect, cont, root, q);

        return q;
    }

//     //get the left rectangle in parent's recctangle
//     private RectHV leftRectangle(RectHV rect, Node x) {
//         if (x.v) {
//             return new RectHV(rect.xmin(), rect.ymin(), x.xaxis, rect.ymax());
//         }
//         else {
//             return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.yaxis);
//         }
//     }

    //get the right rectangle in the parent's rectangle
//     private RectHV rightRectangle(RectHV rect, Node x) {
//         if (x.v) {
//             return new RectHV(x.xaxis, rect.ymin(), rect.xmax(), rect.ymax());
//         }
//         else {
//             return new RectHV(rect.xmin(), x.yaxis, rect.xmax(), rect.ymax());
//         }
//     }

//     private void rangehelper(RectHV rect, RectHV sRect, 
//     Node x, Queue<Point2D> q ) {
//         //have a bunch of if statements 
//         //compare the rectangles of each point 
//         if (x == null) {
//             return;
//         }
//         //if the rect of the point intersects with the point then you
//         //check the x and the y axis of the point and if its in the rectiangle 
//         //then you enqueue it and keep doing the same thing recursively
//         if (rect.intersects(sRect)) {
//             Point2D point = new Point2D(x.xaxis, x.yaxis);
//             if (rect.contains(point)) {
//                 q.enqueue(point);
//             }
//             rangehelper(rect, leftRectangle(sRect, x), x.lb, q);
//             rangehelper(rect, rightRectangle(sRect, x), x.rt, q);
//         }
//     }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor to point p; null if the symbol table is empty 
        //compare thepoint to the points side the point is on to 
        return null;
    }

    //nearest helper does all the comparasions
//     private Point2D nearesthelper(Node x, RectHV rect, double xaxis,
//     double yaxis, Point2D n)
//     {
//         if (x == null) {
//             return n;
//         }
//         double d1 = 0.0;
//         double d2 = 0.0;
//         RectHV left = null;
//         RectHV right = null;
//         Point2D p = new Point2D(xaxis, yaxis);
//         Point2D nearest = n;
//         if (nearest != null) {
//             d1 = p.distanceSquaredTo(nearest);
//             d2 = rect.distanceSquaredTo(p);
//         }
// 
//         if (nearest == null || d1 > d2) {
//             Point2D point = new Point2D(x.xaxis, x.yaxis);
// 
//             if (nearest == null || d1 > p.distanceSquaredTo(point)) {
//                 nearest = point;
//             }
// 
//             if (x.v) {
//                 left = new RectHV(rect.xmin(), rect.ymin(), x.xaxis, rect.ymax());
//                 right = new RectHV(x.xaxis, rect.ymin(), rect.xmax(), rect.ymax());
// 
//                 if (xaxis < x.xaxis) {
//                     nearest = nearesthelper(x.lb, left, xaxis, yaxis, nearest);
//                     nearest = nearesthelper(x.rt, right, xaxis, yaxis, nearest); 
//                 }
//                 else {
//                     nearest = nearesthelper(x.rt, right, xaxis, yaxis, nearest);
//                     nearest = nearesthelper(x.lb, left, xaxis, yaxis, nearest); 
//                 }
//             }
//             else {
//                 left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.yaxis);
//                 right = new RectHV(rect.xmin(), x.yaxis, rect.xmax(), rect.ymax());    
// 
//                 if (yaxis < x.yaxis) {
//                     nearest = nearesthelper(x.lb, left, xaxis, yaxis, nearest);
//                     nearest = nearesthelper(x.rt, right, xaxis, yaxis, nearest);
//                 }
//                 else {
//                     nearest = nearesthelper(x.rt, right, xaxis, yaxis, nearest);
//                     nearest = nearesthelper(x.lb, left, xaxis, yaxis, nearest);
//                 }
//             }
//         }
// 
//         return nearest;
//     }

    public static void main(String[] args) {                 // unit testing (required) 
    }
}