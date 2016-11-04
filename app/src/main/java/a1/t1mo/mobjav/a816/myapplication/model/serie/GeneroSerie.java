package a1.t1mo.mobjav.a816.myapplication.model.serie;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 * Archivo creado por Juan Pablo on 29/10/2016.
 */

public enum GeneroSerie {
    ACTION_ADVENTURE(10759), ANIMATION(16), COMEDY(35), CRIME(80), DOCUMENTARY(99), DRAMA(18),
    FAMILY(10751), KIDS(10762), MYSTERY(9648), NEWS(10763), REALITY(10764), SCIFI_FANTASY(10765),
    SOAP(10766), TALK(10767), WAR_POLITICS(10768), WESTERN(37), TODAS(-1);

    public Integer id;

    private GeneroSerie(Integer id) {
        this.id = id;
    }

}
