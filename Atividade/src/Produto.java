import java.time.LocalDate;

// import java.util.Date;
// import jdk.vm.ci.meta.Local;

public class Produto {
    
    private int codigo;
	public int getCodigo() { return codigo; }
	public void setCodigo(String Codigo) {codigo = Integer.parseInt(Codigo)+1;}

    private LocalDate dataEntrada;
	public LocalDate getDataEntrada() { return dataEntrada; }
	public void setDataEntrada () { dataEntrada = LocalDate.now();}

    private String local;
	public String getLocal() { return local; }
	public void setLocal(String Local) {local = Local;}

    private String tipo;
	public String getTipo() { return tipo; }
	public void setTipo(String Tipo) {tipo = Tipo;}

    private String marca;
	public String getMarca() { return marca; }
	public void setMarca(String Marca) { marca = Marca;}

    private String caracteristicas;
	public String getCaracteristicas() { return caracteristicas; }
	public void setCaracteristicas(String Caracteristicas) {caracteristicas=Caracteristicas;}

    private int tamanho;
	public int getTamanho() { return tamanho; }
	public void setTamanho(int Tamanho) {tamanho=Tamanho;}
    
    private int cor;
	public int getCor() { return cor; }
	public void setCor(int Cor) {cor = Cor;}

    private Double valorEtiqueta;
	public Double getValorEtiqueta() { return valorEtiqueta; }
	public void setValorEtiqueta(Double ValorEtiqueta) {valorEtiqueta=ValorEtiqueta;}

    private Double valorPago;
	public Double getValorPago() { return valorPago; }
	public void setValorPago(Double ValorPago) {valorPago=ValorPago;}
    
    private Double valorMargem;
	public Double getValorMargem() { return valorMargem; }
	public void setValorMargem(Double ValorPago) { valorMargem = ValorPago * 2; }

    private Double precoSugerido;
	public Double getPrecoSugerido() { return precoSugerido; }
	public void setPrecoSugerido(Double Preco) {precoSugerido=Preco;}

}
