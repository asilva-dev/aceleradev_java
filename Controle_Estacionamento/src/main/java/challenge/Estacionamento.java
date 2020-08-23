package challenge;

import java.util.ArrayList;

public class Estacionamento {

    ArrayList<Carro> carros = new ArrayList<Carro>();

    public void estacionar(Carro carro) {
        if (carro.getMotorista() == null)
            throw new EstacionamentoException("O carro precisa de um motorista!");
        ;
        if (carro.getMotorista().getPontos() > 20)
            throw new EstacionamentoException("Motorista com habilitação suspensa!");
        if (carro.getMotorista().getIdade() < 18)
            throw new EstacionamentoException("Motorista ter mais de 18 anos!");

        if (this.carros.size() == 10) {
            for (Carro carroEstacionado : carros) {
                if (carroEstacionado.getMotorista().getIdade() < 55) {
                    carros.remove(carroEstacionado);
                    carros.add(carro);
                    return;
                }
            }
            throw new EstacionamentoException("Sem vagas");
        }
        carros.add(carro);
    }

    public int carrosEstacionados() {
        return this.carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        if (carros.contains(carro))
            return true;
        return false;
    }
}