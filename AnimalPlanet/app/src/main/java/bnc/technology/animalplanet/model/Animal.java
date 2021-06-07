package bnc.technology.animalplanet.model;

public class Animal {

    private int id;
    private int som;
    private int imagemCinza;
    private int imagem;
    private String nome;
    private String[] opcoes;
    private int video;
    private boolean canShowCorrectAnswer;

    public Animal() {
        this.canShowCorrectAnswer = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSom() {
        return som;
    }

    public void setSom(int som) {
        this.som = som;
    }

    public int getImagemCinza() {
        return imagemCinza;
    }

    public void setImagemCinza(int imagemCinza) {
        this.imagemCinza = imagemCinza;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(String[] opcoes) {
        this.opcoes = opcoes;
    }

    public boolean isCanShowCorrectAnswer() {
        return canShowCorrectAnswer;
    }

    public void setCanShowCorrectAnswer(boolean canShowCorrectAnswer) {
        this.canShowCorrectAnswer = canShowCorrectAnswer;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }
}
