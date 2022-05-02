/*
 * Generated by Robotoworks Mechanoid
 */
package eu.masconsult.template.recipes.content;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import java.util.Set;
import com.robotoworks.mechanoid.db.MechanoidContentProvider;
import com.robotoworks.mechanoid.db.MechanoidSQLiteOpenHelper;
import com.robotoworks.mechanoid.db.DefaultContentProviderActions;
import com.robotoworks.mechanoid.db.ContentProviderActions;
import eu.masconsult.template.recipes.content.AbstractRecipesDBOpenHelper.Sources;
import eu.masconsult.template.recipes.content.RecipesRecord;
import eu.masconsult.template.recipes.content.IngredientsRecord;

public abstract class AbstractRecipesDBContentProvider extends MechanoidContentProvider {

	protected static final int RECIPES = 0;
	protected static final int RECIPES_ID = 1;
	protected static final int INGREDIENTS = 2;
	protected static final int INGREDIENTS_ID = 3;

	protected static final int CATEGORIES = 4;
	protected static final int CATEGORIES_ID = 5;
	protected static final int SEARCH = 6;
	protected static final int SEARCH_ID = 7;
	protected static final int SEARCH_WITH_RECIPE = 8;
	protected static final int SEARCH_WITH_RECIPE_ID = 9;
	

	
	public static final int NUM_URI_MATCHERS = 10;

	@Override
    protected UriMatcher createUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RecipesDBContract.CONTENT_AUTHORITY;

		matcher.addURI(authority, "recipes", RECIPES);
		matcher.addURI(authority, "recipes/#", RECIPES_ID);
		matcher.addURI(authority, "ingredients", INGREDIENTS);
		matcher.addURI(authority, "ingredients/#", INGREDIENTS_ID);
		matcher.addURI(authority, "categories", CATEGORIES);
		matcher.addURI(authority, "categories/#", CATEGORIES_ID);
		matcher.addURI(authority, "search", SEARCH);
		matcher.addURI(authority, "search/#", SEARCH_ID);
		matcher.addURI(authority, "search_with_recipe", SEARCH_WITH_RECIPE);
		matcher.addURI(authority, "search_with_recipe/#", SEARCH_WITH_RECIPE_ID);

		// User Actions
        return matcher;
    }
    
    @Override
    protected String[] createContentTypes() {
		String[] contentTypes = new String[NUM_URI_MATCHERS];

		contentTypes[RECIPES] = RecipesDBContract.Recipes.CONTENT_TYPE;
		contentTypes[RECIPES_ID] = RecipesDBContract.Recipes.ITEM_CONTENT_TYPE;
		contentTypes[INGREDIENTS] = RecipesDBContract.Ingredients.CONTENT_TYPE;
		contentTypes[INGREDIENTS_ID] = RecipesDBContract.Ingredients.ITEM_CONTENT_TYPE;
		contentTypes[CATEGORIES] = RecipesDBContract.Categories.CONTENT_TYPE;
		contentTypes[CATEGORIES_ID] = RecipesDBContract.Categories.ITEM_CONTENT_TYPE;
		contentTypes[SEARCH] = RecipesDBContract.Search.CONTENT_TYPE;
		contentTypes[SEARCH_ID] = RecipesDBContract.Search.ITEM_CONTENT_TYPE;
		contentTypes[SEARCH_WITH_RECIPE] = RecipesDBContract.SearchWithRecipe.CONTENT_TYPE;
		contentTypes[SEARCH_WITH_RECIPE_ID] = RecipesDBContract.SearchWithRecipe.ITEM_CONTENT_TYPE;
		
		return contentTypes;
    }

	@Override
	protected MechanoidSQLiteOpenHelper createOpenHelper(Context context) {
        return new RecipesDBOpenHelper(context);
	}
	
	@Override
	protected Set<Uri> getRelatedUris(Uri uri) {
		return RecipesDBContract.REFERENCING_VIEWS.get(uri);
	}
    
    @Override
    protected ContentProviderActions createActions(int id) {
    	switch(id) {
			case RECIPES: 
				return createRecipesActions();
			case RECIPES_ID:
				return createRecipesByIdActions();
			case INGREDIENTS: 
				return createIngredientsActions();
			case INGREDIENTS_ID:
				return createIngredientsByIdActions();
			case CATEGORIES:
				return createCategoriesActions();
			case CATEGORIES_ID: 
				return createCategoriesByIdActions();
			case SEARCH:
				return createSearchActions();
			case SEARCH_ID: 
				return createSearchByIdActions();
			case SEARCH_WITH_RECIPE:
				return createSearchWithRecipeActions();
			case SEARCH_WITH_RECIPE_ID: 
				return createSearchWithRecipeByIdActions();
			default:
				throw new UnsupportedOperationException("Unknown id: " + id);
    	}
    }
    
    protected ContentProviderActions createRecipesByIdActions() {
    	return new DefaultContentProviderActions(Sources.RECIPES, true, RecipesRecord.getFactory());
    }
    
    protected ContentProviderActions createRecipesActions() {
    	return new DefaultContentProviderActions(Sources.RECIPES, false, RecipesRecord.getFactory());
    }
    
    protected ContentProviderActions createIngredientsByIdActions() {
    	return new DefaultContentProviderActions(Sources.INGREDIENTS, true, IngredientsRecord.getFactory());
    }
    
    protected ContentProviderActions createIngredientsActions() {
    	return new DefaultContentProviderActions(Sources.INGREDIENTS, false, IngredientsRecord.getFactory());
    }
    
    protected ContentProviderActions createCategoriesByIdActions() {
    	return new DefaultContentProviderActions(Sources.CATEGORIES, true, CategoriesRecord.getFactory());
    }
    
    protected ContentProviderActions createCategoriesActions() {
    	return new DefaultContentProviderActions(Sources.CATEGORIES, false, CategoriesRecord.getFactory());
    }
    
    protected ContentProviderActions createSearchByIdActions() {
    	return new DefaultContentProviderActions(Sources.SEARCH, true, SearchRecord.getFactory());
    }
    
    protected ContentProviderActions createSearchActions() {
    	return new DefaultContentProviderActions(Sources.SEARCH, false, SearchRecord.getFactory());
    }
    
    protected ContentProviderActions createSearchWithRecipeByIdActions() {
    	return new DefaultContentProviderActions(Sources.SEARCH_WITH_RECIPE, true, SearchWithRecipeRecord.getFactory());
    }
    
    protected ContentProviderActions createSearchWithRecipeActions() {
    	return new DefaultContentProviderActions(Sources.SEARCH_WITH_RECIPE, false, SearchWithRecipeRecord.getFactory());
    }
    
}
