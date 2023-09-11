package Math;

public class Vector2D1 {

    private double x1, y1;

    public Vector2D1(double x1, double y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public Vector2D1() {
        x1 = 0;
        y1 = 0;
    }

    public Vector2D1 add(Vector2D1 v) {
        return new Vector2D1(x1 + v.getX1(), y1 + v.getY1());
    }

    public Vector2D1 red(Vector2D2 v) {
        return new Vector2D1(v.getX2() - x1, v.getY2() - y1);
    }

    public Vector2D1 scale(double valor) {
        return new Vector2D1(x1 * valor, y1 * valor);
    }

    public Vector2D1 limit(double valor) {
        if (getMagnitud() > valor) {
            return this.normalizar().scale(valor);
        }
        return this;
    }

    public Vector2D1 normalizar() {
        double magnitud = getMagnitud();
        return new Vector2D1(x1 / magnitud, y1 / magnitud);
    }

    public double getMagnitud() {
        return Math.sqrt(x1 * x1 + y1 * y1);
    }

    public Vector2D1 setDirection(double angle) {
        double direccion = getMagnitud();
        return new Vector2D1(Math.cos(angle) * direccion, Math.sin(angle) * direccion);
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

}
