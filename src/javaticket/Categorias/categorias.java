package javaticket.Categorias;

public abstract class categorias {

    private int codigo;
    private String titulo;
    private String descripcion;
    private String fecha;
    private double costo;
    private double multa;
    private boolean realizado;
    private boolean cancelado;
    private int capacidad;

    public categorias(int codigo, String titulo, String descripcion, String fecha, double costo, int capacidad) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
        this.realizado = false; //no realizado
        this.cancelado = false; //no cancelado
        this.multa = 0;  // no pagada
        this.capacidad = capacidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCapacidad() {
        return capacidad;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
    public void setCapacidad(int capacidad)
    {
    this.capacidad = capacidad;
    }

    public abstract double calcularMulta();
}
