import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
public class PointST<Value> {
    private RedBlackBST<Point2D, Value> bst;

    public PointST() {                               // construct an empty symbol table of points 
        bst = new RedBlackBST<Point2D, Value>();
    }

    public boolean isEmpty() {                     // is the symbol table empty? 
        if (bst.isEmpty()) {
            return true;
        }
        else return false;
    }

    public int size() {                        // number of points 
        return bst.size();
    }

    public void put(Point2D p, Value val) {     // associate the value val with point p
        if (val == null) {
            throw new NullPointerException("Value is null.");
        } 
        
        if (p == null) {
            throw new NullPointerException("Point is null.");
        }
        bst.put(p, val);   
    }

    public Value get(Point2D p) {                // value associated with point p 
        if (p == null) {
            throw new NullPointerException("The point is null.");
        }
        return bst.get(p);
    }

    public boolean contains(Point2D p) {           // does the symbol table contain point p? 
        if (p == null){
            throw new NullPointerException("point is null.");
        }
        return bst.contains(p);
    }

    public Iterable<Point2D> points() {                      // all points in the symbol table 
        Queue<Point2D> q = new Queue<Point2D>();
        Iterable<Point2D> lst = bst.keys();
        for (Point2D point : lst) {
            q.enqueue(point);
        }
        return q;
    }

    public Iterable<Point2D> range(RectHV rect) {            // all points that are inside the rectangle 
        Point2D min = new Point2D(rect.ymin(), rect.xmin()); 
        Point2D max = new Point2D(rect.ymax(), rect.xmax());
        Iterable<Point2D> minAndMax = bst.keys(min, max);
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D point : minAndMax) {
            if(rect.contains(point)) {
                q.enqueue(point);
            }
        }
        return q;
    }

    public Point2D nearest(Point2D p) {            // a nearest neighbor to point p; null if the symbol table is empty 
        if (bst.isEmpty()) {
            return null;
        }
        
        Point2D minDist = null;
        double distance = p.distanceSquaredTo(minDist);
        Iterable<Point2D> points = bst.keys();
        
        for(Point2D point : points) {
            if(p.distanceSquaredTo(point) < distance && p.compareTo(point) != 0) {
                minDist = point;
                distance = p.distanceSquaredTo(point);
            }
        }
        return minDist;
    }

    public static void main(String[] args) {                 // unit testing (required) 
    }
}