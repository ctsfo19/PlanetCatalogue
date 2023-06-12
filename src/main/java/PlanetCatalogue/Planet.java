/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanetCatalogue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateusz Soltysiak <s195679@student.pg.edu.pl>
 */
public class Planet extends Base {
    
    //declaring class fields
    protected double OrbitRadius;
    protected int Period;
    protected List<Moon> Satellites;
    protected Sun Parent = new Sun();
    
    //default constructor
    public Planet(){
        this.Name = "NoName";
        this.Radius = 0;
        this.Mass = 0;
        this.OrbitRadius = 0;
        this.Period = 0;
        this.SatNum = 0;
    }
    
    //overloaded constructor
    public Planet(String name, Sun parent){
        this.Name = name;
        this.Parent = parent;
        this.Radius = 0;
        this.Mass = 0;
        this.OrbitRadius = 0;
        this.Period = 0;
        this.SatNum = 0;
        this.Satellites = new ArrayList<>();
    }
    
    //get methods
    public double getOrbit(){
        return OrbitRadius;
    }
    
    public int getPeriod(){
        return Period;
    }
    
    public int getMoon(){
        return SatNum;
    }
    
    public String getParent(){
        return Parent.getName();
    }
    
    public String getSatellites(){
        String data = "";
        if(Satellites == null)
            return "No satellites\n";
        else{
        for (Moon Satellite : Satellites) {
            data = data + Satellite.getName() + '\n';
        }
        return data;
        }
    }
    
    public String getData(){
        String data;
        data = String.format("Name:\t%s\nParent:\t%s\nMass:\t%f\nRadius:\t%f\nDensity:\t%f\nRevolution period:\t%d\nOrbit radius:\t%f\nNumber of satellites:\t%d"
                , Name,getParent(), Mass, Radius, getDensity(),Period,OrbitRadius,SatNum);
        return data;
    }
    
    //set methods
    public void setOrbit(double orbit){
        this. OrbitRadius = orbit;
    }
    
    public void setPeriod(int period){
        this.Period = period;
    }
    
    public void setMoon(int moon){
        this.SatNum = moon;
    }
    
    public void setSatellites(int n, String names){
        String name[] = names.split(",");
        for(int i = 0; i < n; i++){
            if(!names.isBlank()&& name.length > i){
            Satellites.add(new Moon(name[i], this));
            }else{
            Satellites.add(new Moon(Integer.toString(i+1),this));
            }
        }
    }
    
    public void addSatellite(Moon sat){
        this.Satellites.add(sat);
        this.SatNum++;
    }
    
    public void remSatellite(Moon sat){
        this.Satellites.remove(sat);
        this.SatNum--;
    }
    
    public void setParent(Sun sun){
        this.Parent = sun;
    }
}
