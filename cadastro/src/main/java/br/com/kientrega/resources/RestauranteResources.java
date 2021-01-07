package br.com.kientrega.resources;

import br.com.kientrega.entidades.Prato;
import br.com.kientrega.entidades.Restuarante;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
    @Tag(name = "Restaurante")
    public List<Restuarante> listarRestaurantes (){
        return Restuarante.listAll();
    }

    @GET
    @Path("/{id}")
    @Tag(name = "Restaurante")
    public Restuarante obterRestuarante (@PathParam("id") Long id){
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> {
            Response.status(Response.Status.NOT_FOUND);
            return new NotFoundException("Restaurante não encontrado!");
        });
        Response.status(Response.Status.OK);
        return  (Restuarante) restauranteOp.get();
    }

    @POST
    @Transactional
    @Tag(name = "Restaurante")
    public void cadastrarRestaurante(Restuarante dto) {
        dto.persist();
        Response.status(Response.Status.CREATED);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Tag(name = "Restaurante")
    public void alterarRestaurante(@PathParam("id") Long id, Restuarante dto) {
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> {
            Response.status(Response.Status.NOT_FOUND);
            return new NotFoundException("Restaurante não encontrado!");
        });
        Restuarante restaurante = (Restuarante) restauranteOp.get();
        restaurante.nome = dto.nome;
        restaurante.proprietario = dto.proprietario;
        restaurante.persist();
        Response.status(Response.Status.OK);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Tag(name = "Restaurante")
    public void apagarRestaurante(@PathParam("id") Long id, Restuarante dto) {
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> {
            Response.status(Response.Status.NOT_FOUND);
            return new NotFoundException("Restaurante não encontrado!");
        });
        ((Restuarante) restauranteOp.get()).delete();
        Response.status(Response.Status.OK);
    }

    @GET
    @Path("/{codigoRestaurante}/pratos")
    @Tag(name = "Prato")
    public List<Prato> listarPratos (@PathParam("codigoRestaurante") Long id) {
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(((Restuarante)restauranteOp.get()).pratos).orElseThrow(() -> {
            Response.status(Response.Status.NOT_FOUND);
            return new NotFoundException("Não há pratos cadastrados!");
        });
        Response.status(Response.Status.OK);
        return ((Restuarante) restauranteOp.get()).pratos;
    }

    @GET
    @Path("/{codigoRestaurante}/pratos/")
    @Tag(name = "Prato")
    public Restuarante obterPratos (@PathParam("codigoRestaurante") Long id){
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> new NotFoundException("Prato não encontrado!"));
        Response.status(Response.Status.OK);
        return  (Restuarante) restauranteOp.get();
    }

    @POST
    @Transactional
    @Tag(name = "Prato")
    public void cadastrarPrato(Restuarante dto) {
        dto.persist();
        Response.status(Response.Status.CREATED);
    }

    @PUT
    @Path("/{codigoRestaurante}/pratos")
    @Transactional
    @Tag(name = "Prato")
    public void alterarPrato(@PathParam("codigoRestaurante") Long id, Restuarante dto) {
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> new NotFoundException("Prato não encontrado!"));
        Restuarante restaurante = (Restuarante) restauranteOp.get();
        restaurante.nome = dto.nome;
        restaurante.proprietario = dto.proprietario;
        restaurante.persist();
        Response.status(Response.Status.OK);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Tag(name = "Prato")
    public void apagarPrato(@PathParam("id") Long id, Restuarante dto) {
        Optional<PanacheEntityBase> restauranteOp = Restuarante.findByIdOptional(id);
        Optional.ofNullable(restauranteOp).orElseThrow(() -> new NotFoundException("Prato não encontrado!"));
        ((Restuarante) restauranteOp.get()).delete();
        Response.status(Response.Status.OK);
    }
}
