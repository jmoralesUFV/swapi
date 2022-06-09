package ufv.dis.final2022.JMI;

import java.time.Period;
import java.util.ArrayList;

/*
    Clase Agenda

    Clase que hace de gestor y control de las estructuras de datos que manejamos mediante clases.
    Instancia mediante arraylists las diferentes clases de manera que tenemos una lista de equipos, usuarios y préstamos.

    Contiene las funciones elementales del procesamiento de una API-Rest como introducir nuevos datos, consultarlos,
    modificarlos o eliminarlos.

*/

public class Agenda {

    // INSTANCIAMOS LAS LISTAS DE PEOPLE Y STARSHIPS
    private ArrayList<people> ListaPeople;
    private ArrayList<starship> ListaNaves;
    private ArrayList<ObjetoBuscar> ListaBusquedas;

    private ObjetoBuscar ExBuscar;

    // Instanciamos una clase del objeto búsqueda
    private ObjetoBuscar CosoaBuscar;

    public Agenda()
    {
        this.ListaPeople = new ArrayList<people>(500);
        this.ListaBusquedas = new ArrayList<ObjetoBuscar>(500);
        this.ListaNaves = new ArrayList<starship>(500);
        this.ExBuscar = new ObjetoBuscar("people", "3");
    }

    /*
        -------------------------------------------------
        CJTO DE FUNCIONES PARA EL TRATAMIENTO DE ObjetoBuscar
        -------------------------------------------------
    */

    public Boolean addOB(ObjetoBuscar OB)
    {
        Boolean añadido=this.ListaBusquedas.add(OB);
        return añadido;
    }

    public ArrayList<ObjetoBuscar> getAllOB()
    {
        return this.ListaBusquedas;
    }

    public ObjetoBuscar getBusquedaById(int id)
    {
        return this.ListaBusquedas.get(id);
    }

    /*
        -------------------------------------------------
        CJTO DE FUNCIONES PARA EL TRATAMIENTO DE People
        -------------------------------------------------
    */

    public Boolean addPeople(people P)
    {
        Boolean añadido=this.ListaPeople.add(P);
        return añadido;
    }

    public ArrayList<people> getAllPeople()
    {
        return this.ListaPeople;
    }

    public people getPeopleById(int id)
    {
        return this.ListaPeople.get(id);
    }

    /*
        -------------------------------------------------
        CJTO DE FUNCIONES PARA EL TRATAMIENTO DE Starship
        -------------------------------------------------
    */

    public Boolean addNave(starship S)
    {
        Boolean añadido=this.ListaNaves.add(S);
        return añadido;
    }

    public ArrayList<starship> getAllNaves()
    {
        return this.ListaNaves;
    }

    public starship getNaveById(int id)
    {
        return this.ListaNaves.get(id);
    }

}
