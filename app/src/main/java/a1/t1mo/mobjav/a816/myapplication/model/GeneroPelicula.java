package a1.t1mo.mobjav.a816.myapplication.model;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */

public enum GeneroPelicula {
    ACTION(28), ADVENTURE(12), ANIMATION(16), COMEDY(35), CRIME(80), DOCUMENTARY(99), DRAMA(18),
    FAMILY(10751), FANTASY(14), HISTORY(36), HORROR(27), MUSIC(10402), MYSTERY(9648), ROMANCE(10749),
    SCIENCE_FICTION(878), TV_MOVIE(10770), THRILLER(53), WAR(10752), WESTERN(37), TODAS(-1);

    public Integer id;

    GeneroPelicula(Integer id) {
        this.id = id;
    }
}
