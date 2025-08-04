package javaticket.Categorias;

public class categorias {

    private int codigo;
    private String titulo;
    private String descripcion;
    private String fecha;
    private double costo;

    // Nuevos atributos para estado
    private boolean realizado;  // true si ya se realizó el evento
    private boolean cancelado;  // true si fue cancelado

    public categorias(int codigo, String titulo, String descripcion, String fecha, double costo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
        this.realizado = false; // por defecto no realizado
        this.cancelado = false; // por defecto no cancelado
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public double getCosto() {
        return costo;
    }

    // Getters y setters para los nuevos estados
    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
}
