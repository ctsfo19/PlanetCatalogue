/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanetCatalogue;

/**
 *
 * @author Mateusz So?tysiak <s195679@student.pg.edu.pl>
 */
public class Moon extends Base{
    protected double OrbitRadius;
    protected int Period;
    protected Planet Parent = new Planet();
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected String SatNum = "I'm a moon, I'm the ultimate satellite!";
    
    //default constructor
    public Moon(){
        this.Name = "NoName";
        this.Radius = 0;
        this.Mass = 0;
        this.OrbitRadius = 0;
        this.Period = 0;
    }
    
    //overloaded constructor
    public Moon(String name, Planet parent){
        this.Name = name;
        this.Parent = parent;
        this.Radius = 0;
        this.Mass = 0;
        this.OrbitRadius = 0;
        this.Period = 0;
    }
    
    //get methods
    
    public double getOrbit(){
        return OrbitRadius;
    }
    
    public int getPeriod(){
        return Period;
    }
    
    public String getParent(){
        return Parent.getName();
    }
    
    public String getData(){
        String data;
        data = String.format("Name:\t%s\nParent:\t%s\nMass:\t%f\nRadius:\t%f\nDensity:\t%f\nRevolution period:\t%d\nOrbit radius:\t%f\n"
                , Name, getParent(), Mass, Radius, getDensity(),Period,OrbitRadius);
        return data;
    }
    
    //set methods
    
    public void setOrbit(double orbit){
        this. OrbitRadius = orbit;
    }
    
    public void setPeriod(int period){
        this.Period = period;
    }
    
    public void setParent(Planet pl){
        this.Parent = pl;
    }
    
}
