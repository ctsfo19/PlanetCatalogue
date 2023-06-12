/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanetCatalogue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateusz So?tysiak <s195679@student.pg.edu.pl>
 */
public class Container implements Serializable {
    public List<Object> objects;

    public Container() {
        objects = new ArrayList<>();
    }

    public void addObject(Object obj) {
        objects.add(obj);
    }

    public List<Object> getObjects() {
        return objects;
    }
    public void clear(){
        this.objects = null;
    }
}