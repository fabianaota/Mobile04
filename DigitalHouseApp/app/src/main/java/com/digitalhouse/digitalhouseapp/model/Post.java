package com.digitalhouse.digitalhouseapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

// POJO - Plain Old Java Object
// No modelo MVC as classes do tipo POJO fazem parte da Model (Model View Controller)
// e representam os dados (modelo) do aplicativo
public class Post implements Serializable {

    // O nome do atributo deve ser igual ao retornado da API
    // Se for diferente, é necessário utilizar o @SerializedName("nomeDoAtributoNaAPI")
    @SerializedName("title")
    private String titulo;

    @SerializedName("author")
    private String autor;

    @SerializedName("description")
    private String descricao;

    @SerializedName("imageUrl")
    private String imagemUrl;

    @SerializedName("date")
    private String dataCriacao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
