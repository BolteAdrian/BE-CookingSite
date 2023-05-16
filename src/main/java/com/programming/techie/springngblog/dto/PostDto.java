package com.programming.techie.springngblog.dto;

public class PostDto {
    private Long id;
    private String content;
    private String title;
    private String username;
    private String shortDescription;
    private String category;
    private String picture;
    private String methodOfPreparation;
    private String ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCategory(String category)  {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
    public void setPicture(String picture)  {
        this.picture = picture;
    }
    public String getPicture() {
        return picture;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getShortDescription() {
        return shortDescription;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }

    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

}
