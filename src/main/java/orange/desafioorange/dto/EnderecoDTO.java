package orange.desafioorange.dto;

import orange.desafioorange.model.Endereco;

public class EnderecoDTO {

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String estado;

    private String numero;

    private String cidade;

    public EnderecoDTO(Endereco endereco) {
        setCep(endereco.getCep());
        setBairro(endereco.getBairro());
        setCidade(endereco.getCidade());
        setComplemento(endereco.getComplemento());
        setEstado(endereco.getEstado());
        setLogradouro(endereco.getLogradouro());
        setNumero(endereco.getNumero());
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
