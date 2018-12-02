package kn.beautynow.dominio;

import java.util.List;

public class Galeria {
    private int id;
    private List<Imagem> galeria;

    private List getGaleria() {
        return galeria;
    }

    private void setGaleria(List galeria) {
        this.galeria = galeria;
    }

    private void addImagem(Imagem imagem){
        this.galeria.add(imagem);
    }

    private void apagarImagem(Imagem imagem){
        this.galeria.remove(imagem);
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}
