CREATE TABLE horario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pelicula_id INT,
    horario TIME,
    CONSTRAINT fk_horario_pelicula_id FOREIGN KEY (pelicula_id) REFERENCES pelicula(id)
);

