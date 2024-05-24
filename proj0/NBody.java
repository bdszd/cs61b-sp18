import edu.princeton.cs.algs4.ST;

public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for(int i = 0; i < num; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String imgName = in.readString();
            planets[i] = new Planet(xP,yP,xV,yV,mass,imgName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);

        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        while(t < T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

            }
            //can not be written into the last function.
            // the status of planet which should be calculated before end the loop can not totally update.
            for(int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n",planets.length);
        StdOut.printf("%.2f\n",radius);
        for(Planet p : planets){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);
        }
    }



}
