CREATE TABLE participacion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    actor_id int NOT NULL,
    pelicula_id int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_participaciones_actor_id FOREIGN KEY (actor_id) REFERENCES actor (id),
    CONSTRAINT fk_participaciones_pelicula_id FOREIGN KEY (pelicula_id) REFERENCES pelicula (id)
);
