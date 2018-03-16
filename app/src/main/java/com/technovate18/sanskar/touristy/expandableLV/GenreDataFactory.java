package com.technovate18.sanskar.touristy.expandableLV;

import com.technovate18.sanskar.touristy.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hadoop on 16/3/18.
 */

public class GenreDataFactory {

    public static List<Genre> makeGenres() {
        return Arrays.asList(
                makeRaipur(),
                makeBastar(),
                makeSirpur(),
                makeManipat()
                );
    }

    public static Genre makeRaipur() {
        return new Genre("Raipur City - StateCapital", makeRaipurSub(), R.drawable.places_ic_search);
    }

    public static List<Artist> makeRaipurSub() {
        Artist queen = new Artist("Mana Tuta Science Park", true);
        Artist styx = new Artist("Vivekanand Sarovar", false);
        Artist reoSpeedwagon = new Artist("Purkhauti Muktangan", false);
        Artist boston = new Artist("Dudhadhari Math", true);

        return Arrays.asList(queen, styx, reoSpeedwagon, boston);
    }

    public static Genre makeBastar() {
        return new Genre("Bastar", makeBastarsub(), R.drawable.places_ic_search);
    }

    public static List<Artist> makeBastarsub() {
        Artist queen = new Artist("Chitrakote Waterfalls", true);

        return Arrays.asList(queen);
    }

    public static Genre makeSirpur() {
        return new Genre("Sirpur", makeSirpursub(), R.drawable.places_ic_search);
    }

    public static List<Artist> makeSirpursub() {
        Artist queen = new Artist("Laxman Temple", true);

        return Arrays.asList(queen);
    }

    public static Genre makeManipat() {
        return new Genre("Manipat", makeManipatsub(), R.drawable.places_ic_search);
    }

    public static List<Artist> makeManipatsub() {
        Artist queen = new Artist("Tiger Point Waterfalls", true);

        return Arrays.asList(queen);
    }




    public static Genre makeGangrelDam() {
        return new Genre("Gangrel Dam", makeGangrelDamsub(), R.drawable.places_ic_search);
    }

    public static List<Artist> makeGangrelDamsub() {
        Artist queen = new Artist("Mana Tuta Science Park", true);
        Artist styx = new Artist("Vivekanand Sarovar", false);
        Artist reoSpeedwagon = new Artist("Purkhauti Muktangan", false);
        Artist boston = new Artist("Dudhadhari Math", true);

        return Arrays.asList(queen, styx, reoSpeedwagon, boston);
    }















    public static Genre makeRockGenre() {
        return new Genre("Rock", makeRockArtists(), R.drawable.places_ic_search);
    }

    public static List<Artist> makeRockArtists() {
        Artist queen = new Artist("Queen", true);
        Artist styx = new Artist("Styx", false);
        Artist reoSpeedwagon = new Artist("REO Speedwagon", false);
        Artist boston = new Artist("Boston", true);

        return Arrays.asList(queen, styx, reoSpeedwagon, boston);
    }

    public static Genre makeJazzGenre() {
        return new Genre("Jazz", makeJazzArtists(), R.drawable.places_ic_search);
    }



    public static List<Artist> makeJazzArtists() {
        Artist milesDavis = new Artist("Miles Davis", true);
        Artist ellaFitzgerald = new Artist("Ella Fitzgerald", true);
        Artist billieHoliday = new Artist("Billie Holiday", false);

        return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
    }

    public static Genre makeClassicGenre() {
        return new Genre("Classic", makeClassicArtists(), R.drawable.places_ic_search);
    }



    public static List<Artist> makeClassicArtists() {
        Artist beethoven = new Artist("Ludwig van Beethoven", false);
        Artist bach = new Artist("Johann Sebastian Bach", true);
        Artist brahms = new Artist("Johannes Brahms", false);
        Artist puccini = new Artist("Giacomo Puccini", false);

        return Arrays.asList(beethoven, bach, brahms, puccini);
    }

    public static Genre makeSalsaGenre() {
        return new Genre("Salsa", makeSalsaArtists(), R.drawable.places_ic_search);
    }


    public static List<Artist> makeSalsaArtists() {
        Artist hectorLavoe = new Artist("Hector Lavoe", true);
        Artist celiaCruz = new Artist("Celia Cruz", false);
        Artist willieColon = new Artist("Willie Colon", false);
        Artist marcAnthony = new Artist("Marc Anthony", false);

        return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
    }

    public static Genre makeBluegrassGenre() {
        return new Genre("Bluegrass", makeBluegrassArtists(), R.drawable.places_ic_search);
    }



    public static List<Artist> makeBluegrassArtists() {
        Artist billMonroe = new Artist("Bill Monroe", false);
        Artist earlScruggs = new Artist("Earl Scruggs", false);
        Artist osborneBrothers = new Artist("Osborne Brothers", true);
        Artist johnHartford = new Artist("John Hartford", false);

        return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
    }

}
