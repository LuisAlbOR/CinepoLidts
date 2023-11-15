CREATE TABLE pelicula (
    id int NOT NULL AUTO_INCREMENT,
    image VARCHAR(255),
    url_trailer VARCHAR(255),
    nombre VARCHAR(255),
    minutos TIME,
    clasificacion VARCHAR(255),
    tipo VARCHAR(255),
    year YEAR,
    sinopsis TEXT,
    director_id int NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_participacion_director_id FOREIGN KEY (director_id) REFERENCES director(id)

);
