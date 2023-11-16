package edu.unach.cinepolidts.domain.pelicula;

import edu.unach.cinepolidts.domain.director.Director;
import jakarta.validation.constraints.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        @Min(1)
        Time minutos,

        @NotNull
        Clasificacion clasificacion,

        @NotNull
        Categoria tipo,

        @Min(1950)
        Time year,

        @NotBlank
        String sinopsis,

        Integer directorId,

        @NotEmpty
        List<Integer> actoresIds
) {
}
