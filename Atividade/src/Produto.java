import java.time.LocalDate;

// import java.util.Date;
// import jdk.vm.ci.meta.Local;

public class Produto {
    
    private int codigo;
	public int getCodigo() { return codigo; }
	public void setCodigo() {}

    private LocalDate dataEntrada;
	public LocalDate getDataEntrada() { return dataEntrada; }
	public void setDataEntrada () { LocalDate dataEntrada = LocalDate.now();}

    private String local;
	public String getLocal() { return local; }
	public void setLocal() {}

    private String tipo;
	public String getTipo() { return tipo; }
	public void setTipo() {}

    private String marca;
	public String getMarca() { return marca; }
	public void setMarca() {}

    private String caracteristicas;
	public String getCaracteristicas() { return caracteristicas; }
	public void setCaracteristicas() {}

    private int tamanho;
	public int getTamanho() { return tamanho; }
	public void setTamanho() {}
    
    private int cor;
	public int getCor() { return cor; }
	public void setCor() {}

    private Double valorEtiqueta;
	public Double getValorEtiqueta() { return valorEtiqueta; }
	public void setValorEtiqueta() {}

    private Double valorPago;
	public Double getValorPago() { return valorPago; }
	public void setValorPago() {}
    
    private Double valorMargem;
	public Double getValorMargem() { return valorMargem; }
	public void setValorMargem(Double valorPago, Double valorMargem) { valorMargem = valorPago * 2; }

    private Double precoSugerido;
	public Double getPrecoSugerido() { return precoSugerido; }
	public void setPrecoSugerido() {}

}
