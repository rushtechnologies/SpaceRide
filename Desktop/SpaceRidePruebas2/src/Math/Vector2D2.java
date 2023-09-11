package Math;

public class Vector2D2 {

    private double x2, y2;

    public Vector2D2(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public Vector2D2() {
        x2 = 0;
        y2 = 0;
    }

    public Vector2D2 add(Vector2D2 v) {
        return new Vector2D2(x2 + v.getX2(), y2 + v.getY2());

    }

    public Vector2D2 red(Vector2D1 v) {
        return new Vector2D2(x2 - v.getX1(), y2 - v.getY1());
    }

    public Vector2D2 scale(double valor) {
        return new Vector2D2(x2 * valor, y2 * valor);
    }

    public Vector2D2 limit(double valor) {

        if (getMagnitud() > valor) {
            return this.normalizar().scale(valor);
        }
        return this;
    }

    public Vector2D2 normalizar() {
        double magnitud = getMagnitud();
        return new Vector2D2(x2 / magnitud, y2 / magnitud);
    }

    public double getMagnitud() {
        return Math.sqrt(x2 * x2 + y2 * y2);
    }

    public Vector2D2 setDirection(double angle2) {
        double direccion = getMagnitud();
        return new Vector2D2(Math.cos(angle2) * direccion, Math.sin(angle2) * direccion);
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

}
