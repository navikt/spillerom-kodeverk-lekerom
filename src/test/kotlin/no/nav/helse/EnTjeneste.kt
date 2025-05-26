package no.nav.helse

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID


data class Saksbehandlingsperiode(
    val person: String,
    val fom: LocalDate,
    val tom: LocalDate,
    val uuid:UUID = UUID.randomUUID(),
)

data class VurdertVilkår(
    val periodeId: UUID,
    val vilkårKode: String,
    val status: VilkårStatus,
    val fordi: String,
)

enum class VilkårStatus {
    OPPFYLLT,
    IKKE_OPPFYLLT,
    IKKE_RELEVANT,
    IKKE_VURDERT,
}

class EnTjeneste {
    val saksbehandlingsperioder = mutableListOf<Saksbehandlingsperiode>()
    val vurdertVilkår = mutableListOf<VurdertVilkår>()

    // POST "/v1/{personId}/saksbehandlingsperioder" { fom, tom }
    fun postSaksbehandlingsperioder(personId: String, fom: LocalDate, tom: LocalDate) : UUID {
        val per = Saksbehandlingsperiode(personId, fom, tom)
        saksbehandlingsperioder += per
        return per.uuid
    }

    fun lagreVurdertVilkår(periode: UUID, vilkårKode: String, status: VilkårStatus, fordi: String) {
        val v = VurdertVilkår(
            periodeId = periode, vilkårKode = vilkårKode, status = status, fordi = fordi,
        )
        vurdertVilkår += v
    }
}

class EnTjenesteTest {

    @Test
    fun test1() {
        val t = EnTjeneste()
        val pers = "1"
        val periode = t.postSaksbehandlingsperioder(pers, LocalDate.parse("2025-01-01"), LocalDate.parse("2025-01-31"))
        t.lagreVurdertVilkår(periode,
            vilkårKode = "MÅ_SØKE_INNEN_TRE_MÅNEDER",
            status = VilkårStatus.OPPFYLLT,
            fordi = "INNEN_TRE_MÅNEDER")

    }



}

/*

Vedtaks-hjemmel
Ytelseskode
Vilkårshjemmel
Vilkårskode
Beskrivelse
Beregningshjemmel
Beregningskode
Ftrl 8-34
SYKSN
Ftrl 22-13 3
SØKDM
En ytelse som gis pr. dag eller pr. måned, gis for opptil tre måneder før den måneden da kravet ble satt fram


Ftrl 2-1
BOINO
Personer som er bosatt i Norge, er pliktige medlemmer i folketrygden


Ftrl 8-4
ARBUFØR
Er arbeidsufør
Ftrl 8-4
STARTUFØR
Ftrl 8-2 1
OPPTJT
Har vært i arbeid i minst fire uker (opptjeningstid)
Ftrl 8-4
STARTUFØR
Ftrl 8-3 2
MINSTEINNT
Har opparbeidet minste inntekt (1/2G)- inntektsgrunnlaget
Ftrl 8-3 2
INNGRUNN
Ftrl 8-3
INNTAP
Har tapt pensjonsgivende inntekt på grunn av arbeidsuførhet
Ftrl 3-15
PENINN















Hvis SYKSN= innvilgelse


Sykepengegrunnlaget for SN
Ftrl 8-35
SYKGRUNNSN
Til selvstendig næringsdrivende ytes det sykepenger med 80% av sykepengegrunnlaget
Ftrl 8-34 1
SYKPENGSN
Ventetid for selvstendig næringsdrivende
Ftrl 8-34 2
VENTESN
Har forsikring
Ftrl 8-36
FORSIKTSN









 */



/*
Vedtakshjemmel (f.eks: 8-47/Selvstendige)
    Hovedbestemmelsen som gir rett til en ytelse.
    Referanse til: -
        - En Lov
        - En (unik)Kode.
    En beskrivende tekst(?)

Vilkår:
    Referanse til:
        - En Lov
        - En (unik)Kode.
    En beskrivelse som forteller saksbehandler hva som skal vurderes.
    Mulige svar
        Ja
            Fordi
        Nei
            Fordi
        Ikke relevant
            (Fordi)
        Ikke vurdert

Beregningsregler:
    Beskrivende tekst
    Referanse til:
        - En Lov
        - En (unik)Kode.

PAKKE =
    - 1 Vedtakshjemmel (m/navn)
    - liste av Vilkår og Beregningsregler
 */