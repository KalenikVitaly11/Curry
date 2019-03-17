package com.styleru.curry.domain.bookmarks.add;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.bookmarks.BookmarksRepository;

import javax.inject.Inject;

public class AddOrRemoveBookmarkInteractorImpl implements AddOrRemoveBookmarkInteractor {

    private BookmarksRepository repository;

    @Inject
    public AddOrRemoveBookmarkInteractorImpl(BookmarksRepository repository){
        this.repository = repository;
    }

    /**
     *
     * @param recipe Рецепт, который надо добавить в БД
     * @return true, если добавили
     *         false, если удалили
     */
    @Override
    public boolean saveRecipe(Recipe recipe) {
        // Проверяем, есть ли данный рецепт в БД
        // Если есть, то удаляем его
        if (repository.checkRecipe(recipe)){
            repository.removeRecipe(recipe);
            return false;
        }else {
            repository.saveRecipe(recipe);
            return true;
        }
    }
}
