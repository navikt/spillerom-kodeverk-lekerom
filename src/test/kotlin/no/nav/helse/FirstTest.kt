package no.nav.helse

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


data class Vedtak(
    val vilkårsvurderinger: List<Vilkårsvurdering>
)

/*
8-2.1	Må ha arbeidet i 28 dager før arbeidsuførhet inntreffer
8-2.2.1	Unntak fra 8-2.1 ved mottatt dagpenger, omsorgspenger, pleiepenger, opplæringspenger, svangerskapspenger eller foreldrepenger
8-2.2.2	Hvis AAP før FP og retten var brukt opp uten ny opptjening gjelder ikke 8-2.2.1
8-2.2.3
 */

enum class Paragraf {
    `8-2`,
}
//enum class
enum class Henvisning(
    val tekst: String,
    val paragraf: Paragraf,
    //val årskaerTilInnvilgelse: List<Any>,
    //val årskaerTilAvslag: List<Any>,
) {
    `8-2_1`(
        tekst = "Må ha arbeidet i 28 dager før arbeidsuførhet inntreffer",
        paragraf = Paragraf.`8-2`
    )
}

enum class Utfall {
    JA,
    NEI,
    IKKE_VURDERT,
    IKKE_RELEVANT,
}

data class Vilkårsvurdering(
    val vilkår: Henvisning,
    val utfall: Utfall,
)

class FirstTest {

    @Test
    fun test1() {
        Vilkårsvurdering(
            vilkår = Henvisning.`8-2_1`,
            utfall = Utfall.JA,
        )
        assertEquals(1, 1)
    }
}
