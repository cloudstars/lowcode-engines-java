package io.github.cloudstars.lowcode.bpm.vendor.query;

import java.util.List;

public interface Query<T extends Query<?, ?>, U> {
    T asc();

    T desc();

    T orderBy(QueryProperty var1);

    long count();

    U singleResult();

    List<U> list();

    List<U> listPage(int var1, int var2);
}
