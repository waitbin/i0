package com.thoughtworks.i0.samples.blog.api;

import com.thoughtworks.i0.samples.blog.domain.Post;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/posts")
@Produces(APPLICATION_JSON)
public class PostResources {
    private EntityManager entityManager;

    @Inject
    public PostResources(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GET
    public List<Post> all() {
        return entityManager.createQuery("select t from Post t", Post.class).getResultList();
    }

}