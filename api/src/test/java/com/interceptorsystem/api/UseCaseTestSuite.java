package com.interceptorsystem.api;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Esta é uma Suíte de Testes.
 * A anotação @Suite diz ao JUnit para tratar esta classe como um agregador de testes.
 * A anotação @SelectPackages instrui a suíte a encontrar e rodar todas as classes
 * de teste dentro do pacote especificado (e seus subpacotes).
 *
 * Ao rodar esta classe, você executará todos os testes de UseCase de uma só vez.
 */
@Suite
@SelectPackages("com.interceptorsystem.api.usecase")
public class UseCaseTestSuite {
    // O corpo da classe pode ficar vazio.
    // Ela serve apenas como um ponto de entrada para rodar os testes selecionados.
}
