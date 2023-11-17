package edu.unach.cinepolidts.domain.pelicula;

import jakarta.validation.constraints.*;

import java.sql.Date;
import java.sql.Time;

import java.time.Year;
import java.util.List;

public record DatosRegistroPelicula(
        @NotBlank
        //@Pattern(regexp = "^[a-zA-Z0-9 .\\-_:/]{4,100}$\n", message = "El nombre debe tener de 4 a 100 caracteres alfanuméricos")
        String image,

        @NotBlank
        //@Pattern(regexp = "^[a-zA-Z0-9 .\\-_:/]{4,100}$\n", message = "La URL del trailer debe tener de 4 a 100 caracteres alfanuméricos")
        String urlTrailer,

        @NotBlank
        //@Pattern(regexp = "^[a-zA-Z0-9 .\\-_:/]{4,100}$\n", message = "El nombre debe tener de 4 a 100 caracteres alfanuméricos")
        String nombre,

        Time minutos,

        @NotNull
        Clasificacion clasificacion,

        @NotNull
        Categoria tipo,


        Year year,

        @NotBlank
        String sinopsis,

        Integer directorId,

        @NotEmpty
        List<Integer> actoresIds
) {
}
