package a1.t1mo.mobjav.a816.myapplication.model;

import io.realm.RealmObject;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 09/11/2016.
 */

/**
 * Clase necesaria porque Realm todavia no soporta Listas de tipos primitivos como String
 */
public class RealmString extends RealmObject {
    private String value;

    public RealmString() {}

    public RealmString(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}