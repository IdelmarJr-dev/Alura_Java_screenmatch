package br.com.alura.screenmatch.model;


import br.com.alura.screenmatch.service.ConsultaGemini;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

// indica que é uma tabela
@Entity
// indica o nome da tabela
@Table(name = "Series")
public class Serie {
    // indica que é im ID
    @Id
    // indica como o ID vai ser gerado
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // como o Table, tem propriedades e esta indicando que o titulo deve ser unico
    @Column(unique = true)
    private String titulo;
    // indica o tipo da numeracao, porque categoria é um enum
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private Integer totalTemporadas;
    private Double avaliacao;
    private String atores;
    private String ano;
    private String duracao;
    private String Poster;
    private String Sinopse;

    //ignora atributo @Transient

    //mapeamento
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie() {}


    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dadosSerie.avaliacao())).orElse(0.0);
        this.atores = dadosSerie.atores();
        this.ano = dadosSerie.ano();
        this.duracao = dadosSerie.duracao();
        this.Poster = dadosSerie.Poster();
        try {
            this.Sinopse = ConsultaGemini.obterTraducao(dadosSerie.Sinopse()).trim(); //trim() elimina caracteres em branco, quebra de linhas...
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getSinopse() {
        return Sinopse;
    }

    public void setSinopse(String sinopse) {
        Sinopse = sinopse;
    }


    @Override
    public String toString() {
        return  "genero=" + genero +
                ", titulo='" + titulo  +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", atores='" + atores +
                ", ano='" + ano +
                ", duracao='" + duracao +
                ", Poster='" + Poster +
                ", Sinopse='" + Sinopse +
                ", Episodios='" + episodios;
    }
}
