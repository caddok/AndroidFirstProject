package com.example.georgi.allaboutthemoney.utils.repository;

import com.example.georgi.allaboutthemoney.utils.models.Dream;
import com.example.georgi.allaboutthemoney.utils.models.Expense;
import com.example.georgi.allaboutthemoney.utils.models.MonthlySummary;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.function.Consumer;

public class FirebaseRepository<T> implements Repository<T> {
    private static FirebaseRepository expenseRepositoryInstance;
    private static FirebaseRepository dreamRepositoryInstance;
    private static FirebaseRepository summaryRepositoryInstance;
    private final FirebaseFirestore mDb;
    private final Class<T> mKlass;
    private final String mCollectionName;

    public static FirebaseRepository getExpenseRepositoryInstance() {
        if (expenseRepositoryInstance != null) {
            return expenseRepositoryInstance;
        }
        expenseRepositoryInstance =  new FirebaseRepository(Expense.class);
        return expenseRepositoryInstance;
    }

    public static FirebaseRepository getDreamRepositoryInstance() {
        if (dreamRepositoryInstance != null) {
            return dreamRepositoryInstance;
        }
        dreamRepositoryInstance = new FirebaseRepository(Dream.class);
        return dreamRepositoryInstance;
    }

    public static FirebaseRepository getSummaryRepositoryInstance() {
        if (summaryRepositoryInstance != null) {
            return summaryRepositoryInstance;
        }
        summaryRepositoryInstance = new FirebaseRepository(MonthlySummary.class);
        return summaryRepositoryInstance;
    }

    private FirebaseRepository(Class<T> klass) {
        mDb = FirebaseFirestore.getInstance();
        mKlass = klass;
        mCollectionName = klass.getSimpleName().toLowerCase() + "s";
    }

    @Override
    public void getAllFromQuery(Consumer<List<T>> action) {
        mDb.collection(mCollectionName)
                .get()
                .addOnCompleteListener(task -> {
                   List<T> items = task
                           .getResult()
                           .toObjects(mKlass);
                   action.accept(items);
                });
    }

    @Override
    public void addDream(T dream, Consumer<T> action) {
        mDb.collection(mCollectionName)
                .add(dream)
                .addOnCompleteListener(task -> action.accept(dream));
    }

    @Override
    public void addExpense(T expense, Consumer<T> action) {
        mDb.collection(mCollectionName)
                .add(expense)
                .addOnCompleteListener(task -> action.accept(expense));
    }

    @Override
    public void addSummary(T summary, Consumer<T> action) {
        mDb.collection(mCollectionName)
                .add(summary)
                .addOnCompleteListener(task -> action.accept(summary));
    }


}
