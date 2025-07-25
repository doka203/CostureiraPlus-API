package com.cefet.CostureiraPlus.dto;

import java.time.LocalDate;

public class DataPagamentoDTO {

    private LocalDate dataPagamento;

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}