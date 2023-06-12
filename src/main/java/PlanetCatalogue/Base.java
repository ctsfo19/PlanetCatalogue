/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanetCatalogue;
import java.util.Arrays;
import java.io.Serializable;

/**
 *
 * @author Mateusz So?tysiak <s195679@student.pg.edu.pl>
 */
public class Base implements Serializable {
    //class fields declaration
    protected String Name;
    protected double Radius;
    protected double Mass;
    protected int SatNum;
    
    //default constructor
    public Base(){
        this.Name = "NoName";
        this.Radius = 0;
        this.Mass = 0;
    }
    
    //overloaded constructor for creating satellite array
    public Base(String name){
        this.Name = name;
        this.Radius = 0;
        this.Mass = 0;
    }
    
    
    //method returning density
    public double getDensity(){
        double dens, volume;
        volume = (4.0/3.0)*Math.PI*Math.pow(getRadius(),3);
        dens = getMass()/volume;
        return dens;
    }
    
    //method for calculating velocity
//    public double getVelocity(double angularVelo, double orbitRadius){
//        double velocity = angularVelo * orbitRadius;
//        return velocity;
//    }
    
    //methods returning select data
    public String getName(){
        return Name;
    }
    public double getMass(){
        return Mass;
    }
    public double getRadius(){
        return Radius;
    }
    
    //methods for altering/setting data values
    public void setName(String name){
        this.Name = name;
    }
    public void setMass(double mass){
        this.Mass = mass;
    }
    public void setRadius(double radius){
        this.Radius = radius;
    }
    public void setSatNum(int satnum){
        this.SatNum = satnum;
    }

    
}
