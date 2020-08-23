package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	ArrayList<Time> times = new ArrayList<Time>();
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (times.stream().anyMatch(time -> time.getId() == id)) throw new IdentificadorUtilizadoException();
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (times.stream().noneMatch(time -> time.getId() == idTime)) throw new TimeNaoEncontradoException();
		if (jogadores.stream().anyMatch(jogador -> jogador.getId() == id)) throw new IdentificadorUtilizadoException();
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	public void definirCapitao(Long idJogador) {
		Optional<Jogador> novoCapitao = jogadores.stream().filter(jogador -> jogador.getId() == idJogador).findFirst();

		if (novoCapitao.isPresent()) {
			times.stream().filter(time -> time.getId() == novoCapitao.get().getIdTime()).findFirst().get().setCapitao(novoCapitao.get());
		} else {
			throw new JogadorNaoEncontradoException();
		}
	}

	public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException {
		Optional<Time> timeInformado = times.stream().filter(time -> time.getId() == idTime).findFirst();
		if (timeInformado.isPresent()) {
			Jogador capitao = timeInformado.get().getCapitao();
			if (capitao != null) return capitao.getId();
			else throw new CapitaoNaoInformadoException();
		} else throw new TimeNaoEncontradoException();
	}

	public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException {
		Optional<Jogador> jogadorInformado = jogadores.stream().filter(jogador -> jogador.getId() == idJogador).findFirst();
		if (jogadorInformado.isPresent()) return jogadorInformado.get().getNome();
		else throw new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException {
		Optional<Time> timeInformado = times.stream().filter(time -> time.getId() == idTime).findFirst();
		if (timeInformado.isPresent()) return timeInformado.get().getNome();
		else throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException {
		Optional<Time> timeInformado = times.stream().filter(time -> time.getId() == idTime).findFirst();
		if (timeInformado.isPresent()) {
			List<Long> jogadoresDoTime = new ArrayList();
			jogadores.stream().forEach(jogador -> {
				if (jogador.getIdTime() == idTime) jogadoresDoTime.add(jogador.getId());
			});
			return jogadoresDoTime;
		} else throw new TimeNaoEncontradoException();
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException {
		Optional<Time> timeInformado = times.stream().filter(time -> time.getId() == idTime).findFirst();
		if (timeInformado.isPresent()) {
			return jogadores.stream().max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
		} else throw new TimeNaoEncontradoException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException {
		Optional<Time> timeInformado = times.stream().filter(time -> time.getId() == idTime).findFirst();
		if (timeInformado.isPresent()) {
			return jogadores.stream().min(Comparator.comparing(Jogador::getDataNascimento)).get().getId();
		} else throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarTimes() {
		List<Long> idsOrdenados = new ArrayList();
		if (times.isEmpty()) return new ArrayList();
		else {
			times.stream().sorted(Comparator.comparing(Time::getId)).forEach(time -> {
				idsOrdenados.add(time.getId());
			});
			return idsOrdenados;
		}
	}

	public Long buscarJogadorMaiorSalario(Long idTime) throws TimeNaoEncontradoException {
		Optional<Time> timeInformado = times.stream().filter(time -> time.getId() == idTime).findFirst();
		if (timeInformado.isPresent()) {
			return jogadores.stream().max(Comparator.comparing(Jogador::getSalario)).get().getId();
		} else throw new TimeNaoEncontradoException();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException {
		Optional<Jogador> jogadorInformado = jogadores.stream().filter(jogador -> jogador.getId() == idJogador).findFirst();
		if (jogadorInformado.isPresent()) return jogadorInformado.get().getSalario();
		else throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> topJogadores = new ArrayList();
		if (jogadores.isEmpty()) return new ArrayList();
		else {
			jogadores.stream().sorted(Comparator.comparing(Jogador::getNivelHabilidade).thenComparing(Jogador::getId).reversed()).forEach(jogador -> {
				topJogadores.add(jogador.getId());
			});
			return topJogadores.subList(0, top);
		}
	}
}
