package homework.graphics;

public class SphericalMercator extends Mercator {

    @Override
    public double xAxisProjection(double input) {
        return Math.toRadians(input) * RADIUS_MAJOR;
    }

    @Override
    public double yAxisProjection(double input) {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(input) / 2)) * RADIUS_MAJOR;
    }
}
