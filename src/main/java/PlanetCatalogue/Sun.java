/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanetCatalogue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mateusz So?tysiak <s195679@student.pg.edu.pl>
 */
public class Sun extends Base{
    //declaring class fields
    protected double SurfTemp;
    protected List<Planet> Satellites;
    protected String Parent = "I'm the ultimate parent!";
    
    //default constructor
    public Sun(){
        this.Name = "NoName";
        this.Radius = 0;
        this.Mass = 0;
        this.SurfTemp = 0;
        this.SatNum = 0;
    }
    
    //overloaded constructor
    public Sun(String name){
        this.Name = name;
        this.Radius = 0;
        this.Mass = 0;
        this.SurfTemp = 0;
        this.Satellites = new ArrayList<>();
        this.SatNum = 0;
    }
    
    //methods for getting data
    public double getTemp(){
        return SurfTemp;
    }
    
    public String getSatellites(){
        String data = "Satellites:\n";
        if(Satellites == null)
            return "No satellites\n";
        else{
        for (Planet Satellite : Satellites) {
            data = data + Satellite.getName() + '\n';
        }
        return data;
        }
    }
    
    //@Override seems bloody unnecessary after all it doesn't seem that
    public String getData(){
        String data;
        data = String.format("Name:\t%s\nMass:\t%f\nRadius:\t%f\nDensity:\t%f\nSurface temperature:\t%f\nNumber of satellites:\t%d", Name, Mass, Radius, getDensity(),getTemp(),SatNum);
        return data;
    }
    
    //method for altering data
    public void setTemp(double surftemp){
        this.SurfTemp = surftemp;
    }
    
    public void setSatellites(int n, String names){
        String name[] = names.split(",");
        for(int i = 0; i < n; i++){
            if(!names.isBlank()&& name.length > i){
            Satellites.add(new Planet(name[i], this));
            }else{
            Satellites.add(new Planet(Integer.toString(i+1),this));
            }
        }
    }
    
    public void addSatellite(Planet sat){
        this.Satellites.add(sat);
        this.SatNum++;
    }
    
    public void remSatellite(Planet sat){
        this.Satellites.remove(sat);
        this.SatNum--;
    }
    
}
