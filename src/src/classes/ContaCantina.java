package classes;

import Enums.TipoContaEnum;

public class ContaCantina {
    // Atributos privados
    private TipoContaEnum tipoConta;
    private double saldo;
    private double limite;
    private double saldoMinimo;


    public  ContaCantina(double saldo) {
        this.saldo = saldo;
        this.tipoConta = TipoContaEnum.ALUNO;
        this.saldoMinimo = 10.0;
        this.limite = 0.0;
    }

    public ContaCantina(TipoContaEnum tipoConta, double saldo) {
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.saldoMinimo = 10.0;

        if (this.tipoConta == TipoContaEnum.PROFESSOR) {
            this.limite = 100.0;
        } else {
            this.limite = 0.0;
        }
    }

    public void emitirAlertaSaldoBaixo() {
        if (this.saldo <= this.saldoMinimo) {
            System.out.printf("ALERTA DE SALDO BAIXO: R$ ", this.saldo);
        } else {
            System.out.printf("Saldo: R$ ", this.saldo);
        }
    }

    public void debitar(double valor) {
        boolean debitoRealizado = false;

        if (this.tipoConta == TipoContaEnum.DIRETOR) {

            if (this.saldo + this.limite >= valor) {
                this.saldo -= valor;
                debitoRealizado = true;
            }
        }
        else if (this.tipoConta == TipoContaEnum.PROFESSOR) {
            if (this.saldo >= valor || valor <= (this.saldo + this.limite)) {
                this.saldo -= valor;
                debitoRealizado = true;
            }
        }
        else if (this.tipoConta == TipoContaEnum.ALUNO) {
            if (this.saldo >= valor) {
                this.saldo -= valor;
                debitoRealizado = true;
            }
        }
        if (debitoRealizado) {
            emitirAlertaSaldoBaixo();
        } else {
            System.out.println("Conta sem saldo suficiente");
        }
    }

    public void creditar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            if (this.tipoConta == TipoContaEnum.ALUNO && valor > 100.0) {
                this.saldo += 5.0;
            }
            emitirAlertaSaldoBaixo();
        }
    }

    public void setSaldoMinimo(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
        emitirAlertaSaldoBaixo();
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public double getSaldoMinimo() {
        return saldoMinimo;
    }
}