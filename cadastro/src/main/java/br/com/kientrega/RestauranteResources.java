package br.com.kientrega;

import br.com.kientrega.entidades.Restuarante;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResources {

    @GET
    public List<Restuarante> buscarTodos (){
        return Restuarante.listAll();
    }

    @GET
    @Path("/{id}")
    public Restuarante buscarRestuarante (@PathParam("id") Long id){
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
                Optional.ofNullable(restauranteOp).orElseThrow(() -> new NotFoundException("Restaurante não encontrado!"));
        return  (Restuarante) restauranteOp.get();
    }

    @POST
    @Transactional
    public  void adicionar(Restuarante dto) {
        dto.persist();
        Response.status(Response.Status.CREATED);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public  void alterar(@PathParam("id") Long id, Restuarante dto) {
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> new NotFoundException("Restaurante não encontrado!"));
        Restuarante restaurante = (Restuarante) restauranteOp.get();
        restaurante.nome = dto.nome;
        restaurante.proprietario = dto.proprietario;
        restaurante.persist();
    }

}
