import java.lang.Math;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) +(yyPos- p.yyPos)*(yyPos-p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return G*mass*p.mass / (r*r);
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        return dx * calcForceExertedBy(p) / r;
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        return dy * calcForceExertedBy(p) / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double totalForce = 0;
        for(Planet planet:allPlanets){
            if(this.equals(planet)){
                continue;
            }else{
                totalForce += calcForceExertedByX(planet);
            }
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double totalForce = 0;
        for(Planet planet:allPlanets){
            if(this.equals(planet)){
                continue;
            }else{
                totalForce += calcForceExertedByY(planet);
            }
        }
        return totalForce;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel *dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }

}
