package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table>{

    private Collection<Table> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(this.tables);
    }

    @Override
    public void add(Table table) {
        this.tables.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        return this.tables.stream().filter(t ->
                t.getTableNumber() == number).findFirst().orElse(null);
    }
}
