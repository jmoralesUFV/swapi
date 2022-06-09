package ufv.dis.final2022.JMI;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@RestController
@Component
public class ClassController {

    private Agenda AgendaChupiSwapi;

    Gson gson = new Gson();
    people persona = new people();
    starship nave = new starship();

    private final String url = "/swapi";
    private final String urlP = "/swapi/people";
    private final String urlS = "/swapi/naves";
    private final String urlOB = "/swapi/OB";

    public ClassController()
    {
        this.AgendaChupiSwapi = new Agenda();

        boolean flag = false;
        if(flag) {
            throw new NullPointerException();
        }
    }
    // Método post para obtener los datos de Swapi
    @PostMapping(url)
    public String buscarArgo(@RequestBody ObjetoBuscar OB) {

        String a = String.valueOf(OB);

        HttpRequest request;
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response;

        String opi ="https://swapi.dev/api/%s/%s";

        String respuesta = null;

        try{
            String resource = String.format(opi, OB.getEntity(), OB.getId());
            System.out.println(resource);

            request = HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            respuesta = response.body();

            // System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }

        String entidad = String.valueOf(OB.getEntity());

        if(OB.getEntity().equals("people"))
        {
            persona = gson.fromJson(respuesta, people.class);
            System.out.println(persona);
            AgendaChupiSwapi.addPeople(persona);
        }

        if(OB.getEntity().equals("starships"))
        {
            nave = gson.fromJson(respuesta, starship.class);
            System.out.println(nave);
            AgendaChupiSwapi.addNave(nave);
        }

        AgendaChupiSwapi.addOB(OB);

        return respuesta;
    }

    // Llamada a la raíz
    @GetMapping(url)
    public String saludar() {
        // ---
        return "Esto se ha colgado !!";
    }

    // Método get para la consulta people
    @GetMapping(urlP+"/{id}")
    public people getPeopleByIdREST(@PathVariable int id) {
        //
        return this.AgendaChupiSwapi.getPeopleById(id);
    }

    @GetMapping(urlP)
    public ArrayList<people> getAllUsers() {
        //
        return this.AgendaChupiSwapi.getAllPeople();
    }

    // Método get para la consulta naves
    @GetMapping(urlS+"/{id}")
    public starship getNaveByIdREST(@PathVariable int id) {
        //
        return this.AgendaChupiSwapi.getNaveById(id);
    }

    @GetMapping(urlS)
    public ArrayList<starship> getAllNaves() {
        //
        return this.AgendaChupiSwapi.getAllNaves();
    }

    // Método get para la consulta OB
    @GetMapping(urlOB+"/{id}")
    public ObjetoBuscar getOBByIdREST(@PathVariable int id) {
        //
        return this.AgendaChupiSwapi.getBusquedaById(id);
    }

    @GetMapping(urlOB)
    public ArrayList<ObjetoBuscar> getAllOB() {

        ArrayList<people> personas = this.AgendaChupiSwapi.getAllPeople();
        ArrayList<starship> naves = this.AgendaChupiSwapi.getAllNaves();
        ArrayList<ObjetoBuscar> busquedas = this.AgendaChupiSwapi.getAllOB();

        String jsonP = new Gson().toJson(personas);
        String jsonN = new Gson().toJson(naves);
        String jsonOB = new Gson().toJson(busquedas);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Personas.json"))) {
            bw.write(jsonP);
            System.out.println("Fichero creado\n ------------ \n");
        } catch (IOException ex) {
            System.out.println("No ha sido posible crear el archivo Personas.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Naves.json"))) {
            bw.write(jsonN);
            System.out.println("Fichero creado\n ------------ \n");
        } catch (IOException ex) {
            System.out.println("No ha sido posible crear el archivo Naves.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Busquedas.json"))) {
            bw.write(jsonOB);
            System.out.println("Fichero creado\n ------------ \n");
        } catch (IOException ex) {
            System.out.println("No ha sido posible crear el archivo de busquedas.");
        }

        return this.AgendaChupiSwapi.getAllOB();
    }

}
