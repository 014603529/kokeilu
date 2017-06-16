
package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

public class Keskustelualue {
    
    private int id_keskustelualue;
    private String nimi_keskustelualue;
    private List<Keskustelu> keskustelut;
    
    public Keskustelualue(int id, String nimi){
        
        this.id_keskustelualue=id;
        this.nimi_keskustelualue=nimi;
        this.keskustelut = new ArrayList<>();
        
    }

    public int getId() {
        return id_keskustelualue;
    }

    public void setId(int id) {
        this.id_keskustelualue = id;
    }

    public String getNimi() {
        return nimi_keskustelualue;
    }

    public void setNimi(String nimi) {
        this.nimi_keskustelualue = nimi;
    }

    public List<Keskustelu> getKeskustelut() {
        return keskustelut;
    }

    public void setKeskustelut(List<Keskustelu> keskustelut) {
        this.keskustelut = keskustelut;
    }
    
    // Metodi lisää keskustelun listalle vain jos keskustelu ei ole jo listalla. Palauttaa true jos keskustelu lisättiin ja false jos keskustelu oli jo listalla ja sitä ei lisätty.
    public boolean addKeskustelu(Keskustelu lisattavaKeskustelu) {
        
        // Tarkastetaan onko lisättävä keskustelu jo listalla.
        boolean keskusteluOnJoListassa = false;
        for (Keskustelu keskustelu : this.keskustelut) {
            if (keskustelu.getId() == lisattavaKeskustelu.getId()) {
                keskusteluOnJoListassa = true;
            }
        }
        
        // Jos keskustelu ei ollut jo listalla, lisätään se. Jos keskustelu oli jo listalla, ei lisätä.
        if (!keskusteluOnJoListassa) {
            this.keskustelut.add(lisattavaKeskustelu);
            return true;
        } else {
            return false;
        }
    }

}
