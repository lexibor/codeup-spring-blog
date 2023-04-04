package com.codeup.codeupspringblog.models;

import java.util.HashSet;
import java.util.Set;

public class Categories
{
    public static Set<Category> makeCategorySet(String categoryCsv){
        // create an empty list of tag objects to hold the tag objects
        Set<Category> categoryObjects = new HashSet<>();
        // if the user did not submit a tag, the tag input
        // returns an empty string. In this case we want
        // an empty set, so no tag is saved
        // otherwise it will save an empty string to the db
        if (categoryCsv.equals("")){
            return categoryObjects;
        }
        // create an array of strings from a comma separated list of tags and loop over it
        for (String category : categoryCsv.split(",")){
            // turn each string into a category object
            Category categoryObject = new Category(category.trim());
            // add the objects to the set
            categoryObjects.add(categoryObject);
        }
        // return the set
        return categoryObjects;
    }
}

