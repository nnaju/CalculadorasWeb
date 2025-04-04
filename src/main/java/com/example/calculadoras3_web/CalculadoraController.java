package com.example.calculadoras3_web;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalculadoraController {

    // Endpoint para cálculo do IMC
    @PostMapping("/calcular-imc")
    public Map<String, Object> calcularIMC(@RequestParam double peso, @RequestParam double altura) {
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser valores positivos.");
        }

        double imc = peso / (altura * altura);
        String descricao;

        if (imc < 18.5) {
            descricao = "Abaixo do peso";
        } else if (imc < 24.9) {
            descricao = "Peso normal";
        } else if (imc < 29.9) {
            descricao = "Sobrepeso";
        } else if (imc < 34.9) {
            descricao = "Obesidade grau I";
        } else if (imc < 39.9) {
            descricao = "Obesidade grau II";
        } else {
            descricao = "Obesidade grau III";
        }

        Map<String, Object> response = new HashMap<>();
        response.put("tipo", "IMC");
        response.put("valor", String.format("%.2f", imc));
        response.put("descricao", descricao);
        return response;
    }

    // Endpoint para cálculo de Creatina
    @PostMapping("/calcular-creatina")
    public Map<String, Object> calcularCreatina(@RequestParam double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser um valor positivo.");
        }

        double creatina = peso * 0.03; // Exemplo: 0.03g por kg de peso

        Map<String, Object> response = new HashMap<>();
        response.put("tipo", "Creatina");
        response.put("valor", String.format("%.2f", creatina));
        response.put("observacao", "É importante beber bastante água ao tomar creatina.");
        return response;
    }

    // Endpoint para cálculo da TMB
    @PostMapping("/calcular-tmb")
    public Map<String, Object> calcularTMB(
            @RequestParam int idade,
            @RequestParam double peso,
            @RequestParam double altura,
            @RequestParam String sexo) {

        if (peso <= 0 || altura <= 0 || idade <= 0) {
            throw new IllegalArgumentException("Peso, altura e idade devem ser valores positivos.");
        }

        double tmb;
        if ("masculino".equalsIgnoreCase(sexo)) {
            tmb = 66 + (13.7 * peso) + (5 * altura) - (6.8 * idade);
        } else {
            tmb = 655 + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("tipo", "TMB");
        response.put("valor", String.format("%.2f", tmb));
        response.put("explicacao", "A Taxa Metabólica Basal (TMB) indica quantas calorias seu corpo precisa em repouso.");
        return response;
    }
}