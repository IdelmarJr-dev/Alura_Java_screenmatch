package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    //List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int numeroTemporadas, double numeroAvaliacao);

    /* por sinal faz o mesmo da derived querie de cima
    @Query(value = "select * from series WHERE series.total_temporadas <= 5 AND series.avaliacao >= 7.5", nativeQuery = true)
    List<Serie> seriesPorTemporadaEAValiacao(int totalTemporadas, double avaliacao);
     */

    // sililar com a query nativa acima
    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :numeroTemporadas AND s.avaliacao >= :numeroAvaliacao") // os : ao lado da variavel significa para o jpqa que esse é um parametro que esta varievel significa
    List<Serie> seriesPorTemporadaEAValiacao(int numeroTemporadas, double numeroAvaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nomeTrecho%")
    List<Episodio> EpisodioPorTrecho(String nomeTrecho);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);
}
